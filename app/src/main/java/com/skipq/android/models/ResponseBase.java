package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class ResponseBase {

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

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
}
