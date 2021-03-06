package com.goldit.managerinfo.fragment.detail;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goldit.managerinfo.BaseApplication;
import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.BaseFragment;
import com.goldit.managerinfo.coreapi.utils.FragmentUtil;
import com.goldit.managerinfo.coreapi.utils.KeyboardUtil;
import com.goldit.managerinfo.coreapi.utils.SharedPrefUtils;
import com.goldit.managerinfo.coreapi.utils.ToastUtil;
import com.goldit.managerinfo.fragment.model.Account;
import com.goldit.managerinfo.fragment.model.Contact;
import com.goldit.managerinfo.fragment.model.Update;
import com.goldit.managerinfo.window.PermissionHelper;
import com.goldit.managerinfo.window.WindowManagers;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.security.Permission;

import butterknife.BindView;
import butterknife.OnClick;

import static com.goldit.managerinfo.login.LoginActivity.isActive;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailContactFragment extends BaseFragment implements DetailContract.View {

    WindowManagers managers;

    @BindView(R.id.actionBackDetail)
    ImageView actionBackDetail;
    @BindView(R.id.editTelContactDetail)
    EditText editTelContactDetail;
    @BindView(R.id.editEmailContactDetail)
    EditText editEmailContactDetail;
    @BindView(R.id.editFullNameContactDetail)
    EditText editFullNameContactDetail;
    @BindView(R.id.noteContactDetail)
    EditText noteContactDetail;
    @BindView(R.id.statusDetailContact)
    TextView statusDetailContact;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.layout_1)
    LinearLayout layout_1;
    @BindView(R.id.layout_2)
    LinearLayout layout_2;

    Account account;
    Contact.User contact;
    public static DetailPresenter presenter;
    CircleProgressDialog circleProgressDialog;
    private int MY_PERMISSIONS_REQUEST_CALL_CONTACTS = 91;
    private int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 92;
    private String[] permission_list = {Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_contact;
    }

    private BroadcastReceiver endCall = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (managers.isShow())
                managers.closeView();
        }
    };

