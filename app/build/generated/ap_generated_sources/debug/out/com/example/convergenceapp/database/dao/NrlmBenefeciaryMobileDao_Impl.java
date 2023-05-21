package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.dbBean.MemberCodeBean;
import com.example.convergenceapp.database.dbBean.NrlmBenefeciaryMobileBean;
import com.example.convergenceapp.database.entity.NrlmBenefeciaryMobileEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NrlmBenefeciaryMobileDao_Impl implements NrlmBenefeciaryMobileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NrlmBenefeciaryMobileEntity> __insertionAdapterOfNrlmBenefeciaryMobileEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateNrlmBenefeciaryMobileData;

  private final SharedSQLiteStatement __preparedStmtOfSetUpdateSyncFlag;

  public NrlmBenefeciaryMobileDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNrlmBenefeciaryMobileEntity = new EntityInsertionAdapter<NrlmBenefeciaryMobileEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `NrlmBenefeciaryMobileEntity` (`id`,`gp_code`,`village_code`,`shg_code`,`member_code`,`mobile_number`,`mobile_belongs_to`,`whether_part_of_shg`,`reason_of_discontinue`,`bank_code`,`branch_code`,`ifsc_code`,`account_number`,`entered_by`,`entered_date`,`syc_flag`,`updated_date`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NrlmBenefeciaryMobileEntity value) {
        stmt.bindLong(1, value.id);
        if (value.getGp_code() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGp_code());
        }
        if (value.getVillage_code() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getVillage_code());
        }
        if (value.getShg_code() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getShg_code());
        }
        if (value.getMember_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMember_code());
        }
        if (value.getMobile_number() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMobile_number());
        }
        if (value.getMobile_belongs_to() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMobile_belongs_to());
        }
        if (value.getWhether_part_of_shg() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getWhether_part_of_shg());
        }
        if (value.getReason_of_discontinue() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getReason_of_discontinue());
        }
        if (value.getBank_code() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getBank_code());
        }
        if (value.getBranch_code() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getBranch_code());
        }
        if (value.getIfsc_code() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getIfsc_code());
        }
        if (value.getAccount_number() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getAccount_number());
        }
        if (value.getEntered_by() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getEntered_by());
        }
        if (value.getEntered_date() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getEntered_date());
        }
        if (value.getSyc_flag() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getSyc_flag());
        }
        if (value.getUpdated_date() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getUpdated_date());
        }
      }
    };
    this.__preparedStmtOfUpdateNrlmBenefeciaryMobileData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update NrlmBenefeciaryMobileEntity SET shg_code = ? and mobile_number = ? and mobile_belongs_to = ? and whether_part_of_shg = ?  and reason_of_discontinue = ? and bank_code = ? and branch_code = ?  and ifsc_code = ? and account_number = ? and syc_flag = ? and updated_date = ? where NrlmBenefeciaryMobileEntity.village_code = ? and nrlmBenefeciaryMobileEntity.member_code = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetUpdateSyncFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update NrlmBenefeciaryMobileEntity set syc_flag='1' where member_code=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final NrlmBenefeciaryMobileEntity nrlmBenefeciaryMobileEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNrlmBenefeciaryMobileEntity.insert(nrlmBenefeciaryMobileEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateNrlmBenefeciaryMobileData(final String villageCode, final String memberCode,
      final String shgCode, final String mobileNo, final String mobileBelongsTo,
      final String whetherPartOfShg, final String reasonOfDiscontinue, final String bankCode,
      final String branchCode, final String ifscCode, final String accountNumber,
      final String updatedBy, final String updatedDate) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateNrlmBenefeciaryMobileData.acquire();
    int _argIndex = 1;
    if (shgCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, shgCode);
    }
    _argIndex = 2;
    if (mobileNo == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, mobileNo);
    }
    _argIndex = 3;
    if (mobileBelongsTo == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, mobileBelongsTo);
    }
    _argIndex = 4;
    if (whetherPartOfShg == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, whetherPartOfShg);
    }
    _argIndex = 5;
    if (reasonOfDiscontinue == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, reasonOfDiscontinue);
    }
    _argIndex = 6;
    if (bankCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, bankCode);
    }
    _argIndex = 7;
    if (branchCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, branchCode);
    }
    _argIndex = 8;
    if (ifscCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, ifscCode);
    }
    _argIndex = 9;
    if (accountNumber == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, accountNumber);
    }
    _argIndex = 10;
    if (updatedBy == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, updatedBy);
    }
    _argIndex = 11;
    if (updatedDate == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, updatedDate);
    }
    _argIndex = 12;
    if (villageCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, villageCode);
    }
    _argIndex = 13;
    if (memberCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, memberCode);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateNrlmBenefeciaryMobileData.release(_stmt);
    }
  }

  @Override
  public void setUpdateSyncFlag(final String memberCode) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetUpdateSyncFlag.acquire();
    int _argIndex = 1;
    if (memberCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, memberCode);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetUpdateSyncFlag.release(_stmt);
    }
  }

  @Override
  public List<NrlmBenefeciaryMobileBean> getNrlmBenefeciaryMobileDataAcordingSyncFlag(
      final String sycFlag) {
    final String _sql = "select distinct * from NrlmBenefeciaryMobileEntity where syc_flag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sycFlag == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sycFlag);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGpCode = CursorUtil.getColumnIndexOrThrow(_cursor, "gp_code");
      final int _cursorIndexOfVillageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "village_code");
      final int _cursorIndexOfShgCode = CursorUtil.getColumnIndexOrThrow(_cursor, "shg_code");
      final int _cursorIndexOfMemberCode = CursorUtil.getColumnIndexOrThrow(_cursor, "member_code");
      final int _cursorIndexOfMobileNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "mobile_number");
      final int _cursorIndexOfMobileBelongsTo = CursorUtil.getColumnIndexOrThrow(_cursor, "mobile_belongs_to");
      final int _cursorIndexOfWhetherPartOfShg = CursorUtil.getColumnIndexOrThrow(_cursor, "whether_part_of_shg");
      final int _cursorIndexOfReasonOfDiscontinue = CursorUtil.getColumnIndexOrThrow(_cursor, "reason_of_discontinue");
      final int _cursorIndexOfBankCode = CursorUtil.getColumnIndexOrThrow(_cursor, "bank_code");
      final int _cursorIndexOfBranchCode = CursorUtil.getColumnIndexOrThrow(_cursor, "branch_code");
      final int _cursorIndexOfIfscCode = CursorUtil.getColumnIndexOrThrow(_cursor, "ifsc_code");
      final int _cursorIndexOfAccountNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "account_number");
      final int _cursorIndexOfEnteredBy = CursorUtil.getColumnIndexOrThrow(_cursor, "entered_by");
      final int _cursorIndexOfEnteredDate = CursorUtil.getColumnIndexOrThrow(_cursor, "entered_date");
      final int _cursorIndexOfUpdatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_date");
      final List<NrlmBenefeciaryMobileBean> _result = new ArrayList<NrlmBenefeciaryMobileBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NrlmBenefeciaryMobileBean _item;
        _item = new NrlmBenefeciaryMobileBean();
        final String _tmpGp_code;
        if (_cursor.isNull(_cursorIndexOfGpCode)) {
          _tmpGp_code = null;
        } else {
          _tmpGp_code = _cursor.getString(_cursorIndexOfGpCode);
        }
        _item.setGp_code(_tmpGp_code);
        final String _tmpVillage_code;
        if (_cursor.isNull(_cursorIndexOfVillageCode)) {
          _tmpVillage_code = null;
        } else {
          _tmpVillage_code = _cursor.getString(_cursorIndexOfVillageCode);
        }
        _item.setVillage_code(_tmpVillage_code);
        final String _tmpShg_code;
        if (_cursor.isNull(_cursorIndexOfShgCode)) {
          _tmpShg_code = null;
        } else {
          _tmpShg_code = _cursor.getString(_cursorIndexOfShgCode);
        }
        _item.setShg_code(_tmpShg_code);
        final String _tmpMember_code;
        if (_cursor.isNull(_cursorIndexOfMemberCode)) {
          _tmpMember_code = null;
        } else {
          _tmpMember_code = _cursor.getString(_cursorIndexOfMemberCode);
        }
        _item.setMember_code(_tmpMember_code);
        final String _tmpMobile_number;
        if (_cursor.isNull(_cursorIndexOfMobileNumber)) {
          _tmpMobile_number = null;
        } else {
          _tmpMobile_number = _cursor.getString(_cursorIndexOfMobileNumber);
        }
        _item.setMobile_number(_tmpMobile_number);
        final String _tmpMobile_belongs_to;
        if (_cursor.isNull(_cursorIndexOfMobileBelongsTo)) {
          _tmpMobile_belongs_to = null;
        } else {
          _tmpMobile_belongs_to = _cursor.getString(_cursorIndexOfMobileBelongsTo);
        }
        _item.setMobile_belongs_to(_tmpMobile_belongs_to);
        final String _tmpWhether_part_of_shg;
        if (_cursor.isNull(_cursorIndexOfWhetherPartOfShg)) {
          _tmpWhether_part_of_shg = null;
        } else {
          _tmpWhether_part_of_shg = _cursor.getString(_cursorIndexOfWhetherPartOfShg);
        }
        _item.setWhether_part_of_shg(_tmpWhether_part_of_shg);
        final String _tmpReason_of_discontinue;
        if (_cursor.isNull(_cursorIndexOfReasonOfDiscontinue)) {
          _tmpReason_of_discontinue = null;
        } else {
          _tmpReason_of_discontinue = _cursor.getString(_cursorIndexOfReasonOfDiscontinue);
        }
        _item.setReason_of_discontinue(_tmpReason_of_discontinue);
        final String _tmpBank_code;
        if (_cursor.isNull(_cursorIndexOfBankCode)) {
          _tmpBank_code = null;
        } else {
          _tmpBank_code = _cursor.getString(_cursorIndexOfBankCode);
        }
        _item.setBank_code(_tmpBank_code);
        final String _tmpBranch_code;
        if (_cursor.isNull(_cursorIndexOfBranchCode)) {
          _tmpBranch_code = null;
        } else {
          _tmpBranch_code = _cursor.getString(_cursorIndexOfBranchCode);
        }
        _item.setBranch_code(_tmpBranch_code);
        final String _tmpIfsc_code;
        if (_cursor.isNull(_cursorIndexOfIfscCode)) {
          _tmpIfsc_code = null;
        } else {
          _tmpIfsc_code = _cursor.getString(_cursorIndexOfIfscCode);
        }
        _item.setIfsc_code(_tmpIfsc_code);
        final String _tmpAccount_number;
        if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
          _tmpAccount_number = null;
        } else {
          _tmpAccount_number = _cursor.getString(_cursorIndexOfAccountNumber);
        }
        _item.setAccount_number(_tmpAccount_number);
        final String _tmpEntered_by;
        if (_cursor.isNull(_cursorIndexOfEnteredBy)) {
          _tmpEntered_by = null;
        } else {
          _tmpEntered_by = _cursor.getString(_cursorIndexOfEnteredBy);
        }
        _item.setEntered_by(_tmpEntered_by);
        final String _tmpEntered_date;
        if (_cursor.isNull(_cursorIndexOfEnteredDate)) {
          _tmpEntered_date = null;
        } else {
          _tmpEntered_date = _cursor.getString(_cursorIndexOfEnteredDate);
        }
        _item.setEntered_date(_tmpEntered_date);
        final String _tmpUpdated_date;
        if (_cursor.isNull(_cursorIndexOfUpdatedDate)) {
          _tmpUpdated_date = null;
        } else {
          _tmpUpdated_date = _cursor.getString(_cursorIndexOfUpdatedDate);
        }
        _item.setUpdated_date(_tmpUpdated_date);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<NrlmBenefeciaryMobileBean> getNrlmBenefeciaryMobileNoByCode(final String villageCode,
      final String memberCode) {
    final String _sql = "select mobile_number from NrlmBenefeciaryMobileEntity where NrlmBenefeciaryMobileEntity.village_code =? and nrlmBenefeciaryMobileEntity.member_code=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (villageCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, villageCode);
    }
    _argIndex = 2;
    if (memberCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, memberCode);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMobileNumber = 0;
      final List<NrlmBenefeciaryMobileBean> _result = new ArrayList<NrlmBenefeciaryMobileBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NrlmBenefeciaryMobileBean _item;
        _item = new NrlmBenefeciaryMobileBean();
        final String _tmpMobile_number;
        if (_cursor.isNull(_cursorIndexOfMobileNumber)) {
          _tmpMobile_number = null;
        } else {
          _tmpMobile_number = _cursor.getString(_cursorIndexOfMobileNumber);
        }
        _item.setMobile_number(_tmpMobile_number);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getLocalMobileEntry() {
    final String _sql = "select count(*) from(select * from nrlmbenefeciarymobileentity where syc_flag is 0 )";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        final String _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(0);
        }
        _result = _tmp;
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
  public List<MemberCodeBean> getNrlmMemberCode() {
    final String _sql = "select distinct member_code from NrlmBenefeciaryMobileEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMemberCode = 0;
      final List<MemberCodeBean> _result = new ArrayList<MemberCodeBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MemberCodeBean _item;
        _item = new MemberCodeBean();
        final String _tmpMemberCode;
        if (_cursor.isNull(_cursorIndexOfMemberCode)) {
          _tmpMemberCode = null;
        } else {
          _tmpMemberCode = _cursor.getString(_cursorIndexOfMemberCode);
        }
        _item.setMemberCode(_tmpMemberCode);
        _result.add(_item);
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
