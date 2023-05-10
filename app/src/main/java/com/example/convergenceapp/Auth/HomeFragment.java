package com.example.convergenceapp.Auth;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.convergenceapp.Mpin.SetMpinFragmentDirections;
import com.example.convergenceapp.R;
import com.example.convergenceapp.adapter.DataDialogAdapter;
import com.example.convergenceapp.database.AppDatabase;
import com.example.convergenceapp.database.dbBean.BeneficiaryBean;
import com.example.convergenceapp.database.dbBean.GpBean;
import com.example.convergenceapp.database.dbBean.MemberBean;
import com.example.convergenceapp.database.dbBean.NrlmDataBean;
import com.example.convergenceapp.database.dbBean.NrlmVillageBean;
import com.example.convergenceapp.database.dbBean.OtherMembersName;
import com.example.convergenceapp.database.dbBean.ReasonBean;
import com.example.convergenceapp.database.dbBean.ShgBean;
import com.example.convergenceapp.database.dbBean.VillageBean;
import com.example.convergenceapp.database.entity.LoginInfoEntity;
import com.example.convergenceapp.database.entity.MemberEntryInfoEntity;
import com.example.convergenceapp.database.entity.ReasonInfoEntity;
import com.example.convergenceapp.databinding.DialogBankDetailsBinding;
import com.example.convergenceapp.databinding.FragmentHomeBinding;
import com.example.convergenceapp.request.BeneficiaryDetails;
import com.example.convergenceapp.request.LoginRequest;
import com.example.convergenceapp.request.SyncRequest;
import com.example.convergenceapp.utils.AppUtils;
import com.example.convergenceapp.utils.Cryptography;
import com.example.convergenceapp.utils.DialogFactory;
import com.example.convergenceapp.utils.NetworkFactory;
import com.example.convergenceapp.utils.PreferenceFactory;
import com.example.convergenceapp.utils.PreferenceKeyManager;
import com.example.convergenceapp.vollyCall.VolleyResult;
import com.example.convergenceapp.vollyCall.VolleyService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class HomeFragment extends Fragment {
    public VolleyResult mResultCallBack = null;
    List<OtherMembersName> otherMemberData;
    List<String> familyMemberNames;

    int flag=0;

    ArrayAdapter<String> selectionFromAdapter,otherMemberAdapter,selectionFromAdapter1,gpAdapter,nrlmGpAdapter,nrlmVillageAdapter,shgAdapter,memberAdapter,reasonAdapter,wiilingAdapter,villageAdapter,beneficiaryAdapter;
    Toolbar toolbar_home;
    List<String> list,list1,gpName,villageName,beneficiaryName,nrlmGpName,nrlmGpCode,nrlmVillageName,nrlmVillageCode,shgName,shgCode,memberName,memberCode,reasonList;

    String selectedGp,selectedVillage,selectedBeneficiary,selectedWIlling
            ,selectedInShg,selectedBenAvailable,beneficiaryAccNo,beneficiaryId,selectedReason,selectedLgdCode,selectedLgdVillageCode,

    beneficiaryBankName,beneficiaryBranchname,beneficiaryMobileNo, ifscCode,
            selectedNrlmGpCode,selectedNrlmGp,selectedNrlmVillageCode,selectedNrlmVillage,selectedShg,selectedShgCode,
            selectedmemberCode,selectedmember,fatherName,motherName;

    public NavController navController;


    AppDatabase appDatabase;

    List<BeneficiaryBean> beneficiaryBeans;
    List<NrlmVillageBean> nrlmVillageBeans;
    List<ShgBean> shgBeans;
    List<MemberBean> memberBeans;
    List<ReasonBean> reasonBeans;


    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase= AppDatabase.getDatabase(getContext());

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHomeBinding.inflate(inflater);

        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_home);


        list = new ArrayList<String>();
        list.add("Yes");
        list.add("No");

        list1 = new ArrayList<String>();
        list1.add("Yes (NRLM)");
        list1.add("Yes (NON NRLM)");
        list1.add("No");



        //GET GP NAME

        List<GpBean> gpBeans = appDatabase.pmaygInfoDao().getGPList();
        gpName = new ArrayList<String>();

        for (int i=0;i<gpBeans.size();i++){
            gpName.add(gpBeans.get(i).getGpName());

        }
        //GET Reason

        List<ReasonBean> reasonBeans = appDatabase.reasonInfoDao().getReason();
        reasonList = new ArrayList<String>();

        for (int i=0;i<reasonBeans.size();i++){
            reasonList.add(reasonBeans.get(i).getReason());

        }





//GET Nrlm GP NAME

        List<NrlmDataBean> nrlmDataBeans = appDatabase.nrlmInfoDao().getNrlmList();
        nrlmGpName= new ArrayList<String>();
        nrlmGpCode= new ArrayList<String>();


        for (int i=0;i<nrlmDataBeans.size();i++){
            nrlmGpName.add(nrlmDataBeans.get(i).getGpName());
            nrlmGpCode.add(nrlmDataBeans.get(i).getGpCode());


        }


