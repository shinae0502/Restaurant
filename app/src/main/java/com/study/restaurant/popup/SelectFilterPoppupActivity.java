package com.study.restaurant.popup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.restaurant.R;

public class SelectFilterPoppupActivity extends BasePopupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_filter_poppup);

        setDimClickListener();
    }

    public static void show(AppCompatActivity appCompatActivity) {
        BasePopupActivity.show(appCompatActivity, new Intent(appCompatActivity, SelectFilterPoppupActivity.class), 0x04);
    }
}
