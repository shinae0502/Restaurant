package com.study.restaurant.ui.picturereview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.ui.picturereview.PictureReviewFragment;

public class PictureReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_review_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PictureReviewFragment.newInstance())
                    .commitNow();
        }
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