//    getActivity().registerReceiver(reload_receive, new IntentFilter("RELOAD_DATA"));

    @Override
    protected void initView() {
        initDialogLoading();
        initPresenter();
        initTabLayout();
        registerReceive();
        if (managers == null)
            managers = new WindowManagers(getContext());
    }

    private void registerReceive() {
        getContext().registerReceiver(endCall, new IntentFilter("END_CALL"));
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Thông tin"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("Ghi chú"), 1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        layout_1.setVisibility(View.VISIBLE);
                        layout_2.setVisibility(View.GONE);
                        break;
                    case 1:
                        layout_1.setVisibility(View.GONE);
                        layout_2.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initPresenter() {
        Bundle bundle = getArguments();
        account = bundle.getParcelable("Account");
        Contact.User contact = bundle.getParcelable("Contact");
        presenter = new DetailPresenter();
        presenter.setView(this);
        if (account != null && contact != null) {
            presenter.requestCustomer(account.getData().getUser_id() + "", contact.getId() + "", account.getData().getToken());
        }
    }

    private void initLayoutView(Contact contact) {

    }

    private void initViewStatus(Contact.User contact) {
        switch (contact.getStatus()) {
            case "0":
                //0 - Chưa Tele
                statusDetailContact.setText("Chưa Tele");
                statusDetailContact.setTag("0");
                break;
            case "1":
                //1 - Đồng ý gặp Sale/Đang theo
                statusDetailContact.setText("Đồng ý gặp Sale/Đang theo");
                statusDetailContact.setTag("1");
                break;
            case "2":
                //2 - Đã từ chối
                statusDetailContact.setText("Đã từ chối");
                statusDetailContact.setTag("2");
                break;
            case "3":
                //3 - Không nghe máy
                statusDetailContact.setText("Không nghe máy");
                statusDetailContact.setTag("3");

                break;
            case "4":
                //4 - Đã khóa
                statusDetailContact.setText("Đã khóa");
                statusDetailContact.setTag("4");
                break;
            case "5":
                //5 - Da chốt
                statusDetailContact.setText("Đã chốt");
                statusDetailContact.setTag("5");
                break;

            case "6":
                //5 - Da chốt
                statusDetailContact.setText("Quan tâm - Gọi lại");
                statusDetailContact.setTag("6");
                break;
        }
    }


    private void initDialogLoading() {
        circleProgressDialog = new CircleProgressDialog(getContext());
        circleProgressDialog.setCancelable(false);
        circleProgressDialog.setText(null);
    }

    @OnClick({R.id.actionCallContactDetail, R.id.actionSendMailContactDetail, R.id.actionBackDetail, R.id.actionStatusContact, R.id.actionUpdateContact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.actionBackDetail:
                FragmentUtil.popBackStack(getActivity());
                KeyboardUtil.hideSoftKeyboard(getActivity());
                break;
            case R.id.actionCallContactDetail:
                if (PermissionHelper.checkListPermission(permission_list, getActivity(), MY_PERMISSIONS_REQUEST_CALL_CONTACTS)) {
                    performCallContact();
                }
                KeyboardUtil.hideSoftKeyboard(getActivity());
                break;
            case R.id.actionSendMailContactDetail:
                KeyboardUtil.hideSoftKeyboard(getActivity());
                performSendMail();
                break;
            case R.id.actionStatusContact:
                KeyboardUtil.hideSoftKeyboard(getActivity());
                DialogFragment dialogFragment = DialogSelectStatus.getInstance();
                dialogFragment.show(getFragmentManager(), "");
                break;
            case R.id.actionUpdateContact:
                KeyboardUtil.hideSoftKeyboard(getActivity());
                performUpdateContact();
                break;
        }
    }

//    private boolean checkPermission() {
//
//
//
//        if (ContextCompat.checkSelfPermission(getActivity(),
//                Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//                    Manifest.permission.CALL_PHONE)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{Manifest.permission.CALL_PHONE},
//                        MY_PERMISSIONS_REQUEST_CALL_CONTACTS);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//            return false;
//        } else if (ContextCompat.checkSelfPermission(getActivity(),
//                Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//                    Manifest.permission.READ_PHONE_STATE)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{Manifest.permission.READ_PHONE_STATE},
//                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }

    private void performUpdateContact() {
        final Contact.User user = new Contact.User();
        user.setId(contact.getId());
        if (!noteContactDetail.getText().toString().equals("")) {
            user.setNote(noteContactDetail.getText().toString().trim());
            user.setMsisdn(contact.getMsisdn());
            user.setName(editFullNameContactDetail.getText().toString());
            user.setEmail(editEmailContactDetail.getText().toString());
            user.setProject_id(contact.getProject_id());
            user.setStatus(statusDetailContact.getTag() + "");
            if (account != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);

                builder
                        .setMessage("Bạn có chắc muốn cập nhật?")
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // Yes-code
                                presenter.updateContact(user, account.getData().getUser_id() + "", account.getData().getToken());
                                Toast.makeText(getActivity(), "Thông tin đã được cập nhật", Toast.LENGTH_SHORT).show();
                                FragmentUtil.popBackStack(getActivity());
                                Intent intent = new Intent("RELOAD_DATA");
                                getActivity().sendBroadcast(intent);
                            }
                        })
                        .setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
            // presenter.updateContact(user, account.getData().getUser_id() + "", account.getData().getToken());
        }
    }

    private void performCallContact() {
        presenter.postCallAction(account.getData().getUser_id(), contact.getMsisdn());
        if (contact != null) {
//            SharedPrefUtils.putBoolean("OUTGOINGCALL", true);
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getMsisdn()));
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:900" ));
            startActivity(intent);
            managers.show();
        }
    }

    private void performSendMail() {
        if (contact != null) {
            Intent gmail = new Intent(Intent.ACTION_SEND);
            gmail.putExtra(Intent.EXTRA_EMAIL, new String[]{contact.getEmail()});
            gmail.setData(Uri.parse(contact.getEmail()));
            gmail.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
            gmail.setType("plain/text");
            gmail.putExtra(Intent.EXTRA_TEXT, "EXTRA_TEXT");
            startActivity(gmail);
        }
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
    public void onUpdateContactSuccess(Update data) {
        if (data.getCode() == 210 || data.getCode() == 200) {
            Toast.makeText(getContext(), "Cập nhật thành công! ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateStatusUserUpdate(String status, String stringStatus) {
        statusDetailContact.setText(stringStatus);
        statusDetailContact.setTag(status);
    }

    @Override
    public void getCustomerSuccess(Contact data) {
        contact = data.getData().get(0);
        if (contact != null) {
            editTelContactDetail.setText(contact.getMsisdn());
            editEmailContactDetail.setText(contact.getEmail());
            if (contact.getName().equals("")) {
                editFullNameContactDetail.setText(contact.getMsisdn());
            } else
                editFullNameContactDetail.setText(contact.getName());
            noteContactDetail.setText(contact.getNote() + " ");
            initViewStatus(contact);
            noteContactDetail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String root = contact.getNote();
                    if (charSequence.toString().length() < root.length()) {
                        noteContactDetail.setText(contact.getNote() + " ");
                        return;
                    }
                    if (!charSequence.toString().substring(0, root.length()).contains(contact.getNote())) {
                        noteContactDetail.setText(contact.getNote() + " ");
                        return;
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            noteContactDetail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String root = contact.getNote();
                    if (charSequence.toString().length() < root.length()) {
                        noteContactDetail.setText(contact.getNote() + " ");
                        return;
                    }
                    if (!charSequence.toString().substring(0, root.length()).contains(contact.getNote())) {
                        noteContactDetail.setText(contact.getNote() + " ");
                        return;
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceive();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KeyboardUtil.hideSoftKeyboard(getActivity());
        getContext().unregisterReceiver(endCall);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_CONTACTS) {
            if (PermissionHelper.checkResult(grantResults)) {
                performCallContact();
            } else {
                ToastUtil.ShortShow(getActivity(), "Không thể thực hiện do bạn chưa cấp quyền!");
            }
        }
    }
}
