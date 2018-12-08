package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.study.restaurant.BR;
import com.study.restaurant.ui.conveniencedetail.ConvenienceDetailActivity;
import com.study.restaurant.ui.menudetail.MenuDetailActivity;
import com.study.restaurant.ui.restaurantdetailview.RestaurantDetailActivity;
import com.study.restaurant.util.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreDetail extends BaseObservable implements Parcelable {
    private Store restaurant;
    private ArrayList<News> image;
    private ArrayList<News> reviews;
    private ArrayList<TopList> toplist;
    private ArrayList<Story> stories;
    private ArrayList<Store> store;
    private OpenHours open_hours;
    private ArrayList<StoreMenu> menus;
    private ArrayList<StoreKeyword> keywords;
    private String review_total;
    private String review_best;
    private String review_good;
    private String review_bad;


    public StoreDetail() {

    }

    protected StoreDetail(Parcel in) {
        restaurant = in.readParcelable(Store.class.getClassLoader());
        toplist = in.createTypedArrayList(TopList.CREATOR);
        stories = in.createTypedArrayList(Story.CREATOR);
        store = in.createTypedArrayList(Store.CREATOR);
        open_hours = in.readParcelable(OpenHours.class.getClassLoader());
        menus = in.createTypedArrayList(StoreMenu.CREATOR);
        keywords = in.createTypedArrayList(StoreKeyword.CREATOR);
        review_total = in.readString();
        review_best = in.readString();
        review_good = in.readString();
        review_bad = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(restaurant, flags);
        dest.writeTypedList(toplist);
        dest.writeTypedList(stories);
        dest.writeTypedList(store);
        dest.writeParcelable(open_hours, flags);
        dest.writeTypedList(menus);
        dest.writeTypedList(keywords);
        dest.writeString(review_total);
        dest.writeString(review_best);
        dest.writeString(review_good);
        dest.writeString(review_bad);
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

    public ArrayList<News> getImage() {
        return image;
    }

    public void setImage(ArrayList<News> image) {
        this.image = image;
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

    @Bindable
    public boolean isHasTopList() {
        return !(toplist == null || toplist.size() == 0);
    }

    @Bindable
    public boolean isHasStory() {
        return !(stories == null || stories.size() == 0);
    }

    @Bindable
    public boolean isHasStore() {
        return !(store == null || store.size() == 0);
    }

    public boolean isHasReview() {
        return !(reviews == null || reviews.size() == 0);
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }

    /**
     * {@link R.layout#activity_restaurant_detail}
     *
     * @param v
     */
    public void moreConvenience(View v) {
        Logger.v("moreConvenience");
        ConvenienceDetailActivity.go(v.getContext(), this);
    }

    public void moreMenu(View v) {
        MenuDetailActivity.go(v.getContext(), menus, restaurant.getStoreName());
    }

    public void attachMenu(ViewGroup viewGroup, ViewGroup menuImageLayout) {
        for (int i = 0; i < getMenus().size(); i++) {
            if (getMenus().get(i).getStoreMenuType() == StoreMenu.StoreMenuType.TEXT) {
                viewGroup.addView(getMenus().get(i).getView(viewGroup.getContext()));
            } else {
                menuImageLayout.addView(getMenus().get(i).getView(viewGroup.getContext()));
            }
        }
    }
}
