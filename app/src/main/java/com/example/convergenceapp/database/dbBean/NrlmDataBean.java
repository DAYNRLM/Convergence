package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class NrlmDataBean {
    @ColumnInfo(name = "reason")
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @ColumnInfo(name = "gp_name")
    private String gpName;
    @ColumnInfo(name = "gp_code")
    private String gpCode;
    @ColumnInfo(name = "village_name")
    private String villageName;
    @ColumnInfo(name = "village_code")
    private String villageCode;
    @ColumnInfo(name = "shg_code")
    private String shgCode;
    @ColumnInfo(name = "shg_name")
    private String shgName;

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getGpCode() {
        return gpCode;
    }

    public void setGpCode(String gpCode) {
        this.gpCode = gpCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getShgCode() {
        return shgCode;
    }

    public void setShgCode(String shgCode) {
        this.shgCode = shgCode;
    }

    public String getShgName() {
        return shgName;
    }

    public void setShgName(String shgName) {
        this.shgName = shgName;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @ColumnInfo(name = "member_code")
    private String memberCode;
    @ColumnInfo(name = "member_name")
    private String memberName;

}
