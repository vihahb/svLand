package com.goldit.managerinfo.coreapi.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hex0r on 10/8/15.
 */
public class ErrorMessage {
    @SerializedName("message")
    public String message;
    @SerializedName("error_message")
    public String error_message;
}
