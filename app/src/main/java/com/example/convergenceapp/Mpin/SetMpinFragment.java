package com.example.convergenceapp.Mpin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.convergenceapp.R;
import com.example.convergenceapp.databinding.FragmentSetMpinBinding;
import com.example.convergenceapp.utils.DialogFactory;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;

public class SetMpinFragment extends Fragment {

    private FragmentSetMpinBinding binding;
    public NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSetMpinBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        binding.btnSetMpin.setOnClickListener(viewBtn->{
            String mpin=binding.pinviewFirst.getText().toString();
            String verifyMpin=binding.pinviewSecond.getText().toString();
            if(mpin.isEmpty())
            {
                Toast.makeText(getContext(),getContext().getResources().getString(R.string.enter_mpin_first) , Toast.LENGTH_SHORT).show();
            }else if(verifyMpin.isEmpty()){
                Toast.makeText(getContext(),getContext().getResources().getString(R.string.enter_mpin_first) , Toast.LENGTH_SHORT).show();

            }else
            {
                if(mpin.equalsIgnoreCase(verifyMpin))
                {
                    PreferenceFactory.getInstance().saveSharedPrefrecesData(PreferenceKeyManager.getPrefKeyMpin(),verifyMpin,getContext());

                    NavDirections navDirections= SetMpinFragmentDirections.actionSetMpinFragmentToVerifyMpinFragment();
                    navController.navigate(navDirections);
                }else{
                    DialogFactory.getInstance().showAlert(getContext(),getContext().getResources().getString(R.string.wrong_mpin),"Ok");

                }

            }

        });
    }

}