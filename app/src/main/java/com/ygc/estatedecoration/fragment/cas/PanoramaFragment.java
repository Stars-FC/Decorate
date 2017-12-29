package com.ygc.estatedecoration.fragment.cas;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CasePanoramaAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.PanoramaBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.lazyviewpager.LazyFragmentPagerAdapter;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PanoramaFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable{

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private CasePanoramaAdapter mCasePanoramaAdapter;

    private int allPagerNum;//总页数
    private int curPagerNum = 1;//当前页数

    public static PanoramaFragment newInstance() {
        PanoramaFragment fragment = new PanoramaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        requestDataEvent(curPagerNum, Constant.NORMAL_REQUEST);
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
        mCasePanoramaAdapter = new CasePanoramaAdapter();
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mCasePanoramaAdapter);
    }

    private void requestDataEvent(int curPageNum, final String requestMark) {
        APPApi.getInstance().service
                .queryCasePanoramaData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PanoramaBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull PanoramaBean panoramaBean) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestFinishEvent() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
