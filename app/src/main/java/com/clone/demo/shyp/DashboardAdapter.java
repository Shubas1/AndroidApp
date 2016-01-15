package com.clone.demo.shyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardAdapter extends ArrayAdapter<DashboardShippingData> {

    // View lookup cache
    private static class ViewHolder {
        TextView address;
        TextView days;
    }

    public DashboardAdapter(Context context, ArrayList<DashboardShippingData> shipments) {
        super(context, R.layout.activity_dashboard_row, shipments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        DashboardShippingData shipment = getItem(position);

        System.out.println("shipment data "+shipment.toString());

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_dashboard_row, parent, false);
            viewHolder.address = (TextView) convertView.findViewById(R.id.shipment_address);
            viewHolder.days = (TextView) convertView.findViewById(R.id.shipment_days);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.address.setText(shipment.shipment_address);
        viewHolder.days.setText(shipment.shipment_days);
        // Return the completed view to render on screen
        return convertView;
    }
}