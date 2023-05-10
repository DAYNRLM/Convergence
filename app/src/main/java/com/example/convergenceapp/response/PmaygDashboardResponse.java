package com.example.convergenceapp.response;

import com.google.gson.Gson;

public class PmaygDashboardResponse {

    public Data data;
    public String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;




    public class Data{
        public String pending;
        public String village_allot;

        public String getPending() {
            return pending;
        }

        public void setPending(String pending) {
            this.pending = pending;
        }

        public String getVillage_allot() {
            return village_allot;
        }

        public void setVillage_allot(String village_allot) {
            this.village_allot = village_allot;
        }

        public String getCompleted() {
            return completed;
        }

        public void setCompleted(String completed) {
            this.completed = completed;
        }

        public String getGp_allot() {
            return gp_allot;
        }

        public void setGp_allot(String gp_allot) {
            this.gp_allot = gp_allot;
        }

        public String getTot_allot() {
            return tot_allot;
        }

        public void setTot_allot(String tot_allot) {
            this.tot_allot = tot_allot;
        }

        public String completed;
        public String gp_allot;
        public String tot_allot;
    }


    public static PmaygDashboardResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, PmaygDashboardResponse.class);
    }
}




