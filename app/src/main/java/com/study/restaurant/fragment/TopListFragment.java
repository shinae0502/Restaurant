package com.study.restaurant.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.adapter.TopListRvAdt;
import com.study.restaurant.model.TopList;
import com.study.restaurant.util.BottomDetectRecyclerView;

import java.util.ArrayList;

public class
TopListFragment extends Fragment {

    private BottomDetectRecyclerView topListRv;

    ArrayList<TopList> toplistList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_list, container, false);

        topListRv = view.findViewById(R.id.topListRv);
        topListRv.setAdapter(new TopListRvAdt());

        topListRv.setLayoutManager(new LinearLayoutManager(getContext()));

        //하단 감지
        topListRv.addOnBottomDetectListener(() -> {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    //requestData();
                }
            }.sendEmptyMessageDelayed(0, 5000);
        });

        requestData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() { // ----- ???
        super.onDetach();
    }

    public void requestData() {
        for (int i = 0; i < 20; i++) {
            TopList topList = new TopList();
            topList.setTitle("수요미식회 방속 맛집 7곳");
            topList.setSubtitle("빙수, 전복, 대구맛집이 궁금해?");
            topList.setImage("https://mp-seoul-image-production-s3.mangoplate.com/659356_1514182764198632.jpg");
            toplistList.add(topList);
        }
        // 데이터가 로드되었기때문에 리스트의 하단 감지 플레그 초기화
        topListRv.setLast(false);
        ((TopListRvAdt) topListRv.getAdapter()).setTopLists(toplistList);
    }


}

