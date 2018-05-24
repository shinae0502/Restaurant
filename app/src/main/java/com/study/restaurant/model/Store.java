package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {
    String store_id;
    String name;
    String score;
    String lat;
    String lng;
    String location;
    String hit;
    String review_count;
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getReview_count() {
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    protected Store(Parcel in) {
        store_id = in.readString();
        name = in.readString();
        score = in.readString();
        lat = in.readString();
        lng = in.readString();
        location = in.readString();
        hit = in.readString();
        review_count = in.readString();
        img = in.readString();

    }

    public Store()
    {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(store_id);
        dest.writeString(name);
        dest.writeString(score);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(location);
        dest.writeString(hit);
        dest.writeString(review_count);
        dest.writeString(img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };
}
