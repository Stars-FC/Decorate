package com.ygc.estatedecoration.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhan on 2017-9-5.
 * 工具类
 */

public class MyPublic {

    /**
     * 加载网络图片
     */
    public static void loadImg(String url, ImageView view) {
        if (TextUtils.isEmpty(url) || view == null) return;
//        Glide.with(ConfigApplication.getC()).load(UrlUtils.All + "/" + url).into(view);
    }

    /**
     * 获取当前时间
     */
    public static String getNowTime() {
//        return "2017-09-11";
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取订单状态
     */
    public static String getType(String state) {
        if (TextUtils.isEmpty(state)) return "无";
        switch (state) {
            case "1":
                return "待付款";
            case "2":
                return "待收货";
            case "3":
                return "待评价";
            case "4":
                return "已评价";
            case "5":
                return "退货/售后";
        }
        return "无";
    }

    /**
     * 获取客服QQ
     * <p>
     * 1.此处传入的QQ号,需开通QQ推广功能,不然向此QQ号发送临时消息,会不成功.
     * 2.开通QQ推广方法:
     * ................1.打开QQ推广网址http://shang.qq.com并用QQ登录
     * ................2.点击顶部导航栏:推广工具
     * ................3.在弹出菜单中点击'立即免费开通' 即可
     */
    public static String getQQ() {
        return "1070371262";
    }

    /**
     * 获取客服电话
     */
    public static String getPhone() {
        return "13888888888";
    }

    /**
     * 打电话
     */
    public static void call(Context context, String mobile) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + String.valueOf(mobile));
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取app版本号
     */
    public static String getVersion(Context act) {
        try {
            PackageManager manager = act.getPackageManager();
            PackageInfo info = manager.getPackageInfo(act.getPackageName(), 0);
            String version = info.versionName;
            return "Version " + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号获取失败";
        }
    }

}
