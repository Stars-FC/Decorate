package com.ygc.estatedecoration.fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

/**
 * Created by FC on 2017/11/13.
 * 消息界面
 */

public class NewsFragment extends BaseFragment {
    private static final String ARG_C = "content";

    public static NewsFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("消息");
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
        return R.layout.fragment_news;
    }
}
