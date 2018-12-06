package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ItemNewsBinding;
import com.study.restaurant.databinding.ItemReviewBinding;

public class ReviewHolder extends RecyclerView.ViewHolder {
    ItemNewsBinding itemNewsBinding;
    ImageView imgScroe;
    TextView tvScore;
    LinearLayout pictureLayout;


    public ReviewHolder(ItemNewsBinding itemNewsBinding) {
        super(itemNewsBinding.getRoot());
        this.itemNewsBinding = itemNewsBinding;
        imgScroe = itemNewsBinding.imgScore;
        tvScore = itemNewsBinding.tvScore;
        pictureLayout = itemNewsBinding.pictureLayout;
    }
}
