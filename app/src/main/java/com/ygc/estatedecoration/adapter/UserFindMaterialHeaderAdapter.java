package com.ygc.estatedecoration.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;

import java.util.List;

public class UserFindMaterialHeaderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public UserFindMaterialHeaderAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iconIv = helper.getView(R.id.icon_iv);
        TextView titleTv = helper.getView(R.id.title_tv);
        int layoutPosition = helper.getLayoutPosition();
        if (layoutPosition == 0) {
            iconIv.setImageResource(R.drawable.mugong);
            titleTv.setText("木工");
        } else if (layoutPosition == 1) {
            iconIv.setImageResource(R.drawable.nigong);
            titleTv.setText("泥工");
        } else if (layoutPosition == 2) {
            iconIv.setImageResource(R.drawable.shuidiangong);
            titleTv.setText("水电工");
        }else if (layoutPosition == 3) {
            iconIv.setImageResource(R.drawable.qigong);
            titleTv.setText("漆工");
        }else if (layoutPosition == 4) {
            iconIv.setImageResource(R.drawable.anzhuanggong);
            titleTv.setText("安装工");
        }else if (layoutPosition == 5) {
            iconIv.setImageResource(R.drawable.zagong);
            titleTv.setText("杂工");
        }else if (layoutPosition == 6) {
            iconIv.setImageResource(R.drawable.boli);
            titleTv.setText("玻璃");
        }else if (layoutPosition == 7) {
            iconIv.setImageResource(R.drawable.menchuang);
            titleTv.setText("门窗");
        }else if (layoutPosition == 8) {
            iconIv.setImageResource(R.drawable.kongtiao);
            titleTv.setText("空调");
        }else if (layoutPosition == 9) {
            iconIv.setImageResource(R.drawable.dinuan);
            titleTv.setText("地暖");
        }else if (layoutPosition == 10) {
            iconIv.setImageResource(R.drawable.dengju);
            titleTv.setText("灯具");
        }else if (layoutPosition == 11) {
            iconIv.setImageResource(R.drawable.jieju);
            titleTv.setText("洁具");
        }else if (layoutPosition == 12) {
            iconIv.setImageResource(R.drawable.ruanbao);
            titleTv.setText("软装");
        }else if (layoutPosition == 13) {
//            iconIv.setImageResource();
            titleTv.setText("预留");
        }
    }
}
