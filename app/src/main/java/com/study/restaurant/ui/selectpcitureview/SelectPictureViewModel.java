package com.study.restaurant.ui.selectpcitureview;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.adapter.SelectPicRvAdt;
import com.study.restaurant.common.BananaConstants.PictureUploadMode;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.ui.selectpcitureview.SelectPictureActivity;
import com.study.restaurant.util.Logger;

import java.util.ArrayList;

import static com.study.restaurant.common.BananaConstants.PictureUploadMode.REVIEW;

/**
 * Activity {@link SelectPictureActivity}
 * Layout: {@link R.layout#activity_select_picture}
 */
public class SelectPictureViewModel extends ViewModel {
    private PictureUploadMode pictureUploadMode;
    private BananaNavigation.SelectPictureNavigation selectPictureNavigation;
    private int count = 0;
    public MutableLiveData<Boolean> isCheckIn = new MutableLiveData<>();
    public MutableLiveData<String> strCount = new MutableLiveData<>();
    public MutableLiveData<ArrayList<MyImage>> selectedImgList = new MutableLiveData<>();
    public String strLeftMenu;
    public MutableLiveData<Boolean> hasSelectedPicture = new MutableLiveData<>();
    private SelectPicRvAdt selectPicRvAdt = new SelectPicRvAdt();

    public SelectPicRvAdt getSelectPicRvAdt() {
        return selectPicRvAdt;
    }

    public SelectPictureViewModel() {
        selectedImgList.setValue(new ArrayList<>());
        hasSelectedPicture.setValue(false);
        selectPicRvAdt.setSelectedImageList(selectedImgList);
    }

    /**
     * 건너뛰기 or 리뷰쓰기
     *
     * @return
     */
    public String getStrLeftMenu() {
        if (pictureUploadMode == PictureUploadMode.PREV_PICTURE) {
            strLeftMenu = "리뷰쓰기";
        } else if (pictureUploadMode == REVIEW) {
            strLeftMenu = "건너뛰기";
        }
        return strLeftMenu;
    }


    public void setPictureUploadMode(PictureUploadMode pictureUploadMode) {
        this.pictureUploadMode = pictureUploadMode;
        if (pictureUploadMode == PictureUploadMode.CHECK_IN) {
            isCheckIn.setValue(true);
        }
    }

    public void setSelectPictureNavigation(BananaNavigation.SelectPictureNavigation selectPictureNavigation) {
        this.selectPictureNavigation = selectPictureNavigation;
    }


    public void setCount(int count) {
        Logger.v(count);
        this.count = count;
        hasSelectedPicture.setValue(count > 0);
        strCount.setValue("완료 (" + count + "/20)");
    }

    public int getCount() {
        return count;
    }

    public void clickPass(View v) {
        selectPictureNavigation.goWriteReviewWithoutPicture();
    }

    public void clickCount(View v) {
        if (pictureUploadMode == PictureUploadMode.REVIEW) {
            selectPictureNavigation.goWriteReview();
        } else if (pictureUploadMode == PictureUploadMode.PREV_PICTURE) {
            selectPictureNavigation.goUploadPicture(selectedImgList.getValue());
        } else if (pictureUploadMode == PictureUploadMode.POST_PICTURE) {
            selectPictureNavigation.goUploadPictureOnFinish(selectedImgList.getValue());
        }
    }

    public void clickComplete(View v) {
        selectPictureNavigation.goCheckIn();
    }

    public String getTitleName() {
        switch (pictureUploadMode) {
            case REVIEW:
                return "리뷰쓰기";
            case POST_PICTURE:
            case PREV_PICTURE:
                return "사진 올리기";
            case CHECK_IN:
                return "체크인";
            default:
                return "모드없음";
        }
    }
}
