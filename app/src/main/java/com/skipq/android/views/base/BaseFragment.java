package com.skipq.android.views.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skipq.android.AppConfig;
import com.skipq.android.R;
import com.skipq.android.controller.ApiClient;
import com.skipq.android.controller.ApiInterface;
import com.skipq.android.utilities.CommonUtils;

public class BaseFragment extends Fragment implements AppConfig{
    public CommonUtils commonUtils = CommonUtils.getInstance();
    public boolean isLoadingBase;
    public ProgressDialog mProgressDialog;
    public ApiInterface apiInterface = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(getContext(), R.style.ProgressDialogTransparentTheme);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setupUI(View view, Activity activity) {
        commonUtils.setupUI(view, activity);
    }

    public void navWithoutFinish(Activity mActivity, Class mClass) {
        Intent intent = new Intent(mActivity, mClass);
        navWithoutFinish(intent);
    }

    public void navWithoutFinish(Intent intent) {
        startActivity(intent);
    }
}
