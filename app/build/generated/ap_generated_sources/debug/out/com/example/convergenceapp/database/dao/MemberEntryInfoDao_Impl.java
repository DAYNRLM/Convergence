package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.entity.MemberEntryInfoEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MemberEntryInfoDao_Impl implements MemberEntryInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MemberEntryInfoEntity> __insertionAdapterOfMemberEntryInfoEntity;

  private final SharedSQLiteStatement __preparedStmtOfSetUpdateSyncFlag;

  public MemberEntryInfoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMemberEntryInfoEntity = new EntityInsertionAdapter<MemberEntryInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MemberEntryInfoEntity` (`id`,`gp_Name`,`scheme_Name`,`ben_Id`,`lgd_GpCode`,`lgd_Villagecode`,`mobile_no`,`ben_availability`,`any_Familyinshg`,`willing_joinshg`,`reason`,`shg_Code`,`member_Code`,`village_Code`,`created_on`,`appVersion`,`syncFlag`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MemberEntryInfoEntity value) {
        stmt.bindLong(1, value.id);
        if (value.getGp_Name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGp_Name());
        }
        if (value.getScheme_Name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getScheme_Name());
        }
        if (value.getBen_Id() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBen_Id());
        }
        if (value.getLgd_GpCode() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLgd_GpCode());
        }
        if (value.getLgd_Villagecode() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getLgd_Villagecode());
        }
        if (value.getMobile_no() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMobile_no());
        }
        if (value.getBen_availability() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getBen_availability());
        }
        if (value.getAny_Familyinshg() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getAny_Familyinshg());
        }
        if (value.getWilling_joinshg() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getWilling_joinshg());
        }
        if (value.getReason() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getReason());
        }
        if (value.getShg_Code() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getShg_Code());
        }
        if (value.getMember_Code() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getMember_Code());
        }
        if (value.getVillage_Code() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getVillage_Code());
        }
        if (value.getCreated_on() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCreated_on());
        }
        if (value.getAppVersion() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getAppVersion());
        }
        if (value.getSyncFlag() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getSyncFlag());
        }
      }
    };
    this.__preparedStmtOfSetUpdateSyncFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update MemberEntryInfoEntity set syncFlag='1' where ben_Id=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final MemberEntryInfoEntity memberEntryInfoEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMemberEntryInfoEntity.insert(memberEntryInfoEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setUpdateSyncFlag(final String benId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetUpdateSyncFlag.acquire();
    int _argIndex = 1;
    if (benId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, benId);
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
  public List<MemberEntryInfoEntity> getSyncData(final String flag) {
    final String _sql = "select * from MemberEntryInfoEntity where syncFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (flag == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, flag);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfGpName = CursorUtil.getColumnIndexOrThrow(_cursor, "gp_Name");
      final int _cursorIndexOfSchemeName = CursorUtil.getColumnIndexOrThrow(_cursor, "scheme_Name");
      final int _cursorIndexOfBenId = CursorUtil.getColumnIndexOrThrow(_cursor, "ben_Id");
      final int _cursorIndexOfLgdGpCode = CursorUtil.getColumnIndexOrThrow(_cursor, "lgd_GpCode");
      final int _cursorIndexOfLgdVillagecode = CursorUtil.getColumnIndexOrThrow(_cursor, "lgd_Villagecode");
      final int _cursorIndexOfMobileNo = CursorUtil.getColumnIndexOrThrow(_cursor, "mobile_no");
      final int _cursorIndexOfBenAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "ben_availability");
      final int _cursorIndexOfAnyFamilyinshg = CursorUtil.getColumnIndexOrThrow(_cursor, "any_Familyinshg");
      final int _cursorIndexOfWillingJoinshg = CursorUtil.getColumnIndexOrThrow(_cursor, "willing_joinshg");
      final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
      final int _cursorIndexOfShgCode = CursorUtil.getColumnIndexOrThrow(_cursor, "shg_Code");
      final int _cursorIndexOfMemberCode = CursorUtil.getColumnIndexOrThrow(_cursor, "member_Code");
      final int _cursorIndexOfVillageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "village_Code");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "created_on");
      final int _cursorIndexOfAppVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "appVersion");
      final int _cursorIndexOfSyncFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "syncFlag");
      final List<MemberEntryInfoEntity> _result = new ArrayList<MemberEntryInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MemberEntryInfoEntity _item;
        final String _tmpGp_Name;
        if (_cursor.isNull(_cursorIndexOfGpName)) {
          _tmpGp_Name = null;
        } else {
          _tmpGp_Name = _cursor.getString(_cursorIndexOfGpName);
        }
        final String _tmpScheme_Name;
        if (_cursor.isNull(_cursorIndexOfSchemeName)) {
          _tmpScheme_Name = null;
        } else {
          _tmpScheme_Name = _cursor.getString(_cursorIndexOfSchemeName);
        }
        final String _tmpBen_Id;
        if (_cursor.isNull(_cursorIndexOfBenId)) {
          _tmpBen_Id = null;
        } else {
          _tmpBen_Id = _cursor.getString(_cursorIndexOfBenId);
        }
        final String _tmpLgd_GpCode;
        if (_cursor.isNull(_cursorIndexOfLgdGpCode)) {
          _tmpLgd_GpCode = null;
        } else {
          _tmpLgd_GpCode = _cursor.getString(_cursorIndexOfLgdGpCode);
        }
        final String _tmpLgd_Villagecode;
        if (_cursor.isNull(_cursorIndexOfLgdVillagecode)) {
          _tmpLgd_Villagecode = null;
        } else {
          _tmpLgd_Villagecode = _cursor.getString(_cursorIndexOfLgdVillagecode);
        }
        final String _tmpMobile_no;
        if (_cursor.isNull(_cursorIndexOfMobileNo)) {
          _tmpMobile_no = null;
        } else {
          _tmpMobile_no = _cursor.getString(_cursorIndexOfMobileNo);
        }
        final String _tmpBen_availability;
        if (_cursor.isNull(_cursorIndexOfBenAvailability)) {
          _tmpBen_availability = null;
        } else {
          _tmpBen_availability = _cursor.getString(_cursorIndexOfBenAvailability);
        }
        final String _tmpAny_Familyinshg;
        if (_cursor.isNull(_cursorIndexOfAnyFamilyinshg)) {
          _tmpAny_Familyinshg = null;
        } else {
          _tmpAny_Familyinshg = _cursor.getString(_cursorIndexOfAnyFamilyinshg);
        }
        final String _tmpWilling_joinshg;
        if (_cursor.isNull(_cursorIndexOfWillingJoinshg)) {
          _tmpWilling_joinshg = null;
        } else {
          _tmpWilling_joinshg = _cursor.getString(_cursorIndexOfWillingJoinshg);
        }
        final String _tmpReason;
        if (_cursor.isNull(_cursorIndexOfReason)) {
          _tmpReason = null;
        } else {
          _tmpReason = _cursor.getString(_cursorIndexOfReason);
        }
        final String _tmpShg_Code;
        if (_cursor.isNull(_cursorIndexOfShgCode)) {
          _tmpShg_Code = null;
        } else {
          _tmpShg_Code = _cursor.getString(_cursorIndexOfShgCode);
        }
        final String _tmpMember_Code;
        if (_cursor.isNull(_cursorIndexOfMemberCode)) {
          _tmpMember_Code = null;
        } else {
          _tmpMember_Code = _cursor.getString(_cursorIndexOfMemberCode);
        }
        final String _tmpVillage_Code;
        if (_cursor.isNull(_cursorIndexOfVillageCode)) {
          _tmpVillage_Code = null;
        } else {
          _tmpVillage_Code = _cursor.getString(_cursorIndexOfVillageCode);
        }
        final String _tmpCreated_on;
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _tmpCreated_on = null;
        } else {
          _tmpCreated_on = _cursor.getString(_cursorIndexOfCreatedOn);
        }
        final String _tmpAppVersion;
        if (_cursor.isNull(_cursorIndexOfAppVersion)) {
          _tmpAppVersion = null;
        } else {
          _tmpAppVersion = _cursor.getString(_cursorIndexOfAppVersion);
        }
        final String _tmpSyncFlag;
        if (_cursor.isNull(_cursorIndexOfSyncFlag)) {
          _tmpSyncFlag = null;
        } else {
          _tmpSyncFlag = _cursor.getString(_cursorIndexOfSyncFlag);
        }
        _item = new MemberEntryInfoEntity(_tmpGp_Name,_tmpScheme_Name,_tmpBen_Id,_tmpLgd_GpCode,_tmpLgd_Villagecode,_tmpMobile_no,_tmpBen_availability,_tmpAny_Familyinshg,_tmpWilling_joinshg,_tmpReason,_tmpShg_Code,_tmpMember_Code,_tmpVillage_Code,_tmpCreated_on,_tmpAppVersion,_tmpSyncFlag);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getBenIds() {
    final String _sql = "select distinct ben_Id from MemberEntryInfoEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getLocalBenEntry() {
    final String _sql = "select count(*) from(select * from MemberEntryInfoEntity where syncFlag is 0)";
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
