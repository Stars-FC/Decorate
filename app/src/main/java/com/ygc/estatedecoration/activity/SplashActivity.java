package com.ygc.estatedecoration.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.bean.LoginBean;
import com.ygc.estatedecoration.user_activity.UserHomeActivity;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 欢迎界面
 */

public class SplashActivity extends AutoLayoutActivity {
    public CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        compositeDisposable = new CompositeDisposable();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserUtils.getOnLineBoolean(SplashActivity.this, "")) {
                    toLogin();
                } else {
                    startActivity(new Intent(SplashActivity.this, UserHomeActivity.class));
                    finish();
                }
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    private void toLogin() {
        APPApi.getInstance().service
                .login(UserUtils.getUserName(), UserUtils.getUserPws(), 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginBean roleFindAllBean) {
                        String msg = roleFindAllBean.getMsg();
                        int type = roleFindAllBean.getData().getType();
                        if ("登录成功".equals(msg)) {
                            Intent intent = new Intent();
                            if (type == 0) {
                                //用户端登陆
                                UserUtils.sDataBean = roleFindAllBean.getData();
                                intent.setClass(SplashActivity.this, UserHomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {      //1、2、3、4
                                //服务商端登陆
                                UserUtils.sDataBean = roleFindAllBean.getData();
                                intent.setClass(SplashActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(SplashActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        Toast.makeText(SplashActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        //用户端登陆
                        startActivity(new Intent(SplashActivity.this, UserHomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
