package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class MemberBean {
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

    public MemberBean(String memberCode, String memberName) {
        this.memberCode = memberCode;
        this.memberName = memberName;
    }

    @ColumnInfo(name = "member_code")
    private String memberCode;
    @ColumnInfo(name = "member_name")
    private String memberName;

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getBelonging_name() {
        return belonging_name;
    }

    public void setBelonging_name(String belonging_name) {
        this.belonging_name = belonging_name;
    }

    @ColumnInfo(name = "mobile_number")
    private String mobile_number;
    @ColumnInfo(name = "belonging_name")
    private String belonging_name;

    @ColumnInfo(name = "act_num")
    private String actNum;

    public String getActNum() {
        return actNum;
    }

    public void setActNum(String actNum) {
        this.actNum = actNum;
    }
}
