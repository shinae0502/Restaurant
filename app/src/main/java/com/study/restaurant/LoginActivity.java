package com.study.restaurant;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static void go(final AppCompatActivity appCompatActivity)
    {
        new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent(appCompatActivity, LoginActivity.class);
                appCompatActivity.startActivity(intent);
            }
        }.sendEmptyMessageDelayed(0, 1000);

    }

    public void skip(View view) {
        MainActivity.go(this);
    }
}
