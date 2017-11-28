package com.ygc.estatedecoration.fragment.cas;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CasePanoramaAdapter;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class PanoramaFragment extends BaseFragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private CasePanoramaAdapter mCasePanoramaAdapter;
    private List<String> dataList = new ArrayList<>();

    private int allPagerNum;//总页数
    private int curPagerNum = 1;//当前页数
    private CompositeDisposable compositeDisposable;

    public PanoramaFragment() {
        // Required empty public constructor
    }

    public static PanoramaFragment newInstance(String param1, String param2) {
        PanoramaFragment fragment = new PanoramaFragment();
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
        compositeDisposable = new CompositeDisposable();
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
        mCasePanoramaAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
               /* curPagerNum++;
                if (curPagerNum <= allPagerNum) {
                    requestDataEvent(Constant.NORMAL_REQUEST);
                }*/
               mCasePanoramaAdapter.loadMoreComplete();
            }
        }, mRecyclerView);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_panorama;
    }

    private void initRecyclerView() {
        for (int i = 0; i < 10; i++) {
            dataList.add("haha" + (i + 1));
        }
        mCasePanoramaAdapter = new CasePanoramaAdapter(R.layout.item_case_panorama, dataList);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mCasePanoramaAdapter);
    }

    private void requestDataEvent(final String requestMark) {
        /*APPApi.getInstance().service
                .queryCasePanoramaData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Base base) {
                        requestFinishEvent(base, requestMark);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    private void requestFinishEvent(Base base, String requestMark) {
        if (requestMark.equals(Constant.REFRESH_REQUEST)) {
            mCasePanoramaAdapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
