package com.ygc.estatedecoration.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by FC on 2017/10/30.
 */

public class MyApplication extends Application {

    private static Context mContext;//定义全局上下文

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    /**
     * @return 上下文
     */
    public static Context getmContext() {
        return mContext;
    }

}
