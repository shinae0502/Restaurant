# [구현] Splash화면 개발하기 (토큰 체크 기능 개발 하기)

최초 Splash화면에서 사용자의 단말기 정보를 보내는 부분이 있었는데

이부분에 사용자의 토큰정보를 추가로 전달해서 현재 사용자가 로그인된 상태인지 확인하는

부분을 추가하기로 했다.

토큰 테이블을 만들어보자. 처음 만들어 보는거라서 그냥 간단하게 만들었다.
```
token
user_id
date
```

토큰을 보통 해더 부분에 넣는걸로 알고 있어서 헤더 부분에 토큰넣는 코드를 추가해보자
엑세스 토큰을 레트로 핏으로 헤더에 넣는 코드
```java
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


    //TODO:: 레트로핏 초기화 BASE URL 설정하는 곳
    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(URL)
            .build();

    //TODO:: 통신인터페이스 기반 서비스 생성
    RestaurantService service = retrofit.create(RestaurantService.class);
```

API토큰 체크 기능은 API쪽에서 정리하자.

일단 현재 로그인 기능이 완성이 안되었기때문에 스플레시 화면에서 무조건 로그인 화면으로 가야 할 것 같다.
토큰이 만료되었을때 SNS로그아웃을 시키며 유저 정보를 삭제 하는 코드를 만들자.
```java
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
```
일단 여기까지 개발 하고 로그인 화면으로 넘어가도록 하자.