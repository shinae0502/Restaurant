package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.study.restaurant.BR;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.ui.picturedetailview.PhotoDetailActivity;

import java.util.ArrayList;

public class News extends BaseObservable implements Parcelable {
    private String news_id;
    private String store_id;
    private String profile_img;
    private String user_name;
    private String user_writing;
    private String user_follower;
    private String hash_tag;
    private String score;
    private String contents;
    private String like_count;
    private String reply_count;
    private String date;
    private String user_id;
    private String tag1;
    private String tag2;
    private String url;
    private String favorite_id;
    private String like_id;
    private ArrayList<StorePicture> storePictures;
    private String name;


    protected News(Parcel in) {
        news_id = in.readString();
        store_id = in.readString();
        profile_img = in.readString();
        user_name = in.readString();
        user_writing = in.readString();
        user_follower = in.readString();
        hash_tag = in.readString();
        score = in.readString();
        contents = in.readString();
        like_count = in.readString();
        reply_count = in.readString();
        date = in.readString();
        user_id = in.readString();
        tag1 = in.readString();
        tag2 = in.readString();
        url = in.readString();
        favorite_id = in.readString();
        like_id = in.readString();
        storePictures = in.createTypedArrayList(StorePicture.CREATOR);
        name = in.readString();
        domain = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(news_id);
        dest.writeString(store_id);
        dest.writeString(profile_img);
        dest.writeString(user_name);
        dest.writeString(user_writing);
        dest.writeString(user_follower);
        dest.writeString(hash_tag);
        dest.writeString(score);
        dest.writeString(contents);
        dest.writeString(like_count);
        dest.writeString(reply_count);
        dest.writeString(date);
        dest.writeString(user_id);
        dest.writeString(tag1);
        dest.writeString(tag2);
        dest.writeString(url);
        dest.writeString(favorite_id);
        dest.writeString(like_id);
        dest.writeTypedList(storePictures);
        dest.writeString(name);
        dest.writeString(domain);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public ArrayList<StorePicture> getStorePictures() {
        return storePictures;
    }

    public void setStorePictures(ArrayList<StorePicture> storePictures) {
        this.storePictures = storePictures;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_writing() {
        return user_writing;
    }

    public void setUser_writing(String user_writing) {
        this.user_writing = user_writing;
    }

    public String getUser_follower() {
        return user_follower;
    }

    public void setUser_follower(String user_follower) {
        this.user_follower = user_follower;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    String domain = "http://sarang628.iptime.org:83/image_upload/";

    @Bindable
    public boolean ishasUrl() {
        return !(url == null && url.length() == 0);
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getReply_count() {
        return reply_count;
    }

    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void clickFavorite(View v) {
        Store store = new Store();
        store.setStore_id(getStore_id());
        if (v.isSelected()) {
            ApiManager.getInstance().deleteFavorite(v.getContext(), store, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    Store store = new Gson().fromJson(result, Store.class);
                    if (store.isExistsFavority_id()) {
                        setFavorite_id(store.getFavority_id());
                    } else {
                        setFavorite_id(null);
                    }
                }

                @Override
                public void failed(String msg) {

                }
            });
        } else {
            ApiManager.getInstance().addFavorite(v.getContext(), store, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    Store store = new Gson().fromJson(result, Store.class);
                    if (store.isExistsFavority_id()) {
                        setFavorite_id(store.getFavority_id());
                    } else {
                        setFavorite_id(null);
                    }
                }

                @Override
                public void failed(String msg) {

                }
            });
        }
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
        notifyPropertyChanged(BR.existsFavorite_id);
    }

    @Bindable
    public boolean isExistsFavorite_id() {
        return !(favorite_id == null || favorite_id.length() == 0);
    }

    public void clickHeart(View v) {
        Store store = new Store();
        if (v.isSelected()) {
            ApiManager.getInstance().deleteLikeReview(v.getContext(), news_id, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    News news = new Gson().fromJson(result, News.class);
                    if (news.isExistsHeart_id()) {
                        setLike_id(news.getLike_id());
                    } else {
                        setLike_id(null);
                    }
                }

                @Override
                public void failed(String msg) {

                }
            });
        } else {
            ApiManager.getInstance().addLikeReview(v.getContext(), news_id, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    News news = new Gson().fromJson(result, News.class);
                    if (news.isExistsHeart_id()) {
                        setLike_id(news.getLike_id());
                    } else {
                        setLike_id(null);
                    }
                }

                @Override
                public void failed(String msg) {

                }
            });
        }
    }

    @Bindable
    public boolean isExistsHeart_id() {
        return !(like_id == null || like_id.length() == 0);
    }

    public String getLike_id() {
        return like_id;
    }

    public void setLike_id(String like_id) {
        this.like_id = like_id;
        notifyPropertyChanged(BR.existsHeart_id);
    }

    public void clickComment(View v) {
        Toast.makeText(v.getContext(), "clickComment", Toast.LENGTH_SHORT).show();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clickImage(View v, ArrayList<News> news) {
        PhotoDetailActivity.go(v.getContext(), news);
    }
}
