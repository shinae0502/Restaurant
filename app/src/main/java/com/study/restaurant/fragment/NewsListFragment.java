package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.News;
import com.study.restaurant.util.MyGlide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsListFragment extends Fragment {

    RecyclerView rvNewsList;
    SwipeRefreshLayout srlNewsList;

    public NewsListFragment() {
        // Required empty public constructor
    }

    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);
        rvNewsList = v.findViewById(R.id.rvNewsList);
        rvNewsList.setAdapter(new ReviewRvAdt());
        rvNewsList.setLayoutManager(new LinearLayoutManager(getContext()));

        srlNewsList = v.findViewById(R.id.srlNewsList);
        srlNewsList.setOnRefreshListener(() -> requestNews());

        requestNews();
        return v;
    }

    void requestNews() {
        //뉴스 피드 요청하기
        Map<String, String> param = new HashMap<>();
        ApiManager.getInstance().getNews(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type type = new TypeToken<ArrayList<News>>() {
                }.getType();
                ArrayList<News> newsArrayList = new Gson().fromJson(result, type);
                ((ReviewRvAdt) rvNewsList.getAdapter()).setNewsList(newsArrayList);
                srlNewsList.setRefreshing(false);
            }

            @Override
            public void failed(String msg) {
                Toast.makeText(getContext(), "소식 요청에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
