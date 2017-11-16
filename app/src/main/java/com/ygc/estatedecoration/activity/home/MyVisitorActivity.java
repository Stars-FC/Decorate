package com.ygc.estatedecoration.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MyVisitorAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by FC on 2017/11/16.
 * 主页-我的访客界面
 */

public class MyVisitorActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的访客");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mRecyclerView.setAdapter(new MyVisitorAdapter(R.layout.item_home_myvisitor_top, R.layout.item_home_myvisitor_below, list));

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_myvisitor;
    }
}
