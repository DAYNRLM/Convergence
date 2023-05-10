package com.example.convergenceapp.response;

import com.google.gson.Gson;

import java.util.ArrayList;

public class UnassignResponse {
    public ArrayList<Datum> data;

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public class Datum{
        public String village_code;
        public String village_status;

        public String getVillage_code() {
            return village_code;
        }

        public void setVillage_code(String village_code) {
            this.village_code = village_code;
        }

        public String getVillage_status() {
            return village_status;
        }

        public void setVillage_status(String village_status) {
            this.village_status = village_status;
        }
    }

    public static UnassignResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, UnassignResponse.class);
    }
}

