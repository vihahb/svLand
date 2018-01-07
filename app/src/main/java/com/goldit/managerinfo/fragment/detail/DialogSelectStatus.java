package com.goldit.managerinfo.fragment.detail;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.goldit.managerinfo.R;

import static com.goldit.managerinfo.fragment.detail.DetailContactFragment.presenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogSelectStatus extends DialogFragment {

    public static DialogSelectStatus instance;

    public static DialogSelectStatus getInstance() {
        instance = new DialogSelectStatus();
        return instance;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_status, null);
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setTitle("Finish");
        dialog.show();
        final TextView actionStatus0 = dialog.findViewById(R.id.actionStatus0);
        final TextView actionStatus1 = dialog.findViewById(R.id.actionStatus1);
        final TextView actionStatus2 = dialog.findViewById(R.id.actionStatus2);
        final TextView actionStatus3 = dialog.findViewById(R.id.actionStatus3);
        final TextView actionStatus4 = dialog.findViewById(R.id.actionStatus4);
        final TextView actionStatus5 = dialog.findViewById(R.id.actionStatus5);
        final TextView actionStatus6 = dialog.findViewById(R.id.actionStatus6);

        actionStatus0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setStatus(actionStatus0.getTag().toString(), actionStatus0.getText().toString());
                dialog.dismiss();
            }
        });
        actionStatus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setStatus(actionStatus1.getTag().toString(), actionStatus1.getText().toString());
                dialog.dismiss();

            }
        });
        actionStatus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setStatus(actionStatus2.getTag().toString(), actionStatus2.getText().toString());
                dialog.dismiss();

            }
        });
        actionStatus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setStatus(actionStatus3.getTag().toString(), actionStatus3.getText().toString());
                dialog.dismiss();

            }
        });
        actionStatus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setStatus(actionStatus4.getTag().toString(), actionStatus4.getText().toString());
                dialog.dismiss();

            }
        });
        actionStatus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setStatus(actionStatus5.getTag().toString(), actionStatus5.getText().toString());
                dialog.dismiss();

            }
        });

        actionStatus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setStatus(actionStatus6.getTag().toString(), actionStatus6.getText().toString());
                dialog.dismiss();

            }
        });

        return dialog;
    }


}
