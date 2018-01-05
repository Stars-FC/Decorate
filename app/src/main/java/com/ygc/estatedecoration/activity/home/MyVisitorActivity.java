package com.ygc.estatedecoration.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MyVisitorAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.VisiterBean;
import com.ygc.estatedecoration.entity.HomeMyVisitor;
import com.ygc.estatedecoration.entity.HomeMyVisitorSection;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/16.
 * 主页-我的访客界面
 */

public class MyVisitorActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_all)
    TextView mTvAll;//总浏览量
    @BindView(R.id.tv_today)
    TextView mTvToday;//今日览量


    private MyVisitorAdapter mAdapter;
    private List<HomeMyVisitorSection> mList;

    private List<VisiterBean.DataBean.ListBean> mData;

    private int mPager = 0;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的访客");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {


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

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAdapter = new MyVisitorAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataFromNet();
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

    /**
     * 获取我的访客信息
     */
    public void getDataFromNet() {

        APPApi.getInstance().service
                .findAllByAuId(UserUtils.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VisiterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(VisiterBean bean) {
                        String msg = bean.getMsg();
                        if ("1".equals(bean.getResponseState())) {
                            mTvAll.setText(bean.getData().getSumNum() + "");
                            mTvToday.setText(bean.getData().getTodayNum() + "");
                            mAdapter.setNewData(bean.getData().getList());
                        } else {
                            showToast(bean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 为适配器设置数据
     *
     * @param bean
     */
    public void setAdapter(VisiterBean bean) {
        mData = bean.getData().getList();
        //为嵌套的两个recyclerview赋值
        mList = new ArrayList<>();

        for (int i = 0; i < mData.size(); i++) {
            mList.add(new HomeMyVisitorSection(true, mData.get(i).getVisited_time()));
            for (int j = 0; j < mData.get(i).getUserList().size(); j++) {
                mList.add(new HomeMyVisitorSection(new HomeMyVisitor(mData.
                        get(i).getUserList().get(j).getUserInfo().getPicture_url(),
                        mData.get(i).getUserList().get(j).getUserInfo().getNickname(),
                        mData.get(i).getUserList().get(j).getUserInfo().getCreate_time())));
            }
        }
//        mAdapter = new MyVisitorAdapter(R.layout.item_home_myvisitor_below, R.layout.item_home_myvisitor_top, mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
