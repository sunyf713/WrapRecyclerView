package com.bixw.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bixinwei on 16/3/9.
 */
public abstract class BaseMultiItemTypeRecyclerAdapter<ItemBean> extends RecyclerView.Adapter<BaseRecyclerViewHolder> implements IHeaderFootView{

    public ArrayList<ItemBean> mDatas = new ArrayList<>();

    private ArrayList<View> mHeaderViews;

    private ArrayList<View> mFootViews;

    static final ArrayList<View> EMPTY_INFO_LIST = new ArrayList<>();

    static final int VIEW_TYPE_HEADER = Integer.MIN_VALUE;
    static final int VIEW_TYPE_FOOT = Integer.MAX_VALUE;

    IViewType viewTypes;

    public BaseMultiItemTypeRecyclerAdapter(IViewType viewTypes) {
        if (mHeaderViews == null) {
            this.mHeaderViews = EMPTY_INFO_LIST;
        }
        if (mFootViews == null) {
            this.mFootViews = EMPTY_INFO_LIST;
        }
        this.viewTypes=viewTypes;
    }


    public void addHeaderView(ArrayList<View> views) {
        mHeaderViews.clear();
        mHeaderViews = views;
    }

    public void addFootView(ArrayList<View> views) {
        mFootViews.clear();
        mFootViews = views;
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        /**
         *
         * @param itemView normalItem view
         * @param itemBean normalItem WrapBean
         * @param position normalItem real position ,be consistent with mDatas
         */
        void onItemClick(View itemView, Object itemBean, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void append(ItemBean itemBean) {
        if (itemBean != null) {
            mDatas.add(itemBean);
            notifyDataSetChanged();
        }
    }

    public void append(List<ItemBean> itemBeans) {
        if (itemBeans.size() > 0) {
            for (ItemBean itemBean : itemBeans) {
                mDatas.add(itemBean);
            }

            notifyDataSetChanged();
        }
    }

    public void replaceAll(List<ItemBean> itemBeans) {
        mDatas.clear();
        if (itemBeans.size() > 0) {
            mDatas.addAll(itemBeans);
            notifyDataSetChanged();
        }
    }

    public void removeAt(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void remove(ItemBean itemBean) {
        mDatas.remove(itemBean);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + mDatas.size();
    }




    public abstract void bindData(BaseRecyclerViewHolder holder, Object itemBean, int position,int viewType);

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER&&mHeaderViews.size()>0) {
            return new BaseRecyclerViewHolder(mHeaderViews.get(0));
        } else if (viewType == VIEW_TYPE_FOOT&&mFootViews.size()>0) {
            return new BaseRecyclerViewHolder(mFootViews.get(0));
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(viewTypes.getItemViewLayoutId(viewType), parent,
                    false);
            return new BaseRecyclerViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, final int position) {

        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }
        final int realPosition = position - numHeaders;
        int adapterCount = mDatas.size();
        if (realPosition < adapterCount) {
            bindData(holder, mDatas.get(realPosition), realPosition,getItemViewType(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder.itemView, mDatas.get(realPosition), realPosition);
                    }
                }
            });
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return VIEW_TYPE_HEADER;
        }
        int realPosition = position - numHeaders;
        int adapterCount = mDatas.size();
        if (realPosition < adapterCount) {
            return viewTypes.getItemViewType(position,mDatas.get(realPosition));
        }
        return VIEW_TYPE_FOOT;
    }



}
