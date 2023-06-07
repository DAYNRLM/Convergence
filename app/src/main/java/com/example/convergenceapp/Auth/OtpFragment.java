package com.example.convergenceapp.Auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chaos.view.PinView;
import com.example.convergenceapp.R;
import com.example.convergenceapp.utils.DialogFactory;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;
import com.example.convergenceapp.vollyCall.VolleyResult;
import com.google.android.material.textfield.TextInputEditText;


public class OtpFragment extends Fragment {

    PinView otpEdt ;
    Button verifyBtn, submitbtn ;
    public NavController navController;

    NavDirections navDirections;
    public VolleyResult mResultCallBack = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =       inflater.inflate(R.layout.fragment_otp, container, false);
        otpEdt = (PinView) view.findViewById(R.id.et_otp);
        verifyBtn = (Button) view.findViewById(R.id.verifyotpbtn);
        submitbtn = (Button) view.findViewById(R.id.submitnewpasbtn);

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enterOtp= otpEdt.getText().toString();
                String mainOtp = PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getRandomOtp(),getContext());

                if (!enterOtp.equalsIgnoreCase(mainOtp)){
                    DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!","Please Enter Valid OTP","Ok",true);

                }
                else {
                    callResetPasswordAPI();

                }

            }
        });








        return view;
    }

    public void callResetPasswordAPI(){

    }
}