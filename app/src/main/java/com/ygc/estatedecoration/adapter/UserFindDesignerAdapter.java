package com.ygc.estatedecoration.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.user_activity.UserLookStoreActivity;

import java.util.List;

public class UserFindDesignerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;
    public UserFindDesignerAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        CircleImageView headIv = helper.getView(R.id.server_head_iv);

        helper.getView(R.id.server_info_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserLookStoreActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
