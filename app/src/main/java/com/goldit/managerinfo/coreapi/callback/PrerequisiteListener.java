package com.goldit.managerinfo.coreapi.callback;

/**
 * Created by hex0r on 10/8/15.
 */
public interface PrerequisiteListener {
    void onSuccess();

    void onFail(Throwable error);
}
