package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.LoginActivity;
import com.ygc.estatedecoration.adapter.MySettingBankCardAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.BankCardBean;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.bean.LoginBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 设置-管理银行卡页面
 */

public class SettingBankCardActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;

    private MySettingBankCardAdapter mAdapter;

    private BasePopupWindow mPopupWindow;

    private List<BankCardBean.DataBean> mData;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的银行卡");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setRightImageResource(R.drawable.tianjia);
        return true;
    }

    @Override
    protected void addListener() {
        //每个条目的点击事件
        mRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showSelectPicPopupWindow(position);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4EBE65")); //设置下拉刷新箭头颜色

        //下拉加载
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                getBankCradData();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mSwipeRefreshLayout.setRefreshing(true);

        mAdapter = new MySettingBankCardAdapter();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerview.setAdapter(mAdapter);
        getBankCradData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview_bg_gray;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft://后退按钮
                finish();
                break;
            case R.id.naviFrameRight://添加银行卡
                Intent intent = new Intent(SettingBankCardActivity.this, SettingAddBankCardActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 解绑银行卡popopwindows
     */
    private void showSelectPicPopupWindow(final int postion) {
        if (mPopupWindow == null) {
            mPopupWindow = new BasePopupWindow(SettingBankCardActivity.this);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(SettingBankCardActivity.this).inflate(R.layout.popup_window_delete_bangcard, null);
            popupView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 解绑银行卡
                    deleteBankeCardByid(mData.get(postion).getBcId());
                    mPopupWindow.dismiss();
                }
            });
            mPopupWindow.setContentView(popupView);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int top = popupView.findViewById(R.id.container_ll).getTop();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top) {
                            mPopupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
        }
        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 解绑银行卡
     *
     * @param bcId 上传的银行卡id
     */
    private void deleteBankeCardByid(String bcId) {
        APPApi.getInstance().service
                .deleteBankeCardByid(bcId)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        String msg = bean.getMsg();
                        showToast(msg);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("Fc_解绑银行卡" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 获取银行卡信息
     */
    public void getBankCradData() {
        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;
        }

        APPApi.getInstance().service
                .getBankCard(UserUtils.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BankCardBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BankCardBean bean) {
                        String msg = bean.getMsg();
                        if ("1".equals(bean.getResponseState())) {
                            mData = bean.getData();
                            mAdapter.setNewData(mData);
                        } else {
                            showToast(msg);
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
                        LogUtil.e("Fc_获取银行卡" + e.getMessage());
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
        getBankCradData();
    }
}
