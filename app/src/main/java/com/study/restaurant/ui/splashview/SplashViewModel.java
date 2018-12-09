package com.study.restaurant.ui.splashview;

import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.CommonResponse;
import com.study.restaurant.model.User;
import com.study.restaurant.navigation.BananaNavigation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class SplashViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    BananaNavigation.SplashNavigation splashNavigation;
    boolean isConnectCheck = false;

    public void next(View v) {
        if (!isConnectCheck)
            return;

        if (BananaPreference.getInstance(v.getContext()).loadUser() != null
                && BananaPreference.getInstance(v.getContext()).loadUser().isLogin()) {
            splashNavigation.goMain();
        } else {
            splashNavigation.goLogin();
        }
        splashNavigation.finish();
    }

    public void setSplashNavigation(BananaNavigation.SplashNavigation splashNavigation) {
        this.splashNavigation = splashNavigation;
    }

    public void sendConnectionLog(AppCompatActivity appCompatActivity) {
        Map<String, String> param = new HashMap<>();
        param.put("uuid", BananaPreference.getInstance(appCompatActivity).getUUID());
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
                    BananaLoginManager.getInstance(appCompatActivity).logout(result1 -> {
                        isConnectCheck = true;
                        BananaPreference.getInstance(appCompatActivity).saveUser(new User());
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
}
