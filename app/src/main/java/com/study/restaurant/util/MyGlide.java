package com.study.restaurant.util;

import android.content.Context;

/**
 * Created by yangsarang on 2018. 3. 24..
 */

public class MyGlide {

    // 로드되기 배경
    // placeholder

    // 에러 났을때의 배경
    // error(R.mipmap.future_studio_launcher)

    // 페이드 이팩트
    // transition(DrawableTransitionOptions.withCrossFade())

    // 원형 이미지
    //.apply(RequestOptions.circleCropTransform())

    public static GlideRequests with(Context context)
    {
        return GlideApp.with(context);
    }
}
