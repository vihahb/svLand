package com.goldit.managerinfo.coreapi.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.goldit.managerinfo.BaseApplication;

import java.util.Set;


public class SharedPrefUtils {

    public static final String SHAREPREF_NAME = "VW";

    // // internal handle

    public SecurePreferences preferences;

    /**
     * @param context your current context.
     */
    public SharedPrefUtils(Context context) {
        this.preferences = new SecurePreferences(context);
    }

    private void putStringOrReplace(String key, String value) {
        if (value == null) {
            preferences.edit().remove(key).commit();
        } else {
            preferences.edit().putString(key, value).commit();
        }
    }

    private String getStringFromSharedPre(String key, String defValue) {
        if (preferences.contains(key)) {
            return preferences.getString(key, defValue);
        }
        return defValue;
    }

    private void putBooleanToShare(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    private Boolean getBooleanFromShare(String key, boolean defValue) {
        if (preferences.contains(key)) {
            return preferences.getBoolean(key, defValue);
        } else {
            return defValue;
        }
    }

    private void putIntToShare(String key, int value) {
        preferences.edit().putInt(key, value).commit();
    }

    private int getIntFromShare(String key, int defValue) {
        if (preferences.contains(key)) {
            return preferences.getInt(key, defValue);
        } else {
            return defValue;
        }
    }

    private Set<String> getArrayString(String key, Set<String> defValue) {
        if (preferences.contains(key)) {
            return preferences.getStringSet(key, defValue);
        } else {
            return defValue;
        }
    }

    private void putLongToShare(String key, long value) {
        preferences.edit().putLong(key, value).commit();
    }

    private long getLongFromShare(String key, long defValue) {
        if (preferences.contains(key)) {
            return preferences.getLong(key, defValue);
        } else {
            return defValue;
        }
    }

    private void putFloatToShare(String key, float value) {
        preferences.edit().putFloat(key, value).commit();
    }


    private void putArrayList(String key, Set<String> value) {
        preferences.edit().putStringSet(key, value).commit();
    }

    private float getFloatFromShare(String key, float defValue) {
        if (preferences.contains(key)) {
            return preferences.getFloat(key, defValue);
        } else {
            return defValue;
        }
    }

    private boolean containKEY(String key) {
        return preferences.contains(key);
    }

    private void deleteKey(String key) {
        if (preferences.contains(key)) {
            preferences.edit().remove(key).commit();
        }
    }

    public static void setString(Context context, String key, String values) {
        SharedPreferences.Editor editor = context.getSharedPreferences("advertises", Context.MODE_PRIVATE).edit();
        editor.putString(key, values);
        editor.commit();
    }

    public static void setBoolean(Context context, String key, boolean values) {
        SharedPreferences.Editor editor = context.getSharedPreferences("advertises", Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, values);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("advertises", Context.MODE_PRIVATE);
        String restoredText = prefs.getString(key, null);
        return restoredText;
    }

    public static Boolean getBolean(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("advertises", Context.MODE_PRIVATE);
        Boolean restoredText = prefs.getBoolean(key, false);
        return restoredText;
    }


    private void clearAllData() {
        preferences.edit().clear().commit();
    }


    public static void putString(String key, String value) {
        BaseApplication.getSharedPreferences().putStringOrReplace(key, value);
    }

    public static String getString(String key, String defValue) {
        return BaseApplication.getSharedPreferences().getStringFromSharedPre(key, defValue);
    }

    public static void putInt(String key, int value) {
        BaseApplication.getSharedPreferences().putIntToShare(key, value);
    }

    public static int getInt(String key, int defValue) {
        return BaseApplication.getSharedPreferences().getIntFromShare(key, defValue);
    }

    public static void putLong(String key, long value) {
        BaseApplication.getSharedPreferences().putLongToShare(key, value);
    }

    public static long getLong(String key, int defValue) {
        return BaseApplication.getSharedPreferences().getLongFromShare(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        BaseApplication.getSharedPreferences().putBooleanToShare(key, value);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return BaseApplication.getSharedPreferences().getBooleanFromShare(key, defValue);
    }

    public static void putFloat(String key, float value) {
        BaseApplication.getSharedPreferences().putFloatToShare(key, value);
    }

    public static void putSetString(String key, Set<String> value) {
        BaseApplication.getSharedPreferences().putArrayList(key, value);
    }

    public static float getFloat(String key, float defValue) {
        return BaseApplication.getSharedPreferences().getFloatFromShare(key, defValue);
    }

    public static Set<String> getSetStrings(String key, Set<String> defValue) {
        return BaseApplication.getSharedPreferences().getArrayString(key, defValue);
    }

    public static boolean containKey(String key) {
        return BaseApplication.getSharedPreferences().containKEY(key);
    }

    public static void removeKey(String key) {
        BaseApplication.getSharedPreferences().deleteKey(key);
    }

    public static void clearData() {
        BaseApplication.getSharedPreferences().clearAllData();
    }

    //thaopt
    public static void putStringPref(Context context, String key, String value) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringPref(Context context, String key, String value) {
        if (context == null) {
            return value;
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, value);
    }
}
