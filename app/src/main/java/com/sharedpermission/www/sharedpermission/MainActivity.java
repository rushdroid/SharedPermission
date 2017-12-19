package com.sharedpermission.www.sharedpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sharedpermission.android.PermissionHandler;
import com.sharedpermission.android.SharedPrefrenceHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveModel();
        savaVariable();
        checkPermission();
    }

    private void saveModel() {
        User user = new User();
        user.setAddress("New York");
        user.setAge(23);
        user.setMobile("999999999");
        user.setPhoneNumber("88888888888");
        SharedPrefrenceHelper.getInstance(MainActivity.this).saveModel("Saveuser", user);
        User user1 = SharedPrefrenceHelper.getInstance(MainActivity.this).retriveModel("Saveuser", User.class);
        Log.d("USERDATA", " : " + user1.getAddress());
        Log.d("USERDATA", " : " + user1.getName());
        Log.d("USERDATA", " : " + user1.getMobile());
        Log.d("USERDATA", " : " + user1.getAge());
        Log.d("USERDATA", " : " + user1.getPhoneNumber());
    }

    private void savaVariable() {
        SharedPrefrenceHelper.getInstance(MainActivity.this).setStringShared("KeyString", "Hello");
        SharedPrefrenceHelper.getInstance(MainActivity.this).setBooleanShared("KeyBool", true);
        SharedPrefrenceHelper.getInstance(MainActivity.this).setIntShared("KeyInt", 100);


        Log.d("SharedData", "savaVariable: " + SharedPrefrenceHelper.getInstance(MainActivity.this).getStringShared("KeyString", ""));
        Log.d("SharedData", "savaVariable: " + SharedPrefrenceHelper.getInstance(MainActivity.this).getBooleanShared("KeyBool", true));
        Log.d("SharedData", "savaVariable: " + SharedPrefrenceHelper.getInstance(MainActivity.this).getIntShared("KeyInt", 00));
    }

    private void checkPermission() {
        if (PermissionHandler.CheckPermissionActivity("Storage", Manifest.permission.WRITE_EXTERNAL_STORAGE, MainActivity.this, 100)) {
            System.out.println("Permission Granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Do your Stuff On Permission Gramnted
            }else {
                //Do your Stuff On Permission Reject
                // You can Request your Permission second time
            }
        }
    }
}
