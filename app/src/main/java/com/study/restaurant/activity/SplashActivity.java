package com.study.restaurant.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.login.LoginProvider;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.CommonResponse;
import com.study.restaurant.model.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class SplashActivity extends AppCompatActivity {

    boolean isConnectCheck = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //접속 로그
        sendConnectionLog();
        BananaLoginManager.getInstance(this).onCreate();
        //로그인 시도하기
        BananaLoginManager.getInstance(this).requestLogin();
    }

    private void sendConnectionLog() {
        Map<String, String> param = new HashMap<>();
        param.put("uuid", BananaPreference.getInstance(this).getUUID());
        param.put("model", Build.MODEL);
        param.put("version", Build.VERSION.RELEASE);
        param.put("timezone", TimeZone.getDefault().getDisplayName());
        param.put("language", Locale.getDefault().getDisplayLanguage());
        param.put("serial", Build.SERIAL);
        ApiManager.getInstance().connectLog(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                CommonResponse commonResponse = new Gson().fromJson(result, CommonResponse.class);

                if (commonResponse == null)
                    return;

                if (commonResponse.getResult().equals("-1")) {
                    BananaLoginManager.getInstance(SplashActivity.this).logout(new LoginProvider.OnResultLogoutListener() {
                        @Override
                        public void onResult(int result) {
                            isConnectCheck = true;
                            BananaPreference.getInstance(SplashActivity.this).saveUser(new User());
                        }
                    });
                } else {
                    isConnectCheck = true;
                }
            }

            @Override
            public void failed(String msg) {

            }
        });
    }


    public void next(View v) {
        if (!isConnectCheck)
            return;

        if (BananaPreference.getInstance(this).loadUser() != null
                && BananaPreference.getInstance(this).loadUser().isLogin()) {
            MainActivity.go(this);
        } else {
            LoginActivity.go(this);
        }
        finish();
    }

    public static void go(final AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        appCompatActivity.startActivity(intent);
    }

}



