package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.MemberReasonBean;
import com.example.convergenceapp.database.entity.MemberReasonEntity;

import java.util.List;

@Dao
public interface MemberReasonDao {
    @Insert
    void insert(MemberReasonEntity memberReasonEntity);

    @Query("select distinct reason_id,reason_name from MemberReasonEntity order by reason_name ASC")
    List<MemberReasonBean> getMemberReasonList();

    @Query("delete from MemberReasonEntity")
    void deleteAll();

}
