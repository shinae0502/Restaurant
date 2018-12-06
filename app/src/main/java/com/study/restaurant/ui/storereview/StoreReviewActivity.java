package com.study.restaurant.ui.storereview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.model.StoreDetail;

public class StoreReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_review_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, StoreReviewFragment.newInstance())
                    .commitNow();
        }
    }

    public static void go(Context context, StoreDetail storeDetail) {
        Intent intent = new Intent(context, StoreReviewActivity.class);
        intent.putExtra("storeDetail", storeDetail);
        context.startActivity(intent);
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
