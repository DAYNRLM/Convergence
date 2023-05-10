package com.example.convergenceapp.Auth;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.VolleyError;
import com.example.convergenceapp.HomeActivity;
import com.example.convergenceapp.NrlmDashboardActivity;
import com.example.convergenceapp.R;
import com.example.convergenceapp.database.AppDatabase;
import com.example.convergenceapp.database.dbBean.BankMasterBean;
import com.example.convergenceapp.database.dbBean.BankNameAndBranchName;
import com.example.convergenceapp.database.dbBean.MemberBean;
import com.example.convergenceapp.database.dbBean.MemberReasonBean;
import com.example.convergenceapp.database.dbBean.MobileBelongsToBean;
import com.example.convergenceapp.database.dbBean.NrlmBenefeciaryMobileBean;
import com.example.convergenceapp.database.dbBean.NrlmDataBean;
import com.example.convergenceapp.database.dbBean.NrlmVillageBean;
import com.example.convergenceapp.database.dbBean.ReasonBean;
import com.example.convergenceapp.database.dbBean.ShgBean;
import com.example.convergenceapp.database.entity.MemberEntryInfoEntity;
import com.example.convergenceapp.database.entity.NrlmBenefeciaryMobileEntity;
import com.example.convergenceapp.database.entity.PmaygInfoEntity;
import com.example.convergenceapp.databinding.FragmentHomeBinding;
import com.example.convergenceapp.databinding.FragmentMemberBinding;
import com.example.convergenceapp.request.BeneficiaryDetails;
import com.example.convergenceapp.request.BenficiaryDtl;
import com.example.convergenceapp.request.MemberSyncRequest;
import com.example.convergenceapp.request.PmaygMasterRequest;
import com.example.convergenceapp.request.SyncRequest;
import com.example.convergenceapp.response.PmaygMasterResponse;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.crypto.NoSuchPaddingException;

public class MemberFragment extends Fragment {
    public VolleyResult mResultCallBack = null;
    Boolean valid;
    ArrayAdapter<String> nrlmGpAdapter, nrlmVillageAdapter, shgAdapter,memberAdapter,mobileBelogToAdapter,bankAdapter,branchAdapter,disContinueReasonAdapter;
    Toolbar toolbar_home;
    List<String> nrlmGpName, nrlmGpCode, nrlmVillageName, nrlmVillageCode, shgName, shgCode, memberName, memberCode,memberSpouseList, memberMobileList,memberActNameList,mobileBelogToName,mobileBelogToCode,
            bankNameList,bankCodeList,branchNameList,branchCodeList,ifscCodeList,actNumLengthList,disContinueReasonNameList;

    List<Integer> disContinueReasonCodeList;


    Integer selectedDisContinueReasonCode=0;
    String selectedNrlmGpCode="",  selectedNrlmVillageCode="",selectedShgCode="",selectedmemberCode="",selectedMobileBelogCode="",selectedBankCode="",selectedBranchCode="",selectedActName="",
            selectedNrlmGp, selectedNrlmVillage, selectedShg,  selectedmember,selectedMobileBelogToName,selectedBankName,selectedBranchName,selectedActNumLength="",selectedMemberSpouse, selectedMemberMobile,
            selectedDisContinueReasonName,selectedBankCodes,selecteDBankBranchCode;


    int indexOfBankName,indexOfBranchName;
    public NavController navController;
    AppDatabase appDatabase;
    NavDirections navDirections;
    List<NrlmVillageBean> nrlmVillageBeans;
    List<BankNameAndBranchName> bankNameAndBranchNamesList;
    List<ShgBean> shgBeans;
    List<MemberBean> memberBeans;
    List<BankMasterBean> bankMasterBeans;
    List<MobileBelongsToBean> mobileBelongsToBeans;
    List<ReasonBean> reasonBeanList;
    private FragmentMemberBinding binding;
    List<NrlmBenefeciaryMobileBean>  nrlmBenefeciaryMobileBeans;

