package com.bixw.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by bixinwei on 16/3/31.
 */
public class PullRefreshHeader extends FrameLayout implements PtrUIHandler {

    public LayoutInflater inflater;

    // 下拉刷新视图（头部视图）
    public ViewGroup headView;

    // 下拉刷新文字
    public TextView tvHeadTitle;


    public PullRefreshHeader(Context context) {
        this(context, null);
    }

    public PullRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init(Context context) {
        inflater=LayoutInflater.from(context);
        headView = (ViewGroup) inflater.inflate(R.layout.view_pull_refresh_header, this, true);
        tvHeadTitle = (TextView) headView.findViewById(R.id.tv_head_title);
    }

    /**
     * Content 重新回到顶部， Header 消失，整个下拉刷新过程完全结束以后，重置 View。
     * @param frame
     */
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        tvHeadTitle.setText("下拉刷新");
    }

    /**
     * 准备刷新，Header 将要出现时调用。
     * @param frame
     */
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        tvHeadTitle.setText("下拉刷新");
    }

    /**
     * 开始刷新，Header 进入刷新状态之前调用。
     * @param frame
     */
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        tvHeadTitle.setText("正在刷新");

    }

    /**
     * 刷新结束，Header 开始向上移动之前调用。
     * @param frame
     */
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        tvHeadTitle.setText("刷新完成");
    }

    /**
     * 下拉过程中位置变化回调。
     * @param frame
     * @param isUnderTouch
     * @param status
     * @param ptrIndicator
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
    }
}
