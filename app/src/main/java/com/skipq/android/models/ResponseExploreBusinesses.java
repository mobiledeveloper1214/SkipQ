package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseExploreBusinesses {

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("businesses")
    private ArrayList<Business> businesses;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<Business> getBusinesses() {
        return businesses;
    }
}
