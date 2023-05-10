package com.example.convergenceapp.request;

import com.example.convergenceapp.database.dbBean.BeneficiaryBean;

import java.util.ArrayList;
import java.util.List;

public  class SyncRequest {
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImei_no() {
        return imei_no;
    }

    public void setImei_no(String imei_no) {
        this.imei_no = imei_no;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getLocation_coordinate() {
        return location_coordinate;
    }

    public void setLocation_coordinate(String location_coordinate) {
        this.location_coordinate = location_coordinate;
    }

    public ArrayList<BeneficiaryDetails> getBenficiary_dtl() {
        return benficiary_dtl;
    }

    public void setBenficiary_dtl(ArrayList<BeneficiaryDetails> benficiary_dtl) {
        this.benficiary_dtl = benficiary_dtl;
    }

    public String user_id;
    public String imei_no;
    public String device_name;
    public String location_coordinate;


    public ArrayList<BeneficiaryDetails> benficiary_dtl;






}


