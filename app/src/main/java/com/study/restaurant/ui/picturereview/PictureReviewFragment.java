package com.study.restaurant.ui.picturereview;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.PictureReviewFragmentBinding;

public class PictureReviewFragment extends Fragment {

    private PictureReviewViewModel mViewModel;
    PictureReviewFragmentBinding pictureReviewFragmentBinding;

    public static PictureReviewFragment newInstance() {
        return new PictureReviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pictureReviewFragmentBinding = DataBindingUtil
                .inflate(inflater, R.layout.picture_review_fragment, container, false);
        return pictureReviewFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PictureReviewViewModel.class);
        pictureReviewFragmentBinding.setVm(mViewModel);
        // TODO: Use the ViewModel
    }

}
