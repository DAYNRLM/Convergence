package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class BankNameAndBranchName {

    @ColumnInfo(name = "bank_name")
    private String bankName;

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
}
