package com.study.restaurant.util;

import android.support.design.widget.AppBarLayout;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private AppBarStateChangeListener.State mCurrentState = AppBarStateChangeListener.State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != AppBarStateChangeListener.State.EXPANDED) {
                onStateChanged(appBarLayout, AppBarStateChangeListener.State.EXPANDED);
            }
            mCurrentState = AppBarStateChangeListener.State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != AppBarStateChangeListener.State.COLLAPSED) {
                onStateChanged(appBarLayout, AppBarStateChangeListener.State.COLLAPSED);
            }
            mCurrentState = AppBarStateChangeListener.State.COLLAPSED;
        } else {
            if (mCurrentState != AppBarStateChangeListener.State.IDLE) {
                onStateChanged(appBarLayout, AppBarStateChangeListener.State.IDLE);
            }
            mCurrentState = AppBarStateChangeListener.State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state);
}