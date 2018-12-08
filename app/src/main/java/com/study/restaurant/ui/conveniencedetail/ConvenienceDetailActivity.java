package com.study.restaurant.ui.conveniencedetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.restaurant.R;
import com.study.restaurant.model.StoreDetail;

public class ConvenienceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convenience_detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ConvenienceDetailFragment.newInstance())
                    .commitNow();
        }
    }

    public static void go(Context context, StoreDetail storeDetail) {
        Intent intent = new Intent(context, ConvenienceDetailActivity.class);
        intent.putExtra("storeDetail", storeDetail);
        context.startActivity(intent);
    }
}
