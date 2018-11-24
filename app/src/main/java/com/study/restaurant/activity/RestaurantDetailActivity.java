package com.study.restaurant.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaBaseActivity;
import com.study.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.study.restaurant.model.Image;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreDetail;
import com.study.restaurant.model.StoreSpec;
import com.study.restaurant.test.Dummy;
import com.study.restaurant.util.AppBarStateChangeListener;
import com.study.restaurant.util.Logger;
import com.study.restaurant.util.MyGlide;
import com.study.restaurant.viewmodel.RestaurantDetailViewModel;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * 서버에서 받은 정보를 가지고 표시해야하는 기능들
 * 맛집 정보 제공 (xml에 구현){@link R.layout.activity_restaurant_detail }
 * 주소표시 {@link R.layout.activity_restaurant_detail }
 * 이미지 리스트 {@link #initUI()}
 * 위치 지도로 표시하기 {@link #onMapReady(GoogleMap)}
 * 편의정보 표시하기 {@link R.layout.layout_restaurant_information}
 * 메뉴 표시하기 {@link R.layout.layout_restaurant_information}
 * 식당 키워드 {@link R.layout.activity_restaurant_detail }
 * TODO:: 베너
 * TODO:: 리뷰리스트
 * TODO:: 관련 TOP리스트 표시
 * TODO:: 주변 인기 식당 리스트 표시
 */
public class RestaurantDetailActivity extends BananaBaseActivity
        implements RestaurantDetailViewModel.RestaurantDetailNavigation,
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
        vb.layoutDetailRestaurantMenu.setVm(vm);
        vb.layoutDetailRestaurantMain.setVm(vm);
        vb.layoutReviewList.setVm(vm);
        vb.layoutRelatedToplist.setVm(vm);
        vb.layoutRelatedStory.setVm(vm);
        vb.layoutAroundRestaurant.setVm(vm);

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

        vb.layoutDetailRestaurantMain.storeImgRv.setAdapter(new StoreImgRvAdt());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RestaurantDetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        vb.layoutDetailRestaurantMain.storeImgRv.setLayoutManager(linearLayoutManager);

        /** 이미지 리스트 */
        vm.getObservableField().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                StoreDetail storeDetail = ((ObservableField<StoreDetail>) sender).get();
                ((StoreImgRvAdt) vb.layoutDetailRestaurantMain.storeImgRv.getAdapter()).setImageArrayList(storeDetail.getImage());

                vm.getTopListRvAdt().setTopLists(storeDetail.getToplist());

            }
        });
        vb.layoutDetailRestaurantMain.storeImgRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = (int) view.getTag();
                if (position < vb.layoutDetailRestaurantMain.storeImgRv.getAdapter().getItemCount() - 1) {
                    outRect.right = 10;
                }
            }
        });

    }

    @Override
    public void initData() {
        ActivityRestaurantDetailBinding vb = (ActivityRestaurantDetailBinding) getViewDataBinding();
        RestaurantDetailViewModel vm = (RestaurantDetailViewModel) getViewModel();
        //더미코드
        StoreSpec storeSpec = Dummy.getInstance().getRestaurantDetail();

        vm.setStoreSpec(storeSpec);
        // data binding


        vb.setStore(getStore());
        vb.layoutDetailRestaurantTitleBar.setStore(getStore());
        vb.layoutDetailRestaurantMain.setStore(getStore());

        vb.setStoreSpec(storeSpec);
        vb.layoutDetailRestaurantTitleBar.setStoreSpec(storeSpec);
        vb.layoutRestaurantInformation.setStoreSpec(storeSpec);

        ApiManager.getInstance().getStoreDetail(getIntent().getParcelableExtra("store"), new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Logger.d(result);
                vm.setStoreDetail(new Gson().fromJson(result, StoreDetail.class));
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
    public void goDetailPhoto() {
        PhotoDetailActivity.go(this);
    }

    @Override
    public void goCheckIn() {
        CheckInActivity.go(this);
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
    }

    /**
     * TODO:: 체크인
     */
    public void checkIn(View v) {
    }

    /**
     * TODO:: 리뷰쓰기
     */
    public void writeReview(View v) {
    }

    /**
     * TODO:: 사진 올리기
     */
    public void uploadPicture(View v) {
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
     * TODO:: 편의정보 더보기
     */
    public void moreConvenience(View v) {
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
     * TODO:: 리뷰 더보기
     */
    public void moreReview(View v) {
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
                    .title(getStore().getName()));
        } catch (Exception e) {

        }
    }


    /**********************************************************************************************
     * 스토어 이미지 리스트
     **********************************************************************************************/

    private class StoreImgRvAdt extends RecyclerView.Adapter {
        ArrayList<Image> imageArrayList;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StoreImageRvHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, null, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            holder.itemView.setTag(position);

            MyGlide.with(holder.itemView.getContext())
                    .load("http://sarang628.iptime.org:83/image_upload/" + imageArrayList.get(position).getUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(((StoreImageRvHolder) holder).img);

        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (imageArrayList != null)
                count = imageArrayList.size();
            return count;
        }

        public void setImageArrayList(ArrayList<Image> imageArrayList) {
            this.imageArrayList = imageArrayList;
            notifyDataSetChanged();
        }
    }

    public class StoreImageRvHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public StoreImageRvHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.storeDetailImg);
        }
    }
}
