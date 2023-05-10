package com.example.convergenceapp;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.convergenceapp.databinding.DialogBankDetailsBindingImpl;
import com.example.convergenceapp.databinding.FragmentChangeLanguageBindingImpl;
import com.example.convergenceapp.databinding.FragmentHomeBindingImpl;
import com.example.convergenceapp.databinding.FragmentMemberBindingImpl;
import com.example.convergenceapp.databinding.FragmentSetMpinBindingImpl;
import com.example.convergenceapp.databinding.FragmentVerifyMpinBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_DIALOGBANKDETAILS = 1;

  private static final int LAYOUT_FRAGMENTCHANGELANGUAGE = 2;

  private static final int LAYOUT_FRAGMENTHOME = 3;

  private static final int LAYOUT_FRAGMENTMEMBER = 4;

  private static final int LAYOUT_FRAGMENTSETMPIN = 5;

  private static final int LAYOUT_FRAGMENTVERIFYMPIN = 6;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(6);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.convergenceapp.R.layout.dialog_bank_details, LAYOUT_DIALOGBANKDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.convergenceapp.R.layout.fragment_change_language, LAYOUT_FRAGMENTCHANGELANGUAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.convergenceapp.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.convergenceapp.R.layout.fragment_member, LAYOUT_FRAGMENTMEMBER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.convergenceapp.R.layout.fragment_set_mpin, LAYOUT_FRAGMENTSETMPIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.convergenceapp.R.layout.fragment_verify_mpin, LAYOUT_FRAGMENTVERIFYMPIN);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_DIALOGBANKDETAILS: {
          if ("layout/dialog_bank_details_0".equals(tag)) {
            return new DialogBankDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_bank_details is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCHANGELANGUAGE: {
          if ("layout/fragment_change_language_0".equals(tag)) {
            return new FragmentChangeLanguageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_change_language is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMEMBER: {
          if ("layout/fragment_member_0".equals(tag)) {
            return new FragmentMemberBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_member is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSETMPIN: {
          if ("layout/fragment_set_mpin_0".equals(tag)) {
            return new FragmentSetMpinBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_set_mpin is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTVERIFYMPIN: {
          if ("layout/fragment_verify_mpin_0".equals(tag)) {
            return new FragmentVerifyMpinBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_verify_mpin is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(6);

    static {
      sKeys.put("layout/dialog_bank_details_0", com.example.convergenceapp.R.layout.dialog_bank_details);
      sKeys.put("layout/fragment_change_language_0", com.example.convergenceapp.R.layout.fragment_change_language);
      sKeys.put("layout/fragment_home_0", com.example.convergenceapp.R.layout.fragment_home);
      sKeys.put("layout/fragment_member_0", com.example.convergenceapp.R.layout.fragment_member);
      sKeys.put("layout/fragment_set_mpin_0", com.example.convergenceapp.R.layout.fragment_set_mpin);
      sKeys.put("layout/fragment_verify_mpin_0", com.example.convergenceapp.R.layout.fragment_verify_mpin);
    }
  }
}
