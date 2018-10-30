package com.study.restaurant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.R;
import com.study.restaurant.model.StoryDetail;
import com.study.restaurant.util.MyGlide;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailContentsFragment extends Fragment {


    private StoryDetail storyDetail;

    public StoryDetailContentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_story_detail_contents, container, false);
        ImageView img = v.findViewById(R.id.imgStoryDetailContents);
        MyGlide.with(getContext())
                .load(storyDetail.getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);
        TextView tv = v.findViewById(R.id.tvStoryDetailContents);
        tv.setText(storyDetail.getContents());
        return v;
    }

    public void setStoryDetail(StoryDetail storyDetail) {
        this.storyDetail = storyDetail;
    }
}
