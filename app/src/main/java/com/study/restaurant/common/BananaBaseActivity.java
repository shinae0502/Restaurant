package com.study.restaurant.common;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BananaBaseActivity extends AppCompatActivity {

    ViewDataBinding viewDataBinding;
    ViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = initDataBinding();
        viewDataBinding.setLifecycleOwner(this);
        viewModel = initViewModel();
        initUI();
        initData();
    }

    public abstract ViewDataBinding initDataBinding();

    public abstract ViewModel initViewModel();

    public abstract void initUI();

    public abstract void initData();

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
