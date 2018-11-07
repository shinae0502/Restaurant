package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable{

    String story_id;
    String title;
    String subtitle;
    String image;
    String date;
    String user_img_url;
    String user_name;
    String hit;


    protected Story(Parcel in) {
        story_id = in.readString();
        title = in.readString();
        subtitle = in.readString();
        image = in.readString();
        date = in.readString();
        user_img_url = in.readString();
        user_name = in.readString();
        hit = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(story_id);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(image);
        dest.writeString(date);
        dest.writeString(user_img_url);
        dest.writeString(user_name);
        dest.writeString(hit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public String getUser_img_url() {
        return user_img_url;
    }

    public void setUser_img_url(String user_img_url) {
        this.user_img_url = user_img_url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        String s = "storyId:" + story_id;
        s += "\n";
        s += "title:" + title;
        s += "\n";
        s += "subtitle:" + subtitle;
        s += "\n";
        s += "image:" + image;
        return s;
    }
}