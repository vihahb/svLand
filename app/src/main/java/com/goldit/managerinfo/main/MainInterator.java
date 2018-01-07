package com.goldit.managerinfo.main;

import android.content.Context;

import com.goldit.managerinfo.fragment.model.Account;
import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Project;
import com.goldit.managerinfo.fragment.model.Update;

import rx.Observable;

/**
 * Created by baonguyen on 12/23/17.
 */

public interface MainInterator {
    Observable<Account> requestLogin(Context context, String userName, String password);

    Observable<Contact> requestListContact(String userId, String token);

    Observable<Contact> requestGetCustomer(String id, String userId, String token);

    Observable<Update> updateDetailContact(String id, String token, String userId, Contact.User contact);

    Observable<Project> getAllProject();

}
