package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.restaurant.databinding.ItemReviewBinding;

public class ReviewHolder extends RecyclerView.ViewHolder {
    ItemReviewBinding itemReviewBinding;

    public static ReviewHolder create(ViewGroup parent, int viewType) {
        ItemReviewBinding itemReviewBinding = ItemReviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReviewHolder(itemReviewBinding);
    }

    public ReviewHolder(ItemReviewBinding itemReviewBinding) {
        super(itemReviewBinding.getRoot());
        this.itemReviewBinding = itemReviewBinding;
    }

    public ItemReviewBinding getItemReviewBinding() {
        return itemReviewBinding;
    }
}
