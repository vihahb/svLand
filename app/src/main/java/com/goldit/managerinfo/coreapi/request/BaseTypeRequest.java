package com.goldit.managerinfo.coreapi.request;

import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by hex0r on 10/8/15.
 */
public abstract class BaseTypeRequest<T> extends Request<T>  {
    protected final Class<T> clazz;
    protected final Response.Listener<T> listener;
    protected final Map<String, String> header;
    protected final Map<String, String> params;
    public String body;
    public int statusCode;
    public Map<String, String> responseHeader;
    protected GsonRequestHeaderOnResult gsonRequestHeaderOnResult;

    public BaseTypeRequest(int method, String url, Response.ErrorListener errorListener, Class<T> clazz, Response.Listener<T> listener, Map<String, String> header, Map<String, String> params) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
        this.header = header;
        this.params = params;
    }

    public void updateParam(String key, String value) {
        if (params != null) {
            params.put(key, value);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return header != null ? header : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (params != null) {
            Log.i("params: ", params.toString());
        }
        return params != null ? params : super.getParams();
    }

    protected void setMembersFrom(VolleyError error) {
        if (error != null) {
            try {
                setMembersFrom(error.networkResponse);
            } catch (UnsupportedEncodingException e) {
                //ignore
            }
        }
    }

    protected void setMembersFrom(@Nullable NetworkResponse networkResponse) throws UnsupportedEncodingException {
        if (networkResponse != null) {
            responseHeader = networkResponse.headers;
            statusCode = networkResponse.statusCode;
            body = getBodyString(networkResponse);
        }
    }

    private String getBodyString(NetworkResponse networkResponse) throws UnsupportedEncodingException {
        if (this instanceof BinaryRequest) {
            return null;
        }
        return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
    }


    @Override
    public void deliverError(VolleyError error) {
        setMembersFrom(error);
        super.deliverError(error);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            setMembersFrom(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setGsonRequestHeaderOnResult(GsonRequestHeaderOnResult mGsonRequestHeaderOnResult) {
        this.gsonRequestHeaderOnResult = mGsonRequestHeaderOnResult;
    }

    public interface GsonRequestHeaderOnResult {
        void onGsonRequestHeaderResult(Map<String, String> mHeaders);
    }
}
