package com.study.restaurant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, ProfileActivity.class);
        appCompatActivity.startActivity(intent);
    }

    public void clickProfile(View view) {
        ProfilePicDetailActivity.go(this);
    }

    public void clickFollower(View v) {
        FollowerActivity.go(this);
    }

    public void clickFollowing(View v) {
        FollowerActivity.go(this);
    }

    public void clickReview(View view) {
        TimelineActivity.go(this);
    }

    public void clickCheckIn(View view) {
        TimelineActivity.go(this);
    }

    public void clickPicture(View view) {
        TimelineActivity.go(this);
    }

    public void clickWannaGo(View view) {
        TimelineActivity.go(this);
    }

    public void clickMyList(View view) {
        TimelineActivity.go(this);
    }

    public void clickBookMark(View view) {
        TimelineActivity.go(this);
    }
}
