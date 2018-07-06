package com.study.restaurant.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.FindRestaurant;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.LOG;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindRestaurantViewModel extends BaseObservable {
    StoreRvAdt storeRvAdt = new StoreRvAdt();

    ArrayList<Store> storeArrayList = new ArrayList<>();
    private FindRestaurant findRestaurant;

    @Bindable
    public ArrayList<Store> getStoreArrayList() {
        return storeArrayList;
    }

    public void setStoreArrayList(ArrayList<Store> storeArrayList) {
        this.storeArrayList.removeAll(this.storeArrayList);
        this.storeArrayList.addAll(storeArrayList);
        storeRvAdt.notifyDataSetChanged();
    }

    public void addStoreArrayList(ArrayList<Store> storeArrayList) {
        this.storeArrayList.addAll(storeArrayList);
        storeRvAdt.notifyDataSetChanged();
    }

    boolean isLast;

    public StoreRvAdt getStoreRvAdt() {
        return storeRvAdt;
    }

    public void setStoreRvAdt(StoreRvAdt storeRvAdt) {
        this.storeRvAdt = storeRvAdt;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && !isLast) {
                    isLast = true;
                    LOG.d("Last!!");
                    requestStoreSummary();
                } else {
                    LOG.d("not Last");
                }
            }
        };
    }

    public NestedScrollView.OnScrollChangeListener getOnScrollChangeListener() {
        return new NestedScrollView.OnScrollChangeListener() {
            /**
             * Called when the scroll position of a view changes.
             *
             * @param v          The view whose scroll position has changed.
             * @param scrollX    Current horizontal scroll origin.
             * @param scrollY    Current vertical scroll origin.
             * @param oldScrollX Previous horizontal scroll origin.
             * @param oldScrollY Previous vertical scroll origin.
             */
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) && !isLast) {
                    isLast = true;
                    LOG.d("Last!!!!");
                    requestStoreSummary();
                }
            }
        };
    }

    public void setFindRestaurant(FindRestaurant findRestaurant) {
        this.findRestaurant = findRestaurant;
    }

    public void requestStoreSummary() {
        HashMap<String, String> param = new HashMap<>();
        param.put("region_id", findRestaurant.getCities().getCurrentRegion().getRegion_id());
        param.put("boundary", "");
        param.put("filter", "");
        param.put("sort", findRestaurant.getSort().getAttribute());
        ApiManager.getInstance().getStoreSummary(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                LOG.d(result);
                isLast = false;
                Type listType = new TypeToken<ArrayList<Store>>() {
                }.getType();
                List<Store> storeList = new Gson().fromJson(result, listType);
                addStoreArrayList((ArrayList<Store>) storeList);
            }

            @Override
            public void failed(String msg) {

            }
        });
    }
}
