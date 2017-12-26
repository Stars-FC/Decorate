package com.ygc.estatedecoration.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.bean.UserAddressDataListBean;
import com.ygc.estatedecoration.entity.base.Base;
import com.ygc.estatedecoration.user_activity.EditAddressActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddressAdapter extends BaseQuickAdapter<UserAddressDataListBean.DataBean, BaseViewHolder> {
    private LinearLayout content_layout;
    private BasePopupWindow delAddressPopupWindow;
    private int position;
    public AddressAdapter(LinearLayout content_layout) {
        super(R.layout.item_add_address);
        this.content_layout = content_layout;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final UserAddressDataListBean.DataBean item) {
        ((TextView)helper.getView(R.id.name_tv)).setText(item.getUserName());
        ((TextView)helper.getView(R.id.phone_number_tv)).setText(item.getUserMobile());
        ((TextView)helper.getView(R.id.address_details_tv)).setText(item.getProvince()+item.getDetail());
        ImageView defaultAddressIconIv = helper.getView(R.id.default_address_icon_iv);
        int defau = item.getDefau();
        if (defau == 1) {
            defaultAddressIconIv.setImageResource(R.drawable.xuanzhong_address);
        } else {
            defaultAddressIconIv.setImageResource(R.drawable.weixuanzhong);
        }
        helper.getView(R.id.set_default_address_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultAddressEvent(helper.getLayoutPosition());
            }
        });

        LinearLayout editAddressLl = helper.getView(R.id.edit_address_ll);
        LinearLayout delAddressLl = helper.getView(R.id.del_address_ll);

        editAddressLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditAddressActivity.class);
                intent.putExtra(EditAddressActivity.OPERATE_ADDRESS, 2);
                intent.putExtra("dataBean", item);
                mContext.startActivity(intent);
            }
        });

        delAddressLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = helper.getLayoutPosition();
                showDelAddressPopupWindow();
            }
        });
    }

    private void setDefaultAddressEvent(final int position) {
        final List<UserAddressDataListBean.DataBean> dataBeanList = getData();
        final UserAddressDataListBean.DataBean item = dataBeanList.get(position);
        APPApi.getInstance().service
                .modifyUserDefaultAddress(item.getAId(), item.getAuId())
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
                            for (int i = 0; i < dataBeanList.size(); i++) {
                                UserAddressDataListBean.DataBean dataBean = dataBeanList.get(i);
                                if (position == i) {
                                    dataBean.setDefau(1);
                                } else {
                                    dataBean.setDefau(0);
                                }
                            }
                            remove(position);
                            addData(0, item);
                            notifyDataSetChanged();
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

    private void showDelAddressPopupWindow() {
        if (delAddressPopupWindow == null) {
            initDelAddressPopupWindow();
        }
        delAddressPopupWindow.showAtLocation(content_layout, Gravity.CENTER, 0, 0);
    }

    private void initDelAddressPopupWindow() {
        delAddressPopupWindow = new BasePopupWindow(mContext);
        delAddressPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup_window_del_address, null);
        final LinearLayout content_layout = (LinearLayout) popupView.findViewById(R.id.content_layout);
        popupView.findViewById(R.id.sure_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAddressPopupWindow.dismiss();
                sureDeleteEvent();

            }
        });
        popupView.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAddressPopupWindow.dismiss();
            }
        });
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int top = content_layout.getTop();
                int bottom = content_layout.getBottom();
                int left = content_layout.getLeft();
                int right = content_layout.getRight();
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < top || y > bottom || x < left || x > right) {
                        delAddressPopupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        delAddressPopupWindow.setContentView(popupView);
        delAddressPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
    }

    private void sureDeleteEvent() {
        String aId = getData().get(position).getAId();
        APPApi.getInstance().service
                .deleteUserAddress(aId)
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
                            remove(position);
                            notifyDataSetChanged();
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
