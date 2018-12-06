package com.study.restaurant.ui.loginview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.ui.mainview.MainActivity;
import com.study.restaurant.ui.OAuthSampleActivity;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.common.ProgressDialog;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.User;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.presenter.LoginPresenter;
import com.study.restaurant.util.Logger;

public class LoginActivity extends AppCompatActivity implements BananaNavigation.LoginNavigation {

    int currentBg = R.drawable.img_intro_bg_1;
    int currentAnim = R.anim.left_to_right;
    ImageView bg1;
    ImageView bg2;
    ImageView bg3;

    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        loginPresenter = new LoginPresenter(this);

        BananaLoginManager.getInstance(this).onCreate();

        bg1 = findViewById(R.id.bg);
        bg2 = findViewById(R.id.bg1);
        bg3 = findViewById(R.id.bg2);

        new LoginBackgroundChanger().sendEmptyMessage(0);

    }

    @Override
    public void goMain() {
        MainActivity.go(this);
    }

    private class LoginBackgroundChanger extends Handler {
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
    }

    public static void go(final AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, LoginActivity.class);
        appCompatActivity.startActivity(intent);
        appCompatActivity.finish();
    }

    public void skip(View view) {
        goMain();
    }

    public void requestFacebookLogin(View view) {
        progressDialog.show();
        BananaLoginManager.getInstance(this).requestFacebookLogin((result, user) -> {
            ApiManager.getInstance().requestFacebookLogin(user.accessToken, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    User user = new Gson().fromJson(result, User.class);
                    //사용자 정보 저장하기
                    BananaPreference.getInstance(LoginActivity.this).saveUser(user);
                    goMain();
                    finish();
                }

                @Override
                public void failed(String msg) {

                }
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.v("requestCode:" + requestCode + ", " + "resultCode:" + resultCode);
        BananaLoginManager.getInstance(this).onActivityResult(requestCode, resultCode, data);
        progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BananaLoginManager.getInstance(this).onDestroy();
    }

    public void onClickSignup(View v) {
        progressDialog.show();
        BananaLoginManager.getInstance(this).requestKakaoLogin((result, user) -> {
            Logger.d("onSuccessLogin");
            if (result == 0) {
                //로그인 성공
                BananaPreference.getInstance(this).saveUser(user);
                goMain();
                finish();
            } else {
                //로그인 실패
                Logger.e("kakao login failed");
            }
        });
    }


    public void naverLogin(View view) {
        startActivity(new Intent(this, OAuthSampleActivity.class));
    }
}
