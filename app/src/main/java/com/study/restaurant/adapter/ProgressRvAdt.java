package com.study.restaurant.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ProgressRvAdt<T> extends RecyclerView.Adapter {

    public static int VIEW_TYPE_PROGRESS = -1;

    protected boolean loadFinished;

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return loadFinished ? 0 : VIEW_TYPE_PROGRESS;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    /**
     * 상속받은 getItemCount와 더해주세요.
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return (loadFinished ? 0 : 1);
    }

    public void setLoadFinished(boolean loadFinished) {
        this.loadFinished = loadFinished;

    }

    public void setDismissProgress() {
        if (progressImage != null)
            progressImage.setVisibility(View.GONE);
    }

    ImageView progressImage;

    public void setProgressImage(ImageView image) {
        progressImage = image;
    }

}
