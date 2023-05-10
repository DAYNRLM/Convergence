package com.example.convergenceapp;

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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.convergenceapp.Auth.HomeFragment;
import com.example.convergenceapp.Auth.HomeFragmentDirections;
import com.example.convergenceapp.database.AppDatabase;
import com.example.convergenceapp.database.dbBean.VillageBean;
import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

Toolbar toolbar;
ArrayAdapter<String> mainScheme;
List<String> data;
    AutoCompleteTextView spinnerScheme;
    Button nrlmBtn,pmaygBtn;
    AppDatabase appDatabase;
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
                } else if (item.getItemId() == R.id.logOut) {
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
}