package com.study.restaurant.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.model.Store;

import java.util.ArrayList;

public class DataBindingAdapter {
    @BindingAdapter({"app:adapter", "app:layoutManager", "app:onScrollChangeListener", "app:data"})
    public static void bind(RecyclerView recyclerView
            , StoreRvAdt rvAdt
            , RecyclerView.LayoutManager layoutManager
            , RecyclerView.OnScrollListener onScrollListener
            , ArrayList<Store> storeArrayList
    ) {
        rvAdt.setStoreList(storeArrayList);
        recyclerView.setAdapter(rvAdt);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(onScrollListener);
    }
}
