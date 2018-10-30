package com.study.restaurant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.model.StoryDetail;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment3 extends Fragment {


    private ArrayList<StoryDetail> storyDetailList;
    ViewPager storyDetailPager;

    public StoryDetailFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_detail_fragment3, container, false);
        storyDetailPager = view.findViewById(R.id.storyDetailPager);
        storyDetailPager.setAdapter(new StoryDetailListAdapter(getChildFragmentManager()));
        storyDetailPager.setClipToPadding(false);
        storyDetailPager.setPadding(60,0,60,0);
        storyDetailPager.setPageMargin(20);

        return view;
    }

    public void setStoryDetailList(ArrayList<StoryDetail> storyDetailList) {
        this.storyDetailList = storyDetailList;
    }

    public class StoryDetailListAdapter extends FragmentStatePagerAdapter
    {

        public StoryDetailListAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            StoryDetailContentsFragment storyDetailContentsFragment = new StoryDetailContentsFragment();
            storyDetailContentsFragment.setStoryDetail(storyDetailList.get(position));
            return storyDetailContentsFragment;
        }

        @Override
        public int getCount() {
            int count = 0;
            if(storyDetailList != null)
                count = storyDetailList.size();
            return count;
        }
    }
}
