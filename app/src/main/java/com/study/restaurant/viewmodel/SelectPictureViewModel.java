package com.study.restaurant.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.study.restaurant.model.MyImage;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.util.Logger;

import java.util.ArrayList;


public class SelectPictureViewModel extends ViewModel {
    private BananaNavigation.SelectPictureNavigation selectPictureNavigation;
    private int count = 0;
    public MutableLiveData<Boolean> isCheckIn = new MutableLiveData<>();
    public MutableLiveData<String> strCount = new MutableLiveData<>();
    public MutableLiveData<ArrayList<MyImage>> selectedImgList = new MutableLiveData<>();

    public SelectPictureViewModel() {
        selectedImgList.setValue(new ArrayList<>());
    }

    public void setSelectPictureNavigation(BananaNavigation.SelectPictureNavigation selectPictureNavigation) {
        this.selectPictureNavigation = selectPictureNavigation;
    }


    public void setCount(int count) {
        Logger.v(count);
        this.count = count;
        strCount.setValue("(" + count + "/20)");
    }

    public int getCount() {
        return count;
    }

    public void clickPass(View v) {
        selectPictureNavigation.goWriteReviewWithoutPicture();
    }

    public void clickCount(View v) {
        selectPictureNavigation.goWriteReview();
    }

    public void clickComplete(View v) {
        selectPictureNavigation.goCheckIn();
    }
}
