package com.lingc.zhihudaily;

import android.app.Application;

/**
 * Create by LingC on 2019/7/3 19:03
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyExceptionHandler.getMyExceptionHandler().init(getApplicationContext());
    }
}
