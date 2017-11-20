package com.ygc.estatedecoration.base;

import android.content.Context;
import android.view.View;

/**
 * Created by FC on 2017/11/20.
 * 主页-交易管理的基类
 */

public abstract class BasePager {

    public final Context mContext;

    public View rootView; //各个子页面的布局

    public BasePager(Context context) {
        mContext = context;
        rootView = initView();
    }

    /**
     * @return 返回界面
     */
    public abstract View initView();

    /**
     * 初始化数据方法，在子页面需要绑定数据、请求网络数据等重写该方法
     */
    public void initData() {

    }
}
