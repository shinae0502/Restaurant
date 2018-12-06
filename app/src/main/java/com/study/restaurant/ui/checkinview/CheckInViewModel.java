package com.study.restaurant.ui.checkinview;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.view.View;

import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.navigation.BananaNavigation;

import java.util.HashMap;
import java.util.Map;

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
    public MutableLiveData<Boolean> isPublic = new MutableLiveData<>();
    public MutableLiveData<String> contents = new MutableLiveData<>();
    public MutableLiveData<String> hint = new MutableLiveData<>();
    public MutableLiveData<Boolean> hasImage = new MutableLiveData<>();
    public MutableLiveData<String> image = new MutableLiveData<>();
    private BananaNavigation.CheckInNavigation checkInNavigation;
    public MutableLiveData<String> foodCategory = new MutableLiveData<>();
    public MutableLiveData<String> hit = new MutableLiveData<>();
    public MutableLiveData<String> reviewCount = new MutableLiveData<>();


    CheckInViewModel() {
        isPublic.setValue(true);
        egmt.setValue("공개");
        restaurantName.setValue("식당명");
        regionName.setValue("지역명");
        hint.setValue("양사랑님이 " + restaurantName.getValue() + "에 방문하였습니다.");
        hasImage.setValue(false);
        foodCategory.setValue("중식");
        hit.setValue("12");
        reviewCount.setValue("34");
    }

    /**
     * {@link R.layout#activity_check_in}
     */
    public void clickEgmt() {
        isPublic.setValue(!isPublic.getValue());
        if (isPublic.getValue()) {
            egmt.setValue("공개");
        } else {
            egmt.setValue("나만 보기");
        }
    }

    public void clickDeleteImage() {
        image.setValue(null);
        hasImage.setValue(false);
    }

    public void clickUploadPicture() {
        checkInNavigation.goPicture();
    }

    public void setCheckInNavigation(BananaNavigation.CheckInNavigation checkInNavigation) {
        this.checkInNavigation = checkInNavigation;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == 0x01) {
            MyImage myImage = data.getParcelableExtra("MyImage");
            if (myImage != null) {
                image.setValue(myImage.getData());
                hasImage.setValue(true);
            }
        }
    }

    /**
     * {@link R.layout#activity_check_in}
     */
    public void clickComplete() {
        if (checkInNavigation.getCurrentPage() == BananaConstants.CheckInPage.WRITE) {
            checkInNavigation.goRegister();
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("isPublic", isPublic.getValue() ? "0" : "1");
        param.put("contents", contents.getValue().length() < 1 ? hint.getValue() : contents.getValue());
        String store_id = "-1";
        param.put("store_id", store_id);
        ApiManager.getInstance().checkIn(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                checkInNavigation.finish();
            }

            @Override
            public void failed(String msg) {

            }
        });
    }
}
