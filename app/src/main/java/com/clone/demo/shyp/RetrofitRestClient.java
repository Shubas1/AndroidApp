package com.clone.demo.shyp;

import com.squareup.okhttp.OkHttpClient;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RetrofitRestClient {

    private static WeatherService REST_CLIENT;
    private static UserAuthService REST_USER;
    private static UserRegisterService REST_USER_REGISTER;
    private static ShipmentsService REST_SHIPMENTS_BY_ID;
    private static AddShipmentService REST_ADD_SHIPMENT;

    //private static String ROOT =
      //      "http://api.openweathermap.org/data/2.5";

    private static String ROOT = "http://192.168.1.14/ShypService/api/v1/";

    /**
     * Set the Rest Adapter for the web service
     *
     * */
    static {
        setupRestClient();
        setupRestAdapterForUser();
        setupRestAdapterForUserRegister();
        setupRestAdapterForShipmentsById();
        setupRestAdapterForAddShipment();
    }

    private RetrofitRestClient() {}

    /**
     * Set method to be accessed by different classes
     *
     * */
    public static WeatherService get() {
        return REST_CLIENT;
    }

    public static UserAuthService getUser() {
        return REST_USER;
    }

    public static UserRegisterService registerUser() {
        return REST_USER_REGISTER;
    }

    public static ShipmentsService shipmentById() {
        return REST_SHIPMENTS_BY_ID;
    }

    public static AddShipmentService addShipment() {
        return REST_ADD_SHIPMENT;
    }
    /**
     * Create REST client adapter for web service
     *
     * */
    private static void setupRestClient() {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()));

        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(WeatherService.class);
    }

    // Set up the rest adapter for user class
    private static void setupRestAdapterForUser() {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()));

        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_USER = restAdapter.create(UserAuthService.class);
    }


    // Set up the rest adapter for user class
    private static void setupRestAdapterForUserRegister() {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()));

        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_USER_REGISTER = restAdapter.create(UserRegisterService.class);
    }

    // get all the shipment by respective Id
    private static void setupRestAdapterForShipmentsById() {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()));

        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_SHIPMENTS_BY_ID = restAdapter.create(ShipmentsService.class);
    }


    // get all the shipment by respective Id
    private static void setupRestAdapterForAddShipment() {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()));

        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_ADD_SHIPMENT = restAdapter.create(AddShipmentService.class);
    }


}