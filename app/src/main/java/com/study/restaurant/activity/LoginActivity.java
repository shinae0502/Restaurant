package com.study.restaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kakao.auth.Session;
import com.study.restaurant.R;
import com.study.restaurant.login.KakaoLoginProvider;
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

        Log.d("sarang", "KakaoLoginProvider onCreate()");

        KakaoLoginProvider.SessionCallback callback = new KakaoLoginProvider.SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
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
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bananaLoginManager.onDestroy();
    }

    public void onClickSignup(View v) {

        findViewById(R.id.com_kakao_login).callOnClick();

        /*UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(LoginActivity.this, "onSessionClosed" + errorResult.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                Toast.makeText(LoginActivity.this, "onSuccess" + result.toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    public void naverLogin(View view) {
        startActivity(new Intent(this, OAuthSampleActivity.class));
    }
}
