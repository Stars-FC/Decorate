package com.ygc.estatedecoration.utils;

import java.io.File;

public class FileUtil {

    /**
     * 删除文件夹和文件夹里面的文件
     *
     * @param dir
     */
    public static void deleteDirWithFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete();
            else if (file.isDirectory())
                deleteDirWithFile(file);
        }
        dir.delete();
    }
}
