package com.goldit.managerinfo.coreapi.utils;

import android.app.Activity;
import android.widget.Toast;

import com.goldit.managerinfo.BuildConfig;


/**
 * Created by Linhnh on 8/19/2016.
 */
public class ToastUtil {
    static String className;
    static String methodName;
    static int lineNumber;

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    private static String createLog(String log) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void LongShow(Activity context, String message) {
        getMethodNames(new Throwable().getStackTrace());
        Toast t = Toast.makeText(context, className + "\n" + message, Toast.LENGTH_LONG);
        if (isDebuggable()) {
            t.show();
        }
    }

    public static void ShortShow(Activity context, String message) {
        getMethodNames(new Throwable().getStackTrace());
        Toast t = Toast.makeText(context, className + "\n" + message, Toast.LENGTH_SHORT);
        if (isDebuggable()) {
            t.show();
        }
    }
}
