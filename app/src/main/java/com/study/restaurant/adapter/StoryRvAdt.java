package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.ui.storedetailview.StoryDetailActivity;
import com.study.restaurant.model.Story;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;
import java.util.List;

public class StoryRvAdt extends ProgressRvAdt<RecyclerView.ViewHolder> {

    List<Story> storyList = new ArrayList<>();

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PROGRESS)
            return ProgressHolder.create(parent, viewType);

        return StoryHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof StoryHolder) {
            ((StoryHolder) holder).setStory(storyList.get(position));
            MyGlide.with(holder.itemView.getContext())
                    .load(storyList.get(position).getImage())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(((StoryHolder) holder).img);
        }

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(view -> StoryDetailActivity.go(holder.itemView.getContext(), storyList.get(position)));

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (storyList != null)
            count = storyList.size();

        return count + super.getItemCount();
    }
}