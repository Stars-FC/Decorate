package com.ygc.estatedecoration.fragment.cas;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseEffectAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class EffectFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public EffectFragment() {
        // Required empty public constructor
    }

    private int allPagerNum;//总页数
    private int curPagerNum = 1;//当前页数
    private CompositeDisposable compositeDisposable;

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private List<String> dataList = new ArrayList<>();
    private CaseEffectAdapter mCaseEffectAdapter;

    public static EffectFragment newInstance(String param1, String param2) {
        EffectFragment fragment = new EffectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        compositeDisposable=new CompositeDisposable();
        requestDataEvent(Constant.NORMAL_REQUEST);
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {


    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initRecyclerView();
    }

    @Override
    protected void addListener() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65"));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_effect;
    }

    private void initRecyclerView() {

        mCaseEffectAdapter = new CaseEffectAdapter(R.layout.item_case_effect, dataList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyclerView.setAdapter(mCaseEffectAdapter);

        mCaseEffectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                requestDataEvent(Constant.NORMAL_REQUEST);
            }
        }, mRecyclerView);
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        requestDataEvent(Constant.REFRESH_REQUEST);
    }

    private void requestDataEvent(String requestMark) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
