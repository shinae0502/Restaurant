# [구현] Splash화면 개발하기 (사용자 개인정보 보내기)

첫화면에서 사용자의 정보를 서버로 보내서 접속정보를 기록해보도록하자.

단말기에서 가져올 정보를 정리해봤다. 권한없이 가쟈올 수 있는 정보중에 그냥
원하는 정보를 정해봤다.
```
uuid 
model
version
timezone
language
serial
``` 

안드로이드 부분 서버 요청 코드이다. UUID는 공통 프리퍼런스 저장부분에 구현해놨다.
저렇게 만드는게 맞는지는 정확히 모르겠다. 어플리케이션 최초 실행시 저렇게 만들라고 언뜻 본 것 같다.
일단 저렇게 사용하도록하자.
```java

public String getUUID() {
    if (sharedPreferences.getString("UUID", "").equals("")) {
        String uuid = UUID.randomUUID().toString();
        sharedPreferences.edit().putString("UUID", uuid).apply();
        return uuid;
    } else {
        return sharedPreferences.getString("UUID", "");
    }
}

private void sendConnectionLog() {
    Map<String, String> param = new HashMap<>();
    param.put("uuid", BananaPreference.getInstance(this).getUUID());
    param.put("model", Build.MODEL);
    param.put("version", Build.VERSION.RELEASE);
    param.put("timezone", TimeZone.getDefault().getDisplayName());
    param.put("language", Locale.getDefault().getDisplayLanguage());
    param.put("serial", Build.SERIAL);
    ApiManager.getInstance().connectLog(param);
}
```