package com.skipq.android.views.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.skipq.android.R;
import com.skipq.android.models.RequestUserForgotPassword;
import com.skipq.android.models.ResponseBase;
import com.skipq.android.views.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {

    @BindView(R.id.activity_forgot_password) RelativeLayout mParent;
    @BindView(R.id.et_email)                 EditText etEmail;

    private RequestUserForgotPassword requestBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgot_password);

        ButterKnife.bind(this);

        initData();
        initUI();
    }

    private void initData() {
        requestBody = new RequestUserForgotPassword();
    }

    private void initUI() {
        setupUI(mParent, this);
    }

    @OnClick(R.id.btn_submit)
    public void onSubmit() {
        if (validInfo()) {
            isLoadingBase = true;
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.show();

            Call<ResponseBase> call = apiInterface.user_forgot_password(requestBody);
            call.enqueue(new Callback<ResponseBase>() {
                @Override
                public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
                    isLoadingBase = false;
                    mProgressDialog.dismiss();

                    if (response.body().getStatus() == 1) {
                        AlertDialog dialog = commonUtils.createAlertDialog(ForgotPasswordActivity.this, R.string.title_success, response.body().getMsg());
                        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                onBack();
                            }
                        });

                        dialog.show();
                    } else {
                        Snackbar.make(mParent, response.body().getMsg(), Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBase> call, Throwable t) {
                    isLoadingBase = false;
                    mProgressDialog.dismiss();
                    Snackbar.make(mParent, t.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

    }

    @OnClick(R.id.btn_back)
    public void onBack() {
        navWithFinish(this, SignInActivity.class);
    }

    private boolean validInfo() {
        boolean valid = false;
        View focusView = null;

        etEmail.setError(null);

        String strEmail           = etEmail          .getText().toString().trim();

        requestBody.setUser_email(strEmail);

        if (!commonUtils.isValidEmail(strEmail)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
        } else {
            valid = true;
        }

        if (!valid) focusView.requestFocus();

        return valid;
    }
}
