package com.study.restaurant.adapter;

import android.databinding.Observable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.BR;
import com.study.restaurant.R;
import com.study.restaurant.databinding.ItemBinding;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.LOG;
import com.study.restaurant.util.MyGlide;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;

public class StoreHolder extends RecyclerView.ViewHolder {
    ItemBinding itemBinding;

    public static StoreHolder create(ViewGroup parent, int viewType) {
        ItemBinding itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StoreHolder(itemBinding);
    }

    ImageView img;
    Store lastStore;

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

    Observable.OnPropertyChangedCallback callback;

    public void setStore(Store store) {
        itemBinding.setStore(store);

        MyGlide.with(itemView.getContext())
                .load(store.getImg())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);

        itemBinding.imgFavorite.setSelected(store.isExistsFavority_id());
        if (callback != null && lastStore != null) {
            lastStore.removeOnPropertyChangedCallback(callback);
        }

        callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.existsFavority_id) {
                    LOG.d(((Store) sender).toString());
                    itemBinding.imgFavorite.setSelected(((Store) sender).isExistsFavority_id());
                }
            }
        };

        store.addOnPropertyChangedCallback(callback);
        lastStore = store;

    }
}