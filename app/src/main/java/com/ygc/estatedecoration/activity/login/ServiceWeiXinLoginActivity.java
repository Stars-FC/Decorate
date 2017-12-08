package com.ygc.estatedecoration.activity.login;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.CaseStyleAdapter;
import com.ygc.estatedecoration.adapter.ServiceWeiXinLoginAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 服务商登陆-微信登陆的绑定手机界面
 */
public class ServiceWeiXinLoginActivity extends BaseActivity {


    @BindView(R.id.view_line)
    View view_line;

    @BindView(R.id.tv_service_provider)
    TextView mServiceProvider; //显示服务商类型

    private BasePopupWindow mCheckPopupWindow;

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
            View popupView = LayoutInflater.from(ServiceWeiXinLoginActivity.this).inflate(R.layout.popuwindows_login_register, null);
            RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.recyclerview);

            final List list = new ArrayList();
            for (int i = 0; i < 12; i++) {
                list.add("木工" + i);
            }
            recyclerView.setLayoutManager(new GridLayoutManager(ServiceWeiXinLoginActivity.this, 3));
            ServiceWeiXinLoginAdapter adapter = new ServiceWeiXinLoginAdapter(list);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    showToast("position" + list.get(position));
                    mServiceProvider.setText(list.get(position).toString());
                    mCheckPopupWindow.dismiss();
                }
            });
            mCheckPopupWindow.setContentView(popupView);
            mCheckPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        }
        mCheckPopupWindow.showAsDropDown(view_line);

    }
}
