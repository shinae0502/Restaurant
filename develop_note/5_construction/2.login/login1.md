# [구현][Android] 로그인 개발하기(SNS 로그인 개발하기)

이전에 기억하기로는 SNS를 통해 서버에 로그인을 하면 서버에서 토큰을 주어서
그다음부터는 로그인 없이 토큰을 계속 사용하는 방식을 사용했었다. 그러다면 SNS에서
로그아웃 했을경우에 현재 내 앱의 로그인 상태를 유지하는게 맞을까? 뭐 운영하는입장에선
그게 맞는것 같다. 기본적으로 각 SNS로그인 마다 공통 기능을 정리한번 해보도록 하자

## 1. 로그인 메니저 개발하기
로그인과 관련된 정보는 하나의 클래스를 통해서만 사용을 하고 싶다. 그래서
BananaLoginManager라는 클래스를 만들었다. 이 클래스에서 하는 제공해야 하는 기능에 대해서
생각해보자.

```
1. 카카오 로그인 요청
2. 페이스북 로그인 요청
3. 현재 로그인 중인 플렛폼
4. 현재 로그인 상태여
5. 사용자 정보 요청
6. 로그아웃
```

## 2. 로그인 공통기능 부분 개발하기
SNS로그인을 연동하면서 공통적으로 필요한 기능에 대해서 생각해보자.
```
1. 로그인 요청
2. 로그인 성공 시 사용자 정보요청
 - 이메일, 사용자 이름, 사용자 프로필 이미지
3. 현재 로그인 상태인지
4. 로그아웃
```

위에 정리한 기능을 바탕으로 추상클래스를 만들어봤다.

```java
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
```

## 2. 카카오톡 로그인 개발하기
https://developers.kakao.com/docs/android/user-management#%EB%A1%9C%EA%B7%B8%EC%9D%B8
기본 구현 설정이나 방법은 위의 내용을 천천히 살펴보며 예제소스를 기반으로 구현하면 될 것 같다.

#### 2.1 로그인 요청
로그인 요청 방법엔 두가지가 있는데 하나는 카카오톡에서 제공하는 UI버튼을 활용하는 방법이고,
두번째는 코드로 요청하는 방법이 있다. 요청후에는 해당 Activity의 OnActivityResult를 통해 로그인 결과를 받는다.
결과를 받은 값을 카카오 API로 전달을 하면, 로그인 성공여부는 ISessionCallback을 통해서 받을 수 있다.
onCreate부분에서 카카오API와 ISessionCallback을 초기화 한다.
```java
//버튼으로 요청
<com.kakao.usermgmt.LoginButton
    android:id="@+id/com_kakao_login"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="1"
    android:layout_marginBottom="30dp"
    android:layout_marginLeft="20dp"
    android:layout_marginRight="20dp"/>

//코드로 요청
Session.getCurrentSession().open(AuthType.KAKAO_TALK, this);
```

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
       return;
   }

   super.onActivityResult(requestCode, resultCode, data);
}
```

```java
public void onCreate() {
    if (KakaoSDK.getAdapter() == null)
        KakaoSDK.init(new KakaoSDKAdapter());
    
    Session.getCurrentSession().addCallback(sessionCallback);
    Session.getCurrentSession().checkAndImplicitOpen();
}

public class SessionCallback implements ISessionCallback {

    @Override
    public void onSessionOpened() {
        Logger.v("onSessionOpened");
        if (onResultLoginListener != null)
            onResultLoginListener.onResult(0, new User());
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Logger.e("onSessionOpenFailed");
        if (onResultLoginListener != null)
            onResultLoginListener.onResult(1, null);
    }
}
```
onSessionOpened은 로그인 성공 onSessionOpenFailed 로그인 실패로해서
로그인 기능은 정리 하면 될것 같다.

로그인 API 요청부분

서버에 AccessToken을 넘겨 로그인을 요청하면 서버는 해당 토큰으로 카카오에 사용자 정보를 요청한 뒤
해당 정보가 DB에 저장되어있다면 데이터를 최신으로 업데이트 하고 없다면 삽입(회원가입) 후 해당 유저정보를
리턴하는 방식을 사용하였다.
```java
String accessToken = Session.getCurrentSession().getTokenInfo().getAccessToken();
 ApiManager.getInstance().requestKakaoLogin(accessToken, new ApiManager.CallbackListener() {
     @Override
     public void callback(String result) {
         Type listType = new TypeToken<ArrayList<User>>() {
         }.getType();
         List<User> users = new Gson().fromJson(result, listType);
         onReceiveUserListener.onReceive(users.get(0));
     }

     @Override
     public void failed(String msg) {

     }
 });
```

#### 2.2 사용자 정보 요청
생각해보니 SNS에서 사용자 정보요청 부분은 필요없는것 같다. 로그인과 회원가입 용도로만 사용되며
서버로 AccessToken만 넘겨주면 로그인 및 회원가입부분은 처리할 수 있기떄문에 사용자 정보요청은
구현 할필요가 없는 것 같다.


#### 2.3 현재 로그인 상태
구글링해서 카카오에서 해당 기능에대한 설명이 있어서 간단하게 구현하였다.

```java
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
```

#### 2.4 로그아웃

로그아웃에 카카오 사이트에 있는 내용을 참고하여 개발하였다.
```java
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
         
## 3. 페이스북 로그인 개발하기
