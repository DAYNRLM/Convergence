package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.dbBean.CheckAndDeleteBean;
import com.example.convergenceapp.database.entity.CheckAndDeleteEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CheckAndDeleteDao_Impl implements CheckAndDeleteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CheckAndDeleteEntity> __insertionAdapterOfCheckAndDeleteEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CheckAndDeleteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCheckAndDeleteEntity = new EntityInsertionAdapter<CheckAndDeleteEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CheckAndDeleteEntity` (`id`,`village_code`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CheckAndDeleteEntity value) {
        stmt.bindLong(1, value.id);
        if (value.getVillage_code() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getVillage_code());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from CheckAndDeleteEntity";
        return _query;
      }
    };
  }

  @Override
  public void insert(final CheckAndDeleteEntity checkAndDeleteEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCheckAndDeleteEntity.insert(checkAndDeleteEntity);
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
  public List<CheckAndDeleteBean> getVillageCodeList() {
    final String _sql = "select distinct village_code from CheckAndDeleteEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVillageCode = 0;
      final List<CheckAndDeleteBean> _result = new ArrayList<CheckAndDeleteBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CheckAndDeleteBean _item;
        _item = new CheckAndDeleteBean();
        final String _tmpVillageCode;
        if (_cursor.isNull(_cursorIndexOfVillageCode)) {
          _tmpVillageCode = null;
        } else {
          _tmpVillageCode = _cursor.getString(_cursorIndexOfVillageCode);
        }
        _item.setVillageCode(_tmpVillageCode);
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
