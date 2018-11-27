package com.study.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyImage implements Parcelable {

    private String data;

    public MyImage() {

    }

    protected MyImage(Parcel in) {
        data = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MyImage> CREATOR = new Creator<MyImage>() {
        @Override
        public MyImage createFromParcel(Parcel in) {
            return new MyImage(in);
        }

        @Override
        public MyImage[] newArray(int size) {
            return new MyImage[size];
        }
    };

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        boolean compare = false;

        if (obj != null && obj instanceof MyImage) {
            compare = this.data.equals(((MyImage) obj).data);
        }

        return compare;
    }
}
