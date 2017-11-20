package com.ygc.estatedecoration.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.TransactionManageOfferActivity;
import com.ygc.estatedecoration.adapter.HomeMyStoreEvaluateAdapter;
import com.ygc.estatedecoration.adapter.HomeTransactionManageOfferAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/20.
 * 首页-交易管理-ViewPager第二界面报价页面
 */

public class TransactionManageOfferFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private HomeTransactionManageOfferAdapter mAdapter;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        mAdapter = new HomeTransactionManageOfferAdapter(list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
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
        return R.layout.home_need_already_offer;
    }

    @OnClick({ R.id.tv_offer})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_offer:
                intent.setClass(mActivity, TransactionManageOfferActivity.class);
                startActivity(intent);
                break;
        }
    }
}
