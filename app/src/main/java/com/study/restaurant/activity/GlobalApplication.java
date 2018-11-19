package com.study.restaurant.activity;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;

import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.FindRestaurant;

import java.util.ArrayList;

public class GlobalApplication extends Application {

    FindRestaurant findRestaurant;
    ArrayList<AppCompatActivity> activityArrayList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        ApiManager.getInstance().setApplication(this);
    }

    public FindRestaurant getFindRestaurant() {
        if (findRestaurant == null)
            findRestaurant = new FindRestaurant();
        return findRestaurant;
    }

    public void setFindRestaurant(FindRestaurant findRestaurant) {
        this.findRestaurant = findRestaurant;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(AppCompatActivity appCompatActivity) {
        activityArrayList.add(appCompatActivity);
    }

    public void finishActivity() {
        for (AppCompatActivity appCompatActivity : activityArrayList) {
            if (appCompatActivity != null && !appCompatActivity.isDestroyed()) {
                appCompatActivity.finish();
            }
        }
    }
}
