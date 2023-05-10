package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BankMasterEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String bank_code,bank_name,bank_branch_code,bank_branch_name,ifsc_code,alength;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getBank_branch_name() {
        return bank_branch_name;
    }

    public void setBank_branch_name(String bank_branch_name) {
        this.bank_branch_name = bank_branch_name;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }



    public String getAlength() {
        return alength;
    }

    public void setAlength(String alength) {
        this.alength = alength;
    }

    public BankMasterEntity(String bank_code, String bank_name, String bank_branch_code, String bank_branch_name, String ifsc_code, String alength) {
        this.bank_code = bank_code;
        this.bank_name = bank_name;
        this.bank_branch_code = bank_branch_code;
        this.bank_branch_name = bank_branch_name;
        this.ifsc_code = ifsc_code;
        this.alength = alength;
    }
}