package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class RequestUserLogin {
    @SerializedName("user_email")
    private String user_email;

    @SerializedName("user_password")
    private String user_password;

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
