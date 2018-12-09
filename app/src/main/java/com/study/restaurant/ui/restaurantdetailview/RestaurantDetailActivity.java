package com.study.restaurant.ui.restaurantdetailview;

import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.adapter.StoreImgRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaBaseActivity1;
import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreDetail;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.ui.checkinview.CheckInActivity;
import com.study.restaurant.ui.restaurantdetailmapview.RestaurantDetailMapActivity;
import com.study.restaurant.ui.selectpcitureview.SelectPictureActivity;
import com.study.restaurant.util.AppBarStateChangeListener;
import com.study.restaurant.util.Logger;
import com.study.restaurant.ui.restaurantdetailmapview.RestaurantDetailViewModel;

/**
 * {@link R.layout#activity_restaurant_detail}
 */
public class RestaurantDetailActivity extends BananaBaseActivity1
        implements BananaNavigation.RestaurantDetailNavigation,
        OnMapReadyCallback {

    /**********************************************************************************************
     * life cycle
     **********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**********************************************************************************************
     * initialize
     **********************************************************************************************/

    @Override
    public ViewDataBinding initDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail);
    }

    @Override
    public ViewModel initViewModel() {
        return ViewModelProviders.of(this).get(RestaurantDetailViewModel.class);
    }

    @Override
    public void initUI() {
        ActivityRestaurantDetailBinding vb = (ActivityRestaurantDetailBinding) getViewDataBinding();
        RestaurantDetailViewModel vm = (RestaurantDetailViewModel) getViewModel();

        vm.setRestaurantDetailNavigation(this);
        vb.setVm(vm);
        vb.layoutReviewList.reviewRv.setAdapter(vm.getReviewRvAdt());
        vb.layoutDetailRestaurantMain.storeImgRv.setAdapter(new StoreImgRvAdt());

        // Set actionbar
        AppBarLayout appBarLayout = vb.appBar;
        final Toolbar actionBar = vb.layoutDetailRestaurantTitleBar.actionBar;
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.IDLE) {
                    actionBar.setSelected(false);
                    actionBar.setBackgroundColor(Color.WHITE);
                } else if (state == State.COLLAPSED) {
                    actionBar.setSelected(true);
                    actionBar.setBackgroundColor(getResources().getColor(R.color.orange));
                }
            }
        });

        /** 스토어 상세 데이터가 갱신되었을 때 */
        vm.getStoreDetailObservableField().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                StoreDetail storeDetail = ((ObservableField<StoreDetail>) sender).get();
                ((StoreImgRvAdt) vb.layoutDetailRestaurantMain.storeImgRv.getAdapter()).setImageArrayList(storeDetail.getImage());
                vb.layoutReviewList.reviewRv.getAdapter().notifyDataSetChanged();
                vm.getTopListRvAdt().setTopLists(storeDetail.getToplist());
                ((ReviewRvAdt) vb.layoutReviewList.reviewRv.getAdapter()).setNewsList(storeDetail.getReviews());

            }
        });
        vb.layoutDetailRestaurantMain.storeImgRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 10;
            }
        });

    }

    @Override
    public void initData() {
        ActivityRestaurantDetailBinding vb = (ActivityRestaurantDetailBinding) getViewDataBinding();
        RestaurantDetailViewModel vm = (RestaurantDetailViewModel) getViewModel();

        vb.setStore(getStore());
        ApiManager.getInstance().getStoreDetail(getIntent().getParcelableExtra("store"), new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Logger.d(result);
                StoreDetail storeDetail = new Gson().fromJson(result, StoreDetail.class);
                vm.setStoreDetail(storeDetail);
                vb.layoutRestaurantInformation.setStoreDetail(storeDetail);
                storeDetail.attachMenu(vb.layoutRestaurantInformation.menuLayout, vb.layoutRestaurantInformation.menuImageLayout);
                for (int i = 0; i < storeDetail.getKeywords().size(); i++) {
                    vb.keyworkdLayout.addView(storeDetail.getKeywords().get(i).getView(RestaurantDetailActivity.this));
                }
            }

            @Override
            public void failed(String msg) {

            }
        });

    }

    /**********************************************************************************************
     * navigation
     **********************************************************************************************/

    public static void go(AppCompatActivity appCompatActivity, Store store) {
        Intent intent = new Intent(appCompatActivity, RestaurantDetailActivity.class);
        intent.putExtra("store", store);
        appCompatActivity.startActivity(intent);
    }

    @Override
    public void goCheckIn() {
        CheckInActivity.go(this, getStore());
    }

    @Override
    public void goReview() {
        SelectPictureActivity.go(this, BananaConstants.PictureUploadMode.REVIEW, getStore());
    }

    @Override
    public void goMap(Store store) {
        RestaurantDetailMapActivity.go(this, store);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showCallPermissionPopup() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("권한 요청");
        b.setMessage("전화를 하기위해선 전화하기 권한이 필요합니다. 권한을 요창하시겠습니까?");
        b.setPositiveButton("예", (dialogInterface, i) ->
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0x01));
        b.setNegativeButton("아니오", (dialogInterface, i) -> {
        });
        b.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ((RestaurantDetailViewModel) getViewModel())
                .onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    /**********************************************************************************************
     * function
     **********************************************************************************************/

    private Store getStore() {
        return getIntent().getParcelableExtra("store");
    }

    /**
     * TODO:: 닫기 기능
     */
    public void clickClose(View view) {
        onBackPressed();
    }

    /**
     * TODO:: 마이리스트 추가
     */
    public void addMyList(View v) {
    }

    /**
     * TODO:: 카카오톡 공유
     */
    public void shareKakao(View v) {
    }

    /**
     * TODO:: 일반공유
     */
    public void normarShare(View v) {
    }

    /**
     * TODO:: 즐겨찾기
     */
    public void addFavorite(View v) {
        RestaurantDetailViewModel vm = (RestaurantDetailViewModel) getViewModel();
        if (vm.getStoreDetail().getRestaurant().isExistsFavority_id()) {
            deleteFavorite();
            return;
        }
        ApiManager.getInstance().addFavorite(this, vm.getStoreDetail().getRestaurant(), new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Store store = new Gson().fromJson(result, Store.class);
                if (store.isExistsFavority_id()) {
                    vm.getStoreDetail().getRestaurant().setFavority_id(store.getFavority_id());
                }
                vm.isExistsFavoriteId.setValue(store.isExistsFavority_id());
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    public void deleteFavorite() {
        RestaurantDetailViewModel vm = (RestaurantDetailViewModel) getViewModel();
        ApiManager.getInstance().deleteFavorite(this, vm.getStoreDetail().getRestaurant(), new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Store store = new Gson().fromJson(result, Store.class);
                if (store.isExistsFavority_id()) {
                    vm.getStoreDetail().getRestaurant().setFavority_id(store.getFavority_id());
                } else {
                    vm.getStoreDetail().getRestaurant().setFavority_id(null);
                }
                vm.isExistsFavoriteId.setValue(store.isExistsFavority_id());
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    /**
     * 체크인
     */
    public void checkIn(View v) {
        goCheckIn();
    }

    /**
     * 리뷰쓰기
     */
    public void writeReview(View v) {
        goReview();
    }

    /**
     * 사진 올리기
     */
    public void uploadPicture(View v) {
        SelectPictureActivity.go(this, BananaConstants.PictureUploadMode.PREV_PICTURE, getStore());
    }

    /**
     * TODO:: 길찾기
     */
    public void findRounte(View v) {
    }

    /**
     * TODO:: 네비게이션
     */
    public void navigation(View v) {
    }

    /**
     * TODO:: 택시부르기
     */
    public void callTaxi(View v) {
    }

    /**
     * TODO:: 주소 복사
     */
    public void copyAddress(View v) {
    }

    /**
     * TODO:: 전화하기
     */
    public void call(View v) {
    }

    /**
     * TODO:: 메뉴 더보기
     */
    public void moreMenu(View v) {
    }

    /**
     * TODO:: 잘못된 정보 수정 요청 이동
     */
    public void requestModify(View v) {
    }

    /**
     * TODO:: 맛깔나는 리뷰 필더링 버튼
     */
    public void reviewFilter(View v) {
        Toast.makeText(this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * TODO:: 블로그 검색 기능
     */
    public void searchBlog(View v) {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            LatLng latLng = new LatLng(Double.valueOf(getStore().getLat()), Double.valueOf(getStore().getLon())); // whatever
            float zoom = 14f;// whatever
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
            googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(getStore().getStoreName()));
        } catch (Exception e) {

        }
    }

}
