package com.ygc.estatedecoration.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.my.SettingActivity;
import com.ygc.estatedecoration.activity.my.SettingSaleActivity;
import com.ygc.estatedecoration.adapter.MyVisitorAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.entity.HomeMyVisitor;
import com.ygc.estatedecoration.entity.HomeMyVisitorSection;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //为嵌套的两个recyclerview赋值
        List<HomeMyVisitorSection> list = new ArrayList<>();
        list.add(new HomeMyVisitorSection(true, "11月11日"));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "张三", "12:31")));
        list.add(new HomeMyVisitorSection(true, "11月14日"));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "学习", "23:00")));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "使我", "5:00")));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "快乐", "")));
        list.add(new HomeMyVisitorSection(true, "11月16日"));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "还好", "")));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "没", "12：00")));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "放弃", "13：00")));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "终于", "17:30")));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "等到", "")));
        list.add(new HomeMyVisitorSection(new HomeMyVisitor("", "你", "")));

        MyVisitorAdapter myVisitorAdapter = new MyVisitorAdapter(R.layout.item_home_myvisitor_below, R.layout.item_home_myvisitor_top, list);

        mRecyclerView.setAdapter(myVisitorAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_myvisitor;
    }

    @OnClick({R.id.naviButtonLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退按钮
                finish();
                break;
        }
    }
}
