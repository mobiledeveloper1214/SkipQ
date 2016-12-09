package com.skipq.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skipq.android.R;
import com.skipq.android.models.Business;
import com.skipq.android.utilities.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.skipq.android.AppConfig.PATH_BUSINESS_LOGO;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Business> businesses;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_biz_logo)     CircleImageView ivBizLogo;
        @BindView(R.id.tv_biz_name)     TextView tvBizName;
        @BindView(R.id.tv_biz_category) TextView tvBizCategory;
        @BindView(R.id.tv_biz_status)   TextView tvBizStatus;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public ExploreAdapter(Context mContext, ArrayList<Business> businesses) {
        this.mContext  = mContext;
        this.businesses = businesses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CommonUtils commonUtils = CommonUtils.getInstance();

        Business business = businesses.get(position);

        Glide.with(mContext)
                .load(PATH_BUSINESS_LOGO + business.getBiz_logo_url())
                .crossFade()
                .centerCrop()
                .into(holder.ivBizLogo);

        holder.tvBizName.setText(business.getBiz_name());
        holder.tvBizCategory.setText(business.getBiz_category_name());
        holder.tvBizStatus.setText(business.getBiz_status() == 1 ? "Open now" : "Closed");
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }
}
