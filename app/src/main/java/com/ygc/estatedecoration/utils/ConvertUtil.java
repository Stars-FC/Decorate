package com.ygc.estatedecoration.utils;

import android.content.Context;

public class ConvertUtil {

    public static int dp2px(Context context, int values) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (values * scale + 0.5f);
    }
}
