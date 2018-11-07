package com.study.restaurant.api;

import android.content.Context;
import android.util.Log;

import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.model.Banner;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.LOG;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiManager {

    private static ApiManager apiManager;

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public static String URL = "http://sarang628.iptime.org:83/php/";

    public RestaurantService getService() {
        //TODO:: 레트로핏 초기화 BASE URL 설정하는 곳
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();

        //TODO:: 통신인터페이스 기반 서비스 생성
        RestaurantService service = retrofit.create(RestaurantService.class);

        return service;
    }

    public void getBanner(Banner banner, final CallbackListener callbackListener) {

        getService().getBanner().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (IOException e) {
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
        getService().getStoreSummary(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
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
                } catch (IOException e) {
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
                } catch (IOException e) {
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

        getService().requestKakaoLogin(accessToken).enqueue(new Callback<ResponseBody>() {
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

    public void requestFacebookLogin(String accessToken, final CallbackListener callbackListener) {

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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void registerNews(Map<String, RequestBody> param, final CallbackListener callbackListener) {
        getService().registerNews(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (callbackListener != null)
                    try {
                        callbackListener.callback(body);
                    } catch (Exception e) {
                        LOG.d(e.toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void addFavorite(Context context, Store store) {
        Map<String, String> param = new HashMap<>();
        param.put("user_id", BananaPreference.getInstance(context).loadUser().user_id);
        param.put("store_id", store.getStore_id());
        getService().addFavorite(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
                String body = "";
                try {
                    body = response.body().string();
                    store.setFavority_id("100");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void deleteFavorite(Context context, Store store) {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
