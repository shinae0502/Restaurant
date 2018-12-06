package com.study.restaurant.popup;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.ui.GlobalApplication;
import com.study.restaurant.databinding.ActivitySelectDistancePopupBinding;
import com.study.restaurant.model.Boundary;

public class SelectDistancePopup extends BasePopupActivity {

    Boundary boundary;
    ActivitySelectDistancePopupBinding activitySelectDistancePopupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySelectDistancePopupBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_distance_popup);
        boundary = ((GlobalApplication) getApplication()).getFindRestaurant().getBoundary();
        refreshUI();
        boundary.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                refreshUI();
                finishWithAnimation();
            }
        });
        setDimClickListener();
        activitySelectDistancePopupBinding.setBoundary(boundary);
    }

    public static void show(AppCompatActivity appCompatActivity) {
        BasePopupActivity.show(appCompatActivity, new Intent(appCompatActivity, SelectDistancePopup.class), 0x03);
    }

    public void refreshUI() {
        activitySelectDistancePopupBinding.level1.setSelected(boundary.isLevel1());
        activitySelectDistancePopupBinding.level2.setSelected(boundary.isLevel2());
        activitySelectDistancePopupBinding.level3.setSelected(boundary.isLevel3());
        activitySelectDistancePopupBinding.level4.setSelected(boundary.isLevel4());
        activitySelectDistancePopupBinding.level5.setSelected(boundary.isLevel5());
    }

    public void level5(View view) {
        boundary.setLevel1(false);
        boundary.setLevel2(false);
        boundary.setLevel3(false);
        boundary.setLevel4(false);
        boundary.setLevel5(true);
    }

    public void level4(View view) {
        boundary.setLevel1(false);
        boundary.setLevel2(false);
        boundary.setLevel3(false);
        boundary.setLevel4(true);
        boundary.setLevel5(false);
    }

    public void level3(View view) {
        boundary.setLevel1(false);
        boundary.setLevel2(false);
        boundary.setLevel3(true);
        boundary.setLevel4(false);
        boundary.setLevel5(false);
    }

    public void level2(View view) {
        boundary.setLevel1(false);
        boundary.setLevel2(true);
        boundary.setLevel3(false);
        boundary.setLevel4(false);
        boundary.setLevel5(false);
    }

    public void level1(View view) {
        boundary.setLevel1(true);
        boundary.setLevel2(false);
        boundary.setLevel3(false);
        boundary.setLevel4(false);
        boundary.setLevel5(false);
    }

}
