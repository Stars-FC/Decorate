package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MyActivitiesAdapter;
import com.ygc.estatedecoration.adapter.MyBrightAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.MyBrightBean;
import com.ygc.estatedecoration.bean.UserInformationBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyBrightActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyBrightAdapter mAdapter;
    private List<MyBrightBean.DataBean> mData;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的亮点");
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setRightTextColor(Color.parseColor("#4EBE65"));
        bar.setRightText("添加");
        return true;
    }

    @Override
    protected void addListener() {
        //每个条目的点击事件
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
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
                getDataFromNet();
            }
        });

        //上拉加载更多
       /* mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mAdapter.loadMoreComplete();//完成
//                mAdapter.loadMoreFail();//失败
//                mAdapter.loadMoreEnd();//结束
            }
        }, mRecyclerView);*/
    }

    @Override
    protected void initView() {
        mAdapter = new MyBrightAdapter();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getDataFromNet();//获取并显示数据
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview_line;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.naviFrameRight://添加
//                showToast("添加");
                Intent intent = new Intent(MyBrightActivity.this, AddMyBrightActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取网路数据
     */
    public void getDataFromNet() {
        APPApi.getInstance().service
                .myBright(UserUtils.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBrightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MyBrightBean brightBean) {
                        mData = brightBean.getData();
                        if (!(mData.size() == 0)) {
                            mAdapter.setNewData(brightBean.getData());
                        }
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mSwipeRefreshLayout.setRefreshing(true);
        getDataFromNet();
    }
}
