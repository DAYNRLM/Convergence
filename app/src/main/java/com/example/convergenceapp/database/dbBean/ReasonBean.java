package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class ReasonBean {
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    @ColumnInfo(name = "reason")
    private String reason;

    public ReasonBean(String reason, String reason_id) {
        this.reason = reason;
        this.reason_id = reason_id;
    }

    public String getReason_id() {
        return reason_id;
    }

    public void setReason_id(String reason_id) {
        this.reason_id = reason_id;
    }

    @ColumnInfo(name = "reason_id")
    private String reason_id;
}
