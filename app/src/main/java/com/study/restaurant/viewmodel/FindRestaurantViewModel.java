package com.study.restaurant.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Banner;
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

    Banner banner = new Banner();

    public Object getRvItem(int position) {
        if (position > 1) {
            return findRestaurant.getStoreArrayList().get(position - 2);
        } else {
            return null;
        }
    }

    public int getRvCount() {
        return 2 + findRestaurant.getStoreArrayList().size();
    }

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
        removeAllStore();
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

    public void setFindRestaurant(FindRestaurant findRestaurant) {
        this.findRestaurant = findRestaurant;
    }

    public void requestStoreSummary() {
        HashMap<String, String> param = new HashMap<>();
        param.put("region_id", findRestaurant.getCities().getSelectedRegionIds());
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
            removeAllStore();
            requestStoreSummary();
        } else {
            findRestaurantNavigation.showBoundaryPopup();
        }
    }

    public void clickFilter(View v) {
        findRestaurantNavigation.showFilterPopup();
    }


    public void removeAllStore() {
        findRestaurant.getStoreArrayList().removeAll(findRestaurant.getStoreArrayList());
        storeRvAdt.notifyDataSetChanged();
    }
}