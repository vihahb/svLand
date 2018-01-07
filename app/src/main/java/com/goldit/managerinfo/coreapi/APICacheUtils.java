package com.goldit.managerinfo.coreapi;

import com.goldit.managerinfo.coreapi.utils.DebugLog;
import com.goldit.managerinfo.coreapi.utils.SharedPrefUtils;
import com.goldit.managerinfo.coreapi.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;


public class APICacheUtils {

    private static APICacheUtils instance;
    private static Gson gson;

    public static APICacheUtils get() {
        if (instance == null) {
            instance = new APICacheUtils();
        }
        return instance;
    }

    private APICacheUtils() {
//        GsonBuilder builder = new GsonBuilder();
//        gson = builder.create();
//        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();
    }


    public void saveResult(Object obj, String key) {
        String json = gson.toJson(obj);
        DebugLog.i("save Json" + json);
        SharedPrefUtils.putString(key, json);
    }

    public synchronized <T> T getResult(String key, Class<T> clazz) {
        String json = SharedPrefUtils.getString(key, "");
        if (StringUtil.isEmpty(json)) {
            return null;
        } else {
            try {
                return gson.fromJson(json, clazz);
            } catch (Exception ex) {
                return null;
            }
        }
    }


    public <T> T strtoObject(String json, Class<T> clazz) {
        if (StringUtil.isEmpty(json)) {
            return null;
        } else {
            try {
                return gson.fromJson(json, clazz);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = gson.fromJson(s, clazz);
        return Arrays.asList(arr);
    }

    public <T> List<T> stringToArrKEY(String key, Class<T[]> clazz) {
        String json = SharedPrefUtils.getString(key, "");
        T[] arr = gson.fromJson(json, clazz);
        return Arrays.asList(arr);
        //or return Arrays.asList(gson.fromJson(s, clazz)); for a one-liner
    }

    public <T> void arrayToStr(List<T> list, String key) {
        String json = gson.toJson(list);
        SharedPrefUtils.putString(key, json);
        DebugLog.e("----save Json" + json);
    }


    public String toJson(Object jsonObject) {
        return gson.toJson(jsonObject);
    }

}
