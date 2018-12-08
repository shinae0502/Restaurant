package com.study.restaurant.ui.incorrectinformation;

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
import com.study.restaurant.databinding.IncorrectInformationFragmentBinding;
import com.study.restaurant.model.Store;

public class IncorrectInformationFragment extends Fragment {

    private IncorrectInformationViewModel mViewModel;
    private IncorrectInformationFragmentBinding incorrectInformationFragmentBinding;

    public static IncorrectInformationFragment newInstance() {
        return new IncorrectInformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        incorrectInformationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.incorrect_information_fragment, container, false);
        return incorrectInformationFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(IncorrectInformationViewModel.class);
        mViewModel.setTitleBar(getStore().getStoreName());
        incorrectInformationFragmentBinding.setVm(mViewModel);
        // TODO: Use the ViewModel
    }

    private Store getStore() {
        return getActivity().getIntent().getParcelableExtra("store");
    }

}
