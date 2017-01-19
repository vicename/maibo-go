package com.generallibrary.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 *
 * Created by feng on 2016/8/19.
 */
public class LibDeprecatedUtil {
    public static int getColor(Context context, int id) {
        int c = 0;
        if (Build.VERSION.SDK_INT < 23) {
            c = context.getResources().getColor(id);
        } else {
            c = context.getResources().getColor(id, context.getTheme());
        }
        return c;
    }

    public static Drawable getDrawable(Context context, int id) {
        Drawable d = null;
        if (Build.VERSION.SDK_INT < 23) {
            d = context.getResources().getDrawable(id);
        } else {
            d = context.getResources().getDrawable(id, context.getTheme());
        }
        return d;
    }
}
