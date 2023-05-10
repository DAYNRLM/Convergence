package com.example.convergenceapp.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.convergenceapp.database.dbBean.BankMasterBean;
import com.example.convergenceapp.database.dbBean.BankNameAndBranchName;
import com.example.convergenceapp.database.entity.BankMasterEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BankMasterDao_Impl implements BankMasterDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BankMasterEntity> __insertionAdapterOfBankMasterEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public BankMasterDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBankMasterEntity = new EntityInsertionAdapter<BankMasterEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `BankMasterEntity` (`id`,`bank_code`,`bank_name`,`bank_branch_code`,`bank_branch_name`,`ifsc_code`,`alength`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BankMasterEntity value) {
        stmt.bindLong(1, value.id);
        if (value.getBank_code() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBank_code());
        }
        if (value.getBank_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBank_name());
        }
        if (value.getBank_branch_code() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBank_branch_code());
        }
        if (value.getBank_branch_name() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getBank_branch_name());
        }
        if (value.getIfsc_code() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getIfsc_code());
        }
        if (value.getAlength() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAlength());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from BankMasterEntity";
        return _query;
      }
    };
  }

  @Override
  public void insert(final BankMasterEntity bankMasterEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBankMasterEntity.insert(bankMasterEntity);
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
  public List<BankMasterBean> getBankList() {
    final String _sql = "select distinct bank_code,bank_name from BankMasterEntity order by bank_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfBankCode = 0;
      final int _cursorIndexOfBankName = 1;
      final List<BankMasterBean> _result = new ArrayList<BankMasterBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BankMasterBean _item;
        _item = new BankMasterBean();
        final String _tmpBankCode;
        if (_cursor.isNull(_cursorIndexOfBankCode)) {
          _tmpBankCode = null;
        } else {
          _tmpBankCode = _cursor.getString(_cursorIndexOfBankCode);
        }
        _item.setBankCode(_tmpBankCode);
        final String _tmpBankName;
        if (_cursor.isNull(_cursorIndexOfBankName)) {
          _tmpBankName = null;
        } else {
          _tmpBankName = _cursor.getString(_cursorIndexOfBankName);
        }
        _item.setBankName(_tmpBankName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<BankMasterBean> getBranchList(final String bankCode) {
    final String _sql = "select distinct bank_branch_code,bank_branch_name,ifsc_code,alength from BankMasterEntity where BankMasterEntity.bank_code =? order by bank_branch_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bankCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bankCode);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfBranchCode = 0;
      final int _cursorIndexOfBranchName = 1;
      final int _cursorIndexOfIfscCode = 2;
      final int _cursorIndexOfAlength = 3;
      final List<BankMasterBean> _result = new ArrayList<BankMasterBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BankMasterBean _item;
        _item = new BankMasterBean();
        final String _tmpBranchCode;
        if (_cursor.isNull(_cursorIndexOfBranchCode)) {
          _tmpBranchCode = null;
        } else {
          _tmpBranchCode = _cursor.getString(_cursorIndexOfBranchCode);
        }
        _item.setBranchCode(_tmpBranchCode);
        final String _tmpBranchName;
        if (_cursor.isNull(_cursorIndexOfBranchName)) {
          _tmpBranchName = null;
        } else {
          _tmpBranchName = _cursor.getString(_cursorIndexOfBranchName);
        }
        _item.setBranchName(_tmpBranchName);
        final String _tmpIfscCode;
        if (_cursor.isNull(_cursorIndexOfIfscCode)) {
          _tmpIfscCode = null;
        } else {
          _tmpIfscCode = _cursor.getString(_cursorIndexOfIfscCode);
        }
        _item.setIfscCode(_tmpIfscCode);
        final String _tmpAlength;
        if (_cursor.isNull(_cursorIndexOfAlength)) {
          _tmpAlength = null;
        } else {
          _tmpAlength = _cursor.getString(_cursorIndexOfAlength);
        }
        _item.setAlength(_tmpAlength);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<BankNameAndBranchName> getBankNameAndBranchName(final String bankCode) {
    final String _sql = "select distinct bank_branch_name,bank_name,bank_code from BankMasterEntity where BankMasterEntity.bank_branch_code =? order by bank_branch_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bankCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bankCode);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfBranchName = 0;
      final int _cursorIndexOfBankName = 1;
      final int _cursorIndexOfBankCode = 2;
      final List<BankNameAndBranchName> _result = new ArrayList<BankNameAndBranchName>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BankNameAndBranchName _item;
        _item = new BankNameAndBranchName();
        final String _tmpBranchName;
        if (_cursor.isNull(_cursorIndexOfBranchName)) {
          _tmpBranchName = null;
        } else {
          _tmpBranchName = _cursor.getString(_cursorIndexOfBranchName);
        }
        _item.setBranchName(_tmpBranchName);
        final String _tmpBankName;
        if (_cursor.isNull(_cursorIndexOfBankName)) {
          _tmpBankName = null;
        } else {
          _tmpBankName = _cursor.getString(_cursorIndexOfBankName);
        }
        _item.setBankName(_tmpBankName);
        final String _tmpBankCode;
        if (_cursor.isNull(_cursorIndexOfBankCode)) {
          _tmpBankCode = null;
        } else {
          _tmpBankCode = _cursor.getString(_cursorIndexOfBankCode);
        }
        _item.setBankCode(_tmpBankCode);
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
