package com.study.restaurant.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentStoryDetailFragment3Binding;
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

    FragmentStoryDetailFragment3Binding fragmentStoryDetailFragment3Binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentStoryDetailFragment3Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_story_detail_fragment3, null, false);
        fragmentStoryDetailFragment3Binding.setStory(getActivity().getIntent().getParcelableExtra("story"));
        storyDetailPager = fragmentStoryDetailFragment3Binding.storyDetailPager;
        storyDetailPager.setAdapter(new StoryDetailListAdapter(getChildFragmentManager()));
        storyDetailPager.setClipToPadding(false);
        storyDetailPager.setPadding(60, 0, 60, 0);
        storyDetailPager.setPageMargin(20);
        storyDetailPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragmentStoryDetailFragment3Binding.tvCommentCount.setText("댓글 " + storyDetailList.get(position).getComment());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentStoryDetailFragment3Binding.setStory(getActivity().getIntent().getParcelableExtra("story"));

        return fragmentStoryDetailFragment3Binding.getRoot();
    }

    public void setStoryDetailList(ArrayList<StoryDetail> storyDetailList) {
        this.storyDetailList = storyDetailList;
    }

    public class StoryDetailListAdapter extends FragmentStatePagerAdapter {

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
            if (storyDetailList != null)
                count = storyDetailList.size();
            return count;
        }
    }
}
