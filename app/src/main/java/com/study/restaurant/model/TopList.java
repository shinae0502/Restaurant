package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;


public class TopList implements Parcelable {

    private String topListId;
    private String title;
    private String subtitle;
    private String badge;
    private int image;
    private int hit;
    private String date;
//    private Date date;

    public TopList(String topListId, String title, String subtitle, String badge, int image, int hit, String date) {
//        this.context = context;
        this.topListId = topListId;
        this.title = title;
        this.subtitle = subtitle;
        this.badge = badge;
        this.image = image;
        this.hit = hit;
        this.date = date;
    }

    public String getTopListId() {
        return topListId;
    }

    public void setTopListId(String topListId) {
        this.topListId = topListId;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    protected TopList(Parcel in) {
        topListId = in.readString();
        title = in.readString();
        subtitle = in.readString();
        badge = in.readString();
        image = Integer.parseInt(in.readString());
        hit = Integer.parseInt(in.readString());
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topListId);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(badge);
        dest.writeString(image+"");
        dest.writeString(hit+"");
        dest.writeString(date+"");
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



} // =========================================================  class TopListContents