#[구현][Android] 식당 등록하기(MVVM 하나씩 익히기: 데이터 바인딩에 뷰모델 적용하기)

식당 등록부분은 크게 어려운 기능은 없고 필수 항목을 입력했느냐 안했느냐에 따라서
등록하는 버튼을 활성, 비활성화 시키는 기능이 필요하다. 별거 없는 기능이므로 어떻게 해야
조금더 편하게 코드를 써야하는지에 대해서 생각해보려고 한다. 

안드로이드 코드를 짜다보면 기능들을 어떻게 나눠서 관리해아하는지 클래스에 진입했을때
조금더 한눈에 전체 기능을 알 수 있었으면 좋겠다. 이떄문에 나온것이 패턴이고, MVVM패턴이
현재 가장 

MVVM에 패턴에 한발짝 다가기 위해선 일단 데이터바인딩에 익숙해 지도록 하자.
```
MVVM 공통부분
1. 현재 액티비티에 데이터 바인딩 처리를 한다
2. 데이터바인딩에 뷰모델을 적용하도록 하자
```

### 데이터 바인딩 적용하기
위 부분은 익숙해지면 추후에는 공통 액티비티를 기본적으로 뷰모델과 데이터바인딩이
되어지도록 만들면된다. 지금은 익숙하지 않으니 엑티비티마다 데이터 바인딩과 뷰모델을
만들어 적용시키도록 하자.

일단은 액티비티는 이러한 패턴으로 개발하려고 한다.
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 1.데이터 바인딩 초기화
    ActivityRegisterRestaurantBinding activityRegisterRestaurantBinding
            = DataBindingUtil.setContentView(this, R.layout.activity_register_restaurant);
    // 2.뷰모델 초기화
    vm = new RegisterRestaurantViewModel(this);
    // 3.바인더에 뷰모델 등록
    activityRegisterRestaurantBinding.setVm(vm);
}
```

### 뷰모델 적용하기(2way binding 익숙해지기)
뷰모델도 일단은 다른건 생각하지말고 현재 뷰에서 set하거나 get하는 데이터에 대해서
하나의 변수로 뷰에서 변경할경우 자동으로 데이터 변수가 변경되고, 데이터 변수를 변경할경우
자동으로 뷰가 변경되는 2way binding만을 생각하도록 하자.
RegisterRestaurantViewModel.java


식당 등록하기에 기능들에대해서 정리를 해보고 분류를 해보자
1. 식당 이름등록
2. 식당 지역선택 및 주소 입력
3. 전화번호 입력
4. 음식종류 선택
5. 등록하기

이중에 뷰에 변수를 적용하여 2way binding할 수 있는 요소를 찾아보자.


기본 변수
1. 각 음식 카테고리 선택 여부 변수화
    * isSelectKoreanFood
    * isSelectJapaneseFood
    * isSelectChineseFood
    * isSelectWesternFood
    * isSelectWorldWideFood
    * isSelectBuffet
    * isSelectCafe
    * isSelectBar
2. 식당 이름 
    * restaurantName
3. 주소
    * address
4. 위도, 경도
    * latitude
    * longitude

응용이 필요한 변수    
1. 식당이름 또는 주소입력 시 실시간 체크하여 등록 버튼 활성 비활성화
    * isRequiredField
2. 선택한 음식종류 가져오기
    * getSelectedFood
