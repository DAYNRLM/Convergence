package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class CheckAndDeleteBean {
    @ColumnInfo(name = "village_code")
    private String villageCode;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
}
