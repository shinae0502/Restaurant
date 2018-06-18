package com.study.restaurant.popup;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.study.restaurant.R;

public class SelectRegionPopup extends Dialog {
    public SelectRegionPopup(@NonNull Context context) {
        super(context);
        setContentView(R.layout.popup_select_region);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
    }


}
