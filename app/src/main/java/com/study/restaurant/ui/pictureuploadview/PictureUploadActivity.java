package com.study.restaurant.ui.pictureuploadview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.common.BananaViewModelActivity;
import com.study.restaurant.databinding.ActivityPictureUploadBinding;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.model.Store;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.ui.selectpcitureview.SelectPictureActivity;

import java.util.ArrayList;

/**
 * ViewModel: {@link UploadPictureViewModel}
 * Layout: {@link R.layout#activity_picture_upload}
 */
public class PictureUploadActivity
        extends BananaViewModelActivity<ActivityPictureUploadBinding, UploadPictureViewModel>
        implements BananaNavigation.PictureUploadNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setFragmentManager(getSupportFragmentManager());
        mViewDataBinding.setVm(mViewModel);
        mViewModel.setNavigation(this);
        mViewModel.setImages(getImages());
        if (getStore() != null) {
            mViewModel.restaurantName.setValue(getStore().getStoreName());
            mViewModel.regionName.setValue(getStore().getRegion_name());
        }
    }


    public static void go(Context context, ArrayList<MyImage> images, Store store) {
        Intent intent = new Intent(context, PictureUploadActivity.class);
        intent.putExtra("images", images);
        intent.putExtra("store", store);
        context.startActivity(intent);
    }

    public ArrayList<MyImage> getImages() {
        return getIntent().getParcelableArrayListExtra("images");
    }

    public Store getStore() {
        return getIntent().getParcelableExtra("store");
    }

    /**
     * @return layout resource id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_upload;
    }

    /**
     * set view model class
     */
    @Override
    public Class<UploadPictureViewModel> getViewModelClass() {
        return UploadPictureViewModel.class;
    }

    @Override
    public void goSelectPicture(ArrayList<MyImage> myImages) {
        SelectPictureActivity.go(this, BananaConstants.PictureUploadMode.POST_PICTURE, getStore(), mViewModel.images.getValue());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }
}
