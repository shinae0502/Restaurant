package com.study.restaurant.ui.profilepicdetailview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.restaurant.R;

public class ProfilePicDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic_detail);
    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, ProfilePicDetailActivity.class);
        appCompatActivity.startActivity(intent);
    }

}
