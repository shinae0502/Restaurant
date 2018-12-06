package com.study.restaurant.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentCheckInBinding;
import com.study.restaurant.ui.checkinview.CheckInActivity;
import com.study.restaurant.ui.checkinview.CheckInViewModel;

/**
 * A simple {@link Fragment} subclass.
 * 부모 엑티비티 {@link CheckInActivity}
 */
public class CheckInFragment extends Fragment {


    public CheckInFragment() {
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
        FragmentCheckInBinding fragmentCheckInBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_check_in, container, false);
        fragmentCheckInBinding.setLifecycleOwner(this);
        fragmentCheckInBinding.setVm(vm);
        return fragmentCheckInBinding.getRoot();
    }

}
