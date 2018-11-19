package com.study.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.databinding.ActivityStoryDetailBinding;
import com.study.restaurant.fragment.StoryDetailFragment1;
import com.study.restaurant.fragment.StoryDetailFragment2;
import com.study.restaurant.fragment.StoryDetailFragment3;
import com.study.restaurant.model.Story;
import com.study.restaurant.model.StoryDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoryDetailActivity extends AppCompatActivity {

    ViewPager vpDetailStory;
    Story story;
    ActivityStoryDetailBinding activityStoryDetailBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStoryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_story_detail);
        vpDetailStory = activityStoryDetailBinding.vpDetailStory;
        vpDetailStory.setAdapter(new DetailStoryVpAdt(getSupportFragmentManager()));
        story = getIntent().getParcelableExtra("story");
        activityStoryDetailBinding.setStory(story);

        //스토리 상세 정보 요청하기

        Map<String, String> param = new HashMap<>();
        param.put("story_id", story.getStory_id());
        ApiManager.getInstance().getStoryDetail(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type listType = new TypeToken<ArrayList<StoryDetail>>() {
                }.getType();
                ArrayList<StoryDetail> storyDetailArrayList = new Gson().fromJson(result, listType);
                if (storyDetailArrayList.size() <= 0) {
                    Toast.makeText(StoryDetailActivity.this, "등록되지 않은 스토리 입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    findViewById(R.id.loading).setVisibility(View.GONE);
                    activityStoryDetailBinding.rlDetailStory.setVisibility(View.VISIBLE);
                    ((DetailStoryVpAdt) vpDetailStory.getAdapter()).setStoryDetailList(storyDetailArrayList);
                }
            }

            @Override
            public void failed(String msg) {

            }
        });

    }

    public static void go(Context context, Story story) {
        Intent intent = new Intent(context, StoryDetailActivity.class);
        intent.putExtra("story", story);
        context.startActivity(intent);
    }

    private class DetailStoryVpAdt extends FragmentStatePagerAdapter {

        private ArrayList<StoryDetail> storyDetailList;

        public DetailStoryVpAdt(FragmentManager fm) {
            super(fm);
        }

        public void setStoryDetailList(ArrayList<StoryDetail> storyDetailList) {
            this.storyDetailList = storyDetailList;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return new StoryDetailFragment1();
            } else if (position == 2) {
                return new StoryDetailFragment2();
            } else {
                StoryDetailFragment3 storyDetailFragment3 = new StoryDetailFragment3();
                storyDetailFragment3.setStoryDetailList(storyDetailList);
                return storyDetailFragment3;
            }

        }

        @Override
        public int getCount() {
            int count = 0;
            if (storyDetailList != null)
                count = 3;
            return count;
        }
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
