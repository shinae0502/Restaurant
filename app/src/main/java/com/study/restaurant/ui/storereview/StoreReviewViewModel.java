package com.study.restaurant.ui.storereview;

import android.arch.lifecycle.ViewModel;
import android.support.v4.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreReviewViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    ReviewRvAdt reviewRvAdt = new ReviewRvAdt();
    private SwipeRefreshLayout storeReviewRl;


    public ReviewRvAdt getReviewRvAdt() {
        return reviewRvAdt;
    }


    public SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return this::requestNews;
    }

    public void requestNews() {
        Map<String, String> param = new HashMap<>();
        ApiManager.getInstance().getNews(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                ArrayList<News> news = new Gson().fromJson(result, new TypeToken<ArrayList<News>>() {
                }.getType());
                reviewRvAdt.setNewsList(news);
                storeReviewRl.setRefreshing(false);
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    public void setRefreshLayout(SwipeRefreshLayout storeReviewRl) {
        this.storeReviewRl = storeReviewRl;
    }
}
