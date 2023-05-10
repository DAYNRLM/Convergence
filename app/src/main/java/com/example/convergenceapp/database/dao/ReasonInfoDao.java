package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.MemberBean;
import com.example.convergenceapp.database.dbBean.ReasonBean;
import com.example.convergenceapp.database.entity.ReasonInfoEntity;

import java.util.List;
@Dao
public interface ReasonInfoDao {
    @Query("delete from ReasonInfoEntity")
    void deleteAll();
    @Insert
    void insert(ReasonInfoEntity reasonInfoEntity);
    @Query("select reason from ReasonInfoEntity order by reason ASC")
    List<ReasonBean> getReason();



}
