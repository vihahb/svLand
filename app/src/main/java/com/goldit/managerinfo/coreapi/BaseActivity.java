package com.goldit.managerinfo.coreapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

import com.goldit.managerinfo.coreapi.utils.RxBus;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public abstract class BaseActivity extends ActionBarActivity {

    boolean layout = true;
    private CompositeSubscription mSubscriptions;
    long timeTotal;
    boolean isRunningLife = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        onPostSetContentView(savedInstanceState);
        Intent intent = getIntent();
        setContentView(setContentViewId());
        ButterKnife.bind(this);
        addSubscription(subscribeEvents());
        initData();
    }


    public void setLayout(boolean layout) {
        this.layout = layout;
    }

    protected void onPostSetContentView(Bundle savedInstanceState) {

    }


    public abstract int setContentViewId();

    public abstract void initData();

    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable().observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<Object>() {
            @Override
            public void call(Object o) {
            }
        }).subscribe(RxBus.defaultSubscriber());
    }

    protected void addSubscription(Subscription subscription) {
        if (subscription == null) return;
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
        mSubscriptions.add(subscription);
    }



    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    //-----------------------------------------------------



}
