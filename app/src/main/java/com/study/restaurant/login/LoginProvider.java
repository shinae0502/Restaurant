package com.study.restaurant.login;

import com.study.restaurant.model.User;

/**
 * 1. 로그인 요청
 * 2. 로그인 성공 시 사용자 정보요청
 * - 이메일, 사용자 이름, 사용자 프로필 이미지
 * 3. 현재 로그인 상태인지
 * 4. 로그아웃
 */
public abstract class LoginProvider {

    /**
     * 로그인 요청
     */
    public abstract void requestLogin(OnResultLoginListener onResultLoginListener);

    /**
     * 유저 정보 요청
     */
    public abstract void requestUser(OnReceiveUserListener onReceiveUserListener);

    /**
     * 현재 로그인 상태
     *
     * @return
     */
    public abstract boolean isLoggedIn();

    /**
     * 로그아웃 요청
     *
     * @param onResultLogoutListener
     */
    public abstract void logout(OnResultLogoutListener onResultLogoutListener);

    public interface OnResultLoginListener {
        void onResult(int result, User user);
    }

    public interface OnResultLogoutListener {
        void onResult(int result);
    }

    public interface OnReceiveUserListener {
        void onReceive(User user);
    }
}
