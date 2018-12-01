package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StoreKeyword implements Parcelable {
    String store_id;
    String restaurant_name;
    String city_name;
    String region_name;

    public StoreKeyword() {

    }

    protected StoreKeyword(Parcel in) {
        store_id = in.readString();
        restaurant_name = in.readString();
        city_name = in.readString();
        region_name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(store_id);
        dest.writeString(restaurant_name);
        dest.writeString(city_name);
        dest.writeString(region_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoreKeyword> CREATOR = new Creator<StoreKeyword>() {
        @Override
        public StoreKeyword createFromParcel(Parcel in) {
            return new StoreKeyword(in);
        }

        @Override
        public StoreKeyword[] newArray(int size) {
            return new StoreKeyword[size];
        }
    };

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
