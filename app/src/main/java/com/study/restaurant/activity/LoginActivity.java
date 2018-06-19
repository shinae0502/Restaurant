package com.study.restaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.ProgressDialog;
import com.study.restaurant.login.LoginProvider;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.User;
import com.study.restaurant.presenter.LoginPresenter;
import com.study.restaurant.util.LOG;

public class LoginActivity extends AppCompatActivity {

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

        progressDialog =  new ProgressDialog(this);

        loginPresenter = new LoginPresenter(this);

        BananaLoginManager.getInstance(this).onCreate();

        bg1 = findViewById(R.id.bg);
        bg2 = findViewById(R.id.bg1);
        bg3 = findViewById(R.id.bg2);

        new LoginBackgroundChanger().sendEmptyMessage(0);

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
        MainActivity.go(this);
    }

    public void requestFacebookLogin(View view) {
        progressDialog.show();
        BananaLoginManager.getInstance(this).requestFacebookLogin();
        BananaLoginManager.getInstance(this).setCallbackListener(new LoginProvider.CallBack() {
            @Override
            public void onSuccessLogin(User user) {
                //페이스북 로그인 하기
                ApiManager.getInstance().requestFacebookLogin(user.accessToken, new ApiManager.CallbackListener() {
                    @Override
                    public void callback(String result) {
                        if (loginPresenter.processLogin(result)) {
                            MainActivity.go(LoginActivity.this);
                            finish();
                        }
                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LOG.d("");
        BananaLoginManager.getInstance(this).onActivityResult(requestCode, resultCode, data);
        progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BananaLoginManager.getInstance(this).onDestroy();
        BananaLoginManager.getInstance(this).setCallbackListener(null);
    }

    public void onClickSignup(View v) {

        //리스너를 먼저 등록해야한다.
        BananaLoginManager.getInstance(this).setCallbackListener(new LoginProvider.CallBack() {
            @Override
            public void onSuccessLogin(User user) {
                LOG.d("onSuccessLogin");
                String accessToken = Session.getCurrentSession().getTokenInfo().getAccessToken();
                //카카오 로그인 성공
                //서버 로그인 api 호출
                ApiManager.getInstance().requestKakaoLogin(accessToken, new ApiManager.CallbackListener() {
                    @Override
                    public void callback(String result) {
                        if (loginPresenter.processLogin(result)) {
                            MainActivity.go(LoginActivity.this);
                            finish();
                        }
                    }

                    @Override
                    public void failed(String msg) {
                        LOG.d("failed");
                    }
                });
            }
        });

        progressDialog.show();
        Session.getCurrentSession().open(AuthType.KAKAO_TALK, this);
    }


    public void naverLogin(View view) {
        startActivity(new Intent(this, OAuthSampleActivity.class));
    }
}
