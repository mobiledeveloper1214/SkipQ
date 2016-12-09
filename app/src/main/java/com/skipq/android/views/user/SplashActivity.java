package com.skipq.android.views.user;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.skipq.android.R;
import com.skipq.android.views.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @BindView(R.id.iv_splash) ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        initData();
        initUI();
    }

    private void initData() {

    }

    private void initUI() {
        startAnimation();
        startSplash();
    }

    private void startAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(SPLASH_DISPLAY_LENGTH);
        alphaAnimation.setStartOffset(0);
        alphaAnimation.setFillAfter(true);
        ivSplash.startAnimation(alphaAnimation);
    }

    private void startSplash() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                navWithFinish(SplashActivity.this, WelcomeActivity.class);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
