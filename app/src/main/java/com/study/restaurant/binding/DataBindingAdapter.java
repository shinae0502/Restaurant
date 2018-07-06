package com.study.restaurant.binding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;

import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.model.Store;

import java.util.ArrayList;

public class DataBindingAdapter {
    @BindingAdapter({"app:adapter", "app:onScrollListener", "app:myData"})
    public static void bind(RecyclerView recyclerView
            , StoreRvAdt rvAdt
            , RecyclerView.OnScrollListener onScrollListener
            , ArrayList<Store> storeArrayList
    ) {
        rvAdt.setStoreList(storeArrayList);
        recyclerView.setAdapter(rvAdt);
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @BindingAdapter({"app:onScrollChangeListener"})
    public static void nestedScrollBind(NestedScrollView nestedScrollView
            , NestedScrollView.OnScrollChangeListener onScrollChangeListener
    ) {
        nestedScrollView.setOnScrollChangeListener(onScrollChangeListener);
    }
}
