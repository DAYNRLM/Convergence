package com.example.convergenceapp.Auth;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.convergenceapp.R;

public class MemberFragmentDirections {
  private MemberFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMemberFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_memberFragment_to_homeFragment);
  }
}
