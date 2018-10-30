package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.restaurant.databinding.ItemTopListBinding;


public class TopListHolder extends RecyclerView.ViewHolder {
    ItemTopListBinding itemToplistBinding;
    ImageView img;

    public static TopListHolder create(ViewGroup parent, int viewType) {
        ItemTopListBinding itemToplistBinding = ItemTopListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TopListHolder(itemToplistBinding);
    }

    public TopListHolder(ItemTopListBinding itemToplistBinding) {
        super(itemToplistBinding.getRoot());
        this.itemToplistBinding = itemToplistBinding;
        img = itemToplistBinding.img;
    }
}