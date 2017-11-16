package com.ygc.estatedecoration.fragment.home;

import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺-公司信息界面
 */

public class MyStoreInformationFragment extends BaseFragment{
    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.home_mystore_information;
    }
}
