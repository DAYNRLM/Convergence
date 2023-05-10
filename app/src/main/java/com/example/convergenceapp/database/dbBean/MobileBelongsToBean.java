package com.example.convergenceapp.database.dbBean;

import androidx.room.ColumnInfo;

public class MobileBelongsToBean {

    @ColumnInfo(name = "type_id")
    private String typeId;

    @ColumnInfo(name = "type_name")
    private String typeName;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
