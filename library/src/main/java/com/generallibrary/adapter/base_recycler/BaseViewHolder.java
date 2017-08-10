package com.generallibrary.adapter.base_recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 */
public abstract class BaseViewHolder<T> extends ViewHolder implements View.OnClickListener {

    private BaseOnItemClickRecyclerListener mListener;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 加载得到的数据
     */
    public T mData;
    /**
     * 只有当该holder作为item使用，并且使用带参构造函数实例化position才有意义，使用无参构造函数则position没有意义
     */
    protected int position;

    public BaseViewHolder(Context context, BaseOnItemClickRecyclerListener listener, View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mContext = context;
        this.mListener = listener;
    }

    /**
     * 设置数据
     */
    public void setData(T data) {
        this.mData = data;
        refreshView();
    }

    /**
     * 刷新数据
     */
    public abstract void refreshView();

    /**
     * 获得数据
     */
    public T getData() {
        return mData;
    }

    /**
     * 当复用holder的时候，需要调用该方法来同步holder对应的索引位置
     */
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v, position);
        }
    }
}