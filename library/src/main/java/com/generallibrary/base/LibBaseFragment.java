package com.generallibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.generallibrary.CustomViews.loading.CommonProgressDialog;
import com.generallibrary.R;
import com.generallibrary.utils.WeakHandler;

/**
 * Created by feng on 2016/10/14.
 * 延迟加载的fragment，只有获取用户焦点后才获取数据 ，布局不变而只修改数据
 */
public abstract class LibBaseFragment extends Fragment implements WeakHandler.IHandler, SwipeRefreshLayout.OnRefreshListener {

    private boolean isLoad = false;
    /**
     * 是否是创建布局, setUserVisibleHint方法在onCreateView之前也会调用，不加这个判断可能导致崩溃
     */
    private boolean isInitView = false;
    protected View mView;
    protected Activity mContext;
    public WeakHandler mHandler;
    private CommonProgressDialog mProgressDialog;
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isInitView = true;
        mHandler = new WeakHandler(this);
        mContext = getActivity();
        return doCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 和adpter搭配使用的时候生效，不配合adpter的时候，比如单独对在activity中使用的话得使用下面俩方法触发
     *
     * @param isVisibleToUser 是否对用户可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isInitView) {
            if (isVisibleToUser) {
                doVisible();
            } else {
                doInVisible();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        doVisible();
    }

    @Override
    public void onPause() {
        super.onPause();
        doInVisible();
    }

    /**
     * 界面可见时做相应的处理
     */
    private void doVisible() {
        if (!isLoad) {
            isLoad = true;//加载数据完成
            lazyLoad();
        }
    }

    /**
     * 界面不可见时，做的操作
     */
    protected void doInVisible() {
    }

    /**
     * 界面可见时加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 填充布局的抽象方法
     */
    protected abstract View doCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    @Override
    public void handleMessage(Message msg) {
    }

    public void closeProgressDialog() {
        if (mProgressDialog != null && !mContext.isFinishing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void showProgressDialog(String... hint) {
        if (mProgressDialog == null) {
            mProgressDialog = new CommonProgressDialog(mContext);
        }
        mProgressDialog.setMessage(hint != null && hint.length > 0 ? hint[0]
                : getString(R.string.being_loaded));
        mProgressDialog.setCanceledOnTouchOutside(false);

        if (!mContext.isFinishing() && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    protected SwipeRefreshLayout initRefreshLayout(View view, int id) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(id);
        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.colorPrimary, R.color.yellow);
        mSwipeRefreshLayout.setProgressViewOffset(true, -200, 50);
        return mSwipeRefreshLayout;
    }

    protected boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    protected void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    protected void setSwipeRefreshEnable(boolean enable) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setEnabled(enable);
        }
    }

    protected boolean getSwipeRefreshLayout() {
        return mSwipeRefreshLayout != null && mSwipeRefreshLayout.isEnabled();
    }
}