    int iPartOfShg=0;
    String mobNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = AppDatabase.getDatabase(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentMemberBinding.inflate(inflater);
        //binding = FragmentHomeBinding.inflate(inflater);

        navController = NavHostFragment.findNavController(this);
        navDirections=MemberFragmentDirections.actionMemberFragmentToHomeFragment();


        ///   selectNrlmGP
        List<NrlmDataBean> nrlmDataBeans = appDatabase.nrlmInfoDao().getNrlmList();
        nrlmGpName = new ArrayList<String>();
        nrlmGpCode = new ArrayList<String>();

        for (int i = 0; i < nrlmDataBeans.size(); i++) {
            nrlmGpName.add(nrlmDataBeans.get(i).getGpName());
            nrlmGpCode.add(nrlmDataBeans.get(i).getGpCode());

        }

        nrlmGpAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, nrlmGpName);
        binding.spinnerNrlmGp.setAdapter(nrlmGpAdapter);
        nrlmGpAdapter.notifyDataSetChanged();


        binding.spinnerNrlmGp.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedNrlmGp = nrlmGpName.get(i);
            selectedNrlmGpCode = nrlmGpCode.get(i);

            nrlmVillageBeans = appDatabase.nrlmInfoDao().getNrlmVillage(selectedNrlmGpCode);
            nrlmVillageName = new ArrayList<String>();
            nrlmVillageCode = new ArrayList<String>();


