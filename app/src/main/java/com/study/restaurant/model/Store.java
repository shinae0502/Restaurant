package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class Store extends BaseObservable implements Parcelable {
    String store_id;
    String name;
    String score;
    String lat;
    String lon;
    String location;
    String hit;
    String review_count;
    String img;
    private int position;

    String store_name;
    String address;
    String reg_user_id;

    String region_name;

    Location myLocation;

    protected Store(Parcel in) {
        store_id = in.readString();
        name = in.readString();
        score = in.readString();
        lat = in.readString();
        lon = in.readString();
        location = in.readString();
        hit = in.readString();
        review_count = in.readString();
        img = in.readString();
        position = in.readInt();
        store_name = in.readString();
        lat = in.readString();
        address = in.readString();
        reg_user_id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(store_id);
        dest.writeString(name);
        dest.writeString(score);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeString(location);
        dest.writeString(hit);
        dest.writeString(review_count);
        dest.writeString(img);
        dest.writeInt(position);
        dest.writeString(store_name);
        dest.writeString(lat);
        dest.writeString(address);
        dest.writeString(reg_user_id);
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

    public int getPosition() {
        return position;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReg_user_id() {
        return reg_user_id;
    }

    public void setReg_user_id(String reg_user_id) {
        this.reg_user_id = reg_user_id;
    }

    public static Creator<Store> getCREATOR() {
        return CREATOR;
    }

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

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getPositionAndName() {
        return (position + 1) + ". " + getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
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

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Bindable
    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    @Bindable
    public String getReview_count() {
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public Store() {

    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Location getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }

    /** 내위치와 가게의 거리 */
    @Bindable
    public String getDistance() {
        float distance = 0;
        try {
            Location locationB = new Location("point B");
            locationB.setLatitude(Double.valueOf(getLat()));
            locationB.setLongitude(Double.valueOf(getLon()));
            distance = myLocation.distanceTo(locationB);
        } catch (Exception e) {

        }

        /** 소수점 2자리 설정*/
        DecimalFormat format = new DecimalFormat(".##");

        if (distance > 1000)
            return format.format(distance / 1000) + "km";
        else
            return (int) distance + "m";
    }

    @Bindable
    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
