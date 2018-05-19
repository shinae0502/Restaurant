package com.study.restaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MangoPickFragment extends Fragment {

    public MangoPickFragment() {
        // Required empty public constructor
    }

    public static MangoPickFragment newInstance() {
        MangoPickFragment fragment = new MangoPickFragment();
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
        return inflater.inflate(R.layout.fragment_mango_pick, container, false);
    }
}
