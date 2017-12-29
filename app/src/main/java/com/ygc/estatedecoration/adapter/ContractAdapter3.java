package com.ygc.estatedecoration.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.bean.ScheduleBean;
import com.ygc.estatedecoration.entity.base.Base;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ContractAdapter3 extends BaseQuickAdapter<ScheduleBean.DataBeanX.ReplenishContractListBean, BaseViewHolder> {

    List<ScheduleBean.DataBeanX.ReplenishContractListBean> data;
    public ContractAdapter3(@Nullable List<ScheduleBean.DataBeanX.ReplenishContractListBean> data) {
        super(R.layout.item_contract_stage, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ScheduleBean.DataBeanX.ReplenishContractListBean item) {
        ((TextView) helper.getView(R.id.time_tv)).setText(item.getCreateTime());
        ((TextView)helper.getView(R.id.content_tv)).setText(item.getDetail());
        TextView operateContractTv = helper.getView(R.id.operate_contract_tv);
        final String rcId = item.getRcId();
        final int rcState = item.getRcState();
        if (rcState == 0) {
            operateContractTv.setText("发起验收");
        } else if (rcState == 1) {
            operateContractTv.setText("提醒验收");
        } else if (rcState == 2) {
            operateContractTv.setText("用户已验收");
            operateContractTv.setBackgroundResource(R.drawable.shape_hollow_rounded_rectangle2);
        }
        operateContractTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rcState == 0) {
                    verificationEvent(item, rcId, rcState);
                } else if (rcState == 1) {
                    verificationEvent(item, rcId, rcState);
                }
            }
        });
    }

    private void verificationEvent(final ScheduleBean.DataBeanX.ReplenishContractListBean item, String consId, final int csState) {
        APPApi.getInstance().service
                .modifyBuChongContractState(consId, String.valueOf(csState))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Base base) {
                        Toast.makeText(mContext, base.msg, Toast.LENGTH_SHORT).show();
                        if (base.responseState.equals("1")) {
                            if (csState == 0) {
                                item.setRcState(1);
                                notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
