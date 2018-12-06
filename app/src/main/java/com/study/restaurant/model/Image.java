package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.study.restaurant.R;

/**
 * {@link R.layout#item_img}
 */
public class Image extends BaseObservable implements Parcelable {
    String pic_id;
    String number;
    String user_id;
    String store_id;
    String url;
    String date;

    protected Image(Parcel in) {
        pic_id = in.readString();
        number = in.readString();
        user_id = in.readString();
        store_id = in.readString();
        url = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pic_id);
        dest.writeString(number);
        dest.writeString(user_id);
        dest.writeString(store_id);
        dest.writeString(url);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

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

    public String getUrl() {

        String head = url.contains("http") ? "" : "http://sarang628.iptime.org:83/image_upload/";
        return head + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void clickImage(View v)
    {
        
    }
}
