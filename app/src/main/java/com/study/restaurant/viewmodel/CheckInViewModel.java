package com.study.restaurant.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.view.View;

import com.study.restaurant.activity.CheckInActivity;
import com.study.restaurant.navigation.BananaNavigation;

public class CheckInViewModel extends ViewModel {
    public MutableLiveData<String> restaurantName = new MutableLiveData<>();
    public MutableLiveData<String> regionName = new MutableLiveData<>();
    /**
     * 공개여부 문구
     */
    public MutableLiveData<String> egmt = new MutableLiveData<>();
    /**
     * 공개 여부
     */
    public MutableLiveData<Boolean> isEgmt = new MutableLiveData<>();
    public MutableLiveData<String> contents = new MutableLiveData<>();
    public MutableLiveData<String> hint = new MutableLiveData<>();
    public MutableLiveData<Boolean> hasImage = new MutableLiveData<>();
    public MutableLiveData<String> image = new MutableLiveData<>();
    private BananaNavigation.CheckInNavigation checkInNavigation;


    CheckInViewModel() {
        isEgmt.setValue(true);
        egmt.setValue("공개");
        restaurantName.setValue("식당명");
        regionName.setValue("지역명");
        hint.setValue("양사랑님이 " + restaurantName.getValue() + "에 방문하였습니다.");
        hasImage.setValue(false);
    }

    public void clickEgmt(View v) {
        isEgmt.setValue(!isEgmt.getValue());
        if (isEgmt.getValue()) {
            egmt.setValue("공개");
        } else {
            egmt.setValue("나만 보기");
        }
    }

    public void clickDeleteImage(View v) {
        image.setValue(null);
        hasImage.setValue(false);
    }

    public void clickUploadPicture(View v) {
        checkInNavigation.goPicture();
    }

    public void setCheckInNavigation(BananaNavigation.CheckInNavigation checkInNavigation) {
        this.checkInNavigation = checkInNavigation;
    }
}
