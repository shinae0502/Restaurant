package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;

public class StoreSpec extends BaseObservable {
    Review img1;
    Review img2;
    Review img3;
    Review img4;
    Review img5;

    String address;

    String updateDate;
    String openingHours;
    String breaktime;
    String prices;
    String menu1;
    String menu2;
    String menu3;
    String keyword;

    String menu1_price;
    String menu2_price;
    String menu3_price;

    ArrayList<Review> reviews;
    //ArrayList<TopList> topLists;
    //ArrayList<Story> stories;
    ArrayList<Store> aroundRestaurant;

    /*public ArrayList<Story> getStories() {
        return stories;
    }*/

    /*public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }*/

    public ArrayList<Store> getAroundRestaurant() {
        return aroundRestaurant;
    }

    public void setAroundRestaurant(ArrayList<Store> aroundRestaurant) {
        this.aroundRestaurant = aroundRestaurant;
    }

    /*public ArrayList<TopList> getTopLists() {
        return topLists;
    }

    public void setTopLists(ArrayList<TopList> topLists) {
        this.topLists = topLists;
    }*/

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getMenu1_price() {
        return menu1_price;
    }

    public void setMenu1_price(String menu1_price) {
        this.menu1_price = menu1_price;
    }

    public String getMenu2_price() {
        return menu2_price;
    }

    public void setMenu2_price(String menu2_price) {
        this.menu2_price = menu2_price;
    }

    public String getMenu3_price() {
        return menu3_price;
    }

    public void setMenu3_price(String menu3_price) {
        this.menu3_price = menu3_price;
    }

    @Bindable
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Bindable
    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Bindable
    public String getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(String breaktime) {
        this.breaktime = breaktime;
    }

    @Bindable
    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    @Bindable
    public String getMenu1() {
        return menu1;
    }

    public void setMenu1(String menu1) {
        this.menu1 = menu1;
    }

    @Bindable
    public String getMenu2() {
        return menu2;
    }

    public void setMenu2(String menu2) {
        this.menu2 = menu2;
    }

    @Bindable
    public String getMenu3() {
        return menu3;
    }

    public void setMenu3(String menu3) {
        this.menu3 = menu3;
    }

    @Bindable
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Review getImg1() {
        return img1;
    }

    public void setImg1(Review img1) {
        this.img1 = img1;
    }

    public Review getImg2() {
        return img2;
    }

    public void setImg2(Review img2) {
        this.img2 = img2;
    }

    public Review getImg3() {
        return img3;
    }

    public void setImg3(Review img3) {
        this.img3 = img3;
    }

    public Review getImg4() {
        return img4;
    }

    public void setImg4(Review img4) {
        this.img4 = img4;
    }

    public Review getImg5() {
        return img5;
    }

    public void setImg5(Review img5) {
        this.img5 = img5;
    }
}
