package com.study.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaBaseActivity;
import com.study.restaurant.common.BananaViewModelActivity;
import com.study.restaurant.databinding.ActivitySearchKeywordBinding;
import com.study.restaurant.model.StoreKeyword;
import com.study.restaurant.viewmodel.SearchKeywordViewModel;

public class SearchKeywordActivity extends BananaViewModelActivity<ActivitySearchKeywordBinding, SearchKeywordViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewDataBinding.setKeyword(getStoreKeyword());
        mViewDataBinding.titleBarLeftBack.setTitle(getStoreKeyword().getRestaurant_name());
    }

    /**
     * @return layout resource id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_keyword;
    }

    /**
     * set view model class
     */
    @Override
    public Class<SearchKeywordViewModel> getViewModelClass() {
        return SearchKeywordViewModel.class;
    }

    public static void go(Context context, StoreKeyword storeKeyword) {
        Intent intent = new Intent(context, SearchKeywordActivity.class);
        intent.putExtra("storeKeyword", storeKeyword);
        context.startActivity(intent);
    }

    StoreKeyword getStoreKeyword() {
        return getIntent().getParcelableExtra("storeKeyword");
    }
}
