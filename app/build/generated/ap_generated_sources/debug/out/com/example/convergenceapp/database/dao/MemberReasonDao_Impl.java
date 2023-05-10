package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.dbBean.MemberReasonBean;
import com.example.convergenceapp.database.entity.MemberReasonEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MemberReasonDao_Impl implements MemberReasonDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MemberReasonEntity> __insertionAdapterOfMemberReasonEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MemberReasonDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMemberReasonEntity = new EntityInsertionAdapter<MemberReasonEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MemberReasonEntity` (`id`,`reason_id`,`reason_name`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MemberReasonEntity value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.reason_id);
        if (value.getReason_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getReason_name());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from MemberReasonEntity";
        return _query;
      }
    };
  }

  @Override
  public void insert(final MemberReasonEntity memberReasonEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMemberReasonEntity.insert(memberReasonEntity);
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
  public List<MemberReasonBean> getMemberReasonList() {
    final String _sql = "select distinct reason_id,reason_name from MemberReasonEntity order by reason_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfReasonId = 0;
      final int _cursorIndexOfReasonName = 1;
      final List<MemberReasonBean> _result = new ArrayList<MemberReasonBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MemberReasonBean _item;
        _item = new MemberReasonBean();
        final int _tmpReasonId;
        _tmpReasonId = _cursor.getInt(_cursorIndexOfReasonId);
        _item.setReasonId(_tmpReasonId);
        final String _tmpReasonName;
        if (_cursor.isNull(_cursorIndexOfReasonName)) {
          _tmpReasonName = null;
        } else {
          _tmpReasonName = _cursor.getString(_cursorIndexOfReasonName);
        }
        _item.setReasonName(_tmpReasonName);
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
