package com.sharedpermission.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Rushabh on 12/18/17.
 */

public class SharedPrefrenceHelper {
    public static String PREF_NAME = String.valueOf(R.string.app_name);
    public static SharedPrefrenceHelper sharedPrefrenceHelper;
    static Context context;

    public static SharedPrefrenceHelper getInstance(Context mContext) {
        if (context == null) {
            context = mContext;
        }
        if (sharedPrefrenceHelper == null) {
            sharedPrefrenceHelper = new SharedPrefrenceHelper(mContext);
        }
        return sharedPrefrenceHelper;
    }

    public SharedPrefrenceHelper(Context mContext) {
        setSharedPrefName(mContext.getPackageName());
    }

    public void setSharedPrefName(String PrefName) {
        PREF_NAME = PrefName;
    }

    /**
     * Set a string shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setStringShared(String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set a integer shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setIntShared(String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Set a Boolean shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setBooleanShared(String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a string shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public String getStringShared(String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        return settings.getString(key, defValue);
    }

    /**
     * Get a integer shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public int getIntShared(String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        return settings.getInt(key, defValue);
    }

    /**
     * Get a boolean shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public boolean getBooleanShared(String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        return settings.getBoolean(key, defValue);
    }

    public void clearAllSharedData() {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

    public <T extends Serializable> T stringToObjectS(String string) {
        byte[] bytes = Base64.decode(string, 0);
        T object = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            object = (T) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public String objectToString(Parcelable object) {

        String encoded = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            encoded = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    public <T> T retriveModel(String key, Class<T> theClass) {
        Gson gson = new Gson();
        String UserInfo = getStringShared(key, "");
        return new Gson().fromJson(UserInfo, theClass);
    }

    public void saveModel(String key, Object theClass) {
        String str = new Gson().toJson(theClass);
        setStringShared(key, str);
    }
}