            for (int j = 0; j < nrlmVillageBeans.size(); j++) {
                nrlmVillageName.add(nrlmVillageBeans.get(j).getNrlmVillageName());

            }
            for (int j = 0; j < nrlmVillageBeans.size(); j++) {
                nrlmVillageCode.add(nrlmVillageBeans.get(j).getNrlmVillageCode());

            }
            nrlmVillageAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, nrlmVillageName);
            binding.spinnerNrlmVillage.setAdapter(nrlmVillageAdapter);
            nrlmVillageAdapter.notifyDataSetChanged();

            if (!selectedNrlmGp.equalsIgnoreCase("")) {
                selectedNrlmVillageCode = "";
                selectedNrlmVillage = "";
                binding.spinnerNrlmVillage.setText("");

                selectedShg = "";
                selectedShgCode = "";
                binding.spinnerShgName.setText("");

                selectedmemberCode = "";
                selectedmember = "";
                binding.spinnerMemberName.setText("");

                binding.tvSpouseName.setText("");
                binding.llSpouseName.setVisibility(View.GONE);

                binding.etMobile.setText("");

                selectedMobileBelogCode = "";
                selectedMobileBelogToName = "";
                binding.spinnerMobileBelongsTo.setText("");

                iPartOfShg=0;
                binding.rgPartOfShg.clearCheck();

                selectedDisContinueReasonCode = 0;
                selectedDisContinueReasonName = "";
                binding.spinnerDisContinueReason.setText("");

                selectedBankCode = "";
                selectedBankName = "";
                binding.spinnerBank.setText("");

                selectedBranchCode="";
                selectedBranchName = "";
                binding.spinnerBranch.setText("");
                binding.tvIfscCode.setText("");
                binding.llIfscCode.setVisibility(View.GONE);
                binding.etAccountNumber.setText("");


            }

        });


        //   selectNrlmVillage


        binding.spinnerNrlmVillage.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedNrlmVillage = nrlmVillageName.get(i);
            selectedNrlmVillageCode = nrlmVillageCode.get(i);
            shgBeans = appDatabase.nrlmInfoDao().getShg(selectedNrlmVillage);
            shgName = new ArrayList<String>();
            shgCode = new ArrayList<String>();

            for (int j = 0; j < shgBeans.size(); j++) {
                shgName.add(shgBeans.get(j).getShgName());
                shgCode.add(shgBeans.get(j).getShgCode());

            }


            shgAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, shgName);
            binding.spinnerShgName.setAdapter(shgAdapter);
            shgAdapter.notifyDataSetChanged();
            if (!selectedNrlmVillage.equalsIgnoreCase("")) {
                selectedShg = "";
                selectedShgCode = "";
                binding.spinnerShgName.setText("");
                selectedmemberCode = "";
                selectedmember = "";
                binding.spinnerMemberName.setText("");

                binding.tvSpouseName.setText("");
                binding.llSpouseName.setVisibility(View.GONE);
                binding.etMobile.setText("");

                selectedMobileBelogCode = "";
                selectedMobileBelogToName = "";
                binding.spinnerMobileBelongsTo.setText("");

                iPartOfShg=0;
                binding.rgPartOfShg.clearCheck();

                selectedDisContinueReasonCode = 0;
                selectedDisContinueReasonName = "";
                binding.spinnerDisContinueReason.setText("");

                selectedBankCode = "";
                selectedBankName = "";
                binding.spinnerBank.setText("");

                selectedBranchCode="";
                selectedBranchName = "";
                binding.spinnerBranch.setText("");
                binding.tvIfscCode.setText("");
                binding.llIfscCode.setVisibility(View.GONE);
                binding.etAccountNumber.setText("");

            }

        });


        ///   select Nrlm SHG


        binding.spinnerShgName.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedShg= shgName.get(i);
            selectedShgCode= shgCode.get(i);

            memberBeans = appDatabase.nrlmInfoDao().getMember(selectedShgCode);
            memberName = new ArrayList<String>();
            memberCode = new ArrayList<String>();
            memberSpouseList = new ArrayList<String>();
            memberMobileList = new ArrayList<String>();
            memberActNameList = new ArrayList<String>();
            for (int j=0;j<memberBeans.size();j++){
                memberName.add(memberBeans.get(j).getMemberName());
                memberCode.add(memberBeans.get(j).getMemberCode());
                memberSpouseList.add(memberBeans.get(j).getBelonging_name());
                memberMobileList.add(memberBeans.get(j).getMobile_number());
                memberActNameList.add(memberBeans.get(j).getActNum());

            }

            memberAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text,memberName);
            binding.spinnerMemberName.setAdapter(memberAdapter);
            memberAdapter.notifyDataSetChanged();
            if (!selectedShg.equalsIgnoreCase("")) {
                selectedmemberCode = "";
                selectedmember = "";
                binding.spinnerMemberName.setText("");

                binding.tvSpouseName.setText("");
                binding.llSpouseName.setVisibility(View.GONE);
                binding.etMobile.setText("");

                selectedMobileBelogCode = "";
                selectedMobileBelogToName = "";
                binding.spinnerMobileBelongsTo.setText("");

                iPartOfShg=0;
                binding.rgPartOfShg.clearCheck();

                selectedDisContinueReasonCode = 0;
                selectedDisContinueReasonName = "";
                binding.spinnerDisContinueReason.setText("");

                selectedBankCode = "";
                selectedBankName = "";
                binding.spinnerBank.setText("");

                selectedBranchCode="";
                selectedBranchName = "";
                binding.spinnerBranch.setText("");
                binding.tvIfscCode.setText("");
                binding.llIfscCode.setVisibility(View.GONE);
                binding.etAccountNumber.setText("");

            }

        });
        //  select member

        binding.spinnerMemberName.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedmember = memberName.get(i);
            selectedmemberCode = memberCode.get(i);
            selectedMemberSpouse = memberSpouseList.get(i);
            selectedMemberMobile = memberMobileList.get(i);
            selectedActName = memberActNameList.get(i);
            selecteDBankBranchCode= appDatabase.nrlmInfoDao().getMemberbranchCode(selectedmemberCode);
            bankNameAndBranchNamesList= appDatabase.bankMasterDao().getBankNameAndBranchName(selecteDBankBranchCode);

            selectedBankCodes=appDatabase.nrlmInfoDao().getMemberbankCode(selectedmemberCode);
            //  selectedBankCode=appDatabase.nrlmInfoDao().getMemberbankCode(selectedmemberCode);
            for (int j =0; j<bankNameAndBranchNamesList.size();j++){
                //  selectedBankCodes=bankNameAndBranchNamesList.get(j).getBranchCode();
                selectedBankName=bankNameAndBranchNamesList.get(j).getBankName();
                selectedBranchName=bankNameAndBranchNamesList.get(j).getBranchName();
                selectedBankCode=bankNameAndBranchNamesList.get(j).getBankCode();
                indexOfBankName = bankNameList.indexOf(selectedBankName);
                indexOfBranchName = bankNameList.indexOf(selectedBranchName);
                Toast.makeText(getContext(),"Bank"+indexOfBranchName+indexOfBankName,Toast.LENGTH_LONG).show();
                //   Toast.makeText(getContext(),"Bank"+selectedBranchName+selectedBankName,Toast.LENGTH_LONG).show();
                binding.txtbranchNameShowid.setText(selectedBranchName);
                binding.txtBankNameShowid.setText(selectedBankName);
                //   containsString()


                binding.spinnerBank.setText(selectedBankName);
            }
          /*  if (!selectedBankCodes.equalsIgnoreCase("NA")){
                for (int j =0; j<bankNameAndBranchNamesList.size();j++){
                    selecteDBankBranchCode=bankNameAndBranchNamesList.get(j).getBranchCode();
                    selectedBankName=bankNameAndBranchNamesList.get(j).getBankName();
                    selectedBranchName=bankNameAndBranchNamesList.get(j).getBranchCode();
                    binding.spinnerBank.setSelection(j);
                }
               // binding.spinnerBranch.setText(selectedBranchName);

            }*/
            //   selectedBankCode = memberActNameList.get(i);
            if (!selectedmember.equalsIgnoreCase("")) {

                binding.tvSpouseName.setText(selectedMemberSpouse);
                binding.llSpouseName.setVisibility(View.VISIBLE);
                binding.etMobile.setText(selectedMemberMobile);
                binding.etAccountNumber.setText(selectedActName);

                selectedMobileBelogCode = "";
                selectedMobileBelogToName = "";
                binding.spinnerMobileBelongsTo.setText("");

                iPartOfShg=0;
                binding.rgPartOfShg.clearCheck();

                selectedDisContinueReasonCode = 0;
                selectedDisContinueReasonName = "";
                binding.spinnerDisContinueReason.setText("");

                selectedBankCode = "";
                selectedBankName = "";
                binding.spinnerBank.setText("");

                selectedBranchCode="";
                selectedBranchName = "";
                binding.spinnerBranch.setText("");
                binding.tvIfscCode.setText("");
                binding.llIfscCode.setVisibility(View.GONE);
                // binding.etAccountNumber.setText("");
            }

        });



        mobileBelongsToBeans = appDatabase.mobileNoBelongsToDao().getMobileBelongsToList();
        mobileBelogToName = new ArrayList<String>();
        mobileBelogToCode = new ArrayList<String>();

        for (int j=0;j<mobileBelongsToBeans.size();j++){
            mobileBelogToName.add(mobileBelongsToBeans.get(j).getTypeName());
            mobileBelogToCode.add(mobileBelongsToBeans.get(j).getTypeId());
        }

        mobileBelogToAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, mobileBelogToName);
        binding.spinnerMobileBelongsTo.setAdapter(mobileBelogToAdapter);
        mobileBelogToAdapter.notifyDataSetChanged();

        binding.spinnerMobileBelongsTo.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedMobileBelogToName = mobileBelogToName.get(i);
            selectedMobileBelogCode = mobileBelogToCode.get(i);
        });

        List<MemberReasonBean> reasonBeans = appDatabase.memberReasonDao().getMemberReasonList();
        disContinueReasonNameList = new ArrayList<String>();
        disContinueReasonCodeList = new ArrayList<Integer>();

        for (int i = 0; i < reasonBeans.size(); i++) {
            disContinueReasonNameList.add(reasonBeans.get(i).getReasonName());
            disContinueReasonCodeList.add(reasonBeans.get(i).getReasonId());

        }

        disContinueReasonAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, disContinueReasonNameList);
        binding.spinnerDisContinueReason.setAdapter(disContinueReasonAdapter);
        disContinueReasonAdapter.notifyDataSetChanged();

        binding.spinnerDisContinueReason.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedDisContinueReasonName = disContinueReasonNameList.get(i);
            selectedDisContinueReasonCode = disContinueReasonCodeList.get(i);
        });

        binding.rgPartOfShg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group,int checkedId){
                if (checkedId == R.id.rbPartOfShgYes) {
                    iPartOfShg=1;
                    binding.llReason.setVisibility(View.GONE);
                    binding.llBank.setVisibility(View.VISIBLE);
                    binding.llBranch.setVisibility(View.VISIBLE);
                    binding.branchShowVisid.setVisibility(View.VISIBLE);
                    binding.bankShowVisid.setVisibility(View.VISIBLE);
                    binding.llAccountNumber.setVisibility(View.VISIBLE);
                    binding.llMobile.setVisibility(View.VISIBLE);
                    binding.llMobileBelongsTo.setVisibility(View.VISIBLE);


                    selectedDisContinueReasonCode=0;
                } else if (checkedId == R.id.rbPartOfShgNo) {
                    iPartOfShg=2;
                    binding.llReason.setVisibility(View.VISIBLE);
                    binding.llBank.setVisibility(View.GONE);
                    binding.llBranch.setVisibility(View.GONE);
                    binding.llIfscCode.setVisibility(View.GONE);
                    binding.llAccountNumber.setVisibility(View.GONE);
                    binding.llMobile.setVisibility(View.GONE);
                    binding.llMobileBelongsTo.setVisibility(View.GONE);

                    binding.etAccountNumber.setText("");
                    binding.tvIfscCode.setText("");

                    selectedBankCode = "";
                    selectedBankName = "";
                    binding.spinnerBank.setText("");

                    selectedBranchCode="";
                    selectedBranchName = "";
                    binding.spinnerBranch.setText("");

                }
            }
        });

        bankMasterBeans = appDatabase.bankMasterDao().getBankList();
        bankNameList = new ArrayList<String>();
        bankCodeList = new ArrayList<String>();
        for (int j=0;j<bankMasterBeans.size();j++){
            bankNameList.add(bankMasterBeans.get(j).getBankName());
            bankCodeList.add(bankMasterBeans.get(j).getBankCode());



        }

       /* indexOfBankName = bankNameList.indexOf(selectedBankName);
        indexOfBranchName = bankNameList.indexOf(selectedBranchName);*/
        bankAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, bankNameList);
        binding.spinnerBank.setAdapter(bankAdapter);
        //    binding.spinnerBranch.setSelection(indexOfBranchName);
        //   binding.spinnerBank.setSelection(indexOfBankName);
        bankAdapter.notifyDataSetChanged();
        binding.spinnerBank.setSelection(indexOfBankName);


        binding.spinnerBank.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedBankName = bankNameList.get(i);
            selectedBankCode = bankCodeList.get(i);
            List<BankMasterBean>  branchMasterList = appDatabase.bankMasterDao().getBranchList(selectedBankCode);
            branchNameList = new ArrayList<String>();
            branchCodeList = new ArrayList<String>();
            ifscCodeList = new ArrayList<String>();
            actNumLengthList = new ArrayList<String>();
            for (int j=0;j<branchMasterList.size();j++){
                branchNameList.add(branchMasterList.get(j).getBranchName());
                branchCodeList.add(branchMasterList.get(j).getBranchCode());
                ifscCodeList.add(branchMasterList.get(j).getIfscCode());
                actNumLengthList.add(branchMasterList.get(j).getAlength());
            }
            branchAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, branchNameList);
            binding.spinnerBranch.setAdapter(branchAdapter);
            branchAdapter.notifyDataSetChanged();
            if (!selectedBankCode.equalsIgnoreCase("")) {
                selectedBranchName="";
                selectedBranchCode="";
                binding.spinnerBranch.setText("");

            }


        });



        binding.spinnerBranch.setOnItemClickListener((adapterView, view1, i, l) -> {
            selectedBranchName = branchNameList.get(i);
            selectedBranchCode = branchCodeList.get(i);
            selectedActNumLength=actNumLengthList.get(i);
            binding.tvIfscCode.setText(ifscCodeList.get(i));
            binding.llIfscCode.setVisibility(View.VISIBLE);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mobNo= binding.etMobile.getText().toString();



                Log.d(TAG, "onClick: village_code ::"+selectedNrlmVillageCode+
                        "gp_code ::"+selectedNrlmGpCode+
                        "shg_code ::"+selectedShgCode+
                        "member_code ::"+selectedmemberCode+
                        "mobile_number :: "+binding.etMobile.getText().toString()+
                        "mobile_belongs_to ::"+selectedMobileBelogCode+
                        "whether_part_of_shg ::"+iPartOfShg+
                        "reason_of_distinue ::"+selectedDisContinueReasonCode+
                        "bank_code ::"+selectedBankCode+
                        "branch_code ::"+selectedBranchCode+
                        "ifsc_code ::"+binding.tvIfscCode.getText().toString()+
                        "account_number ::"+binding.etAccountNumber.getText().toString()/*+
                        "entered_by"+selectedBranchCode+"entered_date"+selectedBranchCode+"updated_by"+selectedBranchCode+"updated_date"+selectedBranchCode*/);
                if(selectedNrlmGpCode.isEmpty())
                    DialogFactory.getInstance().showAlert(getContext(),"Please select GP.","Ok");
                else  if(selectedNrlmVillageCode.isEmpty())
                    DialogFactory.getInstance().showAlert(getContext(),"Please select Village.","Ok");
                else  if(selectedShgCode.isEmpty())
                    DialogFactory.getInstance().showAlert(getContext(),"Please select SHG.","Ok");
                else  if(selectedmemberCode.isEmpty())
                    DialogFactory.getInstance().showAlert(getContext(),"Please select Member.","Ok");

                else if(iPartOfShg==1 &&binding.etMobile.getText().toString().isEmpty()) {

                    binding.etMobile.setError("Invalid Entry");
                    DialogFactory.getInstance().showAlert(getContext(),"Please enter  mobile number.","Ok");

                }

                else if(iPartOfShg==1 &&!isValidMobileNumber(mobNo)|| mobNo.equalsIgnoreCase("6666666666")
                        || mobNo.equalsIgnoreCase("7777777777")|| mobNo.equalsIgnoreCase("8888888888")
                         || mobNo.equalsIgnoreCase("9999999999")) {
                    binding.etMobile.setError("Invalid Entry");
                    DialogFactory.getInstance().showAlert(getContext(),"Please enter valid mobile number.","Ok");
                }


                else  if(iPartOfShg==1 && selectedMobileBelogCode.isEmpty())
                    DialogFactory.getInstance().showAlert(getContext(),"Please select Mobile No. belogns to.","Ok");
                else  if(iPartOfShg==0)
                    DialogFactory.getInstance().showAlert(getContext(),"Please select Whether member is part of SHG.","Ok");
                else  if(iPartOfShg==1 && selectedBankCode.isEmpty() )
                    DialogFactory.getInstance().showAlert(getContext(),"Please select Bank Name.","Ok");
                else  if(iPartOfShg==1 && selectedBranchCode.isEmpty())
                    DialogFactory.getInstance().showAlert(getContext(),"Please select Branch Name.","Ok");
                else if(iPartOfShg==1 && binding.etAccountNumber.getText().toString().isEmpty()) {
                    binding.etAccountNumber.setError("Invalid Entry");
                    DialogFactory.getInstance().showAlert(getContext(),"Please enter  Account Number.","Ok");
                }
                else if(iPartOfShg==1 && !binding.etAccountNumber.getText().toString().isEmpty() && !selectedActNumLength.isEmpty() &&  !isValidActNum(binding.etAccountNumber.getText().toString().trim().length(),selectedActNumLength) ) {
                    binding.etAccountNumber.setError("Invalid Entry");
                    DialogFactory.getInstance().showAlert(getContext(),"Please enter valid  Account Number.","Ok");
                }
                else  if(iPartOfShg==2 && selectedDisContinueReasonCode==0 )
                    DialogFactory.getInstance().showAlert(getContext(),"Please select Reason of discontinue.","Ok");

                else{
                    String enteredDate = getCurrentDateTimefordatabaseStorage();

                    //       syncAPI(selectedShgCode,selectedmemberCode,selectedNrlmVillageCode,selectedMobileBelogCode,binding.etMobile.getText().toString(),selectedBankCode,selectedBranchCode,binding.etAccountNumber.getText().toString(), String.valueOf(selectedDisContinueReasonCode),enteredDate);

                    String userId = appDatabase.loginInfoDao().getLoginId();
                    {



                        appDatabase.nrlmBenefeciaryMobileDao().insert(
                                new NrlmBenefeciaryMobileEntity(selectedNrlmGpCode, selectedNrlmVillageCode, selectedShgCode,
                                        selectedmemberCode, binding.etMobile.getText().toString(), selectedMobileBelogCode, String.valueOf(iPartOfShg), String.valueOf(selectedDisContinueReasonCode),
                                        selectedBankCode, selectedBranchCode, binding.tvIfscCode.getText().toString(), binding.etAccountNumber.getText().toString(),
                                        userId, enteredDate, "0", ""
                                ));
                        syncApi();
                        //DialogFactory.getInstance().showAlert(getContext(),"Data has been saved successfully.","Ok");

                    }
                    //     Log.d(TAG, "onClick:Data "+ appDatabase.nrlmBenefeciaryMobileDao().getNrlmBenefeciaryMobileData());


                }


            }
        });


    }


    public void syncApi(){



        if(NetworkFactory.isInternetOn(getContext()))
        {

            ProgressDialog   progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();




            /*******make json object is encrypted and *********/
            JSONObject encryptedObject =new JSONObject();
            JSONObject plainData=null;

            try {
                Cryptography cryptography = new Cryptography();




                @SuppressLint("HardwareIds") String  imeiNo = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

                MemberSyncRequest memberSyncRequest=new MemberSyncRequest();

                String  loginId= PreferenceFactory.getInstance().getSharedPrefrencesData(PreferenceKeyManager.getPrefLoginId(),getContext());
                String  deviceInfo=AppUtils.getInstance().getDeviceInfo();
                memberSyncRequest.setUser_id(loginId);
                memberSyncRequest.setImei_no(imeiNo);
                memberSyncRequest.setDevice_name(deviceInfo);
                memberSyncRequest.setLocation_coordinate("1232323");
                nrlmBenefeciaryMobileBeans=appDatabase.nrlmBenefeciaryMobileDao().getNrlmBenefeciaryMobileDataAcordingSyncFlag("0");
                List<String> nrlmMemberList=new ArrayList<>();

                ArrayList<BenficiaryDtl> Bendata = new ArrayList<>();

                for (int i=0;i<nrlmBenefeciaryMobileBeans.size();i++){



                    BenficiaryDtl beneficiaryDetails=new BenficiaryDtl();



                    beneficiaryDetails.setShg_code(nrlmBenefeciaryMobileBeans.get(i).getShg_code());
                    beneficiaryDetails.setShg_member_code(nrlmBenefeciaryMobileBeans.get(i).getMember_code());
                    beneficiaryDetails.setEntity_code(nrlmBenefeciaryMobileBeans.get(i).getVillage_code());
                    beneficiaryDetails.setMob_no_belongs_to(nrlmBenefeciaryMobileBeans.get(i).getMobile_belongs_to());
                    beneficiaryDetails.setMob_no(nrlmBenefeciaryMobileBeans.get(i).getMobile_number());
                    beneficiaryDetails.setBank_code(nrlmBenefeciaryMobileBeans.get(i).getBank_code());
                    beneficiaryDetails.setBranch_code(nrlmBenefeciaryMobileBeans.get(i).getBranch_code());
                    beneficiaryDetails.setAc_no(nrlmBenefeciaryMobileBeans.get(i).getAccount_number());
                    beneficiaryDetails.setReason_id(nrlmBenefeciaryMobileBeans.get(i).getReason_of_discontinue());
                    beneficiaryDetails.setApp_ver("1.0.0");
                    beneficiaryDetails.setCreated_on_app(nrlmBenefeciaryMobileBeans.get(i).getEntered_date());
                    nrlmMemberList.add(nrlmBenefeciaryMobileBeans.get(i).getMember_code());
                    Bendata.add(beneficiaryDetails);


                }
                memberSyncRequest.setBenficiary_dtl(Bendata);


                //encryptedObject.accumulate("data",cryptography.encrypt(new Gson().toJson(pmaygMasterRequest)));
                String plnObj=new Gson().toJson(memberSyncRequest);
                plainData =new JSONObject(plnObj);
                navController = NavHostFragment.findNavController(this);
                navDirections=MemberFragmentDirections.actionMemberFragmentToHomeFragment();


            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } //catch (InvalidKeyException e) {
            // e.printStackTrace();
            // } catch (InvalidAlgorithmParameterException e) {
            // e.printStackTrace();
            //} catch (IllegalBlockSizeException e) {
            //  e.printStackTrace();
            // } catch (BadPaddingException e) {
            // e.printStackTrace();
            // } catch (UnsupportedEncodingException e) {
            //   e.printStackTrace();
            // }
            /***********************************************/

            //AppUtils.getInstance().showLog("request of NrlmMaster" +encryptedObject, LoginFragment.class);
            Log.d(TAG, "request of PmaygMaster "+plainData.toString());
            mResultCallBack = new VolleyResult() {
                @Override
                public void notifySuccess(String requestType, JSONObject response) {

                    progressDialog.dismiss();
              /*      JSONObject jsonObject = null;    //manish commented



                    String objectResponse="";
                    if(response.has("data")){
                        try {
                            objectResponse=response.getString("data");
                          //   AppUtils.getInstance().showLog("response encrupt"+objectResponse,LoginFragment.class);
                            Log.d(TAG, "response encrupt "+objectResponse.toString());


                        }catch (JSONException e)
                        {
                        //    AppUtils.getInstance().showLog("objjjjjjj"+objectResponse,LoginFragment.class);
                            Log.d(TAG, "objjjjjjj: "+objectResponse.toString());
                        }


                    }else {
                        return;
                    }*/

                 /*   try {               //manish commented
                        JSONObject jsonObject1=new JSONObject(objectResponse);
                        objectResponse=jsonObject1.getString("data");
                    //    AppUtils.getInstance().showLog("dashboard"+jsonObject1,LoginFragment.class);
                        Log.d(TAG, "dashboard: "+jsonObject1.toString());

                    }catch (JSONException e)
                    {
                       // AppUtils.getInstance().showLog("exceptionDataOfBlock"+e,LoginFragment.class);
                        Log.d(TAG, "exceptionDataOfBlock: "+e.toString());

                    }*/


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {

                            // jsonObject = new JSONObject(cryptography.decrypt(objectResponse)); //Manish comment
                            //if (jsonObject.getString("E200").equalsIgnoreCase("Success"))
                            // AppUtils.getInstance().showLog("responseJSON" + jsonObject.toString(), LoginFragment.class);
                            Log.d(TAG, "responseJSON: "+response.toString());

                            //       AppUtils.getInstance().showLog("responseJSON" +message), LoginFragment.class);


                           /* if (code==200){



                                }*/





                            if(response.getString("message").equalsIgnoreCase("success"))
                            {

                                //  appDatabase.pmaygInfoDao().updateSyncFlag(beneficiaryId);
                                for (int i=0;i<nrlmBenefeciaryMobileBeans.size();i++)
                                {
                                    String memCode=nrlmBenefeciaryMobileBeans.get(i).getMember_code();
                                    appDatabase.nrlmBenefeciaryMobileDao().setUpdateSyncFlag(memCode);
                                    // appDatabase.pmaygInfoDao().updateSyncFlag(benId);
                                }
                                progressDialog.dismiss();
                                Toast.makeText(getContext(),"Synced successfully",Toast.LENGTH_LONG).show();
                                AppUtils.getInstance().showLog("Synced", HomeFragment.class);
                                //navController.navigate(navDirections);
                                Intent i = new Intent(getContext(), HomeActivity.class);
                                startActivity(i);

                            }
                            else {
                                Toast.makeText(getContext(),"Data saved locally",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getContext(), HomeActivity.class);
                                startActivity(i);


                            }




                        } catch (Exception e) {
                            //    progressDialog.dismiss();
                            // Log.d(TAG, "notifySuccess: "+e);
                            AppUtils.getInstance().showLog("DecryptEx" + e, LoginFragment.class);
                        }
                    }


                }

                @Override
                public void notifyError(String requestType, VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Data saved locally",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getContext(), HomeActivity.class);
                    startActivity(i);


                }
            };
            VolleyService  volleyService = VolleyService.getInstance(getContext());

            //  volleyService.postDataVolley("dashboardRequest", "http://10.197.183.105:8080/nrlmwebservice/services/convergence/assigndata", encryptedObject, mResultCallBack);
            volleyService.postDataVolley("Request of sync", "https://nrlm.gov.in/nrlmwebservice/services/convergence/memsyncdata", plainData, mResultCallBack);



        }
        else {
            //   progressDialog.dismiss();
            Log.d(TAG, "Internet: ");
            Toast.makeText(getContext(),"Data saved locally ",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getContext(), HomeActivity.class);
            startActivity(i);



        }

    }


    public static boolean isValidMobileNumber(String strMobileNo) {
        String phonePattern = "^[6-9][0-9]{9}$";
        if (strMobileNo.matches(phonePattern))
            return true;
        else
            return false;

    }



    public static boolean isValidActNum(int inputActLength, String validActNumLength) {
        Boolean bResult=false;
        String[] array = validActNumLength.split(",");

        for(int i=0;i<array.length;i++){
            if(inputActLength==(Integer.parseInt(array[i])))
                bResult = true;
        }
        return bResult;
    }

    public static String getCurrentDateTimefordatabaseStorage()
    {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String strDate = sdf.format(new Date());

        return strDate;
    }

    private boolean containsString(String testString, ArrayList<String> list)
    {
        return list.contains(testString);
    }
}
