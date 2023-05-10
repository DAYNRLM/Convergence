package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.NrlmBenefeciaryMobileBean;
import com.example.convergenceapp.database.entity.NrlmBenefeciaryMobileEntity;

import java.util.List;

@Dao
public interface NrlmBenefeciaryMobileDao {
    @Insert
    void insert(NrlmBenefeciaryMobileEntity nrlmBenefeciaryMobileEntity);

    @Query("select * from NrlmBenefeciaryMobileEntity where syc_flag=:sycFlag")
    List<NrlmBenefeciaryMobileBean> getNrlmBenefeciaryMobileDataAcordingSyncFlag(String sycFlag);

    @Query("select mobile_number from NrlmBenefeciaryMobileEntity where NrlmBenefeciaryMobileEntity.village_code =:villageCode and nrlmBenefeciaryMobileEntity.member_code=:memberCode")
    List<NrlmBenefeciaryMobileBean> getNrlmBenefeciaryMobileNoByCode(String villageCode,String memberCode);

    @Query("update NrlmBenefeciaryMobileEntity SET shg_code = :shgCode and mobile_number = :mobileNo and mobile_belongs_to = :mobileBelongsTo and whether_part_of_shg = :whetherPartOfShg  and reason_of_discontinue = :reasonOfDiscontinue and bank_code = :bankCode and branch_code = :branchCode  and ifsc_code = :ifscCode and account_number = :accountNumber and syc_flag = :updatedBy and updated_date = :updatedDate where NrlmBenefeciaryMobileEntity.village_code = :villageCode and nrlmBenefeciaryMobileEntity.member_code = :memberCode")
    void updateNrlmBenefeciaryMobileData(String villageCode,String memberCode,String shgCode,String mobileNo,String mobileBelongsTo,String whetherPartOfShg ,String reasonOfDiscontinue ,String bankCode,String branchCode,String ifscCode,String accountNumber,String updatedBy,String updatedDate);

    @Query("update NrlmBenefeciaryMobileEntity set syc_flag='1' where member_code=:memberCode")
    void setUpdateSyncFlag(String memberCode);


    @Query("select count(*) from(select * from nrlmbenefeciarymobileentity where syc_flag is 0 )")
    String getLocalMobileEntry();


}
