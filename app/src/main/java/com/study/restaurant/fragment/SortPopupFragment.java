package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

public class SortPopupFragment extends Fragment {

    public SortPopupFragment() {
        // Required empty public constructor
    }

    public static SortPopupFragment newInstance(String param1, String param2) {
        SortPopupFragment fragment = new SortPopupFragment();
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
        return inflater.inflate(R.layout.fragment_sort_popup, container, false);
    }
}
