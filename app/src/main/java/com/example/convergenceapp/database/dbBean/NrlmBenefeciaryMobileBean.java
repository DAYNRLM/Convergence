package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class NrlmBenefeciaryMobileBean {
    @ColumnInfo(name = "gp_code")
    private String gp_code;

    @ColumnInfo(name = "village_code")
    private String village_code;

    @ColumnInfo(name = "shg_code")
    private String shg_code;

    @ColumnInfo(name = "member_code")
    private String member_code;

    @ColumnInfo(name = "mobile_number")
    private String mobile_number;

    @ColumnInfo(name = "mobile_belongs_to")
    private String mobile_belongs_to;

   @ColumnInfo(name = "whether_part_of_shg")
    private String whether_part_of_shg;

   @ColumnInfo(name = "reason_of_discontinue")
    private String reason_of_discontinue;

    @ColumnInfo(name = "bank_code")
    private String bank_code;

    @ColumnInfo(name = "branch_code")
    private String branch_code;

    @ColumnInfo(name = "ifsc_code")
    private String ifsc_code;

    @ColumnInfo(name = "account_number")
    private String account_number;

    @ColumnInfo(name = "entered_by")
    private String entered_by;

    @ColumnInfo(name = "entered_date")
    private String entered_date;



    @ColumnInfo(name = "updated_date")
    private String updated_date;

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
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

    public String getGp_code() {
        return gp_code;
    }

    public void setGp_code(String gp_code) {
        this.gp_code = gp_code;
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
    

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }
}
