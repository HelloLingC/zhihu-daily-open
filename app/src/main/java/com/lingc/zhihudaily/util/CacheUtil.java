package com.lingc.zhihudaily.util;

import android.content.Context;

import java.io.File;

/**
 * Create by LingC on 2019/7/10 06:33
 */
public class CacheUtil {

    public static void clearCache(Context context) {
        deleteDirectory(context.getCacheDir());
    }

    public static void deleteDirectory(File file) {
        if (file.exists() && file.isDirectory()) {
            for (File item : file.listFiles()) {
                item.delete();
            }
        }
    }

}
