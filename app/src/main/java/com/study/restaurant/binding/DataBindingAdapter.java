package com.study.restaurant.binding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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

    @BindingAdapter({"app:setSpanSizeLookup"})
    public static void spanSizeLookupBind(RecyclerView recyclerView, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanSizeLookup(spanSizeLookup);
    }

    @BindingAdapter({"app:spanCount"})
    public static void spanCountBind(RecyclerView recyclerView, int count) {
        ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanCount(count);
    }

    @BindingAdapter({"app:selected"})
    public static void selectedBind(ViewGroup viewGroup
            , boolean selected
    ) {
        viewGroup.setSelected(selected);
    }
}
