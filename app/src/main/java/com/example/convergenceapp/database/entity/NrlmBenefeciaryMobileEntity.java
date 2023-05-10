package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NrlmBenefeciaryMobileEntity {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGp_code() {
        return gp_code;
    }

    public void setGp_code(String gp_code) {
        this.gp_code = gp_code;
    }

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    public String getShg_code() {
        return shg_code;
    }

    public void setShg_code(String shg_code) {
        this.shg_code = shg_code;
    }

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getMobile_belongs_to() {
        return mobile_belongs_to;
    }

    public void setMobile_belongs_to(String mobile_belongs_to) {
        this.mobile_belongs_to = mobile_belongs_to;
    }

    public String getWhether_part_of_shg() {
        return whether_part_of_shg;
    }

    public void setWhether_part_of_shg(String whether_part_of_shg) {
        this.whether_part_of_shg = whether_part_of_shg;
    }

    public String getReason_of_discontinue() {
        return reason_of_discontinue;
    }

    public void setReason_of_discontinue(String reason_of_discontinue) {
        this.reason_of_discontinue = reason_of_discontinue;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getEntered_by() {
        return entered_by;
    }

    public void setEntered_by(String entered_by) {
        this.entered_by = entered_by;
    }

    public String getEntered_date() {
        return entered_date;
    }

    public void setEntered_date(String entered_date) {
        this.entered_date = entered_date;
    }

    public String getSyc_flag() {
        return syc_flag;
    }

    public void setSyc_flag(String syc_flag) {
        this.syc_flag = syc_flag;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String gp_code,village_code,shg_code,member_code, mobile_number, mobile_belongs_to,whether_part_of_shg,reason_of_discontinue, bank_code, branch_code, ifsc_code, account_number, entered_by, entered_date, syc_flag, updated_date;

    public NrlmBenefeciaryMobileEntity(String gp_code, String village_code, String shg_code, String member_code, String mobile_number, String mobile_belongs_to, String whether_part_of_shg, String reason_of_discontinue, String bank_code, String branch_code, String ifsc_code, String account_number, String entered_by, String entered_date, String syc_flag, String updated_date) {
        this.gp_code = gp_code;
        this.village_code = village_code;
        this.shg_code = shg_code;
        this.member_code = member_code;
        this.mobile_number = mobile_number;
        this.mobile_belongs_to = mobile_belongs_to;
        this.whether_part_of_shg = whether_part_of_shg;
        this.reason_of_discontinue = reason_of_discontinue;
        this.bank_code = bank_code;
        this.branch_code = branch_code;
        this.ifsc_code = ifsc_code;
        this.account_number = account_number;
        this.entered_by = entered_by;
        this.entered_date = entered_date;
        this.syc_flag = syc_flag;
        this.updated_date = updated_date;
    }
}
