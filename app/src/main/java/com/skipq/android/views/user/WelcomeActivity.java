package com.skipq.android.views.user;

import android.os.Bundle;

import com.skipq.android.R;
import com.skipq.android.views.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_up)
    public void onSignUp() {
        navWithFinish(this, SignUpActivity.class);
    }

    @OnClick(R.id.btn_sign_in)
    public void onSignIn() {
        navWithFinish(this, SignInActivity.class);
    }
}
