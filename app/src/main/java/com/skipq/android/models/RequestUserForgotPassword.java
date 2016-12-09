package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class RequestUserForgotPassword {
    @SerializedName("user_email")
    private String user_email;

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
