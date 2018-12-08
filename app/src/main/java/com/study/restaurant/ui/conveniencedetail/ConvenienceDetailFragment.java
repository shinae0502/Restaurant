package com.study.restaurant.ui.conveniencedetail;

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
import com.study.restaurant.databinding.ConvenienceDetailFragmentBinding;
import com.study.restaurant.model.StoreDetail;

/**
 * {@link com.study.restaurant.model.StoreDetail}
 */
public class ConvenienceDetailFragment extends Fragment {

    private ConvenienceDetailViewModel mViewModel;

    public static ConvenienceDetailFragment newInstance() {
        return new ConvenienceDetailFragment();
    }

    ConvenienceDetailFragmentBinding convenienceDetailFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        convenienceDetailFragmentBinding
                = DataBindingUtil.inflate(inflater, R.layout.convenience_detail_fragment, container, false);
        convenienceDetailFragmentBinding.setStoreDetail(getStoreDetail());
        return convenienceDetailFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConvenienceDetailViewModel.class);
        convenienceDetailFragmentBinding.setVm(mViewModel);
        // TODO: Use the ViewModel
    }

    public StoreDetail getStoreDetail() {
        return getActivity().getIntent().getParcelableExtra("storeDetail");
    }

}
