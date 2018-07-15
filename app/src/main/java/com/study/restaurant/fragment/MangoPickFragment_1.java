package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

public class MangoPickFragment_1 extends Fragment {

    public MangoPickFragment_1() {
        // Required empty public constructor
    }

    public static MangoPickFragment_1 newInstance() {
        MangoPickFragment_1 fragment = new MangoPickFragment_1();
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
        View v = inflater.inflate(R.layout.fragment_mango_pick, container, false);
        return v;
    }
}
