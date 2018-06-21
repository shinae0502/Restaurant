package com.study.restaurant.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.restaurant.R;

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
        return super.newTab().setCustomView(R.layout.layout_custom_tab);
    }

    public void setTabCount(int index, int count)
    {
        View custonView = getTabAt(index).getCustomView();
        RelativeLayout countLayout = custonView.findViewById(R.id.countLayout);
        TextView countView = custonView.findViewById(R.id.count);

        countView.setText(""+count);

        if(count > 0) {
            countLayout.setVisibility(VISIBLE);
        }
        else {
            countLayout.setVisibility(GONE);
        }

    }

    TextView getTitleView(int index) {
        View custonView = getTabAt(index).getCustomView();
        TextView tvTitle = custonView.findViewById(R.id.title);
        return tvTitle;
    }

    public void setText(int index, String title) {
        getTitleView(index).setText(title);
    }

    public String getText(int position) {
        return getTitleView(position).getText().toString();
    }
}
