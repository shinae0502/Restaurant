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

    @FormUrlEncoded
    @POST("regStore.php")
    Call<ResponseBody> regStore(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("getStoryDetail.php")
    Call<ResponseBody> getStoryDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("getToplistDetail.php")
    Call<ResponseBody> getToplistDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("getNews.php")
    Call<ResponseBody> getNews(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("getStoreKeyword.php")
    Call<ResponseBody> getStoreKeyword(@FieldMap Map<String, String> params);

}
