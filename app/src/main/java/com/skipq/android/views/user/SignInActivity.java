package com.skipq.android.views.user;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.skipq.android.AppController;
import com.skipq.android.R;
import com.skipq.android.models.RequestUserForgotPassword;
import com.skipq.android.models.RequestUserLogin;
import com.skipq.android.models.ResponseUserLogin;
import com.skipq.android.views.base.BaseActivity;
import com.skipq.android.views.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends BaseActivity {

    @BindView(R.id.activity_sign_in) RelativeLayout mParent;
    @BindView(R.id.et_email)         EditText etEmail;
    @BindView(R.id.et_password)      EditText etPassword;

    private RequestUserLogin userLoginRequestBody;
    private RequestUserForgotPassword userForgotPasswordRequestBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        ButterKnife.bind(this);

        initData();
        initUI();
    }

    private void initData() {
        userLoginRequestBody = new RequestUserLogin();
        userForgotPasswordRequestBody = new RequestUserForgotPassword();
    }

    private void initUI() {
        setupUI(mParent, this);

        etPassword.setTypeface(Typeface.DEFAULT);
        etPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.btn_sign_in)
    public void onSignIn() {
        if (validInfo()) {
            isLoadingBase = true;
            mProgressDialog.setMessage("Login...");
            mProgressDialog.show();

            Call<ResponseUserLogin> call = apiInterface.user_login(userLoginRequestBody);
            call.enqueue(new Callback<ResponseUserLogin>() {
                @Override
                public void onResponse(Call<ResponseUserLogin> call, Response<ResponseUserLogin> response) {
                    isLoadingBase = false;
                    mProgressDialog.dismiss();

                    if (response.body().getStatus() == 1) {
                        AppController.currentUser = response.body().getCurrent_user();

                        navWithFinish(SignInActivity.this, MainActivity.class);
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

    }

    @OnClick(R.id.btn_back)
    public void onBack() {
        navWithFinish(this, WelcomeActivity.class);
    }

    @OnClick(R.id.btn_forgot_password)
    public void onForgotPassword() {
        navWithFinish(this, ForgotPasswordActivity.class);
    }

    @OnClick(R.id.btn_sign_up)
    public void onSignUp() {
        navWithFinish(this, SignUpActivity.class);
    }

    private boolean validInfo() {
        boolean valid = false;
        View focusView = null;

        etEmail.setError(null);
        etPassword.setError(null);

        String strEmail    = etEmail   .getText().toString().trim();
        String strPassword = etPassword.getText().toString().trim();

        userLoginRequestBody.setUser_email(strEmail);
        userLoginRequestBody.setUser_password(strPassword);

        if (!commonUtils.isValidEmail(strEmail)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
        } else if (strPassword.length() < 6) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
        } else {
            valid = true;
        }

        if (!valid) focusView.requestFocus();

        return valid;
    }
}
