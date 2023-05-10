package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.CheckAndDeleteBean;
import com.example.convergenceapp.database.entity.CheckAndDeleteEntity;

import java.util.List;

@Dao
public interface CheckAndDeleteDao {

    @Insert
    void insert(CheckAndDeleteEntity checkAndDeleteEntity);

    @Query("select distinct village_code from CheckAndDeleteEntity")
    List<CheckAndDeleteBean> getVillageCodeList();


    @Query("delete from CheckAndDeleteEntity")
    void deleteAll();
}
