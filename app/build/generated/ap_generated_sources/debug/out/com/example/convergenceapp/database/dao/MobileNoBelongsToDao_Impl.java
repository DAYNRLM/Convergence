package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.dbBean.MobileBelongsToBean;
import com.example.convergenceapp.database.entity.MobileNoBelongsToEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MobileNoBelongsToDao_Impl implements MobileNoBelongsToDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MobileNoBelongsToEntity> __insertionAdapterOfMobileNoBelongsToEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MobileNoBelongsToDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMobileNoBelongsToEntity = new EntityInsertionAdapter<MobileNoBelongsToEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MobileNoBelongsToEntity` (`id`,`type_id`,`type_name`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MobileNoBelongsToEntity value) {
        stmt.bindLong(1, value.id);
        if (value.getType_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getType_id());
        }
        if (value.getType_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getType_name());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from MobileNoBelongsToEntity";
        return _query;
      }
    };
  }

  @Override
  public void insert(final MobileNoBelongsToEntity mobileNoBelongsToEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMobileNoBelongsToEntity.insert(mobileNoBelongsToEntity);
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
  public List<MobileBelongsToBean> getMobileBelongsToList() {
    final String _sql = "select distinct type_id,type_name from MobileNoBelongsToEntity order by type_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTypeId = 0;
      final int _cursorIndexOfTypeName = 1;
      final List<MobileBelongsToBean> _result = new ArrayList<MobileBelongsToBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MobileBelongsToBean _item;
        _item = new MobileBelongsToBean();
        final String _tmpTypeId;
        if (_cursor.isNull(_cursorIndexOfTypeId)) {
          _tmpTypeId = null;
        } else {
          _tmpTypeId = _cursor.getString(_cursorIndexOfTypeId);
        }
        _item.setTypeId(_tmpTypeId);
        final String _tmpTypeName;
        if (_cursor.isNull(_cursorIndexOfTypeName)) {
          _tmpTypeName = null;
        } else {
          _tmpTypeName = _cursor.getString(_cursorIndexOfTypeName);
        }
        _item.setTypeName(_tmpTypeName);
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
