package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.restaurant.databinding.ItemBannerBinding;
import com.viewpagerindicator.CirclePageIndicator;

public class BannerHolder extends RecyclerView.ViewHolder {
    ItemBannerBinding itemBannerBinding;

    public static BannerHolder create(ViewGroup parent, int viewType) {
        ItemBannerBinding itemBannerBinding = ItemBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BannerHolder(itemBannerBinding);
    }

    public BannerHolder(ItemBannerBinding itemBannerBinding) {
        super(itemBannerBinding.getRoot());
        this.itemBannerBinding = itemBannerBinding;
        itemBannerBinding.bannerPager.setAdapter(new CustomPagerAdapter(itemBannerBinding.getRoot().getContext()));

        CirclePageIndicator circlePageIndicator = itemBannerBinding.indicator;
        circlePageIndicator.setViewPager(itemBannerBinding.bannerPager);
    }

}