package com.goldit.managerinfo.coreapi;

import android.net.Uri;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.goldit.managerinfo.BaseApplication;
import com.goldit.managerinfo.coreapi.constant.ApiConstant;
import com.goldit.managerinfo.coreapi.request.BaseTypeRequest;
import com.goldit.managerinfo.coreapi.response.ErrorReponse;
import com.goldit.managerinfo.coreapi.utils.DebugLog;
import com.goldit.managerinfo.coreapi.utils.SharedPrefUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public abstract class BaseApiRequest<T> implements BaseTypeRequest.GsonRequestHeaderOnResult {

    protected BaseTypeRequest<T> baseTypeRequest;

    public void execute() {
        if (checkRequestCondition()) {
            excecuteRequest();
        }
    }

    protected boolean checkRequestCondition() {
        if (!checkNetwork()) {
            onRequestError(new NoConnectionError(), "");
            return false;
        } else {
            return true;
        }
    }

    protected Response.Listener<T> getListener() {
        return new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                onRequestSuccess(response);
            }
        };
    }

    protected Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleErrorResponse(error);
            }
        };
    }

    protected void excecuteRequest() {
        //TODO handle create new request in here
    }

    protected String createRequestUrl() {
        if ((getMethod() == Request.Method.GET || getMethod() == Request.Method.DELETE) && getRequestParams() != null) {
            Uri.Builder builder = Uri.parse(getRequestURL()).buildUpon();
            if (isRequiredAccessToken()) {
//                builder.appendQueryParameter(AppConstant.APP_VERSION, "1.0");
            }
            for (Map.Entry<String, String> entry : getRequestParams().entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return builder.build().toString();
        } else {
            return getRequestURL();
        }
    }

    protected RetryPolicy getDefaultRetryPolicy() {
        return new DefaultRetryPolicy(
                ApiConstant.REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    protected Map<String, String> handleRequestParams() {
        Map<String, String> requestParams = getRequestParams();
        if (isRequiredAccessToken()) {
            if (requestParams == null) {
                requestParams = new HashMap<>();
            }
        }
        DebugLog.i(requestParams.toString());
        return requestParams;
    }

    public BaseTypeRequest<T> getBaseTypeRequest() {
        return baseTypeRequest;
    }

    public void handleResponseHeader(Map<String, String> headers) {
        //TODO Handle headers response
    }

    @Override
    public void onGsonRequestHeaderResult(Map<String, String> headers) {
        handleResponseHeader(headers);
    }

    abstract public String getRequestURL();

    abstract public boolean isRequiredAccessToken();

    abstract public Map<String, String> getRequestParams();

    public Map<String, String> getRequestHeaders() {
        Map<String, String> header = new HashMap<>();
        header.put("H-Offer-version", "1.0");
        if (SharedPrefUtils.containKey("H-Offer-token"))
            header.put("H-Offer-token", SharedPrefUtils.getString("H-Offer-token", ""));
        return header;
    }

    abstract public Class<T> getResponseClass();

    abstract public int getMethod();

    abstract public void onRequestSuccess(T response);

    abstract public void onRequestError(VolleyError error, String message);

    protected boolean checkNetwork() {
        return NetworkUtils.getInstance(BaseApplication.getInstance()).isNetworkConnected();
    }

    protected boolean isHideApiErrorDialog() {
        return false;
    }

    protected boolean isHideConnectionErrorDialog() {
        return false;
    }

    protected void handleErrorResponse(VolleyError volleyError)   {
        String response = NetworkUtils.getErrorMessage(volleyError);
        String errorVolley = "";
        try {
            ErrorReponse errorMessage = new Gson().fromJson(response, ErrorReponse.class);
            if (!errorMessage.errorMessage.isEmpty()) {
                errorVolley = errorMessage.errorMessage;
            }
            System.out.println("errorMessage------------>" + errorVolley);
        } catch (Exception e) {
            DebugLog.e("not a json error message: " + response + errorVolley);
        }

        String header = volleyError.networkResponse != null && volleyError.networkResponse.headers != null ?
                volleyError.networkResponse.headers.toString() : volleyError.getClass().getSimpleName();
        if (response.isEmpty()) {
            response = volleyError.getMessage();
        }

        DebugLog.e("error: " + response + "  " + header);
        if (volleyError.getMessage() != null && volleyError.getMessage().contains("java.io.EOFException")) {
            onRequestError(volleyError, errorVolley);
        } else if (volleyError instanceof NetworkError || volleyError instanceof TimeoutError) {
            if (!isHideConnectionErrorDialog()) {
            }
            onRequestError(volleyError, errorVolley);
        } else {
            if (!isHideApiErrorDialog()) {
            }
            onRequestError(volleyError, errorVolley);
        }
    }
}
