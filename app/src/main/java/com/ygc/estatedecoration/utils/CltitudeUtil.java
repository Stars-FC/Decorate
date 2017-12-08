package com.ygc.estatedecoration.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p/>
 * 计算控件等常用工具类
 */
public class CltitudeUtil {

    public static int phoneWidth;
    public static int phoneHeight;
    public static int tabWidth = 180;//单个标题宽度
    public static int tabHeight;
    public static float uiWidth = 750f; // ui宽
    public static float uiHeight = 1334f;//ui高
    //活动测试url
    public static String activityUrl = "http://xhomeservice.com/FileMergeService/showimage?filename=maiyisongyi.png";

    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void MyGetWindowManager(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        phoneWidth = metric.widthPixels;     // 屏幕宽度（像素）
        phoneHeight = metric.heightPixels;   // 屏幕高度（像素）
//        tabWidth = phoneWidth/6;
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
    }

    /**
     * textview 适配计算
     * * @param context
     *
     * @param w
     * @param h
     * @param margins
     * @param padding
     * @param size
     * @return
     */
    public static TextView configTextviewParams(Context context, int w, int h, int[] margins, int[] padding, int size, int color) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        textView.setPadding(padding[0], padding[1], padding[2], padding[3]);
        textView.setLayoutParams(params);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextSize(size);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(context.getResources().getColor(color));

        return textView;
    }

    /**
     * imageView 父布局 LinearLayout 适配计算
     *
     * @param context
     * @param w
     * @param h
     * @param margins
     * @param padding
     * @return
     */
    public static ImageView configImageViewParams(Context context, int w, int h, int[] margins, int[] padding) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        imageView.setPadding(padding[0], padding[1], padding[2], padding[3]);
        imageView.setLayoutParams(params);
        return imageView;
    }

    /**
     * imageView 父布局 RelativeLayout 适配计算
     *
     * @param context
     * @param w
     * @param h
     * @param margins
     * @param padding
     * @return
     */
    public static ImageView configImageViewRParams(Context context, int w, int h, int[] margins, int[] padding) {
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
        params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        imageView.setPadding(padding[0], padding[1], padding[2], padding[3]);
        imageView.setLayoutParams(params);
        return imageView;
    }

//    public static LinearLayout configLinearLayoutParams(Context context, int w, int h, int[] margins, int[] padding){
//        LinearLayout linearLayout = new LinearLayout(context);
//
//    }


    /**
     * 计算宽
     *
     * @param width
     * @return
     */
    public static float uiWidthCalculate(float width) {
        return width / uiWidth * phoneWidth;
    }

    /**
     * 计算高
     *
     * @param height
     * @return
     */
    public static float uiHeightCalculate(float height) {
        return height / uiHeight * phoneHeight;
    }

    /**
     * 文字不同风格 第一位小，小数点前大，后面默认
     *
     * @param context
     * @param textView
     * @param text
     * @param smallStyle
     * @param bigStyle
     */
    public static void setTextStyle(Context context, TextView textView, String text, int smallStyle, int bigStyle) {
        SpannableString styledText = new SpannableString(text);

        styledText.setSpan(new TextAppearanceSpan(context, smallStyle), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (text.indexOf('.') != -1) {
            styledText.setSpan(new TextAppearanceSpan(context, bigStyle), 1, text.indexOf('.'), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            styledText.setSpan(new TextAppearanceSpan(context, bigStyle), 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    /**
     * 下划线
     *
     * @param textView
     */
    private void addButtomLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    /**
     * 移除线
     *
     * @param textView
     */
    public static void removeLine(TextView textView) {
        textView.getPaint().setFlags(0); // 取消设置的的划线

    }

    /**
     * 设置中划线并加清晰
     *
     * @param textView
     */
    public static void addLine(TextView textView) {
        textView.getPaint().setFlags(
                Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰

    }

    /**
     * 中划线
     *
     * @param textView
     */
    public static void addCenterLine(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
    }

    /**
     * 抗锯齿
     *
     * @param textView
     */
    public static void addjuchiLine(TextView textView) {
        textView.getPaint().setAntiAlias(true);// 抗锯齿
    }

    /**
     * * 两个Double数相乘 *
     *
     * @param num   *
     * @param price *
     * @return Double
     */
    public static Double ddmul(double num, double price) {
        BigDecimal num1 = new BigDecimal(Double.toString(num));
        BigDecimal price1 = new BigDecimal(Double.toString(price));
        return getTwoPoint(new Double(num1.multiply(price1).doubleValue()), 2);

    }

    /**
     * * 两个Double数相加 *
     *
     * @param num   *
     * @param price *
     * @return Double
     */
    public static Double ddadd(double num, double price) {
        BigDecimal num1 = new BigDecimal(Double.toString(num));
        BigDecimal price1 = new BigDecimal(Double.toString(price));
        return getTwoPoint(new Double(num1.add(price1).doubleValue()), 2);
    }

    public static Float ddadd(float num, float price) {
        BigDecimal num1 = new BigDecimal(Float.toString(num));
        BigDecimal price1 = new BigDecimal(Float.toString(price));
        return new Float(num1.add(price1).floatValue());
    }

    /**
     * * 两个Double数相减 *
     *
     * @param num   *
     * @param price *
     * @return Double
     */
    public static Double ddsub(double num, double price) {
        BigDecimal num1 = new BigDecimal(Double.toString(num));
        BigDecimal price1 = new BigDecimal(Double.toString(price));
        return getTwoPoint(new Double(num1.subtract(price1).doubleValue()), 2);

    }

    /**
     * * 两个Double数相除 *
     *
     * @param num   *
     * @param price *
     * @return Double
     */
    public static Double dddiv(double num, double price) {
        BigDecimal num1 = new BigDecimal(Double.toString(num));
        BigDecimal price1 = new BigDecimal(Double.toString(price));
        return getTwoPoint(new Double(num1.divide(price1).doubleValue()), 2);

    }

    /**
     * 保留*位小数
     *
     * @param aDouble
     * @param scale   表示表示需要精确到小数点以后几位。
     * @return
     */
    public static Double getTwoPoint(Double aDouble, int scale) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(aDouble));

        return bigDecimal.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 跳转并携ArrayList<GoodsItem>带数据到新activity
     */
//    public static void toActivity(Context context, Class aClass, ArrayList<GoodsItem> list) {
//        Intent intent = new Intent(context, aClass);
//        intent.putExtra("goodsItemList",(Serializable)list);
//        context.startActivity(intent);
//
//    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean stringIsEmpty(String s) {
        if ((s != null) && (!s.equals("")) && (s.length() != 0)) {
            return false;
        }
        return true;
    }

//    /**
//     * 判断是否登录
//     * @param context
//     * @return
//     */
//    public static boolean isLogined(Context context){
//        if (SharedPreferencesUtil.getData(context,"userName","").toString().length()> 0){
//            return true;
//        }
//        return false;
//    }

    /**
     * 字符转换成utf-8
     *
     * @param s
     * @return
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * dip，dp转化成px 用来处理不同分辨路的屏幕
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getLocalVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }


}
