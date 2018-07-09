package com.study.restaurant.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.study.restaurant.BR;
import com.study.restaurant.R;
import com.study.restaurant.view.MainActivitytNavigation;

public class MainActivityViewModel extends BaseObservable {
    boolean menu1 = true;
    boolean menu2;
    boolean menu3;
    boolean menu4;
    boolean menu5;

    boolean isMenuEanbled;
    MainActivitytNavigation mainActivitytNavigation;

    public MainActivityViewModel(MainActivitytNavigation mainActivitytNavigation) {
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

    public void clickFindRestaurant(View v) {
        mainActivitytNavigation.goFindRestaurant();
        setMenu1(true);
    }


    public void clickMangoPick(View v) {
        mainActivitytNavigation.goMangoPick();
        setMenu2(true);
    }

    public void clickMyInformation(View v) {
        mainActivitytNavigation.goMyInformation();
        setMenu5(true);
    }

    public void clickNews(View v) {
        mainActivitytNavigation.goNews();
        setMenu4(true);
    }

    public void clickRegister(View v) {
        Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.menu_rotation);
        if (!isMenu3()) {
            setMenu3(!isMenu3());
            ((RelativeLayout) v).getChildAt(1)
                    .startAnimation(animation);
            ((RelativeLayout) v).getChildAt(1)
                    .setRotation(45);
            setMenuEanbled(isMenu3());
            notifyPropertyChanged(BR.menuEanbled);
            notifyAllMenu();
            mainActivitytNavigation.registerShowAnimation();
        } else {
            setMenu3(!isMenu3());
            setMenuEanbled(isMenu3());
            ((RelativeLayout) v).getChildAt(1)
                    .startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.menu_rotation_1));
            ((RelativeLayout) v).getChildAt(1)
                    .setRotation(0);
            notifyPropertyChanged(BR.menuEanbled);
            notifyAllMenu();
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
