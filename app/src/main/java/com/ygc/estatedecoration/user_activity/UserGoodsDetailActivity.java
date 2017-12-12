package com.ygc.estatedecoration.user_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintJob;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.ProductParamsAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.FlowLayout;
import com.ygc.estatedecoration.widget.TitleBar;

import butterknife.BindView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FC on 2017/11/28.
 * 商品详情页面
 */

public class UserGoodsDetailActivity extends BaseActivity {

    @BindView(R.id.commodity_detail_tv)
    TextView mTv_commodityDetail;


    @BindView(R.id.rl_goods_detail)
    RelativeLayout rl_goods_detail;

    private BasePopupWindow mPopupWindow;

    /**
     * 显示的两个分类
     */
    private List<String> colorList = new ArrayList<>();
    private List<String> sizeList = new ArrayList<>();
    private FlowLayout mFlowLayoutColor;
    private FlowLayout mFlowLayoutSize;
    private TextView mTv;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {}

    @Override
    protected void initData(Bundle savedInstanceState) {
        ImmersionBar.with(this).fitsSystemWindows(false).transparentStatusBar().statusBarAlpha(0.5f).init();
        setTxtVFirstLineRetract();
    }

    private void setTxtVFirstLineRetract() {//设置TextView首行缩进
        SpannableStringBuilder span = new SpannableStringBuilder("缩进" + mTv_commodityDetail.getText());
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTv_commodityDetail.setText(span);
        colorList.add("白色");
        colorList.add("黑色");
        colorList.add("彩色");
        colorList.add("红色");
        sizeList.add("100x123");
        sizeList.add("50x50");
        sizeList.add("130x90");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_goods_detail2;
    }

    @OnClick({R.id.iv_back, R.id.tv_offer, R.id.ll_add_shoppingcart, R.id.production_params_ll})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.ll_add_shoppingcart://加入购物车
                    showAddShoppingCartPopupWindow(0);
                    break;
                case R.id.tv_offer://立即购买
                    showAddShoppingCartPopupWindow(1);

                    break;
                case R.id.production_params_ll://产品参数
                    showProductParamsInfo();
                    break;
            }
        }
    }

    private BasePopupWindow productParamsPopupWindow;
    private void showProductParamsInfo() {
        if (productParamsPopupWindow == null) {
            initProductParamsPopupWindow();
        }
        productParamsPopupWindow.showAtLocation(rl_goods_detail, Gravity.BOTTOM, 0, 0);
    }

    private void initProductParamsPopupWindow() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add("heh");
        }
        productParamsPopupWindow = new BasePopupWindow(this);
        productParamsPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View popView = getLayoutInflater().inflate(R.layout.popup_window_product_params, null);
        RecyclerView recyclerView = (RecyclerView) popView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ProductParamsAdapter(R.layout.item_product_params, dataList));
        popView.findViewById(R.id.sure_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productParamsPopupWindow.dismiss();
            }
        });
        final LinearLayout content_layout = (LinearLayout) popView.findViewById(R.id.content_layout);
        popView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = (int) event.getY();
                int top = content_layout.getTop();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < top) {
                        productParamsPopupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        productParamsPopupWindow.setContentView(popView);
        productParamsPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));

    }

    private void showAddShoppingCartPopupWindow(final int mark) {
        if (mPopupWindow == null) {
            mPopupWindow = new BasePopupWindow(UserGoodsDetailActivity.this);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(UserGoodsDetailActivity.this).inflate(R.layout.popup_window_add_shoppingcart, null);

            mFlowLayoutColor = (FlowLayout) popupView.findViewById(R.id.flow_layout_one);
            mFlowLayoutSize = (FlowLayout) popupView.findViewById(R.id.flow_layout_two);
            popupView.findViewById(R.id.close_rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });

            popupView.findViewById(R.id.tv_true).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                    if (mark == 1) {//立即购买
                        Intent instanceBuyIntent = new Intent();
                        instanceBuyIntent.setClass(UserGoodsDetailActivity.this, ConfirmBuyActivity.class);
                        startActivity(instanceBuyIntent);
                    } else if (mark == 0) {//加入购物车

                    }
                }
            });
            //显示分类内容
            getData(colorList, mFlowLayoutColor, "color");
            getData(sizeList, mFlowLayoutSize, "size");

            mPopupWindow.setContentView(popupView);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int top = popupView.findViewById(R.id.container_rl).getTop();
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
        mPopupWindow.showAtLocation(rl_goods_detail, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 显示流式标签
     *
     * @param list
     * @param flowLayout
     */
    public void getData(final List<String> list, final FlowLayout flowLayout, final String mark) {
        // 循环添加TextView到容器
        for (int i = 0; i < list.size(); i++) {
            mTv = (TextView) LayoutInflater.from(UserGoodsDetailActivity.this).inflate(
                    R.layout.item_user_search, flowLayout, false);
            mTv.setText(list.get(i));
            final String str = mTv.getText().toString();
            //点击事件
            final int finalI = i;
            mTv.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    if (mark.equals("color")) {
                        for (int j = 0; j < mFlowLayoutColor.getChildCount(); j++) {
                            if (finalI == j) {
                                mTv.setBackgroundResource(R.drawable.shape_all_bt);
                            } else {
                                mTv.setBackgroundResource(R.drawable.bg_user_search_item);
                            }
                        }
                    } else {
                        for (int j = 0; j < mFlowLayoutSize.getChildCount(); j++) {
                            if (finalI == j) {
                                mTv.setBackgroundResource(R.drawable.shape_all_bt);
                            } else {
                                mTv.setBackgroundResource(R.drawable.bg_user_search_item);
                            }
                        }
                    }

                }
            });
            flowLayout.addView(mTv);
        }
    }
}
