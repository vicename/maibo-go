package com.generallibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public abstract class RecycleCommonAdapter<T> extends
        RecyclerView.Adapter<RecycleViewHolder> {

    protected Context mContext;
    protected List<T> list;
    private int resourceID;
    private Toast toast;
    private OnItemClickListener mItemClickListener;

    /**
     * @param context
     */
    public RecycleCommonAdapter(Context context, List<T> list, int resourceID) {
        super();
        this.mContext = context;
        this.list = list;
        this.resourceID = resourceID;
    }

    // RecyclerView显示的子View
    // 该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(resourceID, viewGroup, false);
        return RecycleViewHolder.get(v);
    }

    // 将数据绑定到子View
    @Override
    public void onBindViewHolder(RecycleViewHolder viewHolder, final int i) {
        convert(viewHolder, list.get(i % list.size()), i);
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onClick(v, i);
            }
        });
    }

    // RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public abstract void convert(RecycleViewHolder holder, T t, int position);

    public void setDatas(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void MessageShow(String msg) {
        if (toast == null) {
            toast = Toast.makeText(mContext, msg,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
