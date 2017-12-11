package com.ygc.estatedecoration.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.GirdDropDownAdapter;
import com.ygc.estatedecoration.adapter.HomeNeedHallAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.TitleBar;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/14.
 * 首页-需求大厅界面
 */

public class NeedHallActivity extends BaseActivity {

    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;

    private View mTopRightView; //右头布局
    private View mTopLeftView; //左侧头布局
    private View mRcView;//recycleyview的布局

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<View> popupViews = new ArrayList<>();
    private String headers[] = { "装修类型", "房屋现状"};//筛选的标题集合
//    private String alls[] = {"不限", "沈阳", "北京", "上海", "成都", "广州", "深圳", "南京", "杭州"};
    private String types[] = {"不限", "家装", "工装", "家装", "工装"};
    private String nows[] = {"不限", "局部改造", "毛胚房", "旧房翻新"};
    private int constellationPosition = 0;//默认选择
    private GirdDropDownAdapter allsAdapter;
    private GirdDropDownAdapter typesAdapter;
    private GirdDropDownAdapter nowsAdapter;
    private HomeNeedHallAdapter mAdapter;
    private List<NeedBean.DataBean> list = new ArrayList<>();


    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {
        //筛选按钮的点击事件
        mTopRightView.findViewById(R.id.tv_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDropDownMenu.closeMenu();//关闭选择列表
                mDrawerLayout.openDrawer(GravityCompat.END); //显示左侧筛选界面
            }
        });

        mTopLeftView.findViewById(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDropDownMenu.closeMenu();//关闭选择列表
                showToast("mTopLeftView");
            }
        });

        //每个条目的点击事件
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("mRecyclerview第" + position + "数据");
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        //上拉加载更多
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mAdapter.loadMoreComplete();//完成
//                mAdapter.loadMoreFail();//失败
//                mAdapter.loadMoreEnd();//结束
            }
        }, mRecyclerView);
    }

    @Override
    protected void initView() {


        //头布局
        mTopRightView = View.inflate(getApplicationContext(), R.layout.top_home_needhall_right, null);

        mTopLeftView= View.inflate(getApplicationContext(), R.layout.top_home_needhall_left, null);

        mRcView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.recyclerview, null);

        mRecyclerView = (RecyclerView) mRcView.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRcView.findViewById(R.id.swipeLayout);

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止mDrawerLayout滑动显示

        //设置mDropDownMenu筛选
        initDropDownMenu();

        mAdapter = new HomeNeedHallAdapter(list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(NeedHallActivity.this));
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置mDropDownMenu筛选
     */
    private void initDropDownMenu() {

        //init alls menu
//        final ListView cityView = new ListView(this);
//        allsAdapter = new GirdDropDownAdapter(this, Arrays.asList(alls));
//        cityView.setDividerHeight(0);
//        cityView.setAdapter(allsAdapter);

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
//        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);

        //add item click event
       /* cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                allsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : alls[position]);
                mDropDownMenu.closeMenu();
            }
        });*/

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typesAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : types[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nowsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : nows[position]);
                mDropDownMenu.closeMenu();
            }
        });

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, mRcView,mTopLeftView, mTopRightView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

//        queryAllNeedDataEvent(1);

    }

    private void queryAllNeedDataEvent(int pageNum) {
        APPApi.getInstance().service
                .queryAllNeed(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeedBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NeedBean needBean) {
                        if (needBean.ResponseStatus.equals("1")) {
                            list.addAll(needBean.getData());
                        } else {
                            showToast(needBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
