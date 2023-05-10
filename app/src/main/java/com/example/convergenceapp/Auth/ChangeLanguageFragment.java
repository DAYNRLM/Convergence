package com.example.convergenceapp.Auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convergenceapp.R;
import com.example.convergenceapp.adapter.LanguageAdapter;
import com.example.convergenceapp.database.entity.LanguageEntity;
import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.LanguageConstant;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;

import java.util.ArrayList;
import java.util.List;

public class ChangeLanguageFragment extends Fragment {
    List<LanguageEntity> languagedata=new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_language, container, false);
        recyclerView=view.findViewById(R.id.changLanguage_Rv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String index = "0";

        String languageId = PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyLanguageId(),getContext());


     //   AppUtils.getInstance().showLog("languageId" + languageId, ChangeLanguageFragment.class);
        /* String languageId = loginInfo.getLanguageId();*/

        for (int i = 0; i < LanguageConstant.language_id.length; i++) {
            if (LanguageConstant.language_id[i].equalsIgnoreCase(languageId)) {
                index = String.valueOf(i);


            }
        }


        LanguageEntity englisgLang = new LanguageEntity();
        englisgLang.setLanguagecode(LanguageConstant.language_code[0]);
        englisgLang.setLocalLanguage(LanguageConstant.local_language[0]);
        englisgLang.setEnglishLanguage(LanguageConstant.language_english[0]);
        englisgLang.setLanguageid(LanguageConstant.language_id[0]);
        languagedata.add(englisgLang);

        if(!index.equalsIgnoreCase("0")) {
            String languageCode = LanguageConstant.language_code[Integer.parseInt(index)];
            String localLanguage = LanguageConstant.local_language[Integer.parseInt(index)];
            String language = LanguageConstant.language_english[Integer.parseInt(index)];
            String lanId = LanguageConstant.language_id[Integer.parseInt(index)];

            LanguageEntity localLangFromDb = new LanguageEntity();
            localLangFromDb.setLanguagecode(languageCode);
            localLangFromDb.setLocalLanguage(localLanguage);
            localLangFromDb.setEnglishLanguage(language);
            localLangFromDb.setLanguageid(lanId);
            languagedata.add(localLangFromDb);
        }


        LanguageAdapter selectLanguageAdaptor = new LanguageAdapter(getContext(), languagedata);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(selectLanguageAdaptor);
        selectLanguageAdaptor.notifyDataSetChanged();
    }
    }

