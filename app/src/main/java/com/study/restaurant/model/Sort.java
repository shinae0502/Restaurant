package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.study.restaurant.BR;

public class Sort extends BaseObservable {

    boolean isReputatuon = true;
    boolean isRecommand;
    boolean isReview;
    boolean isDistance;

    @Bindable
    public boolean isReputatuon() {
        return isReputatuon;
    }

    public void setReputatuon(boolean reputatuon) {
        isReputatuon = reputatuon;
        notifyPropertyChanged(BR.reputatuon);
    }

    @Bindable
    public boolean isRecommand() {
        return isRecommand;
    }

    public void setRecommand(boolean recommand) {
        isRecommand = recommand;
        notifyPropertyChanged(BR.recommand);
    }

    @Bindable
    public boolean isReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        isReview = review;
        notifyPropertyChanged(BR.review);
    }

    @Bindable
    public boolean isDistance() {
        return isDistance;
    }

    public void setDistance(boolean distance) {
        isDistance = distance;
        notifyPropertyChanged(BR.distance);
    }

}
