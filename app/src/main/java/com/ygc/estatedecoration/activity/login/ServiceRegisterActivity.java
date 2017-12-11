package com.ygc.estatedecoration.activity.login;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.ServiceWeiXinLoginAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.RecyclerSpace;
import com.ygc.estatedecoration.utils.ValidationUtil;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务商注册界面
 */
public class ServiceRegisterActivity extends BaseActivity {


    @BindView(R.id.view_line)
    View view_line;//下划线
    @BindView(R.id.login_register)
    LinearLayout login_register;//主布局
    @BindView(R.id.tv_service_provider)
    TextView mServiceProvider; //显示服务商类型

    @BindView(R.id.service_et_photo)
    EditText mServiceEtPhoto;//手机号
    @BindView(R.id.service_et_nickname)
    EditText mServiceEtNickname;//昵称
    @BindView(R.id.service_et_psw)
    EditText mServiceEtPsw;//密码
    @BindView(R.id.service_et_againpsw)
    EditText mServiceEtAgainpsw;//确认密码
    @BindView(R.id.service_et_verification)
    EditText mServiceEtVerification;//验证码
    @BindView(R.id.service_tv_get_verification)
    Button mServiceTvGetVerification;//验证码按钮

    private BasePopupWindow mCheckPopupWindow;
    private BasePopupWindow mCheckMaterialPopupWindow;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("服务商注册");
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
        return R.layout.login_service_register;
    }

    @OnClick({R.id.naviButtonLeft, R.id.tv_service_provider, R.id.service_tv_get_verification, R.id.bt_register_login,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviButtonLeft://后退
                finish();
                break;
            case R.id.tv_service_provider://选择服务商类型
                showSelectPicPopupWindow();
                break;
            case R.id.service_tv_get_verification://获取验证码
                getNetVerification();
                break;
            case R.id.bt_register_login://注册完成
                break;
        }
    }

    /**
     * 选择服务商类型popuwindows
     */
    private void showSelectPicPopupWindow() {
        if (mCheckPopupWindow == null) {
            mCheckPopupWindow = new BasePopupWindow(ServiceRegisterActivity.this);
            mCheckPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(ServiceRegisterActivity.this).inflate(R.layout.popuwindows_login_register, null);
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
        mCheckPopupWindow.showAtLocation(login_register, Gravity.CENTER, 0, 0);
    }

    /**
     * 选择材料商类型popuwindows
     */
    private void showMaterialPicPopupWindow() {
        if (mCheckMaterialPopupWindow == null) {

            mCheckMaterialPopupWindow = new BasePopupWindow(ServiceRegisterActivity.this);
            View popupView = LayoutInflater.from(ServiceRegisterActivity.this).inflate(R.layout.popup_window_case_style, null);
            RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.style_recyclerview);
            recyclerView.addItemDecoration(new RecyclerSpace(20, Color.parseColor("#f6f6f6")));
            final List list = new ArrayList();
            for (int i = 0; i < 13; i++) {
                list.add("木工" + i);
            }
            recyclerView.setLayoutManager(new GridLayoutManager(ServiceRegisterActivity.this, 3));
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


    /**
     * 获取验证码
     */
    public void getNetVerification() {

        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;//判断网络状态，没有网络直接返回不想下执行
        }

        String photoNum = MyPublic.getText(mServiceEtVerification);
        if (TextUtils.isEmpty(photoNum)) {
            showToast("请填写手机号");
            return;
        }

        if (!ValidationUtil.isPhone(photoNum)) {
            showToast("手机号格式错误");
            return;
        }

        MyPublic.getVerification(ServiceRegisterActivity.this, photoNum, mServiceTvGetVerification);
    }

    // TODO: 2017/12/11  获取材料商什么
    public void getNetRole() {
    }
}
