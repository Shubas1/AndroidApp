package com.clone.demo.shyp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Dashboard extends Activity {


    LoadingDialog objLoadingDialog;
    PublicDelegate objPublicDelegate;

    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final String TAG = Dashboard.class.getSimpleName();

    DashboardAdapter adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // close previous activities to avoid user to go back
        ScreenSlideActivity.fragmentActivity.finish();
        Login2.login2.finish();

        objLoadingDialog = new LoadingDialog(this);
        objPublicDelegate = new PublicDelegate(this);

        int color = getResources().getColor(R.color.action_bar_color);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable((color)));

        // Enabling Up / Back navigation
        bar.setDisplayHomeAsUpEnabled(false);

        // get shipment details from server
        try {

            objLoadingDialog.show("Please wait...");
            //mNetworkMaster.runShipmentAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Construct the data source
        ArrayList<DashboardShippingData> arrayOfShipment = new ArrayList<DashboardShippingData>();

        // Create the adapter to convert the array to views
        adapter = new DashboardAdapter(this, arrayOfShipment);

        String jsonValue = Ipsum.shipment_array;

        // Or even append an entire new collection
        // Fetching some data, data has now returned
        // If data was JSON, convert to ArrayList of shipping objects.

        try {

            JSONArray jsonArray = new JSONArray(jsonValue);
            ArrayList<DashboardShippingData> newUsers = DashboardShippingData.fromJson(jsonArray);
            adapter.addAll(newUsers);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.shipping_item_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listPairedClickItem);

        new GetServerResponse().execute();

        //adapter.clear();

        /*
        // Call to server to get the shipments by id ... id belongs to user id
        RetrofitRestClient.shipmentById().shipmentsById(23, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                Log.i("Tag", "Shipment data " + new Gson().toJson(o));

                try {
                    parseJSONValue(new Gson().toJson(o));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                System.out.println("Register error ");
                objLoadingDialog.cancel();
                retrofitError.printStackTrace();
            }
        });
        */


    }// end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }// end switch

    }// end onCreateOptionsMenu

    // pgrm mark ----- -------

    // action when list view of dashboard item been clicked
    // <currently this is not in use>

    private AdapterView.OnItemClickListener listPairedClickItem = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        }// end onItemClick

    };// end listPairedClickItem

    // pgrm mark ---- ------

    public void openShippingOptions(View view) {

        Toast.makeText(Dashboard.this, "open shipping options", Toast.LENGTH_SHORT).show();

    }// end openShippingOptions

    // pgrm mark ---- ------

    public void openShipmentPopup(View view) {

        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(Dashboard.this, view);

        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.menu_dashboard_popup, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.retake_photo:
                        retakeShipmentPhoto();
                        return true;
                    default:
                        return true;
                }// end switch

                //Toast.makeText(Dashboard.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                //return true;
            }
        });

        popup.show();//showing popup menu

    }// end openShippingOptions

    // pgrm mark ----- -----
    // retake the shipment photo

    public void retakeShipmentPhoto() {

        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


    }// end retake shipment photo

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                if(fileUri != null) {
                    Log.d(TAG, "Image saved to:\n" + fileUri);
                    Log.d(TAG, "Image path:\n" + fileUri.getPath());
                    // use uri.getLastPathSegment() if store in folder
                }

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }

    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    // pgrm mark ------ ------

    public void openAddShipmentController(View view) {

        Intent intent = new Intent(Dashboard.this, AddShipmentController.class);
        startActivity(intent);

    }// end openAddShipmentController

    /**
     * Analysis of data coming form the server when user authenticated
     *
     * @param  reader
     * @return void
     */

    public void parseJSONValue(String reader) throws Exception {

        this.objLoadingDialog.cancel();

        Shipments shipments = new GsonBuilder().create().fromJson(reader, Shipments.class);
        Log.e("Status Code: ", shipments.getStatusCode() + "");
        //Log.e("Response Email: ", user.getResponseData().getEmail()+"");

        int statusCode = shipments.getStatusCode();

        if (statusCode != 200) {
            objPublicDelegate.showAlertDialog("Message", shipments.getErrorMessage());
        }// end if
          else {

            try {

                ArrayList<ShipmentsResponseData> shipmentArrayList = shipments.getShipmentsResponseData();

                for(ShipmentsResponseData shipment: shipmentArrayList){
                    //Log.e("main title: ",shipment.getAddress()+"");
                    //Log.e("des title: ",shipment.getMore_address()+"");

                }

                // remove previous data from adapter
                //adapter.clear();

                // Add new data into the adapter
                ArrayList<DashboardShippingData> newUsers = DashboardShippingData.fromGson(shipmentArrayList);
                adapter.addAll(newUsers);

                adapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }// end else

    }// end parseJSONValue

    private class GetServerResponse extends AsyncTask<Void, Void, JSONObject> {

        protected JSONObject doInBackground(Void... urls) {

                JSONObject json = getJSONFromHttpPost("http://192.168.1.14/ShypService/api/v1/apiGetShipments/23");

                //System.out.println("json from server" +json);

                return json;
        }

        protected void onPostExecute(JSONObject result) {
            System.out.println("Downloaded " + result + " bytes");

            objLoadingDialog.cancel();

            try {

                JSONArray jsonArray = result.getJSONArray("responseData");
                ArrayList<DashboardShippingData> newUsers = DashboardShippingData.fromJson(jsonArray);
                adapter.addAll(newUsers);

                adapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static JSONObject getJSONFromHttpPost(String URL) {

        try{
            // Create a new HttpClient and Post Header
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL);
            String resultString = null;

            long t = System.currentTimeMillis();
            HttpResponse response = (HttpResponse) httpclient.execute(httpPost);
            System.out.println("HTTPResponse received in [" + (System.currentTimeMillis()-t) + "ms]");
            // Get hold of the response entity (-> the data):
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                // Read the content stream
                InputStream instream = entity.getContent();

                // convert content stream to a String
                resultString= convertStreamToString(instream);
                instream.close();
                System.out.println("result String : " + resultString);

                // Transform the String into a JSONObject
                JSONObject jsonObjRecv = new JSONObject(resultString);


                return jsonObjRecv;
            }

        }catch(Exception e){e.printStackTrace();}

        return null;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line ="";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}// end main class