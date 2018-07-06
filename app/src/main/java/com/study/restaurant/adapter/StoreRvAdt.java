package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.model.Banner;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;
import java.util.List;

public class StoreRvAdt extends ProgressRvAdt<RecyclerView.ViewHolder> {

    public static int VIEW_TYPE_BANNER = 2;
    public static int VIEW_TYPE_MENU = 3;

    List<Store> storeList = new ArrayList<>();


    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_MENU;
        } else if (position == 1) {
            return VIEW_TYPE_BANNER;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PROGRESS)
            return ProgressHolder.create(parent, viewType);
        else if (viewType == VIEW_TYPE_BANNER)
            return BannerHolder.create(parent, viewType);
        else if (viewType == VIEW_TYPE_MENU)
            return FindReataurantMenuHolder.create(parent, viewType);

        return StoreHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StoreHolder) {
            storeList.get(position).setPosition(position);
            ((StoreHolder) holder).setStore(storeList.get(position));
            MyGlide.with(holder.itemView.getContext())
                    .load(storeList.get(position).getImg())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(((StoreHolder) holder).img);
        }
        //holder.itemBinding.parent.setOnClickListener(view -> RestaurantDetailActivity.go(appCompatActivity, storeList.get(position)));
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }
}
