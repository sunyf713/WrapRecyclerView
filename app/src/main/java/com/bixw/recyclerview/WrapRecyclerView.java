package com.bixw.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by bixinwei on 16/3/9.
 */
public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>() ;

    private ArrayList<View> mFootViews = new ArrayList<>() ;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * this method must be called after setAdapter
     * @param view headView
     */
    public void addHeaderView(View view){
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if(getAdapter()==null){
            throw new NullPointerException("setAdapter() it wasn't already called before");
        }
        IHeaderFootView adapter= (IHeaderFootView) getAdapter();
        adapter.addHeaderView(mHeaderViews);
    }

    /**
     * this method must be called after setAdapter
     * @param view footView
     */
    public void addFootView(View view){
        mFootViews.clear();
        mFootViews.add(view);

        if(getAdapter()==null){
            throw new NullPointerException("setAdapter() it wasn't already called before");
        }
        IHeaderFootView adapter= (IHeaderFootView) getAdapter();
        adapter.addFootView(mFootViews);
    }



}
