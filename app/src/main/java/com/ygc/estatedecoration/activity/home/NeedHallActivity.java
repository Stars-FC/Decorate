package com.ygc.estatedecoration.activity.home;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.GirdDropDownAdapter;
import com.ygc.estatedecoration.adapter.HomeNeedHallAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.TitleBar;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/14.
 * 首页-需求大厅界面
 */

public class NeedHallActivity extends BaseActivity {

    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;

    private View mTopView; //头布局
    private View mRcView;//recycleyview的布局

    private RecyclerView mRecyclerView;

    private List<View> popupViews = new ArrayList<>();
    private String headers[] = {"全部", "装修类型", "房屋现状"};//筛选的标题集合
    private String alls[] = {"不限", "沈阳", "北京", "上海", "成都", "广州", "深圳", "南京", "杭州"};
    private String types[] = {"不限", "家装", "工装", "家装", "工装"};
    private String nows[] = {"不限", "局部改造", "毛胚房", "旧房翻新"};
    private int constellationPosition = 0;//默认选择
    private GirdDropDownAdapter allsAdapter;
    private GirdDropDownAdapter typesAdapter;
    private GirdDropDownAdapter nowsAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {
        //筛选按钮的点击事件
        mTopView.findViewById(R.id.tv_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDropDownMenu.closeMenu();//关闭选择列表
                mDrawerLayout.openDrawer(GravityCompat.END); //显示左侧筛选界面
            }
        });
    }

    @Override
    protected void initView() {
        //头布局
        mTopView = View.inflate(getApplicationContext(), R.layout.top_home_needhall, null);
        //recycleyview的布局
//        mRcView = View.inflate(getApplicationContext(), R.layout.recyclerview, null);

        mRcView =LayoutInflater.from(getApplicationContext()).inflate(R.layout.recyclerview,null);

        mRecyclerView = (RecyclerView)mRcView.findViewById(R.id.recyclerview);

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止mDrawerLayout滑动显示

        //设置mDropDownMenu筛选
        initDropDownMenu();
    }

    /**
     * 设置mDropDownMenu筛选
     */
    private void initDropDownMenu() {

        //init alls menu
        final ListView cityView = new ListView(this);
        allsAdapter = new GirdDropDownAdapter(this, Arrays.asList(alls));
        cityView.setDividerHeight(0);
        cityView.setAdapter(allsAdapter);

        //init types menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        typesAdapter = new GirdDropDownAdapter(this, Arrays.asList(types));
        ageView.setAdapter(typesAdapter);

        //init nows menu
        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        nowsAdapter = new GirdDropDownAdapter(this, Arrays.asList(nows));
        sexView.setAdapter(nowsAdapter);

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                allsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : alls[position]);
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typesAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : types[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nowsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : nows[position]);
                mDropDownMenu.closeMenu();
            }
        });

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, mRcView, mTopView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(NeedHallActivity.this));
        mRecyclerView.setAdapter(new HomeNeedHallAdapter(list));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_need_hall;
    }

    @OnClick({R.id.back})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.back: //后退按钮
                finish();
                break;

        }
    }
}
