package com.skipq.android.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.skipq.android.AppController;
import com.skipq.android.R;
import com.skipq.android.adapters.ExploreAdapter;
import com.skipq.android.models.RequestBase;
import com.skipq.android.models.ResponseExploreBusinesses;
import com.skipq.android.utilities.recycler.RecyclerItemClickListener;
import com.skipq.android.views.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreFragment extends BaseFragment {

    @BindView(R.id.fragment_explore) RelativeLayout mParent;
    @BindView(R.id.recycler_view)    RecyclerView mRecyclerView;


    private Context mContext;
    private LinearLayoutManager mLayoutManager;
    private ExploreAdapter mExploreAdapter;

    private RequestBase requestBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);

        initData();
        initUI();

        return view;
    }

    private void initData() {
        requestBody = new RequestBase();
        requestBody.setUser_id(AppController.currentUser.getUser_id());

        if (AppController.businesses == null)
            explore_businesses();
        else
            display_businesses();
    }

    private void initUI() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, EnterQueueActivity.class);
                intent.putExtra(INDEX, position);
                navWithoutFinish(intent);
            }
        }));
    }

    private void explore_businesses() {
        isLoadingBase = true;
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();

        Call<ResponseExploreBusinesses> call = apiInterface.explore_businesses(requestBody);
        call.enqueue(new Callback<ResponseExploreBusinesses>() {
            @Override
            public void onResponse(Call<ResponseExploreBusinesses> call, Response<ResponseExploreBusinesses> response) {
                isLoadingBase = false;
                mProgressDialog.dismiss();

                if (response.body().getStatus() == 1) {
                    AppController.businesses = response.body().getBusinesses();
                    display_businesses();
                } else {
                    Snackbar.make(mParent, response.body().getMsg(), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseExploreBusinesses> call, Throwable t) {
                isLoadingBase = false;
                mProgressDialog.dismiss();
                Snackbar.make(mParent, t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void display_businesses() {
        mExploreAdapter = new ExploreAdapter(mContext, AppController.businesses);
        mRecyclerView.setAdapter(mExploreAdapter);
    }
}