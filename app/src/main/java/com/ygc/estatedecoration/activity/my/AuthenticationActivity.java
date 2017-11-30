package com.ygc.estatedecoration.activity.my;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ygc.estatedecoration.BuildConfig;
import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by FC on 2017/11/15.
 * 我的-实名认证页面
 */

public class AuthenticationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.parent_layout)
    LinearLayout mLl_parentLayout;

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("实名认证");
        bar.setLeftImageResource(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_authentication;
    }

    @OnClick({R.id.naviFrameLeft,R.id.button,R.id.add_ic_iv})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft:
                    finish();
                    break;
                case R.id.add_ic_iv:
                    addICEvent();
                    break;
                case R.id.button:
                    showToast("认证");
                    break;
            }
        }
    }

    private void addICEvent() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA) || EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showSelectPicPopupWindow();
        } else {
            cameraReadAndWriteTask();
        }
    }

    private BasePopupWindow mSelectPicPopupWindow;

    private void showSelectPicPopupWindow() {
        if (mSelectPicPopupWindow == null) {
            mSelectPicPopupWindow = new BasePopupWindow(this);
            mSelectPicPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_select_pic, null);
            popupView.findViewById(R.id.take_photo_tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectPicPopupWindow.dismiss();
                    if (hasCameraPermission()) {
                        takeCamera();
                    } else {
                        cameraTask();
                    }
                }
            });
            popupView.findViewById(R.id.local_tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectPicPopupWindow.dismiss();
                    if (hasReadAndWritePermission()) {
                        takePhoto();
                    } else {
                        readAndWriteTask();
                    }
                }
            });
            popupView.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectPicPopupWindow.dismiss();
                }
            });
            mSelectPicPopupWindow.setContentView(popupView);
            mSelectPicPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int top = popupView.findViewById(R.id.container_ll).getTop();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top) {
                            mSelectPicPopupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
        }
        mSelectPicPopupWindow.showAtLocation(mLl_parentLayout, Gravity.BOTTOM, 0, 0);
    }

    private static final int TAKE_PHOTO_REQUEST_CODE = 1;
    private static final int TAKE_CAMERA_REQUEST_CODE = 2;
    private static final int PHOTO_CLIP = 3;
    //选择本地图片
    private void takePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }

    //调起照相机拍照
    private void takeCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "ic_dibiao.jpg")));
        startActivityForResult(intent, TAKE_CAMERA_REQUEST_CODE);
    }

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }

    //申请所有需要的权限
    private static final int RC_CAMERA_READ_WRITE_PERM = 123;
    private static final int RC_CAMERA_PERM = 124;
    private static final int RC_READ_WRITE_PERM = 125;

    @AfterPermissionGranted(RC_CAMERA_READ_WRITE_PERM)
    public void cameraReadAndWriteTask() {
        if (!hasReadAndWritePermission() && !hasCameraPermission()) {
            EasyPermissions.requestPermissions(this, "是否允许获得您的相机、存储空间使用权限？", RC_CAMERA_READ_WRITE_PERM, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        if (!hasCameraPermission()) {
            EasyPermissions.requestPermissions(this, "是否允许获得您的相机使用权限？", RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    @AfterPermissionGranted(RC_READ_WRITE_PERM)
    public void readAndWriteTask() {
        if (!hasReadAndWritePermission()) {
            EasyPermissions.requestPermissions(this, "是否允许获得您的存储空间使用权限？", RC_READ_WRITE_PERM, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    private boolean hasReadAndWritePermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        showSelectPicPopupWindow();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {//再次被拒绝会弹出提示框
            String alertMsg = "";
            if (perms.size() == 2) {
                alertMsg = "未获得您的相机、存储空间使用权限，此功能无法使用。请前往应用权限设置打开权限。";
            } else {
                String permissionName = perms.get(0);
                if (permissionName.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    alertMsg = "未获得您的存储空间使用权限，请前往应用权限设置打开权限。";
                } else if (permissionName.equals(Manifest.permission.CAMERA)) {
                    alertMsg = "未获得您的相机使用权限，请前往应用权限设置打开权限。";
                }
            }
            new AppSettingsDialog.Builder(this).setTitle("提示：").setRationale(alertMsg).setNegativeButton("取消").setPositiveButton("去打开").build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_CAMERA_REQUEST_CODE:
                    File file = new File(Environment.getExternalStorageDirectory()
                            + "/ic_dibiao.jpg");//保存图片
                    if (file.exists()) {
                        //对相机拍照照片进行裁剪
                        photoClip(Uri.fromFile(file));
                    }
                    break;
                case TAKE_PHOTO_REQUEST_CODE:
                    if (data != null) {
                        Uri uri = data.getData();
                        //对相册取出照片进行裁剪
                        photoClip(uri);
                    }
                    break;
                case PHOTO_CLIP:
                    if (data != null) {

                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap photo = extras.getParcelable("data");
                            try {
                                //获得图片路径
                                filepath = saveFile(photo, Environment.getExternalStorageDirectory().toString(), "ic_dibiao.jpg");
                                //上传照片
                                toUploadFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //上传完成将照片写入imageview与用户进行交互
//                            mImageView.setImageBitmap(photo);
                        }
                    }
                    break;
            }
        }
    }

    private File filepath;
    private void toUploadFile() {
        if (filepath.exists()) {
            Log.i("521", "toUploadFile: 文件存在...");
        }
    }

    public static File saveFile(Bitmap bm, String path, String fileName) throws IOException {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path, fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除储存bitmap的file
        if (filepath != null && filepath.exists()) {
            filepath.delete();
        }
    }
}
