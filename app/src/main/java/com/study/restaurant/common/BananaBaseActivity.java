package com.study.restaurant.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.study.restaurant.util.Logger;

public abstract class BananaBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.v("enter:" + getClass().getSimpleName());
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
