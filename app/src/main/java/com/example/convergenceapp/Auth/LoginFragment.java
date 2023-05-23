package com.example.convergenceapp.Auth;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.VolleyError;

import com.example.convergenceapp.BuildConfig;
import com.example.convergenceapp.MpinActivity;
import com.example.convergenceapp.R;
import com.example.convergenceapp.database.AppDatabase;

import com.example.convergenceapp.database.entity.BankMasterEntity;
import com.example.convergenceapp.database.entity.LoginInfoEntity;

import com.example.convergenceapp.database.entity.MemberReasonEntity;
import com.example.convergenceapp.database.entity.MobileNoBelongsToEntity;
import com.example.convergenceapp.database.entity.NrlmInfoEntity;
import com.example.convergenceapp.database.entity.PmaygInfoEntity;
import com.example.convergenceapp.database.entity.ReasonInfoEntity;
import com.example.convergenceapp.request.BankMasterRequest;
import com.example.convergenceapp.request.LoginRequest;
import com.example.convergenceapp.request.NrlmMasterRequest;
import com.example.convergenceapp.request.PmaygMasterRequest;
import com.example.convergenceapp.response.NrlmMasterResponse;

import com.example.convergenceapp.response.PmaygMasterResponse;

import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.Cryptography;
import com.example.convergenceapp.utils.DialogFactory;
import com.example.convergenceapp.utils.NetworkFactory;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;
import com.example.convergenceapp.vollyCall.VolleyResult;
import com.example.convergenceapp.vollyCall.VolleyService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



public class LoginFragment extends Fragment {
    public Button loginBtn;
    public TextInputEditText loginIdEt;
    public  TextInputEditText loginPassEt;
    public NavController navController;
    public VolleyResult mResultCallBack = null;
    ProgressDialog progressDialog;
    String loginId;
    @SuppressLint("HardwareIds")  String  imeiNo;
    String deviceInfo;
    public String nrlmgp_code, nrlmlgd_gp_code, nrlmgp_name, nrlmvillage_code, nrlmvillage_name, nrlmshg_name, nrlmshg_code, nrlmmember_name
            , nrlmmember_code, nrlmuser_id, nrlmblock_name, nrlmlgd_state_code, nrlmstate_name, nrlmstate_code, nrlmblock_code,
            nrlmdistrict_name, nrlmlgd_district_code, nrlmlgd_block_code,  nrlmDistrictcode,nrlm_mem_bank_code;

    public String gp_code;
    public String gp_name;
    public String village_code;
    public String village_name;
    public String flag;
    public String ifsc_code;
    public String scheme;
    public String beneficiary_holder_name;
    public String beneficiary_id;
    public String beneficiary_acc_no;
    public String beneficiary_bank_name;
    public String beneficiary_branch_name;
    public String mobile_no;
    public String member_name;
    public String holder_sync_flag;
    public String mothername;
    public String districtname;
    public String blockcode;
    public String districtcode;
    public String statecode;
    public String fathername;
    public String blockname;
    public String sl_no_member;
    int nrlmcode,pmaygCode;

