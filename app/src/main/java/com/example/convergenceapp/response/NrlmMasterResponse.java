package com.example.convergenceapp.response;

import com.google.gson.Gson;

import java.util.ArrayList;


public class NrlmMasterResponse {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int status;
    public class AssignDatum{


        public String getVillage_code() {
            return village_code;
        }

        public void setVillage_code(String village_code) {
            this.village_code = village_code;
        }

        public String getShg_code() {
            return shg_code;
        }

        public void setShg_code(String shg_code) {
            this.shg_code = shg_code;
        }

        public String getLgd_state_code() {
            return lgd_state_code;
        }

        public void setLgd_state_code(String lgd_state_code) {
            this.lgd_state_code = lgd_state_code;
        }

        public String getShg_member_code() {
            return shg_member_code;
        }

        public void setShg_member_code(String shg_member_code) {
            this.shg_member_code = shg_member_code;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getLgd_block_code() {
            return lgd_block_code;
        }

        public void setLgd_block_code(String lgd_block_code) {
            this.lgd_block_code = lgd_block_code;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getLgd_district_code() {
            return lgd_district_code;
        }

        public void setLgd_district_code(String lgd_district_code) {
            this.lgd_district_code = lgd_district_code;
        }

        public String getGrampanchayat_code() {
            return grampanchayat_code;
        }

        public void setGrampanchayat_code(String grampanchayat_code) {
            this.grampanchayat_code = grampanchayat_code;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }

        public String getBlock_name() {
            return block_name;
        }

        public void setBlock_name(String block_name) {
            this.block_name = block_name;
        }

        public String getLgd_gp_code() {
            return lgd_gp_code;
        }

        public void setLgd_gp_code(String lgd_gp_code) {
            this.lgd_gp_code = lgd_gp_code;
        }

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
        }

        public String getBlock_code() {
            return block_code;
        }

        public void setBlock_code(String block_code) {
            this.block_code = block_code;
        }

        public String getVillage_name() {
            return village_name;
        }

        public void setVillage_name(String village_name) {
            this.village_name = village_name;
        }

        public String getState_code() {
            return state_code;
        }

        public void setState_code(String state_code) {
            this.state_code = state_code;
        }

        public String getGrampanchayat_name() {
            return grampanchayat_name;
        }

        public void setGrampanchayat_name(String grampanchayat_name) {
            this.grampanchayat_name = grampanchayat_name;
        }

        public String member_name;
        public String lgd_district_code;
        public String grampanchayat_code;
        public String district_name;
        public String block_name;
        public String ac_no;
        public String bank_flag;

        public String getBank_flag() {
            return bank_flag;
        }

        public void setBank_flag(String bank_flag) {
            this.bank_flag = bank_flag;
        }

        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        public String getBelonging_name() {
            return belonging_name;
        }
        public String mem_bank_code;

        public String getMem_bank_code() {
            return mem_bank_code;
        }

        public void setMem_bank_code(String mem_bank_code) {
            this.mem_bank_code = mem_bank_code;
        }

        public String getAc_no() {
            return ac_no;
        }

        public void setAc_no(String ac_no) {
            this.ac_no = ac_no;
        }

        public void setBelonging_name(String belonging_name) {
            this.belonging_name = belonging_name;
        }

        public String lgd_gp_code;
        public String state_name;
        public String user_id;
        public String mem_branch_code;
        public String district_code;

        public String getMem_branch_code() {
            return mem_branch_code;
        }

        public void setMem_branch_code(String mem_branch_code) {
            this.mem_branch_code = mem_branch_code;
        }

        public String block_code;
        public String village_name;
        public String state_code;
        public String grampanchayat_name;
        public String village_code;
        public String shg_code;
        public String lgd_state_code;
        public String shg_member_code;
        public String group_name;
        public String lgd_block_code;
        public String mobile_number;
        public String belonging_name;
    }

    public class Data{
        public ArrayList<AssignDatum> assign_data;

        public ArrayList<AssignDatum> getAssign_data() {
            return assign_data;
        }

        public void setAssign_data(ArrayList<AssignDatum> assign_data) {
            this.assign_data = assign_data;
        }
    }





    public static NrlmMasterResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, NrlmMasterResponse.class);
    }
}





















