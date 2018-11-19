# [구현] Splash화면 개발하기 (SNS 로그인 개발하기)



#### 3. 현재 로그인 상태인지

일단 따로 정보가 없어서 아래와 같이 엑세스토큰을 요청해서 널인지 넣어봤다.
테스트해보고 결정하도록하자.

```java
@Override
public boolean isLoggedIn() {
    return Session.getCurrentSession().getTokenInfo().getAccessToken() != null;
}
```

#### 4. 로그아웃
로그아웃 요청코드
```java
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
```

이렇게 구현하고 전체 기능을 테스테 해보도록 하자.