package com.ygc.estatedecoration.fragment.home;

import android.os.Bundle;
import android.support.transition.Transition;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.TransactionManageNeedAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理-ViewPager第一界面需求详情页面
 */

public class TransactionManageNeedFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private TransactionManageNeedAdapter mTransactionManageNeedAdapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        for (int i = 0; i < 10; i++) {
            dataList.add("heh");
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mTransactionManageNeedAdapter = new TransactionManageNeedAdapter(R.layout.item_need_detail, dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mTransactionManageNeedAdapter);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.home_need_details;
    }
}
