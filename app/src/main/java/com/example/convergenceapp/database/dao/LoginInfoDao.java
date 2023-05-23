package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.convergenceapp.database.entity.LoginInfoEntity;

import java.util.List;

@Dao
public interface LoginInfoDao {

    @Insert
    void insert(LoginInfoEntity loginInfoEntity);

    @Query("select distinct  state_short_name from logininfoentity")
    String getStateNameDB();
    @Query("select distinct language_id from loginInfoEntity")
    String getLanguageCode();

    @Query("delete from logininfoentity")
    void deleteAll();

    @Query("select distinct * from LoginInfoEntity")
    LoginInfoEntity getLoginData();
    @Query("select distinct login_id from loginInfoEntity")
    String getLoginId();
    @Query("select distinct server_date_time from loginInfoEntity")
    String getServerDate();


    @Query("select count(*) from(select * from logininfoentity)")
    String getLocalCount();


}
