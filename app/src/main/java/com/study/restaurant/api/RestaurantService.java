package com.study.restaurant.api;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {


    @FormUrlEncoded
    @POST("ConnectLog.php")
    Call<ResponseBody> connectLog(@FieldMap Map<String, String> params);

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

    @Multipart
    @POST("registerNews.php")
    Call<ResponseBody> registerNews(@PartMap() Map<String, RequestBody> params,
                                    @Part MultipartBody.Part pic1,
                                    @Part MultipartBody.Part pic2,
                                    @Part MultipartBody.Part pic3,
                                    @Part MultipartBody.Part pic4,
                                    @Part MultipartBody.Part pic5,
                                    @Part MultipartBody.Part pic6,
                                    @Part MultipartBody.Part pic7,
                                    @Part MultipartBody.Part pic8,
                                    @Part MultipartBody.Part pic9,
                                    @Part MultipartBody.Part pic0);

    @FormUrlEncoded
    @POST("getUser.php")
    Call<ResponseBody> getUser(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("addFavorite.php")
    Call<ResponseBody> addFavorite(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("deleteFavorite.php")
    Call<ResponseBody> deleteFavorite(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("getStoreDetail.php")
    Call<ResponseBody> getStoreDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("checkIn.php")
    Call<ResponseBody> checkIn(@FieldMap Map<String, String> params);

    @Multipart
    @POST("registerNews.php")
    Call<ResponseBody> uploadPicture(@PartMap() Map<String, RequestBody> params,
                                     @Part MultipartBody.Part pic1);

}
