package com.example.convergenceapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.convergenceapp.Auth.HomeFragment;
import com.example.convergenceapp.Auth.HomeFragmentDirections;
import com.example.convergenceapp.database.AppDatabase;


import com.example.convergenceapp.database.entity.MemberEntryInfoEntity;
import com.example.convergenceapp.request.BeneficiaryDetails;
import com.example.convergenceapp.request.PmaygDashboardRequest;

import com.example.convergenceapp.request.SyncRequest;
import com.example.convergenceapp.response.NrlmMasterResponse;
import com.example.convergenceapp.response.PmaygDashboardResponse;

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

public class PmaygDashboardActivity extends AppCompatActivity {
    Button update;
    public VolleyResult mResultCallBack = null;
    ProgressDialog progressDialog;
    String totalmemberAllotted,gpAlotted,villageAlloted,surveyComplete,surveyPending,locallySave;
    TextView gpAllottxt,villageAllotttxt,memAllottxt,surveyComtxt,surveyPentxt,locallySavetxt;
    AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmayg_dashboard);
        gpAllottxt =findViewById(R.id.gp_number);
        villageAllotttxt =findViewById(R.id.village_alloted);
        memAllottxt =findViewById(R.id.ben_allot);
        surveyComtxt =findViewById(R.id.ben_survey_completed);
        surveyPentxt =findViewById(R.id.ben_survey_pending);
        locallySavetxt =findViewById(R.id.beneficiarysync_locally);
        update=findViewById(R.id.btn_update);
       Button sync=findViewById(R.id.btn_syncp);
       ImageView imageView=findViewById(R.id.backarrowp);
        appDatabase= AppDatabase.getDatabase(getApplicationContext());
        locallySave=appDatabase.memberEntryInfoDao().getLocalBenEntry();
        locallySavetxt.setText(appDatabase.memberEntryInfoDao().getLocalBenEntry());
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!locallySave.equalsIgnoreCase("0")){
                    List<MemberEntryInfoEntity>  membersyncdata=appDatabase.memberEntryInfoDao().getSyncData("0");

                    if(NetworkFactory.isInternetOn(getApplicationContext())) {
                        String userid=PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getApplicationContext());
                        String imei= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefImeiNo(),getApplicationContext());
                        String deviceInfo= AppUtils.getInstance().getDeviceInfo();

                        syncAPI(userid,imei ,deviceInfo, "1232323", membersyncdata);

                    }else {
                        Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();

                    }
                }


            }
        });


          callPmaygDashboardApi();
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(PmaygDashboardActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
});

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callPmaygDashboardApi();

                    }
                }, 1000);


            }
        });


    }

    public void callPmaygDashboardApi(){


        if(NetworkFactory.isInternetOn(getApplicationContext()))
        {


            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();



          //  ******make json object is encrypted and ********
            JSONObject encryptedObject =new JSONObject();
           // JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                PmaygDashboardRequest pmaygDashboardRequest=new PmaygDashboardRequest();
                String loginId= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getApplicationContext());
                String  deviceInfo= AppUtils.getInstance().getDeviceInfo();
                pmaygDashboardRequest.setUser_id(loginId);
                pmaygDashboardRequest.setImei_no(imeiNo);
                pmaygDashboardRequest.setDevice_name(deviceInfo);
                pmaygDashboardRequest.setLocation_coordinate("1232323");



                String data=new Gson().toJson(pmaygDashboardRequest);
           //     plainData=new JSONObject(data);
                encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(pmaygDashboardRequest)));
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
         //   *********************************************

            // AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
            Log.d(TAG, "request of NrlmMaster "+encryptedObject.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {

                    progressDialog.dismiss();

                    JSONObject jsonObject = null;  //manish comment

                    String objectResponse="";
                    if(response.has("data")){
                        try {
                            objectResponse=response.getString("data");
                               //AppUtils.getInstance().showLog("response encrupt"+objectResponse,PmaygDashboardActivity.class);
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

                            Log.d(TAG, "responseJSON: "+response.toString());

                            jsonObject = new JSONObject(cryptography.decrypt(objectResponse));

                            PmaygDashboardResponse pmaygDashboardResponse = PmaygDashboardResponse.jsonToJava(jsonObject.toString());
                        //    String statusCode= nrlmDashboardResponse.getStatus();







                            totalmemberAllotted= pmaygDashboardResponse.getData().getTot_allot();
                            gpAlotted= pmaygDashboardResponse.getData().getGp_allot();
                            villageAlloted= pmaygDashboardResponse.getData().getVillage_allot();
                            surveyComplete= pmaygDashboardResponse.getData().getCompleted();
                            surveyPending= pmaygDashboardResponse.getData().getPending();


                          gpAllottxt.setText(gpAlotted);
                            villageAllotttxt.setText(villageAlloted);
                            surveyComtxt.setText(surveyComplete);
                            surveyPentxt.setText(surveyPending);
                            locallySavetxt.setText(locallySave);
                            memAllottxt.setText(totalmemberAllotted);
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyPmaygbenalot(),totalmemberAllotted , getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyPmayggpalot(),gpAlotted, getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyPmaygvillagealot(),villageAlloted , getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyPmaygsurveycom(),surveyComplete , getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyPmaygsurveypen(),surveyPending , getApplicationContext());



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
                    gpAllottxt.setText("0");
                    villageAllotttxt.setText("0");
                    surveyComtxt.setText("0");
                    surveyPentxt.setText("0");
                    locallySavetxt.setText(locallySave);
                    memAllottxt.setText("0");

                }
            };
            VolleyService volleyService = VolleyService.getInstance(getApplicationContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("dashboard Nrlm", AppUtils.buildURL+"pmaygdashdata", encryptedObject, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");
            Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_LONG).show();

           // progressDialog.dismiss();


            String memberAlotted = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyPmaygbenalot(),getApplicationContext());
            String gpAlotted = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyPmayggpalot(), getApplicationContext());
            String villageAlotted = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyPmaygvillagealot(), getApplicationContext());
            String surveyCom = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyPmaygsurveycom(), getApplicationContext());
            String surveyPen = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyPmaygsurveypen(), getApplicationContext());

            String completed= String.valueOf(Integer.parseInt(memberAlotted)  - Integer.parseInt(surveyPen));
            gpAllottxt.setText(gpAlotted);
            villageAllotttxt.setText(villageAlotted);
            surveyComtxt.setText(completed);
            surveyPentxt.setText(surveyPen);
            locallySavetxt.setText(locallySave);
            memAllottxt.setText(memberAlotted);


        }
    }


    public void syncAPI(String userid, String imei, String device, String location, List<MemberEntryInfoEntity> memberSyncData )
    {
        if(NetworkFactory.isInternetOn(getApplicationContext()))
        {

            ProgressDialog   progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            List<String> benIdList=new ArrayList<>();

            //*******make json object is encrypted and *********//*
            JSONObject encryptedObject =new JSONObject();
            //JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();



                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                ArrayList<BeneficiaryDetails> Bendata = new ArrayList<>();


                for(int i=0;i<memberSyncData.size();i++) {
                    BeneficiaryDetails beneficiaryDetails=new BeneficiaryDetails();


                    beneficiaryDetails.setScheme_name(memberSyncData.get(i).getScheme_Name());
                    beneficiaryDetails.setReg_no(memberSyncData.get(i).getBen_Id());
                    beneficiaryDetails.setLgd_gp_cd(memberSyncData.get(i).getLgd_GpCode());
                    beneficiaryDetails.setLgd_vill_cd(memberSyncData.get(i).getLgd_Villagecode());
                    beneficiaryDetails.setMobile_no(memberSyncData.get(i).getMobile_no());
                    beneficiaryDetails.setBenif_avail(memberSyncData.get(i).getBen_availability());
                    beneficiaryDetails.setFamily_mem_shg(memberSyncData.get(i).getAny_Familyinshg());
                    beneficiaryDetails.setJoin_shg(memberSyncData.get(i).getWilling_joinshg());
                    beneficiaryDetails.setReason(memberSyncData.get(i).getReason());
                    beneficiaryDetails.setShg_code(memberSyncData.get(i).getShg_Code());
                    beneficiaryDetails.setShg_member_code(memberSyncData.get(i).getMember_Code());
                    beneficiaryDetails.setEntity_code(memberSyncData.get(i).getVillage_Code());
                    beneficiaryDetails.setApp_ver(memberSyncData.get(i).getAppVersion());
                    beneficiaryDetails.setCreated_on_app(memberSyncData.get(i).getCreated_on());
                    benIdList.add(memberSyncData.get(i).getBen_Id());
                    Bendata.add(beneficiaryDetails);
                }


                SyncRequest syncRequest = new SyncRequest();
                syncRequest.setUser_id(userid);
                syncRequest.setImei_no(imeiNo);
                syncRequest.setDevice_name(device);
                syncRequest.setLocation_coordinate(location);
                syncRequest.setBenficiary_dtl(Bendata);


                AppUtils.getInstance().showLog("Sync Data"+encryptedObject, HomeFragment.class);
                encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(syncRequest)));
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

            Log.d(TAG, "request of Sync "+encryptedObject.toString());


            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {
                    JSONObject jsonObject = null;

                    String objectResponse="";
                    if(response.has("data")){
                        try {
                            objectResponse=response.getString("data");

                        }catch (JSONException e)
                        {
                            AppUtils.getInstance().showLog("ExceptionInVerifyMobile" +
                                    ""+e,HomeFragment.class);
                        }
                    }else {
                        return;
                    }

                    try {
                        JSONObject jsonObject1=new JSONObject(objectResponse);
                        objectResponse=jsonObject1.getString("data");
                        AppUtils.getInstance().showLog("dataAtSubmit"+jsonObject1,HomeFragment.class);
                    }catch (JSONException e)
                    {
                        AppUtils.getInstance().showLog("exceptionAtSubmit"+e,HomeFragment.class);

                    }


                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            Cryptography cryptography = new Cryptography();
                            jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Main data of state
                            AppUtils.getInstance().showLog("responseJSON" + jsonObject.toString(), HomeFragment.class);
                        } catch (Exception e) {
                            AppUtils.getInstance().showLog("DecryptEx" + e, HomeFragment.class);
                        }
                    }

                    try {
                        if(jsonObject.getString("message").equalsIgnoreCase("success"))
                        {

                            for (int i=0;i<benIdList.size();i++)
                            {
                                String benId=benIdList.get(i);
                                appDatabase.memberEntryInfoDao().setUpdateSyncFlag(benId);
                                appDatabase.pmaygInfoDao().updateSyncFlag(benId);
                            }
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Synced successfully",Toast.LENGTH_LONG).show();
                            locallySavetxt.setText(locallySave);
                            Intent i = new Intent(PmaygDashboardActivity.this, PmaygDashboardActivity.class);
                            startActivity(i); // invoke the SecondActivity.
                            finish();

                            AppUtils.getInstance().showLog("Synced", HomeFragment.class);

                        }


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            try {

                                JSONObject viewData=response;
                                Log.d(TAG, "responseJSON: "+viewData.toString());








                            } catch (Exception e) {
                                Log.d(TAG, "notifySuccess: "+e);
                            }


                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                         progressDialog.dismiss();

                }
            };
            VolleyService volleyService = VolleyService.getInstance(getApplicationContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("Request of sync", AppUtils.buildURL+"syncdata", encryptedObject, mResultCallBack);



        }else {
            //Log.d(TAG, "Internet: ");*//*


        }
    }
}