package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;

import com.study.restaurant.BR;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreDetail extends BaseObservable implements Parcelable {
    Store restaurant;
    ArrayList<Image> image;
    ArrayList<Review> news;
    ArrayList<TopList> toplist;
    ArrayList<Store> store;
    OpenHours open_hours;
    ArrayList<StoreMenu> menus;
    ArrayList<StoreKeyword> keywords;
    String review_total;
    String review_best;
    String review_good;
    String review_bad;



    protected StoreDetail(Parcel in) {
        restaurant = in.readParcelable(Store.class.getClassLoader());
        image = in.createTypedArrayList(Image.CREATOR);
        toplist = in.createTypedArrayList(TopList.CREATOR);
        store = in.createTypedArrayList(Store.CREATOR);
        open_hours = in.readParcelable(OpenHours.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(restaurant, flags);
        dest.writeTypedList(image);
        dest.writeTypedList(toplist);
        dest.writeTypedList(store);
        dest.writeParcelable(open_hours, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoreDetail> CREATOR = new Creator<StoreDetail>() {
        @Override
        public StoreDetail createFromParcel(Parcel in) {
            return new StoreDetail(in);
        }

        @Override
        public StoreDetail[] newArray(int size) {
            return new StoreDetail[size];
        }
    };

    public Store getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Store restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Image> getImage() {
        return image;
    }

    public void setImage(ArrayList<Image> image) {
        this.image = image;
    }

    public ArrayList<Review> getNews() {
        return news;
    }

    public void setNews(ArrayList<Review> news) {
        this.news = news;
    }

    public ArrayList<TopList> getToplist() {
        return toplist;
    }

    public void setToplist(ArrayList<TopList> toplist) {
        this.toplist = toplist;
    }

    public ArrayList<Store> getStore() {
        return store;
    }

    public void setStore(ArrayList<Store> store) {
        this.store = store;
    }

    public OpenHours getOpen_hours() {
        return open_hours;
    }

    public void setOpen_hours(OpenHours open_hours) {
        this.open_hours = open_hours;
    }

    public ArrayList<StoreMenu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<StoreMenu> menus) {
        this.menus = menus;
    }

    public ArrayList<StoreKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<StoreKeyword> keywords) {
        this.keywords = keywords;
    }
}
