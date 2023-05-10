package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PmaygInfoEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String gp_code;

    private String flag;

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

    public String getGp_name() {
        return gp_name;
    }

    public void setGp_name(String gp_name) {
        this.gp_name = gp_name;
    }

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getBeneficiary_holder_name() {
        return beneficiary_holder_name;
    }

    public void setBeneficiary_holder_name(String beneficiary_holder_name) {
        this.beneficiary_holder_name = beneficiary_holder_name;
    }

    public String getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(String beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public String getBeneficiary_acc_no() {
        return beneficiary_acc_no;
    }

    public void setBeneficiary_acc_no(String beneficiary_acc_no) {
        this.beneficiary_acc_no = beneficiary_acc_no;
    }

    public String getBeneficiary_bank_name() {
        return beneficiary_bank_name;
    }

    public void setBeneficiary_bank_name(String beneficiary_bank_name) {
        this.beneficiary_bank_name = beneficiary_bank_name;
    }

    public String getBeneficiary_branch_name() {
        return beneficiary_branch_name;
    }

    public void setBeneficiary_branch_name(String beneficiary_branch_name) {
        this.beneficiary_branch_name = beneficiary_branch_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getHolder_sync_flag() {
        return holder_sync_flag;
    }

    public void setHolder_sync_flag(String holder_sync_flag) {
        this.holder_sync_flag = holder_sync_flag;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getBlockcode() {
        return blockcode;
    }

    public void setBlockcode(String blockcode) {
        this.blockcode = blockcode;
    }

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getSl_no_member() {
        return sl_no_member;
    }

    public void setSl_no_member(String sl_no_member) {
        this.sl_no_member = sl_no_member;
    }

    private String gp_name;
    private String village_code;
    private String village_name;
    private String scheme;
    private String beneficiary_holder_name;
    private String beneficiary_id;

    private String ifsc_code;

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public PmaygInfoEntity(String gp_code, String gp_name, String village_code, String village_name, String scheme, String beneficiary_holder_name, String beneficiary_id, String beneficiary_acc_no, String beneficiary_bank_name, String beneficiary_branch_name, String mobile_no, String member_name, String holder_sync_flag, String mothername, String districtname, String blockcode, String districtcode, String statecode, String fathername, String blockname, String sl_no_member, String ifsc_code,String flag) {

        this.gp_code = gp_code;
        this.gp_name = gp_name;
        this.village_code = village_code;
        this.village_name = village_name;
        this.scheme = scheme;
        this.beneficiary_holder_name = beneficiary_holder_name;
        this.beneficiary_id = beneficiary_id;
        this.beneficiary_acc_no = beneficiary_acc_no;
        this.beneficiary_bank_name = beneficiary_bank_name;
        this.beneficiary_branch_name = beneficiary_branch_name;
        this.mobile_no = mobile_no;
        this.member_name = member_name;
        this.holder_sync_flag = holder_sync_flag;
        this.mothername = mothername;
        this.districtname = districtname;
        this.blockcode = blockcode;
        this.districtcode = districtcode;
        this.statecode = statecode;
        this.fathername = fathername;
        this.blockname = blockname;
        this.sl_no_member = sl_no_member;
        this.ifsc_code=ifsc_code;
        this.flag=flag;
    }

    private String beneficiary_acc_no;
    private String beneficiary_bank_name;
    private String beneficiary_branch_name;
    private String mobile_no;
    private String member_name;
    private String holder_sync_flag;
    private String mothername;
    private String districtname;
    private String blockcode;
    private String districtcode;
    private String statecode;
    private String fathername;
    private String blockname;
    private String sl_no_member;

  }