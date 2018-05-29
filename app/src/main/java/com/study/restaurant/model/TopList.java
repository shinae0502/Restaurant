package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TopList implements Parcelable {
    String toplist_id;
    String title;
    String subtitle;
    String badge;
    String image;
    String hit;
    String date;

    public String getToplist_id() {
        return toplist_id;
    }

    public void setToplist_id(String toplist_id) {
        this.toplist_id = toplist_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected TopList(Parcel in) {
        toplist_id = in.readString();
        title = in.readString();
        subtitle = in.readString();
        badge = in.readString();
        image = in.readString();
        hit = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toplist_id);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(badge);
        dest.writeString(image);
        dest.writeString(hit);
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
}
