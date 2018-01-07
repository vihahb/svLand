package com.goldit.managerinfo.coreapi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.utils.DebugLog;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment  {


    protected ViewGroup rootView;
    private ViewGroup fragmentViewParent;

    private CompositeSubscription mSubscriptions;


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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
        addSubscription(subscribeEvents());
    }

    protected void initView() {
        setHasOptionsMenu(true);
    }

    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
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
        return null;
    }

    protected void addSubscription(Subscription subscription) {
        if (subscription == null) return;
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
        mSubscriptions.add(subscription);
    }

}
