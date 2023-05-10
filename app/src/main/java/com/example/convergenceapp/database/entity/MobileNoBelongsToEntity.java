package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MobileNoBelongsToEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String type_id,type_name;

    public MobileNoBelongsToEntity(String type_id, String type_name) {
        this.type_id = type_id;
        this.type_name = type_name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
