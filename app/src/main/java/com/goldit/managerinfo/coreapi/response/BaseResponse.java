package com.goldit.managerinfo.coreapi.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hex0r on 10/12/15.
 */
public class BaseResponse {
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("error_message")
    public String error_message;
}
