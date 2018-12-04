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
    ArrayList<News> reviews;
    ArrayList<TopList> toplist;
    ArrayList<Store> store;
    OpenHours open_hours;
    ArrayList<StoreMenu> menus;
    ArrayList<StoreKeyword> keywords;
    String review_total;
    String review_best;
    String review_good;
    String review_bad;


    public StoreDetail() {

    }

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

    public ArrayList<News> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<News> reviews) {
        this.reviews = reviews;
    }

    @Bindable
    public String getReview_total() {
        return "맛깔나는리뷰 (" + review_total + ")";
    }

    public void setReview_total(String review_total) {
        this.review_total = review_total;
    }

    @Bindable
    public String getReview_best() {
        return "맛있다! (" + review_best + ")";
    }

    public void setReview_best(String review_best) {
        this.review_best = review_best;
    }

    @Bindable
    public String getReview_good() {
        return "괜찮다 (" + review_good + ")";
    }

    public void setReview_good(String review_good) {
        this.review_good = review_good;
    }

    @Bindable
    public String getReview_bad() {
        return "별로 (" + review_bad + ")";
    }

    public void setReview_bad(String review_bad) {
        this.review_bad = review_bad;
    }
}
