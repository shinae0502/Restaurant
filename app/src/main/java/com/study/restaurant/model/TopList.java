package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;


public class TopList implements Parcelable {

    private String top_list_id;
    private String title;
    private String subtitle;
    private String badge;
    private String img_url;
    private String view_count;
    private String date;


    protected TopList(Parcel in) {
        top_list_id = in.readString();
        title = in.readString();
        subtitle = in.readString();
        badge = in.readString();
        img_url = in.readString();
        view_count = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(top_list_id);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(badge);
        dest.writeString(img_url);
        dest.writeString(view_count);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TopList> CREATOR = new Creator<TopList>() {
        @Override
        public TopList createFromParcel(Parcel in) {
            return new TopList(in);
        }

        @Override
        public TopList[] newArray(int size) {
            return new TopList[size];
        }
    };

    public String getTop_list_id() {
        return top_list_id;
    }

    public void setTop_list_id(String top_list_id) {
        this.top_list_id = top_list_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}