package com.goldit.managerinfo.coreapi.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Linhnh on 6/11/2016.
 */
public class ErrorReponse {
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;
    @SerializedName("invalid_fields")
    public List<InvalidFields> invalidFields;
    @SerializedName("error_message")
    public String errorMessage;

    public class InvalidFields {
        @SerializedName("email")
        public String email;
        @SerializedName("fild")
        public String fild;
    }
}
