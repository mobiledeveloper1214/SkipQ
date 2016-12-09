package com.skipq.android.controller;

import com.skipq.android.models.RequestBase;
import com.skipq.android.models.RequestUserChangePassword;
import com.skipq.android.models.RequestUserForgotPassword;
import com.skipq.android.models.RequestUserLogin;
import com.skipq.android.models.RequestUserProfileUpdate;
import com.skipq.android.models.RequestUserSignUp;
import com.skipq.android.models.ResponseBase;
import com.skipq.android.models.ResponseExploreBusinesses;
import com.skipq.android.models.ResponseUserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Content-Type: application/json; charset=UTF-8", "api-key: j05wd2ae49d212578ef13cb607cef64b"})
    @POST("user_login")
    Call<ResponseUserLogin> user_login(@Body RequestUserLogin requestUserLogin);

    @Headers({"Content-Type: application/json; charset=UTF-8", "api-key: j05wd2ae49d212578ef13cb607cef64b"})
    @POST("user_forgot_password")
    Call<ResponseBase> user_forgot_password(@Body RequestUserForgotPassword requestUserForgotPassword);

    @Headers({"Content-Type: application/json; charset=UTF-8", "api-key: j05wd2ae49d212578ef13cb607cef64b"})
    @POST("user_change_password")
    Call<ResponseBase> user_change_password(@Body RequestUserChangePassword requestUserChangePassword);

    @Headers({"Content-Type: application/json; charset=UTF-8", "api-key: j05wd2ae49d212578ef13cb607cef64b"})
    @POST("user_signup")
    Call<ResponseUserLogin> user_signup(@Body RequestUserSignUp requestUserSignUp);

    @Headers({"Content-Type: application/json; charset=UTF-8", "api-key: j05wd2ae49d212578ef13cb607cef64b"})
    @POST("user_profile_update")
    Call<ResponseUserLogin> user_profile_update(@Body RequestUserProfileUpdate requestUserProfileUpdate);

    @Headers({"Content-Type: application/json; charset=UTF-8", "api-key: j05wd2ae49d212578ef13cb607cef64b"})
    @POST("explore_businesses")
    Call<ResponseExploreBusinesses> explore_businesses(@Body RequestBase requestBase);
}
