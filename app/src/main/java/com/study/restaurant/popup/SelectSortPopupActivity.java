package com.study.restaurant.popup;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.databinding.ActivitySelectSortPopupBinding;
import com.study.restaurant.model.Sort;

public class SelectSortPopupActivity extends BasePopupActivity {

    Sort sort;
    ActivitySelectSortPopupBinding activitySelectSortPopupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySelectSortPopupBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_select_sort_popup);
        setDimClickListener();
        sort = ((GlobalApplication) getApplication()).getSort();
        refreshUI();
        sort.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                refreshUI();
                setResult(Activity.RESULT_OK);
                finishWithAnimation();
            }
        });
    }

    void refreshUI() {
        activitySelectSortPopupBinding.distance.setSelected(sort.isDistance());
        activitySelectSortPopupBinding.recommand.setSelected(sort.isRecommand());
        activitySelectSortPopupBinding.review.setSelected(sort.isReview());
        activitySelectSortPopupBinding.reputation.setSelected(sort.isReputatuon());
    }

    public static void show(AppCompatActivity appCompatActivity) {
        BasePopupActivity.show(appCompatActivity, new Intent(appCompatActivity, SelectSortPopupActivity.class), 0x02);
    }

    public void distance(View view) {
        sort.setDistance(true);
        sort.setReview(false);
        sort.setRecommand(false);
        sort.setReputatuon(false);
    }

    public void review(View view) {
        sort.setDistance(false);
        sort.setReview(true);
        sort.setRecommand(false);
        sort.setReputatuon(false);
    }

    public void recommand(View view) {
        sort.setDistance(false);
        sort.setReview(false);
        sort.setRecommand(true);
        sort.setReputatuon(false);
    }

    public void reputation(View view) {
        sort.setDistance(false);
        sort.setReview(false);
        sort.setRecommand(false);
        sort.setReputatuon(true);
    }
}
