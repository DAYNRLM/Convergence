package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class MemberReasonBean {

    @ColumnInfo(name = "reason_id")
    private int reasonId;

    @ColumnInfo(name = "reason_name")
    private String reasonName;

    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }
}
