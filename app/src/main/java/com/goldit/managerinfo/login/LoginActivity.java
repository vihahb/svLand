package com.goldit.managerinfo.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.BaseActivity;
import com.goldit.managerinfo.coreapi.utils.KeyboardUtil;
import com.goldit.managerinfo.fragment.model.Account;
import com.goldit.managerinfo.main.MainActivity;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {


    @BindView(R.id.titleStatus)
    TextView titleStatus;
    @BindView(R.id.editUsername)
    EditText editUsername;
    @BindView(R.id.editPassword)
    EditText editPassword;

    LoginPresenter presenter;
    CircleProgressDialog circleProgressDialog;
    public static boolean isActive;


    private void initDialogLoading() {
        circleProgressDialog = new CircleProgressDialog(this);
        circleProgressDialog.setCancelable(false);
        circleProgressDialog.setText(null);
    }

    void initView() {
        editUsername.requestFocus();
        KeyboardUtil.requestKeyboard(editUsername);
        initDialogLoading();
        initPresenter();
    }

    @OnClick(R.id.actionLogin)
    public void actionLogin() {
        if (!editUsername.getText().equals("") && !editPassword.getText().equals("")) {
            presenter.requestLogin(this, editUsername.getText().toString(), editPassword.getText().toString());
        }

    }

    private void initPresenter() {
        presenter = new LoginPresenter();
        presenter.setView(this);
    }

    @Override
    public void loading() {
        if (isActive)
            circleProgressDialog.showDialog();
    }

    @Override
    public void hideloading() {
        if (isActive)
            circleProgressDialog.dismiss();
    }

    @Override
    public void onLoginSuccess(Account account) {
        if (account.getCode() == 200) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("Account", account);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("Bundle", bundle);
            finish();
            startActivity(intent);
            return;
            //login success
        } else if (account.getCode() == 201) {
            //login fail
        } else if (account.getCode() == 202) {
            //device_id fail
        } else if (account.getCode() == 203) {
            //no device id
        }
        titleStatus.setText(account.getData().getSuccess());
    }

    @Override
    public void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    public int setContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initData() {
        initView();
    }

    @Override
    public void onPause() {
        super.onPause();
        isActive = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isActive = false;
    }
}
