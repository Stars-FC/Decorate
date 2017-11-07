package com.ygc.estatedecoration.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetWorkUtil {
	/**
	 * wifi网络
	 */
	public static int NETWORK_WIFI=0;
	/**
	 * 有限网络
	 */
	public static int NETWORK_YOU_XIAN =1;
	/**
	 *  3g网络
	 */
	public static int NETWORK_THREE_G=2;
	/**
	 * 4g网络
	 */
	public static int NETWORK_FOUR_G=3;
	/**
	 * 没有网络
	 */
	public static int NETWORK_ERROR=4;

	/**
	 * 判断网络是否连接
	 */
	public static boolean isNetWorkConnect(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager != null) {
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null) {
				return networkInfo.isAvailable();
			}
		}

		return false;
	}

	/**
	 * 得到网络状态
	 * @return
	 */
	public static int getNetWorkStatus(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = mConnectivityManager
				.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isAvailable()) {
			//网络连接
			String name = netInfo.getTypeName();

			if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				//WiFi网络
				return NETWORK_WIFI;

			} else if (netInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
				//有线网络
				return NETWORK_YOU_XIAN;

			} else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				//3g网络
				return NETWORK_THREE_G;

			} else if (netInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE) {
				//4g网络
				return NETWORK_FOUR_G;

			}else if(netInfo.getType() == TelephonyManager.NETWORK_TYPE_EHRPD){
				//3g网络
				return NETWORK_THREE_G;

			}
		} else {
			//网络断开
			return NETWORK_ERROR;

		}
		return NETWORK_ERROR;
	}

}
