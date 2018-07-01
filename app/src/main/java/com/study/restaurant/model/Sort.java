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
        notifyPropertyChanged(BR.sortTitle);
    }

    @Bindable
    public boolean isRecommand() {
        return isRecommand;
    }

    public void setRecommand(boolean recommand) {
        isRecommand = recommand;
        notifyPropertyChanged(BR.recommand);
        notifyPropertyChanged(BR.sortTitle);
    }

    @Bindable
    public boolean isReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        isReview = review;
        notifyPropertyChanged(BR.review);
        notifyPropertyChanged(BR.sortTitle);
    }

    @Bindable
    public boolean isDistance() {
        return isDistance;
    }

    public void setDistance(boolean distance) {
        isDistance = distance;
        notifyPropertyChanged(BR.distance);
        notifyPropertyChanged(BR.sortTitle);
    }

    @Bindable
    public String getSortTitle() {
        return isReputatuon ? "평점순"
                : isRecommand ? "추천순"
                : isDistance ? "거리순"
                : isReview ? "리뷰순" : "없음";
    }

    public String getAttribute() {
        return isReputatuon ? "score"
                : isRecommand ? "score"
                : isDistance ? "review_count"
                : isReview ? "review_count" : "score";
    }
}
