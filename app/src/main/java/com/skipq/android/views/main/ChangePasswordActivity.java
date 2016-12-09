package com.skipq.android.views.main;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.skipq.android.AppController;
import com.skipq.android.R;
import com.skipq.android.models.RequestUserChangePassword;
import com.skipq.android.models.ResponseBase;
import com.skipq.android.views.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.activity_change_password) LinearLayout mParent;
    @BindView(R.id.toolbar)                  Toolbar      mToolbar;
    @BindView(R.id.et_old_password)          EditText     etOldPassword;
    @BindView(R.id.et_new_password)          EditText     etNewPassword;
    @BindView(R.id.et_confirm_password)      EditText     etConfirmPassword;

    private Context mContext;
    private RequestUserChangePassword requestBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);

        initData();
        initUI();
    }

    private void initData() {
        mContext = this;
        requestBody = new RequestUserChangePassword();
    }

    private void initUI() {
        setupUI(mParent, this);

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etOldPassword.setTypeface(Typeface.DEFAULT);
        etOldPassword.setTransformationMethod(new PasswordTransformationMethod());

        etNewPassword.setTypeface(Typeface.DEFAULT);
        etNewPassword.setTransformationMethod(new PasswordTransformationMethod());

        etConfirmPassword.setTypeface(Typeface.DEFAULT);
        etConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.btn_save)
    public void onSave() {
        if (validInfo()) {
            isLoadingBase = true;
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.show();

            Call<ResponseBase> call = apiInterface.user_change_password(requestBody);
            call.enqueue(new Callback<ResponseBase>() {
                @Override
                public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
                    isLoadingBase = false;
                    mProgressDialog.dismiss();

                    if (response.body().getStatus() == 1) {
                        AlertDialog dialog = commonUtils.createAlertDialog(mContext, R.string.title_success, response.body().getMsg());
                        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
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

    private boolean validInfo() {
        boolean valid = false;
        View focusView = null;

        etOldPassword.setError(null);
        etNewPassword.setError(null);
        etConfirmPassword.setError(null);

        String strOldPassword     = etOldPassword    .getText().toString().trim();
        String strNewPassword     = etNewPassword    .getText().toString().trim();
        String strConfirmPassword = etConfirmPassword.getText().toString().trim();

        requestBody.setUser_id(AppController.currentUser.getUser_id());
        requestBody.setUser_old_password(strOldPassword);
        requestBody.setUser_new_password(strNewPassword);

        if (strOldPassword.isEmpty()) {
            etOldPassword.setError(getString(R.string.error_field_required));
            focusView = etOldPassword;
        } else if (strNewPassword.length() < 6) {
            etNewPassword.setError(getString(R.string.error_invalid_password));
            focusView = etNewPassword;
        } else if (!strNewPassword.equals(strConfirmPassword)) {
            etConfirmPassword.setError(getString(R.string.error_not_match_password));
            focusView = etConfirmPassword;
        } else {
            valid = true;
        }

        if (!valid) focusView.requestFocus();

        return valid;
    }
}
