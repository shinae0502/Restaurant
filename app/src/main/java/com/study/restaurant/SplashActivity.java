package com.study.restaurant;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    int currentAnim = R.drawable.icon_cuisine_japanese;
    ImageView progress;
    Animation anim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d("sarang", "onCreate");
        progress = findViewById(R.id.progress);
        anim = AnimationUtils.loadAnimation(this, R.anim.rotation);

        new Handler() {
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
        }.sendEmptyMessage(0);
    }

    public void next(View v) {
        LoginActivity.go(this);
    }
}



