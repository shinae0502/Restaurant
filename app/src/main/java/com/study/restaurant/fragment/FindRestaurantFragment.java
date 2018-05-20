package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.FunctionImpl;
import com.study.restaurant.R;

public class FindRestaurantFragment extends Fragment implements FunctionImpl.FindRestaurant {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_find_restaurant, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) v.findViewById(R.id.toolbar));
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clickSelectLocation() {
        //TODO::
    }

    @Override
    public void showSelectLocationPopup() {
        //TODO::
    }

    @Override
    public void clickSearch() {
        //TODO::
    }

    @Override
    public void goSearch() {
        //TODO::
    }

    @Override
    public void clickMap() {
        //TODO::
    }

    @Override
    public void goMap() {
        //TODO::
    }

    @Override
    public void clickBanner() {
        //TODO::
    }

    @Override
    public void goBanner() {
        //TODO::
    }

    @Override
    public void clickSort() {
        //TODO::
    }

    @Override
    public void showSelectSortItemPopup() {
        //TODO::
    }

    @Override
    public void clickBoundary() {
        //TODO::
    }

    @Override
    public void showSelectBoundaryPopup() {
        //TODO::
    }

    @Override
    public void clickFilter() {
        //TODO::
    }

    @Override
    public void showSelectFilterPopup() {
        //TODO::
    }

    @Override
    public void clickEventBanner() {
        //TODO::
    }

    @Override
    public void goEvent() {
        //TODO::
    }

    @Override
    public void clickListItem() {
        //TODO::
    }

    @Override
    public void goRestaurant() {
        //TODO::
    }

    @Override
    public void setShowTopButton(boolean show) {
        //TODO::
    }
}
