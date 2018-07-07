package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.model.Banner;
import com.study.restaurant.model.FindRestaurant;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.MyGlide;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;

import java.util.ArrayList;
import java.util.List;

public class StoreRvAdt extends ProgressRvAdt<RecyclerView.ViewHolder> {

    public static int VIEW_TYPE_BANNER = 2;
    public static int VIEW_TYPE_MENU = 3;

    FindRestaurantViewModel vm;
    private FindRestaurant findRestaurant;

    public void setVm(FindRestaurantViewModel vm) {
        this.vm = vm;
        findRestaurant = vm.getFindRestaurant();
        notifyDataSetChanged();
    }

    public FindRestaurant getFindRestaurant() {
        return findRestaurant;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_BANNER;
        } else if (position == 1) {
            return VIEW_TYPE_MENU;
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
            Store store = (Store) vm.getRvItem(position);
            store.setPosition(position);
            ((StoreHolder) holder).setStore(store);
            MyGlide.with(holder.itemView.getContext())
                    .load(store.getImg())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(((StoreHolder) holder).img);
        }

        if (holder instanceof FindReataurantMenuHolder) {
            ((FindReataurantMenuHolder) holder).findRestaurantMenuBinding.setBoundary(findRestaurant.getBoundary());
            ((FindReataurantMenuHolder) holder).findRestaurantMenuBinding.setSort(findRestaurant.getSort());
            ((FindReataurantMenuHolder) holder).findRestaurantMenuBinding.setVm(vm);
        }

        if (holder instanceof ProgressHolder) {
            ((ProgressHolder) holder).progressBinding.progressImg.setVisibility(View.VISIBLE);
        }
        //holder.itemBinding.parent.setOnClickListener(view -> RestaurantDetailActivity.go(appCompatActivity, storeList.get(position)));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (vm != null)
            count = vm.getRvCount() + super.getItemCount();
        return count;
    }
}
