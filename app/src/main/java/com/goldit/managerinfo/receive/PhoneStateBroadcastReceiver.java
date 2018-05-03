package com.goldit.managerinfo.receive;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.goldit.managerinfo.BaseApplication;
import com.goldit.managerinfo.coreapi.utils.SharedPrefUtils;
import com.goldit.managerinfo.window.WindowManagers;

public class PhoneStateBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "PhoneStateBroadcastRece";
    String incoming_number;
    private int prev_state;
    private Context mContext;

    WindowManagers managers;

    static PhoneStateListener phoneStateListener;
    private boolean incomingCall = SharedPrefUtils.getBolean(BaseApplication.CONTEXT, "OUTGOINGCALL");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (managers == null)
            managers = new WindowManagers(context);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            if (phoneStateListener == null) {
                phoneStateListener = new PhoneStateListener();
                telephonyManager.listen(phoneStateListener, android.telephony.PhoneStateListener.LISTEN_CALL_STATE); //Register our listener with TelephonyManager
            }
        } catch (Exception e) {
            Log.e(TAG, "onReceive: " + e.toString());
        }
        Bundle bundle = intent.getExtras();
        String phoneNr = null;
        if (bundle != null) {
            phoneNr = bundle.getString("incoming_number");
        }
        Log.v(TAG, "phoneNr: " + phoneNr);
        mContext = context;
    }

    /**
     * Custom PhoneStateListener
     */
    public class PhoneStateListener extends android.telephony.PhoneStateListener {

        private static final String TAG = "PhoneStateListener";

        @SuppressLint("LongLogTag")
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            Log.e(TAG, "State: " + state);

            if (incomingNumber != null && incomingNumber.length() > 0)
                incoming_number = incomingNumber;

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.e(TAG, "CALL_STATE_RINGING");
                    prev_state = state;
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.e(TAG, "CALL_STATE_OFFHOOK");
                    prev_state = state;
                    if (incomingCall)
                        managers.show();
                    break;

                case TelephonyManager.CALL_STATE_IDLE:

                    Log.e(TAG, "CALL_STATE_IDLE==>" + incoming_number);

                    if ((prev_state == TelephonyManager.CALL_STATE_OFFHOOK)) {
                        prev_state = state;
                        if (managers.isShow())
                            managers.closeView();
                        //Delete last call
                        deleteLastCall();
                    }
                    if ((prev_state == TelephonyManager.CALL_STATE_RINGING)) {
                        prev_state = state;
                        //Rejected or Missed call
                    }
                    break;
            }
        }
    }

    private void deleteLastCall() {

    }
}