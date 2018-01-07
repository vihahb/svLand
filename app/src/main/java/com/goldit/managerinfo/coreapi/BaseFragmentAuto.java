package com.goldit.managerinfo.coreapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.utils.DebugLog;
import com.goldit.managerinfo.coreapi.utils.RxBus;
import com.goldit.managerinfo.coreapi.utils.SharedPrefUtils;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragmentAuto extends Fragment {
    long timeTotal;

    protected ViewGroup rootView;
    private ViewGroup fragmentViewParent;

    private CompositeSubscription mSubscriptions;

    protected boolean isLoading = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView != null ? rootView : createRootView(inflater, container));
        addSubscription(subscribeEvents());
        return rootView != null ? rootView : createRootView(inflater, container);
    }

    private View createRootView(LayoutInflater inflater, ViewGroup container) {
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//            }
//        }, 150);
        rootView = (ViewGroup) inflater.inflate(R.layout.base_fragment, container, false);
        fragmentViewParent = (ViewGroup) rootView.findViewById(R.id.fragmentViewParent);
        fragmentViewParent.addView(inflater.inflate(getLayoutId(), container, false));
        ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void initView() {
        setHasOptionsMenu(true);
    }

    protected void initData() {

    }




    @Override
    public void onDestroy() {
        DebugLog.i("Lifecycle " + this.getClass().getSimpleName());
        fragmentViewParent = null;
        rootView = null;
        super.onDestroy();
    }


    abstract protected int getLayoutId();


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




}
