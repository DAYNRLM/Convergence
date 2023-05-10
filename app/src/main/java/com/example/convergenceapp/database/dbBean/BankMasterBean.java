package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class BankMasterBean {

    @ColumnInfo(name = "bank_code")
    private String bankCode;

    @ColumnInfo(name = "bank_name")
    private String bankName;

    @ColumnInfo(name = "bank_branch_code")
    private String branchCode;

    @ColumnInfo(name = "bank_branch_name")
    private String branchName;

    @ColumnInfo(name = "ifsc_code")
    private String ifscCode;

    @ColumnInfo(name = "alength")
    private String alength;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAlength() {
        return alength;
    }

    public void setAlength(String alength) {
        this.alength = alength;
    }
}
