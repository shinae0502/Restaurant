package com.study.restaurant.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.activity.SearchActivity;
import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Boundary;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.FindRestaurant;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.FindRestaurantNavigation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindRestaurantViewModel extends BaseObservable {
    FindRestaurantNavigation findRestaurantNavigation;
    StoreRvAdt storeRvAdt = new StoreRvAdt();
    boolean isLast;
    private FindRestaurant findRestaurant;

    public FindRestaurantViewModel(FindRestaurantNavigation findRestaurantNavigation) {
        this.findRestaurantNavigation = findRestaurantNavigation;
    }

    public FindRestaurant getFindRestaurant() {
        return findRestaurant;
    }

    public void clickSelectLocation(View v) {
        findRestaurantNavigation.showSelectRegionPopup();
    }

    @Bindable
    public ArrayList<Store> getStoreArrayList() {
        return findRestaurant.getStoreArrayList();
    }

    public void setStoreArrayList(ArrayList<Store> storeArrayList) {
        findRestaurant.getStoreArrayList().removeAll(findRestaurant.getStoreArrayList());
        findRestaurant.getStoreArrayList().addAll(storeArrayList);
        storeRvAdt.notifyDataSetChanged();
    }

    public void addStoreArrayList(ArrayList<Store> storeArrayList) {
        findRestaurant.getStoreArrayList().addAll(storeArrayList);
        storeRvAdt.notifyDataSetChanged();
    }

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

    public void clickSearch(View v) {
        findRestaurantNavigation.goSearch();
    }

    public void clickSort(View v) {
        findRestaurantNavigation.showSortPopup();
    }

    public void clickBoundary(View v) {
        //내 주변을 클릭 시 기존 선택된 도시가 모두 해제된다.
        Boundary boundary = findRestaurant.getBoundary();
        Cities cities = findRestaurant.getCities();
        if (boundary.getBoundary().equals("내 주변")) {
            cities.releaseAllSelected();
            boundary.setLevel3(true);
            //requestAroundStore();
        } else {
            findRestaurantNavigation.showBoundaryPopup();
        }
    }

    public void clickFilter(View v) {
        findRestaurantNavigation.showFilterPopup();
    }


}
