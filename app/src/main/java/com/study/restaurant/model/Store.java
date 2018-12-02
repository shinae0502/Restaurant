package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.study.restaurant.BR;

import java.text.DecimalFormat;

public class Store extends BaseObservable implements Parcelable {
    private int position;
    private String store_id;
    private String store_name;
    private String score;
    private String lat;
    private String lon;
    private String location;
    private String hit;
    private String review_count;
    private String img;
    private String address;
    private String reg_user_id;
    private String region_name;
    private String region_id;
    private String tel;
    private String conv_update_date;
    private String open_hour;
    private String close_hour;
    private String last_order;
    private String foot_category_id;
    private String website;
    private String user_id;
    private String user_name;
    private String profile_pic_url;
    private String price;
    /**
     * 내위치
     */
    Location myLocation;
    String favority_id;


    protected Store(Parcel in) {
        position = in.readInt();
        store_id = in.readString();
        store_name = in.readString();
        score = in.readString();
        lat = in.readString();
        lon = in.readString();
        location = in.readString();
        hit = in.readString();
        review_count = in.readString();
        img = in.readString();
        address = in.readString();
        reg_user_id = in.readString();
        region_name = in.readString();
        region_id = in.readString();
        tel = in.readString();
        conv_update_date = in.readString();
        open_hour = in.readString();
        close_hour = in.readString();
        last_order = in.readString();
        foot_category_id = in.readString();
        website = in.readString();
        user_id = in.readString();
        user_name = in.readString();
        profile_pic_url = in.readString();
        favority_id = in.readString();
        price = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(position);
        dest.writeString(store_id);
        dest.writeString(store_name);
        dest.writeString(score);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeString(location);
        dest.writeString(hit);
        dest.writeString(review_count);
        dest.writeString(img);
        dest.writeString(address);
        dest.writeString(reg_user_id);
        dest.writeString(region_name);
        dest.writeString(region_id);
        dest.writeString(tel);
        dest.writeString(conv_update_date);
        dest.writeString(open_hour);
        dest.writeString(close_hour);
        dest.writeString(last_order);
        dest.writeString(foot_category_id);
        dest.writeString(website);
        dest.writeString(user_id);
        dest.writeString(user_name);
        dest.writeString(profile_pic_url);
        dest.writeString(favority_id);
        dest.writeString(price);
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
    public String getStoreName() {
        return store_name;
    }

    @Bindable
    public String getPositionAndName() {
        return (position + 1) + ". " + getStore_name();
    }

    public void setStoreName(String name) {
        this.store_name = name;
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

    public String getFavority_id() {
        return favority_id;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    @Bindable
    public boolean isExistsFavority_id() {
        return favority_id != null;
    }

    public void setFavority_id(String favority_id) {
        this.favority_id = favority_id;
        notifyPropertyChanged(BR.existsFavority_id);
    }

    /**
     * 내위치와 가게의 거리
     */
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

    @Override
    public String toString() {
        return "position:" + position + "\n"
                + "store_id:" + store_id + "\n"
                + "name:" + store_name + "\n"
                + "score:" + score + "\n"
                + "lat:" + lat + "\n"
                + "lon:" + lon + "\n"
                + "location:" + location + "\n"
                + "hit:" + hit + "\n"
                + "review_count:" + review_count + "\n"
                + "img:" + img + "\n"
                + "store_name:" + store_name + "\n"
                + "address:" + address + "\n"
                + "reg_user_id:" + reg_user_id + "\n"
                + "region_name:" + region_name + "\n";
    }

    @Bindable
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getConv_update_date() {
        return conv_update_date;
    }

    public void setConv_update_date(String conv_update_date) {
        this.conv_update_date = conv_update_date;
    }

    public String getOpen_hour() {
        return open_hour;
    }

    public void setOpen_hour(String open_hour) {
        this.open_hour = open_hour;
    }

    public String getClose_hour() {
        return close_hour;
    }

    public void setClose_hour(String close_hour) {
        this.close_hour = close_hour;
    }

    public String getLast_order() {
        return last_order;
    }

    public void setLast_order(String last_order) {
        this.last_order = last_order;
    }

    public String getFoot_category_id() {
        return foot_category_id;
    }

    public void setFoot_category_id(String foot_category_id) {
        this.foot_category_id = foot_category_id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Bindable
    public String getPriceToInform() {
        if (price == null || price.length() == 0) {
            return "가격정보 없음";
        } else if (price.equals("0")) {
            return "만원 미만";
        } else if (price.equals("1")) {
            return "만원 - 이만원";
        } else if (price.equals("2")) {
            return "이만원 - 삼만원";
        } else if (price.equals("3")) {
            return "삼만원 이상";
        }
        return "가격정보 없음";
    }
}
