package com.example.convergenceapp;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.convergenceapp.Auth.HomeFragment;
import com.example.convergenceapp.Auth.HomeFragmentDirections;
import com.example.convergenceapp.database.AppDatabase;
import com.example.convergenceapp.database.dbBean.CheckAndDeleteBean;
import com.example.convergenceapp.database.dbBean.VillageBean;
import com.example.convergenceapp.database.entity.CheckAndDeleteEntity;
import com.example.convergenceapp.request.DeleteUnassignRequest;
import com.example.convergenceapp.request.UnassignRequest;
import com.example.convergenceapp.response.DeleteUnassignResponse;
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

import javax.crypto.NoSuchPaddingException;


public class HomeActivity extends AppCompatActivity {

Toolbar toolbar;
ArrayAdapter<String> mainScheme;
List<String> data;
    AutoCompleteTextView spinnerScheme;
    Button nrlmBtn,pmaygBtn;
    AppDatabase appDatabase;
    String statusCode;
    public VolleyResult mResultCallBack = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        data=new ArrayList<>();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinnerScheme=findViewById(R.id.spinner_scheme);
        nrlmBtn=findViewById(R.id.btn_nrlm_dashboard);
        pmaygBtn=findViewById(R.id.btn_pmayg_dashboard);
        toolbar.setSubtitle("Test Subtitle");
        toolbar.inflateMenu(R.menu.menu);

        NavHostFragment navHostFragment=(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        NavController navController=navHostFragment.getNavController();
        NavInflater navInflater=navController.getNavInflater();
        NavGraph navGraph=navInflater.inflate(R.navigation.home_nav_graph);
        appDatabase=AppDatabase.getDatabase(getApplicationContext());


        nrlmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,NrlmDashboardActivity.class);
                startActivity(i);

            }
        });

        pmaygBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,PmaygDashboardActivity.class);
                startActivity(i);


            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.changeLanguage) {

             //       Toast.makeText(getApplicationContext(),"Whooooooooooooooo working......",Toast.LENGTH_LONG).show();
                    navGraph.setStartDestination(R.id.changeLanguageFragment);
                    navController.setGraph(navGraph);

                    // do something
                }

                else if (item.getItemId() == R.id.unassign) {
                 callUnassignApi();
                }


                else if (item.getItemId() == R.id.logOut) {
                   /* navGraph.setStartDestination(R.id.loginFragment);
                    navController.setGraph(navGraph);*/
                    logout();

                    // do something
                }
                return false;
            }
        });

        getSchemeSpinner();

        spinnerScheme.setOnItemClickListener((adapterView, view1, i, l) -> {
          String selectedScheme=data.get(i);

          if(selectedScheme.equalsIgnoreCase("PMAYG-Convergence"))
          {
              navGraph.setStartDestination(R.id.homeFragment);
             navController.setGraph(navGraph);




          }else {

              navGraph.setStartDestination(R.id.memberFragment);
              navController.setGraph(navGraph);

          }

        });

    }

  private void getSchemeSpinner()
   {
       data.add("PMAYG-Convergence");
       data.add("SHG Member's Mobile & Bank Details");
       mainScheme = new ArrayAdapter<String>(this, R.layout.spinner_text,data );
       spinnerScheme.setAdapter(mainScheme);



       mainScheme.notifyDataSetChanged();

   }
    public void logout(){
       /* appDatabase.pmaygInfoDao().deleteAll();
        appDatabase.nrlmInfoDao().deleteAll();
        appDatabase.loginInfoDao().deleteAll();
        appDatabase.reasonInfoDao().deleteAll();
        appDatabase.bankMasterDao().deleteAll();*/
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
         PreferenceFactory.getInstance().removeSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(),getApplicationContext());
       /*  NavDirections navDirections= HomeFragmentDirections.actionHomeFragmentToLoginFragment2();
        NavController navController=navHostFragment.getNavController();
        navController.navigate(navDirections);*/
    }

    public void callUnassignApi(){


        if(NetworkFactory.isInternetOn(getApplicationContext()))
        {


            ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();



            //  *//**//*******make json object is encrypted and *********//**//*
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                UnassignRequest unassignRequest=new UnassignRequest();
                String loginId= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getApplicationContext());
                String  deviceInfo= AppUtils.getInstance().getDeviceInfo();
                unassignRequest.setUser_id(loginId);
                unassignRequest.setImei_no(imeiNo);
                unassignRequest.setDevice_name(deviceInfo);
                unassignRequest.setLocation_coordinate("1232323");



                String data=new Gson().toJson(unassignRequest);
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
            //   *//**//***********************************************//**//*

            // AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
            Log.d(TAG, "request of NrlmMaster "+plainData.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {
                    progressDialog.dismiss();
                    //*//**//*                 JSONObject jsonObject = null;  //manish comment

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

                            UnassignResponse unassignResponse = UnassignResponse.jsonToJava(response.toString());

                            statusCode = unassignResponse.getData().get(0).getVillage_status();


                            if (statusCode.equalsIgnoreCase("D")) {
                                appDatabase.checkAndDeleteDao().deleteAll();
                                for (int i =0; i<unassignResponse.getData().size();i++) {

                                    String VillageCode = unassignResponse.getData().get(i).getVillage_code();
                                    appDatabase.checkAndDeleteDao().insert(new CheckAndDeleteEntity(VillageCode));
                                }


                                    callDeleteAPI();



                            }
                            else
                            {
                                //     Toast.makeText(getContext(),"Not Found For Unassign",Toast.LENGTH_LONG).show();
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
            VolleyService volleyService = VolleyService.getInstance(getApplicationContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("dashboard Nrlm", AppUtils.buildURL+"unassignvill", plainData, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");
            Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_LONG).show();



        }
    }


    public void callDeleteAPI(){


        if(NetworkFactory.isInternetOn(getApplicationContext()))
        {


            ProgressDialog   progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();



            //   *//**//*******make json object is encrypted and *********//**//*
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                DeleteUnassignRequest deleteUnassignRequest=new DeleteUnassignRequest();
                String loginId= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getApplicationContext());
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

                     Toast.makeText(getApplicationContext(), "village"+VillageCode, Toast.LENGTH_SHORT).show();

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

                                appDatabase.checkAndDeleteDao().deleteAll();
                                DialogFactory.getInstance().showAlert(getApplicationContext(),"Village are Removed Kindly Login next day","Ok");

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
            VolleyService volleyService = VolleyService.getInstance(getApplicationContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("dashboard Nrlm", AppUtils.buildURL+"delunassignvill", plainData, mResultCallBack);



        }
        else {
            Log.d(TAG, "Internet: ");
            //   Toast.makeText(getContext(),"No internet",Toast.LENGTH_LONG).show();



        }
    }
}