package com.example.nike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PurchaseListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>() ;

    public PurchaseListViewAdapter() {
    }

    public int getCount() {
        return itemList.size() ;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        int i = position;
        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_purchase, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.purchase_name_item) ;
        TextView priceView = (TextView) convertView.findViewById(R.id.purchase_price_item) ;

        ListViewItem listViewItem = itemList.get(position);

        nameView.setText(listViewItem.getItemName());
        priceView.setText(listViewItem.getItemPrice()+"원");

        return convertView;
    }

    public long getItemId(int position) {
        return position ;
    }

    public Object getItem(int position) {
        return itemList.get(position) ;
    }

    public void addPurchaseItem(String name, String price) {
        ListViewItem item = new ListViewItem();

        item.setItemName(name);
        item.setItemPrice(price);

        itemList.add(item);
    }
}
