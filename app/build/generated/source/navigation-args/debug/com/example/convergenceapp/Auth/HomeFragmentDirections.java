package com.example.convergenceapp.Auth;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.convergenceapp.R;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionHomeFragmentSelf() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_self);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToDashboardFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_dashboardFragment);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToLoginFragment2() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_loginFragment2);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToChangeLanguageFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_changeLanguageFragment);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToNrlmDashboardFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_nrlmDashboardFragment);
  }
}
