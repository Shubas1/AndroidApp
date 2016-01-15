package com.clone.demo.shyp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkMaster {

    Activity mMasterActivity;
    Fragment mFragment;
    LoadingDialog objLoadingDialog;
    public Map<String, String> serverUrls = new HashMap<String, String>();

    RestClient client;
    AppPreferences objAppPreferences;
    FragmentManager mFragmentManager;

    // create a constructor
    public NetworkMaster(Activity mMasterActivity) {

        this.mMasterActivity = mMasterActivity;
        this.objAppPreferences = new AppPreferences(mMasterActivity);
        this.mFragmentManager = mMasterActivity.getFragmentManager();

    }// end constructor NetworkMaster


    // programming mark ---- ---- ----  ---- ---- ----  ---- ---- ----  ---- ---- ----
    /**
     * @Class: register Check for authentication
     */

    // Method to run the Async task
    public void runRegisterAsync() throws Exception {

        new RegisterAsync().execute();

    }// end runForgetAsync

    // pgrm mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----
    private class RegisterAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... postParams) {

            // Get the web server URL
            serverUrls = Ipsum.getServerUrls();

            //Write your url here
            client = new RestClient(serverUrls.get("login").toString());
            //client.addParam("username", postParams[0]);
            //client.addParam("password", postParams[1]);

            // Here I am specifying that the key-value pairs are sent in the form-urlencoded format
            client.addHeader("Content-Type", "application/x-www-form-urlencoded");

            // In case your server sends any response back, it will be saved in this response string.
            String response = client.executePost();

            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            System.out.println("Res : " + result);

            // return the result to master activity
            ((Register) mMasterActivity).registerResponseFromNetworkMaster(result);

        }// end onPostExecute

    }// end RegisterAsync

    // programming mark ---- ---- ----  ---- ---- ----  ---- ---- ----  ---- ---- ----
    /**
     * @Class: Login Check for authentication
     */

    // Method to run the Async task
    public void runLoginAsync(HashMap<String, String> params) throws Exception {

        System.out.println("post params" +params.get(0).toString()+" "+params.get(1).toString());
        new LoginAsync().execute(params.get(0).toString(),params.get(1).toString());

    }// end runForgetAsync

    // pgrm mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----
    private class LoginAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... postParams) {

            // Get the web server URL
            serverUrls = Ipsum.getServerUrls();

            System.out.println("post params " +postParams[0]+" "+postParams[1]);

            //Write your url here
            client = new RestClient(Ipsum.LOGIN);
            client.addParam("email", postParams[0]);
            client.addParam("password", postParams[1]);

            // Here I am specifying that the key-value pairs are sent in the form-urlencoded format
            client.addHeader("Content-Type", "application/x-www-form-urlencoded");

            // In case your server sends any response back, it will be saved in this response string.
            String response = client.executePost();

            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            System.out.println("Res : " + result);

            // return the result to master activity
            //((Login) mMasterActivity).loginResponseFromNetworkMaster(result);

        }// end onPostExecute

    }// end RegisterAsync


    // programming mark ---- ---- ----  ---- ---- ----  ---- ---- ----  ---- ---- ----

    /**
     * @Class: Get all the Shipment from server
     */

    // Method to run the Async task
    public void runShipmentAsync() throws Exception {

        new ShipmentAsync().execute();

    }// end runForgetAsync

    // pgrm mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----
    private class ShipmentAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... postParams) {

            // Get the web server URL
            serverUrls = Ipsum.getServerUrls();

            //Write your url here
            client = new RestClient(serverUrls.get("fetch_recipient").toString());

            // Here I am specifying that the key-value pairs are sent in the form-urlencoded format
            client.addHeader("Content-Type", "application/x-www-form-urlencoded");

            // In case your server sends any response back, it will be saved in this response string.
            String response = client.executePost();

            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            System.out.println("Res : " + result);

            // return the result to master activity
            //((Dashboard) mMasterActivity).dashboardResponseFromNetworkMaster(result);

        }// end onPostExecute

    }// end ShipmentAsync

}// end NetworkMaster