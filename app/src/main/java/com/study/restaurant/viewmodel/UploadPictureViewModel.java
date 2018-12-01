package com.study.restaurant.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.fragment.PictureFragment;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Layout: {@link R.layout#activity_picture_upload}
 * Activity: {@link com.study.restaurant.activity.PictureUploadActivity}
 */
public class UploadPictureViewModel extends ViewModel {
    public MutableLiveData<String> restaurantName = new MutableLiveData<>();
    public MutableLiveData<String> regionName = new MutableLiveData<>();
    public MutableLiveData<String> contents = new MutableLiveData<>(); //양사랑님이 2장의 사진을 등록했어요
    public String userName;
    public MutableLiveData<ArrayList<MyImage>> images = new MutableLiveData<>();
    BananaNavigation.PictureUploadNavigation pictureUploadNavigation;
    FragmentManager fragmentManager;
    UploadPicVpAdt uploadPicVpAdt;

    public UploadPictureViewModel() {
        images.setValue(new ArrayList<>());
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setImages(ArrayList<MyImage> images) {
        Logger.v(images.size());
        this.images.getValue().removeAll(this.images.getValue());
        this.images.getValue().addAll(images);
        contents.setValue(userName + "님이 " + images.size() + "의 사진을 등록했어요.");
        if (uploadPicVpAdt != null)
            uploadPicVpAdt.notifyDataSetChanged();
    }

    public FragmentStatePagerAdapter getUploadPicVpAdt() {
        if (uploadPicVpAdt == null) {
            uploadPicVpAdt = new UploadPicVpAdt(fragmentManager);
        }
        return uploadPicVpAdt;
    }

    public void clickPhotoEdit(View v) {
        pictureUploadNavigation.goSelectPicture(images.getValue());
    }

    public void setNavigation(BananaNavigation.PictureUploadNavigation pictureUploadNavigation) {
        this.pictureUploadNavigation = pictureUploadNavigation;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == 0x01) {
            ArrayList<MyImage> myImage = data.getParcelableArrayListExtra("selectedImgList");
            if (myImage != null) {
                setImages(myImage);
            }
        }
    }

    class UploadPicVpAdt extends FragmentStatePagerAdapter {

        HashMap<Integer, PictureFragment> pictureFragmentHashMap = new HashMap<>();

        public UploadPicVpAdt(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            if (pictureFragmentHashMap.get(position) == null) {
                pictureFragmentHashMap.put(position, new PictureFragment());
            }
            PictureFragment pictureFragment = pictureFragmentHashMap.get(position);
            pictureFragment.setImage(images.getValue().get(position));
            return pictureFragment;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return images.getValue().size();
        }
    }

    public void clickComplete(View v) {
        ApiManager.getInstance().uploadPicture(null, null, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                pictureUploadNavigation.finish();
            }

            @Override
            public void failed(String msg) {

            }
        });
    }
}
