package com.study.restaurant.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.LayoutInflater;
import android.view.View;

import com.study.restaurant.databinding.ItemStorePictureBinding;

public class StorePicture extends BaseObservable {
    private String pic_id;
    private String number;
    private String user_id;
    private String store_id;
    private String pic_url;
    private String date;

    public String getPic_id() {
        return pic_id;
    }

    public void setPic_id(String pic_id) {
        this.pic_id = pic_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Bindable
    public String getPic_url() {
        String domain = "http://sarang628.iptime.org:83/image_upload/";
        if (!pic_url.contains("http")) {
            pic_url = domain + pic_url;
        }
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public View getView(Context context) {
        ItemStorePictureBinding itemStorePictureBinding = ItemStorePictureBinding.inflate(LayoutInflater.from(context));
        itemStorePictureBinding.setPicture(this);
        return itemStorePictureBinding.getRoot();
    }
}
