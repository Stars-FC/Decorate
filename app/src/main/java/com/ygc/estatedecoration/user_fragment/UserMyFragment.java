package com.ygc.estatedecoration.user_fragment;


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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.activity.my.ActivitiesActivity;
import com.ygc.estatedecoration.activity.my.AuthenticationActivity;
import com.ygc.estatedecoration.activity.my.UserDemandActivity;
import com.ygc.estatedecoration.activity.my.MoneyBagActivity;
import com.ygc.estatedecoration.activity.my.SettingActivity;
import com.ygc.estatedecoration.app.fragment.BaseFragment;
import com.ygc.estatedecoration.user_activity.UserAddressActivity;
import com.ygc.estatedecoration.user_activity.UserAllOrderActivity;
import com.ygc.estatedecoration.user_activity.UserCollectionActivity;
import com.ygc.estatedecoration.user_activity.UserShopCarActivity;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.CircleImageView;
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

import static android.app.Activity.RESULT_OK;

public class UserMyFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.parent_Layout)
    ScrollView mLl_parentLayout;

    @BindView(R.id.iv_company_icon)
    CircleImageView mImageView;

    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.tv_sex)
    TextView mTvSex;

    private BasePopupWindow mSelectPicPopupWindow;
    private BasePopupWindow mModifyInfoPicPopupWindow;
    private String mSex;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public UserMyFragment() {
        // Required empty public constructor
    }

    public static UserMyFragment newInstance(String param1, String param2) {
        UserMyFragment fragment = new UserMyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("我的");
        bar.setTitleTextColor(Color.BLACK);
        bar.setBackgroundColor(Color.WHITE);
        bar.setRightImageResource(R.drawable.shezhi);
        return true;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_user_my;
    }

    @OnClick({R.id.iv_company_icon, R.id.tv_chage, R.id.my_authentication, R.id.my_moneybag, R.id.my_collection, R.id.my_need, R.id.imageView_right_titlebar,
            R.id.my_activity_rl, R.id.ll_order, R.id.ll_trusteeship, R.id.ll_transaction, R.id.ll_payment, R.id.ll_evaluate, R.id.ll_customer_service, R.id.my_address_rl, R.id.project_progress_rl})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_company_icon://更换头像
                addICEvent();
                break;
            case R.id.tv_chage://修改name，sex
                showmModifyInfoPicPopupWindow();
                break;
            case R.id.my_authentication://实名认证
                intent.setClass(mActivity, AuthenticationActivity.class);
                startActivity(intent);
                break;
            case R.id.my_moneybag://我的钱包
                intent.setClass(mActivity, MoneyBagActivity.class);
                startActivity(intent);
                break;
            case R.id.my_collection://我的收藏
                intent.setClass(mActivity, UserCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.my_need://我的需求
                intent.setClass(mActivity, UserDemandActivity.class);
                intent.putExtra("mark", -1);
                startActivity(intent);
                break;
            case R.id.imageView_right_titlebar://设置
                intent.setClass(mActivity, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_transaction://购物车
                Intent intent1 = new Intent(mActivity, UserShopCarActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_order://全部订单
                skipUserOrderEvent(intent, 0);
                break;
            case R.id.ll_payment://待付款
                skipUserOrderEvent(intent, 1);
                break;
            case R.id.ll_trusteeship://待收货
                skipUserOrderEvent(intent, 2);
                break;
            case R.id.ll_evaluate://待评价
                skipUserOrderEvent(intent, 3);
                break;
            case R.id.ll_customer_service://质保
                skipUserOrderEvent(intent, 4);
                break;
            case R.id.my_address_rl://地址管理
                Intent addressIntent = new Intent(mActivity, UserAddressActivity.class);
                startActivity(addressIntent);
                break;
            case R.id.project_progress_rl://查看进度
                /*Intent projectProgressIntent = new Intent(mActivity, UserProjectProgressActivity.class);
                startActivity(projectProgressIntent);*/
                intent.setClass(mActivity, UserDemandActivity.class);
                intent.putExtra("mark", 1);
                startActivity(intent);
                break;
            case R.id.my_activity_rl://我的活动
                Intent myActivity = new Intent(mActivity, ActivitiesActivity.class);
                startActivity(myActivity);
                break;
        }
    }

    private void skipUserOrderEvent(Intent intent, int orderType) {
        intent.setClass(mActivity, UserAllOrderActivity.class);
        intent.putExtra("orderType", orderType);
        startActivity(intent);
    }

    /**
     * 判断权限
     */
    private void addICEvent() {
        if (EasyPermissions.hasPermissions(mActivity, Manifest.permission.CAMERA) || EasyPermissions.hasPermissions(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showSelectPicPopupWindow();
        } else {
            cameraReadAndWriteTask();
        }
    }

    /**
     * 显示选择图片popopwindows
     */
    private void showSelectPicPopupWindow() {
        if (mSelectPicPopupWindow == null) {
            mSelectPicPopupWindow = new BasePopupWindow(mActivity);
            mSelectPicPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(mActivity).inflate(R.layout.popup_window_select_pic, null);
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
        return EasyPermissions.hasPermissions(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(mActivity, Manifest.permission.CAMERA);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                            mImageView.setImageBitmap(photo);
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

    private void showmModifyInfoPicPopupWindow() {
        if (mModifyInfoPicPopupWindow == null) {
            mModifyInfoPicPopupWindow = new BasePopupWindow(mActivity);
            mModifyInfoPicPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupInfoView = LayoutInflater.from(mActivity).inflate(R.layout.popup_window_modify_info, null);

            final RadioGroup rg = (RadioGroup) popupInfoView.findViewById(R.id.radioGroupID);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton radioButton = (RadioButton) popupInfoView.findViewById(checkedId);
//                    Log.i("tag", "lsn 单选按钮，您的性别是：" + radioButton.getText());//获取被选中的单选按钮的值
                    mSex = radioButton.getText().toString();
                }
            });
            popupInfoView.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mModifyInfoPicPopupWindow.dismiss();

                }
            });
            popupInfoView.findViewById(R.id.bt_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mTvName.setText(((TextView) popupInfoView.findViewById(R.id.et_name)).getText().toString());
                    mTvSex.setText(mSex);

                    mModifyInfoPicPopupWindow.dismiss();
                }
            });
            mModifyInfoPicPopupWindow.setContentView(popupInfoView);
            mModifyInfoPicPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
            popupInfoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int top = popupInfoView.findViewById(R.id.container_ll).getTop();
                    int bottm = popupInfoView.findViewById(R.id.ll_bottm).getBottom();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < top || y > bottm) {
                            mModifyInfoPicPopupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
        }
        mModifyInfoPicPopupWindow.showAtLocation(mLl_parentLayout, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //删除储存bitmap的file
        if (filepath != null && filepath.exists()) {
            filepath.delete();
        }
    }
}