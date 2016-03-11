package com.bixw.recyclerview;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by bixinwei on 16/3/10.
 */
public interface IHeaderFootView {
    void addHeaderView(ArrayList<View> views) ;
    void addFootView(ArrayList<View> views);

}
