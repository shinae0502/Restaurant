package com.study.restaurant.popup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.study.restaurant.R;

public class BasePopupActivity extends AppCompatActivity {
    public void finishWithAnimation() {
        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }

    public static void show(AppCompatActivity appCompatActivity, Intent intent, int requestCode) {
        appCompatActivity.startActivityForResult(intent, requestCode);
        appCompatActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }

    protected void setDimClickListener() {
        //팝업 딤처리 부분 클릭 시 종료
        findViewById(R.id.dim).setOnClickListener(view -> finishWithAnimation());
    }

    @Override
    public void onBackPressed() {
        finishWithAnimation();
    }
}
