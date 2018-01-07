package com.goldit.managerinfo;

import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.goldit.managerinfo.coreapi.utils.SharedPrefUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class BaseApplication extends MultiDexApplication {
    private static SharedPrefUtils sharedPreferences;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        sharedPreferences = new SharedPrefUtils(getApplicationContext());
        mInstance = this;
        CONTEXT = this;
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(configuration);
    }


    public static SharedPrefUtils getSharedPreferences() {
        return sharedPreferences;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        mInstance = null;
    }


    public static BaseApplication getApplication() {
        return mInstance;
    }

    private static BaseApplication mInstance;

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }



}
