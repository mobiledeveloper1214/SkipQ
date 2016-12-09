package com.skipq.android.views.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skipq.android.R;
import com.skipq.android.views.base.BaseFragment;

import butterknife.ButterKnife;

public class ContactUsFragment extends BaseFragment {

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);

        initData();
        initUI();

        return view;
    }

    private void initData() {

    }

    private void initUI() {

    }
}