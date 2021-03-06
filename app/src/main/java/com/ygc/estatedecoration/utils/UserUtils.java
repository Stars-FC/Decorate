package com.ygc.estatedecoration.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ygc.estatedecoration.app.MyApplication;
import com.ygc.estatedecoration.bean.LoginBean;

/**
 * Created by FC on 2017/11/21.
 * 用户信息缓存数据类
 */

public class UserUtils {

    public static final String SEARCH = "search_info"; //搜索的缓存
    public static final String USER = "user_info";     //个人信息的缓存
    public static final String STOREID = "store_id";     //商铺id
    public static final String userId = "userId";
    public static final String userPws = "userPws";
    public static final String userName = "userName";
    public static final String onLine = "onLine";  //判断用户是否在线
    public static LoginBean.DataBean sDataBean;

    /**
     * 保存用户信息
     *
     * @param name
     * @param key
     * @param object
     */
    public static void setParam(String name, String key, Object object) {
        String type = object.getClass().getSimpleName(); // 降低耦合度，提升可扩展性
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences(name, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        //判断根据传入不同的值设置不同的类型
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.commit();//提交
    }

    /**
     * 获取保存的数据
     *
     * @param name
     * @param key
     * @param object
     * @return
     */
    public static Object getParam(String name, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences(name, Context.MODE_PRIVATE);

        //根据传入的类型返回相应的值
        if ("String".equals(type)) {
            return sp.getString(key, (String) object);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) object);
        }

        return null;
    }

    /**
     * 获取到用户是否在线用户在线状态
     *
     * @param context 上下文
     * @param key
     * @return
     */
    public static boolean getOnLineBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(onLine, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 存入用户在线状态
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putOnLineBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(onLine, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * @return 用户id
     */
    public static String getUserId() {
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences(USER, Context.MODE_PRIVATE);
        return sp.getString(userId, "");
    }


    /**
     * @return 用户Name(手机号)
     */
    public static String getUserName() {
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences(USER, Context.MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    /**
     * @return 用户密码
     */
    public static String getUserPws() {
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences(USER, Context.MODE_PRIVATE);
        return sp.getString(userPws, "");
    }

    /**
     * 判断是否已登录
     *
     * @return
     */
    public static boolean isLogin() {
        return !TextUtils.isEmpty(getUserId());
    }

    /**
     * 清除缓存
     *
     * @param name
     */
    public static void clear(String name) {
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
