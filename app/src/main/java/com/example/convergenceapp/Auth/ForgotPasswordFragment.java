package com.example.convergenceapp.Auth;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.convergenceapp.BuildConfig;
import com.example.convergenceapp.R;
import com.example.convergenceapp.request.BenficiaryDtl;
import com.example.convergenceapp.request.MemberSyncRequest;
import com.example.convergenceapp.request.OtpRequest;
import com.example.convergenceapp.response.NrlmDashboardResponse;
import com.example.convergenceapp.response.ResetResponse;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class ForgotPasswordFragment extends Fragment {

    TextInputEditText loginIdEt ;
    Button submitBtn ;
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
        View view =inflater.inflate(R.layout.fragment_forgot_password, container, false);

        loginIdEt = (TextInputEditText) view.findViewById(R.id.et_userMobile);
        submitBtn = (Button) view.findViewById(R.id.submitbtn);
        navController = NavHostFragment.findNavController(this);


        submitBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String otp = AppUtils.getInstance().getRandomOtp();
               PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getRandomOtp(), otp, getContext());
               AppUtils.getInstance().showLog("otp:-"+otp, ForgotPasswordFragment.class);

               String mobileNo = loginIdEt.getText().toString();
               if (mobileNo.equalsIgnoreCase("") || mobileNo.length() < 10){

                   DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!","Please Enter Valid Mobile Number","Ok",true);


               }
               else{
                   PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPREF_KEY_mobileNo(), mobileNo, getContext());

                   otpApi(otp,mobileNo);


               }


           }
       });


        return view;
    }

    public void otpApi(String otp, String mobileno){



        if(NetworkFactory.isInternetOn(getContext()))
        {

            ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();




            /*******make json object is encrypted and *********/
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;

            try {
                Cryptography cryptography = new Cryptography();





                OtpRequest otpRequest=new OtpRequest();

             String plnObj;

                otpRequest.setMobileno(mobileno);
                otpRequest.setMessage(otp);
                 plnObj=new Gson().toJson(otpRequest);
                Log.d(TAG, "request Otp "+plnObj.toString());



               // encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(otpRequest)));
                plnObj=new Gson().toJson(otpRequest);
                 plainData =new JSONObject(plnObj);
                //nonencryptedObject.accumulate("data",new Gson().toJson(otpRequest));

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } //catch (InvalidKeyException e) {
          /*  catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }*/


            Log.d(TAG, "new Otp "+plainData.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {

                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Otp Send",Toast.LENGTH_LONG).show();
                    NavDirections navDirections= ForgotPasswordFragmentDirections.actionForgotPasswordFragment2ToOtpFragment2();
                    navController.navigate(navDirections);


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


                    }

                    try {               //manish commented
                        JSONObject jsonObject1=new JSONObject(objectResponse);
                        objectResponse=jsonObject1.getString("data");
                        //    AppUtils.getInstance().showLog("dashboard"+jsonObject1,LoginFragment.class);



                        // String plnObj=new Gson().toJson(resetResponse);
                       // Log.d(TAG, "responsed: "+status.toString());

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



                                progressDialog.dismiss();





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
                    AppUtils.getInstance().showLog("DecryptEx" + error, LoginFragment.class);
                    Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();




                }
            };
            VolleyService volleyService = VolleyService.getInstance(getContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("Request of sync", "https://nrlm.gov.in/nrlmwebservice/services/convforgot/message",plainData, mResultCallBack);



        }
        else {
            //   progressDialog.dismiss();
            Toast.makeText(getContext(),"No Internet ",Toast.LENGTH_LONG).show();




        }

    }

}