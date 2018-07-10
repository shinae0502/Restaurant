package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.model.Story;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;
import java.util.List;

public class StoryRvAdt extends RecyclerView.Adapter<StoryHolder> {

    List<Story> storyList = new ArrayList<>();

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
        notifyDataSetChanged();
    }

    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return StoryHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, int position) {
        holder.setStory(storyList.get(position));
        MyGlide.with(holder.itemView.getContext())
                .load(storyList.get(position).getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (storyList != null)
            count = storyList.size();

        return count;
    }
}