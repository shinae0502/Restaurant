package com.study.restaurant.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("getBanner.php")
    Call<ResponseBody> getBanner();

    @GET("getStoreSummary.php")
    Call<ResponseBody> getStoreSummary();

    @GET("getTopList.php")
    Call<ResponseBody> getTopList();

    @GET("getStory.php")
    Call<ResponseBody> getStory();

    @GET("kakao_login.php")
    Call<ResponseBody> requestKakaoLogin(@Query("accessToken") String accessToken);

    @GET("facebook_login.php")
    Call<ResponseBody> requestFacebookLogin(@Query("accessToken") String accessToken);

    @GET("getRegion.php")
    Call<ResponseBody> getRegion(@Query("zipCode") String zipCode);

}
