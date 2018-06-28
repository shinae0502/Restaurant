package com.study.restaurant.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("getBanner.php")
    Call<ResponseBody> getBanner();

    @FormUrlEncoded
    @POST("getStoreSummary.php")
    Call<ResponseBody> getStoreSummary(@FieldMap Map<String, String> params);

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

    @GET("getCity.php")
    Call<ResponseBody> getCity();

}
