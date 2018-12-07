package com.study.restaurant.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentPhotoBinding;
import com.study.restaurant.model.StorePicture;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {


    private StorePicture storePicture;

    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPhotoBinding fragmentPhotoBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false);
        fragmentPhotoBinding.setStorePicture(storePicture);
        return fragmentPhotoBinding.getRoot();
    }

    public void setImage(StorePicture storePicture) {
        this.storePicture = storePicture;
    }
}
