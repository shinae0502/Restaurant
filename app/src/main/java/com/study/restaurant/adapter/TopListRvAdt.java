package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.study.restaurant.ui.toplistdetailview.TopListDetailActivity;
import com.study.restaurant.model.TopList;

import java.util.ArrayList;

public class TopListRvAdt extends RecyclerView.Adapter<TopListHolder> {

    private ArrayList<TopList> topLists;

    @Override
    public TopListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TopListHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(TopListHolder holder, int position) {
        holder.itemToplistBinding.setTopList(topLists.get(position));
        holder.itemView.setOnClickListener(view -> TopListDetailActivity.go(holder.itemView.getContext(), topLists.get(position)));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (topLists != null)
            count = topLists.size();

        return count;
    }

    public void setTopLists(ArrayList<TopList> topLists) {
        this.topLists = topLists;
        notifyDataSetChanged();
    }
}