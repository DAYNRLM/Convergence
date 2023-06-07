package com.example.convergenceapp.Mpin;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.convergenceapp.HomeActivity;
import com.example.convergenceapp.LoginActivity;
import com.example.convergenceapp.MpinActivity;
import com.example.convergenceapp.R;
import com.example.convergenceapp.database.AppDatabase;
import com.example.convergenceapp.database.dbBean.CheckAndDeleteBean;
import com.example.convergenceapp.database.entity.CheckAndDeleteEntity;
import com.example.convergenceapp.database.entity.MemberEntryInfoEntity;
import com.example.convergenceapp.databinding.FragmentSetMpinBinding;
import com.example.convergenceapp.databinding.FragmentVerifyMpinBinding;
import com.example.convergenceapp.request.DeleteUnassignRequest;
import com.example.convergenceapp.request.PmaygDashboardRequest;
import com.example.convergenceapp.request.UnassignRequest;
import com.example.convergenceapp.response.DeleteUnassignResponse;
import com.example.convergenceapp.response.NrlmDashboardResponse;
import com.example.convergenceapp.response.UnassignResponse;

import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.Cryptography;
import com.example.convergenceapp.utils.DialogFactory;
import com.example.convergenceapp.utils.NetworkFactory;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;
import com.example.convergenceapp.vollyCall.VolleyResult;
import com.example.convergenceapp.vollyCall.VolleyService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.NoSuchPaddingException;

public class VerifyMpinFragment extends Fragment {
    private FragmentVerifyMpinBinding binding;
    String enterPin, mPin;
    Boolean areEqual;
    public VolleyResult mResultCallBack = null;
    AppDatabase appDatabase;
    String VillageCode;
    String statusCode;

    CheckAndDeleteBean checkAndDeleteBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPin =   PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(), getContext());
        appDatabase= AppDatabase.getDatabase(getContext());

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVerifyMpinBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enterPin = binding.pinviewGetMpin.getText().toString();
                if (enterPin.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Mpin should not be empty", Toast.LENGTH_LONG).show();
                }
                else {
                    areEqual = enterPin.equals(mPin);
                    if (areEqual) {
                    //    callUnassignApi();
                        intentToHome();
                    }
                    else {
                        Toast.makeText(getContext(), "Mpin is wrong ", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


        return view;

    }

         private void intentToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(){
       /* appDatabase.pmaygInfoDao().deleteAll();
        appDatabase.nrlmInfoDao().deleteAll();
        appDatabase.loginInfoDao().deleteAll();
        appDatabase.reasonInfoDao().deleteAll();
        appDatabase.bankMasterDao().deleteAll();*/
        PreferenceFactory.getInstance().removeSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(),getContext());

        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
       /*  NavDirections navDirections= HomeFragmentDirections.actionHomeFragmentToLoginFragment2();
        NavController navController=navHostFragment.getNavController();
        navController.navigate(navDirections);*/
    }
}