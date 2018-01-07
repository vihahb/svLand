package com.goldit.managerinfo.coreapi.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.goldit.managerinfo.coreapi.constant.PrefConstant;

import java.util.Locale;

public class LocalSettingManager {
    public static final String UPDATE_LANGUAGE = "update_language";

    private Context mContext;

    public LocalSettingManager(Context context) {
        mContext = context;
    }

    public String getLanguage() {
        return SharedPrefUtils.getStringPref(mContext, PrefConstant.PREF_LANGUAGES, "en");
    }

    public void setLanguage(String language) {
        SharedPrefUtils.putStringPref(mContext, PrefConstant.PREF_LANGUAGES, language);
    }

    public void updateLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        mContext.getResources().updateConfiguration(config, mContext.getResources().getDisplayMetrics());
    }
}
