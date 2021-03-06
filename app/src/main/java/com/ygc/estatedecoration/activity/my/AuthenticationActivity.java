package com.ygc.estatedecoration.activity.my;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.LoginActivity;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.utils.ValidationUtil;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.TitleBar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @BindView(R.id.et_real_name)
    EditText mEtRealName;//真实姓名
    @BindView(R.id.et_real_id_number)
    EditText mEtRealIdNumber;//身份证号
    @BindView(R.id.et_real_photo)
    EditText mEtRealPhoto;//联系电话

    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("实名认证");
        bar.setLeftImageResource(R.drawable.fanhui);
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

    @OnClick({R.id.naviFrameLeft, R.id.button, R.id.add_ic_iv})
    public void onClickEvent(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.naviFrameLeft://后退
                    finish();
                    break;
                case R.id.add_ic_iv://添加图片
                    addICEvent();
                    break;
                case R.id.button://开始认证
                    getAuthentication();
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
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    /**
     * 提交数据，实名认证
     */
    public void getAuthentication() {
        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;
        }
        String realName = MyPublic.getText(mEtRealName);
        if (TextUtils.isEmpty(realName)) {
            showToast("请输入姓名");
            return;
        }
        String realIdNumber = MyPublic.getText(mEtRealIdNumber);
        if (TextUtils.isEmpty(realIdNumber) || !MyPublic.isIDCard(realIdNumber)) {
            showToast("身份证号格式错误");
            return;
        }
        String realPhoto = MyPublic.getText(mEtRealPhoto);
        if (TextUtils.isEmpty(realPhoto) || !ValidationUtil.isPhone(realPhoto)) {
            showToast("手机号格式错误");
            return;
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setCancelable(false);
        pDialog.show();


        //判断图片是否存在
        if (filepath == null) {
            showToast("请添加上传图片");
            return;
        }

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("au_id", UserUtils.getUserId().toString())
                .addFormDataPart("real_name", realName)
                .addFormDataPart("numbe", realIdNumber)
                .addFormDataPart("tel", realPhoto)
                .addFormDataPart("file", filepath.getName(), RequestBody.create(MediaType.parse("image"), filepath))
                .build();

        APPApi.getInstance().service
                .authentication(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        pDialog.cancel();
                        String msg = bean.getMsg();
                        if ("1".equals(bean.getResponseState())) {
                            showToast(msg);
                        } else {
                            showToast(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.cancel();
                        LogUtil.e("Fc_实名认证，请求网路失败" + e.getMessage());
                        showToast("网络繁忙，请稍后再试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
