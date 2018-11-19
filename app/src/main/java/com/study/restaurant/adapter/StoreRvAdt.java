package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.model.FindRestaurant;
import com.study.restaurant.model.Store;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;

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
            ((StoreHolder) holder).setVm(vm);
        }

        if (holder instanceof FindReataurantMenuHolder) {
            ((FindReataurantMenuHolder) holder).findRestaurantMenuBinding.setBoundary(findRestaurant.getBoundary());
            ((FindReataurantMenuHolder) holder).findRestaurantMenuBinding.setSort(findRestaurant.getSort());
            ((FindReataurantMenuHolder) holder).findRestaurantMenuBinding.setVm(vm);
            ((FindReataurantMenuHolder) holder).findRestaurantMenuBinding.setFilter(findRestaurant.getFilter());
        }

        if (holder instanceof ProgressHolder) {
            ((ProgressHolder) holder).progressBinding.progressImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (vm != null)
            count = vm.getRvCount() + super.getItemCount();
        return count;
    }
}
