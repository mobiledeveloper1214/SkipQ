package com.skipq.android.views.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skipq.android.AppController;
import com.skipq.android.R;
import com.skipq.android.views.base.BaseActivity;
import com.skipq.android.views.user.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.activity_main)   DrawerLayout mParent;
    @BindView(R.id.main_content)    LinearLayout mMainContent;
    @BindView(R.id.toolbar)         Toolbar mToolbar;
    @BindView(R.id.content_frame)   FrameLayout mContentFrame;
    @BindView(R.id.navigation_view) NavigationView mNavigationView;
    @BindView(R.id.toolbar_title)   TextView mToolbarTitle;

    private int selectedFragmentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        setToolbar();
        setDrawerMenu();

        performMenuAction(0);
    }

    private void performMenuAction(int position) {
        if (position == selectedFragmentIndex) {
            mParent.closeDrawer(mNavigationView);
            return;
        }
        Fragment fragment = null;
        String strTitle = null;
        switch (position) {
            case 0:
                fragment = new MyQueuesFragment();
                strTitle = "My Queues";
                break;
            case 1:
                fragment = new ExploreFragment();
                strTitle = "Explore";
                break;
            case 2:
                fragment = new MyProfileFragment();
                strTitle = "My Profile";
                break;
            case 3:
                fragment = new ContactUsFragment();
                strTitle = "Contact Us";
                break;
            case 4:
                commonUtils.logOut(this);
                navWithFinish(this, SignInActivity.class);
                break;
        }

        if (fragment != null) {
            mParent.closeDrawer(mNavigationView);
            selectedFragmentIndex = position;
            getSupportFragmentManager().beginTransaction().replace(mContentFrame.getId(), fragment).commit();
            mToolbarTitle.setText(strTitle);
        }
    }

    private void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mParent.isDrawerOpen(mNavigationView))
                    mParent.closeDrawer(mNavigationView);
                else
                    mParent.openDrawer(mNavigationView);
            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mParent, R.string.str_navigation_drawer_open, R.string.str_navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mNavigationView.getMenu().getItem(selectedFragmentIndex).setChecked(true);
            }
        };
        mParent.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setDrawerMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        CircleImageView ivAvatar = (CircleImageView) headerLayout.findViewById(R.id.iv_avatar);
        TextView tvName = (TextView) headerLayout.findViewById(R.id.tv_name);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.drawer_my_queues:
                        performMenuAction(0);
                        break;
                    case R.id.drawer_explore:
                        performMenuAction(1);
                        break;
                    case R.id.drawer_my_profile:
                        performMenuAction(2);
                        break;
                    case R.id.drawer_contact_us:
                        performMenuAction(3);
                        break;
                    case R.id.drawer_log_out:
                        performMenuAction(4);
                        break;
                }

                return true;
            }
        });

        Glide.with(this)
                .load(PATH_USER_AVATAR + AppController.currentUser.getUser_avatar_url())
                .crossFade()
                .centerCrop()
                .into(ivAvatar);

        tvName.setText(AppController.currentUser.getUser_full_name());
    }

    @Override
    public void onBackPressed() {

    }
}
