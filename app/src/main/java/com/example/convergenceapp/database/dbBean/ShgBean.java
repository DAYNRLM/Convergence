package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class ShgBean {
    @ColumnInfo(name = "shg_code")
    private String shgCode;

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

    public ShgBean(String shgCode, String shgName) {
        this.shgCode = shgCode;
        this.shgName = shgName;
    }

    @ColumnInfo(name = "shg_name")
    private String shgName;
}
