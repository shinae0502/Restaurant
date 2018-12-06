package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;

import com.study.restaurant.databinding.ItemImgBinding;

public class StoreImageRvHolder extends RecyclerView.ViewHolder {
    public ItemImgBinding itemImgBinding;

    public StoreImageRvHolder(ItemImgBinding itemImgBinding) {
        super(itemImgBinding.getRoot());
        this.itemImgBinding = itemImgBinding;
    }
}
