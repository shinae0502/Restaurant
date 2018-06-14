package com.study.restaurant.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantService {
    @GET("getBanner.php")
    Call<ResponseBody> getBanner();

    @GET("getStoreSummary.php")
    Call<ResponseBody> getStoreSummary();

    @GET("getTopList.php")
    Call<ResponseBody> getTopList();

    @GET("getStory.php")
    Call<ResponseBody> getStory();

    @GET("kakao_login.php/?accessToken={accessToken}")
    Call<ResponseBody> requestKakaoLogin(@Path("accessToken")String accessToken);
}
