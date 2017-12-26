package com.ygc.estatedecoration.fragment.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.ScheduleAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.bean.ScheduleBean;
import com.ygc.estatedecoration.event.OperateContractMsg;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServiceProgressFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private NeedBean.DataBean mDataBean;

    @BindView(R.id.swiperefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private ScheduleAdapter mScheduleAdapter;

    public static ServiceProgressFragment newInstance(NeedBean.DataBean dataBean) {
        ServiceProgressFragment fragment = new ServiceProgressFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("demand", dataBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null) {
            mDataBean = (NeedBean.DataBean) getArguments().getSerializable("demand");
        }
    }

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void initData(Bundle arguments) {
        getDemandPlan();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mScheduleAdapter = new ScheduleAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mScheduleAdapter);
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
        return R.layout.fragment_service_progress;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void getDemandPlan() {
          APPApi.getInstance().service
                .getDemandPlan(String.valueOf(mDataBean.getDId()), String.valueOf(mDataBean.getCreator().getType()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScheduleBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ScheduleBean scheduleBean) {
                        requestFinish();
                        if (scheduleBean.getResponseState().equals("1")) {
                            List<ScheduleBean.DataBeanX> dataBeanXList = scheduleBean.getData();
                            if (dataBeanXList.size() > 0) {
                                if (dataBeanXList.get(0)!=null) {
                                    EventBus.getDefault().post(new OperateContractMsg());
                                }
                            }
                            mScheduleAdapter.setNewData(dataBeanXList);
                        } else {
                            showToast(scheduleBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        requestFinish();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestFinish() {
        refreshFinishEvent();
    }

    @Override
    public void onRefresh() {
        getDemandPlan();
    }

    private void refreshFinishEvent() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
