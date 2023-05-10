package com.example.convergenceapp.Mpin;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.convergenceapp.R;

public class SetMpinFragmentDirections {
  private SetMpinFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSetMpinFragmentToVerifyMpinFragment() {
    return new ActionOnlyNavDirections(R.id.action_setMpinFragment_to_verifyMpinFragment);
  }
}
