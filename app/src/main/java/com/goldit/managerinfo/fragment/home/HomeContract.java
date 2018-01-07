package com.goldit.managerinfo.fragment.home;

import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Project;

/**
 * Created by baonguyen on 12/26/17.
 */

public interface HomeContract {
    interface View {
        void loading();

        void hideLoading();

        void onLoadListContactSuccess(Contact contact);

        void onLoadListProjectSuccess(Project projects);

        void loadProjectUserSelected(Project.DataBean dataBean);

    }

    interface Presenter {
        void setView(HomeContract.View view);

        void requestListContact(String userId, String token);

        void requestProject();

        void setProjectUserSelected(Project.DataBean dataBean);
    }
}
