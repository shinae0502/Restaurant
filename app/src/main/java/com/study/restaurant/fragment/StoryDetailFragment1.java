package com.study.restaurant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment1 extends Fragment {

    public StoryDetailFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story_detail_fragment1, container, false);
    }

}
