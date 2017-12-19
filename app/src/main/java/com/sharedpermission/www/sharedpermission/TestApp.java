package com.sharedpermission.www.sharedpermission;

import android.app.Application;

import com.sharedpermission.android.SharedPrefrenceHelper;

/**
 * Created by Rushabh-qs on 19-12-2017.
 */

public class TestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper(this);//if you want to give your name for Sharedpref File
        sharedPrefrenceHelper.setSharedPrefName("Your Pref name"); // Give Your Name
    }

}
