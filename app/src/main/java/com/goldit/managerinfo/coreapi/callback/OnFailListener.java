package com.goldit.managerinfo.coreapi.callback;

import com.android.volley.VolleyError;
import com.goldit.managerinfo.coreapi.request.BaseTypeRequest;
import com.goldit.managerinfo.coreapi.response.Response;


public interface OnFailListener {
    void onFail(BaseTypeRequest request, Response response);

    void onPrerequisiteFail(BaseTypeRequest request, VolleyError error);
}
