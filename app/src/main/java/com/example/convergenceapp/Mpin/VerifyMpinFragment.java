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
    public void callDeleteAPI(){


        if(NetworkFactory.isInternetOn(getContext()))
        {


            ProgressDialog   progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();



         //   *//**//*******make json object is encrypted and *********//**//*
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

               DeleteUnassignRequest deleteUnassignRequest=new DeleteUnassignRequest();
                String loginId= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getContext());
                String  deviceInfo= AppUtils.getInstance().getDeviceInfo();
                deleteUnassignRequest.setUser_id(loginId);
                deleteUnassignRequest.setImei_no(imeiNo);
                deleteUnassignRequest.setDevice_name(deviceInfo);
                deleteUnassignRequest.setLocation_coordinate("1232323");

                List<CheckAndDeleteBean> checkAndDeleteBeans=appDatabase.checkAndDeleteDao().getVillageCodeList();

                String VillageCode;
                String[] str =new String[checkAndDeleteBeans.size()];
                    for (int i=0;i<checkAndDeleteBeans.size();i++){
                        str[i]= checkAndDeleteBeans.get(i).getVillageCode();


                    }
                StringBuffer sb = new StringBuffer();
                int strlength = str.length-1;

                for(int i = 0; i<str.length; i++) {
                    sb.append(str[i]);

                    if (i!=strlength){
                        sb.append(",");

                    }

                }
                 VillageCode = sb.toString();

               //     Toast.makeText(getContext(), "village"+VillageCode, Toast.LENGTH_SHORT).show();

                deleteUnassignRequest.setVillage_code(VillageCode);


                String data=new Gson().toJson(deleteUnassignRequest);
                plainData=new JSONObject(data);
                //encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(nrlmMasterRequest)));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } //catch (InvalidKeyException e) {
            // e.printStackTrace();
            // } catch (InvalidAlgorithmParameterException e) {
            //  e.printStackTrace();
            //} catch (IllegalBlockSizeException e) {
            // e.printStackTrace();
            // } catch (BadPaddingException e) {
            //e.printStackTrace();
            // } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            //}
          //  *//**//***********************************************//**//*

            // AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
            Log.d(TAG, "request of delete "+plainData.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {
                    progressDialog.dismiss();
  // *//**//*                 JSONObject jsonObject = null;  //manish comment

                    String objectResponse="";
                    if(response.has("data")){
                        try {
                            objectResponse=response.getString("data");
                            //   AppUtils.getInstance().showLog("response encrupt"+objectResponse,LoginFragment.class);
                            Log.d(TAG, "response encrupt "+objectResponse.toString());


                        }catch (JSONException e)
                        {
                            //    AppUtils.getInstance().showLog("objjjjjjj"+objectResponse,LoginFragment.class);
                            Log.d(TAG, "objjjjjjj: "+objectResponse.toString());
                        }
                    }else {
                        return;
                    }

                    try {
                        JSONObject jsonObject1=new JSONObject(objectResponse);
                        objectResponse=jsonObject1.getString("data");
                        //    AppUtils.getInstance().showLog("dashboard"+jsonObject1,LoginFragment.class);
                        Log.d(TAG, "dashboard: "+jsonObject1.toString());

                    }catch (JSONException e)
                    {
                        // AppUtils.getInstance().showLog("exceptionDataOfBlock"+e,LoginFragment.class);
                        Log.d(TAG, "exceptionDataOfBlock: "+e.toString());

                    }
                    //*//**//*


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            Cryptography cryptography = new Cryptography();

                            Log.d(TAG, "responseJSON: "+response.toString());

                            DeleteUnassignResponse deleteUnassignResponse = DeleteUnassignResponse.jsonToJava(response.toString());
                           String code = deleteUnassignResponse.getStatus();
                           if (code.equalsIgnoreCase("Success")){

                          //     appDatabase.checkAndDeleteDao().deleteAll();
                               DialogFactory.getInstance().showAlert(getContext(),"Village are Removed Kindly Login next day","Ok");

                               logout();


                           }



                            //checkAndDeleteBean   = appDatabase.checkAndDeleteDao().getVillageCodeList().get();


                        } catch (Exception e) {
                            //progressDialog.dismiss();
                            Log.d(TAG, "notifySuccess: "+e);
                            //AppUtils.getInstance().showLog("DecryptEx" + e, LoginFragment.class);
                        }

                        progressDialog.dismiss();
                    }


                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    progressDialog.dismiss();

                }
            };
            VolleyService volleyService = VolleyService.getInstance(getContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("dashboard Nrlm", AppUtils.buildURL+"delunassignvill", plainData, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");
         //   Toast.makeText(getContext(),"No internet",Toast.LENGTH_LONG).show();



        }
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