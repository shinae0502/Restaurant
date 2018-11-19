package com.study.restaurant.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthService;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.User;
import com.study.restaurant.util.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class KakaoLoginProvider extends LoginProvider {

    private static KakaoLoginProvider kakaoLoginProvider;
    Activity activity;
    public SessionCallback sessionCallback;
    private OnResultLoginListener onResultLoginListener;


    @Override
    public void requestLogin(OnResultLoginListener onResultLoginListener) {
        this.onResultLoginListener = onResultLoginListener;
        Session.getCurrentSession().open(AuthType.KAKAO_TALK, activity);
    }

    @Override
    public void requestUser(OnReceiveUserListener onReceiveUserListener) {
    }


    /**
     * Session.getCurrentSession().isOpen() 여부로 로그인이 되었는지 확인할 수 있습니다. 물론 100%확실한 상태는 아닙니다.
     * 왜냐하면 open의 상태는 false이지만 refreshToken으로 다시 자동 세션 연결이 될 수도 있기 때문입니다.
     * <p>
     * 때문에 오픈여부를 알기보다는 세션이 닫혀있는 상황인지를 판별하는것이 좋을듯 합니다.
     * Session.getCurrentSession().isClosed()로 확인하시길 바랍니다.
     *
     * @return
     */
    @Override
    public boolean isLoggedIn() {
        return !Session.getCurrentSession().isClosed();
    }

    @Override
    public void logout(OnResultLogoutListener onResultLogoutListener) {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                if (onResultLogoutListener != null)
                    onResultLogoutListener.onResult(0);
            }
        });
    }

    public class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Logger.v("onSessionOpened");
            if (onResultLoginListener != null) {
                String accessToken = Session.getCurrentSession().getTokenInfo().getAccessToken();
                ApiManager.getInstance().requestKakaoLogin(accessToken, new ApiManager.CallbackListener() {
                    @Override
                    public void callback(String result) {
                        Logger.v(result);
                        Type listType = new TypeToken<ArrayList<User>>() {
                        }.getType();
                        List<User> users = new Gson().fromJson(result, listType);
                        onResultLoginListener.onResult(0, users.get(0));
                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
            }
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Logger.e("onSessionOpenFailed");
            if (onResultLoginListener != null)
                onResultLoginListener.onResult(1, null);
        }
    }


    public void onDestroy() {
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    public static KakaoLoginProvider getInstance(Activity activity) {
        if (kakaoLoginProvider == null)
            kakaoLoginProvider = new KakaoLoginProvider(activity);

        if (activity != null && !kakaoLoginProvider.activity.equals(activity))
            kakaoLoginProvider.activity = activity;

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
            return () -> KakaoLoginProvider.this.activity.getApplicationContext();
        }
    }

    public void onCreate() {
        if (KakaoSDK.getAdapter() == null)
            KakaoSDK.init(new KakaoSDKAdapter());

        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    public KakaoLoginProvider(Activity activity) {
        this.activity = activity;
        sessionCallback = new SessionCallback();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

}
