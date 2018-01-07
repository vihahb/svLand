package com.goldit.managerinfo.fragment.home;

import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Project;
import com.goldit.managerinfo.main.MainInterator;
import com.goldit.managerinfo.main.MainInteratorImpl;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by baonguyen on 12/26/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    HomeContract.View view;
    MainInterator interator;

    @Override
    public void setView(HomeContract.View view) {
        this.view = view;
        interator = new MainInteratorImpl();
    }

    @Override
    public void requestListContact(String userId, String token) {
        view.loading();
        interator.requestListContact(userId, token).subscribeOn(Schedulers.io())
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
                        view.hideLoading();
                        view.onLoadListContactSuccess(contact);
                    }
                });
    }

    @Override
    public void requestProject() {
        interator.getAllProject().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Project>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Project contact) {
                        view.onLoadListProjectSuccess(contact);
                    }
                });
    }

    @Override
    public void setProjectUserSelected(Project.DataBean dataBean) {
        view.loadProjectUserSelected(dataBean);
    }

}
