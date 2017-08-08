package com.generallibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.generallibrary.R;

/**
 * Created by feng on 2016/10/12.
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * 普通的Toast
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (mToast == null){
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * 普通的Toast
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, int msg) {
        if (mToast == null){
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * 自定义位置的Toast
     *
     * @param context
     * @param msg
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void showToastLocation(Context context, String msg, int gravity, int xOffset, int yOffset) {
        Toast toast = Toast.makeText(context,
                msg, Toast.LENGTH_LONG);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }

    /**
     * 带图片的Toast
     * @param context
     * @param msg
     * @param resId
     */
    public static void showToastPicture(Context context, String msg, int resId) {
        Toast toast = Toast.makeText(context,
                msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(resId);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    /**
     * 完全自定义
     * @param context
     * @param msg
     * @param title
     * @param resId
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void showToastCustomize(Activity context, String msg, String title,int resId, int gravity, int xOffset, int yOffset){
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom,
                (ViewGroup) context.findViewById(R.id.llToast));
        ImageView image = (ImageView) layout
                .findViewById(R.id.tvImageToast);
        image.setImageResource(resId);
        TextView titleTV = (TextView) layout.findViewById(R.id.tvTitleToast);
        titleTV.setText(title);
        TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
        text.setText(msg);
        Toast toast = new Toast(context);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    /**
     * 其他线程
     * @param context
     * @param msg
     */
    public static void showToastThresd(final Context context, final String msg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

}