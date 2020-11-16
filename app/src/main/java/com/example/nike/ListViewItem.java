package com.example.nike;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class ListViewItem implements Parcelable {
    private Drawable itemImg ;
    private String itemName ;
    private String itemPrice ;

    public ListViewItem(){}

    public void setItemImg(Drawable icon) {
        itemImg = icon ;
    }
    public void setItemName(String name) {
        itemName = name ;
    }
    public void setItemPrice(String price) {
        itemPrice = price ;
    }

    public Drawable getItemImg() {
        return this.itemImg ;
    }
    public String getItemName() {
        return this.itemName ;
    }
    public String getItemPrice() {
        return this.itemPrice ;
    }

    public ListViewItem(Parcel in){
        this.itemName = in.readString();
        this.itemPrice = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.itemName);
        parcel.writeString(this.itemPrice);
    }

    public static final Creator<ListViewItem> CREATOR = new Creator<ListViewItem>() {
        @Override
        public ListViewItem createFromParcel(Parcel in) {
            return new ListViewItem(in);
        }

        @Override
        public ListViewItem[] newArray(int size) {
            return new ListViewItem[size];
        }
    };
}
