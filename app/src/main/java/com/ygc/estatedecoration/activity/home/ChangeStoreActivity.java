package com.ygc.estatedecoration.activity.home;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.app.activity.BaseActivity;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.bean.CaseStyleBean;
import com.ygc.estatedecoration.utils.AddressPickTask;
import com.ygc.estatedecoration.utils.LogUtil;
import com.ygc.estatedecoration.utils.MyPublic;
import com.ygc.estatedecoration.utils.NetWorkUtil;
import com.ygc.estatedecoration.utils.PictureCompressUtil;
import com.ygc.estatedecoration.utils.UserUtils;
import com.ygc.estatedecoration.widget.BasePopupWindow;
import com.ygc.estatedecoration.widget.CircleImageView;
import com.ygc.estatedecoration.widget.TitleBar;

import org.angmarch.views.NiceSpinner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by FC on 2017/12/12.
 * 修改店铺信息
 */

public class ChangeStoreActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.ll_change_store)
    ScrollView mLinearLayout;
    @BindView(R.id.tv_show_place)
    TextView tv_show_place;//地点
    @BindView(R.id.nice_spinner_type)
    NiceSpinner nice_spinner_type;//类别
    @BindView(R.id.et_work_time)
    EditText et_work_time;//工作经验
    @BindView(R.id.nice_spinner_style)
    NiceSpinner nice_spinner_style;//擅长风格
    @BindView(R.id.iv_company_icon)
    CircleImageView mImageView;//头像
    @BindView(R.id.home_change_nick)
    EditText mNick;//昵称

    private String mProvince = "辽宁";//省
    private String mCity = "沈阳";//城市

    private BasePopupWindow mSelectPicPopupWindow;
    private File filepath;
    Handler handler;
    private String mStoreId;
    private List<String> mDataset4;


    @Override
    protected boolean buildTitle(TitleBar bar) {
        bar.setTitleText("修改信息");
        bar.setLeftImageResource(R.drawable.fanhui);
        return true;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void initView() {
        final List<String> dataset2 = new LinkedList<>(Arrays.asList("个体", "团体"));
        nice_spinner_type.attachDataSource(dataset2);
        nice_spinner_type.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        //样式点击事件
        nice_spinner_style.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getCaseStyleData();//获取样式风格
        handler = new Handler();
        mStoreId = getIntent().getExtras().getString("storeId");  //我的店铺传递过来的店铺id
    }

    @Override
    protected int getLayoutId() {
        //设置弹出软键盘时上移布局，防止EditText被挡住
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.home_change_store;
    }

    @OnClick({R.id.naviFrameLeft, R.id.ll_icon, R.id.home_change_store_place, R.id.home_change_store_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.naviFrameLeft:
                finish();
                break;
            case R.id.ll_icon://更换头像
                addICEvent();
                break;
            case R.id.home_change_store_place://选择地址
                setPlace();
                break;
            case R.id.home_change_store_ok://确认修改
                changeDataFromNet();
                break;
        }
    }

    /**
     * 选择地址
     */
    private void setPlace() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                tv_show_place.setText(province.getAreaName() + " " + city.getAreaName());
                mProvince = province.getAreaName();
                mCity = city.getAreaName();
            }
        });
        task.execute("辽宁", "沈阳");
    }

    /**
     * 修改信息页面修改信息
     */
    public void changeDataFromNet() {

        if (!NetWorkUtil.isNetWorkConnect(this)) {
            showToast("请检查网络设置");
            return;
        }

        setNet();

        //图片压缩（上传头像可能用不到）
       /* PictureCompressUtil.getInstance().startCompress(this, Arrays.asList(new String[]{Environment.getExternalStorageDirectory()
                        + "/" + filepath.getName()}),
                new PictureCompressUtil.CompressedPicResultCallBack() {
                    @Override
                    public void showResult(List<String> photos, List<File> list) {
                        File file = list.get(0);
                        setNet(file);
                    }
                });*/
    }

    /**
     * 修改店铺信息
     */
    private void setNet() {

        String nick = MyPublic.getText(mNick);
        String type = MyPublic.getText(nice_spinner_type);
        String workTime = MyPublic.getText(et_work_time);
        String style = MyPublic.getText(nice_spinner_style);
        String place = MyPublic.getText(tv_show_place);

        List<String> list = new ArrayList<>(Arrays.asList(nick, type, workTime, style, place, place));
        if (MyPublic.isEmpty(list)) {
            showToast("请填写完整");
            return;
        }
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setCancelable(false);
        pDialog.show();

        RequestBody requestBody;
        //判断图片是否存在（不存在，则不上传图片）
        if (filepath != null) {
            requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("s_id", mStoreId)
                    .addFormDataPart("s_name", nick)
                    .addFormDataPart("s_type", type)
                    .addFormDataPart("work_year", workTime)
                    .addFormDataPart("r_id", style)
                    .addFormDataPart("s_province", mProvince)
                    .addFormDataPart("s_city", mCity)
                    .addFormDataPart("file", filepath.getName(), RequestBody.create(MediaType.parse("image"), filepath))
                    .build();
        } else {
            requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("s_id", mStoreId)
                    .addFormDataPart("s_name", nick)
                    .addFormDataPart("s_type", type)
                    .addFormDataPart("work_year", workTime)
                    .addFormDataPart("r_id", style)
                    .addFormDataPart("s_province", mProvince)
                    .addFormDataPart("s_city", mCity)
                    .build();
        }

        APPApi.getInstance().service
                .editMyStore(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean bean) {
                        String msg = bean.getMsg();
                        showToast(msg);
                        pDialog.cancel();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.cancel();
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取样式风格
     */
    private void getCaseStyleData() {
        showDialog();
        APPApi.getInstance().service
                .getCaseStyleData("5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CaseStyleBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull CaseStyleBean caseStyleBean) {
                        cancelDialog();
                        mDataset4 = new ArrayList<>();
                        if (caseStyleBean.responseState.equals("1")) {
                            List<CaseStyleBean.DataBean> data = caseStyleBean.getData();
                            if (data != null && data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    mDataset4.add(data.get(i).getR_name());//添加样式数据到集合中
                                }
                            }
                            nice_spinner_style.attachDataSource(mDataset4);//显示样式风格
                        } else {
                            showToast(caseStyleBean.msg);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancelDialog();
                        showToast(getResources().getString(R.string.network_error));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 判断权限
     */
    private void addICEvent() {
        if (EasyPermissions.hasPermissions(ChangeStoreActivity.this, Manifest.permission.CAMERA) || EasyPermissions.hasPermissions(ChangeStoreActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
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
            mSelectPicPopupWindow = new BasePopupWindow(ChangeStoreActivity.this);
            mSelectPicPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            final View popupView = LayoutInflater.from(ChangeStoreActivity.this).inflate(R.layout.popup_window_select_pic, null);
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
        mSelectPicPopupWindow.showAtLocation(mLinearLayout, Gravity.BOTTOM, 0, 0);
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
        return EasyPermissions.hasPermissions(ChangeStoreActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(ChangeStoreActivity.this, Manifest.permission.CAMERA);
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
 /*       intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);*/
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
    public void onDestroy() {
        super.onDestroy();
        //删除储存bitmap的file
        if (filepath != null && filepath.exists()) {
            filepath.delete();
        }
    }
}
