package com.skyline.kotlin1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SecondActivity extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<>();
    private RecyclerView recycler_view;
    private SwipeRefreshLayout swipe_view;
    private Context context;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView() {
        recycler_view = findViewById(R.id.recycler_view);
        swipe_view = findViewById(R.id.swipe_view);
        swipe_view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_view.setRefreshing(true);
                swipe_view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        swipe_view.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new RecyclerAdapter();
        recycler_view.setAdapter(mAdapter);
        initData();
    }

    private void initData() {
        int size = data.size();
        for (int i=size; i < size + 50; i++) {
            data.add("position"+i + "  java  activity ");
        }
        mAdapter.notifyDataSetChanged();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.listarray_item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String item = data.get(position);
            holder.getNum().setText(item);
            holder.getNum().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

}
