package com.clone.demo.shyp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Register2 extends Activity {

    RestClient client;

    LoadingDialog objLoadingDialog;

    JSONObject objJSON;

    NetworkMaster mNetworkMaster;

    PublicDelegate objPublicDelegate;

    EditText etName, etEmail, etPassword, etConfirmPassword, etMobileNumber, etZipCode, etReferralCode;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        objLoadingDialog = new LoadingDialog(this);
        objPublicDelegate = new PublicDelegate(this);

        int color = getResources().getColor(R.color.action_bar_color);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable((color)));

    }// end onCreate

    @Override
    public void onStart() {
        super.onStart();

        etName =  (EditText) findViewById(R.id.name);
        etEmail =  (EditText) findViewById(R.id.email);
        etPassword =  (EditText) findViewById(R.id.password);
        etConfirmPassword =  (EditText) findViewById(R.id.password_confirmation);
        etMobileNumber =  (EditText) findViewById(R.id.mobile_number);
        etZipCode =  (EditText) findViewById(R.id.zip_code);
        etReferralCode =  (EditText) findViewById(R.id.referral_code);

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
                    UserRegister userRegister = new UserRegister(
                            this.etName.getText().toString()
                            , this.etEmail.getText().toString()
                            , this.etPassword.getText().toString()
                            , this.etConfirmPassword.getText().toString()
                            , this.etMobileNumber.getText().toString()
                            , this.etZipCode.getText().toString()
                            , this.etReferralCode.getText().toString());

                    // create adapter for the retrofit client
                    RetrofitRestClient.registerUser().registerUser(userRegister, new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {
                            System.out.println("Register data " + new Gson().toJson(o));

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
     * @param
     * @return void
     */

    public void parseJSONValue(String reader) throws Exception {

        this.objLoadingDialog.cancel();

        UserRegister user = new GsonBuilder().create().fromJson(reader, UserRegister.class);
        Log.e("Status Code: ", user.getStatusCode() + "");

        int statusCode = user.getStatusCode();

        if (statusCode == 500) {
            objPublicDelegate.showAlertDialog("Message", user.getErrorMessage());
        }// end if
        else {
            // if once user successfully logged in get the user to the next screen
            Intent intent = new Intent(Register2.this, Dashboard.class);
            startActivity(intent);
        }

    }// end parseJSONValue

}// end main class