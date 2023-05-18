package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class VillageBean {
    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    @ColumnInfo(name = "nrlm_village_code")
    private String villageCode;
    @ColumnInfo(name = "village_name")
    private String villageName;
}
