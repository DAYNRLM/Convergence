package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemberReasonEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int reason_id;
    private String reason_name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getReason_id() {
        return reason_id;
    }

    public void setReason_id(int reason_id) {
        this.reason_id = reason_id;
    }

    public MemberReasonEntity(int reason_id, String reason_name) {
        this.reason_id = reason_id;
        this.reason_name = reason_name;
    }

    public String getReason_name() {
        return reason_name;
    }

    public void setReason_name(String reason_name) {
        this.reason_name = reason_name;
    }
}