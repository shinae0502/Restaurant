package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.restaurant.databinding.ItemStoryBinding;
import com.study.restaurant.model.Story;

public class StoryHolder extends RecyclerView.ViewHolder {
    ItemStoryBinding itemStoryBinding;

    public static StoryHolder create(ViewGroup parent, int viewType) {
        ItemStoryBinding itemStoryBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StoryHolder(itemStoryBinding);
    }

    ImageView img;

    public StoryHolder(ItemStoryBinding itemStoryBinding) {
        super(itemStoryBinding.getRoot());
        this.itemStoryBinding = itemStoryBinding;
        img = itemStoryBinding.img;
    }


    public void setStory(Story story) {
        itemStoryBinding.setStory(story);
    }
}