package com.study.restaurant.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentStoryDetailFragment1Binding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment1 extends Fragment {

    FragmentStoryDetailFragment1Binding fragmentStoryDetailFragment1Binding;

    public StoryDetailFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentStoryDetailFragment1Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_story_detail_fragment1, null, false);
        fragmentStoryDetailFragment1Binding.setStory(getActivity().getIntent().getParcelableExtra("story"));
        return fragmentStoryDetailFragment1Binding.getRoot();
    }

}
