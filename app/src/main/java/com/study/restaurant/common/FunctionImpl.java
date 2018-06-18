package com.study.restaurant.common;

import android.view.View;

public abstract class FunctionImpl {

    public interface Main {
        // 맛집찾기 클릭 -> 맛집찾기 페이지 이동
        void goFindRestaurant(View v);

        // 망고픽 클릭 -> 망고픽 페이지 이동
        void goMangoPick(View v);

        // 등록 클릭 -> 등록 화면 띄우기
        void goRegister(View v);

        // 소식 클릭 -> 소식 페이지 이동
        void goNews(View v);

        // 내정보 클릭 -> 내정보 이동
        void goMyInformation(View v);
    }


    public interface FindRestaurant {

        /**
         * 스토어 요약 정보 요청
         */
        void requestStoreSummery();

        /**
         * 1. 지역선택클릭 -> 지역선택 팝업 발생
         *
         * @param v
         */
        void clickSelectLocation(View v);

        /**
         * 2. 검색클릭 -> 검색화면 이동
         *
         * @param v
         */
        void clickSearch(View v);

        /**
         *
         */
        void goSearch();

        /**
         * 3. 지도클릭 -> 지도화면 이동
         *
         * @param v
         */
        void clickMap(View v);

        /**
         *
         */
        void goMap();

        /**
         * 4. 베너클릭 -> 해당 베너 화면이동
         *
         * @param v
         */
        void clickBanner(View v);

        /**
         *
         */
        void goBanner();

        /**
         * 5. 정렬버튼클릭 -> 정렬 선택 팝업 발생
         *
         * @param v
         */
        void clickSort(View v);

        /**
         *
         */
        void showSelectSortItemPopup();

        /**
         * 6. 검색반경버튼 클릭 -> 검색반경 팝업 발생
         *
         * @param v
         */
        void clickBoundary(View v);

        /**
         *
         */
        void showSelectBoundaryPopup();

        /**
         * 7. 필터 클릭 -> 필터 팝업 발생
         *
         * @param v
         */
        void clickFilter(View v);

        /**
         *
         */
        void showSelectFilterPopup();

        /**
         * 8. 이벤트 베너 클릭 -> 이벤트 페이지 이동
         *
         * @param v
         */
        void clickEventBanner(View v);

        /**
         *
         */
        void goEvent();

        /**
         * 9. 리스트 아이템 클릭 -> 맛집 상세페이지 이동
         *
         * @param v
         */
        void clickListItem(View v);

        /**
         *
         */
        void goRestaurant();

        /**
         * 1. 아래로 스크롤 시 최상단 이동버튼이 나온다
         * 2. 위로 스크롤 시 최상단 이동버튼이 사라진다.
         *
         * @param show
         */
        void setShowTopButton(boolean show);

    }

}
