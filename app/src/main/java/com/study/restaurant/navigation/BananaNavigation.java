package com.study.restaurant.navigation;

import com.study.restaurant.model.Store;

public class BananaNavigation {
    public interface SplashNavigation {
        void goLogin();

        void goMain();
    }

    public interface LoginNavigation {
        void goMain();
    }

    public interface MainNavigation {
        void goFindRestaurant();

        void goMangoPick();

        void goRegister();

        void goNews();

        void goMyInformation();

        void registerShowAnimation();

        void hideMenu();

        void rotationMenu(boolean b);
    }

    public interface FindRestaurantNavigation {

        void showSelectRegionPopup();

        void showSortPopup();

        void showBoundaryPopup();

        void showFilterPopup();

        void goSearch();

        void goDeatilRestaurant(Store store);

        void rvToTop();

        void showErrorPopup(String msg);
    }

    public interface CheckInNavigation {
        void goPicture();

        void goCheckInWrite();

        void finish();

        int getCurrentPage();

        void setCurrentPage(int page);
    }

    public interface SelectPictureNavigation {
        void goWriteReview();

        void goWriteReviewWithoutPicture();

        void goCheckIn();
    }
}
