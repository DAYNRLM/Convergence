package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.BeneficiaryBean;
import com.example.convergenceapp.database.dbBean.GpBean;
import com.example.convergenceapp.database.dbBean.OtherMembersName;
import com.example.convergenceapp.database.dbBean.VillageBean;
import com.example.convergenceapp.database.entity.PmaygInfoEntity;

import java.util.List;

@Dao
public interface PmaygInfoDao {
    @Insert
    void insert(PmaygInfoEntity pmaygInfoEntity);

    @Query("delete from PmaygInfoEntity")
    void deleteAll();


    @Query("select distinct gp_code,gp_name from PmaygInfoEntity order by gp_name ASC")
    List<GpBean> getGPList();
    @Query("select distinct village_code,village_name from PmaygInfoEntity where gp_name=:gpName order by village_name ASC ")
    List<VillageBean> getVillageList(String gpName);
    @Query("select distinct fathername,mothername,beneficiary_id,beneficiary_holder_name,beneficiary_acc_no,beneficiary_bank_name,beneficiary_branch_name,mobile_no,ifsc_code from PmaygInfoEntity where village_name=:villageCode and flag=:flag order by beneficiary_holder_name ASC")
    List<BeneficiaryBean> getBenDetailsList(String villageCode, String flag);

    @Query("select distinct member_name from PmaygInfoEntity where beneficiary_id=:beneficiaryId order by member_name ASC")
    List<OtherMembersName> getMemberData(String beneficiaryId);

    @Query("update PmaygInfoEntity set flag='1' where beneficiary_id=:beneficiaryId")
    void updateSyncFlag(String beneficiaryId);

    @Query("Select distinct gp_code from PmaygInfoEntity where gp_name=:gpName ")
    String getLgdCode(String gpName);

    @Query("select distinct village_code from PmaygInfoEntity where village_name=:villageName")
    String getViilageLgdCode(String villageName);




}
