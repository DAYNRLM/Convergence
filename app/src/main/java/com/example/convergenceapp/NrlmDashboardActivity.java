package com.example.convergenceapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import com.example.convergenceapp.Auth.LoginFragment;
import com.example.convergenceapp.Auth.MemberFragmentDirections;
import com.example.convergenceapp.database.AppDatabase;
import com.example.convergenceapp.database.dbBean.NrlmBenefeciaryMobileBean;
import com.example.convergenceapp.database.entity.MemberEntryInfoEntity;
import com.example.convergenceapp.request.BenficiaryDtl;
import com.example.convergenceapp.request.MemberSyncRequest;
import com.example.convergenceapp.request.PmaygDashboardRequest;
import com.example.convergenceapp.response.NrlmDashboardResponse;
import com.example.convergenceapp.response.PmaygDashboardResponse;

import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.Cryptography;
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

public class NrlmDashboardActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    Button update;
    public VolleyResult mResultCallBack = null;
    AppDatabase appDatabase;

    String totalmemberAllotted,gpAlotted,villageAlloted,surveyComplete,surveyPending,locallySave;
    TextView gpAllottxt,villageAllotttxt,memAllottxt,surveyComtxt,surveyPentxt,locallySavetxt;
    List<NrlmBenefeciaryMobileBean>  nrlmBenefeciaryMobileBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nrlm_dashboard);

        update =findViewById(R.id.btn_nrlm_update);
       Button sync =findViewById(R.id.btn_syncn);
        gpAllottxt =findViewById(R.id.gp_nrlm_number);
        villageAllotttxt =findViewById(R.id.village_nrlm_alloted);
        memAllottxt = findViewById(R.id.member_allotted_new);
        surveyComtxt =findViewById(R.id.member_survey_completed);
        surveyPentxt =findViewById(R.id.member_survey_pending);
        locallySavetxt =findViewById(R.id.member_survey_locally_new);
      ImageView imageView =findViewById(R.id.backarrown);
        appDatabase= AppDatabase.getDatabase(getApplicationContext());
        callNrlmDashboardApi();

        locallySave=appDatabase.nrlmBenefeciaryMobileDao().getLocalMobileEntry();

        locallySavetxt.setText(appDatabase.nrlmBenefeciaryMobileDao().getLocalMobileEntry());
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!locallySave.equalsIgnoreCase("0")){

                        syncApi();

                }


            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NrlmDashboardActivity.this, HomeActivity.class);
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
                        callNrlmDashboardApi();

                    }
                }, 1000);

            }
        });



    }

    public void callNrlmDashboardApi(){


        if(NetworkFactory.isInternetOn(getApplicationContext()))
        {


            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();



            /*******make json object is encrypted and *********/
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
             //   plainData=new JSONObject(data);
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
            /***********************************************/

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

                            Log.d(TAG, "responseJSON: "+response.toString());
                            jsonObject = new JSONObject(cryptography.decrypt(objectResponse));


                            NrlmDashboardResponse nrlmDashboardResponse = NrlmDashboardResponse.jsonToJava(jsonObject.toString());
                       //     String statusCode= nrlmDashboardResponse.getStatus();







                            totalmemberAllotted= nrlmDashboardResponse.getData().getTot_allot();
                            gpAlotted= nrlmDashboardResponse.getData().getGp_allot();
                            villageAlloted= nrlmDashboardResponse.getData().getVillage_allot();
                            surveyComplete= nrlmDashboardResponse.getData().getCompleted();
                            surveyPending= nrlmDashboardResponse.getData().getPending();

                            memAllottxt.setText(totalmemberAllotted);
                            gpAllottxt.setText(gpAlotted);
                            villageAllotttxt.setText(villageAlloted);
                            surveyComtxt.setText(surveyComplete);
                            surveyPentxt.setText(surveyPending);
                         //   String local=  appDatabase.nrlmBenefeciaryMobileDao().getLocalMobileEntry();
                            locallySavetxt.setText(appDatabase.nrlmBenefeciaryMobileDao().getLocalMobileEntry());





                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyNrlmmemalot(),totalmemberAllotted , getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyNrlmgpalot(),gpAlotted, getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyNrlmvillagealot(),villageAlloted , getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyNrlmsurveycom(),surveyComplete , getApplicationContext());
                            PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyNrlmsurveypen(),surveyPending , getApplicationContext());




                            //    Toast.makeText(getApplicationContext(),totalBenAllotted,Toast.LENGTH_LONG).show();










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
                    locallySavetxt.setText(appDatabase.nrlmBenefeciaryMobileDao().getLocalMobileEntry());
                    memAllottxt.setText("0");

                }
            };
            VolleyService volleyService = VolleyService.getInstance(getApplicationContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("dashboard Nrlm", AppUtils.buildURL+"nrlmdashdata", encryptedObject, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");
            Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_LONG).show();
            //progressDialog.dismiss();
            String memberAlotted = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyNrlmmemalot(),getApplicationContext());
            String gpAlotted = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyNrlmgpalot(), getApplicationContext());
            String villageAlotted = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyNrlmvillagealot(), getApplicationContext());
            String surveyCom = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyNrlmsurveycom(), getApplicationContext());
            String surveyPen = Objects.requireNonNull(PreferenceFactory.getInstance()).getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyNrlmsurveypen(), getApplicationContext());


            gpAllottxt.setText(gpAlotted);
            villageAllotttxt.setText(villageAlotted);
            surveyComtxt.setText(surveyCom);
            surveyPentxt.setText(surveyPen);
            locallySavetxt.setText(appDatabase.nrlmBenefeciaryMobileDao().getLocalMobileEntry());
            memAllottxt.setText(memberAlotted);



        }
    }

    public void syncApi(){



        if(NetworkFactory.isInternetOn(getApplicationContext()))
        {

            ProgressDialog   progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();




            /*******make json object is encrypted and *********/
            JSONObject encryptedObject =new JSONObject();
            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                MemberSyncRequest memberSyncRequest=new MemberSyncRequest();

                String  loginId= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getApplicationContext());
                String  deviceInfo=AppUtils.getInstance().getDeviceInfo();
                memberSyncRequest.setUser_id(loginId);
                memberSyncRequest.setImei_no(imeiNo);
                memberSyncRequest.setDevice_name(deviceInfo);
                memberSyncRequest.setLocation_coordinate("1232323");
                nrlmBenefeciaryMobileBeans=appDatabase.nrlmBenefeciaryMobileDao().getNrlmBenefeciaryMobileDataAcordingSyncFlag("0");
                List<String> nrlmMemberList=new ArrayList<>();

                ArrayList<BenficiaryDtl> Bendata = new ArrayList<>();

                for (int i=0;i<nrlmBenefeciaryMobileBeans.size();i++){



                    BenficiaryDtl beneficiaryDetails=new BenficiaryDtl();

                    beneficiaryDetails.setShg_code(nrlmBenefeciaryMobileBeans.get(i).getShg_code());
                    beneficiaryDetails.setShg_member_code(nrlmBenefeciaryMobileBeans.get(i).getMember_code());
                    beneficiaryDetails.setEntity_code(nrlmBenefeciaryMobileBeans.get(i).getVillage_code());
                    beneficiaryDetails.setMob_no_belongs_to(nrlmBenefeciaryMobileBeans.get(i).getMobile_belongs_to());
                    beneficiaryDetails.setMob_no(nrlmBenefeciaryMobileBeans.get(i).getMobile_number());
                    beneficiaryDetails.setBank_code(nrlmBenefeciaryMobileBeans.get(i).getBank_code());
                    beneficiaryDetails.setBranch_code(nrlmBenefeciaryMobileBeans.get(i).getBranch_code());
                    beneficiaryDetails.setAc_no(nrlmBenefeciaryMobileBeans.get(i).getAccount_number());
                    beneficiaryDetails.setReason_id(nrlmBenefeciaryMobileBeans.get(i).getReason_of_discontinue());
                    beneficiaryDetails.setApp_ver(BuildConfig.VERSION_NAME);
                    beneficiaryDetails.setCreated_on_app(nrlmBenefeciaryMobileBeans.get(i).getEntered_date());
                    nrlmMemberList.add(nrlmBenefeciaryMobileBeans.get(i).getMember_code());
                    Bendata.add(beneficiaryDetails);

                }
                memberSyncRequest.setBenficiary_dtl(Bendata);


                encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(memberSyncRequest)));

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
            Log.d(TAG, "request of PmaygMaster "+encryptedObject.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {

                    progressDialog.dismiss();
                    JSONObject jsonObject = null;    //manish commented



                    String objectResponse="";
                    if(response.has("data")){
                        try {
                            objectResponse=response.getString("data");
                            AppUtils.getInstance().showLog("response encrupt"+objectResponse, LoginFragment.class);
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
                            Cryptography cryptography = new Cryptography();

                            jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Manish comment
                            //if (jsonObject.getString("E200").equalsIgnoreCase("Success"))
                            // AppUtils.getInstance().showLog("responseJSON" + jsonObject.toString(), LoginFragment.class);
                            Log.d(TAG, "responseJSON: "+response.toString());

                            //       AppUtils.getInstance().showLog("responseJSON" +message), LoginFragment.class);


                           /* if (code==200){



                                }*/





                            if(jsonObject.getString("message").equalsIgnoreCase("success"))
                            {

                                //  appDatabase.pmaygInfoDao().updateSyncFlag(beneficiaryId);
                                for (int i=0;i<nrlmBenefeciaryMobileBeans.size();i++)
                                {
                                    String memCode=nrlmBenefeciaryMobileBeans.get(i).getMember_code();
                                    appDatabase.nrlmBenefeciaryMobileDao().setUpdateSyncFlag(memCode);
                                    // appDatabase.pmaygInfoDao().updateSyncFlag(benId);
                                }
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Synced successfully",Toast.LENGTH_LONG).show();
                                locallySavetxt.setText(appDatabase.nrlmBenefeciaryMobileDao().getLocalMobileEntry());
                                AppUtils.getInstance().showLog("Synced", HomeFragment.class);

                            }





                        } catch (Exception e) {
                            //    progressDialog.dismiss();
                            // Log.d(TAG, "notifySuccess: "+e);
                            AppUtils.getInstance().showLog("DecryptEx" + e, LoginFragment.class);
                        }
                    }


                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    progressDialog.dismiss();



                }
            };
            VolleyService  volleyService = VolleyService.getInstance(getApplicationContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("Request of sync", AppUtils.buildURL+"memsyncdata", encryptedObject, mResultCallBack);



        }
        else {
            //   progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"No Internet ",Toast.LENGTH_LONG).show();




        }

    }


}