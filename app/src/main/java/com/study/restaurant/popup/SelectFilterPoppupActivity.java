package com.study.restaurant.popup;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.databinding.ActivitySelectFilterPoppupBinding;
import com.study.restaurant.model.Filter;
import com.study.restaurant.BR;
import com.study.restaurant.util.LOG;

public class SelectFilterPoppupActivity extends BasePopupActivity {

    Filter filter;
    ActivitySelectFilterPoppupBinding activitySelectFilterPopupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySelectFilterPopupBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_select_filter_poppup);
        setDimClickListener();

        try {
            filter = ((GlobalApplication) getApplication()).getFilter().clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        refreshCategoryUI();
        refreshFoodUI();
        refreshCostUI();
        refreshParkUI();

        filter.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                LOG.d("" + propertyId);
                if (propertyId == BR.all || propertyId == BR.wannaGo || propertyId == BR.haveBeen) {
                    refreshCategoryUI();
                }

                if (propertyId == BR.koreanFood || propertyId == BR.japaneseFood || propertyId == BR.chineseFood
                        || propertyId == BR.westernFood || propertyId == BR.worldWideFood || propertyId == BR.buffet
                        || propertyId == BR.cafe || propertyId == BR.bar) {
                    refreshFoodUI();
                }

                if (propertyId == BR.cost1 || propertyId == BR.cost2 || propertyId == BR.cost3 || propertyId == BR.cost4) {
                    refreshCostUI();
                }

                if (propertyId == BR.dontCare || propertyId == BR.available) {
                    refreshParkUI();
                }
            }
        });

    }

    private void refreshParkUI() {
        LOG.d("");
        activitySelectFilterPopupBinding.dontCare.setSelected(filter.isDontCare());
        activitySelectFilterPopupBinding.available.setSelected(filter.isAvailable());
    }

    private void refreshCostUI() {
        activitySelectFilterPopupBinding.cost1.setSelected(filter.isCost1());
        activitySelectFilterPopupBinding.cost2.setSelected(filter.isCost2());
        activitySelectFilterPopupBinding.cost3.setSelected(filter.isCost3());
        activitySelectFilterPopupBinding.cost4.setSelected(filter.isCost4());
    }

    private void refreshFoodUI() {
        LOG.d("");
        activitySelectFilterPopupBinding.koeranFood.setSelected(filter.isKoreanFood());
        activitySelectFilterPopupBinding.japaneseFood.setSelected(filter.isJapaneseFood());
        activitySelectFilterPopupBinding.chineseFood.setSelected(filter.isChineseFood());
        activitySelectFilterPopupBinding.westernFood.setSelected(filter.isWesternFood());
        activitySelectFilterPopupBinding.worldWideFood.setSelected(filter.isWorldWideFood());
        activitySelectFilterPopupBinding.buffet.setSelected(filter.isBuffet());
        activitySelectFilterPopupBinding.cafe.setSelected(filter.isCafe());
        activitySelectFilterPopupBinding.bar.setSelected(filter.isBar());
    }

    private void refreshCategoryUI() {
        LOG.d("");
        activitySelectFilterPopupBinding.all.setSelected(filter.isAll());
        activitySelectFilterPopupBinding.wannaGo.setSelected(filter.isWannaGo());
        activitySelectFilterPopupBinding.haveBeen.setSelected(filter.isHaveBeen());
    }

    public static void show(AppCompatActivity appCompatActivity) {
        BasePopupActivity.show(appCompatActivity, new Intent(appCompatActivity, SelectFilterPoppupActivity.class), 0x04);
    }

    public void clickAll(View view) {
        filter.setAll(true);
        filter.setWannaGo(false);
        filter.setHaveBeen(false);
    }

    public void clickWannaGo(View view) {
        filter.setAll(false);
        filter.setWannaGo(true);
        filter.setHaveBeen(false);
    }

    public void clickHaveBeen(View view) {
        filter.setAll(false);
        filter.setWannaGo(false);
        filter.setHaveBeen(true);
    }

    public void clickKoreanFood(View view) {
        filter.setKoreanFood(!filter.isKoreanFood());
    }

    public void clickJapaneseFood(View view) {
        filter.setJapaneseFood(!filter.isJapaneseFood());
    }

    public void clickChineseFood(View view) {
        filter.setChineseFood(!filter.isChineseFood());
    }

    public void clickWesternFood(View view) {
        filter.setWesternFood(!filter.isWesternFood());
    }

    public void clickWorldWideFood(View view) {
        filter.setWorldWideFood(!filter.isWorldWideFood());
    }

    public void clickBuffet(View view) {
        filter.setBuffet(!filter.isBuffet());
    }

    public void clickCafe(View view) {
        filter.setCafe(!filter.isCafe());
    }

    public void clickBar(View view) {
        filter.setBuffet(!filter.isBuffet());
    }

    public void clickCost1(View view) {
        filter.setCost1(!filter.isCost1());
    }

    public void clickCost2(View view) {
        filter.setCost2(!filter.isCost2());
    }

    public void clickCost3(View view) {
        filter.setCost3(!filter.isCost3());
    }

    public void clickCost4(View view) {
        filter.setCost4(!filter.isCost4());
    }

    public void clickDonCare(View view) {
        filter.setDontCare(true);
        filter.setAvailable(false);
    }

    public void clickAvailavle(View view) {
        filter.setDontCare(false);
        filter.setAvailable(true);
    }

    public void adapt(View view) {
        ((GlobalApplication)getApplication()).setFilter(filter);
        setResult(Activity.RESULT_OK);
        finishWithAnimation();
    }

    public void cancel(View view) {
        finishWithAnimation();
    }
}
