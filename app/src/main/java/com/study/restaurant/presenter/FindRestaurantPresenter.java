package com.study.restaurant.presenter;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.manager.MyLocationManager;
import com.study.restaurant.model.Region;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.FindRestaurantView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FindRestaurantPresenter implements FunctionImpl.FindRestaurant {
    MyLocationManager myLocationManager;
    OnSuccessListener<? super Location> tempListener;
    FindRestaurantView findRestaurantView;

    public FindRestaurantPresenter(FindRestaurantView findRestaurantView) {
        this.findRestaurantView = findRestaurantView;
    }

    public void initLocationManager(Activity activity) {
        myLocationManager = new MyLocationManager(activity);
    }

    public void requestLocation(OnSuccessListener<? super Location> listener) {
        //GPS 사용 전 권한 체크
        if (myLocationManager.isGrantedPermission()) {
            //권한이 허용되어있다면 위치요청
            LOG.d("위치요청하기");
            myLocationManager.getLastLocation(listener);
        } else {
            //권한이 없다면 권한 요청하기
            tempListener = listener;
            LOG.d("권한요청하기");
            myLocationManager.requestLocationPermissionPopup(0x02);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0x02) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //위치 다시 요청하기
                if (tempListener != null) {
                    requestLocation(tempListener);
                    tempListener = null;
                }
            } else {
                //권한 거부시
                LOG.d("권한 거부");
                tempListener = null;
            }
        }
    }

    public boolean requestAddress(double latitude, double longitude) {
        String zipCode = myLocationManager.getZipcode(latitude, longitude);
        if (zipCode.equals("")) {
            return false;
        }
        LOG.d(zipCode);
        ApiManager.getInstance().getRegion(zipCode, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type listType = new TypeToken<ArrayList<Region>>() {
                }.getType();
                List<Region> regionList = new Gson().fromJson(result, listType);
                if (regionList != null && regionList.size() > 0)
                    findRestaurantView.setRegion(regionList.get(0));
            }

            @Override
            public void failed(String msg) {

            }
        });
        return true;
    }

    /**
     * 스토어 요약 정보 요청
     */
    @Override
    public void requestStoreSummery() {

    }

    /**
     * 1. 지역선택클릭 -> 지역선택 팝업 발생
     *
     * @param v
     */
    @Override
    public void clickSelectLocation(View v) {
        findRestaurantView.showSelectRegionPopup();
    }


    /**
     * 2. 검색클릭 -> 검색화면 이동
     *
     * @param v
     */
    @Override
    public void clickSearch(View v) {

    }

    /**
     *
     */
    @Override
    public void goSearch() {

    }

    /**
     * 3. 지도클릭 -> 지도화면 이동
     *
     * @param v
     */
    @Override
    public void clickMap(View v) {

    }

    /**
     *
     */
    @Override
    public void goMap() {

    }

    /**
     * 4. 베너클릭 -> 해당 베너 화면이동
     *
     * @param v
     */
    @Override
    public void clickBanner(View v) {

    }

    /**
     *
     */
    @Override
    public void goBanner() {

    }

    /**
     * 5. 정렬버튼클릭 -> 정렬 선택 팝업 발생
     *
     * @param v
     */
    @Override
    public void clickSort(View v) {

    }

    /**
     *
     */
    @Override
    public void showSelectSortItemPopup() {

    }

    /**
     * 6. 검색반경버튼 클릭 -> 검색반경 팝업 발생
     *
     * @param v
     */
    @Override
    public void clickBoundary(View v) {

    }

    /**
     *
     */
    @Override
    public void showSelectBoundaryPopup() {

    }

    /**
     * 7. 필터 클릭 -> 필터 팝업 발생
     *
     * @param v
     */
    @Override
    public void clickFilter(View v) {

    }

    /**
     *
     */
    @Override
    public void showSelectFilterPopup() {

    }

    /**
     * 8. 이벤트 베너 클릭 -> 이벤트 페이지 이동
     *
     * @param v
     */
    @Override
    public void clickEventBanner(View v) {

    }

    /**
     *
     */
    @Override
    public void goEvent() {

    }

    /**
     * 9. 리스트 아이템 클릭 -> 맛집 상세페이지 이동
     *
     * @param v
     */
    @Override
    public void clickListItem(View v) {

    }

    /**
     *
     */
    @Override
    public void goRestaurant() {

    }

    /**
     * 1. 아래로 스크롤 시 최상단 이동버튼이 나온다
     * 2. 위로 스크롤 시 최상단 이동버튼이 사라진다.
     *
     * @param show
     */
    @Override
    public void setShowTopButton(boolean show) {

    }
}
