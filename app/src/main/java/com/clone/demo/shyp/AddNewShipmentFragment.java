package com.clone.demo.shyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddNewShipmentFragment extends Fragment {

	LoadingDialog objLoadingDialog;
	PublicDelegate objPublicDelegate;
	AppPreferences objAppPreferences;

    EditText etRecipientName, etCountry, etAddress, etMoreAddress, etCity, etState, etZip;

	// programming mark ---- ----

	public AddNewShipmentFragment() {
		// Empty constructor required for fragment subclasses
	}

	// programming mark ---- ----

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_add_new_shipment, container, false);

        etRecipientName =  (EditText) rootView.findViewById(R.id.recipient_name);
        etCountry =  (EditText) rootView.findViewById(R.id.country);
        etAddress =  (EditText) rootView.findViewById(R.id.address);
        etMoreAddress =  (EditText) rootView.findViewById(R.id.more_address);
        etCity =  (EditText) rootView.findViewById(R.id.city);
        etState =  (EditText) rootView.findViewById(R.id.state);
        etZip =  (EditText) rootView.findViewById(R.id.zip);
        
        // Action clickable when shipment added on server
        Button addShipment = (Button) rootView.findViewById(R.id.add_shipment);
        addShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getActivity(), "Add Shipment", Toast.LENGTH_LONG).show();

                try {
                    addNewShipment();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

		return rootView;

	}//end onCreateView

	// programming mark ---- ----

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		objLoadingDialog = new LoadingDialog(getActivity());
		objPublicDelegate = new PublicDelegate(getActivity());
		objAppPreferences = new AppPreferences(getActivity());


	}// end onActivityCreated


    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * Add new shipment
     *
     * */

    public void addNewShipment() throws Exception {

        // init the user constructor to set the email and password values
        Shipments shipments = new Shipments(
                 23
                , this.etRecipientName.getText().toString()
                , this.etCountry.getText().toString()
                , this.etAddress.getText().toString()
                , this.etMoreAddress.getText().toString()
                , this.etCity.getText().toString()
                , this.etState.getText().toString()
                , this.etZip.getText().toString());

        // create adapter for the retrofit client
        RetrofitRestClient.addShipment().addShipment(shipments, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                System.out.println("Added data " + new Gson().toJson(o));

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
    }// end addNewShipment

    /**
     * Analysis of data coming form the server when user authenticated
     *
     * @param
     * @return void
     */

    public void parseJSONValue(String reader) throws Exception {

        //this.objLoadingDialog.cancel();

        AddShipmentParams shipments = new GsonBuilder().create().fromJson(reader, AddShipmentParams.class);
        Log.e("Status Code: ", shipments.getStatusCode() + "");

        int statusCode = shipments.getStatusCode();

        if (statusCode == 500) {
            objPublicDelegate.showAlertDialog("Message", shipments.getErrorMessage());
        }// end if
        else {
            objPublicDelegate.showAlertDialog("Message", "Shipment added successfully!!!");
        }

    }// end parseJSONValue

}// end main class AddNewShipmentFragment

