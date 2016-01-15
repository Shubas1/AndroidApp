package com.clone.demo.shyp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Register extends Activity {

    private ListView myList;   private MyAdapter myAdapter;

    private HashMap<String, String> textValues = new HashMap<String, String>();

    RestClient client; LoadingDialog objLoadingDialog; JSONObject objJSON;
    NetworkMaster mNetworkMaster; PublicDelegate objPublicDelegate;

    Geocoder geocoder;
    String bestProvider;
    List<Address> user = null;
    double lat;
    double lng;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        double longitude = 0;
        double latitude = 0;

        /**
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
            Location location = lm
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        } else {
            Location location = lm
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        System.out.println(" DDD latitude: " +latitude+",  longitude: "+longitude);
        */

        objLoadingDialog = new LoadingDialog(this); objPublicDelegate = new PublicDelegate(this);

        mNetworkMaster = new NetworkMaster(this);

        int color = getResources().getColor(R.color.action_bar_color);
        ActionBar bar = getActionBar(); bar.setBackgroundDrawable(new ColorDrawable((color)));

        myList = (ListView) findViewById(R.id.MyList);
        myList.setItemsCanFocus(true);
        myAdapter = new MyAdapter();
        myList.setAdapter(myAdapter);

    }// end onCreate

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public ArrayList myItems = new ArrayList();

        public MyAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < 6; i++) {
                ListItem listItem = new ListItem();
                listItem.caption = "Caption" + i;
                myItems.add(listItem);
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return myItems.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            /**
             * A ViewHolder object stores each of the component views inside the tag field of the Layout,
             * so you can immediately access them without the need to look them up repeatedly.
             * First, you need to create a class to hold your exact set of views
             *
             * */

            ViewHolder holder;

            if (convertView == null) {

                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item, null);

                holder.caption = (EditText) convertView.findViewById(R.id.ItemCaption);
                holder.co_name = (EditText) convertView.findViewById(R.id.ItemCaption);
                holder.email = (EditText) convertView.findViewById(R.id.ItemCaption);

                convertView.setTag(position);

                holder.co_name.setTag(position);
                holder.email.setTag(position);

                holder.caption.addTextChangedListener(new GenericTextWatcher(holder.caption));
                holder.co_name.addTextChangedListener(new GenericTextWatcher(holder.co_name));
                holder.email.addTextChangedListener(new GenericTextWatcher(holder.email));

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //Fill EditText with the value you have in data source
            holder.caption.setId(position);

            if(position == 0) { holder.caption.setHint("Full Name or Business");  }
            if(position == 1) { holder.caption.setHint("Email"); }
            if(position == 2) { holder.caption.setHint("Mobile Number"); }
            if(position == 3) { holder.caption.setHint("Zip Code"); }
            if(position == 4) { holder.caption.setHint("Password"); }
            if(position == 5) { holder.caption.setHint("Referral Code"); }

            return convertView;
        }
    }

    class ViewHolder {
        EditText caption;
        EditText co_name;
        EditText email;
    }

    class ListItem {
        String caption;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_register, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:

                try {

                    System.out.println("stored values "+this.textValues);

                    objLoadingDialog.show("Please wait...");

                    mNetworkMaster.runRegisterAsync();
                } catch(Exception e) {
                    e.printStackTrace();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GenericTextWatcher implements TextWatcher {

        private View view;
        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        public void afterTextChanged(Editable editable) {

            String text = editable.toString();
            //save the value for the given tag :
            Register.this.textValues.put(""+view.getTag(), editable.toString());
        }
    }

    // pgrm mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----

    /**
     * @Method: After successful register user will get back by network master
     * */

    public void registerResponseFromNetworkMaster(String result){

        objLoadingDialog.cancel();
        System.out.println("Response : " + result);

        try {

            objJSON = new JSONObject(result);
            int statusCode = Integer.parseInt(objJSON.getString("statusCode"));

            if(statusCode != 200) {
                objPublicDelegate.showAlertDialog("Message", objJSON.getString("errorMessage"));
            }// end if
            else {
                objPublicDelegate.showAlertDialog("Message", "successfully registered!!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }// end registerResponseFromNetworkMaster

}// end main class