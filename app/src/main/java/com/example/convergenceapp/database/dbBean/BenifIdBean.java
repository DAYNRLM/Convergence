package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class BenifIdBean {
    @ColumnInfo(name = "ben_Id")
    private String benId;

    public String getBenId() {
        return benId;
    }

    public void setBenId(String benId) {
        this.benId = benId;
    }
}
