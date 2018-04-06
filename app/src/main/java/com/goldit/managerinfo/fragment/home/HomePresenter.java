package com.goldit.managerinfo.fragment.home;

import android.util.Log;

import com.goldit.managerinfo.BaseApplication;
import com.goldit.managerinfo.fragment.model.Action;
import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Project;
import com.goldit.managerinfo.main.MainInterator;
import com.goldit.managerinfo.main.MainInteratorImpl;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

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

    public void callClickcusAction(int id, String msisdn) {
        Action action = new Action(id, msisdn);
        JsonObject callObject = new JsonObject();
        callObject.addProperty("id", action.getId());
        callObject.addProperty("msisdn", action.getMsisdn());

//        String jsonObject = callObject.toString();
        String urlCall = "http://app.1datagate.com/api/clickcus";
        Ion.with(BaseApplication.CONTEXT)
                .load(urlCall)
                .setMultipartParameter("id", String.valueOf(action.getId()))
                .setMultipartParameter("msisdn", action.getMsisdn())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.e("callClickcusAction", "onCompleted: " + result.toString());
                    }
                });
    }
}
