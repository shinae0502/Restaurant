package com.study.restaurant.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import com.study.restaurant.databinding.ItemMenuBinding;
import com.study.restaurant.databinding.ItemMenuImageBinding;

public class StoreMenu extends BaseObservable implements Parcelable {
    public enum StoreMenuType {TEXT, IMAGE}

    private String menu_id;
    private String store_id;
    private String menu_name;
    private String menu_price;
    private String menu_picture_url;
    private String number;

    protected StoreMenu(Parcel in) {
        menu_id = in.readString();
        store_id = in.readString();
        menu_name = in.readString();
        menu_price = in.readString();
        menu_picture_url = in.readString();
        number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menu_id);
        dest.writeString(store_id);
        dest.writeString(menu_name);
        dest.writeString(menu_price);
        dest.writeString(menu_picture_url);
        dest.writeString(number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoreMenu> CREATOR = new Creator<StoreMenu>() {
        @Override
        public StoreMenu createFromParcel(Parcel in) {
            return new StoreMenu(in);
        }

        @Override
        public StoreMenu[] newArray(int size) {
            return new StoreMenu[size];
        }
    };

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Bindable
    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    @Bindable
    public String getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }

    public String getMenu_picture_url() {
        return menu_picture_url;
    }

    public void setMenu_picture_url(String menu_picture_url) {
        this.menu_picture_url = menu_picture_url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public View getView(Context context) {
        if (getStoreMenuType() == StoreMenuType.TEXT) {
            ItemMenuBinding itemMenuBinding = ItemMenuBinding.inflate(LayoutInflater.from(context));
            itemMenuBinding.setMenu(this);
            return itemMenuBinding.getRoot();
        } else {
            ItemMenuImageBinding itemMenuImageBinding = ItemMenuImageBinding.inflate(LayoutInflater.from(context));
            itemMenuImageBinding.setMenu(this);
            return itemMenuImageBinding.getRoot();
        }
    }

    public StoreMenuType getStoreMenuType() {
        if (menu_picture_url == null || menu_picture_url.length() == 0)
            return StoreMenuType.TEXT;
        else
            return StoreMenuType.IMAGE;
    }
}
