package com.ygc.estatedecoration.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ygc.estatedecoration.R;
import com.ygc.estatedecoration.api.APPApi;
import com.ygc.estatedecoration.bean.BaseBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhan on 2017-9-5.
 * 工具类
 */

public class MyPublic {
    //数字表达式
    private final static Pattern number_pattern = Pattern.compile("^[0-9]*$");

    /**
     * 加载网络图片
     */
    public static void loadImg(String url, ImageView view) {
        if (TextUtils.isEmpty(url) || view == null) return;
//        Glide.with(ConfigApplication.getC()).load(UrlUtils.All + "/" + url).into(view);
    }

    /**
     * 获取当前时间
     */
    public static String getNowTime() {
//        return "2017-09-11";
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取订单状态
     */
    public static String getType(String state) {
        if (TextUtils.isEmpty(state)) return "无";
        switch (state) {
            case "1":
                return "待付款";
            case "2":
                return "待收货";
            case "3":
                return "待评价";
            case "4":
                return "已评价";
            case "5":
                return "退货/售后";
        }
        return "无";
    }

    /**
     * 获取客服QQ
     * <p>
     * 1.此处传入的QQ号,需开通QQ推广功能,不然向此QQ号发送临时消息,会不成功.
     * 2.开通QQ推广方法:
     * ................1.打开QQ推广网址http://shang.qq.com并用QQ登录
     * ................2.点击顶部导航栏:推广工具
     * ................3.在弹出菜单中点击'立即免费开通' 即可
     */
    public static String getQQ() {
        return "1070371262";
    }

    /**
     * 获取客服电话
     */
    public static String getPhone() {
        return "13888888888";
    }

    /**
     * 打电话
     */
    public static void call(Context context, String mobile) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + String.valueOf(mobile));
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取app版本号
     */
    public static String getVersion(Context act) {
        try {
            PackageManager manager = act.getPackageManager();
            PackageInfo info = manager.getPackageInfo(act.getPackageName(), 0);
            String version = info.versionName;
            return "Version " + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号获取失败";
        }
    }


    //登录注册本分工具类--------------------------------------------------------------------------------

    /**
     * 获取文字
     */
    public static String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

    /**
     * 判断string集合中有没有空值
     */
    public static boolean isEmpty(List<String> list) {
        boolean flag = false;
        for (String string : list) {
            if (MyPublic.isEmpty(string)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 校验银行卡卡号 是否合法
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        boolean isBankCard = cardId.charAt(cardId.length() - 1) == bit;
//        KLog.e("银行卡：" + isBankCard);
        return isBankCard;
    }

    /**
     * 验证身份证号码是否正确
     *
     * @param IDCardNo 身份证号码
     * @return boolean
     */
    public static boolean isIDCard(String IDCardNo) {
        //记录错误信息
        String errmsg = "";
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";

        //================ 身份证号码的长度 15位或18位 ================
        if (IDCardNo.length() != 15 && IDCardNo.length() != 18) {
            errmsg = "身份证号码长度应该为15位或18位!";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
            return false;
        }

        //================ 数字 除最后以为都为数字 ================
        if (IDCardNo.length() == 18) {
            Ai = IDCardNo.substring(0, 17);
        } else if (IDCardNo.length() == 15) {
            Ai = IDCardNo.substring(0, 6) + "19" + IDCardNo.substring(6, 15);
        }
        if (isNumber(Ai) == false) {
            errmsg = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
            return false;
        }

        //================ 出生年月是否有效 ================
        //年份
        String strYear = Ai.substring(6, 10);
        //月份
        String strMonth = Ai.substring(10, 12);
        //日
        String strDay = Ai.substring(12, 14);
        if (AppSysDateMgr.getDateIsTrue(strYear, strMonth, strDay) == false) {
            errmsg = "身份证生日无效";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errmsg = "身份证生日不在有效范围";
                AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg + e.getMessage());
            return false;
        } catch (java.text.ParseException e1) {
            e1.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg + e1.getMessage());
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errmsg = "身份证月份无效";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errmsg = "身份证日期无效";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
            return false;
        }

        //================ 地区码时候有效 ================
        Hashtable hashtable = AppInfoMgr.getAreaCodeAll();
        if (hashtable.get(Ai.substring(0, 2)) == null) {
            errmsg = "身份证地区编码错误";
            AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
            return false;
        }

        //================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;
        if (IDCardNo.length() == 18) {
            if (Ai.equals(IDCardNo) == false) {
                errmsg = "身份证无效，不是合法的身份证号码";
                AppLogMessageMgr.e("AppValidationMgr-->>isIDCard", errmsg);
                return false;
            }
        } else {
            return true;
        }
        return true;
    }

    /**
     * 验证是数字
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return number_pattern.matcher(str).matches();
    }

    /**
     * 判断string是否为空
     */
    public static boolean isEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    /**
     * 拆分字符为"," ,然后把结果交给数组strArray
     *
     * @param str
     * @return
     */
    public static List<String> convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(",");
        return Arrays.asList(strArray);
    }

    /**
     * 获取验证码
     */
    public static void getVerification(final Context context, String photoNum, final Button button, String type) {

        APPApi.getInstance().service
                .doSendCode(photoNum, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        LogUtil.e("请求网路成功");

                        if (null == baseBean) return;
                        String msg = baseBean.getMsg();
//                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        new PhoneGetCodeUtil(button, context).onStart();
                        String responseState = baseBean.getResponseState();
//                        showToast("responseState");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("Fc_请求网路失败" + e.getMessage());
                        Toast.makeText(context, context.getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                        showToast("请稍后再试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
