package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.MobileBelongsToBean;
import com.example.convergenceapp.database.entity.MobileNoBelongsToEntity;

import java.util.List;

@Dao
public interface MobileNoBelongsToDao {
    @Insert
    void insert(MobileNoBelongsToEntity mobileNoBelongsToEntity);

    @Query("select distinct type_id,type_name from MobileNoBelongsToEntity order by type_name ASC")
    List<MobileBelongsToBean> getMobileBelongsToList();



    @Query("delete from MobileNoBelongsToEntity")
    void deleteAll();



}
