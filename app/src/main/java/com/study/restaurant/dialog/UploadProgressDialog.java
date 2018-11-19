package com.study.restaurant.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.R;
import com.study.restaurant.util.MyGlide;


/**
 * Created by yangsarang on 2018. 3. 2..
 */

public class UploadProgressDialog extends Dialog {
    ProgressBar progressBar;
    ProgressBar timerProgress;
    TextView percentage;
    TextView msg;
    ImageView img;

    public UploadProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_upload_progress);
        progressBar = findViewById(R.id.progress_bar);
        timerProgress = findViewById(R.id.timer_progress);
        percentage = findViewById(R.id.percentage);
        msg = findViewById(R.id.msg);
        img = findViewById(R.id.img);
        setCancelable(false);
    }

    public void showImage(String path) {
        img.setVisibility(View.VISIBLE);
        MyGlide.with(getContext())
                .load(path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);
    }

    public void hideImage() {
        img.setVisibility(View.GONE);
    }

    public void setProcessProgress(int processProgress) {
        progressBar.setProgress(processProgress % 100);
        timerProgress.setProgress(processProgress % 100);
        percentage.setText("" + (processProgress % 100) + "%");
    }

    public void setProgressMessage(String progressMessage) {
        msg.setText(progressMessage);
    }
}
