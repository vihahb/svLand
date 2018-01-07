package com.goldit.managerinfo.coreapi.utils;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.goldit.managerinfo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogLoadingFragment extends DialogFragment {
    public static DialogLoadingFragment intance;

    public static DialogLoadingFragment getInstance() {
        if (intance == null) {
            synchronized (DialogLoadingFragment.class) {
                if (intance == null) {
                    intance = new DialogLoadingFragment();
                }
            }
        }
        return intance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dialog_loading, null);
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }
}
