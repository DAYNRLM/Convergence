package com.example.convergenceapp.Auth;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.convergenceapp.R;

public class DashboardFragmentDirections {
  private DashboardFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionDashboardFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_dashboardFragment_to_homeFragment);
  }
}
