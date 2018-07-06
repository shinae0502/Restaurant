package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.restaurant.databinding.ItemFindRestaurantMenuBinding;
import com.study.restaurant.databinding.ProgressBinding;

public class FindReataurantMenuHolder extends RecyclerView.ViewHolder {
    ItemFindRestaurantMenuBinding findRestaurantMenuBinding;

    public static FindReataurantMenuHolder create(ViewGroup parent, int viewType) {
        return new FindReataurantMenuHolder(
                ItemFindRestaurantMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    public FindReataurantMenuHolder(ItemFindRestaurantMenuBinding findRestaurantMenuBinding) {
        super(findRestaurantMenuBinding.getRoot());
        this.findRestaurantMenuBinding = findRestaurantMenuBinding;
    }
}