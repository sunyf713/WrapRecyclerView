package com.bixw.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseRecyclerAdapter.OnRecyclerViewItemClickListener {

    PtrRecyclerView mRecyclerView;
    Context context;
    ArrayList<String> mDatas;
    final int LEFT = 1;
    final int RIGHT = 2;
    BaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mRecyclerView = (PtrRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setMode(PtrRecyclerView.RecyclerMode.BOTTOM);
        PullRefreshHeader header = new PullRefreshHeader(this);
        mRecyclerView.setPullRefreshHeaderView(header);
         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mAdapter = new BaseRecyclerAdapter(new IViewType() {

            @Override
            public int getItemViewLayoutId(int viewType) {
                int layoutId = R.layout.item;
                switch (viewType) {
                    case RIGHT:
                        layoutId = R.layout.item2;
                        break;
                    case LEFT:
                        layoutId = R.layout.item;
                        break;
                }
                return layoutId;
            }

            @Override
            public int getItemViewType(int position, Object itemBean) {
                if (position % 2 == 0) {
                    return LEFT;
                } else {
                    return RIGHT;
                }
            }
        }) {
            @Override
            public void bindData(BaseRecyclerViewHolder holder, Object itemBean, int position,
                                 int viewType) {
                try {
                    switch (viewType) {
                        case LEFT:
                            holder.setText(R.id.textView, itemBean.toString());
                            break;
                        case RIGHT:
                            holder.setText(R.id.textView2, itemBean.toString());
                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        View headerView = LayoutInflater.from(this).inflate(R.layout.header, mRecyclerView, false);
        View headerView2 = LayoutInflater.from(this).inflate(R.layout.header2, mRecyclerView, false);
        View footView = LayoutInflater.from(this).inflate(R.layout.foot, mRecyclerView, false);
        View footView2 = LayoutInflater.from(this).inflate(R.layout.foot2, mRecyclerView, false);

        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.addHeaderView(headerView2);
        mRecyclerView.addFooterView(footView);
        mRecyclerView.addFooterView(footView2);


        mRecyclerView.setOnPullRefreshListener(new PtrRecyclerView.OnPullRefreshListener() {
            @Override
            public void onPullRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.append("new");
                        mRecyclerView.pullRefreshComplete();
                    }
                }, 1500);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new PtrRecyclerView.OnLoadMoreListener() {
            @Override
            public void onloadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 6; i++) {
                            mAdapter.append("" + i);
                        }
                        mRecyclerView.loadMoreComplete();
                        mRecyclerView.noMoreData();
                    }
                }, 1500);

            }
        });

        initData();
        mAdapter.append(mDatas);
    }

    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            mDatas.add("" + i);
        }
    }


    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        Toast.makeText(MainActivity.this, "data:" + itemBean + "    position:" + position, Toast.LENGTH_SHORT).show();
    }
}
