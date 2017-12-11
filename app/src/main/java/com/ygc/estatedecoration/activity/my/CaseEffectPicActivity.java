package com.ygc.estatedecoration.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.adapter.UploadEffectPicAdapter;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.utils.PictureCompressUtil;
import com.ygc.estatedecoration.widget.TitleBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

public class CaseEffectPicActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private UploadEffectPicAdapter adapter;
    private ArrayList<String> picList = new ArrayList<>();

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setLeftImageResource(R.drawable.fanhui);
        bar.setTitleText("上传效果图");
        bar.setRightText("上传");
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new UploadEffectPicAdapter(R.layout.item_upload_effect_pic, picList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_effect_pic;
    }

    @OnClick({R.id.naviFrameLeft, R.id.naviFrameRight, R.id.upload_iv})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.naviFrameRight:
                    uploadEvent();
                    break;
                case R.id.upload_iv:
                    selectPicEvent();
                    break;
            }
        }
    }

    private void selectPicEvent() {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(CaseEffectPicActivity.this, PhotoPicker.REQUEST_CODE);
    }

    private void uploadEvent() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                handleUploadPicEvent(photos);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if (grantResults[0] == 0) {
                    selectPicEvent();
                }
                break;
        }
    }

    private void handleUploadPicEvent(ArrayList<String> photos) {
        PictureCompressUtil.getInstance().startCompress(this, photos, new PictureCompressUtil.CompressedPicResultCallBack() {
            @Override
            public void showResult(List<String> photos, List<File> list) {
                picList.addAll(photos);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
