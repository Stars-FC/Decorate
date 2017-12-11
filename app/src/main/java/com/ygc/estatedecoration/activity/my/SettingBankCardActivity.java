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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.MySettingBankCardAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
//                showToast("mRecyclerview第" + position + "数据");
                showSelectPicPopupWindow();
            }
        });

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
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("" + i);
        }
        mAdapter = new MySettingBankCardAdapter(list);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerview.setAdapter(mAdapter);
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
     * 显示选择图片popopwindows
     */
    private void showSelectPicPopupWindow() {
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

}
