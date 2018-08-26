package com.study.restaurant.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.model.Story;
import com.study.restaurant.util.BottomDetectRecyclerView;

import java.util.ArrayList;

public class StoryFragment extends Fragment {
    BottomDetectRecyclerView storyRv;

    ArrayList<Story> storyArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_story, container, false);

        storyRv = view.findViewById(R.id.storyRv);
        storyRv.setAdapter(new StoryRvAdt());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 2;
                return 1;
            }
        });
        storyRv.setLayoutManager(gridLayoutManager);


        //하단 감지
        storyRv.addOnBottomDetectListener(() -> {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    requestData();
                }
            }.sendEmptyMessageDelayed(0, 5000);
        });

        //마진 설정
        storyRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = (int) view.getTag();

                if (position == 0) {
                    outRect.left = 10;
                    outRect.right = 10;
                    outRect.top = 10;
                    outRect.bottom = 5;
                } else {
                    outRect.right = position % 2 == 0 ? 10 : 5;
                    outRect.left = position % 2 == 0 ? 5 : 10;
                    outRect.top = position >= 2 ? 5 : 10;
                    outRect.bottom = 5;
                }
            }
        });

        requestData();

        return view;
    }

    public void requestData() {
        for (int i = 0; i < 20; i++) {
            Story story = new Story();
            story.setTitle("수요미식회 방속 맛집 7곳");
            story.setSubtitle("빙수, 전복, 대구맛집이 궁금해?");
            story.setImage("https://mp-seoul-image-production-s3.mangoplate.com/659356_1514182764198632.jpg");
            storyArrayList.add(story);
        }
        // 데이터가 로드되었기때문에 리스트의 하단 감지 플레그 초기화
        storyRv.setLast(false);
        ((StoryRvAdt) storyRv.getAdapter()).setStoryList(storyArrayList);
    }
}