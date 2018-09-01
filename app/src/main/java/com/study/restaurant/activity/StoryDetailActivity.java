package com.study.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.study.restaurant.R;
import com.study.restaurant.fragment.StoryDetailFragment1;
import com.study.restaurant.fragment.StoryDetailFragment2;
import com.study.restaurant.fragment.StoryDetailFragment3;

public class StoryDetailActivity extends AppCompatActivity {

    ViewPager vpDetailStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        vpDetailStory = findViewById(R.id.vpDetailStory);
        vpDetailStory.setAdapter(new DetailStoryVpAdt(getSupportFragmentManager()));
    }

    public static void go(Context context) {
        Intent intent = new Intent(context, StoryDetailActivity.class);
        context.startActivity(intent);
    }

    private class DetailStoryVpAdt extends FragmentStatePagerAdapter {

        public DetailStoryVpAdt(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return new StoryDetailFragment1();
            } else if (position == 1) {
                return new StoryDetailFragment2();
            } else {
                return new StoryDetailFragment3();
            }

        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
