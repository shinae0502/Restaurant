package com.study.restaurant;

public abstract class FunctionImpl {

    public interface Main {
        // 맛집찾기 클릭 -> 맛집찾기 페이지 이동
        void goFindRestaurant();

        // 망고픽 클릭 -> 망고픽 페이지 이동
        void goMangoPick();

        // 등록 클릭 -> 등록 화면 띄우기
        void goRegister();

        // 소식 클릭 -> 소식 페이지 이동
        void goNews();

        // 내정보 클릭 -> 내정보 이동
        void goMyInformation();
    }


    public interface FindRestaurant {
        //1. 지역선택클릭 -> 지역선택 팝업 발생
        void clickSelectLocation();
        void showSelectLocationPopup();

        //2. 검색클릭 -> 검색화면 이동
        void clickSearch();
        void goSearch();

        //3. 지도클릭 -> 지도화면 이동
        void clickMap();
        void goMap();

        //4. 베너클릭 -> 해당 베너 화면이동
        void clickBanner();
        void goBanner();

        //5. 정렬버튼클릭 -> 정렬 선택 팝업 발생
        void clickSort();
        void showSelectSortItemPopup();

        //6. 검색반경버튼 클릭 -> 검색반경 팝업 발생
        void clickBoundary();
        void showSelectBoundaryPopup();

        //7. 필터 클릭 -> 필터 팝업 발생
        void clickFilter();
        void showSelectFilterPopup();

        //8. 이벤트 베너 클릭 -> 이벤트 페이지 이동
        void clickEventBanner();
        void goEvent();

        //9. 리스트 아이템 클릭 -> 맛집 상세페이지 이동
        void clickListItem();
        void goRestaurant();

        // 동작 이벤트
        //1. 아래로 스크롤 시 최상단 이동버튼이 나온다
        //2. 위로 스크롤 시 최상단 이동버튼이 사라진다.
        void setShowTopButton(boolean show);

    }

}
