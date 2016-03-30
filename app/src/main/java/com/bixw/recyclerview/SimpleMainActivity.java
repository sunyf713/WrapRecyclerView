package com.bixw.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class SimpleMainActivity extends AppCompatActivity implements BaseSimpleItemTypeRecyclerAdapter.OnRecyclerViewItemClickListener{

    WrapRecyclerView mRecyclerView;
    ArrayList<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        mRecyclerView= (WrapRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BaseSimpleItemTypeRecyclerAdapter adapter=new BaseSimpleItemTypeRecyclerAdapter() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, Object obj, int position) {
                    holder.setText(R.id.textView,(String)obj);
            }
        };
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        View headerView= LayoutInflater.from(this).inflate(R.layout.header,null);
        View footView= LayoutInflater.from(this).inflate(R.layout.foot,null);
        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.addFooterView(footView);
        adapter.append(mDatas);
    }

    protected void initData()
    {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 60; i++)
        {
            mDatas.add("" + i);
        }
    }


    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        Toast.makeText(SimpleMainActivity.this,"data:"+itemBean+"    position:"+position,Toast.LENGTH_SHORT).show();
    }
}
