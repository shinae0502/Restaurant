package com.study.restaurant;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    int currentBg = R.drawable.img_intro_bg_1;
    ImageView bg1;
    ImageView bg2;
    ImageView bg3;
    Animation anim1;
    Animation anim2;
    Animation anim3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bg1 = findViewById(R.id.bg);
        bg2 = findViewById(R.id.bg1);
        bg3 = findViewById(R.id.bg2);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim3 = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (currentBg) {
                    case R.drawable.img_intro_bg_1:
                        currentBg = R.drawable.img_intro_bg_2;
                        bg1.startAnimation(anim1);
                        bg1.setVisibility(View.GONE);
                        break;

                    case R.drawable.img_intro_bg_2:
                        currentBg = R.drawable.img_intro_bg_3;
                        bg2.startAnimation(anim2);
                        bg2.setVisibility(View.GONE);
                        break;

                    case R.drawable.img_intro_bg_3:
                        currentBg = R.drawable.img_intro_bg_1;
                        bg3.startAnimation(anim3);
                        bg3.setVisibility(View.GONE);
                        break;
                }
                sendEmptyMessageDelayed(0, 3000);
            }
        }.sendEmptyMessage(0);
    }

    public static void go(final AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, LoginActivity.class);
        appCompatActivity.startActivity(intent);
    }

    public void skip(View view) {
        MainActivity.go(this);
    }
}
