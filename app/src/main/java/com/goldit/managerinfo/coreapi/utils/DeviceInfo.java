package com.goldit.managerinfo.coreapi.utils;

/**
 * Created by Linhnh on 8/27/2016.
 */
public class DeviceInfo {

    public static String getDeviceSuperInfo() {
        String s = "\n\n\n\n Errors on device:";

        try {
            s += "\n OS Version: " + System.getProperty("os.version") + "(" + android.os.Build.VERSION.INCREMENTAL + ")";
            s += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
            s += "\n Device: " + android.os.Build.DEVICE;
            s += "\n Model (and Product): " + android.os.Build.MODEL + " (" + android.os.Build.PRODUCT + ")";

            s += "\n RELEASE: " + android.os.Build.VERSION.RELEASE;
            s += "\n BRAND: " + android.os.Build.BRAND;
            s += "\n DISPLAY: " + android.os.Build.DISPLAY;
            s += "\n CPU_ABI: " + android.os.Build.CPU_ABI;
            s += "\n CPU_ABI2: " + android.os.Build.CPU_ABI2;
            s += "\n UNKNOWN: " + android.os.Build.UNKNOWN;
            s += "\n HARDWARE: " + android.os.Build.HARDWARE;
            s += "\n Build ID: " + android.os.Build.ID;
            s += "\n MANUFACTURER: " + android.os.Build.MANUFACTURER;
            s += "\n SERIAL: " + android.os.Build.SERIAL;
            s += "\n USER: " + android.os.Build.USER;
            s += "\n HOST: " + android.os.Build.HOST;


        } catch (Exception e) {
        }
        return s;
    }//end getDeviceSuperInfo
}
