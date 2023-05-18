package com.example.convergenceapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.convergenceapp.database.AppDatabase;


import com.example.convergenceapp.request.PmaygDashboardRequest;

import com.example.convergenceapp.response.NrlmMasterResponse;
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

import java.security.NoSuchAlgorithmException;

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
       ImageView imageView=findViewById(R.id.backarrowp);
        appDatabase= AppDatabase.getDatabase(getApplicationContext());
        locallySave=appDatabase.memberEntryInfoDao().getLocalBenEntry();

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
            JSONObject plainData=null;
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
         //   *********************************************

            // AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
            Log.d(TAG, "request of NrlmMaster "+plainData.toString());
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


                            PmaygDashboardResponse pmaygDashboardResponse = PmaygDashboardResponse.jsonToJava(response.toString());
                        //    String statusCode= nrlmDashboardResponse.getStatus();







                            totalmemberAllotted= pmaygDashboardResponse.getData().getTot_allot();
                            gpAlotted= pmaygDashboardResponse.getData().getGp_allot();
                            villageAlloted= pmaygDashboardResponse.getData().getVillage_allot();
                            surveyComplete= pmaygDashboardResponse.getData().getCompleted();
                            surveyPending= pmaygDashboardResponse.getData().getPending();
                         /*   Toast.makeText(getApplicationContext(),"total"+totalmemberAllotted ,Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"gp"+gpAlotted ,Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"vil"+villageAlloted ,Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"com"+surveyComplete ,Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"pen"+surveyPending ,Toast.LENGTH_LONG).show();
*/
                            gpAllottxt.setText(gpAlotted);
                            villageAllotttxt.setText(villageAlloted);
                            surveyComtxt.setText(surveyComplete);
                            surveyPentxt.setText(surveyPending);
                            locallySavetxt.setText(locallySave);
                            memAllottxt.setText(totalmemberAllotted);







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
                    locallySavetxt.setText(locallySave);
                    memAllottxt.setText("0");

                }
            };
            VolleyService volleyService = VolleyService.getInstance(getApplicationContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("dashboard Nrlm", "https://nrlm.gov.in/nrlmwebservice/services/convergence/pmaygdashdata", plainData, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");
            Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_LONG).show();

           // progressDialog.dismiss();
            gpAllottxt.setText("0");
            villageAllotttxt.setText("0");
            surveyComtxt.setText("0");
            surveyPentxt.setText("0");
            locallySavetxt.setText(locallySave);
            memAllottxt.setText("0");



        }
    }

}