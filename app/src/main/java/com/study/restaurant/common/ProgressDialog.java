package com.study.restaurant.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.study.restaurant.R;

public class ProgressDialog extends Dialog {


    int currentAnim = R.drawable.icon_cuisine_japanese;
    ImageView progress;
    Animation anim;

    public ProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_progress);
        progress = findViewById(R.id.progress);
        anim = AnimationUtils.loadAnimation(context, R.anim.rotation);
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
}

