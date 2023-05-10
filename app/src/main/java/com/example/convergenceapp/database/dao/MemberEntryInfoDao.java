package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.entity.MemberEntryInfoEntity;

import java.util.List;

@Dao
public interface MemberEntryInfoDao {
    @Insert
    public void insert(MemberEntryInfoEntity memberEntryInfoEntity);

    @Query("select * from MemberEntryInfoEntity where syncFlag=:flag")
    List<MemberEntryInfoEntity> getSyncData(String flag);

    @Query("update MemberEntryInfoEntity set syncFlag='1' where ben_Id=:benId")
    void setUpdateSyncFlag(String benId);

    @Query("select distinct ben_Id from MemberEntryInfoEntity")
    List<String> getBenIds();


    @Query("select count(*) from(select * from MemberEntryInfoEntity where syncFlag is 0)")
    String getLocalBenEntry();

}
