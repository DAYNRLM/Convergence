package com.example.convergenceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;
import com.google.android.material.navigation.NavigationView;

public class MpinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin);
        NavigationView navigation_view;
        DrawerLayout home_drawer_layout;
        NavHostFragment navHostFragment=(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMpin);
        NavController navController=navHostFragment.getNavController();
        NavInflater navInflater=navController.getNavInflater();
        NavGraph navGraph=navInflater.inflate(R.navigation.mpin_nav_graph);
       //Toolbar tollBar = findViewById(R.id.tollBar);
        //navigation_view = findViewById(R.id.navigation_view);
     //   appUtils = AppUtils.getInstance();
     //   setSupportActionBar(tollBar);

        if(!PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(),MpinActivity.this).equalsIgnoreCase(""))   // which fragment has to be reflect will decide on this variable(set Mpin fragmnet, Verify Mpin fragmet)
        { navGraph.setStartDestination(R.id.verifyMpinFragment);
        }else{
            navGraph.setStartDestination(R.id.setMpinFragment);
        }
        navController.setGraph(navGraph);
    }
    }
