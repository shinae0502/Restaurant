package com.study.restaurant.binding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;

import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.model.FindRestaurant;
import com.study.restaurant.model.Store;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;

import java.util.ArrayList;

public class DataBindingAdapter {
    @BindingAdapter({"app:adapter", "app:onScrollListener", "app:vm"})
    public static void bind(RecyclerView recyclerView
            , StoreRvAdt rvAdt
            , RecyclerView.OnScrollListener onScrollListener
            , FindRestaurantViewModel vm
    ) {
        rvAdt.setVm(vm);
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
