package com.goldit.managerinfo.coreapi.request;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.goldit.managerinfo.BaseApplication;
import com.goldit.managerinfo.coreapi.NetworkUtils;
import com.goldit.managerinfo.coreapi.callback.PrerequisiteListener;


/**
 * Created by hex0r on 10/8/15.
 */
public class PrerequisiteHandler {
    public VolleyError prerequisiteError;

    PrerequisiteListener prerequisiteListener;

    public PrerequisiteHandler() {
        prerequisiteError = new VolleyError();
    }

    public void setPrerequisiteListener(PrerequisiteListener prerequisiteListener) {
        this.prerequisiteListener = prerequisiteListener;
    }

    public boolean check() {
        if (NetworkUtils.getInstance(BaseApplication.getInstance()).isNetworkConnected()) {
            prerequisiteError = null;
            if (prerequisiteListener != null) {
                prerequisiteListener.onSuccess();
            }
            return true;
        } else {
            prerequisiteError = new NoConnectionError();
            if (prerequisiteListener != null) {
                prerequisiteListener.onFail(prerequisiteError);
            }
            return false;
        }
    }
}
