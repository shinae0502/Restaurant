package com.study.restaurant.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.activity.RestaurantDetailActivity;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;
import java.util.List;

public class StoreRvAdt extends RecyclerView.Adapter<StoreHolder> {

    List<Store> storeList = new ArrayList<>();
    AppCompatActivity appCompatActivity;

    public StoreRvAdt(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
        notifyDataSetChanged();
    }

    @Override
    public StoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return StoreHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(StoreHolder holder, int position) {
        storeList.get(position).setPosition(position);
        holder.setStore(storeList.get(position));
        MyGlide.with(holder.itemView.getContext())
                .load(storeList.get(position).getImg())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.img);
        holder.itemBinding.parent.setOnClickListener(view -> RestaurantDetailActivity.go(appCompatActivity, storeList.get(position)));
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }
}
