package com.generallibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Li DaChang on 16/9/5.
 * ..-..---.-.--..---.-...-..-....-.
 */
public class LibCommonUtils {
    public static boolean initPath(String path) {
        File rootFile = new File(path);
        return initPath(rootFile);
    }

    public static boolean initPath(File rootFile) {
        return rootFile.exists() || rootFile.mkdirs();
    }



    public static boolean isInMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 将带中文的URL转换成UTF-8编码
     *
     * @param url url
     * @return 编码后的url
     */
    public static String encodeUrl(String url) {
        return Uri.encode(url, "-![.:/,%?&=]");
    }

    /**
     * 让按钮一段时间内不可点击
     *
     * @param view view控件
     * @param time 不可点击的时间
     */
    public static void disableViewForSeconds(final View view, int time) {
        view.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, time);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取导航栏高度
     *
     * @param context context
     * @return 高度
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    public static String formatPrice(double price) {
        return new DecimalFormat("####0.00").format(price);
    }

}
