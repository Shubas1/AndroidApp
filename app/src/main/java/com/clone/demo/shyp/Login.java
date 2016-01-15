package com.clone.demo.shyp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Login extends Activity {

    private ListView myList;   private MyAdapter myAdapter;

    private HashMap textValues = new HashMap();

    RestClient client; LoadingDialog objLoadingDialog; JSONObject objJSON;
    NetworkMaster mNetworkMaster; PublicDelegate objPublicDelegate;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        public MyAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // @required method : How many items are in the data set represented by this Adapter.
        public int getCount() {
            return 2;
        }

        // @required method: Get the data item associated with the specified position in the data set.
        public Object getItem(int position) {
            return position;
        }

        // @required method: Get the row id associated with the specified position in the list.
        public long getItemId(int position) {
            return position;
        }

        // @required method
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

                // set text according to list view
                if(position == 0){
                    holder.email = (EditText) convertView.findViewById(R.id.ItemCaption);
                    holder.email.setTag(position);
                    holder.email.addTextChangedListener(new GenericTextWatcher(holder.email));
                    holder.email.setHint("Email Address");
                } else {
                    holder.password = (EditText) convertView.findViewById(R.id.ItemCaption);
                    holder.password.setTag(position);
                    holder.password.addTextChangedListener(new GenericTextWatcher(holder.password));
                    holder.password.setHint("Password");
                }

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }

    class ViewHolder {
        EditText email;
        EditText password;
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

                    this.objLoadingDialog.show("Please wait...");
                    //mNetworkMaster.runLoginAsync(this.textValues);

                    // init the user constructor to set the email and password values
                    User user = new User(this.textValues.get(0).toString(), this.textValues.get(1).toString());

                    // create adapter for the retrofit client
                    RetrofitRestClient.getUser().authUser(user, new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {
                            Log.i("Tag", "Login data " + new Gson().toJson(o));

                            try {
                                parseJSONValue(new Gson().toJson(o));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {

                        }
                    });

                    /*RetrofitRestClient.get().getWeather("California", new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {
                            //Log.i("Tag", "Login data " + o.toString());
                            Log.i("Tag", "Login data " + new Gson().toJson(o));
                            parseJSONData(new Gson().toJson(o));
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            retrofitError.printStackTrace();
                        }
                    });
                    */

                    //Intent intent = new Intent(Login.this, Dashboard.class);
                    //startActivity(intent);

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
            Login.this.textValues.put(view.getTag(), editable.toString());
        }
    }

    // pgrm mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----

    /**
     * @Method: After successful login user will get back by network master
     * */

    public void loginResponseFromNetworkMaster(String result){

        this.objLoadingDialog.cancel();
        System.out.println("Response : " + result);

        try {

            objJSON = new JSONObject(result);
            int statusCode = Integer.parseInt(objJSON.getString("statusCode"));

            if(statusCode != 200) {
                objPublicDelegate.showAlertDialog("Message", objJSON.getString("errorMessage"));
            }// end if
            else {
                objPublicDelegate.showAlertDialog("Message", "successfully logged!!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }// end loginResponseFromNetworkMaster

    public void parseJSONData(String reader) {

        WeatherResponse weatherResponse = new GsonBuilder().create().fromJson(reader, WeatherResponse.class);
        Log.e("Base: ", weatherResponse.getBase()+"");
        Log.e("Lat Coord: ", weatherResponse.getCoord().getLat()+"");
        Log.e("Lon Coord: ", weatherResponse.getCoord().getLon()+"");

        ArrayList<Weather> weatherArrayList = weatherResponse.getWeather();

        for(Weather weather: weatherArrayList){
            Log.e("main title: ",weather.getMain()+"");
            Log.e("des title: ",weather.getDescription()+"");
        }
    }

    /**
     * Analysis of data coming form the server when user authenticated
     *
     * @param String
     *
     * @return void
     *
     * */

    public void parseJSONValue(String reader) throws Exception {

        this.objLoadingDialog.cancel();

        User user = new GsonBuilder().create().fromJson(reader, User.class);
        Log.e("Status Code: ", user.getStatusCode()+"");
        //Log.e("Response Email: ", user.getResponseData().getEmail()+"");

        int statusCode = user.getStatusCode();

        if(statusCode != 200) {
            objPublicDelegate.showAlertDialog("Message", user.getErrorMessage());
        }// end if
         else {
            // if once user successfully logged in get the user to the next screen
            Intent intent = new Intent(Login.this, Dashboard.class);
            startActivity(intent);
        }

    }// end parseJSONValue

}// end main class