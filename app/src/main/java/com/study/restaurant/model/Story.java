package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable {

    //    private Context context;
    private String storyId;
    private String title;
    private String subtitle;
    // private int image;
    private String image;

    public Story() {

    }

    public Story(String storyId, String title, String subtitle, int image) {
        this.storyId = storyId;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
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


    protected Story(Parcel in) {
        storyId = in.readString();
        title = in.readString();
        subtitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storyId);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(image + "");
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


} // =====================================================  class StoryContents