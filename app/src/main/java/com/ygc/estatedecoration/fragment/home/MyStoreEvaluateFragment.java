package com.ygc.estatedecoration.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.HomeMyStoreEvaluateAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺-评价界面
 */

public class MyStoreEvaluateFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private HomeMyStoreEvaluateAdapter mHomeMyStoreEvaluateAdapter;

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

        mHomeMyStoreEvaluateAdapter = new HomeMyStoreEvaluateAdapter(list);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerview.setAdapter(mHomeMyStoreEvaluateAdapter);
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
        return R.layout.home_mystore_evaluate;
    }
}