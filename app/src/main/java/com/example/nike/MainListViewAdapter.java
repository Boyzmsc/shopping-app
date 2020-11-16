package com.example.nike;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>() ;

    public MainListViewAdapter() {
    }

    public int getCount() {
        return itemList.size() ;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        int i = position;
        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        ImageView imgView = (ImageView) convertView.findViewById(R.id.img_item) ;
        TextView nameView = (TextView) convertView.findViewById(R.id.name_item) ;
        TextView priceView = (TextView) convertView.findViewById(R.id.price_item) ;
        CheckBox checkBoxView = (CheckBox) convertView.findViewById(R.id.cb_item);

        ListViewItem listViewItem = itemList.get(position);

        imgView.setImageDrawable(listViewItem.getItemImg());
        nameView.setText(listViewItem.getItemName());
        priceView.setText(listViewItem.getItemPrice()+"원");
        checkBoxView.setChecked(false);

        return convertView;
    }

    public long getItemId(int position) {
        return position ;
    }

    public Object getItem(int position) {
        return itemList.get(position) ;
    }

    public void addMainItem(Drawable img, String name, String price) {
        ListViewItem item = new ListViewItem();

        item.setItemImg(img);
        item.setItemName(name);
        item.setItemPrice(price);

        itemList.add(item);
    }

}
