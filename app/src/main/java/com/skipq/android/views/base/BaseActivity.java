package com.skipq.android.views.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.skipq.android.AppConfig;
import com.skipq.android.R;
import com.skipq.android.controller.ApiClient;
import com.skipq.android.controller.ApiInterface;
import com.skipq.android.utilities.CommonUtils;

public class BaseActivity extends AppCompatActivity implements AppConfig {

    public CommonUtils commonUtils = CommonUtils.getInstance();
    public boolean isLoadingBase;
    public ProgressDialog mProgressDialog;
    public ApiInterface apiInterface = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this, R.style.ProgressDialogTransparentTheme);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void navWithFinish(Activity mActivity, Class mClass) {
        Intent intent = new Intent(mActivity, mClass);
        navWithFinish(mActivity, intent);
    }

    public void navWithFinish(Activity mActivity, Intent intent) {
        startActivity(intent);
        mActivity.finish();
    }

    public void navWithoutFinish(Activity mActivity, Class mClass) {
        Intent intent = new Intent(mActivity, mClass);
        navWithoutFinish(intent);
    }

    public void navWithoutFinish(Intent intent) {
        startActivity(intent);
    }

    public void setupUI(View view, Activity activity) {
        commonUtils.setupUI(view, activity);
    }
}

