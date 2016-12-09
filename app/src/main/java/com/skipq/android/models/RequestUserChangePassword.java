package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class RequestUserChangePassword {
    @SerializedName("user_id")
    private long user_id;

    @SerializedName("user_old_password")
    private String user_old_password;

    @SerializedName("user_new_password")
    private String user_new_password;

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setUser_old_password(String user_old_password) {
        this.user_old_password = user_old_password;
    }

    public void setUser_new_password(String user_new_password) {
        this.user_new_password = user_new_password;
    }
}
