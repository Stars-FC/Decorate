package com.ygc.estatedecoration.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.bean.DemandOfferBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.widget.CircleImageView;

/**
 * Created by FC on 2017/11/14.
 * 主页-交易管理-报价页面的RecyclerView的适配器
 */

public class HomeTransactionManageOfferAdapter extends BaseQuickAdapter<DemandOfferBean.DataBean,BaseViewHolder> {

    public HomeTransactionManageOfferAdapter() {
        super(R.layout.item_progress);
    }

    @Override
    protected void convert(BaseViewHolder helper, DemandOfferBean.DataBean item) {

        CircleImageView circleImageView = helper.getView(R.id.head_iv);
        TextView nicknameTv = helper.getView(R.id.nickname_tv);
        TextView dateTv = helper.getView(R.id.date_tv);
        TextView moneyTv = helper.getView(R.id.offer_tv);
        TextView consumeTimeTv = helper.getView(R.id.consume_time_tv);
        TextView msgTv = helper.getView(R.id.liuyan_tv);

        DemandOfferBean.DataBean.CreatorBean creator = item.getCreator();
        String head_portrait = creator.getHead_portrait();
        if (!TextUtils.isEmpty(head_portrait)) {
            Glide.with(mContext).load(Constant.BASE_IMG + head_portrait).into(circleImageView);
        } else {
            circleImageView.setImageResource(R.drawable.touxiangxuqiubaojia);
        }
        String nickname = creator.getNickname();
        nicknameTv.setText(nickname);

        dateTv.setText(item.getCreateTime().substring(0, 10));


        RelativeLayout lookProgressRl = helper.getView(R.id.look_progress_rl);
        lookProgressRl.setVisibility(View.GONE);

        LinearLayout infoLl = helper.getView(R.id.info_ll);
        int toOpen = item.getToOpen();
        if (toOpen == 0) {
            infoLl.setVisibility(View.GONE);
        } else {
            infoLl.setVisibility(View.VISIBLE);
        }
        int price = item.getPrice();
        int needTime = item.getNeedTime();
        moneyTv.setText(price + "元");
        consumeTimeTv.setText(needTime + "天");

        msgTv.setText(item.getMessage());
    }
}
