package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class BeneficiaryBean {
    @ColumnInfo(name = "beneficiary_id")
    private String benId;
    @ColumnInfo(name = "beneficiary_holder_name")
    private String benName;
    @ColumnInfo(name = "fathername")
    private String fatherName;

    private String ifsc_code;

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @ColumnInfo(name = "mothername")
    private String motherName;
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @ColumnInfo(name = "beneficiary_acc_no")
    private String benAccNo;
    @ColumnInfo(name = "mobile_no")
    private String mobileNumber;

    public String getBenId() {
        return benId;
    }

    public void setBenId(String benId) {
        this.benId = benId;
    }

    public String getBenName() {
        return benName;
    }

    public void setBenName(String benName) {
        this.benName = benName;
    }

    public String getBenAccNo() {
        return benAccNo;
    }

    public void setBenAccNo(String benAccNo) {
        this.benAccNo = benAccNo;
    }

    public String getBenBankName() {
        return benBankName;
    }

    public void setBenBankName(String benBankName) {
        this.benBankName = benBankName;
    }

    public String getBenBranchName() {
        return benBranchName;
    }

    public void setBenBranchName(String benBranchName) {
        this.benBranchName = benBranchName;
    }

    @ColumnInfo(name = "beneficiary_bank_name")
    private String benBankName;
    @ColumnInfo(name = "beneficiary_branch_name")
    private String benBranchName;


}
