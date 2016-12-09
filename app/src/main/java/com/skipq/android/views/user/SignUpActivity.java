package com.skipq.android.views.user;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.skipq.android.AppController;
import com.skipq.android.R;
import com.skipq.android.models.RequestUserSignUp;
import com.skipq.android.models.ResponseUserLogin;
import com.skipq.android.views.base.BaseActivity;
import com.skipq.android.views.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.activity_sign_up)    RelativeLayout mParent;
    @BindView(R.id.et_full_name)        EditText       etFullName;
    @BindView(R.id.et_email)            EditText       etEmail;
    @BindView(R.id.et_mobile)           EditText       etMobile;
    @BindView(R.id.et_password)         EditText       etPassword;
    @BindView(R.id.et_confirm_password) EditText       etConfirmPassword;

    private RequestUserSignUp requestBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        ButterKnife.bind(this);

        initData();
        initUI();
    }



    private void initData() {
        requestBody = new RequestUserSignUp();
    }

    private void initUI() {
        setupUI(mParent, this);

        etPassword.setTypeface(Typeface.DEFAULT);
        etPassword.setTransformationMethod(new PasswordTransformationMethod());

        etConfirmPassword.setTypeface(Typeface.DEFAULT);
        etConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.btn_sign_up)
    public void onSignUp() {
        if (validInfo()) {
            isLoadingBase = true;
            mProgressDialog.setMessage("Sign up...");
            mProgressDialog.show();

            Call<ResponseUserLogin> call = apiInterface.user_signup(requestBody);
            call.enqueue(new Callback<ResponseUserLogin>() {
                @Override
                public void onResponse(Call<ResponseUserLogin> call, Response<ResponseUserLogin> response) {
                    isLoadingBase = false;
                    mProgressDialog.dismiss();

                    if (response.body().getStatus() == 1) {
                        AppController.currentUser = response.body().getCurrent_user();

                        AlertDialog dialog = commonUtils.createAlertDialog(SignUpActivity.this, R.string.title_success, response.body().getMsg());
                        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                navWithFinish(SignUpActivity.this, MainActivity.class);
                            }
                        });
                        dialog.show();
                    } else {
                        Snackbar.make(mParent, response.body().getMsg(), Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseUserLogin> call, Throwable t) {
                    isLoadingBase = false;
                    mProgressDialog.dismiss();
                    Snackbar.make(mParent, t.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    @OnClick(R.id.btn_back)
    public void onBack() {
        navWithFinish(this, WelcomeActivity.class);
    }

    @OnClick(R.id.btn_sign_in)
    public void onSignIn() {
        navWithFinish(this, SignInActivity.class);
    }

    private boolean validInfo() {
        boolean valid = false;
        View focusView = null;

        etFullName.setError(null);
        etEmail.setError(null);
        etMobile.setError(null);
        etPassword.setError(null);
        etConfirmPassword.setError(null);

        String strFullName        = etFullName       .getText().toString().trim();
        String strEmail           = etEmail          .getText().toString().trim();
        String strMobile          = etMobile         .getText().toString().trim();
        String strPassword        = etPassword       .getText().toString().trim();
        String strConfirmPassword = etConfirmPassword.getText().toString().trim();

        requestBody.setUser_full_name(strFullName);
        requestBody.setUser_email(strEmail);
        requestBody.setUser_mobile(strMobile);
        requestBody.setUser_password(strPassword);

        if (strFullName.isEmpty()) {
            etFullName.setError(getString(R.string.error_field_required));
            focusView = etFullName;
        } else if (!commonUtils.isValidEmail(strEmail)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
        } else if (strPassword.length() < 6) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
        } else if (!strPassword.equals(strConfirmPassword)) {
            etConfirmPassword.setError(getString(R.string.error_not_match_password));
            focusView = etConfirmPassword;
        } else {
            valid = true;
        }

        if (!valid) focusView.requestFocus();

        return valid;
    }
}
