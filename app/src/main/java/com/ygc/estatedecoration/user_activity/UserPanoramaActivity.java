package com.ygc.estatedecoration.user_activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.gyf.barlibrary.ImmersionBar;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.UserCasePanoramaDetailBean;
import com.ygc.estatedecoration.entity.base.Constant;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.TitleBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPanoramaActivity extends BaseActivity {

    private VrPanoramaView mVrPanoramaView;
    private ImagerLoaderTask mImagerLoaderTask;
    private int mCp_id;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        return false;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        initVrPaNormalView();
    }

    //初始化VR图片
    private void initVrPaNormalView() {
        mVrPanoramaView = (VrPanoramaView) findViewById(R.id.mVrPanoramaView);
//        paNormalOptions = new VrPanoramaView.Options();
//        paNormalOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        mVrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
        mVrPanoramaView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
        mVrPanoramaView.setStereoModeButtonEnabled(false); //设置隐藏立体模型的按钮
//        mVrPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_STEREO);
        mVrPanoramaView.setEventListener(new ActivityEventListener()); //设置监听
        //加载本地的图片源
//        mVrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes), paNormalOptions);
        //设置网络图片源
//        mVrPanoramaView.loadImageFromByteArray(, paNormalOptions);
    }

    private class ImagerLoaderTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            String url = params[0];
            URLConnection urlConnection;
            InputStream is;
            try {
                urlConnection = new URL(url).openConnection();
                is = urlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            cancelDialog();
            mVrPanoramaView.setVisibility(View.VISIBLE);
            //创建VrPanoramaView.options，去决定显示VR是普通效果还是立体效果；
            VrPanoramaView.Options options = new VrPanoramaView.Options();
            //TYPE_STEREO_OVER_UNDER立体效果，就是图片的上半部分放在左眼显示，下半部分放在右眼显示；
            //TYPE_MONO 普通效果；
            options.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
            //使用VR控件对象，显示效果           需要两个参数1.Bitmap对象2.VrPanoramaView.options；
            mVrPanoramaView.loadImageFromBitmap(bitmap, options);
            super.onPostExecute(bitmap);
        }
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {//图片加载成功
        }


        @Override
        public void onLoadError(String errorMessage) {//图片加载失败
        }

        @Override
        public void onClick() {//当我们点击了VrPanoramaView 时候触发            super.onClick();
            Log.i("521", "onClick: 哈哈哈哈");
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            //改变显示模式时候出发（全屏模式和纸板模式）
            super.onDisplayModeChanged(newDisplayMode);
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ImmersionBar.with(this).fitsSystemWindows(false).transparentStatusBar().init();
        getIntentData();
        getPanoramaDetailData();
    }

    private void getIntentData() {
        mCp_id = getIntent().getIntExtra("cp_id", -1);
    }

    private void getPanoramaDetailData() {
        showDialog();
        APPApi.getInstance().service
                .getCasePanoramaDetailData(String.valueOf(mCp_id), String.valueOf(UserUtils.sDataBean.getAu_id()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCasePanoramaDetailBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull UserCasePanoramaDetailBean userCasePanoramaDetailBean) {
                        cancelDialog();
                        if (userCasePanoramaDetailBean.responseState.equals("1")) {
                            String dynamic_picture = userCasePanoramaDetailBean.getData().getDynamic_picture();
                            mImagerLoaderTask = new ImagerLoaderTask();
                            mImagerLoaderTask.execute(Constant.BASE_IMG + dynamic_picture);
                        } else {
                            showToast(userCasePanoramaDetailBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_panorama;
    }

    @Override
    protected void onPause() {
        mVrPanoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mVrPanoramaView.resumeRendering();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mVrPanoramaView.shutdown();
        if(mImagerLoaderTask!=null){
            //在退出Activity时，如果异步任务没有取消，就需要
            if(!mImagerLoaderTask.isCancelled()){
                mImagerLoaderTask.cancel(true);
            }
        }
        super.onDestroy();
    }

    @OnClick({R.id.iv_back})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
            }
        }
    }
}
