package com.study.restaurant.ui.storereview;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.databinding.StoreReviewFragmentBinding;
import com.study.restaurant.model.News;
import com.study.restaurant.model.StoreDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreReviewFragment extends Fragment {

    private StoreReviewViewModel mViewModel;
    private StoreReviewFragmentBinding storeReviewFragmentBinding;

    public static StoreReviewFragment newInstance() {
        return new StoreReviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        storeReviewFragmentBinding =
                DataBindingUtil.inflate(inflater, R.layout.store_review_fragment, container, false);
        storeReviewFragmentBinding.storeReviewRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 10;
                outRect.left = 20;
                outRect.right = 20;
                outRect.top = 10;
            }
        });

        storeReviewFragmentBinding.titleBar.setTitle(getStoreDetail().getRestaurant().getStore_name());

        return storeReviewFragmentBinding.getRoot();
    }

    StoreDetail getStoreDetail() {
        return getActivity().getIntent().getParcelableExtra("storeDetail");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StoreReviewViewModel.class);
        // TODO: Use the ViewModel
        storeReviewFragmentBinding.setVm(mViewModel);
        mViewModel.setRefreshLayout(storeReviewFragmentBinding.storeReviewRl);
        mViewModel.requestNews();
    }

}
