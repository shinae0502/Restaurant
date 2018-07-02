package com.study.restaurant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.restaurant.R;
import com.study.restaurant.model.Store;

public class RestaurantDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
    }

    public static void go(AppCompatActivity appCompatActivity, Store store) {
        Intent intent = new Intent(appCompatActivity, RestaurantDetailActivity.class);
        intent.putExtra("store", store);
        appCompatActivity.startActivity(intent);
    }
}
