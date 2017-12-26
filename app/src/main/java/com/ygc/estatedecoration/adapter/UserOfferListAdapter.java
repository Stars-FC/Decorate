package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.bean.DemandOfferBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.user_activity.UserProjectProgressActivity;
import com.ygc.estatedecoration.widget.CircleImageView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserOfferListAdapter extends BaseQuickAdapter<DemandOfferBean.DataBean, BaseViewHolder> {

    public UserOfferListAdapter() {
        super(R.layout.item_progress);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DemandOfferBean.DataBean item) {
        final int layoutPosition = helper.getLayoutPosition();
        CircleImageView headIv = helper.getView(R.id.head_iv);
        String head_portrait = item.getCreator().getHead_portrait();
        if (TextUtils.isEmpty(head_portrait)) {
            headIv.setImageResource(R.drawable.touxiangxuqiubaojia);
        } else {
            Glide.with(mContext).load(Constant.BASE_IMG + head_portrait).into(headIv);
        }
        helper.setText(R.id.nickname_tv, item.getCreator().getNickname());
        helper.setText(R.id.date_tv, item.getCreateTime().substring(0, 10));
        helper.setText(R.id.offer_tv, item.getPrice() + "(元)");
        helper.setText(R.id.consume_time_tv, item.getNeedTime() + "(天)");
        helper.setText(R.id.liuyan_tv, item.getMessage());
        TextView btnEventTv = helper.getView(R.id.btn_event_tv);
        final int beChecked = item.getBeChecked();
        if (beChecked == 0) {
            btnEventTv.setText("确认选标");
        } else {
            btnEventTv.setText("查看进度");
        }
        final String dId = item.getDId();
        final String doId = item.getDoId();
        final int type = item.getCreator().getType();
        btnEventTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beChecked == 0) {
                    sureSelectedBiaoEvent(dId, doId, type, layoutPosition);//选标
                } else if (beChecked == 1) {//查看进度
                    Intent intent = new Intent(mContext, UserProjectProgressActivity.class);
                    intent.putExtra("dataBean", item);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private void sureSelectedBiaoEvent(String dId, String doId, int type, final int mLayoutPosition) {
        APPApi.getInstance().service
                .demandSelected(dId, doId, type)
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
                            getData().get(mLayoutPosition).setBeChecked(1);
                            notifyItemChanged(mLayoutPosition);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
