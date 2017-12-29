package com.ygc.estatedecoration.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.ChangeStoreActivity;
import com.ygc.estatedecoration.adapter.HomeMyStoreAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.bean.MyStoreBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.fragment.home.MyStoreBrightFragment;
import com.ygc.estatedecoration.fragment.home.MyStoreEvaluateFragment;
import com.ygc.estatedecoration.fragment.home.MyStoreInformationFragment;
import com.ygc.estatedecoration.fragment.home.MyStorePersonalCaseFragment;
import com.ygc.estatedecoration.user_fragment.UserEvaluateFragment;
import com.ygc.estatedecoration.user_fragment.UserInformationFragment;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.CircleImageView;
import com.ygc.estatedecoration.widget.StarView;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FC on 2017/11/14.
 * 主页-我的店铺页面
 */

public class UserLookStoreActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    XTabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.rl_background)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.mystore_icon)
    CircleImageView mMystoreIcon;//头像
    @BindView(R.id.mystore_name)
    TextView mMystoreName;//店铺名称
    @BindView(R.id.mystore_sex)
    ImageView mMystoreSex;//性别
    @BindView(R.id.mystore_type)
    TextView mMystoreType;//类型（个体、团体）
    @BindView(R.id.mystore_starview)
    StarView mMystoreStarview;//星星（设置个数）
    @BindView(R.id.mystore_place)
    TextView mMystorePlace;//地点
    @BindView(R.id.mystore_number)
    TextView mMystoreNumber;//成交量
    @BindView(R.id.mystore_work_time)
    TextView mMystoreWorkTime;//工作经验
    @BindView(R.id.mystore_style)
    TextView mMystoreStyle;//擅长风格

    private HomeMyStoreAdapter mAdapter;

    private String mStoreId;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        getDataMyStore();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ImmersionBar.with(this).fitsSystemWindows(false).transparentStatusBar().init();

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new UserInformationFragment());
        fragments.add(new UserEvaluateFragment());
        fragments.add(MyStorePersonalCaseFragment.newInstance());
        fragments.add(new MyStoreBrightFragment());

        List<String> list = new ArrayList<>();
        list.add("店铺信息");
        list.add("评价信用");
        list.add("案例");
        list.add("亮点");

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new HomeMyStoreAdapter(fragmentManager, fragments, list);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        getNetdoVisited();//访客访问向服务器提交数据
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_look_service_store;
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish(); //后退
                break;
        }
    }

    /**
     * 访客
     */
    public void getNetdoVisited() {

        // TODO: 2017/12/28  "9" --被访问者id（需要在主页面获取到被访问者id）
        APPApi.getInstance().service
                .doVisited(UserUtils.getUserId(), "9")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        String msg = bean.getMsg();
//                        showToast(msg);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("FC_访问" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 查询店铺信息
     */
    public void getDataMyStore() {
        APPApi.getInstance().service
                .myStore(UserUtils.getUserId())
//                .myStore("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyStoreBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MyStoreBean myStoreBean) {
                        String msg = myStoreBean.getMsg();
                        String state = myStoreBean.getResponseState();
                        if ("1".equals(state)) {
                            setText(myStoreBean);
                        } else {
                            showToast(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(getResources().getString(R.string.network_error));
                        LogUtil.e("FC_我的店铺" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 为控件赋值
     *
     * @param bean
     */
    public void setText(MyStoreBean bean) {


        EventBus.getDefault().post(bean);

        // TODO: 2017/12/26  ，星星个数，擅长风格
       /*
        @BindView(R.id.mystore_starview)
        StarView mMystoreStarview;//星星（设置个数）*/

        //获取带店铺Id
        mStoreId = String.valueOf(bean.getData().getS_id());

        UserUtils.setParam(UserUtils.STOREID, "storeId", mStoreId);

        String r_picture = Constant.BASE_IMG + bean.getData().getS_logo();
        Glide.with(UserLookStoreActivity.this)
                .load(r_picture)
                .placeholder(R.drawable.iv_error)
                .error(R.drawable.iv_error)
                .dontAnimate()
                .into(mMystoreIcon);
        mMystoreName.setText(bean.getData().getS_name());
        mMystoreType.setText(bean.getData().getS_type());
        mMystorePlace.setText(bean.getData().getS_province() + " " + bean.getData().getS_city());
        mMystoreNumber.setText("成交量" + bean.getData().getTurnover());
        mMystoreWorkTime.setText(bean.getData().getWork_experience() + "年工作经验");
        mMystoreStyle.setText(bean.getData().getRinfo().getR_name());
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        getDataMyStore();//从新获取数据
    }

}
