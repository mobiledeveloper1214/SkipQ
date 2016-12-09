package com.skipq.android.views.main;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.skipq.android.AppController;
import com.skipq.android.R;
import com.skipq.android.models.RequestUserProfileUpdate;
import com.skipq.android.models.ResponseUserLogin;
import com.skipq.android.views.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileFragment extends BaseFragment {
    private final int CHOOSE_PHOTO_REQUEST_CODE = 0;
    private final int TAKE_PHOTO_REQUEST_CODE   = 1;

    @BindView(R.id.fragment_my_profile) CoordinatorLayout mParent;
    @BindView(R.id.iv_avatar)           CircleImageView ivAvatar;
    @BindView(R.id.iv_avatar_edit)      CircleImageView ivAvatarEdit;
    @BindView(R.id.et_full_name)        EditText etFullName;
    @BindView(R.id.et_email)            EditText etEmail;
    @BindView(R.id.et_mobile)           EditText etMobile;

    private Context mContext;
    private boolean isEditable;
    private String strAvatar, strFullName, strEmail, strMobile;
    private RequestUserProfileUpdate requestBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ButterKnife.bind(this, view);

        initData();
        initUI(view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_my_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                if (isEditable) {
                    updateProfile();
                }

                isEditable = !isEditable;
                setEnabled();
                item.setIcon(isEditable ? R.drawable.ic_check: R.drawable.ic_edit);
                item.setTitle(isEditable ? R.string.menu_done: R.string.menu_edit);
                break;

        }
        return true;
    }

    private void initData() {
        mContext = getContext();
        isEditable = false;
        requestBody = new RequestUserProfileUpdate();

        strAvatar = "";
        strFullName = AppController.currentUser.getUser_full_name();
        strEmail    = AppController.currentUser.getUser_email();
        strMobile   = AppController.currentUser.getUser_mobile();
    }

    private void initUI(View view) {
        setupUI(mParent, getActivity());
        setEnabled();


        Glide.with(mContext)
                .load(PATH_USER_AVATAR + AppController.currentUser.getUser_avatar_url())
                .crossFade()
                .centerCrop()
                .into(ivAvatar);

        etFullName.setText(strFullName);
        etEmail.setText(strEmail);
        etMobile.setText(strMobile);
    }

    @OnClick(R.id.iv_avatar_edit)
    public void onAvatarEdit() {
        final Dialog dialog = new BottomSheetDialog(mContext);
        dialog.setContentView(R.layout.dialog_choose_avatar);
        dialog.setCancelable(true);

        Button btnChoose = (Button) dialog.findViewById(R.id.btn_choose);
        Button btnTake   = (Button) dialog.findViewById(R.id.btn_take);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAvatar();
                dialog.dismiss();
            }
        });

        if (btnTake != null) {
            btnTake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    takeAvatar();
                    dialog.dismiss();
                }
            });
        }

        dialog.show();
    }

    @OnClick(R.id.btn_change_password)
    public void onChangePassword() {
        navWithoutFinish(getActivity(), ChangePasswordActivity.class);
    }

    private void setEnabled() {
        etFullName.setEnabled(isEditable);
        etEmail.setEnabled(isEditable);
        etMobile.setEnabled(isEditable);

        ivAvatarEdit.setVisibility(isEditable ? View.VISIBLE : View.INVISIBLE);
    }


//    Profile Avatar
    private void chooseAvatar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), CHOOSE_PHOTO_REQUEST_CODE);
    }

    private void takeAvatar() {
        new Permissive.Request(Manifest.permission.CAMERA)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePhotoIntent.resolveActivity(mContext.getPackageManager()) != null) {
                            startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST_CODE);
                        }
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
                        Snackbar.make(mParent, "You must allow this permission to capture the photo", Snackbar.LENGTH_LONG).show();
                    }
                })
                .execute(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_PHOTO_REQUEST_CODE) {
                Uri uri = data.getData();
                Glide.with(mContext)
                        .load(uri)
                        .centerCrop()
                        .crossFade()
                        .into(ivAvatar);

                Glide.with(mContext)
                        .load(uri)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                strAvatar = commonUtils.bitmapToBase64(resource);
                            }
                        });
            }

            if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                if (imageBitmap != null) {
                    ivAvatar.setImageBitmap(imageBitmap);
                    strAvatar = commonUtils.bitmapToBase64(imageBitmap);
                }
            }
        }
    }

    private void updateProfile() {
        if (validInfo()) {
            isLoadingBase = true;
            mProgressDialog.setMessage("Updating...");
            mProgressDialog.show();

            Call<ResponseUserLogin> call = apiInterface.user_profile_update(requestBody);
            call.enqueue(new Callback<ResponseUserLogin>() {
                @Override
                public void onResponse(Call<ResponseUserLogin> call, Response<ResponseUserLogin> response) {
                    isLoadingBase = false;
                    mProgressDialog.dismiss();

                    if (response.body().getStatus() == 1) {
                        AppController.currentUser = response.body().getCurrent_user();

                        AlertDialog dialog = commonUtils.createAlertDialog(mContext, R.string.title_success, response.body().getMsg());
                        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
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

    private boolean validInfo() {
        boolean valid = false;
        View focusView = null;

        etFullName.setError(null);
        etEmail.setError(null);
        etMobile.setError(null);

        String strFullName        = etFullName       .getText().toString().trim();
        String strEmail           = etEmail          .getText().toString().trim();
        String strMobile          = etMobile         .getText().toString().trim();

        requestBody.setUser_id(AppController.currentUser.getUser_id());
        requestBody.setUser_full_name(strFullName);
        requestBody.setUser_email(strEmail);
        requestBody.setUser_mobile(strMobile);
        requestBody.setUser_avatar(strAvatar);

        if (strFullName.isEmpty()) {
            etFullName.setError(getString(R.string.error_field_required));
            focusView = etFullName;
        } else if (!commonUtils.isValidEmail(strEmail)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
        } else if (strMobile.isEmpty()) {
            etMobile.setError(getString(R.string.error_field_required));
            focusView = etMobile;
        } else {
            valid = true;
        }

        if (!valid) focusView.requestFocus();

        return valid;
    }
}