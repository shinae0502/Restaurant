# 즐겨찾기

즐겨찾기 테이블 구조
```
favority_id
user_id
store_id
```

즐겨찾기 추가 제거 API 함수
```php
public static function addFavorite()
{
    $dbConnection = new DBConnection();
    $connect = $dbConnection->getConnect();

    if (!$connect)
        return;

    $mysql_database = 'banana';

    //파라미터 체크
    if (!isset($_POST['store_id']) || !isset($_POST['user_id'])) {
        JsonUtil::printError("1", "파라미터 없음");
        return;
    }
    $store_id = $_POST['store_id'];
    $user_id = $_POST['user_id'];

    // query test
    $query = sprintf("use %s", $mysql_database);
    $connect->query($query);
    $q = "INSERT INTO Favority(user_id, store_id) values ($user_id, $store_id)";
    $stmt = $connect->prepare($q);
    $result = $stmt->execute();
    if ($result == 1) {
        //$q = "SELECT * FROM Favority WHERE "
        //삽입이 정상적으로 되었을 때, 정보를 보내준다.
        $json = $stmt->fetchAll(PDO::FETCH_ASSOC);
        JsonUtil::printJson($json);
    } else {
        JsonUtil::printError("2", "DB 삽입 실패");
        return;
    }
}

public static function deleteFavorite()
{
    $dbConnection = new DBConnection();
    $connect = $dbConnection->getConnect();

    if (!$connect)
        return;

    $mysql_database = 'banana';

    //파라미터 체크
    if (!isset($_POST['store_id']) || !isset($_POST['user_id'])) {
        JsonUtil::printError("1", "파라미터 없음");
        return;
    }
    $store_id = $_POST['store_id'];
    $user_id = $_POST['user_id'];

    // query test
    $query = sprintf("use %s", $mysql_database);
    $connect->query($query);
    $q = "DELETE FROM Favority where user_id = '$user_id' and store_id = '$store_id'";
    $stmt = $connect->prepare($q);
    $result = $stmt->execute();
    if ($result == 1) {
        //$q = "SELECT * FROM Favority WHERE "
        //삽입이 정상적으로 되었을 때, 정보를 보내준다.
        $json = $stmt->fetchAll(PDO::FETCH_ASSOC);
        JsonUtil::printJson($json);
    } else {
        JsonUtil::printError("2", "DB 삭제 실패");
        return;
    }
}
```

안드로이드 동작 순서
```
1. 사용자 즐겨찾기 버튼 클릭
2. 현재 즐겨찾기 상태 체크
3. 상태에 따라 서버로 즐겨찾기 추가 제거 요청
4. 서버로 부터 응답을 받음
5. 성공시 데이터 및 UI갱신
``` 

1. 사용자 즐겨찾기 버튼 클
```xml
<!-- 즐겨찾기 버튼 -->
<ImageView
    android:id="@+id/imgFavorite"
    android:layout_width="30dp"
    android:layout_height="30dp"
    android:layout_alignParentRight="true"
    android:layout_marginRight="10dp"
    android:layout_marginTop="10dp"
    android:onClick='@{(view) -> vm.clickFavorite(view, store)}'
    android:src="@drawable/selector_restaurant_wannago" />
```
클릭을 했을때 아래와같은 방법으로 함수를 호출해주는 방법이 핵심이다.
android:onClick='@{(view) -> vm.clickFavorite(view, store)}'


2. 현재 즐겨찾기 상태 체크
3. 상태에 따라 서버로 즐겨찾기 추가 제거 요청 
클릭에 대한 정의는 viewmodel에서 하였으며 즐겨찾기 상태에 따른 추가 제거 요청 기능을 한다.
```java
public void clickFavorite(View v, Store store) {
        if (!BananaPreference.getInstance(v.getContext()).loadUser().isLogin()) {
            Toast.makeText(v.getContext(), "로그인 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (store.isExistsFavority_id()) {
            ApiManager.getInstance().deleteFavorite(v.getContext(), store);
        } else {
            ApiManager.getInstance().addFavorite(v.getContext(), store);
        }
    }
```

4. 서버로 부터 응답을 받음
5. 성공시 데이터 및 UI갱신
```java
public void addFavorite(Context context, Store store) {
    Map<String, String> param = new HashMap<>();
    param.put("user_id", BananaPreference.getInstance(context).loadUser().user_id);
    param.put("store_id", store.getStore_id());
    getService().addFavorite(param).enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
            String body = "";
            try {
                body = response.body().string();
                store.setFavority_id("100");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    });
}

public void deleteFavorite(Context context, Store store) {
    Map<String, String> param = new HashMap<>();
    param.put("user_id", BananaPreference.getInstance(context).loadUser().user_id);
    param.put("store_id", store.getStore_id());
    getService().deleteFavorite(param).enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            //통신 성공시 모든 메시지를 받는 곳(response, 200/500 code 등등..)
            String body = "";
            try {
                body = response.body().string();
                store.setFavority_id(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    });
}
```