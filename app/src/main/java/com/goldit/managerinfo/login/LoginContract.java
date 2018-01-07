package com.goldit.managerinfo.login;

import android.content.Context;

import com.goldit.managerinfo.fragment.model.Account;

/**
 * Created by baonguyen on 12/23/17.
 */

public interface LoginContract {
    interface View {
        void loading();

        void hideloading();

        void onLoginSuccess(Account account);
    }

    interface Presenter {
        void setView(View view);

        void requestLogin(Context context, String user, String password);
    }

}
