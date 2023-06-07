package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.BeneficiaryBean;
import com.example.convergenceapp.database.dbBean.MemberBean;
import com.example.convergenceapp.database.dbBean.NrlmDataBean;
import com.example.convergenceapp.database.dbBean.NrlmVillageBean;
import com.example.convergenceapp.database.dbBean.ShgBean;
import com.example.convergenceapp.database.entity.NrlmInfoEntity;

import java.util.List;

@Dao
public interface NrlmInfoDao {
    @Insert
    void insert(NrlmInfoEntity nrlmInfoEntity);

    @Query("select distinct gp_name,gp_code from NrlmInfoEntity order by gp_name ASC")
    List<NrlmDataBean> getNrlmList();

    @Query("select distinct village_name,village_code from NrlmInfoEntity where NrlmInfoEntity.gp_code =:gpCode order by village_name ASC")
    List<NrlmVillageBean> getNrlmVillage(String gpCode);


    @Query("select distinct shg_name,shg_code from NrlmInfoEntity where NrlmInfoEntity.village_name =:villageCode order by shg_name ASC")
    List<ShgBean> getShg(String villageCode);

    @Query("select distinct member_code,member_name,mobile_number,belonging_name,act_num from NrlmInfoEntity where NrlmInfoEntity.shg_code =:shgCode order by member_name ASC")
    List<MemberBean> getMember(String shgCode);

    @Query("select distinct mem_branch_code from NrlmInfoEntity where NrlmInfoEntity.member_code =:memCode order by member_name ASC")
    String getMemberbranchCode(String memCode);
    @Query("select distinct mem_bank_code from NrlmInfoEntity where NrlmInfoEntity.member_code =:memCode order by member_name ASC")
    String getMemberbankCode(String memCode);
    @Query("select distinct bank_flag from NrlmInfoEntity where NrlmInfoEntity.member_code =:memCode order by member_name ASC")
    String getMemberbankFlag(String memCode);


    @Query("delete from NrlmInfoEntity")
    void deleteAll();

    @Query("select distinct lgd_district_code from NrlmInfoEntity")
    String getDistrictCode();





}
