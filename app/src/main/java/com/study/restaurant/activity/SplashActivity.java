package com.study.restaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.facebook.login.LoginManager;
import com.study.restaurant.R;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.manager.BananaLoginManager;

public class SplashActivity extends AppCompatActivity {

    int currentAnim = R.drawable.icon_cuisine_japanese;
    ImageView progress;
    Animation anim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d("sarang", "onCreate");
        BananaLoginManager.getInstance(this).onCreate();
        progress = findViewById(R.id.progress);
        anim = AnimationUtils.loadAnimation(this, R.anim.rotation);
        new SplashImageHandler().sendEmptyMessage(0);

    }

    class SplashImageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (currentAnim) {
                case R.drawable.icon_cuisine_japanese:
                    currentAnim = R.drawable.icon_cuisine_korean;
                    break;

                case R.drawable.icon_cuisine_korean:
                    currentAnim = R.drawable.icon_cuisine_western;
                    break;

                case R.drawable.icon_cuisine_western:
                    currentAnim = R.drawable.icon_cuisine_world;
                    break;

                case R.drawable.icon_cuisine_world:
                    currentAnim = R.drawable.icon_cuisine_japanese;
                    break;
            }
            progress.setImageResource(currentAnim);
            progress.startAnimation(anim);
            sendEmptyMessageDelayed(0, 600);
        }
    }

    public void next(View v) {
        if (BananaPreference.getInstance(this).loadUser() != null
         && BananaPreference.getInstance(this).loadUser().isLogin()) {
            MainActivity.go(this);
        } else {
            LoginActivity.go(this);
        }
        finish();
    }

    public static void go(final AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        appCompatActivity.startActivity(intent);
    }

}



