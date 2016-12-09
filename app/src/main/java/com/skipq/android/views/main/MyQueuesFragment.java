package com.skipq.android.views.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skipq.android.R;
import com.skipq.android.adapters.MyQueuesAdapter;
import com.skipq.android.utilities.recycler.RecyclerItemClickListener;
import com.skipq.android.views.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyQueuesFragment extends BaseFragment {

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;


    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private MyQueuesAdapter mMyQueuesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_queues, container, false);
        ButterKnife.bind(this, view);

        initData();
        initUI();

        return view;
    }

    private void initData() {

    }

    private void initUI() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mMyQueuesAdapter = new MyQueuesAdapter(mContext);
        mRecyclerView.setAdapter(mMyQueuesAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("SHI", position + "");
            }
        }));
    }
}