package com.study.restaurant.ui.findrestaurantview;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.model.Boundary;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.FindRestaurant;
import com.study.restaurant.model.Store;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.util.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.study.restaurant.BR;

import static com.study.restaurant.adapter.ProgressRvAdt.VIEW_TYPE_PROGRESS;
import static com.study.restaurant.adapter.StoreRvAdt.VIEW_TYPE_BANNER;
import static com.study.restaurant.adapter.StoreRvAdt.VIEW_TYPE_MENU;

/**
 * FindRestaurantFragment 에서 사용
 */
public class FindRestaurantViewModel extends BaseObservable {
    private final Context context;
    private BananaNavigation.FindRestaurantNavigation findRestaurantNavigation;
    private StoreRvAdt storeRvAdt = new StoreRvAdt();
    private boolean isLast;
    private FindRestaurant findRestaurant;
    private boolean isVisibleTopButton;

    //하단 터치 감지를위한 리싸이클러뷰 필요
    RecyclerView findRestaurantRv;

    public void setFindRestaurantRv(RecyclerView findRestaurantRv) {
        this.findRestaurantRv = findRestaurantRv;
    }

    @Bindable
    public boolean isVisibleTopButton() {
        return isVisibleTopButton;
    }

    public void setVisibleTopButton(boolean visibleTopButton) {
        isVisibleTopButton = visibleTopButton;
        notifyPropertyChanged(BR.visibleTopButton);
    }

    public Object getRvItem(int position) {
        if (position > 1) {
            return findRestaurant.getStoreArrayList().get(position - 2);
        } else {
            return null;
        }
    }

    public int getRvCount() {
        return 2 + findRestaurant.getStoreArrayList().size();
    }

    public FindRestaurantViewModel(Context context, BananaNavigation.FindRestaurantNavigation findRestaurantNavigation) {
        this.context = context;
        this.findRestaurantNavigation = findRestaurantNavigation;
    }

    public FindRestaurant getFindRestaurant() {
        return findRestaurant;
    }

    public void clickSelectLocation(View v) {
        findRestaurantNavigation.showSelectRegionPopup();
    }

    public void addStoreArrayList(ArrayList<Store> storeArrayList) {
        findRestaurant.addAllStoreArrayList(storeArrayList);
        storeRvAdt.notifyDataSetChanged();
        handler.sendEmptyMessageDelayed(0, 100);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!findRestaurantRv.canScrollVertically(1)) {
                isLast = true;
            }
        }
    };

    public StoreRvAdt getStoreRvAdt() {
        return storeRvAdt;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && !isLast) {
                    isLast = true;
                    Logger.d("Last!!");
                    requestStoreSummary();
                } else {
                    Logger.d("not Last");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    // Scroll Down
                    if (!isVisibleTopButton()) {
                        setVisibleTopButton(true);
                    }
                } else if (dy < 0) {
                    // Scroll Up
                    if (isVisibleTopButton()) {
                        setVisibleTopButton(false);
                    }
                }
            }

        };
    }

    public void setFindRestaurant(FindRestaurant findRestaurant) {
        this.findRestaurant = findRestaurant;
    }

    public void requestStoreSummary() {

        HashMap<String, String> param = new HashMap<>();
        param.put("region_id", findRestaurant.getCities().getSelectedRegionIds());
        param.put("boundary", "");
        param.put("filter", "");
        param.put("sort", findRestaurant.getSort().getAttribute());
        param.put("total_count", "" + findRestaurant.getStoreArrayList().size());
        param.put("user_id", BananaPreference.getInstance(context).loadUser().isLogin() ?
                BananaPreference.getInstance(context).loadUser().user_id : "-1");
        ApiManager.getInstance().getStoreSummary(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Logger.d(result);
                isLast = false;
                Type listType = new TypeToken<ArrayList<Store>>() {
                }.getType();
                List<Store> storeList = new Gson().fromJson(result, listType);
                /** 내 위치 넣어주기 */
                addStoreArrayList((ArrayList<Store>) storeList);
            }

            @Override
            public void failed(String msg) {
                findRestaurantNavigation.showErrorPopup(msg);
            }
        });

    }

    public void clickSearch(View v) {
        findRestaurantNavigation.goSearch();
    }

    public void clickSort(View v) {
        findRestaurantNavigation.showSortPopup();
    }

    public void clickBoundary(View v) {
        //내 주변을 클릭 시 기존 선택된 도시가 모두 해제된다.
        Boundary boundary = findRestaurant.getBoundary();
        Cities cities = findRestaurant.getCities();
        if (boundary.getBoundary().equals("내 주변")) {
            cities.releaseAllSelected();
            boundary.setLevel3(true);
            removeAllStore();
            requestStoreSummary();
        } else {
            findRestaurantNavigation.showBoundaryPopup();
        }
    }

    public void clickFilter(View v) {
        findRestaurantNavigation.showFilterPopup();
    }


    public void removeAllStore() {
        findRestaurant.getStoreArrayList().removeAll(findRestaurant.getStoreArrayList());
        storeRvAdt.notifyDataSetChanged();
    }

    public void clickRestaurant(View v) {
        findRestaurantNavigation.goDeatilRestaurant((Store) v.getTag());
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (storeRvAdt.getItemViewType(position) == VIEW_TYPE_PROGRESS)
                    return 2;

                if (VIEW_TYPE_BANNER == storeRvAdt.getItemViewType(position)) {
                    return 2;
                }

                if (storeRvAdt.getItemViewType(position) == VIEW_TYPE_MENU) {
                    return 2;
                }

                return 1;
            }
        };
    }

    public void clickToTop(View v) {
        findRestaurantNavigation.rvToTop();
        setVisibleTopButton(false);
    }

    public void setMyLocation(Location myLocation) {
        findRestaurant.setMyLocation(myLocation);
    }

    public void clickFavorite(View v, Store store) {
        if (!BananaPreference.getInstance(v.getContext()).loadUser().isLogin()) {
            Toast.makeText(v.getContext(), "로그인 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (store.isExistsFavority_id()) {
            ApiManager.getInstance().deleteFavorite(v.getContext(), store);
        } else {
            ApiManager.getInstance().addFavorite(v.getContext(), store);
        }
    }
}