//Gp selection

        gpAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,gpName );
        binding.spinnerGp.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();

        binding.spinnerGp.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedGp= gpName.get(i);
            flag=0;
            selectedLgdCode=appDatabase.pmaygInfoDao().getLgdCode(selectedGp);
            AppUtils.getInstance().showLog("This is LGD code"+selectedGp, HomeFragment.class);


            AppUtils.getInstance().showLog("This is LGD code"+selectedLgdCode, HomeFragment.class);


            //   Toast.makeText(getContext(), selectedGp, Toast.LENGTH_SHORT).show();
            //GET village NAME

            List<VillageBean> villageBeans = appDatabase.pmaygInfoDao().getVillageList(selectedGp);
            villageName = new ArrayList<String>();

            for (int j=0;j<villageBeans.size();j++){
                villageName.add(villageBeans.get(j).getVillageName());
                // Toast.makeText(getContext(), villageBeans.get(j).getVillageName(), Toast.LENGTH_SHORT).show();

            }


            villageAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,villageName );
            binding.spinnerVillage.setAdapter(villageAdapter);
            villageAdapter.notifyDataSetChanged();



            //  ----Set Visibility-----------
            binding.villageVis.setVisibility(View.VISIBLE);
            binding.fathervis.setVisibility(View.GONE);
            binding.motherNamevis.setVisibility(View.GONE);
            binding.benvis.setVisibility(View.GONE);
            binding.bankDetails.setVisibility(View.GONE);
            binding.mobVis.setVisibility(View.GONE);
            binding.benAvaiVisi.setVisibility(View.GONE);
            binding.anyFamilyVisib.setVisibility(View.GONE);
            binding.lisOfFamilyMember.setVisibility(View.GONE);
            binding.nrlmVillVissib.setVisibility(View.GONE);
            binding.ngpVisib.setVisibility(View.GONE);
            binding.shgVisi.setVisibility(View.GONE);
            binding.memberVisi.setVisibility(View.GONE);
            binding.btnSave.setVisibility(View.GONE);
            binding.willingVisib.setVisibility(View.GONE);
            binding.reasonVisi.setVisibility(View.GONE);
            binding.memberOuterLay.setVisibility(View.GONE);





            //-----Seleted data set blank----
            binding.spinnerVillage.setText("");
            binding.spinnerBeneficiary.setText("");
            binding.spinnerBenAvail.setText("");
            binding.spinnerNrlmVillage.setText("");
            binding.spinnerNrlmGp.setText("");
            binding.spinnerInShg.setText("");
            binding.spinnerMemberName.setText("");
            binding.spinnerWilling.setText("");
            binding.spinnerReason.setText("");
            binding.SpinnerFamily.setText("");





            selectedVillage= "";selectedBeneficiary= "";selectedWIlling= "";
            selectedInShg= "";selectedBenAvailable= "";beneficiaryAccNo= "";beneficiaryId= "";selectedReason= "";
            beneficiaryBankName= "";beneficiaryBranchname= "";beneficiaryMobileNo= ""; ifscCode="";
            selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
            selectedShgCode= "";selectedmemberCode= "";selectedmember= "";beneficiaryMobileNo= "";



            gpAdapter.notifyDataSetChanged();

        });


        //Village selection



        //GET village NAME




        binding.spinnerVillage.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedVillage= villageName.get(i);
            flag=0;
            selectedLgdVillageCode=appDatabase.pmaygInfoDao().getViilageLgdCode(selectedVillage);

            AppUtils.getInstance().showLog("VillageCode"+selectedLgdVillageCode, HomeFragment.class);
            //GET beneficiary NAME

            beneficiaryBeans = appDatabase.pmaygInfoDao().getBenDetailsList(selectedVillage,"0");
            beneficiaryName = new ArrayList<String>();


            for (int j=0;j<beneficiaryBeans.size();j++){
                beneficiaryName.add(beneficiaryBeans.get(j).getBenName());
                //  Toast.makeText(getContext(), beneficiaryBeans.get(i).getBenName(), Toast.LENGTH_SHORT).show();

            }

            // set Visibility
            binding.benvis.setVisibility(View.VISIBLE);
            binding.bankDetails.setVisibility(View.GONE);
            binding.mobVis.setVisibility(View.GONE);
            binding.benAvaiVisi.setVisibility(View.GONE);
            binding.anyFamilyVisib.setVisibility(View.GONE);
            binding.nrlmVillVissib.setVisibility(View.GONE);
            binding.ngpVisib.setVisibility(View.GONE);
            binding.shgVisi.setVisibility(View.GONE);
            binding.fathervis.setVisibility(View.GONE);
            binding.motherNamevis.setVisibility(View.GONE);
            binding.memberVisi.setVisibility(View.GONE);
            binding.btnSave.setVisibility(View.GONE);
            binding.willingVisib.setVisibility(View.GONE);
            binding.reasonVisi.setVisibility(View.GONE);
            binding.memberOuterLay.setVisibility(View.GONE);

            //-----Seleted data set blank----


            binding.spinnerBeneficiary.setText("");
            binding.spinnerBenAvail.setText("");
            binding.spinnerNrlmVillage.setText("");
            binding.spinnerNrlmGp.setText("");
            binding.spinnerInShg.setText("");
            binding.spinnerMemberName.setText("");
            binding.spinnerWilling.setText("");
            binding.spinnerReason.setText("");


            selectedBeneficiary= "";selectedWIlling= "";
            selectedInShg= "";selectedBenAvailable= "";beneficiaryAccNo= "";beneficiaryId= "";selectedReason= "";
            beneficiaryBankName= "";beneficiaryBranchname= "";beneficiaryMobileNo= ""; ifscCode="";
            selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
            selectedShgCode= "";selectedmemberCode= "";selectedmember= "";beneficiaryMobileNo= "";



            beneficiaryAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,beneficiaryName );
            binding.spinnerBeneficiary.setAdapter(beneficiaryAdapter);
            beneficiaryAdapter.notifyDataSetChanged();

            villageAdapter.notifyDataSetChanged();


        });




        //Beneficiary selection




        binding.spinnerBeneficiary.setOnItemClickListener((adapterView, view1, i, l) -> {

            flag=0;
            selectedBeneficiary= beneficiaryName.get(i);
            beneficiaryId= beneficiaryBeans.get(i).getBenId();
            beneficiaryAccNo= beneficiaryBeans.get(i).getBenAccNo();
            beneficiaryBankName= beneficiaryBeans.get(i).getBenBankName();
            beneficiaryBranchname= beneficiaryBeans.get(i).getBenBranchName();
            beneficiaryMobileNo= beneficiaryBeans.get(i).getMobileNumber();
            ifscCode=beneficiaryBeans.get(i).getIfsc_code();
            fatherName= beneficiaryBeans.get(i).getFatherName();
            motherName= beneficiaryBeans.get(i).getMotherName();

            otherMemberData=appDatabase.pmaygInfoDao().getMemberData(beneficiaryId); //uper se niche le aana
            familyMemberNames=new ArrayList<>();
            for(int j=0;j<otherMemberData.size();j++)
            {
                familyMemberNames.add(otherMemberData.get(j).getMembersName());
            }
            familyMemberNames.add("NOT IN THE LIST");



            if(!beneficiaryMobileNo.equalsIgnoreCase("NA"))
            {
                binding.edtMobile.setText(beneficiaryMobileNo);
                binding.edtMobile.setFocusable(false);
            }

            binding.fatherName.setText(fatherName);

            binding.motherName.setText(motherName);

            binding.benIdShowVis.setVisibility(View.VISIBLE);
            binding.benidShow.setText(beneficiaryId);




            // set Visibility
            binding.benAvaiVisi.setVisibility(View.VISIBLE);
            binding.mobVis.setVisibility(View.VISIBLE);
            binding.bankDetails.setVisibility(View.VISIBLE);
            binding.anyFamilyVisib.setVisibility(View.GONE);
            binding.nrlmVillVissib.setVisibility(View.GONE);
            binding.ngpVisib.setVisibility(View.GONE);
            binding.shgVisi.setVisibility(View.GONE);
            binding.fathervis.setVisibility(View.VISIBLE);
            binding.motherNamevis.setVisibility(View.VISIBLE);
            binding.memberVisi.setVisibility(View.GONE);
            binding.btnSave.setVisibility(View.GONE);
            binding.willingVisib.setVisibility(View.GONE);
            binding.reasonVisi.setVisibility(View.GONE);
            binding.memberOuterLay.setVisibility(View.GONE);



            binding.spinnerBenAvail.setText("");
            binding.spinnerNrlmVillage.setText("");
            binding.spinnerNrlmGp.setText("");
            binding.spinnerInShg.setText("");
            binding.spinnerMemberName.setText("");
            binding.spinnerWilling.setText("");
            binding.spinnerReason.setText("");

            //-----Selected data set blank----

            selectedWIlling= "";
            selectedInShg= "";selectedBenAvailable= "";selectedReason= "";

            selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
            selectedShgCode= "";selectedmemberCode= "";selectedmember= "";




        });



