package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private long user_id;

    @SerializedName("user_full_name")
    private String user_full_name;

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("user_mobile")
    private String user_mobile;

    @SerializedName("user_password")
    private String user_password;

    @SerializedName("user_avatar_url")
    private String user_avatar_url;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }
}
