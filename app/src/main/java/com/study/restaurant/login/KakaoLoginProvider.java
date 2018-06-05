package com.study.restaurant.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class KakaoLoginProvider {

    private static KakaoLoginProvider kakaoLoginProvider;
    Activity activity;
    private SessionCallback callback;

    public KakaoLoginProvider(Activity activity) {
        this.activity = activity;
    }

    public static class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Log.d("sarang", "onSessionOpened");
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("sarang", "onSessionOpenFailed");
            if (exception != null) {
                Logger.e(exception);
                Log.d("sarang", exception.toString());
            }
        }
    }

    protected void redirectSignupActivity() {
        /*final Intent intent = new Intent(this, SampleSignupActivity.class);
        startActivity(intent);
        finish();*/
    }

    public void onDestroy() {
        Session.getCurrentSession().removeCallback(callback);
    }

    public static KakaoLoginProvider getInstance(Activity activity) {
        if (kakaoLoginProvider == null)
            kakaoLoginProvider = new KakaoLoginProvider(activity);
        return kakaoLoginProvider;
    }


    private class KakaoSDKAdapter extends KakaoAdapter {

        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[]{AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return KakaoLoginProvider.this.activity.getApplicationContext();
                }
            };
        }
    }

    public void onCreate() {
        Log.d("sarang","KakaoSDK manager.init");
//        KakaoSDK.init(new KakaoSDKAdapter());
    }

}
