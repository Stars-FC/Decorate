package com.ygc.estatedecoration.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FC on 2017/10/30.
 */

public class MyApplication extends Application {

    private List<Activity> activityList;//用于存放所有启动的Activity的集合

    private static Context mContext;//定义全局上下文

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        activityList = new ArrayList<Activity>();
    }

    /**
     * @return 上下文
     */
    public static Context getmContext() {
        return mContext;
    }


    /**
     * 添加Activity
     */
    public void addActivity(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!activityList.contains(activity)) {
            activityList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity(Activity activity) {
        //判断当前集合中存在该Activity
        if (activityList.contains(activity)) {
            activityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
