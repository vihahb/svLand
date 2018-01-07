package com.goldit.managerinfo.login;

import android.content.Context;

import com.goldit.managerinfo.coreapi.APICacheUtils;
import com.goldit.managerinfo.fragment.model.Account;
import com.goldit.managerinfo.main.MainInterator;
import com.goldit.managerinfo.main.MainInteratorImpl;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by baonguyen on 12/23/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;
    MainInterator interator;

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
        interator = new MainInteratorImpl();
    }

    @Override
    public void requestLogin(Context context, String user, String password) {
        view.loading();
        interator.requestLogin(context, user, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Account>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Account account) {
                        view.hideloading();
                        view.onLoginSuccess(account);
                    }
                });
    }
}
