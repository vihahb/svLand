package com.goldit.managerinfo.fragment.detail;

import android.util.Log;

import com.goldit.managerinfo.BaseApplication;
import com.goldit.managerinfo.fragment.model.Action;
import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Update;
import com.goldit.managerinfo.main.MainInterator;
import com.goldit.managerinfo.main.MainInteratorImpl;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

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

    @Override
    public void postCallAction(int id, String msisdn) {
        view.loading();
        Action action = new Action(id, msisdn);
        JsonObject callObject = new JsonObject();
        callObject.addProperty("id", action.getId());
        callObject.addProperty("msisdn", action.getMsisdn());

        String jsonObject = callObject.toString();
        String urlCall = "http://app.1datagate.com/api/call";
        Ion.with(BaseApplication.CONTEXT)
                .load(urlCall)
                .setMultipartParameter("id", String.valueOf(action.getId()))
                .setMultipartParameter("msisdn", action.getMsisdn())
//                .setJsonObjectBody(callObject)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.e("postCallAction", "onCompleted: " + result.toString());
                    }
                });
    }
}
