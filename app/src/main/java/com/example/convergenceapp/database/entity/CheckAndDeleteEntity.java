package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CheckAndDeleteEntity {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String village_code;

    public CheckAndDeleteEntity(String village_code) {
        this.village_code = village_code;
    }
}
