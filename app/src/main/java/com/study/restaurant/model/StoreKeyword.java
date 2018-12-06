package com.study.restaurant.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import com.study.restaurant.ui.searchkeywordview.SearchKeywordActivity;
import com.study.restaurant.databinding.ItemKeywordBinding;

public class StoreKeyword extends BaseObservable implements Parcelable {
    String store_id;
    String restaurant_name;
    String city_name;
    String region_name;
    String store_keyword;
    String keyword_id;

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

    public String getStore_keyword() {
        return store_keyword;
    }

    public void setStore_keyword(String store_keyword) {
        this.store_keyword = store_keyword;
    }

    public String getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(String keyword_id) {
        this.keyword_id = keyword_id;
    }

    @Bindable
    public String getKeyword() {
        String keyword = "";
        if (store_keyword != null && store_keyword.length() > 0) {
            keyword = store_keyword;
        }
        return "#" + keyword;
    }

    public View getView(Context context) {
        ItemKeywordBinding itemKeywordBinding = ItemKeywordBinding.inflate(LayoutInflater.from(context));
        itemKeywordBinding.setKeyword(this);
        return itemKeywordBinding.getRoot();

    }

    public void clickKeyword(View v) {
        SearchKeywordActivity.go(v.getContext(), this);
    }
}
