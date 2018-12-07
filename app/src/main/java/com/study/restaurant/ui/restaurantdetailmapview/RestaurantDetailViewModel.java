package com.study.restaurant.ui.restaurantdetailmapview;

import android.Manifest;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.study.restaurant.ui.restaurantdetailview.RestaurantDetailActivity;
import com.study.restaurant.ui.storereview.StoreReviewActivity;
import com.study.restaurant.adapter.AroundRestaurantRvAdt;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.adapter.StoreImgRvAdt;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.adapter.TopListRvAdt;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreDetail;
import com.study.restaurant.navigation.BananaNavigation;

/**
 * {@link com.study.restaurant.R.layout#activity_restaurant_detail}
 * Activity {@link RestaurantDetailActivity}
 */
public class RestaurantDetailViewModel extends ViewModel {
    /**
     * 화면 이동 네비게이션
     */
    private BananaNavigation.RestaurantDetailNavigation restaurantDetailNavigation;

    private StoreImgRvAdt storeImageRvAdt = new StoreImgRvAdt();
    private ReviewRvAdt reviewRvAdt = null;
    private TopListRvAdt topListRvAdt = new TopListRvAdt();
    private StoryRvAdt storyRvAdt = new StoryRvAdt();
    private AroundRestaurantRvAdt aroundRestaurantRvAdt = new AroundRestaurantRvAdt();

    private ObservableField<StoreDetail> storeDetailObservableField = new ObservableField<>();
    public MutableLiveData<Boolean> isExistsFavoriteId = new MutableLiveData<>();

    public RestaurantDetailViewModel() {
        isExistsFavoriteId.setValue(false);
    }

    public ObservableField<StoreDetail> getStoreDetailObservableField() {
        return storeDetailObservableField;
    }

    public StoreImgRvAdt getStoreImageRvAdt() {
        return storeImageRvAdt;
    }

    public StoreDetail getStoreDetail() {
        return storeDetailObservableField.get();
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        storeDetailObservableField.set(storeDetail);
        storeDetailObservableField.notifyChange();
        isExistsFavoriteId.setValue(storeDetail.getRestaurant().isExistsFavority_id());
    }

    public ReviewRvAdt getReviewRvAdt() {
        if (reviewRvAdt == null) {
            reviewRvAdt = new ReviewRvAdt();
        }
        return reviewRvAdt;
    }

    public void setReviewRvAdt(ReviewRvAdt reviewRvAdt) {
        this.reviewRvAdt = reviewRvAdt;
    }

    public TopListRvAdt getTopListRvAdt() {
        return topListRvAdt;
    }

    public void setTopListRvAdt(TopListRvAdt topListRvAdt) {
        this.topListRvAdt = topListRvAdt;
    }

    public StoryRvAdt getStoryRvAdt() {
        return storyRvAdt;
    }

    public void setStoryRvAdt(StoryRvAdt storyRvAdt) {
        this.storyRvAdt = storyRvAdt;
    }

    public AroundRestaurantRvAdt getAroundRestaurantRvAdt() {
        return aroundRestaurantRvAdt;
    }

    public void setAroundRestaurantRvAdt(AroundRestaurantRvAdt aroundRestaurantRvAdt) {
        this.aroundRestaurantRvAdt = aroundRestaurantRvAdt;
    }

    public BananaNavigation.RestaurantDetailNavigation getRestaurantDetailNavigation() {
        return restaurantDetailNavigation;
    }

    public void setRestaurantDetailNavigation(BananaNavigation.RestaurantDetailNavigation restaurantDetailNavigation) {
        this.restaurantDetailNavigation = restaurantDetailNavigation;
    }

    public void clickCheckIn(View v) {
        restaurantDetailNavigation.goCheckIn();
    }

    public void clickMap(View v, Store store) {
        restaurantDetailNavigation.goMap(store);
    }

    public void call(View v, String tel) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + tel));
        if (ActivityCompat.checkSelfPermission(v.getContext(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            restaurantDetailNavigation.showCallPermissionPopup();
            return;
        }
        v.getContext().startActivity(phoneIntent);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0x01) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    public void clickReviewBad(View v) {
        StoreReviewActivity.go(v.getContext(), storeDetailObservableField.get());
    }

    public void clickReviewBest(View v) {
        StoreReviewActivity.go(v.getContext(), storeDetailObservableField.get());
    }

    public void clickReviewGood(View v) {
        StoreReviewActivity.go(v.getContext(), storeDetailObservableField.get());
    }

    public void moreReview(View v) {
        StoreReviewActivity.go(v.getContext(), storeDetailObservableField.get());
    }
}
