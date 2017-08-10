package com.generallibrary.adapter.base_recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import java.util.List;

/**
 */
public abstract class BaseRecyclerViewAdapter<T> extends Adapter<ViewHolder> {

    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 接收传递过来的数据
     */
    private List<T> mData;
    /**
     * 获得holder
     */
    private BaseViewHolder baseHolder;

    private int mCurrentPos;
    private OnItemRecyclerClickListener mOnItemClickRecyclerListener;

    public BaseRecyclerViewAdapter(Context mContext, List<T> data) {
        this.mContext = mContext;
        setData(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getItemHolder(mContext, mOnItemClickRecyclerListener, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder != null) {
            baseHolder = (BaseViewHolder) holder;
            baseHolder.setPosition(position);
            baseHolder.setData(mData.get(position));
        }

        mCurrentPos = position;
    }

    public int getCurrentPosition() {
        return mCurrentPos;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public List<T> getmData() {
        return mData;
    }

    public void setData(List<T> data) {
        this.mData = data;
    }

    public void addData(List<T> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 获得Holder
     */
    public abstract BaseViewHolder getItemHolder(Context context, OnItemRecyclerClickListener listener, ViewGroup parent);

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemRecyclerClickListener listener) {
        this.mOnItemClickRecyclerListener = listener;
    }
}