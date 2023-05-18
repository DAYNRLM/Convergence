package com.example.convergenceapp.response;

import com.google.gson.Gson;

import java.util.ArrayList;

import kotlin.jvm.Synchronized;

public class PmaygMasterResponse {
    public Data data;
    public String message;
    public int status;

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

    public static class AssignDatum{
        public String reg_no;
        public String beneficiaryname;
        public String familydet_name;
        public String grampanchayatcode;
        public String nrlm_village_code;
        public String statename;

        public String getNrlm_village_code() {
            return nrlm_village_code;
        }

        public void setNrlm_village_code(String nrlm_village_code) {
            this.nrlm_village_code = nrlm_village_code;
        }

        public String mobileno;
        public String scheme;
        public String flag_sync;
        public String statecode;
        public String fathername;
        public String blockname;
        public String sl_no_member;
        public String districtname;
        public String accountno;
        public String familydet_relation;
        public String grampanchayatname;
        public String mothername;
        public String bankname;
        public String districtcode;
        public String villagecode;
        public String blockcode;
        public String villagename;
        public String branchname;
        public String ifsc_code;

        public String getIfsc_code() {
            return ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }


        public String getGrampanchayatcode() {
            return grampanchayatcode;
        }

        public void setGrampanchayatcode(String grampanchayatcode) {
            this.grampanchayatcode = grampanchayatcode;
        }


        public String getFlag_sync() {
            return flag_sync;
        }

        public void setFlag_sync(String flag_sync) {
            this.flag_sync = flag_sync;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }


        public String getReg_no() {
            return reg_no;
        }

        public void setReg_no(String reg_no) {
            this.reg_no = reg_no;
        }

        public String getBeneficiaryname() {
            return beneficiaryname;
        }

        public void setBeneficiaryname(String beneficiaryname) {
            this.beneficiaryname = beneficiaryname;
        }

        public String getFamilydet_name() {
            return familydet_name;
        }

        public void setFamilydet_name(String familydet_name) {
            this.familydet_name = familydet_name;
        }



        public String getStatename() {
            return statename;
        }

        public void setStatename(String statename) {
            this.statename = statename;
        }

        public String getMobileno() {
            return mobileno;
        }

        public void setMobileno(String mobileno) {
            this.mobileno = mobileno;
        }

        public String getStatecode() {
            return statecode;
        }

        public void setStatecode(String statecode) {
            this.statecode = statecode;
        }

        public String getFathername() {
            return fathername;
        }

        public void setFathername(String fathername) {
            this.fathername = fathername;
        }

        public String getBlockname() {
            return blockname;
        }

        public void setBlockname(String blockname) {
            this.blockname = blockname;
        }

        public String getSl_no_member() {
            return sl_no_member;
        }

        public void setSl_no_member(String sl_no_member) {
            this.sl_no_member = sl_no_member;
        }

        public String getDistrictname() {
            return districtname;
        }

        public void setDistrictname(String districtname) {
            this.districtname = districtname;
        }

        public String getAccountno() {
            return accountno;
        }

        public void setAccountno(String accountno) {
            this.accountno = accountno;
        }

        public String getFamilydet_relation() {
            return familydet_relation;
        }

        public void setFamilydet_relation(String familydet_relation) {
            this.familydet_relation = familydet_relation;
        }

        public String getGrampanchayatname() {
            return grampanchayatname;
        }

        public void setGrampanchayatname(String grampanchayatname) {
            this.grampanchayatname = grampanchayatname;
        }

        public String getMothername() {
            return mothername;
        }

        public void setMothername(String mothername) {
            this.mothername = mothername;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getDistrictcode() {
            return districtcode;
        }

        public void setDistrictcode(String districtcode) {
            this.districtcode = districtcode;
        }

        public String getVillagecode() {
            return villagecode;
        }

        public void setVillagecode(String villagecode) {
            this.villagecode = villagecode;
        }

        public String getBlockcode() {
            return blockcode;
        }

        public void setBlockcode(String blockcode) {
            this.blockcode = blockcode;
        }

        public String getVillagename() {
            return villagename;
        }

        public void setVillagename(String villagename) {
            this.villagename = villagename;
        }

        public String getBranchname() {
            return branchname;
        }

        public void setBranchname(String branchname) {
            this.branchname = branchname;
        }

    }

    public static class Data{
        public ArrayList<AssignDatum> getAssign_data() {
            return assign_data;
        }

        public void setAssign_data(ArrayList<AssignDatum> assign_data) {
            this.assign_data = assign_data;
        }

        public ArrayList<AssignDatum> assign_data;
    }



    public static PmaygMasterResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, PmaygMasterResponse.class);
    }


}
