package com.sharedpermission.android;

/**
 * Created by Rushabh on 18-01-2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


public class PermissionHandler {

    public static boolean CheckPermissionActivity(String Text, String permission, Context mContext, int reqCode) {
        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
            reqPermission(permission, mContext, reqCode, Text);
            return false;
        } else {
            return true;
        }
    }

    public static boolean CheckPermissionFragment(Fragment fragment, String Text, String permission, Context mContext, int reqCode) {
        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
            reqFragmentPermission(fragment, (Activity) mContext, Text, reqCode, permission);
            return false;
        } else {
            return true;
        }
    }


    public static void reqFragmentPermission(Fragment fragment, final Activity mContext, final String Text, int PermissionCode, String Permission) {
        if (!fragment.shouldShowRequestPermissionRationale(Permission)) {
            fragment.requestPermissions(new String[]{Permission}, PermissionCode);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setCancelable(false);
            builder.setTitle("Permission");
            builder.setMessage("You needs to Grant Access " + Text + " From Settings >> AppInfo >> Permissions For Using Application");
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //
                    dialog.dismiss();
                    String packageName = Utils.getAppPackageName(mContext);
                    try {
                        //Open the specific App Info page:
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + packageName));
                        mContext.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //e.printStackTrace();
                        Toast.makeText(mContext, "You need to Grant Access " + Text + " From Settings >> AppInfo >> Permissions For Using Application", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // Create the AlertDialog object and return it
            builder.create().show();
        }
    }

    public static void reqPermission(String permission, final Context mContext, int reqCode, final String Message) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, permission)) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{permission}, reqCode);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setCancelable(false);
            builder.setTitle("Permission");
            builder.setMessage("You needs to Grant Access " + Message + " From Settings >> AppInfo >> Permissions For Using Application");
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //
                    dialog.dismiss();
                    String packageName = Utils.getAppPackageName(mContext);
                    try {
                        //Open the specific App Info page:
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + packageName));
                        mContext.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //e.printStackTrace();
                        Toast.makeText(mContext, "You need to Grant Access " + Message + " From Settings >> AppInfo >> Permissions For Using Application", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // Create the AlertDialog object and return it
            builder.create().show();
        }
    }

}
