# 내 정보 개발하기

내 정보 화면

회원정보 테이블 구조
```
user_id
email
login_platform
picture
name
follower
following
review
checkin
upload_pictures
```

서버 회원정보 제공기능 구현하기
```php
public static function getUser()
{
    $dbConnection = new DBConnection();
    $connect = $dbConnection->getConnect();

    if (!$connect)
        return;

    $mysql_database = 'banana';

    $user_id = $_POST['user_id'];

    // query test
    $query = sprintf("use %s", $mysql_database);
    $connect->query($query);
    $q = "select * from User WHERE user_id = '$user_id'";
    $stmt = $connect->prepare($q);
    $result = $stmt->execute();
    $json = $stmt->fetchAll(PDO::FETCH_ASSOC);
    JsonUtil::printJson($json);
}
```

api 요청
```java
@FormUrlEncoded
@POST("getUser.php")
Call<ResponseBody> getUser(@FieldMap Map<String, String> params);


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
```

