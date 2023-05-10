package com.example.convergenceapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.convergenceapp.database.dbBean.BankMasterBean;
import com.example.convergenceapp.database.dbBean.BankNameAndBranchName;
import com.example.convergenceapp.database.entity.BankMasterEntity;

import java.util.List;

@Dao
public interface BankMasterDao {
    @Insert
    void insert(BankMasterEntity bankMasterEntity);

    @Query("select distinct bank_code,bank_name from BankMasterEntity order by bank_name ASC")
    List<BankMasterBean> getBankList();

    @Query("select distinct bank_branch_code,bank_branch_name,ifsc_code,alength from BankMasterEntity where BankMasterEntity.bank_code =:bankCode order by bank_branch_name ASC")
    List<BankMasterBean> getBranchList(String bankCode);

    @Query("select distinct bank_branch_name,bank_name,bank_code from BankMasterEntity where BankMasterEntity.bank_branch_code =:bankCode order by bank_branch_name ASC")
    List<BankNameAndBranchName> getBankNameAndBranchName(String bankCode);

    @Query("delete from BankMasterEntity")
    void deleteAll();


}
