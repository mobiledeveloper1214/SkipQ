package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class ResponseUserLogin {

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("current_user")
    private User current_user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(User current_user) {
        this.current_user = current_user;
    }
}
