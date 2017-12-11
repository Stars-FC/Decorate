package com.ygc.estatedecoration.utils;

import android.content.Context;

import java.io.File;

public class ClearCacheUtil {

    /**
     * 清楚应用专属缓存存储目录中通过鲁班压缩的图片
     * @param context
     */
    public static void clearAppCacheDir(Context context) {
        File externalCacheDirFile = new File(context.getExternalCacheDir() + File.separator + "luban_disk_cache");
        FileUtil.deleteDirWithFile(externalCacheDirFile);
    }
}
