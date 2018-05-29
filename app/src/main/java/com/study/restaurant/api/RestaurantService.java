package com.study.restaurant.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantService {
    @GET("getBanner.php")
    Call<ResponseBody> getBanner();

    @GET("getStoreSummary.php")
    Call<ResponseBody> getStoreSummary();

    @GET("getTopList.php")
    Call<ResponseBody> getTopList();

    @GET("getStory.php")
    Call<ResponseBody> getStory();
}
