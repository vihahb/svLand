package com.goldit.managerinfo.main;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.goldit.managerinfo.coreapi.BaseOnlyRequestController;
import com.goldit.managerinfo.coreapi.callback.ResponseHandler;
import com.goldit.managerinfo.coreapi.constant.ApiConstant;
import com.goldit.managerinfo.fragment.model.Account;
import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Project;
import com.goldit.managerinfo.fragment.model.Update;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by baonguyen on 12/23/17.
 */

public class MainInteratorImpl implements MainInterator {
    @Override
    public Observable<Account> requestLogin(final Context context, final String userName, final String password) {
        return Observable.create(new Observable.OnSubscribe<Account>() {
            @Override
            public void call(final Subscriber<? super Account> subscriber) {
                Map<String, String> params = new HashMap<>();
                String deviceId = Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                params.put("device_id", deviceId);
                params.put("user", userName);
                params.put("password", password);

                new BaseOnlyRequestController<Account>(new ResponseHandler<Account>() {
                    @Override
                    public void onSuccess(@Nullable Account responseObject) {
                        subscriber.onNext(responseObject);
                    }

                    @Override
                    public void onFail(@Nullable VolleyError error, String messageError) {
                        System.out.println("Error " + messageError);

                    }
                }, Account.class, params, ApiConstant.LOGIN, Request.Method.POST, true).execute();
            }
        });
    }

    @Override
    public Observable<Contact> requestListContact(final String userId, final String token) {
        return Observable.create(new Observable.OnSubscribe<Contact>() {
            @Override
            public void call(final Subscriber<? super Contact> subscriber) {
                Map<String, String> params = new HashMap<>();
                params.put("id", userId);
                params.put("token", token);

                new BaseOnlyRequestController<Contact>(new ResponseHandler<Contact>() {
                    @Override
                    public void onSuccess(@Nullable Contact responseObject) {
                        subscriber.onNext(responseObject);
                    }

                    @Override
                    public void onFail(@Nullable VolleyError error, String messageError) {
                        System.out.println("Error " + messageError);

                    }
                }, Contact.class, params, ApiConstant.LIST_CONTACT, Request.Method.POST, true).execute();
            }
        });
    }

    @Override
    public Observable<Contact> requestGetCustomer(final String id, final String userId, final String token) {
        return Observable.create(new Observable.OnSubscribe<Contact>() {
            @Override
            public void call(final Subscriber<? super Contact> subscriber) {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("token", token);
                params.put("user_id", userId);

                new BaseOnlyRequestController<Contact>(new ResponseHandler<Contact>() {
                    @Override
                    public void onSuccess(@Nullable Contact responseObject) {
                        subscriber.onNext(responseObject);
                    }

                    @Override
                    public void onFail(@Nullable VolleyError error, String messageError) {
                        System.out.println("Error " + messageError);

                    }
                }, Contact.class, params, ApiConstant.GET_CUSTOMER, Request.Method.POST, true).execute();
            }
        });
    }

    @Override
    public Observable<Update> updateDetailContact(final String id, final String token, final String contactId, final Contact.User contact) {
        return Observable.create(new Observable.OnSubscribe<Update>() {
            @Override
            public void call(final Subscriber<? super Update> subscriber) {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("token", token);
                params.put("user_id", contactId);
                params.put("msisdn", contact.getMsisdn());
                params.put("email", contact.getEmail());
                params.put("status", contact.getStatus());
                params.put("note", contact.getNote());
                params.put("name", contact.getName());


                new BaseOnlyRequestController<Update>(new ResponseHandler<Update>() {
                    @Override
                    public void onSuccess(@Nullable Update responseObject) {
                        subscriber.onNext(responseObject);
                    }

                    @Override
                    public void onFail(@Nullable VolleyError error, String messageError) {
                        System.out.println("Error " + messageError);

                    }
                }, Update.class, params, ApiConstant.UPDATE_CONTACT, Request.Method.POST, true).execute();
            }
        });
    }

    @Override
    public Observable<Project> getAllProject() {
        return Observable.create(new Observable.OnSubscribe<Project>() {
            @Override
            public void call(final Subscriber<? super Project> subscriber) {

                new BaseOnlyRequestController<Project>(new ResponseHandler<Project>() {
                    @Override
                    public void onSuccess(@Nullable Project responseObject) {
                        subscriber.onNext(responseObject);
                    }

                    @Override
                    public void onFail(@Nullable VolleyError error, String messageError) {
                        System.out.println("Error " + messageError);

                    }
                }, Project.class, null, ApiConstant.GET_PROJECT, Request.Method.POST, true).execute();
            }
        });
    }
}
