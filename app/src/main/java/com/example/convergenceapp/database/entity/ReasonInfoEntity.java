package com.example.convergenceapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ReasonInfoEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String reason;
    private String reasonCode;

    public int getId() {
        return id;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReasonInfoEntity(String reason, String reasonCode) {
        this.reason = reason;
        this.reasonCode = reasonCode;
    }
   /* public ReasonInfoEntity( String reason) {

        this.reason = reason;
    }*/
}
