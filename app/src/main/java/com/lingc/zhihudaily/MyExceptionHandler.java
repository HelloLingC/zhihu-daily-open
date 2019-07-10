package com.lingc.zhihudaily;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.lingc.zhihudaily.view.activities.ExceptionActivity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Create by LingC on 2019/7/3 18:59
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static MyExceptionHandler myExceptionHandler = new MyExceptionHandler();

    public final String LOG_TAG = "MyExceptionHandler";

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private Context context;

    public static MyExceptionHandler getMyExceptionHandler() {
        return myExceptionHandler;
    }

    /**
     * 把老子变成默认的异常处理器
     */
    public void init(Context context) {
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, final Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                e.printStackTrace();
            }
        }).start();
        Intent intent = new Intent(context, ExceptionActivity.class);
        intent.putExtra("message", getCrashInfo(e));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        System.exit(0); // 关闭已奔溃的app进程
    }

    private String getCrashInfo(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String errorMessage = writer.toString();
        return errorMessage;
    }
}
