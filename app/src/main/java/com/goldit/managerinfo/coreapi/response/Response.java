package com.goldit.managerinfo.coreapi.response;

import com.android.volley.VolleyError;

import java.util.Map;

/**
 * Created by hex0r on 10/8/15.
 */
public class Response<T> {
    public final T responseObject;
    public final String body;
    public final Map<String, String> headers;
    public final int statusCode;
    public final VolleyError error;

    public Response(T responseObject, String body, Map<String, String> headers, int statusCode, VolleyError error) {
        this.responseObject = responseObject;
        this.body = body;
        this.headers = headers;
        this.statusCode = statusCode;
        this.error = error;
    }
}
