package com.skipq.android.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.skipq.android.R;
import com.skipq.android.utilities.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyQueuesAdapter extends RecyclerView.Adapter<MyQueuesAdapter.ViewHolder> {
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_container) LinearLayout mContainer;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public MyQueuesAdapter(Context mContext) {
        this.mContext  = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_queue, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CommonUtils commonUtils = CommonUtils.getInstance();

        holder.mContainer.setBackgroundColor(ContextCompat.getColor(mContext, commonUtils.isOdd(position) ? R.color.colorTextHint : android.R.color.white));

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
