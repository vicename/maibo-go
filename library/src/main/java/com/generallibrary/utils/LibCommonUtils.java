package com.generallibrary.utils;

import android.os.Handler;
import android.view.View;

import java.io.File;
import java.text.DecimalFormat;

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

    public static String formatPrice(double price) {
        return new DecimalFormat("####0.00").format(price);
    }

}
