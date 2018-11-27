package com.study.restaurant.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentCheckInContentsBinding;
import com.study.restaurant.viewmodel.CheckInViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckInContentsFragment extends Fragment {


    public CheckInContentsFragment() {
        // Required empty public constructor
    }
    CheckInViewModel vm;

    public void setVm(CheckInViewModel vm) {
        this.vm = vm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCheckInContentsBinding fragmentCheckInContentsBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_check_in_contents, container, false);
        fragmentCheckInContentsBinding.setLifecycleOwner(this);
        fragmentCheckInContentsBinding.setVm(vm);
        return fragmentCheckInContentsBinding.getRoot();
    }

}
