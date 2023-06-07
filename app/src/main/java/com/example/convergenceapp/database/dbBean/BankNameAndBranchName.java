package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class BankNameAndBranchName {

    @ColumnInfo(name = "bank_name")
    private String bankName;


    @ColumnInfo(name = "ifsc_code")
    private String ifsc_code;

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @ColumnInfo(name = "bank_code")
    private String bankCode;

    @ColumnInfo(name = "bank_branch_name")
    private String branchName;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @ColumnInfo(name = "bank_branch_code")
    private String branchCode;
}
