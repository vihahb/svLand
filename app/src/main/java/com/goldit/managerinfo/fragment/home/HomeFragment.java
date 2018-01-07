package com.goldit.managerinfo.fragment.home;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.Key;
import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.BaseFragment;
import com.goldit.managerinfo.coreapi.utils.FragmentUtil;
import com.goldit.managerinfo.fragment.detail.DetailContactFragment;
import com.goldit.managerinfo.fragment.model.Account;
import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Project;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.goldit.managerinfo.login.LoginActivity.isActive;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, HomeAdapter.ItemClickListener {

    public static HomeFragment fragment;
    public static HomePresenter homePresenter;
    CircleProgressDialog circleProgressDialog;
    @BindView(R.id.recyclerListContact)
    RecyclerView recyclerListContact;
    Account account;

    @BindView(R.id.actionSelectProject)
    TextView actionSelectProject;
    @BindView(R.id.actionRefesh)
    ImageView actionRefesh;
    public static HomeFragment getInstance() {
        if (fragment == null) {
            fragment = new HomeFragment();
        }
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        initDialogLoading();
        initPresenter();
        actionRefesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPresenter();
                Toast.makeText(getActivity(),"Danh sách đã được cập nhật",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initPresenter() {
        homePresenter = new HomePresenter();
        homePresenter.setView(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            account = bundle.getParcelable("Account");
            homePresenter.requestListContact(account.getData().getUser_id() + "", account.getData().getToken());
            homePresenter.requestProject();
        }
    }


    private void initDialogLoading() {
        circleProgressDialog = new CircleProgressDialog(getContext());
        circleProgressDialog.setCancelable(false);
        circleProgressDialog.setText(null);
    }


    @Override
    public void loading() {
        if (isActive)
            circleProgressDialog.showDialog();
    }

    @Override
    public void hideLoading() {
        if (isActive)
            circleProgressDialog.dismiss();
    }

    @Override
    public void onLoadListContactSuccess(Contact contact) {
        if (contact != null)
            initListContact(contact.getData(), "-1");

    }

    List<Contact.User> data;

    private void initListContact(List<Contact.User> data, String filter) {
        this.data = data;
        if (data != null) {
            if (filter.equals("-1")) {
                actionSelectProject.setText("Tất cả dự án");
                HomeAdapter adapter = new HomeAdapter();
                adapter.setClickListener(this);
                adapter.updateList(data);
                recyclerListContact.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerListContact.setAdapter(adapter);
            } else {
                List<Contact.User> dataNew = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getProject_id().equals(filter)) {
                        dataNew.add(data.get(i));
                    }
                }
                HomeAdapter adapter = new HomeAdapter();
                adapter.setClickListener(this);
                adapter.updateList(dataNew);
                recyclerListContact.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerListContact.setAdapter(adapter);
            }

        }
    }

    Project project;

    @Override
    public void onLoadListProjectSuccess(Project projects) {
        if (projects != null)
            this.project = projects;
    }

    @Override
    public void loadProjectUserSelected(Project.DataBean dataBean) {
        actionSelectProject.setText(dataBean.getProject_name());
        if (data != null) {
            initListContact(data, dataBean.getId() + "");
        }
    }


    @Override
    public void onClick(Contact.User contact, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Contact", contact);
        if (account != null) {
            bundle.putParcelable("Account", account);
        }
        FragmentUtil.addFragmentData(getActivity(), new DetailContactFragment(), true, bundle);
    }

    @OnClick(R.id.actionSelectProject)
    void actionSelectProject() {
        Bundle bundle = new Bundle();
        if (project != null) {
            bundle.putParcelable("Project", project);
        }

        DialogFragment dialogFragment = new DialogSelectProject();
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getFragmentManager(), "");
    }

    @Override
    public void onResume() {
        super.onResume();
        initPresenter();
    }
}
