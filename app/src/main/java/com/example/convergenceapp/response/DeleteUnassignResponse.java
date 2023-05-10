package com.example.convergenceapp.response;

import com.google.gson.Gson;

public class DeleteUnassignResponse {
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

        }

    public static DeleteUnassignResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, DeleteUnassignResponse.class);
    }
}
