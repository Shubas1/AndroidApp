package com.clone.demo.shyp;

import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class DashboardShippingData {

    String shipment_address, shipment_days;
    ImageView shipment_image;

    // Constructor to convert JSON object into a Java class instance
    public DashboardShippingData(JSONObject object) {

        try {

            this.shipment_address = object.getString("address");
            this.shipment_days = object.getString("more_address");

            System.out.println("json objects name " + this.shipment_address + " home town " + this.shipment_days);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }// end DashboardShippingData

    // Constructor to convert JSON object into a Java class instance
    public DashboardShippingData(ShipmentsResponseData shipment) {

        try {

            this.shipment_address = shipment.getAddress();
            this.shipment_days = shipment.getMore_address();

            System.out.println("json objects name " + this.shipment_address + " home town " + this.shipment_days);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }// end DashboardShippingData

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<DashboardShippingData> fromJson(JSONArray jsonObjects) {

        ArrayList<DashboardShippingData> shipments = new ArrayList<DashboardShippingData>();

        for (int i = 0; i < jsonObjects.length(); i++) {

            try {
                shipments.add(new DashboardShippingData(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return shipments;
    }// end fromJson

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<DashboardShippingData> fromGson(ArrayList<ShipmentsResponseData> shipmentArrayList) {

        ArrayList<DashboardShippingData> dataArrayList = new ArrayList<DashboardShippingData>();

        for(ShipmentsResponseData shipment: shipmentArrayList){
            Log.e("main title: ", shipment.getAddress() + "");
            Log.e("des title: ",shipment.getMore_address()+"");

            try {
                dataArrayList.add(new DashboardShippingData(shipment));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return dataArrayList;

    }// end fromGson

}// end main class DashboardShippingData