package com.example.convergenceapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.fragment.NavHostFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;
public class LoginActivity extends AppCompatActivity {

    NavController navController;
    Toolbar tollBar;




    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);


        NavHostFragment navHostFragment=(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentlogin);
        navController=navHostFragment.getNavController();
        NavInflater navInflater=navController.getNavInflater();
        NavGraph navGraph=navInflater.inflate(R.navigation.login_nav_graph);

        if(!PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(),LoginActivity.this).equalsIgnoreCase(""))   // which fragment has to be reflect will decide on this variable(set Mpin fragmnet, Verify Mpin fragmet)
        {
            Intent intent=new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            navGraph.setStartDestination(R.id.loginFragment);
        }
        navController.setGraph(navGraph);

    }

}