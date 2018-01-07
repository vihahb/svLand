package com.goldit.managerinfo.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;


/**
 * Created by Kill on 2/10/2017.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        this.realm = Realm.getDefaultInstance();
    }


    public static RealmController with(Fragment fragment) {
        if (instance == null)
            instance = new RealmController(fragment.getActivity().getApplication());
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public void refesh() {
//        RealmResults<Book> results = realm.where(Book.class).findAllAsync();
//        results.load();
    }

    //clear all object
    public void clearAll(Class<? extends RealmObject> clazz) {
        realm.beginTransaction();
        realm.delete(clazz);
        realm.commitTransaction();
    }

    //find all objects in the Object.class
    public RealmResults getAllObjects(Class<? extends RealmObject> clazz) {
        return realm.where(clazz).findAll();
    }

    //query single item with id=?
    public Object getSingleObject(Class<? extends RealmObject> clazz, int id) {
        return realm.where(clazz).equalTo("id", id).findFirst();
    }

    //check if Object.class is empty
//    public boolean hasObject(Class<? extends RealmObject> clazz) {
//        return !realm.allObjects(clazz).isEmpty();
//    }


    //querry
    public RealmResults querryObject(Class<? extends RealmObject> clazz, String key, String value) {
        return realm.where(clazz)
                .contains(key, value)
//                .or()
//                .contains("key","value")
                .findAll();
    }


    //greaterThan lọc các đối tượng thỏa mãn
    public RealmResults getAllFilter(Class<? extends RealmObject> clazz, String key, int value) {
        return realm.where(clazz).greaterThan(key, value).findAll();
    }

    //sort

    public RealmResults getAllSortList(Class<? extends RealmObject> clazz, String key, boolean value) {
        return realm.where(clazz).findAllSorted(key);
    }

}
