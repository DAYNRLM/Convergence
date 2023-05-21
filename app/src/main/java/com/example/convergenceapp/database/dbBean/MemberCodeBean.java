package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class MemberCodeBean {

    @ColumnInfo(name = "member_code")
    private String memberCode;

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }
}
