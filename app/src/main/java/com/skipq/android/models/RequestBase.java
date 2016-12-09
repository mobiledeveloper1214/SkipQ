package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class RequestBase {
    @SerializedName("user_id")
    private long user_id;

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
