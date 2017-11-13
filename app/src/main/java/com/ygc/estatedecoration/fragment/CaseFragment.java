package com.ygc.estatedecoration.fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

/**
 * Created by FC on 2017/11/13.
 * 案例界面
 */

public class CaseFragment extends BaseFragment {

    private static final String ARG_C = "content";

    public static CaseFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        CaseFragment fragment = new CaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("案例");
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
        return R.layout.fragment_case;
    }
}
