package com.example.convergenceapp.Auth;

import static android.content.ContentValues.TAG;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.chaos.view.PinView;
import com.example.convergenceapp.R;
import com.example.convergenceapp.request.ForgotPasswordRequest;
import com.example.convergenceapp.request.OtpRequest;
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

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;


public class OtpFragment extends Fragment {

    PinView otpEdt ;
    EditText useridet,passwordet ;
    Button verifyBtn, submitbtn ;
    public NavController navController;
    LinearLayout passVis,otpvis;
    String userId,password,mobileno,confirmPassword;

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
        passVis = (LinearLayout) view.findViewById(R.id.enterpassvis);
        otpvis = (LinearLayout) view.findViewById(R.id.otpvis);
        useridet = (EditText) view.findViewById(R.id.et_userIdd);
        passwordet = (EditText) view.findViewById(R.id.et_passwordd);
         mobileno = PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPREF_KEY_mobileNo(),getContext());
        navController = NavHostFragment.findNavController(this);


        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enterOtp= otpEdt.getText().toString();
                String mainOtp = PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getRandomOtp(),getContext());

                if (!enterOtp.equalsIgnoreCase(mainOtp)){
                    DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!","Please Enter Valid OTP","Ok",true);

                }
                else {
                    passVis.setVisibility(View.VISIBLE);
                    otpvis.setVisibility(View.GONE);
                    verifyBtn.setVisibility(View.GONE);
                    submitbtn.setVisibility(View.VISIBLE);







                }

            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId= useridet.getText().toString().toUpperCase();
                password = passwordet.getText().toString();


                if (userId.equalsIgnoreCase("")){
                    DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!","Please Enter UserId","Ok",true);


                }
               else if (password.equalsIgnoreCase("")){
                    DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!","Please Enter password","Ok",true);


                }


                else {
                    callResetPasswordAPI(userId,AppUtils.getInstance().getSha256(password),mobileno);

                }

            }
        });








        return view;
    }

    public void callResetPasswordAPI(String loginId,String password,String mobileno){

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





                    ForgotPasswordRequest forgotPasswordRequest=new ForgotPasswordRequest();

                    String plnObj;
                String    imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                    forgotPasswordRequest.setMobileno(mobileno);
                    forgotPasswordRequest.setLogin_id(loginId);
                    forgotPasswordRequest.setPassword(password);
                    forgotPasswordRequest.setImei_no(imeiNo);
                    forgotPasswordRequest.setLocation_coordinate("14534464");
                    forgotPasswordRequest.setDevice_name(AppUtils.getInstance().getDeviceInfo());
                    plnObj=new Gson().toJson(forgotPasswordRequest);
                    Log.d(TAG, "request Otp "+plnObj.toString());



                    // encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(otpRequest)));
                    plnObj=new Gson().toJson(forgotPasswordRequest);
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

                     //  Toast.makeText(getContext(),"reset success",Toast.LENGTH_LONG).show();
                       /* NavDirections navDirections= ForgotPasswordFragmentDirections.actionForgotPasswordFragment2ToOtpFragment2();
                        navController.navigate(navDirections);
*/

                        JSONObject jsonObject = null;    //manish commented



                        String objectResponse="";








                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            try {
                              //  Cryptography cryptography = new Cryptography();

                           //     jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Manish comment


                                Log.d(TAG, "responseJSON: "+response.toString());




                                ResetResponse resetResponse = ResetResponse.jsonToJava(response.toString());
                                String status= resetResponse.getStatus();

                             /*   if (status.equalsIgnoreCase("Updated Successfully!!!")){
                                    NavDirections navDirections= OtpFragmentDirections.actionOtpFragment2ToLoginFragment();
                                    navController.navigate(navDirections);


                                }*/
                                DialogFactory.getInstance().showAlertDialog(getContext(), 1, "Alert!", status.toString(), "ok", new DialogInterface.OnClickListener() {


                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        goToLogin();
                                    }
                                },null,null,true);



                            } catch (Exception e) {

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
                volleyService.postDataVolley("Request of sync", "https://nrlm.gov.in/nrlmwebservice/services/convforgot/resetPassword",plainData, mResultCallBack);



            }
            else {
                //   progressDialog.dismiss();
                Toast.makeText(getContext(),"No Internet ",Toast.LENGTH_LONG).show();






        }

    }

    public void goToLogin(){
        NavDirections navDirections= OtpFragmentDirections.actionOtpFragment2ToLoginFragment();
        navController.navigate(navDirections);
    }
}