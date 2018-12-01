package com.study.restaurant.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentPictureBinding;
import com.study.restaurant.model.MyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment {


    private MyImage myImage;

    public PictureFragment() {
        // Required empty public constructor
    }

    FragmentPictureBinding fragmentPictureBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPictureBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_picture, container, false);
        fragmentPictureBinding.setMyImage(myImage);
        return fragmentPictureBinding.getRoot();
    }

    public void setImage(MyImage myImage) {
        this.myImage = myImage;
        if (fragmentPictureBinding != null)
            fragmentPictureBinding.setMyImage(myImage);
    }
}
