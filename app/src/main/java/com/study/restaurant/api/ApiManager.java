package com.study.restaurant.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.model.Banner;
import com.study.restaurant.model.CommonResponse;
import com.study.restaurant.model.Store;
import com.study.restaurant.test.Dummy;
import com.study.restaurant.util.Logger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiManager {

    private static ApiManager apiManager;
    private static GlobalApplication application;

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public void setApplication(GlobalApplication application) {
        this.application = application;
    }

    public static String URL = "http://sarang628.iptime.org:83/php/";

    public RestaurantService getService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("User-Agent", "android")
                    .header("accessToken", BananaPreference.getInstance(application).getAccessToken())
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        OkHttpClient client = httpClient.build();


        //레트로핏 초기화 BASE URL 설정하는 곳
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(URL)
                .build();

        //통신인터페이스 기반 서비스 생성
        RestaurantService service = retrofit.create(RestaurantService.class);
        return service;
    }

    public void connectLog(Map<String, String> param, final CallbackListener callbackListener) {
        getService().connectLog(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                if (response.code() == 200) {
                    try {
                        body = response.body().string();
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 500) {
                    try {
                        body = response.body().string();
                        callbackListener.failed(body);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getBanner(Banner banner, final CallbackListener callbackListener) {

        getService().getBanner().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    callbackListener.callback(body);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getStoreSummary(Map<String, String> param, final CallbackListener callbackListener) {

        //TODO:: 맛집 찾기 더미 요청 중
        boolean isDummy = true;
        //더미요청
        if (isDummy) {
            String result = Dummy.getInstance().getStoreList();
            try {
                CommonResponse commonResponse = new Gson().fromJson(result, CommonResponse.class);
                commonResponse.getResult().equals("-1");
                {
                    callbackListener.failed(commonResponse.getErrCode());
                    return;
                }
            } catch (JsonSyntaxException e) {

            }

            callbackListener.callback(result);
            return;
        }


        getService().getStoreSummary(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                    CommonResponse commonResponse = new Gson().fromJson(body, CommonResponse.class);
                    if (commonResponse != null) {
                        commonResponse.getResult().equals("-1");
                        {
                            callbackListener.failed(commonResponse.getErrCode());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getStory(final CallbackListener callbackListener) {

        getService().getStory().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    callbackListener.callback(body);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getTopList(final CallbackListener callbackListener) {

        getService().getTopList().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    callbackListener.callback(body);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public interface CallbackListener {
        void callback(String result);

        void failed(String msg);
    }

    public void requestKakaoLogin(String accessToken, final CallbackListener callbackListener) {
        Logger.i(accessToken);
        getService().requestKakaoLogin(accessToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    Logger.d(e.toString());
                }

                if (callbackListener != null)
                    callbackListener.callback(body);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void requestFacebookLogin(String accessToken, final CallbackListener callbackListener) {
        Logger.v(accessToken);
        getService().requestFacebookLogin(accessToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    Log.d("sarang", e.toString());
                }

                if (callbackListener != null)
                    callbackListener.callback(body);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getRegion(String zipCode, final CallbackListener callbackListener) {

        getService().getRegion(zipCode).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    Log.d("sarang", e.toString());
                }

                if (callbackListener != null)
                    callbackListener.callback(body);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getCity(final CallbackListener callbackListener) {

        getService().getCity().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    Log.d("sarang", e.toString());
                }

                if (callbackListener != null)
                    callbackListener.callback(body);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void regStore(Map<String, String> param, final CallbackListener callbackListener) {
        getService().regStore(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getStoryDetail(Map<String, String> param, final CallbackListener callbackListener) {
        getService().getStoryDetail(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getToplistDetail(Map<String, String> param, final CallbackListener callbackListener) {
        getService().getToplistDetail(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getNews(Map<String, String> param, final CallbackListener callbackListener) {
        getService().getNews(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getStoreKeyword(Map<String, String> param, final CallbackListener callbackListener) {
        getService().getStoreKeyword(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void registerNews(Map<String, RequestBody> param,
                             MultipartBody.Part pic1,
                             MultipartBody.Part pic2,
                             MultipartBody.Part pic3,
                             MultipartBody.Part pic4,
                             MultipartBody.Part pic5,
                             MultipartBody.Part pic6,
                             MultipartBody.Part pic7,
                             MultipartBody.Part pic8,
                             MultipartBody.Part pic9,
                             MultipartBody.Part pic0,
                             final CallbackListener callbackListener) {
        getService().registerNews(param,
                pic1,
                pic2,
                pic3,
                pic4,
                pic5,
                pic6,
                pic7,
                pic8,
                pic9,
                pic0).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                    Logger.v(body);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getUser(Map<String, String> param, final CallbackListener callbackListener) {
        getService().getUser(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        Logger.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void addFavorite(Context context, Store store, final CallbackListener callbackListener) {
        boolean isDummy = true;
        if (isDummy == true) {
            store.setFavority_id("100");
            if (callbackListener != null)
                callbackListener.callback(Dummy.getInstance().getAddFavorite());
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("user_id", BananaPreference.getInstance(context).loadUser().user_id);
        param.put("store_id", store.getStore_id());

        getService().addFavorite(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)

                String body = "";
                try {
                    if (response.code() == 200) {
                        body = response.body().string();
                        store.setFavority_id("100");
                        if (callbackListener != null)
                            callbackListener.callback(body);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void addFavorite(Context context, Store store) {
        addFavorite(context, store, null);
    }

    public void deleteFavorite(Context context, Store store, final CallbackListener callbackListener) {
        boolean isDummy = true;
        if (isDummy) {
            store.setFavority_id(null);
            if (callbackListener != null)
                callbackListener.callback(Dummy.getInstance().getDeleteFavorite());
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("user_id", BananaPreference.getInstance(context).loadUser().user_id);
        param.put("store_id", store.getStore_id());
        getService().deleteFavorite(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                    store.setFavority_id(null);
                    if (callbackListener != null) {
                        callbackListener.callback(body);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void deleteFavorite(Context context, Store store) {
        deleteFavorite(context, store, null);
    }

    public void getStoreDetail(Store store, final CallbackListener callbackListener) {
        boolean isDummy = true;
        //더미요청
        if (isDummy) {
            String result = Dummy.getInstance().getRestaurantDetail();
            try {
                CommonResponse commonResponse = new Gson().fromJson(result, CommonResponse.class);
                commonResponse.getResult().equals("-1");
                {
                    callbackListener.failed(commonResponse.getErrCode());
                    return;
                }
            } catch (Exception e) {

            }

            callbackListener.callback(result);
            return;
        }

        Map<String, String> param = new HashMap<>();
        param.put("store_id", store.getStore_id());
        param.put("region_id", store.getRegion_id());
        getService().getStoreDetail(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                    if (callbackListener != null)
                        callbackListener.callback(body);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
