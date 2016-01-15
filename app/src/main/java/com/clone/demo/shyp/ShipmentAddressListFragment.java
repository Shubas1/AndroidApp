package com.clone.demo.shyp;

import android.support.v4.app.Fragment;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShipmentAddressListFragment extends Fragment {

	Map<String, String> serverUrls = new HashMap<String,String>();
	LoadingDialog objLoadingDialog;
	JSONObject objJSON;
	PublicDelegate objPublicDelegate;
	AppPreferences objAppPreferences;
	RestClient client;

    ShipmentListAdapter mShipmentListAdapter;
    ListView mListView;

    ArrayList<String> searchedList = new ArrayList<String>();

	// programming mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ----

	public ShipmentAddressListFragment() {
		// Empty constructor required for fragment subclasses
	}

	// programming mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.list_fragment_shipment_address, container, false);

        final ArrayList<String> nameList = new ArrayList<String>();

        //populate list
        nameList.add("Apples");
        nameList.add("Oranges");
        nameList.add("Grapes");
        nameList.add("Pineapples");
        nameList.add("Mangoes");
        nameList.add("Watermelons");
        nameList.add("Strawberries");
        nameList.add("Bananas");
        nameList.add("Apricots");
        nameList.add("Olives");
        nameList.add("Peaches");
        nameList.add("Jackfruits");

        mShipmentListAdapter = new ShipmentListAdapter(getActivity(),nameList);
        mListView = (ListView) rootView.findViewById(R.id.shipping_item_list);
        mListView.setAdapter(mShipmentListAdapter);

        // search text in array list of address
        EditText search = (EditText) rootView.findViewById(R.id.search_shipment_address);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString().toLowerCase();

                // remove all previous values in array list
                searchedList.clear();

                // search values into array list
                for(String names: nameList) {

                    String name = names.toString().toLowerCase();
                    System.out.println("Search "+text+" in "+name);

                    if(name.contains(text)) {
                        System.out.println(text+" searched !");
                        searchedList.add(names);
                    }
                    else {
                        System.out.println("OOps search can't find!");
                    }
                }
                // reload the listview
                reloadAllData();
            }

        });

		return rootView;

	}//end onCreateView

    /**
     * helper to show what happens when all data is new
     */
    private void reloadAllData(){

        System.out.println("Accessing reload adapter searched !");

        mShipmentListAdapter = new ShipmentListAdapter(getActivity(),searchedList);
        mListView.setAdapter(mShipmentListAdapter);
        mShipmentListAdapter.notifyDataSetChanged();

    }

	// programming mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		objLoadingDialog = new LoadingDialog(getActivity());
		objPublicDelegate = new PublicDelegate(getActivity());
		objAppPreferences = new AppPreferences(getActivity());

		/*if(!objPublicDelegate.checkNetworkStatus()){

			objPublicDelegate.showAlertDialog("Warning", "Please check your internet connection");

		}else {

		}*/


	}// end onActivityCreated


    @Override
    public void onResume() {
        super.onResume();

    }

    /*

	// programming mark ---- ---- ----- ---- ---- ----- ---- ---- -----  ---- ---- -----

	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		super.onListItemClick(l, v, pos, id);


	}// end onListItemClick

    */

}// end main class YourStats

