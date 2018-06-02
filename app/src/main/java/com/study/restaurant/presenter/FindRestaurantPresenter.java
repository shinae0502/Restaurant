package com.study.restaurant.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.model.Store;
import com.study.restaurant.view.CommonView;
import com.study.restaurant.view.FindRestaurantView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FindRestaurantPresenter extends CommonPresenter implements FunctionImpl.FindRestaurant {

    Activity appCompatActivity;
    FindRestaurantView findRestaurantView;

    public FindRestaurantPresenter(Activity activity) {
        appCompatActivity = activity;
    }


    /**
     * 스토어 요약 정보 요청
     */
    @Override
    public void requestStoreSummery() {
        ApiManager.getInstance().getStoreSummary(new Store(), new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Log.d("exceptionTag", "" + result);
                Type listType = new TypeToken<ArrayList<Store>>() {
                }.getType();
                List<Store> storeList = new Gson().fromJson(result, listType);
                findRestaurantView.setStoreList(storeList);
            }

            @Override
            public void failed(String msg) {
            }
        });

    }

    /**
     * 1. 지역선택클릭 -> 지역선택 팝업 발생
     *
     * @param v
     */
    @Override
    public void clickSelectLocation(View v) {
        Toast.makeText(appCompatActivity, "clickSelectLocation", Toast.LENGTH_SHORT).show();
        showSelectLocationPopup();
    }

    /**
     *
     */
    @Override
    public void showSelectLocationPopup() {

    }

    /**
     * 2. 검색클릭 -> 검색화면 이동
     *
     * @param v
     */
    @Override
    public void clickSearch(View v) {
        Toast.makeText(appCompatActivity, "clickSearch", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(appCompatActivity, "clickMap", Toast.LENGTH_SHORT).show();
    }

    /**
     *
     */
    @Override
    public void goMap() {

    }


    @Override
    public void clickBanner(View v) {
        Toast.makeText(appCompatActivity, "clickBanner", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(appCompatActivity, "clickSort", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(appCompatActivity, "clickBoundary", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(appCompatActivity, "clickFilter", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(appCompatActivity, "clickEventBanner", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(appCompatActivity, "clickMap", Toast.LENGTH_SHORT).show();
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

    @Override
    public void registerView(CommonView commonView) {
        findRestaurantView = (FindRestaurantView) commonView;
    }
}
