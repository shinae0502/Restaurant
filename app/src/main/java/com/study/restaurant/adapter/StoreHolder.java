package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ItemBinding;
import com.study.restaurant.model.Store;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;

public class StoreHolder extends RecyclerView.ViewHolder {
    ItemBinding itemBinding;

    public static StoreHolder create(ViewGroup parent, int viewType) {
        ItemBinding itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StoreHolder(itemBinding);
    }

    ImageView img;

    public StoreHolder(ItemBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
        img = itemView.findViewById(R.id.img);
    }

    public void setVm(FindRestaurantViewModel viewModel) {
        itemBinding.setVm(viewModel);
    }

    public ItemBinding getItemBinding() {
        return itemBinding;
    }

    public void setStore(Store store) {
        itemBinding.setStore(store);
    }
}