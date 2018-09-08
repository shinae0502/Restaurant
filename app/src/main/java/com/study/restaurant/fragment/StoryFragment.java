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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Story;
import com.study.restaurant.util.BottomDetectRecyclerView;

import java.lang.reflect.Type;
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

        // 포지션에 따른 리스트 아이템 머지
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == storyRv.getAdapter().getItemCount() - 1)
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

    /** 스토리 데이터 요청하기 */
    public void requestData() {
        ApiManager.getInstance().getStory(new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                System.out.println(result);
                try {
                    Type listType = new TypeToken<ArrayList<Story>>() {
                    }.getType();
                    storyArrayList = new Gson().fromJson(result, listType);
                    // 데이터가 로드되었기때문에 리스트의 하단 감지 플레그 초기화
                    storyRv.setLast(false);
                    ((StoryRvAdt) storyRv.getAdapter()).setStoryList(storyArrayList);
                } catch (Exception e) {
                }
            }

            @Override
            public void failed(String msg) {

            }
        });
    }
}