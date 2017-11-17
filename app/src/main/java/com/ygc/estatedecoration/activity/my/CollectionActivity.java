package com.ygc.estatedecoration.activity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeAdapter;
import com.ygc.estatedecoration.adapter.MyCollectionAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的-我的收藏页面
 */
public class CollectionActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private MyCollectionAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的收藏");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        bar.setRightText("编辑");
        bar.setRightTextColor(Color.BLACK);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mAdapter = new MyCollectionAdapter(list);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}
