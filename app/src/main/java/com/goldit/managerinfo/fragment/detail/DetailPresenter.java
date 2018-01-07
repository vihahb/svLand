package com.goldit.managerinfo.fragment.detail;

import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Update;
import com.goldit.managerinfo.main.MainInterator;
import com.goldit.managerinfo.main.MainInteratorImpl;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by baonguyen on 12/28/17.
 */

public class DetailPresenter implements DetailContract.Presenter {

    DetailContract.View view;
    MainInterator interator;

    @Override
    public void setView(DetailContract.View view) {
        this.view = view;
        interator = new MainInteratorImpl();
    }

    @Override
    public void requestCustomer(String id, String userId, String token) {
        view.loading();
        interator.requestGetCustomer(id, userId, token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Contact>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Contact contact) {
                        view.hideloading();
                        view.getCustomerSuccess(contact);
                    }
                });
    }

    @Override
    public void updateContact(Contact.User user, String id, String token) {
        view.loading();
        interator.updateDetailContact(id, token, user.getId() + "", user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Update>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Update data) {
                        view.hideloading();
                        view.onUpdateContactSuccess(data);
                    }
                });
    }

    @Override
    public void setStatus(String status, String stringStatus) {
        view.updateStatusUserUpdate(status, stringStatus);
    }
}
