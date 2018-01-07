package com.goldit.managerinfo.coreapi.callback;


import com.goldit.managerinfo.coreapi.request.BaseTypeRequest;
import com.goldit.managerinfo.coreapi.response.Response;

public interface OnSuccessListener<T> {
    void onSuccess(BaseTypeRequest request, Response<T> response);
}