//yes / No choice
        selectionFromAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,list );
        binding.spinnerBenAvail.setAdapter(selectionFromAdapter);
        selectionFromAdapter.notifyDataSetChanged();


//yes / No choice

        selectionFromAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,list1 );
        binding.spinnerInShg.setAdapter(selectionFromAdapter1);
        selectionFromAdapter1.notifyDataSetChanged();


        // selection  Beneficiary Availability


        binding.spinnerBenAvail.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedBenAvailable= list.get(i);
            flag=0;
            if (selectedBenAvailable.equalsIgnoreCase("Yes")) {


                // set Visibility
                binding.anyFamilyVisib.setVisibility(View.VISIBLE);
                binding.nrlmVillVissib.setVisibility(View.GONE);
                binding.ngpVisib.setVisibility(View.GONE);
                binding.shgVisi.setVisibility(View.GONE);
                binding.memberVisi.setVisibility(View.GONE);
                binding.btnSave.setVisibility(View.GONE);
                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);


                //-----Seleted data set blank----

                selectedWIlling= "";
                selectedInShg= "";selectedReason= "";
                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";


                binding.spinnerNrlmVillage.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerInShg.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");


            }
            else  if (selectedBenAvailable.equalsIgnoreCase("No")) {{

                binding.btnSave.setVisibility(View.VISIBLE);
                flag=0;

                // set Visibility
                binding.anyFamilyVisib.setVisibility(View.GONE);
                binding.nrlmVillVissib.setVisibility(View.GONE);
                binding.ngpVisib.setVisibility(View.GONE);
                binding.shgVisi.setVisibility(View.GONE);
                binding.memberVisi.setVisibility(View.GONE);
                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);
                binding.lisOfFamilyMember.setVisibility(View.GONE);
                binding.memberOuterLay.setVisibility(View.GONE);

                //-----Seleted data set blank----

                selectedWIlling= "";
                selectedInShg= "";;selectedReason= "";
                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";

                binding.spinnerNrlmVillage.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerInShg.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");

            }}
        });

        // select in SHG

        binding.spinnerInShg.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedInShg= list1.get(i);
            if (selectedInShg.equalsIgnoreCase("Yes (NRLM)")) {

                flag=0;
                // set Visibility

                binding.nrlmVillVissib.setVisibility(View.VISIBLE);
                binding.ngpVisib.setVisibility(View.VISIBLE);
                binding.shgVisi.setVisibility(View.VISIBLE);
                binding.memberVisi.setVisibility(View.VISIBLE);
                binding.btnSave.setVisibility(View.GONE);
                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);
                binding.lisOfFamilyMember.setVisibility(View.GONE);

                binding.memberOuterLay.setVisibility(View.GONE);


                //-----Seleted data set blank----

                selectedWIlling= "";
                selectedReason= "";

                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";

                binding.spinnerNrlmVillage.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");
                binding.spinnerShgName.setText("");

            }
            else if (selectedInShg.equalsIgnoreCase("Yes (NON NRLM)")){

                binding.SpinnerFamily.setText("");
                flag=0;

                // set Visibility
                binding.nrlmVillVissib.setVisibility(View.GONE);
                binding.ngpVisib.setVisibility(View.GONE);
                binding.shgVisi.setVisibility(View.GONE);
                binding.memberVisi.setVisibility(View.GONE);

                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);
                binding.lisOfFamilyMember.setVisibility(View.VISIBLE);

                //-----Seleted data set blank----

                selectedWIlling= "";selectedReason= "";
                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";

                otherMemberAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,familyMemberNames);
                binding.SpinnerFamily.setAdapter(otherMemberAdapter);
                otherMemberAdapter.notifyDataSetChanged();


                binding.spinnerNrlmVillage.setText("");
                binding.spinnerShgName.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");

            }
            else if (selectedInShg.equalsIgnoreCase("No")){

                flag=0;

                // set Visibility

                binding.nrlmVillVissib.setVisibility(View.GONE);
                binding.ngpVisib.setVisibility(View.GONE);
                binding.shgVisi.setVisibility(View.GONE);
                binding.memberVisi.setVisibility(View.GONE);
                binding.btnSave.setVisibility(View.GONE);
                binding.willingVisib.setVisibility(View.VISIBLE);
                binding.reasonVisi.setVisibility(View.GONE);
                binding.lisOfFamilyMember.setVisibility(View.GONE);
                binding.memberOuterLay.setVisibility(View.GONE);


                //-----Seleted data set blank----

                selectedWIlling= "";selectedReason= "";
                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";

                binding.spinnerNrlmVillage.setText("");
                binding.spinnerShgName.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");




            }

        });


        binding.SpinnerFamily.setOnItemClickListener((adapterView, view1, i, l) ->{

            String memberCheck=familyMemberNames.get(i);
            if(memberCheck.equalsIgnoreCase("NOT IN THE LIST"))
            {
                flag=1;
                binding.memberOuterLay.setVisibility(View.VISIBLE);

                binding.btnSave.setVisibility(View.VISIBLE);

            }else {
                flag=0;

                selectedmemberCode=familyMemberNames.get(i);
                binding.memberOuterLay.setVisibility(View.GONE);
                binding.btnSave.setVisibility(View.VISIBLE);

            }

        });





        nrlmGpAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,nrlmGpName );
        binding.spinnerNrlmGp.setAdapter(nrlmGpAdapter);
        nrlmGpAdapter.notifyDataSetChanged();


        binding.spinnerNrlmGp.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedNrlmGp= nrlmGpName.get(i);
            selectedNrlmGpCode= nrlmGpCode.get(i);

            nrlmVillageBeans = appDatabase.nrlmInfoDao().getNrlmVillage(selectedNrlmGpCode);
            nrlmVillageName = new ArrayList<String>();


            for (int j=0;j<nrlmVillageBeans.size();j++){
                nrlmVillageName.add(nrlmVillageBeans.get(j).getNrlmVillageName());
                //  Toast.makeText(getContext(), beneficiaryBeans.get(i).getBenName(), Toast.LENGTH_SHORT).show();

            }
            nrlmVillageAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,nrlmVillageName );
            binding.spinnerNrlmVillage.setAdapter(nrlmVillageAdapter);
            nrlmVillageAdapter.notifyDataSetChanged();

            if (!selectedNrlmGp.equalsIgnoreCase("")) {

                // set Visibility

                binding.nrlmVillVissib.setVisibility(View.VISIBLE);
                binding.shgVisi.setVisibility(View.VISIBLE);
                binding.memberVisi.setVisibility(View.VISIBLE);
                binding.btnSave.setVisibility(View.GONE);
                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);

                //-----Seleted data set blank----

                selectedWIlling= "";
                selectedReason= "";

                selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";

                binding.spinnerNrlmVillage.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerShgName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");

            }

        });


        //   selectNrlmVillage



        binding.spinnerNrlmVillage.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedNrlmVillage=   nrlmVillageName.get(i);
            //selectedNrlmVillageCode= nrlmVillageCode.get(i);


            shgBeans = appDatabase.nrlmInfoDao().getShg(selectedNrlmVillage);
            shgName = new ArrayList<String>();
            shgCode = new ArrayList<String>();

            for (int j=0;j<shgBeans.size();j++){
                shgName.add(shgBeans.get(j).getShgName());
                shgCode.add(shgBeans.get(j).getShgCode());
                //  Toast.makeText(getContext(),shgBeans.get(j).getShgName(), Toast.LENGTH_SHORT).show();

            }


            shgAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,shgName );
            binding.spinnerShgName.setAdapter(shgAdapter);
            shgAdapter.notifyDataSetChanged();
            if (!selectedNrlmVillage.equalsIgnoreCase("")) {


                // set Visibility

                binding.shgVisi.setVisibility(View.VISIBLE);
                binding.memberVisi.setVisibility(View.VISIBLE);
                binding.btnSave.setVisibility(View.GONE);
                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);

                //-----Seleted data set blank----

                selectedWIlling= "";
                selectedReason= "";

                selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";


                binding.spinnerMemberName.setText("");
                binding.spinnerShgName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");
            }

        });


        ///   select Nrlm SHG



        binding.spinnerShgName.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedShg= shgName.get(i);
            selectedShgCode= shgCode.get(i);
            // Toast.makeText(getContext(),selectedShgCode, Toast.LENGTH_SHORT).show();

            memberBeans = appDatabase.nrlmInfoDao().getMember(selectedShgCode);
            // Toast.makeText(getContext(),memberBeans.get(i).getMemberName(), Toast.LENGTH_SHORT).show();
            memberName = new ArrayList<String>();
            memberCode = new ArrayList<String>();
            for (int j=0;j<memberBeans.size();j++){
                memberName.add(memberBeans.get(j).getMemberName());
                memberCode.add(memberBeans.get(j).getMemberCode());
                //   Toast.makeText(getContext(),shgBeans.get(j).getShgName(), Toast.LENGTH_SHORT).show();

            }

            memberAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,memberName );
            binding.spinnerMemberName.setAdapter(memberAdapter);
            memberAdapter.notifyDataSetChanged();
            if (!selectedShg.equalsIgnoreCase("")) {



                // set Visibility

                binding.memberVisi.setVisibility(View.VISIBLE);
                binding.btnSave.setVisibility(View.GONE);
                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);

                //-----Seleted data set blank----

                selectedWIlling= "";
                selectedReason= "";

                selectedmemberCode= "";
                selectedmember= "";
                binding.spinnerMemberName.setText("");
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");

            }

        });
        //  select member

        binding.spinnerMemberName.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedmember= memberName.get(i);
            selectedmemberCode= memberCode.get(i);
            if (!selectedmember.equalsIgnoreCase("")) {


                // set Visibility

                binding.memberVisi.setVisibility(View.VISIBLE);
                binding.btnSave.setVisibility(View.VISIBLE);
                binding.willingVisib.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);

                //-----Seleted data set blank----

                selectedWIlling= "";
                selectedReason= "";
                binding.spinnerWilling.setText("");
                binding.spinnerReason.setText("");


            }

        });



        // selection of willing


        wiilingAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,list );
        binding.spinnerWilling.setAdapter(wiilingAdapter);
        wiilingAdapter.notifyDataSetChanged();
        binding.spinnerWilling.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedWIlling= list.get(i);
            if (selectedWIlling.equalsIgnoreCase("Yes")) {

                //set visibility
                binding.btnSave.setVisibility(View.VISIBLE);
                binding.nrlmVillVissib.setVisibility(View.GONE);
                binding.ngpVisib.setVisibility(View.GONE);
                binding.shgVisi.setVisibility(View.GONE);
                binding.memberVisi.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.GONE);

                //-----Seleted data set blank----

                selectedReason= "";

                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";

                binding.spinnerNrlmVillage.setText("");
                binding.spinnerShgName.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerReason.setText("");



            }
            else if(selectedWIlling.equalsIgnoreCase("No")) {

                //set visibility
                binding.nrlmVillVissib.setVisibility(View.GONE);
                binding.ngpVisib.setVisibility(View.GONE);
                binding.shgVisi.setVisibility(View.GONE);
                binding.btnSave.setVisibility(View.GONE);
                binding.reasonVisi.setVisibility(View.VISIBLE);
                binding.memberVisi.setVisibility(View.GONE);

                //-----Seleted data set blank----

                selectedReason= "";
                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";

                binding.spinnerNrlmVillage.setText("");
                binding.spinnerShgName.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerMemberName.setText("");
                binding.spinnerReason.setText("");


            }
        });

        ///   Reason


        reasonAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,reasonList );
        binding.spinnerReason.setAdapter(reasonAdapter);
        reasonAdapter.notifyDataSetChanged();
        binding.spinnerReason.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedReason= reasonList.get(i);
            if (!selectedReason.equalsIgnoreCase("")) {


                //set visibility
                binding.nrlmVillVissib.setVisibility(View.GONE);
                binding.ngpVisib.setVisibility(View.GONE);
                binding.shgVisi.setVisibility(View.GONE);
                binding.btnSave.setVisibility(View.VISIBLE);
                binding.memberVisi.setVisibility(View.GONE);

                //-----Seleted data set blank----


                selectedNrlmGpCode= "";selectedNrlmGp= "";selectedNrlmVillageCode= "";selectedNrlmVillage= "";selectedShg= "";
                selectedShgCode= "";selectedmemberCode= "";selectedmember= "";



                binding.spinnerNrlmVillage.setText("");
                binding.spinnerShgName.setText("");
                binding.spinnerNrlmGp.setText("");
                binding.spinnerMemberName.setText("");

            }

        });






        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag==1) {
                    selectedmemberCode = binding.memberNameET.getText().toString();
                    AppUtils.getInstance().showLog("Edit text Member Name  "+selectedmember,HomeFragment.class);
                    if (selectedmemberCode.equalsIgnoreCase("")) {
                        DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!","Member name can't be empty...","Ok",true);
                        return;

                    }

                }
              /*  Toast.makeText(getContext(),selectedGp+selectedVillage+selectedBeneficiary+selectedWIlling
                        +selectedInShg+selectedBenAvailable+beneficiaryAccNo+beneficiaryId+selectedReason+
                        beneficiaryBankName+beneficiaryBranchname+beneficiaryMobileNo+ifscCode+
                        selectedNrlmGpCode+selectedNrlmGp+selectedNrlmVillageCode+selectedNrlmVillage+selectedShg+selectedShgCode+
                        selectedmemberCode+selectedmember,Toast.LENGTH_LONG).show();*/
                AppUtils.getInstance().showLog("SelecltedGp "+selectedGp, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedVillage "+selectedVillage, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedBeneficiary "+selectedBeneficiary, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedWIlling "+selectedWIlling, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedBenAvailable "+selectedBenAvailable, HomeFragment.class);
                AppUtils.getInstance().showLog("beneficiaryAccNo "+beneficiaryAccNo, HomeFragment.class);
                AppUtils.getInstance().showLog("beneficiaryId "+beneficiaryId, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedReason "+selectedReason, HomeFragment.class);
                AppUtils.getInstance().showLog("beneficiaryBankName "+beneficiaryBankName, HomeFragment.class);
                AppUtils.getInstance().showLog("beneficiaryBranchname "+beneficiaryBranchname, HomeFragment.class);
                AppUtils.getInstance().showLog("beneficiaryMobileNo "+beneficiaryMobileNo, HomeFragment.class);
                AppUtils.getInstance().showLog("ifscCode "+ifscCode, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedNrlmGpCode "+selectedNrlmGpCode, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedNrlmVillageCode "+selectedNrlmVillageCode, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedShg "+selectedShg, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedShgCode "+selectedShgCode, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedNrlmGp "+selectedNrlmGp, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedmemberCode "+selectedmemberCode, HomeFragment.class);
                AppUtils.getInstance().showLog("selectedmember "+selectedmember, HomeFragment.class);

                /*this.gp_Name = gp_Name;
                this.scheme_Name = scheme_Name;
                this.ben_Id = ben_Id;
                this.lgd_GpCode = lgd_GpCode;
                this.lgd_Villagecode = lgd_Villagecode;
                this.mobile_no = mobile_no;
                this.ben_availability = ben_availability;
                this.any_Familyinshg = any_Familyinshg;
                this.willing_joinshg = willing_joinshg;
                this.reason = reason;
                this.shg_Code = shg_Code;
                this.member_Code = member_Code;
                this.village_Code = village_Code;
                this.created_on = created_on;*/

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    appDatabase.memberEntryInfoDao().insert(new MemberEntryInfoEntity(selectedGp,"PMAYG",beneficiaryId,selectedLgdCode,selectedLgdVillageCode,beneficiaryMobileNo,selectedBenAvailable,selectedInShg,selectedWIlling,selectedReason,selectedShgCode,selectedmemberCode,selectedNrlmVillageCode,AppUtils.getInstance().getCurrentDateAndTime(),AppUtils.appVersion,"0"));
                }
                List<MemberEntryInfoEntity>  membersyncdata=appDatabase.memberEntryInfoDao().getSyncData("0");


                //appDatabase.memberEntryInfoDao().insert();

                if(NetworkFactory.isInternetOn(getContext())) {
                    String userid=PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getContext());
                    String imei= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefImeiNo(),getContext());
                    String deviceInfo= AppUtils.getInstance().getDeviceInfo();

                    syncAPI(userid,imei ,deviceInfo, "1232323", membersyncdata);

                }else {
                    //DialogFactory.getInstance().showAlertDialog(getContext(),1,"Alert!","Data saved Offline...","Ok",true);
                    DialogFactory.getInstance().showAlertDialog(getContext(), 1, "Alert!", "Data saved offlinee...", "ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            refresh();
                        }
                    },null,null,true);

                }
            }
        });







        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.llBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showBankDialog();


            }
        });

        binding.othermember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOtherMemberDialog();
            }
        });




    }
    public  void SyncApi(String userid,String imei, String device, String location, String scheme, String reg, String lgdgp, String lgdvill, String mobile, String beniavai,String familymem,String join, String rsn,String shgcd,String shgmemcod, String entity, String appvr,String cretedon ){
        BeneficiaryDetails beneficiaryDetails=new BeneficiaryDetails();

        beneficiaryDetails.setScheme_name(scheme);
        beneficiaryDetails.setReg_no(reg);
        beneficiaryDetails.setLgd_gp_cd(lgdgp);
        beneficiaryDetails.setLgd_vill_cd(lgdvill);
        beneficiaryDetails.setMobile_no(mobile);
        beneficiaryDetails.setBenif_avail(beniavai);
        beneficiaryDetails.setFamily_mem_shg(familymem);
        beneficiaryDetails.setJoin_shg(join);
        beneficiaryDetails.setReason(rsn);
        beneficiaryDetails.setShg_code(shgcd);
        beneficiaryDetails.setShg_member_code(shgmemcod);
        beneficiaryDetails.setEntity_code(entity);
        beneficiaryDetails.setApp_ver(appvr);
        beneficiaryDetails.setCreated_on_app(cretedon);
        ArrayList<BeneficiaryDetails> Bendata = new ArrayList<>();
        Bendata.add(beneficiaryDetails);


        SyncRequest syncRequest =new SyncRequest();
        syncRequest.setUser_id(userid);
        syncRequest.setImei_no(imei);
        syncRequest.setDevice_name(device);
        syncRequest.setLocation_coordinate(location);
        syncRequest.setBenficiary_dtl(Bendata);
        String data = new Gson().toJson(syncRequest);
        AppUtils.getInstance().showLog("Actual Data-----"+data, HomeFragment.class);

    }
    public void syncAPI(String userid,String imei, String device, String location,List<MemberEntryInfoEntity> memberSyncData )
    {
        if(NetworkFactory.isInternetOn(getContext()))
        {

            ProgressDialog   progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            List<String> benIdList=new ArrayList<>();

            //*******make json object is encrypted and *********//*
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;
            try {
                Cryptography cryptography = new Cryptography();



                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                ArrayList<BeneficiaryDetails> Bendata = new ArrayList<>();


                for(int i=0;i<memberSyncData.size();i++) {
                    BeneficiaryDetails beneficiaryDetails=new BeneficiaryDetails();


                    beneficiaryDetails.setScheme_name(memberSyncData.get(i).getScheme_Name());
                    beneficiaryDetails.setReg_no(memberSyncData.get(i).getBen_Id());
                    beneficiaryDetails.setLgd_gp_cd(memberSyncData.get(i).getLgd_GpCode());
                    beneficiaryDetails.setLgd_vill_cd(memberSyncData.get(i).getLgd_Villagecode());
                    beneficiaryDetails.setMobile_no(memberSyncData.get(i).getMobile_no());
                    beneficiaryDetails.setBenif_avail(memberSyncData.get(i).getBen_availability());
                    beneficiaryDetails.setFamily_mem_shg(memberSyncData.get(i).getAny_Familyinshg());
                    beneficiaryDetails.setJoin_shg(memberSyncData.get(i).getWilling_joinshg());
                    beneficiaryDetails.setReason(memberSyncData.get(i).getReason());
                    beneficiaryDetails.setShg_code(memberSyncData.get(i).getShg_Code());
                    beneficiaryDetails.setShg_member_code(memberSyncData.get(i).getMember_Code());
                    beneficiaryDetails.setEntity_code(memberSyncData.get(i).getVillage_Code());
                    beneficiaryDetails.setApp_ver(memberSyncData.get(i).getAppVersion());
                    beneficiaryDetails.setCreated_on_app(memberSyncData.get(i).getCreated_on());
                    benIdList.add(memberSyncData.get(i).getBen_Id());
                    Bendata.add(beneficiaryDetails);
                }


                SyncRequest syncRequest = new SyncRequest();
                syncRequest.setUser_id(userid);
                syncRequest.setImei_no(imeiNo);
                syncRequest.setDevice_name(device);
                syncRequest.setLocation_coordinate(location);
                syncRequest.setBenficiary_dtl(Bendata);




                // String loginId=PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefKeyLoginid(),getCurrentContext());






                String data=new Gson().toJson(syncRequest);
                plainData=new JSONObject(data);
                AppUtils.getInstance().showLog("Sync Data"+plainData, HomeFragment.class);
                //encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(nrlmMasterRequest)));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } //catch (InvalidKeyException e) {
            // e.printStackTrace();
            // } catch (InvalidAlgorithmParameterException e) {
            //  e.printStackTrace();
            //} catch (IllegalBlockSizeException e) {
            // e.printStackTrace();
            // } catch (BadPaddingException e) {
            //e.printStackTrace();
            // } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            //}
            //***********************************************//*

            // AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
            Log.d(TAG, "request of Sync "+plainData.toString());
            NavController  navController = NavHostFragment.findNavController(this);
            NavDirections navDirections=HomeFragmentDirections.actionHomeFragmentSelf();


            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {


                    try {
                        if(response.getString("message").equalsIgnoreCase("success"))
                        {

                            //  appDatabase.pmaygInfoDao().updateSyncFlag(beneficiaryId);
                            for (int i=0;i<benIdList.size();i++)
                            {
                                String benId=benIdList.get(i);
                                appDatabase.memberEntryInfoDao().setUpdateSyncFlag(benId);
                                appDatabase.pmaygInfoDao().updateSyncFlag(benId);
                            }
                            progressDialog.dismiss();
                            Toast.makeText(getContext(),"Synced successfully",Toast.LENGTH_LONG).show();
                            AppUtils.getInstance().showLog("Synced", HomeFragment.class);
                            navController.navigate(navDirections);

                        }


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            try {
                                Cryptography cryptography = new Cryptography();
                                //jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Manish comment
                                //if (jsonObject.getString("E200").equalsIgnoreCase("Success"))
                                // AppUtils.getInstance().showLog("responseJSON" + jsonObject.toString(), LoginFragment.class);
                                JSONObject viewData=response;
                                Log.d(TAG, "responseJSON: "+viewData.toString());




                                // nrlmMasterResponse.getData();




                            } catch (Exception e) {
                                //progressDialog.dismiss();
                                Log.d(TAG, "notifySuccess: "+e);
                                //AppUtils.getInstance().showLog("DecryptEx" + e, LoginFragment.class);
                            }

                            // progressDialog.dismiss();
                            // intentToMpin();
                            //callNrlmMasterAPI();
                            //  callPmaygMasterAPI();
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

//progressDialog.dismiss();
                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    //      progressDialog.dismiss();

                }
            };
            VolleyService volleyService = VolleyService.getInstance(getContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("Request of sync", "https://nrlm.gov.in/nrlmwebservice/services/convergence/syncdata", plainData, mResultCallBack);



        }else {
            //progressDialog.dismiss();
            //Log.d(TAG, "Internet: ");*//*

        }
    }
    private void showOtherMemberDialog()
    {
        Dialog dialog = new Dialog(getContext());
        //   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor()));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setContentView(R.layout.dilog_layout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        dialog.show();


        RecyclerView rvTest = (RecyclerView) dialog.findViewById(R.id.dialog_rv);
        rvTest.setHasFixedSize(true);
        rvTest.setLayoutManager(new LinearLayoutManager(getContext()));
        //rvTest.addItemDecoration(new SimpleDividerItemDecoration(context, R.drawable.divider));
        DataDialogAdapter rvAdapter = new DataDialogAdapter(getContext(), otherMemberData);
        rvTest.setAdapter(rvAdapter);
    }
    private void showBankDialog(){
        final Dialog dialog = new Dialog(getContext());
        DialogBankDetailsBinding bankDialogBinding=DialogBankDetailsBinding.inflate(getLayoutInflater());
        dialog.setContentView(bankDialogBinding.getRoot());
        bankDialogBinding.tvAcc.setText("Account Number : "+beneficiaryAccNo);
        bankDialogBinding.tvBankName.setText("Bank Name : "+beneficiaryBankName);
        bankDialogBinding.tvBranchName.setText("Branch Name : "+beneficiaryBranchname);
        bankDialogBinding.ifsc.setText("IFSC Code : "+ifscCode); //manish comment
        bankDialogBinding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public void exit(){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    /*  public void logout(){
          navController = NavHostFragment.findNavController(this);
          appDatabase.pmaygInfoDao().deleteAll();
          appDatabase.nrlmInfoDao().deleteAll();
          appDatabase.loginInfoDao().deleteAll();
          appDatabase.reasonInfoDao().deleteAll();
          PreferenceFactory.getInstance().removeSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getContext());
        //  PreferenceFactory.getInstance().removeSharedPrefrencesData(PreferenceKeyManager.getPrefKeyMpin(),getContext());

          NavDirections navDirections= HomeFragmentDirections.actionHomeFragmentToLoginFragment();
          navController.navigate(navDirections);
      }

      public void changeLanguage()
      {
          navController = NavHostFragment.findNavController(this);
          NavDirections navDirections=HomeFragmentDirections.actionHomeFragmentToChangeLanguageFragment();
          navController.navigate(navDirections);

      }*/
    public void refresh ()
    {
        NavController  navController = NavHostFragment.findNavController(this);
        NavDirections navDirections=HomeFragmentDirections.actionHomeFragmentSelf();
        navController.navigate(navDirections);


    }
/*    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        menu.clear();

        inflater.inflate(R.menu.menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logOut:
              //  Toast.makeText(getContext(), "Working", Toast.LENGTH_SHORT).show();
                logout();

                // Not implemented here
                return true;
            case R.id.changeLanguage:
                changeLanguage();

                return true;

            default:
                break;
        }

        return false;
    }*/
}