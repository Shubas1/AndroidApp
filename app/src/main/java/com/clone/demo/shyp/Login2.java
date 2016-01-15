package com.clone.demo.shyp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Login2 extends Activity {

    LoadingDialog objLoadingDialog;

    PublicDelegate objPublicDelegate;

    AppPreferences objAppPreferences;

    EditText etEmail, etPassword;

    // create a reference object of this activity
    public static Activity login2;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // other class can access properties of this class activity i.e finish()...
        login2 = this;

        objLoadingDialog = new LoadingDialog(this);
        objPublicDelegate = new PublicDelegate(this);
        objAppPreferences = new AppPreferences(this);

        int color = getResources().getColor(R.color.action_bar_color);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable((color)));

    }// end onCreate

    @Override
    public void onStart() {
        super.onStart();

        etEmail =  (EditText) findViewById(R.id.email);
        etPassword =  (EditText) findViewById(R.id.password);

    }// end onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:

                try {

                    System.out.println("stored values " + this.etEmail.getText().toString());

                    this.objLoadingDialog.show("Please wait...");

                    // init the user constructor to set the email and password values
                    User user = new User(this.etEmail.getText().toString(), this.etPassword.getText().toString());

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
                            System.out.println("Register error ");
                            objLoadingDialog.cancel();
                            retrofitError.printStackTrace();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Analysis of data coming form the server when user authenticated
     *
     * @param reader
     * @return void
     */

    public void parseJSONValue(String reader) throws Exception {

        this.objLoadingDialog.cancel();

        User user = new GsonBuilder().create().fromJson(reader, User.class);
        Log.e("Status Code: ", user.getStatusCode() + "");
        //Log.e("Response Email: ", user.getResponseData().getEmail()+"");

        int statusCode = user.getStatusCode();

        if (statusCode != 200) {
            objPublicDelegate.showAlertDialog("Message", user.getErrorMessage());
        }// end if
        else {

            this.objAppPreferences.setUserDefaults("AUTH_USER_ID",""+user.getResponseData().getId());

            // if once user successfully logged in get the user to the next screen
            Intent intent = new Intent(Login2.this, Dashboard.class);
            startActivity(intent);

        }

    }// end parseJSONValue

}// end main class