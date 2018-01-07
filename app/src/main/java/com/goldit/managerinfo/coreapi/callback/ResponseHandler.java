package com.goldit.managerinfo.coreapi.callback;

import android.support.annotation.Nullable;

import com.android.volley.VolleyError;

/**
 * Created by hex0r on 10/8/15.
 */
public interface ResponseHandler<T> {
//    void onResponse(@Nullable T responseObject, @Nullable VolleyError error ,String messageError);
    void onSuccess(@Nullable T responseObject);
    void onFail(@Nullable VolleyError error, String messageError);
}
