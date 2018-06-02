package com.study.restaurant.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.manager.MyLocationManager;
import com.study.restaurant.presenter.FindRestaurantPresenter;
import com.study.restaurant.view.FindRestaurantView;

public class FindRestaurantFragment extends Fragment {

    private FindRestaurantPresenter findRestaurantPresenter;
    private FragmentFindRestaurantBinding fragmentFindRestaurantBinding;
    private FindRestaurantView findRestaurantView;

    public FindRestaurantFragment() {
        // Required empty public constructor
    }

    public static FindRestaurantFragment newInstance() {
        FindRestaurantFragment fragment = new FindRestaurantFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findRestaurantPresenter = new FindRestaurantPresenter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        //바인딩 초기화
        fragmentFindRestaurantBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_restaurant, container, false);

        //프리젠터 등록
        fragmentFindRestaurantBinding.setPresenter(findRestaurantPresenter);

        //뷰 등록
        findRestaurantView = new FindRestaurantView(getActivity(), fragmentFindRestaurantBinding);

        //뷰 초기화
        findRestaurantView.init();

        //프리젠터에 뷰 등록
        findRestaurantPresenter.registerView(findRestaurantView);

        //스토어 정보 요청
        findRestaurantPresenter.requestStoreSummery();

        //위치 요청
        MyLocationManager.getInstance(getActivity()).getLastLocation();

        return fragmentFindRestaurantBinding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
