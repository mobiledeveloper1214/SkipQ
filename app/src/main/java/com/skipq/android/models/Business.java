package com.skipq.android.models;

import com.google.gson.annotations.SerializedName;

public class Business {
    @SerializedName("biz_id")
    private long biz_id;

    @SerializedName("biz_name")
    private String biz_name;

    @SerializedName("biz_category_name")
    private String biz_category_name;

    @SerializedName("biz_description")
    private String biz_description;

    @SerializedName("biz_counter_num")
    private int biz_counter_num;

    @SerializedName("biz_phone_number")
    private String biz_phone_number;

    @SerializedName("biz_logo_url")
    private String biz_logo_url;

    @SerializedName("biz_website")
    private String biz_website;

    @SerializedName("biz_address")
    private String biz_address;

    @SerializedName("biz_location_latitude")
    private double biz_location_latitude;

    @SerializedName("biz_location_longitude")
    private double biz_location_longitude;

    @SerializedName("biz_open_days")
    private String biz_open_days;

    @SerializedName("biz_status")
    private int biz_status;

    public long getBiz_id() {
        return biz_id;
    }

    public String getBiz_name() {
        return biz_name;
    }

    public String getBiz_category_name() {
        return biz_category_name;
    }

    public String getBiz_description() {
        return biz_description;
    }

    public int getBiz_counter_num() {
        return biz_counter_num;
    }

    public String getBiz_phone_number() {
        return biz_phone_number;
    }

    public String getBiz_logo_url() {
        return biz_logo_url;
    }

    public String getBiz_website() {
        return biz_website;
    }

    public String getBiz_address() {
        return biz_address;
    }

    public double getBiz_location_latitude() {
        return biz_location_latitude;
    }

    public double getBiz_location_longitude() {
        return biz_location_longitude;
    }

    public String getBiz_open_days() {
        return biz_open_days;
    }

    public int getBiz_status() {
        return biz_status;
    }
}
