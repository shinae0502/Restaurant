package com.study.restaurant.ui.mainview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.study.restaurant.BR;
import com.study.restaurant.navigation.BananaNavigation;

public class MainActivityViewModel extends BaseObservable {
    boolean menu1 = true;
    boolean menu2;
    boolean menu3;
    boolean menu4;
    boolean menu5;

    boolean isMenuEanbled;
    BananaNavigation.MainNavigation mainActivitytNavigation;

    public MainActivityViewModel(BananaNavigation.MainNavigation mainActivitytNavigation) {
        this.mainActivitytNavigation = mainActivitytNavigation;
    }

    @Bindable
    public boolean isMenuEanbled() {
        return isMenuEanbled;
    }

    public void setMenuEanbled(boolean menuEanbled) {
        isMenuEanbled = menuEanbled;
    }

    @Bindable
    public boolean isMenu1() {
        return menu1;
    }

    public void setMenu1(boolean b) {
        this.menu1 = b;
        if (b == true) {
            menu2 = false;
            menu3 = false;
            menu4 = false;
            menu5 = false;
            notifyAllMenu();
        }
    }

    private void notifyAllMenu() {
        notifyPropertyChanged(BR.menu1);
        notifyPropertyChanged(BR.menu2);
        notifyPropertyChanged(BR.menu3);
        notifyPropertyChanged(BR.menu4);
        notifyPropertyChanged(BR.menu5);
    }

    @Bindable
    public boolean isMenu2() {
        return menu2;
    }

    public void setMenu2(boolean b) {
        this.menu2 = b;
        if (b == true) {
            menu1 = false;
            menu3 = false;
            menu4 = false;
            menu5 = false;
            notifyAllMenu();
        }
    }

    @Bindable
    public boolean isMenu3() {
        return menu3;
    }

    public void setMenu3(boolean b) {
        this.menu3 = b;
    }

    @Bindable
    public boolean isMenu4() {
        return menu4;
    }

    public void setMenu4(boolean b) {
        this.menu4 = b;
        if (b == true) {
            menu1 = false;
            menu2 = false;
            menu3 = false;
            menu5 = false;
            notifyAllMenu();
        }
    }

    @Bindable
    public boolean isMenu5() {
        return menu5;
    }

    public void setMenu5(boolean b) {
        this.menu5 = b;
        if (b == true) {
            menu1 = false;
            menu2 = false;
            menu3 = false;
            menu4 = false;
            notifyAllMenu();
        }
    }

    public void clickFindRestaurant() {
        mainActivitytNavigation.goFindRestaurant();
        setMenu1(true);
    }


    public void clickMangoPick() {
        mainActivitytNavigation.goMangoPick();
        setMenu2(true);
    }

    public void clickMyInformation() {
        mainActivitytNavigation.goMyInformation();
        setMenu5(true);
    }

    public void clickNews() {
        mainActivitytNavigation.goNews();
        setMenu4(true);
    }

    /**
     * 하단 메뉴의 가운데 버튼을 클릭 시 등록화면 보여주는기능
     * TODO::함수명 바꿔주기
     *
     * @param v
     */
    public void clickRegister() {
        showRegisterMenu(!isMenu3());
    }

    public void showRegisterMenu(boolean b) {
        setMenu3(b);
        setMenuEanbled(b);
        mainActivitytNavigation.rotationMenu(b);
        notifyPropertyChanged(BR.menuEanbled);
        notifyAllMenu();
        if (b) {
            mainActivitytNavigation.registerShowAnimation();
        } else {
            mainActivitytNavigation.hideMenu();
        }
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setMenu1(true);
                        break;
                    case 1:
                        setMenu2(true);
                        break;
                    case 2:
                        setMenu4(true);
                        break;
                    case 3:
                        setMenu5(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
    }

}