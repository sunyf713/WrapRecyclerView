package com.bixw.recyclerview;

/**
 * Created by bixinwei on 16/3/10.
 */
public interface IViewType {
    int getItemViewLayoutId(int viewType);
    int getItemViewType(int position,Object itemBean);
}
