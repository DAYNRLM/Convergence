package com.example.convergenceapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.convergenceapp.database.AppDatabase;
import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

    public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 2000; // After completion of 2000 ms, the next activity will get started.
    String mPin,count,appLoginDate;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        count=appDatabase.loginInfoDao().getLocalCount();
        getLanguageCode();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkAutoLogout();
                mPin =   PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(), getApplicationContext());
                if (mPin.equalsIgnoreCase("")) {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i); // invoke the SecondActivity.
                    finish(); // the current activity will get finished.

                }
                else{
                    Intent i = new Intent(SplashScreen.this, MpinActivity.class);
                    startActivity(i); // invoke the SecondActivity.
                    finish(); // the current activity will get finished.

                }

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
    private void getLanguageCode() {
        PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyLanguageId(), "1", this);

        String getLanguageCode = PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLanguageCode(), SplashScreen.this);
        if (getLanguageCode.equalsIgnoreCase("")) {
            getLanguageCode = "en";
        }
        AppUtils.getInstance().setLocale(getLanguageCode, getResources(), SplashScreen.this);
    }

    public void checkAutoLogout(){
        // create instance of the SimpleDateFormat that matches the given date
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar appLoginDatecal = Calendar.getInstance();


        if (count.equalsIgnoreCase("0")){

            appLoginDate=sdf.format(appLoginDatecal.getTime());
        }
        else {
            appLoginDate = appDatabase.loginInfoDao().getServerDate();
        }

         appLoginDatecal = Calendar.getInstance();
        try {

           appLoginDatecal.setTime(sdf.parse(appLoginDate));// all done
            Log.d(TAG, appLoginDate+" is the date before adding days and server login date".toString());

           appLoginDatecal.add(Calendar.DAY_OF_MONTH, +3);
              String dateAfterAdding = sdf.format(appLoginDatecal.getTime());

            Log.d(TAG, dateAfterAdding+" is the date after adding days".toString());



        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar cal = Calendar.getInstance();
      //  System.out.println(sdf.format(cal.getTime())+" is the current date");



        if (cal.compareTo(appLoginDatecal) == 0){
            PreferenceFactory.getInstance().removeSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(),getApplicationContext());
           /* Intent i = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(i); // invoke the SecondActivity.
            finish(); // the current activity will get finished.
*/
        }




        //  String dateAfter = sdf.format(cal.getTime());

    }

    }
