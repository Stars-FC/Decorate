package com.ygc.estatedecoration.fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

/**
 * Created by FC on 2017/11/13.
 *
 * 首页
 */

public class HomeFragment extends BaseFragment {

    private static final String ARG_C = "content";

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
//        String content = getArguments().getString(ARG_C); //获取
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("地产装饰");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        return true;
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
        return R.layout.fragment_home;
    }
}