    NrlmMasterResponse nrlmMasterResponse;
    PmaygMasterResponse pmaygMasterResponse;
    AppDatabase appDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase=AppDatabase.getDatabase(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //handleSSLHandshake();




        return view;



    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        loginBtn = (Button) view.findViewById(R.id.btn_login);
        loginIdEt = (TextInputEditText) view.findViewById(R.id.et_userId);
        loginPassEt = (TextInputEditText) view.findViewById(R.id.et_password);
        navController = NavHostFragment.findNavController(this);
        // String mPin =   PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(), getContext());


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String password = loginPassEt.getText().toString();
                AppUtils.getInstance().showLog("Password"+password, LoginFragment.class);
                String userId = loginIdEt.getText().toString().trim().toUpperCase().replaceAll("\\s","");
                if (userId.equalsIgnoreCase("")) {
                    progressDialog.dismiss();
                    loginIdEt.setError(getString(R.string.invalid_userid));
                }
                else if (password.equalsIgnoreCase("")) {
                    progressDialog.dismiss();
                    loginPassEt.setError(getString(R.string.invalid_password));
                }

             //   callPmaygMasterAPI();
               // callNrlmMasterAPI();



                   getLoginAPI(userId,password);

                // String password=loginPassEt.getText().toString();

                // AppUtils.getInstance().showLog( "password"+ AppUtils.getInstance().getSha256(password), LoginFragment.class   );





            }


        });
    }
    private void intentToMpin() {
        Intent intent = new Intent(getContext(), MpinActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    private void callPmaygMasterAPI () {



        if(NetworkFactory.isInternetOn(getContext()))
        {

/*
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();*/




            /*******make json object is encrypted and *********/
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;

            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                PmaygMasterRequest pmaygMasterRequest=new PmaygMasterRequest();

                String  loginId=PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getContext());
                String  deviceInfo=AppUtils.getInstance().getDeviceInfo();
                pmaygMasterRequest.setUser_id(loginId);
                pmaygMasterRequest.setImei_no(imeiNo);
                pmaygMasterRequest.setDevice_name(deviceInfo);
                pmaygMasterRequest.setLocation_coordinate("1232323");


                encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(pmaygMasterRequest)));
               // String plnObj=new Gson().toJson(pmaygMasterRequest);
               // plainData =new JSONObject(plnObj);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } //catch (InvalidKeyException e) {
            catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            // e.printStackTrace();
            // } catch (InvalidAlgorithmParameterException e) {
            // e.printStackTrace();
            //} catch (IllegalBlockSizeException e) {
            //  e.printStackTrace();
            // } catch (BadPaddingException e) {
            // e.printStackTrace();
            // } catch (UnsupportedEncodingException e) {
            //   e.printStackTrace();
            // }
            /***********************************************/

            //AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
         //   Log.d(TAG, "request of PmaygMaster "+plainData.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {

                    //   progressDialog.dismiss();
                    JSONObject jsonObject = null;    //manish commented



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

                    try {               //manish commented
                        JSONObject jsonObject1=new JSONObject(objectResponse);
                        objectResponse=jsonObject1.getString("data");
                    //    AppUtils.getInstance().showLog("dashboard"+jsonObject1,LoginFragment.class);
                        Log.d(TAG, "dashboard: "+jsonObject1.toString());

                    }catch (JSONException e)
                    {
                       // AppUtils.getInstance().showLog("exceptionDataOfBlock"+e,LoginFragment.class);
                        Log.d(TAG, "exceptionDataOfBlock: "+e.toString());

                    }


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            List<String> memberBenIds=new ArrayList<>();

                            Cryptography cryptography = new Cryptography();
                             jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Manish comment
                            //if (jsonObject.getString("E200").equalsIgnoreCase("Success"))
                            // AppUtils.getInstance().showLog("responseJSON" + jsonObject.toString(), LoginFragment.class);
                            Log.d(TAG, "responseJSON: "+jsonObject.toString());

                            pmaygMasterResponse = PmaygMasterResponse.jsonToJava(jsonObject.toString());
                            pmaygCode= pmaygMasterResponse.getStatus();
                            //       AppUtils.getInstance().showLog("responseJSON" +message), LoginFragment.class);


                           /* if (code==200){



                                }*/

                            appDatabase.pmaygInfoDao().deleteAll();

                            //  progressDialog.dismiss();

                            // nrlmMasterResponse.getData();
                            for(int j=0;j<pmaygMasterResponse.getData().getAssign_data().size();j++){
                                gp_code=pmaygMasterResponse.getData().getAssign_data().get(j).getGrampanchayatcode();
                                gp_name=pmaygMasterResponse.getData().getAssign_data().get(j).getGrampanchayatname();
                                village_code=pmaygMasterResponse.getData().getAssign_data().get(j).getVillagecode();
                                village_name=pmaygMasterResponse.getData().getAssign_data().get(j).getVillagename();
                                scheme=pmaygMasterResponse.getData().getAssign_data().get(j).getScheme();
                                beneficiary_holder_name=pmaygMasterResponse.getData().getAssign_data().get(j).getBeneficiaryname();
                                beneficiary_id=pmaygMasterResponse.getData().getAssign_data().get(j).getReg_no();
                                beneficiary_acc_no=pmaygMasterResponse.getData().getAssign_data().get(j).getAccountno();
                                beneficiary_bank_name=pmaygMasterResponse.getData().getAssign_data().get(j).getBankname();
                                beneficiary_branch_name=pmaygMasterResponse.getData().getAssign_data().get(j).getBranchname();
                                mobile_no=pmaygMasterResponse.getData().getAssign_data().get(j).getMobileno();
                                member_name=pmaygMasterResponse.getData().getAssign_data().get(j).getFamilydet_name();
                                holder_sync_flag=pmaygMasterResponse.getData().getAssign_data().get(j).getFlag_sync();
                                mothername=pmaygMasterResponse.getData().getAssign_data().get(j).getMothername();
                                districtname=pmaygMasterResponse.getData().getAssign_data().get(j).getDistrictname();
                                blockcode=pmaygMasterResponse.getData().getAssign_data().get(j).getBlockcode();
                                districtcode=pmaygMasterResponse.getData().getAssign_data().get(j).getDistrictcode();
                                statecode=pmaygMasterResponse.getData().getAssign_data().get(j).getStatecode();
                                fathername=pmaygMasterResponse.getData().getAssign_data().get(j).getFathername();
                                blockname=pmaygMasterResponse.getData().getAssign_data().get(j).getBlockname();
                                sl_no_member=pmaygMasterResponse.getData().getAssign_data().get(j).getSl_no_member();
                                ifsc_code=pmaygMasterResponse.getData().getAssign_data().get(j).getIfsc_code();
                             String   nrlmVillageCode=pmaygMasterResponse.getData().getAssign_data().get(j).getNrlm_village_code();
                                flag="0";

                                AppUtils.getInstance().showLog("GPCode"+gp_code, LoginFragment.class);

                                appDatabase.pmaygInfoDao().insert(new PmaygInfoEntity(gp_code,gp_name,village_code,nrlmVillageCode
                                        ,village_name,scheme,beneficiary_holder_name,beneficiary_id
                                        ,beneficiary_acc_no,beneficiary_bank_name,beneficiary_branch_name,mobile_no,member_name,holder_sync_flag,mothername
                                        ,districtname,blockcode,districtcode,statecode,fathername,blockname,sl_no_member,ifsc_code,flag));
                                String beneficiaryname= pmaygMasterResponse.getData().getAssign_data().get(j).getBeneficiaryname();
                                //   Toast.makeText(getContext(),beneficiaryname,Toast.LENGTH_LONG).show();



                            }

                            memberBenIds=appDatabase.memberEntryInfoDao().getBenIds();
                            for(int i=0;i<memberBenIds.size();i++)
                            {
                                appDatabase.pmaygInfoDao().updateSyncFlag(memberBenIds.get(i));
                            }


                        } catch (Exception e) {
                            //    progressDialog.dismiss();
                            // Log.d(TAG, "notifySuccess: "+e);
                            AppUtils.getInstance().showLog("DecryptEx" + e, LoginFragment.class);
                        }
                        callNrlmMasterAPI();

                        //callBankMasterAPI();

                    }


                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    //progressDialog.dismiss();

                }
            };
            VolleyService  volleyService = VolleyService.getInstance(getContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("masterNrlm", AppUtils.buildURL+"pmaygassigndata", encryptedObject, mResultCallBack);



        }
        else {
            //   progressDialog.dismiss();
            Log.d(TAG, "Internet: ");

        }

    }
    private void callBankMasterAPI () {

        if(NetworkFactory.isInternetOn(getContext()))
        {
          //  JSONObject plainData=null;
            JSONObject encryptedObject =new JSONObject();

            try {
                Cryptography cryptography = new Cryptography();

                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                //   String nrlmDistrict = appDatabase.nrlmInfoDao().getDistrictCode();
                String loginId=PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getContext());
                String districtCode=PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPREF_KEY_distictcode(),getContext());
                //  Toast.makeText(getContext(), "district  "+ districtCode, Toast.LENGTH_SHORT).show();
                String  deviceInfo=AppUtils.getInstance().getDeviceInfo();
                BankMasterRequest nrlmMasterRequest=new BankMasterRequest();
                nrlmMasterRequest.setUser_id(loginId);
                nrlmMasterRequest.setImei_no(imeiNo);
                nrlmMasterRequest.setDevice_name(deviceInfo);
                nrlmMasterRequest.setLocation_coordinate("1232323");
                nrlmMasterRequest.setDistrict_code(districtCode);
             //   String data=new Gson().toJson(nrlmMasterRequest);
             //   plainData=new JSONObject(data);
                encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(nrlmMasterRequest)));

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "request of NrlmMaster "+encryptedObject.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject jsonObject) throws JSONException {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String  objectResponse=jsonObject.getString("data");

                        Cryptography cryptography = null;
                        try {
                            cryptography = new Cryptography();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (NoSuchPaddingException e) {
                            e.printStackTrace();
                        }
                        try {
                            jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Main data of state
                        } catch (InvalidKeyException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (InvalidAlgorithmParameterException e) {
                            e.printStackTrace();
                        } catch (IllegalBlockSizeException e) {
                            e.printStackTrace();
                        } catch (BadPaddingException e) {
                            e.printStackTrace();
                        }

                        try {
                            if(jsonObject.getInt("status")==1) {
                                JSONObject jsonData = jsonObject.getJSONObject("data");
                                JSONArray jsonBranchArray = jsonData.getJSONArray("branch_data");
                                if (jsonBranchArray.length() > 0) {
                                    appDatabase.bankMasterDao().deleteAll();
                                    for (int i = 0; i < jsonBranchArray.length(); i++) {
                                        JSONObject jsonBranchObject = jsonBranchArray.getJSONObject(i);
                                        String bankCode = jsonBranchObject.optString("bank_code");
                                        String bankName = jsonBranchObject.optString("bank_name");
                                        String branchCode = jsonBranchObject.optString("bank_branch_code");
                                        String branchName = jsonBranchObject.optString("bank_branch_name");
                                        String ifscCode = jsonBranchObject.optString("ifsc_code");
                                        String actNoLength = jsonBranchObject.optString("alength");

                                        appDatabase.bankMasterDao().insert(new BankMasterEntity(bankCode, bankName, branchCode, branchName, ifscCode, actNoLength));
                                    }
                                }
                            }



                        } catch (Exception e) {
                            Log.d(TAG, "notifySuccess: "+e);

                        }
                        //   callNrlmMasterAPI();


                    }


                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    //      progressDialog.dismiss();

                }
            };
            VolleyService  volleyService = VolleyService.getInstance(getContext());
            volleyService.postDataVolley("masterNrlm", AppUtils.buildURL+"bankbranchdata", encryptedObject, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");

        }

    }


    private void callNrlmMasterAPI () {

        if(NetworkFactory.isInternetOn(getContext()))
        {


    /*        progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
*/



            /*******make json object is encrypted and *********/
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                NrlmMasterRequest nrlmMasterRequest=new NrlmMasterRequest();
                String loginId= Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getContext());
                String  deviceInfo=AppUtils.getInstance().getDeviceInfo();
                nrlmMasterRequest.setUser_id(loginId);
                nrlmMasterRequest.setImei_no(imeiNo);
                nrlmMasterRequest.setDevice_name(deviceInfo);
                nrlmMasterRequest.setLocation_coordinate("1232323");



               // plainData=new JSONObject(data);
                encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(nrlmMasterRequest)));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } //catch (InvalidKeyException e) {
            catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
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
            /***********************************************/

            // AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
            Log.d(TAG, "request of NrlmMaster "+encryptedObject.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {
                    //        progressDialog.dismiss();
                    JSONObject jsonObject = null;  //manish comment

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


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            Cryptography cryptography = new Cryptography();
                            jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Manish comment
                            //if (jsonObject.getString("E200").equalsIgnoreCase("Success"))
                            // AppUtils.getInstance().showLog("responseJSON" + jsonObject.toString(), LoginFragment.class);
                            Log.d(TAG, "responseJSON: "+jsonObject.toString());

                            nrlmMasterResponse = NrlmMasterResponse.jsonToJava(jsonObject.toString());
                            nrlmcode= nrlmMasterResponse.getStatus();

                            appDatabase.nrlmInfoDao().deleteAll();




                            // nrlmMasterResponse.getData();

                            for(int j=0;j<nrlmMasterResponse.getData().getAssign_data().size();j++){

                                //  Toast.makeText(getContext(),shgCode,Toast.LENGTH_LONG).show();

                                nrlmgp_code=nrlmMasterResponse.getData().getAssign_data().get(j).getGrampanchayat_code();
                                nrlmlgd_gp_code=nrlmMasterResponse.getData().getAssign_data().get(j).getLgd_gp_code();
                                nrlmgp_name=nrlmMasterResponse.getData().getAssign_data().get(j).getGrampanchayat_name();
                                nrlmvillage_code=nrlmMasterResponse.getData().getAssign_data().get(j).getVillage_code();
                                nrlmvillage_name=nrlmMasterResponse.getData().getAssign_data().get(j).getVillage_name();
                                nrlmshg_name=nrlmMasterResponse.getData().getAssign_data().get(j).getGroup_name();
                                nrlmshg_code =nrlmMasterResponse.getData().getAssign_data().get(j).getShg_code();
                                nrlmmember_name=nrlmMasterResponse.getData().getAssign_data().get(j).getMember_name();
                                nrlmmember_code =nrlmMasterResponse.getData().getAssign_data().get(j).getShg_member_code();
                                nrlmuser_id=nrlmMasterResponse.getData().getAssign_data().get(j).getUser_id();
                                nrlmblock_name=nrlmMasterResponse.getData().getAssign_data().get(j).getBlock_name();
                                nrlmlgd_state_code=nrlmMasterResponse.getData().getAssign_data().get(j).getLgd_state_code();
                                nrlmstate_name=nrlmMasterResponse.getData().getAssign_data().get(j).getState_name();
                                nrlmstate_code=nrlmMasterResponse.getData().getAssign_data().get(j).getState_code();
                                nrlmblock_code=nrlmMasterResponse.getData().getAssign_data().get(j).getBlock_code();
                                nrlmdistrict_name=nrlmMasterResponse.getData().getAssign_data().get(j).getDistrict_name();
                                nrlmlgd_district_code=nrlmMasterResponse.getData().getAssign_data().get(j).getLgd_district_code();
                                nrlmlgd_block_code=nrlmMasterResponse.getData().getAssign_data().get(j).getLgd_block_code();
                                nrlmDistrictcode=nrlmMasterResponse.getData().getAssign_data().get(j).getDistrict_code();
                                nrlm_mem_bank_code=nrlmMasterResponse.getData().getAssign_data().get(j).getMem_bank_code();
                                String memBranchCode =nrlmMasterResponse.getData().getAssign_data().get(j).getMem_branch_code();
                                // Toast.makeText(getContext(),"branchCode"+memBranchCode,Toast.LENGTH_LONG).show();

                                String nrlm_mobile_number=nrlmMasterResponse.getData().getAssign_data().get(j).getMobile_number();
                                String nrlm_belonging_name=nrlmMasterResponse.getData().getAssign_data().get(j).getBelonging_name();
                                String actNum= nrlmMasterResponse.getData().getAssign_data().get(j).getAc_no();


                                appDatabase.nrlmInfoDao().insert(new NrlmInfoEntity(nrlmgp_code,memBranchCode,nrlm_mem_bank_code,nrlmlgd_gp_code,nrlmgp_name
                                        ,nrlmvillage_code,nrlmvillage_name,nrlmshg_name,nrlmshg_code
                                        ,nrlmmember_name,nrlmmember_code,nrlmuser_id,nrlmblock_name,nrlmlgd_state_code,nrlmstate_name
                                        ,nrlmstate_code,nrlmblock_code,nrlmdistrict_name,nrlmlgd_district_code,nrlmlgd_block_code,nrlm_mobile_number,nrlm_belonging_name,actNum));





                            }

                            Objects.requireNonNull(PreferenceFactory.getInstance()).saveSharedPrefrecesData(PreferenceKeyManager.getPREF_KEY_distictcode(),nrlmDistrictcode,getContext());



                        } catch (Exception e) {
                            //progressDialog.dismiss();
                            Log.d(TAG, "notifySuccess: "+e);
                        }

                        callBankMasterAPI();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                intentToMpin();


                            }
                        }, 10000);

                    }


                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    //      progressDialog.dismiss();

                }
            };
            VolleyService  volleyService = VolleyService.getInstance(getContext());

            volleyService.postDataVolley("masterNrlm", AppUtils.buildURL+"assigndata", encryptedObject, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");

        }

    }

    @SuppressLint("HardwareIds")
    public void getLoginAPI(String userId, String password)
    {
        if(NetworkFactory.isInternetOn(getContext()))
        {





            /*******make json object is encrypted and *********/
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();




                imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                AppUtils.getInstance().showLog("IMEI"+imeiNo, LoginFragment.class);
                Objects.requireNonNull(PreferenceFactory.getInstance()).saveSharedPrefrecesData(PreferenceKeyManager.getPrefImeiNo(),imeiNo,getContext());


                LoginRequest loginRequest=new LoginRequest();


                loginRequest.setUser_id(userId);
                loginRequest.setUser_password(AppUtils.getInstance().getSha256(password));
                loginRequest.setImei_no(imeiNo);
                loginRequest.setDevice_name(AppUtils.getInstance().getDeviceInfo());
                loginRequest.setApp_version(BuildConfig.VERSION_NAME);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    loginRequest.setDate(AppUtils.getInstance().getCurrentDate());
                }
                loginRequest.setAndroid_version(BuildConfig.VERSION_NAME);
                loginRequest.setLocation_coordinate("1232323");
                loginRequest.setAndroid_api_version("30");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    loginRequest.setLogout_time(AppUtils.getInstance().getCurrentDateAndTime());
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    loginRequest.setApp_login_time(AppUtils.getInstance().getCurrentDateAndTime());
                }


                String data=new Gson().toJson(loginRequest);
             //   plainData=new JSONObject(data);
                encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(loginRequest)));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } //catch (InvalidKeyException e) {
            catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

            /***********************************************/

            Log.d(TAG, "request of LoginApi "+encryptedObject.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {


                  //  progressDialog.dismiss();
                    JSONObject jsonObject = null;  //manish comment

                    String objectResponse="";
                    if(response.has("data")){
                        try {
                            objectResponse=response.getString("data");
                            Log.d(TAG, "response encrupt "+objectResponse.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        JSONObject jsonObject1=new JSONObject(objectResponse);
                        objectResponse=jsonObject1.getString("data");
                        Log.d(TAG, "dashboard: "+jsonObject1.toString());

                    }catch (JSONException e)
                    {
                        Log.d(TAG, "exceptionDataOfBlock: "+e.toString());

                    }

                    Objects.requireNonNull(PreferenceFactory.getInstance()).saveSharedPrefrecesData(PreferenceKeyManager.getPrefLoginId(),userId,getContext());


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            Cryptography cryptography = new Cryptography();
                            jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Manish comment
                            //if (jsonObject.getString("E200").equalsIgnoreCase("Success"))
                            // AppUtils.getInstance().showLog("responseJSON" + jsonObject.toString(), LoginFragment.class);
                            JSONObject viewData=response;
                            Log.d(TAG, "responseJSON: "+viewData.toString());

                            // appDatabase.nrlmInfoDao().deleteAll();
                          //  int size=response.length();
                            JSONObject data=jsonObject.getJSONObject("data");
                            JSONObject userData=data.getJSONObject("user_data");

                            if (!(userData.has("Errorstatus"))) {



                                for (int i = 0; i < 1; i++) {
                                    try {
                                        //   JSONObject data=response.getJSONObject("data");
                                        JSONArray reason = data.getJSONArray("reason");
                                        //    JSONObject userData=data.getJSONObject("user_data");
                                        JSONArray mobBelongsToJsonArray = data.getJSONArray("mob_belongs_to");
                                        JSONArray memberReasonJsonArray = data.getJSONArray("MemberReason");

                                        appDatabase.loginInfoDao().insert(new LoginInfoEntity(userData.getString("user_id"), "123456", userData.getString("mobile_number")
                                                , userData.getString("state_code"), userData.getString("state_short_name"), userData.getString("server_date_time"), userData.getString("language_id")
                                                , userData.getString("login_attempt"), userData.getString("logout_days"), userData.getString("user_name")));


                                        appDatabase.reasonInfoDao().deleteAll();
                                        for (int j = 0; j < reason.length(); j++) {
                                            JSONObject reasonObject = reason.getJSONObject(j);
                                            String reasonName = reasonObject.getString("reason_name");
                                            String reasonCode = reasonObject.getString("reason_id");
                                            appDatabase.reasonInfoDao().insert(new ReasonInfoEntity(reasonName, reasonCode));
                                        }

                                        appDatabase.mobileNoBelongsToDao().deleteAll();

                                        for (int k = 0; k < mobBelongsToJsonArray.length(); k++) {
                                            JSONObject mobBelongsToObject = mobBelongsToJsonArray.getJSONObject(k);
                                            String typeId = mobBelongsToObject.getString("type_id");
                                            String typeName = mobBelongsToObject.getString("type_name");
                                            appDatabase.mobileNoBelongsToDao().insert(new MobileNoBelongsToEntity(typeId, typeName));
                                        }

                                        appDatabase.memberReasonDao().deleteAll();
                                        for (int k = 0; k < memberReasonJsonArray.length(); k++) {
                                            JSONObject memberReasonObject = memberReasonJsonArray.getJSONObject(k);
                                            int reasonId = memberReasonObject.getInt("reason_id");
                                            String reasonName = memberReasonObject.getString("reason");
                                            appDatabase.memberReasonDao().insert(new MemberReasonEntity(reasonId, reasonName));
                                        }


                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                callPmaygMasterAPI();
                            }
                            else {
                                progressDialog.dismiss();
                             //   callPmaygMasterAPI();
                                DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!",userData.getString("Errorstatus"),"Ok",true);

                            }

                            // nrlmMasterResponse.getData();




                        } catch (Exception e) {
                            //progressDialog.dismiss();
                            Log.d(TAG, "notifySuccess: "+e);
                            //AppUtils.getInstance().showLog("DecryptEx" + e, LoginFragment.class);
                        }

                        // progressDialog.dismiss();
                        // intentToMpin();
                        //callNrlmMasterAPI();


                    }

//progressDialog.dismiss();
                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                          progressDialog.dismiss();
                    Toast.makeText(getContext(), "No Internet"+error, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "getLoginAPI: "+error);

                }
            };
            VolleyService  volleyService = VolleyService.getInstance(getContext());

            volleyService.postDataVolley("loginAPI", AppUtils.buildURL+"login", encryptedObject, mResultCallBack);



        }else {
            progressDialog.dismiss();
            Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();



        }

    }


}



