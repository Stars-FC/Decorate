package com.ygc.estatedecoration.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.home.InitiatingAcceptanceActivity;
import com.ygc.estatedecoration.activity.home.InitiatingContractActivity;
import com.ygc.estatedecoration.activity.home.ReadInitiatingContractActivity;
import com.ygc.estatedecoration.activity.home.SupplementaryContractActivity;
import com.ygc.estatedecoration.adapter.ScheduleAdapter;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.bean.ScheduleBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.event.ChangeContractStateMsg;
import com.ygc.estatedecoration.event.ContractStateMsg;
import com.ygc.estatedecoration.widget.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServiceProgressFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.schedule_list_view)
    ListView listView;
    private List<ScheduleBean> list = new ArrayList<>();
    private ScheduleAdapter adapter;
    private List<String> titleList = new ArrayList<>();
    private List<String> contentList = new ArrayList<>();
    private List<String> timeList = new ArrayList<>();
    private ScheduleBean scheduleBean;

    private CompositeDisposable compositeDisposable;

    public static ServiceProgressFragment newInstance() {
        ServiceProgressFragment fragment = new ServiceProgressFragment();
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
        EventBus.getDefault().register(this);

        compositeDisposable = new CompositeDisposable(); //RxJava的内存泄露处理
        getDataFromNet(Constant.NORMAL_REQUEST);//获取网络数据

        getData(); //原版获取数据
    }

    /**
     * 原版获取数据及其为适配器赋值
     */
    private void getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        String title[] = {"确认工作", "签署合同", "服务商工作中", "评价"};
        String content[] = {"雇主XXX雇佣了您，赶紧发起合同吧", "您已和雇主签署了合同", "XXXXXXXXXXXXXXXXXXXXXX", ""};
        titleList = Arrays.asList(title);
        contentList = Arrays.asList(content);
        timeList.add(time);
        for (int i = 0; i < 1; i++) {
            ScheduleBean bean = new ScheduleBean();
            bean.setTitle(titleList.get(i));
            bean.setTime(timeList.get(i));
            if (i >= 1) {
                bean.setType("" + (i + 1));
            } else {
                bean.setType("" + i);
            }
            bean.setContent(contentList.get(i));
            list.add(bean);

        }
        adapter = new ScheduleAdapter(mActivity, list);
        listView.setAdapter(adapter);
        if (list != null && list.size() != 0) {
            if (list.get(list.size() - 1).getType().equals("0")) {
                EventBus.getDefault().post(new ContractStateMsg(1));
            } else {
                EventBus.getDefault().post(new ContractStateMsg(2));
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void addListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_service_progress;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (list.get(position).getType().equals("3")) {
            Intent intent = new Intent(mActivity, InitiatingAcceptanceActivity.class);
            intent.putExtra("bean", list.get(position));
            startActivityForResult(intent, 10);
        } else if (list.get(position).getType().equals("2")) {
            Intent intent = new Intent(mActivity, ReadInitiatingContractActivity.class);
            startActivity(intent);
        } else if (list.get(position).getType().equals("1")) {
            startActivityForResult(new Intent(mActivity, SupplementaryContractActivity.class), 12);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeContractStateMsg(ChangeContractStateMsg changeContractStateMsg) {
        Intent data = changeContractStateMsg.getIntent();
        int requestCode = changeContractStateMsg.getRequestCode();
        int resultCode = changeContractStateMsg.getResultCode();

        if (data != null) {
            scheduleBean = new ScheduleBean();
            scheduleBean = (ScheduleBean) data.getSerializableExtra("bean");
            String time = "";
            switch (requestCode) {
                case 10:
                    list.clear();
                    time = data.getStringExtra("time");
                    timeList.add(2, time);

                    for (int i = 0; i < 4; i++) {
                        ScheduleBean bean = new ScheduleBean();
                        bean.setTitle(titleList.get(i));
                        bean.setTime(timeList.get(i));
                        if (i >= 1) {
                            if (i == 2) {
                                bean.setType(scheduleBean.getType());
                            } else {
                                bean.setType("" + (i + 1));
                            }

                        } else {
                            bean.setType("" + i);
                        }
                        bean.setContent(contentList.get(i));

                        list.add(bean);
                    }

                    break;
                case 11:
                    list.clear();
                    time = data.getStringExtra("time");
                    timeList.add(time);
                    for (int i = 0; i < 2; i++) {
                        ScheduleBean bean = new ScheduleBean();
                        bean.setTitle(titleList.get(i));
                        bean.setTime(timeList.get(i));
                        bean.setType("" + i);
                        bean.setContent(contentList.get(i));
                        list.add(bean);
                    }
                    EventBus.getDefault().post(new ContractStateMsg(2));
                    break;
                case 12:
                    list.clear();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss ");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    time = formatter.format(curDate);
                    timeList.add(time);
                    timeList.add(time);
                    for (int i = 0; i < 4; i++) {
                        ScheduleBean bean = new ScheduleBean();
                        bean.setTitle(titleList.get(i));
                        bean.setTime(timeList.get(i));
                        if (i >= 1) {
                            bean.setType("" + (i + 1));

                        } else {
                            bean.setType("" + i);

                        }
                        bean.setContent(contentList.get(i));
                        list.add(bean);
                    }
                    EventBus.getDefault().post(new ContractStateMsg(2));
                    break;
            }
            adapter.notifyDataSetChanged();
        } else {
            return;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        compositeDisposable.clear();
    }

    /**
     * 获取网络数据
     */
    public void getDataFromNet(String request) {
       /* APPApi.getInstance().service
                .queryCasePanoramaData()        //接口方法名
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Base base) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
