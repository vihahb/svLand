package com.goldit.managerinfo.fragment.detail;

import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Update;

/**
 * Created by baonguyen on 12/28/17.
 */

public interface DetailContract {
    interface View {
        void loading();

        void hideloading();

        void onUpdateContactSuccess(Update data);

        void updateStatusUserUpdate(String status, String stringStatus);

        void getCustomerSuccess(Contact contact);
    }

    interface Presenter {
        void setView(DetailContract.View view);

        void requestCustomer(String id, String userId, String token);

        void updateContact(Contact.User user, String id, String token);

        void setStatus(String status, String stringStatus);
    }
}
