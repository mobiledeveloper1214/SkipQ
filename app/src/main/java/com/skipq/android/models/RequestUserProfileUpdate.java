package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class RequestUserProfileUpdate {

    @SerializedName("user_id")
    private long user_id;

    @SerializedName("user_full_name")
    private String user_full_name;

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("user_mobile")
    private String user_mobile;

    @SerializedName("user_avatar")
    private String user_avatar;

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }
}
