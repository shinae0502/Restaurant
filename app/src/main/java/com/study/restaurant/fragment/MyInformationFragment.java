package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.activity.SplashActivity;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.manager.BananaLoginManager;

public class MyInformationFragment extends Fragment {

    public MyInformationFragment() {
        // Required empty public constructor
    }

    public static MyInformationFragment newInstance() {
        MyInformationFragment fragment = new MyInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myinformation, container, false);

        ((TextView) v.findViewById(R.id.profile)).setText(BananaPreference.getInstance(getContext()).loadUser().toString());
        v.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BananaLoginManager.getInstance((AppCompatActivity) getActivity()).logout();
                SplashActivity.go((AppCompatActivity)getActivity());
            }
        });
        return v;
    }
}
