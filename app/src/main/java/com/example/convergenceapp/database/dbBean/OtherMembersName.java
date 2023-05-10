package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class OtherMembersName {
    public String getMembersName() {
        return membersName;
    }

    public void setMembersName(String membersName) {
        this.membersName = membersName;
    }

    @ColumnInfo(name="member_name")
    private String membersName;


}
