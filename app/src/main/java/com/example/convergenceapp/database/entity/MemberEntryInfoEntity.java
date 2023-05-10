package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class MemberEntryInfoEntity {


 /*   {"user_id":"as","imei_no":"as", "device_name":"as", "location_coordinate":"as","benficiary_dtl":[{"scheme_name":"asdf",
            "reg_no":"asdf",
            "lgd_gp_cd":"asdf",
            "lgd_vill_cd":"asdf",
            "mobile_no":"asdf",
            "benif_avail":"asdf",
            "family_mem_shg":"asdf",
            "join_shg":"asdf",
            "reason":"asdf",
            "shg_code":"asdf",
            "shg_member_code":"asdf",
            "entity_code":"asdf",
            "app_ver":"asdf",
            "created_on_app":"2023-04-18 17:58:33.667"}]}
*/
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String gp_Name;
    private String scheme_Name;
    private String ben_Id;
    private String lgd_GpCode;
    private String lgd_Villagecode;
    private String mobile_no;
    private String ben_availability;
    private String any_Familyinshg;
    private String willing_joinshg;
    private String reason;
    private String shg_Code;
    private String member_Code;
    private String village_Code;
    private String created_on;



    private String appVersion;
    private String syncFlag;


    public String getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(String syncFlag) {
        this.syncFlag = syncFlag;
    }
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getGp_Name() {
        return gp_Name;
    }

    public void setGp_Name(String gp_Name) {
        this.gp_Name = gp_Name;
    }

    public String getScheme_Name() {
        return scheme_Name;
    }

    public void setScheme_Name(String scheme_Name) {
        this.scheme_Name = scheme_Name;
    }

    public String getBen_Id() {
        return ben_Id;
    }

    public void setBen_Id(String ben_Id) {
        this.ben_Id = ben_Id;
    }

    public String getLgd_GpCode() {
        return lgd_GpCode;
    }

    public void setLgd_GpCode(String lgd_GpCode) {
        this.lgd_GpCode = lgd_GpCode;
    }

    public String getLgd_Villagecode() {
        return lgd_Villagecode;
    }

    public void setLgd_Villagecode(String lgd_Villagecode) {
        this.lgd_Villagecode = lgd_Villagecode;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getBen_availability() {
        return ben_availability;
    }

    public void setBen_availability(String ben_availability) {
        this.ben_availability = ben_availability;
    }

    public String getAny_Familyinshg() {
        return any_Familyinshg;
    }

    public void setAny_Familyinshg(String any_Familyinshg) {
        this.any_Familyinshg = any_Familyinshg;
    }

    public String getWilling_joinshg() {
        return willing_joinshg;
    }

    public void setWilling_joinshg(String willing_joinshg) {
        this.willing_joinshg = willing_joinshg;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getShg_Code() {
        return shg_Code;
    }

    public void setShg_Code(String shg_Code) {
        this.shg_Code = shg_Code;
    }

    public String getMember_Code() {
        return member_Code;
    }

    public void setMember_Code(String member_Code) {
        this.member_Code = member_Code;
    }

    public String getVillage_Code() {
        return village_Code;
    }

    public void setVillage_Code(String village_Code) {
        this.village_Code = village_Code;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public MemberEntryInfoEntity( String gp_Name, String scheme_Name, String ben_Id, String lgd_GpCode, String lgd_Villagecode, String mobile_no, String ben_availability, String any_Familyinshg, String willing_joinshg, String reason, String shg_Code, String member_Code, String village_Code, String created_on,String appVersion, String syncFlag) {
        this.gp_Name = gp_Name;
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
        this.created_on = created_on;
        this.appVersion = appVersion;
        this.syncFlag=syncFlag;
    }
}
