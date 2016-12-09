package com.skipq.android;

import android.location.Location;

import com.skipq.android.models.Business;
import com.skipq.android.models.User;

import java.util.ArrayList;

public class AppController {
    public static Location mLocation = null;

    public static User currentUser = null;
    public static ArrayList<Business> businesses = null;
}
