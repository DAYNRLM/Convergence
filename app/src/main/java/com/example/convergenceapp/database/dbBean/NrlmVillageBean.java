package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class NrlmVillageBean {

    @ColumnInfo(name = "village_code")
    private String nrlmVillageCode;

    public String getNrlmVillageCode() {
        return nrlmVillageCode;
    }

    public void setNrlmVillageCode(String nrlmVillageCode) {
        this.nrlmVillageCode = nrlmVillageCode;
    }

    public String getNrlmVillageName() {
        return nrlmVillageName;
    }

    public void setNrlmVillageName(String nrlmVillageName) {
        this.nrlmVillageName = nrlmVillageName;
    }

    public NrlmVillageBean(String nrlmVillageCode, String nrlmVillageName) {
        this.nrlmVillageCode = nrlmVillageCode;
        this.nrlmVillageName = nrlmVillageName;
    }

    @ColumnInfo(name = "village_name")
    private String nrlmVillageName;
}
