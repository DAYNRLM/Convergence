package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.dbBean.BeneficiaryBean;
import com.example.convergenceapp.database.dbBean.GpBean;
import com.example.convergenceapp.database.dbBean.OtherMembersName;
import com.example.convergenceapp.database.dbBean.VillageBean;
import com.example.convergenceapp.database.entity.PmaygInfoEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PmaygInfoDao_Impl implements PmaygInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PmaygInfoEntity> __insertionAdapterOfPmaygInfoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSyncFlag;

  public PmaygInfoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPmaygInfoEntity = new EntityInsertionAdapter<PmaygInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PmaygInfoEntity` (`id`,`gp_code`,`flag`,`gp_name`,`village_code`,`village_name`,`scheme`,`beneficiary_holder_name`,`beneficiary_id`,`nrlm_village_code`,`ifsc_code`,`beneficiary_acc_no`,`beneficiary_bank_name`,`beneficiary_branch_name`,`mobile_no`,`member_name`,`holder_sync_flag`,`mothername`,`districtname`,`blockcode`,`districtcode`,`statecode`,`fathername`,`blockname`,`sl_no_member`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PmaygInfoEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getGp_code() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGp_code());
        }
        if (value.getFlag() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFlag());
        }
        if (value.getGp_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGp_name());
        }
        if (value.getVillage_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getVillage_code());
        }
        if (value.getVillage_name() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getVillage_name());
        }
        if (value.getScheme() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getScheme());
        }
        if (value.getBeneficiary_holder_name() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getBeneficiary_holder_name());
        }
        if (value.getBeneficiary_id() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getBeneficiary_id());
        }
        if (value.getNrlm_village_code() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getNrlm_village_code());
        }
        if (value.getIfsc_code() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getIfsc_code());
        }
        if (value.getBeneficiary_acc_no() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getBeneficiary_acc_no());
        }
        if (value.getBeneficiary_bank_name() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getBeneficiary_bank_name());
        }
        if (value.getBeneficiary_branch_name() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getBeneficiary_branch_name());
        }
        if (value.getMobile_no() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getMobile_no());
        }
        if (value.getMember_name() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getMember_name());
        }
        if (value.getHolder_sync_flag() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getHolder_sync_flag());
        }
        if (value.getMothername() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getMothername());
        }
        if (value.getDistrictname() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getDistrictname());
        }
        if (value.getBlockcode() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getBlockcode());
        }
        if (value.getDistrictcode() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getDistrictcode());
        }
        if (value.getStatecode() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getStatecode());
        }
        if (value.getFathername() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindString(23, value.getFathername());
        }
        if (value.getBlockname() == null) {
          stmt.bindNull(24);
        } else {
          stmt.bindString(24, value.getBlockname());
        }
        if (value.getSl_no_member() == null) {
          stmt.bindNull(25);
        } else {
          stmt.bindString(25, value.getSl_no_member());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from PmaygInfoEntity";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSyncFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update PmaygInfoEntity set flag='1' where beneficiary_id=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final PmaygInfoEntity pmaygInfoEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPmaygInfoEntity.insert(pmaygInfoEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void updateSyncFlag(final String beneficiaryId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSyncFlag.acquire();
    int _argIndex = 1;
    if (beneficiaryId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, beneficiaryId);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateSyncFlag.release(_stmt);
    }
  }

  @Override
  public List<GpBean> getGPList() {
    final String _sql = "select distinct gp_code,gp_name from PmaygInfoEntity order by gp_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGpCode = 0;
      final int _cursorIndexOfGpName = 1;
      final List<GpBean> _result = new ArrayList<GpBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GpBean _item;
        _item = new GpBean();
        final String _tmpGpCode;
        if (_cursor.isNull(_cursorIndexOfGpCode)) {
          _tmpGpCode = null;
        } else {
          _tmpGpCode = _cursor.getString(_cursorIndexOfGpCode);
        }
        _item.setGpCode(_tmpGpCode);
        final String _tmpGpName;
        if (_cursor.isNull(_cursorIndexOfGpName)) {
          _tmpGpName = null;
        } else {
          _tmpGpName = _cursor.getString(_cursorIndexOfGpName);
        }
        _item.setGpName(_tmpGpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<VillageBean> getVillageList(final String gpName) {
    final String _sql = "select distinct nrlm_village_code,village_name from PmaygInfoEntity where gp_name=? order by village_name ASC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gpName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gpName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVillageCode = 0;
      final int _cursorIndexOfVillageName = 1;
      final List<VillageBean> _result = new ArrayList<VillageBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final VillageBean _item;
        _item = new VillageBean();
        final String _tmpVillageCode;
        if (_cursor.isNull(_cursorIndexOfVillageCode)) {
          _tmpVillageCode = null;
        } else {
          _tmpVillageCode = _cursor.getString(_cursorIndexOfVillageCode);
        }
        _item.setVillageCode(_tmpVillageCode);
        final String _tmpVillageName;
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _tmpVillageName = null;
        } else {
          _tmpVillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        _item.setVillageName(_tmpVillageName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<BeneficiaryBean> getBenDetailsList(final String villageCode, final String flag) {
    final String _sql = "select distinct fathername,mothername,beneficiary_id,beneficiary_holder_name,beneficiary_acc_no,beneficiary_bank_name,beneficiary_branch_name,mobile_no,ifsc_code from PmaygInfoEntity where village_name=? and flag=? order by beneficiary_holder_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (villageCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, villageCode);
    }
    _argIndex = 2;
    if (flag == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, flag);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFatherName = 0;
      final int _cursorIndexOfMotherName = 1;
      final int _cursorIndexOfBenId = 2;
      final int _cursorIndexOfBenName = 3;
      final int _cursorIndexOfBenAccNo = 4;
      final int _cursorIndexOfBenBankName = 5;
      final int _cursorIndexOfBenBranchName = 6;
      final int _cursorIndexOfMobileNumber = 7;
      final int _cursorIndexOfIfscCode = 8;
      final List<BeneficiaryBean> _result = new ArrayList<BeneficiaryBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BeneficiaryBean _item;
        _item = new BeneficiaryBean();
        final String _tmpFatherName;
        if (_cursor.isNull(_cursorIndexOfFatherName)) {
          _tmpFatherName = null;
        } else {
          _tmpFatherName = _cursor.getString(_cursorIndexOfFatherName);
        }
        _item.setFatherName(_tmpFatherName);
        final String _tmpMotherName;
        if (_cursor.isNull(_cursorIndexOfMotherName)) {
          _tmpMotherName = null;
        } else {
          _tmpMotherName = _cursor.getString(_cursorIndexOfMotherName);
        }
        _item.setMotherName(_tmpMotherName);
        final String _tmpBenId;
        if (_cursor.isNull(_cursorIndexOfBenId)) {
          _tmpBenId = null;
        } else {
          _tmpBenId = _cursor.getString(_cursorIndexOfBenId);
        }
        _item.setBenId(_tmpBenId);
        final String _tmpBenName;
        if (_cursor.isNull(_cursorIndexOfBenName)) {
          _tmpBenName = null;
        } else {
          _tmpBenName = _cursor.getString(_cursorIndexOfBenName);
        }
        _item.setBenName(_tmpBenName);
        final String _tmpBenAccNo;
        if (_cursor.isNull(_cursorIndexOfBenAccNo)) {
          _tmpBenAccNo = null;
        } else {
          _tmpBenAccNo = _cursor.getString(_cursorIndexOfBenAccNo);
        }
        _item.setBenAccNo(_tmpBenAccNo);
        final String _tmpBenBankName;
        if (_cursor.isNull(_cursorIndexOfBenBankName)) {
          _tmpBenBankName = null;
        } else {
          _tmpBenBankName = _cursor.getString(_cursorIndexOfBenBankName);
        }
        _item.setBenBankName(_tmpBenBankName);
        final String _tmpBenBranchName;
        if (_cursor.isNull(_cursorIndexOfBenBranchName)) {
          _tmpBenBranchName = null;
        } else {
          _tmpBenBranchName = _cursor.getString(_cursorIndexOfBenBranchName);
        }
        _item.setBenBranchName(_tmpBenBranchName);
        final String _tmpMobileNumber;
        if (_cursor.isNull(_cursorIndexOfMobileNumber)) {
          _tmpMobileNumber = null;
        } else {
          _tmpMobileNumber = _cursor.getString(_cursorIndexOfMobileNumber);
        }
        _item.setMobileNumber(_tmpMobileNumber);
        final String _tmpIfsc_code;
        if (_cursor.isNull(_cursorIndexOfIfscCode)) {
          _tmpIfsc_code = null;
        } else {
          _tmpIfsc_code = _cursor.getString(_cursorIndexOfIfscCode);
        }
        _item.setIfsc_code(_tmpIfsc_code);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<OtherMembersName> getMemberData(final String beneficiaryId) {
    final String _sql = "select distinct member_name from PmaygInfoEntity where beneficiary_id=? order by member_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (beneficiaryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, beneficiaryId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMembersName = 0;
      final List<OtherMembersName> _result = new ArrayList<OtherMembersName>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final OtherMembersName _item;
        _item = new OtherMembersName();
        final String _tmpMembersName;
        if (_cursor.isNull(_cursorIndexOfMembersName)) {
          _tmpMembersName = null;
        } else {
          _tmpMembersName = _cursor.getString(_cursorIndexOfMembersName);
        }
        _item.setMembersName(_tmpMembersName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getLgdCode(final String gpName) {
    final String _sql = "Select distinct gp_code from PmaygInfoEntity where gp_name=? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gpName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gpName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getViilageLgdCode(final String villageName) {
    final String _sql = "select distinct village_code from PmaygInfoEntity where village_name=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (villageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, villageName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
