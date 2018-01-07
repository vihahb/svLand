package com.goldit.managerinfo.coreapi.constant;


import com.goldit.managerinfo.BuildConfig;

public class ApiConstant {
    public static final int REQUEST_TIMEOUT = 15000;
    public static final boolean IS_DEBUG_BUILD_TYPE = BuildConfig.BUILD_TYPE.equals("debug");
    //Server KH
    public final static String REAL_URL = "http://app.1datagate.com";
    public final static String DEV_URL = "http://app.1datagate.com";
    public final static String BASE_URL = IS_DEBUG_BUILD_TYPE ? DEV_URL : REAL_URL;


    public static final String LOGIN = "/api/login";
    public static final String LIST_CONTACT = "/api/getList";
    public static final String UPDATE_CONTACT = "/api/updateUser";
    public static final String GET_CUSTOMER = "/api/getcustomer";
    public static final String GET_PROJECT = "/api/getproject";
}
