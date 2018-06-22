package com.study.restaurant.util;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.databinding.LayoutCustomTabBinding;

import java.util.zip.Inflater;

public class CustomTabLayout extends TabLayout {
    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    public Tab newTab() {
        return super.newTab();
    }
}
