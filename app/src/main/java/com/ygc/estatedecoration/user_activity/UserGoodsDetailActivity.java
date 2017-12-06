package com.ygc.estatedecoration.user_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
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
    protected void initView() {
        //设置状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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

    @OnClick({R.id.iv_back, R.id.tv_offer, R.id.ll_add_shoppingcart})
    public void onClickEvent(View view) {
        Intent intent = new Intent();
        if (view != null) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.ll_add_shoppingcart://加入购物车
                    showAddShoppingCartPopupWindow();
                    break;
                case R.id.tv_offer://立即购买
                    intent.setClass(UserGoodsDetailActivity.this, ConfirmBuyActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    private void showAddShoppingCartPopupWindow() {
        if (mPopupWindow == null) {
            mPopupWindow = new BasePopupWindow(UserGoodsDetailActivity.this);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(UserGoodsDetailActivity.this).inflate(R.layout.popup_window_add_shoppingcart, null);

            mFlowLayoutColor = (FlowLayout) popupView.findViewById(R.id.flow_layout_one);
            mFlowLayoutSize = (FlowLayout) popupView.findViewById(R.id.flow_layout_two);


            popupView.findViewById(R.id.tv_true).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
            //显示分类内容
            getData(colorList, mFlowLayoutColor);
            getData(sizeList, mFlowLayoutSize);

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
    public void getData(List<String> list, FlowLayout flowLayout) {
        // 循环添加TextView到容器
        for (int i = 0; i < list.size(); i++) {
            mTv = (TextView) LayoutInflater.from(UserGoodsDetailActivity.this).inflate(
                    R.layout.item_user_search, flowLayout, false);
            mTv.setText(list.get(i));
            final String str = mTv.getText().toString();
            //点击事件
            mTv.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    //加入搜索历史纪录记录
                    Toast.makeText(UserGoodsDetailActivity.this, str, Toast.LENGTH_LONG).show();
                }
            });
            flowLayout.addView(mTv);
        }
    }
}
