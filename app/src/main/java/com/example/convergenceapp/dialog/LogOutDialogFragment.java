package com.example.convergenceapp.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.convergenceapp.LoginActivity;
import com.example.convergenceapp.R;



import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import java.sql.Timestamp;
import java.util.Date;

public class LogOutDialogFragment extends DialogFragment {
    AlertDialog alertDialog;
    //CustomProgressDialog customProgressDialog;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //customProgressDialog =CustomProgressDialog.newInstance(requireContext());
      //  loginRepo = LoginRepo.getInstance(AppExecutor.getInstance().threadExecutor(), requireContext());
        //appUtils=AppUtils.getInstance();
        //appDateFactory= AppDateFactory.getInstance();
        alertDialog =   new MaterialAlertDialogBuilder(requireContext()).setIcon(R.drawable.ic_baseline_logout_24)
                .setTitle(getContext().getResources().getString(R.string.dialog_sign_out_title)).setMessage(getContext().getResources().getString(R.string.dialog_sign_out_msg))
                .setCancelable(false)
                .setPositiveButton(getContext().getResources().getString(R.string.dialog_btn_signout), (dialogInterface, i) -> {



                    //PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefLoginSessionKey(),"",requireContext());
                    //ViewUtilsKt.toast(requireContext(),getContext().getResources().getString(R.string.toast_success_sign_out));
                    dialogInterface.dismiss();
                    //PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefLogoutTime(),""+new Timestamp(new Date().getTime()),getContext());
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getContext().startActivity(intent);
                    getActivity().finish();

                }).setNegativeButton(getContext().getResources().getString(R.string.dialog_cancel_btn), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).show();
        setCancelable(false);
        return alertDialog;
    }
}
