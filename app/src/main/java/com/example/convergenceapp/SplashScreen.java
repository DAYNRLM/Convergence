package com.example.convergenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 2000; // After completion of 2000 ms, the next activity will get started.
String mPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        getLanguageCode();
        mPin =   PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(), getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
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
    }
