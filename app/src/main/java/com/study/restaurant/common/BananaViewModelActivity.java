package com.study.restaurant.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BananaViewModelActivity<T extends ViewDataBinding, V extends ViewModel> extends AppCompatActivity {

    protected T mViewDataBinding;
    protected V mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
    }

    /**
     * @return layout resource id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * set view model class
     */
    public abstract Class<V> getViewModelClass();

    public void clickClose(View v) {
        onBackPressed();
    }

}
