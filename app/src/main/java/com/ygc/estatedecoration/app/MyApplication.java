package com.ygc.estatedecoration.app;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by FC on 2017/10/30.
 */

public class MyApplication extends Application {

    private static Context mContext;//定义全局上下文

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    /**
     * @return 上下文
     */
    public static Context getmContext() {
        return mContext;
    }

}
