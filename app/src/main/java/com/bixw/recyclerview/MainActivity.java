package com.bixw.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseMultiItemTypeRecyclerAdapter.OnRecyclerViewItemClickListener{

    WrapRecyclerView mRecyclerView;
    ArrayList<String> mDatas;
    final int LEFT=1;
    final int RIGHT=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        mRecyclerView= (WrapRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BaseMultiItemTypeRecyclerAdapter adapter= new BaseMultiItemTypeRecyclerAdapter(new IViewType(){

            @Override
            public int getItemViewLayoutId(int viewType) {
                int layoutId=R.layout.item;
                switch (viewType){
                    case RIGHT:
                        layoutId= R.layout.item2;
                        break;
                    case LEFT:
                        layoutId= R.layout.item;
                        break;
                }
                return layoutId;
            }

            @Override
            public int getItemViewType(int position,Object itemBean) {
                if(position%2==0){
                    return LEFT;
                }else{
                    return RIGHT;
                }
            }
        }) {
            @Override
            public void bindData(BaseRecyclerViewHolder holder, Object itemBean, int position, int viewType) {
              try {
                  switch (viewType){
                      case LEFT:
                          holder.setText(R.id.textView, itemBean.toString());
                          break;
                      case RIGHT:
                          holder.setText(R.id.textView2, itemBean.toString());
                          break;
                  }

              }catch(Exception e){
                  e.printStackTrace();
              }
            }
        };
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        View headerView= LayoutInflater.from(this).inflate(R.layout.header,null);
        View footView= LayoutInflater.from(this).inflate(R.layout.foot,null);
        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.addFootView(footView);
        adapter.append(mDatas);
    }

    protected void initData()
    {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            mDatas.add("" + i);
        }
    }


    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        Toast.makeText(MainActivity.this,"data:"+itemBean+"    position:"+position,Toast.LENGTH_SHORT).show();
    }
}
