package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.entity.LoginInfoEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LoginInfoDao_Impl implements LoginInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LoginInfoEntity> __insertionAdapterOfLoginInfoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public LoginInfoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoginInfoEntity = new EntityInsertionAdapter<LoginInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `LoginInfoEntity` (`id`,`login_id`,`password`,`mobile_number`,`state_code`,`state_short_name`,`server_date_time`,`language_id`,`login_attempt`,`logout_days`,`user_name`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoginInfoEntity value) {
        stmt.bindLong(1, value.id);
        if (value.getLogin_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLogin_id());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getMobile_number() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMobile_number());
        }
        if (value.getState_code() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getState_code());
        }
        if (value.getState_short_name() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getState_short_name());
        }
        if (value.getServer_date_time() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getServer_date_time());
        }
        if (value.getLanguage_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLanguage_id());
        }
        if (value.getLogin_attempt() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getLogin_attempt());
        }
        if (value.getLogout_days() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLogout_days());
        }
        if (value.getUser_name() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getUser_name());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from logininfoentity";
        return _query;
      }
    };
  }

  @Override
  public void insert(final LoginInfoEntity loginInfoEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLoginInfoEntity.insert(loginInfoEntity);
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
  public String getStateNameDB() {
    final String _sql = "select distinct  state_short_name from logininfoentity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
  public String getLanguageCode() {
    final String _sql = "select distinct language_id from loginInfoEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
  public LoginInfoEntity getLoginData() {
    final String _sql = "select distinct * from LoginInfoEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLoginId = CursorUtil.getColumnIndexOrThrow(_cursor, "login_id");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfMobileNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "mobile_number");
      final int _cursorIndexOfStateCode = CursorUtil.getColumnIndexOrThrow(_cursor, "state_code");
      final int _cursorIndexOfStateShortName = CursorUtil.getColumnIndexOrThrow(_cursor, "state_short_name");
      final int _cursorIndexOfServerDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "server_date_time");
      final int _cursorIndexOfLanguageId = CursorUtil.getColumnIndexOrThrow(_cursor, "language_id");
      final int _cursorIndexOfLoginAttempt = CursorUtil.getColumnIndexOrThrow(_cursor, "login_attempt");
      final int _cursorIndexOfLogoutDays = CursorUtil.getColumnIndexOrThrow(_cursor, "logout_days");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final LoginInfoEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpLogin_id;
        if (_cursor.isNull(_cursorIndexOfLoginId)) {
          _tmpLogin_id = null;
        } else {
          _tmpLogin_id = _cursor.getString(_cursorIndexOfLoginId);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpMobile_number;
        if (_cursor.isNull(_cursorIndexOfMobileNumber)) {
          _tmpMobile_number = null;
        } else {
          _tmpMobile_number = _cursor.getString(_cursorIndexOfMobileNumber);
        }
        final String _tmpState_code;
        if (_cursor.isNull(_cursorIndexOfStateCode)) {
          _tmpState_code = null;
        } else {
          _tmpState_code = _cursor.getString(_cursorIndexOfStateCode);
        }
        final String _tmpState_short_name;
        if (_cursor.isNull(_cursorIndexOfStateShortName)) {
          _tmpState_short_name = null;
        } else {
          _tmpState_short_name = _cursor.getString(_cursorIndexOfStateShortName);
        }
        final String _tmpServer_date_time;
        if (_cursor.isNull(_cursorIndexOfServerDateTime)) {
          _tmpServer_date_time = null;
        } else {
          _tmpServer_date_time = _cursor.getString(_cursorIndexOfServerDateTime);
        }
        final String _tmpLanguage_id;
        if (_cursor.isNull(_cursorIndexOfLanguageId)) {
          _tmpLanguage_id = null;
        } else {
          _tmpLanguage_id = _cursor.getString(_cursorIndexOfLanguageId);
        }
        final String _tmpLogin_attempt;
        if (_cursor.isNull(_cursorIndexOfLoginAttempt)) {
          _tmpLogin_attempt = null;
        } else {
          _tmpLogin_attempt = _cursor.getString(_cursorIndexOfLoginAttempt);
        }
        final String _tmpLogout_days;
        if (_cursor.isNull(_cursorIndexOfLogoutDays)) {
          _tmpLogout_days = null;
        } else {
          _tmpLogout_days = _cursor.getString(_cursorIndexOfLogoutDays);
        }
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        _result = new LoginInfoEntity(_tmpLogin_id,_tmpPassword,_tmpMobile_number,_tmpState_code,_tmpState_short_name,_tmpServer_date_time,_tmpLanguage_id,_tmpLogin_attempt,_tmpLogout_days,_tmpUser_name);
        _result.id = _cursor.getInt(_cursorIndexOfId);
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
  public String getLoginId() {
    final String _sql = "select distinct login_id from loginInfoEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
  public String getServerDate() {
    final String _sql = "select distinct server_date_time from loginInfoEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
  public String getLocalCount() {
    final String _sql = "select count(*) from(select * from logininfoentity)";
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
