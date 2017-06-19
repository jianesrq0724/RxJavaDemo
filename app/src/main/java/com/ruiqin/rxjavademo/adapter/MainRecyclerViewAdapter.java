package com.ruiqin.rxjavademo.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiqin.rxjavademo.R;

import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mListData;
    private OnItemClickLister mOnItemClickLister;

    public MainRecyclerViewAdapter(List<String> mListData) {
        this.mListData = mListData;
    }

    /**
     * 设置点击事件
     */
    public void setItemClickLister(OnItemClickLister onItemClickLister) {
        this.mOnItemClickLister = onItemClickLister;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mTextView = (TextView) itemView.findViewById(R.id.item_main_textView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_recyclerview, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        /**
         * 设置点击事件
         */
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                mOnItemClickLister.onClick(view, position);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mListData.get(position));
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }


    /**
     * 由子类来实现
     */
    public interface OnItemClickLister {
        void onClick(View view, int position);
    }

}
