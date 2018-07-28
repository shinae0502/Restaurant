package com.study.restaurant.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreSpec;
import com.study.restaurant.util.AppBarStateChangeListener;
import com.study.restaurant.util.LOG;
import com.study.restaurant.util.MyGlide;
import com.study.restaurant.viewmodel.RestaurantDetailViewModel;

import java.io.InputStream;

public class RestaurantDetailActivity extends AppCompatActivity implements RestaurantDetailViewModel.RestaurantDetailNavigation {

    //레스토랑 상세 정보 불러오기
    ActivityRestaurantDetailBinding activityRestaurantDetailBinding;
    RestaurantDetailViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create view binding
        activityRestaurantDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail);

        vm = new RestaurantDetailViewModel();

        StoreSpec storeSpec = null;
        /** 더미 테스트 코드 */
        try {
            Resources res = getBaseContext().getResources();
            InputStream in_s = res.openRawResource(R.raw.store_spec_dummy);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            storeSpec = new Gson().fromJson(new String(b), StoreSpec.class);
        } catch (Exception e) {
            LOG.d(e.toString());
        }

        vm.setStoreSpec(storeSpec);
        /** 네비게이션 설정하기 */
        vm.setRestaurantDetailNavigation(this);
        // data binding
        activityRestaurantDetailBinding.setVm(vm);
        activityRestaurantDetailBinding.layoutDetailRestaurantMain.setVm(vm);
        activityRestaurantDetailBinding.layoutReviewList.setVm(vm);
        activityRestaurantDetailBinding.layoutRelatedToplist.setVm(vm);
        activityRestaurantDetailBinding.layoutRelatedStory.setVm(vm);
        activityRestaurantDetailBinding.layoutAroundRestaurant.setVm(vm);

        activityRestaurantDetailBinding.setStore(getStore());
        activityRestaurantDetailBinding.layoutDetailRestaurantTitleBar.setStore(getStore());
        activityRestaurantDetailBinding.layoutDetailRestaurantMain.setStore(getStore());

        activityRestaurantDetailBinding.setStoreSpec(storeSpec);
        activityRestaurantDetailBinding.layoutDetailRestaurantTitleBar.setStoreSpec(storeSpec);
        activityRestaurantDetailBinding.layoutRestaurantInformation.setStoreSpec(storeSpec);


        // Set actionbar
        AppBarLayout appBarLayout = activityRestaurantDetailBinding.appBar;
        final Toolbar actionBar = activityRestaurantDetailBinding.layoutDetailRestaurantTitleBar.actionBar;
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.IDLE) {
                    actionBar.setSelected(false);
                    actionBar.setBackgroundColor(Color.WHITE);
//                    nestedScrollView.stopNestedScroll();
                } else if (state == State.COLLAPSED) {
                    actionBar.setSelected(true);
                    actionBar.setBackgroundColor(getResources().getColor(R.color.orange));
                }
            }
        });

        // Set main images
        MyGlide.with(this)
                .load(storeSpec.getImg1().getImg1())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img1);

        MyGlide.with(this)
                .load(storeSpec.getImg2().getImg1())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img2);

        MyGlide.with(this)
                .load(storeSpec.getImg3().getImg1())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img3);

        MyGlide.with(this)
                .load(storeSpec.getImg4().getImg1())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img4);

        MyGlide.with(this)
                .load(storeSpec.getImg5().getImg1())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img5);
    }

    public static void go(AppCompatActivity appCompatActivity, Store store) {
        Intent intent = new Intent(appCompatActivity, RestaurantDetailActivity.class);
        intent.putExtra("store", store);
        appCompatActivity.startActivity(intent);
    }

    private Store getStore() {
        return getIntent().getParcelableExtra("store");
    }

    public void clickClose(View view) {
        onBackPressed();
    }

    @Override
    public void goDetailPhoto() {
        PhotoDetailActivity.go(this);
    }
}
