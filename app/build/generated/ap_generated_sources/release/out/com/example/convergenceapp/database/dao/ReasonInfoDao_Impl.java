package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.dbBean.ReasonBean;
import com.example.convergenceapp.database.entity.ReasonInfoEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ReasonInfoDao_Impl implements ReasonInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReasonInfoEntity> __insertionAdapterOfReasonInfoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ReasonInfoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReasonInfoEntity = new EntityInsertionAdapter<ReasonInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ReasonInfoEntity` (`id`,`reason`,`reasonCode`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReasonInfoEntity value) {
        stmt.bindLong(1, value.id);
        if (value.getReason() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getReason());
        }
        if (value.getReasonCode() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getReasonCode());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from ReasonInfoEntity";
        return _query;
      }
    };
  }

  @Override
  public void insert(final ReasonInfoEntity reasonInfoEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReasonInfoEntity.insert(reasonInfoEntity);
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
  public List<ReasonBean> getReason() {
    final String _sql = "select reason from ReasonInfoEntity order by reason ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfReason = 0;
      final List<ReasonBean> _result = new ArrayList<ReasonBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ReasonBean _item;
        final String _tmpReason;
        if (_cursor.isNull(_cursorIndexOfReason)) {
          _tmpReason = null;
        } else {
          _tmpReason = _cursor.getString(_cursorIndexOfReason);
        }
        _item = new ReasonBean(_tmpReason,null);
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
