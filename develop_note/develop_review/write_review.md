# 리뷰 쓰기 화면 개발

리뷰 쓰기 화면 UI
<img src = "register_review4.png" height="800px">

이제 업로드만 남았다 이부분은 생각을 좀 많이 해야하는 부분같다. 이미지를 업로드 했을때 어떤방식으로 저장할 것인지 업로드 절차는 어떻게 할 것인지 리뷰를 수정해야할 경우, 리뷰를 삭제 할 경우 데이터 처리 등  일단 UI먼저 개발해보도록 하자.

UI개발 화면을 정리해보자

```
1. 상단 타이틀바
2. 맛있다, 괜찮다, 별로 평가하는부분에 대한 selector 처리
3. 텍스트 입력부분
4. 완료버튼
```



아래 링크를 통해 구현한 코드를 확인 할 수 있다.

TODO:: activity_write_review.xml 링크하기



기능을 정리해보자.

```
1. 타이틀바의 뒤로가기 기능과 닫기기능
2. 텍스트 작성 시 글자갯수 갱신 기능과 2000자 제한
3. 글자가 없을경우 완료 버튼 비활성화
4. 완료 버튼을 눌렀을 경우 서버로 데이터 전송
```

먼저 이미지 업로드를 제외한 리뷰 등록코드를 먼저 작성해보자.
먼저 서버로 전송하는 부분은 POST타입이 아닌 MULTIPART타입으로 전송해야한다.
레트로 핏으로 멀티파트 작상하는 방법
```java
@Multipart
@POST("registerNews.php")
Call<ResponseBody> registerNews(@PartMap() Map<String, RequestBody> params);
```
멀티파트 파라미터 작성 방법
```java
Map<String, RequestBody> params = new HashMap<>();
    params.put("user_id", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().user_id));
    params.put("store_id", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getStore_id()));
    params.put("user_name", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().name));
    params.put("profile_img", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().picture));
    params.put("score", RequestBody.create(MediaType.parse("text/plain"), "" + score));
    params.put("contents", RequestBody.create(MediaType.parse("text/plain"), "" + edReview.getText().toString()));
    params.put("tag1", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getRestaurant_name()));
    params.put("tag2", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getCity_name()));
```

서버 전송 부분을 좀더 상세하게 정리해보자.

```
1. 전송 버튼을 누르면 먼저 이미지 압축 시작하기
2. 압축된 이미지를 임시 저장소에 저장하기
3. 압축이 끝나면 멀티파트업로드를 이용해 이미지와 입력된 데이터 업로드하기
4. 업로드 되는 이미지 프로그래스 처리하기
5. 업로드가 끝나면 임시 압축한 파일 지우기
```

이제 서버에서 어떻게 처리해야할지 생각해보자.

```
1. 멀티파트 업로드를 통해 데이터와 이미지를 함꼐 받는다.
2. 업로드 받은 이미지를 TODO::저장방법
3. 업로드 되는 이미지 프로그래스 처리
4. 데이터 테이블에 삽입
```

```
이미지 활용이 필요한 부분
1. 맛집 상세화면에서 상단부분에 업로드된 이미지 5장 정도를 로드해야한다. 이부분은 리뷰테이블에서 가져와야함.
2. 맛집 화면을 갤러리처럼 보는 부분이 리뷰테이블에서 가져오는 방법
3. 타임라인 부분에 내가 올린 리뷰 사진 리스트가 나온다.
```

리뷰 쓰기가 완료되면 이전에 열려져있던 activity를 다 종료 시켜줘야하는데
쌓여있는데 activity에 대한 관리 방법에 대해 알아보자.

```
찾아낸 방법
1. onActivityResult를 이용해서 각 엑티비티마다 종료해주는 기능을 추가한다.
2. Activity 객채를 static 하게 모아두었다가 finish 시킨다.
```
두 방법다 뭔가 좋은 방법은 아닌것 같지만 다른방법을 찾지 못하여
2번째 방법을 사용하였다.


