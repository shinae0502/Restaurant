package com.study.restaurant.ui.menudetail;

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
import com.study.restaurant.databinding.MenuDetailFragmentBinding;
import com.study.restaurant.model.StoreMenu;

import java.util.ArrayList;

public class MenuDetailFragment extends Fragment {

    private MenuDetailViewModel mViewModel;
    private MenuDetailFragmentBinding menuDetailFragmentBinding;

    public static MenuDetailFragment newInstance() {
        return new MenuDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        menuDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.menu_detail_fragment, container, false);
        return menuDetailFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MenuDetailViewModel.class);
        mViewModel.setTitle(getStoreName());
        mViewModel.attachMenu(menuDetailFragmentBinding.menuLayout, menuDetailFragmentBinding.menuImageLayout, getStoreMenu());
        menuDetailFragmentBinding.setVm(mViewModel);

        // TODO: Use the ViewModel
    }

    public ArrayList<StoreMenu> getStoreMenu() {
        return getActivity().getIntent().getParcelableArrayListExtra("storeMenu");
    }

    public String getStoreName() {
        return getActivity().getIntent().getStringExtra("storeName");
    }

}
