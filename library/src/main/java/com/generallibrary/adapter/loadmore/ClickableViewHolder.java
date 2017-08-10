package com.generallibrary.adapter.loadmore;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.generallibrary.adapter.base_recycler.OnItemRecyclerClickListener;

/**
 * @author YanLu
 * @since 15/11/1
 */
public abstract class ClickableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    OnItemRecyclerClickListener mClickListener;

    public ClickableViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View v) {
        if(mClickListener != null){
            mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void addOnViewClickListener(View v){
        if(v != null) {
            v.setOnClickListener(this);
        }
    }
    public void addOnItemViewClickListener(){
        if(itemView != null) {
            itemView.setOnClickListener(this);
        }
    }

    public OnItemRecyclerClickListener getOnRecyclerItemClickListener() {
        return mClickListener;
    }

    public void setOnRecyclerItemClickListener(OnItemRecyclerClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }
}
