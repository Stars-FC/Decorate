package com.ygc.estatedecoration.activity.login;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.ServiceWeiXinLoginAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 服务商登陆-微信登陆的绑定手机界面
 */
public class ServiceWeiXinLoginActivity extends BaseActivity {


    @BindView(R.id.view_line)
    View view_line;

    @BindView(R.id.ll_weixin)
    LinearLayout ll_weixin;

    @BindView(R.id.tv_service_provider)
    TextView mServiceProvider; //显示服务商类型

    private BasePopupWindow mCheckPopupWindow;
    private BasePopupWindow mCheckMaterialPopupWindow;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("服务商绑定手机");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_service_weixin;
    }

    @OnClick({R.id.naviFrameLeft, R.id.tv_service_provider})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft://后退
                finish();
                break;
            case R.id.tv_service_provider://选择服务商类型
                showSelectPicPopupWindow();
        }
    }

    /**
     * 选择服务商类型popuwindows
     */
    private void showSelectPicPopupWindow() {
        if (mCheckPopupWindow == null) {
            mCheckPopupWindow = new BasePopupWindow(ServiceWeiXinLoginActivity.this);
            mCheckPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(ServiceWeiXinLoginActivity.this).inflate(R.layout.popuwindows_login_register, null);
            popupView.findViewById(R.id.tv_designer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("设计师");
                    mCheckPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.tv_construction).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("施工服务");
                    mCheckPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.tv_supervisor).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("监理");
                    mCheckPopupWindow.dismiss();
                }
            });
            popupView.findViewById(R.id.tv_material).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mServiceProvider.setText("材料商");
                    showMaterialPicPopupWindow();
                    mCheckPopupWindow.dismiss();
                }
            });
            mCheckPopupWindow.setContentView(popupView);
            mCheckPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int top = popupView.findViewById(R.id.ll_popuwindows_register).getTop();
                    int bottm = popupView.findViewById(R.id.ll_popuwindows_register).getBottom();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top || y > bottm) {
                            mCheckPopupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
        }
        mCheckPopupWindow.showAtLocation(ll_weixin, Gravity.CENTER, 0, 0);
    }

    /**
     * 选择材料商类型popuwindows
     */
    private void showMaterialPicPopupWindow() {
        if (mCheckMaterialPopupWindow == null) {

            mCheckMaterialPopupWindow = new BasePopupWindow(ServiceWeiXinLoginActivity.this);
            View popupView = LayoutInflater.from(ServiceWeiXinLoginActivity.this).inflate(R.layout.popup_window_case_style, null);
            RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.style_recyclerview);
            recyclerView.addItemDecoration(new RecyclerSpace(20, Color.parseColor("#f6f6f6")));
            final List list = new ArrayList();
            for (int i = 0; i < 13; i++) {
                list.add("木工" + i);
            }
            recyclerView.setLayoutManager(new GridLayoutManager(ServiceWeiXinLoginActivity.this, 3));
            ServiceWeiXinLoginAdapter adapter = new ServiceWeiXinLoginAdapter(list);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    showToast("position" + list.get(position));
                    mServiceProvider.setText("材料商—" + list.get(position).toString());
                    mCheckMaterialPopupWindow.dismiss();
                }
            });
            mCheckMaterialPopupWindow.setContentView(popupView);
//            mCheckMaterialPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));

        }
        mCheckMaterialPopupWindow.showAsDropDown(view_line);

    }
}
