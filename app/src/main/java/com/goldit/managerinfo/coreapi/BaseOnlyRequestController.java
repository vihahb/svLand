package com.goldit.managerinfo.coreapi;

import com.android.volley.VolleyError;

import java.util.Map;

import com.goldit.managerinfo.coreapi.callback.ResponseHandler;
import com.goldit.managerinfo.coreapi.constant.ApiConstant;
import com.goldit.managerinfo.coreapi.request.ObjectApiRequest;


/**
 * Created by Kill on 7/20/2016.
 */
public class BaseOnlyRequestController<T> extends ObjectApiRequest<T> {
    private ResponseHandler<T> onApiResponse;
    Map<String, String> params;
    String api;
    int type;
    Class<T> c;
    boolean root;

    public BaseOnlyRequestController(ResponseHandler<T> onApiResponse, Class<T> c, Map<String, String> params, String api, int type, boolean root) {
        this.params = params;
        this.onApiResponse = onApiResponse;
        this.api = api;
        this.type = type;
        this.c = c;
        this.root = root;
    }


    @Override
    public String getRequestURL() {
        if (root)
            return ApiConstant.BASE_URL + api;
        else return api;
    }

    @Override
    public boolean isRequiredAccessToken() {
        return true;
    }

    @Override
    public Map<String, String> getRequestParams() {
        return params;
    }

    @Override
    public Class<T> getResponseClass() {
        return c;
    }

    @Override
    public int getMethod() {
        return type;
    }

    @Override
    public void onRequestSuccess(T response) {
        onApiResponse.onSuccess(response);
    }

    @Override
    public void onRequestError(VolleyError error, String message) {
        onApiResponse.onFail(error, message);
    }

}
