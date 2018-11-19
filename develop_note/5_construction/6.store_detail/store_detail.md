# 맛집 상세보기 개발하기

이 앱에서 가장 많으 정보가 표시되어야 하는 부분이 아닌가 싶다.

기획서 정리한부분을 보자

[맛집 상세 기획서](https://docs.google.com/presentation/d/1lnIcCSdxju_wRRwW0msWjdMBYY1iroJgfuuHko581SE/edit#slide=id.g45c1f014ad_0_467)

기획서에 나와있는 기능을 정리해보자.
```
1. 닫기 기능
2. 마이리스트 추가
3. 카카오톡 공유
4. 일반 공유
5. 이미지 리스트 
6. 맛집 정보 제공
7. 즐겨찾기
8. 체크인
9. 리뷰쓰기
10. 사진올리기
11. 주소표시
12. 위치 지도로 표시하기
13. 길찾기
14. 네비게이션
15. 택시부르기
16. 주소 복사
17. 전화하기
18. 편의정보
19. 편의정보 더보기
20. 메뉴
21. 메뉴 더보기
22. 잘못된 정보 수정 요청 이동
23. 식당 키워드
24. 베너
25. 맛깔나는 리뷰 필터링 버튼
26. 리뷰 리스트
27. 리뷰 더보기
28. 블로그 검색 기능
29. 관련 TOP리스트 표시
30. 주변 인기 식당 리스트 표시
```

먼저 위에 있는 기능을 빈 함수와 연결시켜 정리해보았다.

```java
/**********************************************************************************************
* function
**********************************************************************************************/

private Store getStore() {
return getIntent().getParcelableExtra("store");
}

/** TODO:: 닫기 기능 */
public void clickClose(View view) {
onBackPressed();
}

/** TODO:: 마이리스트 추가 */
public void addMyList(View v){}

/** TODO:: 카카오톡 공유 */
public void shareKakao(View v){}

/** TODO:: 일반공유 */
public void normarShare(View v){}

/**TODO:: 정보 제공 기능
* 서버에서 받은 정보를 가지고 표시해야하는 기능들
* 이미지 리스트
* 맛집 정보 제공
* 주소표시
* 위치 지도로 표시하기
* 편의정보 표시하기
* 메뉴 표시하기
* 식당 키워드
* 베너
* 리뷰리스트
* 관련 TOP리스트 표시
* 주변 인기 식당 리스트 표시
* */

/** TODO:: 즐겨찾기 */
public void addFavorite(View v){}

/** TODO:: 체크인 */
public void checkIn(View v){}

/** TODO:: 리뷰쓰기 */
public void writeReview(View v){}

/** TODO:: 사진 올리기 */
public void uploadPicture(View v){}

/** TODO:: 길찾기 */
public void findRounte(View v){}

/** TODO:: 네비게이션 */
public void navigation(View v){}

/** TODO:: 택시부르기 */
public void callTaxi(View v){}

/** TODO:: 주소 복사 */
public void copyAddress(View v){}

/** TODO:: 전화하기 */
public void call(View v){}

/** TODO:: 편의정보 더보기 */
public void moreConvenience(View v){}

/** TODO:: 메뉴 더보기 */
public void moreMenu(View v){}

/** TODO:: 잘못된 정보 수정 요청 이동 */
public void requestModify(View v){}

/** TODO:: 맛깔나는 리뷰 필더링 버튼*/
public void reviewFilter(View v){}

/** TODO:: 리뷰 더보기 */
public void moreReview(View v){}

/** TODO:: 블로그 검색 기능 */
public void searchBlog(View v){}
```

그다음엔 레이아웃에서 클릭하는 부분을 연결해보자



위에서 부터 차근차근 개발해보자.

하지만 처음부터 어렵다.

맛집의 이미지를 최대 5장 가져오는 기능인데. 이 사진은 사용자가 리뷰로 올린 이미지를 기반으로 한다.

기존에 리뷰테이블과 이미지 테이블을 분리해놔서 이미지 테이블에서 날짜 순으로 이미지 데이터를 불러오면된다.

스토어의 상세 정보를 가져오는 쿼리를 짜봤다.

```
// 맛집 정보 가져오기
$q = "SELECT * FROM Store WHERE store_id = $store_id"

// 이미지 가져오기
$q = "SELECT * FROM Image WHERE store_id = $store_id";

```