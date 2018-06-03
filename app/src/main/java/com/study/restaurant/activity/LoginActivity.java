package com.study.restaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.study.restaurant.R;
import com.study.restaurant.login.LoginProvider;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.User;

public class LoginActivity extends AppCompatActivity {

    int currentBg = R.drawable.img_intro_bg_1;
    int currentAnim = R.anim.left_to_right;
    ImageView bg1;
    ImageView bg2;
    ImageView bg3;
    BananaLoginManager bananaLoginManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bananaLoginManager = new BananaLoginManager(this);
        bananaLoginManager.onCreate();

        bananaLoginManager.setCallbackListener(new LoginProvider.CallBack() {
            @Override
            public void onSuccessLogin(User user) {
                //로그인 성공시
                MainActivity.go(LoginActivity.this);
                finish();
            }
        });

        bg1 = findViewById(R.id.bg);
        bg2 = findViewById(R.id.bg1);
        bg3 = findViewById(R.id.bg2);
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                currentAnim = currentAnim == R.anim.left_to_right ? R.anim.right_to_left : R.anim.left_to_right;

                switch (currentBg) {
                    case R.drawable.img_intro_bg_1:
                        currentBg = R.drawable.img_intro_bg_2;
                        bg1.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, currentAnim));
                        break;

                    case R.drawable.img_intro_bg_2:
                        currentBg = R.drawable.img_intro_bg_3;
                        bg2.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, currentAnim));
                        break;

                    case R.drawable.img_intro_bg_3:
                        currentBg = R.drawable.img_intro_bg_1;
                        bg3.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, currentAnim));
                        break;
                }
                sendEmptyMessageDelayed(0, 4000);
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

    public void requestFacebookLogin(View view) {
        bananaLoginManager.requestFacebookLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bananaLoginManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bananaLoginManager.onDestroy();
    }
}
