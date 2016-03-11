package com.bixw.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bixinwei on 16/3/9.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
    }

    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 对文本控件根据viewId直接赋值
     * @param textViewId
     * @param text
     */
    public void setText(int textViewId,String text){
        TextView tv=getView(textViewId);
        if(tv!=null) {
            tv.setText(text);
        }
    }
}
