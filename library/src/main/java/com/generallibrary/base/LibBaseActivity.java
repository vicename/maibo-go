package com.generallibrary.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.generallibrary.custom_views.loading.CommonProgressDialog;
import com.generallibrary.R;
import com.generallibrary.utils.LibWindowUtils;
import com.generallibrary.utils.LibWorker;
import com.generallibrary.utils.WeakHandler;

/**
 * Created by Li DaChang on 16/9/5.
 * ..-..---.-.--..---.-...-..-....-.
 */
public abstract class LibBaseActivity extends AppCompatActivity implements WeakHandler.IHandler {
    public Context mContext;
    public WeakHandler mHandler;
    private View mStatusBarFitter;
    private CommonProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mHandler = new WeakHandler(this);
            initVar();
            initView();
            initListener();
            loadData();
    }

    protected abstract void initVar();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void loadData();

    /**
     * 关闭loading对话框
     */
    public void closeProgressDialog() {
        if (progressDialog != null && !isFinishing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 打开loading对话框
     *
     * @param hint
     */
    public void showProgressDialog(String... hint) {
        if (progressDialog == null) {
            progressDialog = new CommonProgressDialog(this);
        }
        progressDialog.setMessage(hint != null && hint.length > 0 ? hint[0]
                : getString(R.string.being_loaded));
        progressDialog.setCanceledOnTouchOutside(false);

        if (!isFinishing() && !progressDialog.isShowing())
            progressDialog.show();
    }


    @Override
    public abstract void handleMessage(Message msg);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                closeInput();
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 关闭输入法
     */
    protected void closeInput() {
        View view = getWindow().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            }
        }
        return true;
    }

    /**
     * 设置系统状态栏/导航栏透明
     * 假如需要的话就在页面onCreate()方法的setContentView之前调用.设置之后需要根据需要在xml里或java代码里调整相关元素的位置例如toolbar
     *
     * @param isOnlyKITKAT         是否仅限KITKAT版本(android4.4/api19/api20).毕竟5.0以上有状态栏变色.
     * @param isNavigationBarTrans 是否设置导航栏透明,毕竟很多情况下不需要导航栏透明
     * @param isFitStatusBar       是否填充状态栏,状态栏默认为透明,假如页面存在toolbar,状态栏会则需要填充
     * @param colorId              颜色id,填充状态栏时候的颜色.如果不填充的话可以随便填.注意是id,不是颜色值
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setSystemBarTranslucent(boolean isOnlyKITKAT, boolean isNavigationBarTrans, boolean isFitStatusBar, int colorId) {
        boolean isVersionFit = false;
        if (isOnlyKITKAT) {
            //如果系统版本等于4.4KITKAT或KITKAT_WATCH
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH) {
                isVersionFit = true;
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                isVersionFit = true;
            }
        }
        if (isVersionFit) {
            //添加状态栏透明标记
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (isNavigationBarTrans) {
                //添加导航栏透明标记
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
            if (isFitStatusBar) {
                //填充状态栏
                fitStatusBar(colorId);
            }
        }
    }

//    /**
//     * 用View填充StatusBar
//     */
//    protected void fitStatusBar() {
//        fitStatusBar(R.color.colorPrimary);
//    }

    /**
     * 用View填充StatusBar
     */
    protected void fitStatusBar(int resId) {
        // 创建View
        mStatusBarFitter = new View(this);
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LibWindowUtils.getStatusBarHeight(LibBaseActivity.this));
        mStatusBarFitter.setBackgroundColor(ContextCompat.getColor(LibBaseActivity.this, resId));
        mStatusBarFitter.setLayoutParams(lParams);
        // 获得根视图并把TextView加进去。
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.addView(mStatusBarFitter);
    }

    protected void removeStatusBarFitter() {
        if (mStatusBarFitter != null && mStatusBarFitter.getParent() != null) {
            ((ViewGroup) mStatusBarFitter.getParent()).removeView(mStatusBarFitter);
        }
    }

    protected void toastGo(final String msg) {
        if (LibWorker.isInMainThread()) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
