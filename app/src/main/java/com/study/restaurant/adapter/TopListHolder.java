package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.restaurant.databinding.ItemToplistBinding;

public class TopListHolder extends RecyclerView.ViewHolder {
    ItemToplistBinding itemToplistBinding;

    public static TopListHolder create(ViewGroup parent, int viewType) {
        ItemToplistBinding itemToplistBinding = ItemToplistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TopListHolder(itemToplistBinding);
    }

    public TopListHolder(ItemToplistBinding itemToplistBinding) {
        super(itemToplistBinding.getRoot());
        this.itemToplistBinding = itemToplistBinding;
    }

    public ItemToplistBinding getItemToplistBinding() {
        return itemToplistBinding;
    }
}