package com.ygc.estatedecoration.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.DemandAndProgressActivity;
import com.ygc.estatedecoration.activity.home.MyStoreAvtivity;
import com.ygc.estatedecoration.activity.home.MyVisitorActivity;
import com.ygc.estatedecoration.activity.home.NeedHallActivity;
import com.ygc.estatedecoration.activity.home.ServerMsgActivity;
import com.ygc.estatedecoration.activity.home.TransactionManageActivity;
import com.ygc.estatedecoration.activity.home.TransactionManageDetailActivity;
import com.ygc.estatedecoration.adapter.HomeAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/13.
 * <p>
 * 首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeAdapter mHomeAdapter;

    private static final String ARG_C = "content";
    private View mView;

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
        bar.setTitleText("地标装饰");
        bar.setRightImageResource(R.drawable.xiaoxi_sel);
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        LogUtil.e("主页初始化");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mHomeAdapter = new HomeAdapter(list);

        mView = View.inflate(mActivity, R.layout.top_home, null);

        mHomeAdapter.addHeaderView(mView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected void addListener() {
        mView.findViewById(R.id.my_store).setOnClickListener(this);
        mView.findViewById(R.id.need_Analysis).setOnClickListener(this);
        mView.findViewById(R.id.trade_manage).setOnClickListener(this);
        mView.findViewById(R.id.my_visitor).setOnClickListener(this);

        //每个条目的点击事件
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                showToast("mRecyclerview第" + position + "数据");
                Intent intent = new Intent(mActivity, DemandAndProgressActivity.class);
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHomeAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                /*if (mCurrentCounter >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    mQuickAdapter.loadMoreEnd();
                } else {
                    if (isErr) {
                        //成功获取更多数据
                        mQuickAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                        mCurrentCounter = mQuickAdapter.getData().size();
                        mQuickAdapter.loadMoreComplete();
                    } else {
                        //获取更多数据失败
                        isErr = true;
                        Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                        mQuickAdapter.loadMoreFail();

                    }
                }*/
            }
        });

        //上拉加载更多
        mHomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mHomeAdapter.loadMoreComplete();//完成
//                mAdapter.loadMoreFail();//失败
//                mAdapter.loadMoreEnd();//结束
                 /* curPagerNum++;
                if (curPagerNum <= allPagerNum) {
                    requestDataEvent(Constant.NORMAL_REQUEST);
                }*/
                mHomeAdapter.loadMoreComplete();
            }
        }, mRecyclerView);

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.my_store: //我的商铺
                intent.setClass(mActivity, MyStoreAvtivity.class);
                startActivity(intent);
                break;
            case R.id.need_Analysis://需求大厅
                intent.setClass(mActivity, NeedHallActivity.class);
                startActivity(intent);
                break;
            case R.id.trade_manage://交易管理
                intent.setClass(mActivity, TransactionManageActivity.class);
                startActivity(intent);
                break;
            case R.id.my_visitor://我的访客
                intent.setClass(mActivity, MyVisitorActivity.class);
                startActivity(intent);
                break;
        }
    }

    @OnClick({R.id.naviFrameRight})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameRight:
                    Intent intent = new Intent(mActivity, ServerMsgActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
