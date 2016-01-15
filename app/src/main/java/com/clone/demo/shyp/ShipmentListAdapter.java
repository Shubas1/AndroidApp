package com.clone.demo.shyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ShipmentListAdapter extends BaseAdapter {

    private ArrayList myListItems;
    private LayoutInflater myLayoutInflater;

    public ShipmentListAdapter(Context context, ArrayList arrayList){
        myListItems = arrayList;
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myListItems.size();
    }

    @Override

    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = myLayoutInflater.inflate(R.layout.cell_shipment_list_adapter, null);
            holder.itemName = (TextView) view.findViewById(R.id.tv_shipment_address);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }


        String stringItem = myListItems.get(position).toString();
        if (stringItem != null) {
            if (holder.itemName != null) {
                //set the item name on the TextView
                holder.itemName.setText(stringItem);
            }
        }
        return view;
    }

    private static class ViewHolder {
        protected TextView itemName;
    }

}// end ShipmentListAdapter