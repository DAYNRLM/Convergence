package com.example.convergenceapp.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.convergenceapp.database.dao.BankMasterDao;
import com.example.convergenceapp.database.dao.BankMasterDao_Impl;
import com.example.convergenceapp.database.dao.CheckAndDeleteDao;
import com.example.convergenceapp.database.dao.CheckAndDeleteDao_Impl;
import com.example.convergenceapp.database.dao.LoginInfoDao;
import com.example.convergenceapp.database.dao.LoginInfoDao_Impl;
import com.example.convergenceapp.database.dao.MemberEntryInfoDao;
import com.example.convergenceapp.database.dao.MemberEntryInfoDao_Impl;
import com.example.convergenceapp.database.dao.MemberReasonDao;
import com.example.convergenceapp.database.dao.MemberReasonDao_Impl;
import com.example.convergenceapp.database.dao.MobileNoBelongsToDao;
import com.example.convergenceapp.database.dao.MobileNoBelongsToDao_Impl;
import com.example.convergenceapp.database.dao.NrlmBenefeciaryMobileDao;
import com.example.convergenceapp.database.dao.NrlmBenefeciaryMobileDao_Impl;
import com.example.convergenceapp.database.dao.NrlmInfoDao;
import com.example.convergenceapp.database.dao.NrlmInfoDao_Impl;
import com.example.convergenceapp.database.dao.PmaygInfoDao;
import com.example.convergenceapp.database.dao.PmaygInfoDao_Impl;
import com.example.convergenceapp.database.dao.ReasonInfoDao;
import com.example.convergenceapp.database.dao.ReasonInfoDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile LoginInfoDao _loginInfoDao;

  private volatile NrlmInfoDao _nrlmInfoDao;

  private volatile PmaygInfoDao _pmaygInfoDao;

  private volatile ReasonInfoDao _reasonInfoDao;

  private volatile NrlmBenefeciaryMobileDao _nrlmBenefeciaryMobileDao;

  private volatile BankMasterDao _bankMasterDao;

  private volatile MobileNoBelongsToDao _mobileNoBelongsToDao;

  private volatile MemberReasonDao _memberReasonDao;

  private volatile CheckAndDeleteDao _checkAndDeleteDao;

  private volatile MemberEntryInfoDao _memberEntryInfoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(7) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LoginInfoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `login_id` TEXT, `password` TEXT, `mobile_number` TEXT, `state_code` TEXT, `state_short_name` TEXT, `server_date_time` TEXT, `language_id` TEXT, `login_attempt` TEXT, `logout_days` TEXT, `user_name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CheckAndDeleteEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `village_code` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `NrlmInfoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `gp_code` TEXT, `mem_branch_code` TEXT, `mem_bank_code` TEXT, `lgd_gp_code` TEXT, `gp_name` TEXT, `village_code` TEXT, `village_name` TEXT, `shg_name` TEXT, `shg_code` TEXT, `member_name` TEXT, `member_code` TEXT, `user_id` TEXT, `block_name` TEXT, `lgd_state_code` TEXT, `state_name` TEXT, `state_code` TEXT, `block_code` TEXT, `district_name` TEXT, `lgd_district_code` TEXT, `lgd_block_code` TEXT, `mobile_number` TEXT, `belonging_name` TEXT, `act_num` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PmaygInfoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `gp_code` TEXT, `flag` TEXT, `gp_name` TEXT, `village_code` TEXT, `village_name` TEXT, `scheme` TEXT, `beneficiary_holder_name` TEXT, `beneficiary_id` TEXT, `ifsc_code` TEXT, `beneficiary_acc_no` TEXT, `beneficiary_bank_name` TEXT, `beneficiary_branch_name` TEXT, `mobile_no` TEXT, `member_name` TEXT, `holder_sync_flag` TEXT, `mothername` TEXT, `districtname` TEXT, `blockcode` TEXT, `districtcode` TEXT, `statecode` TEXT, `fathername` TEXT, `blockname` TEXT, `sl_no_member` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ReasonInfoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `reason` TEXT, `reasonCode` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MemberEntryInfoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `gp_Name` TEXT, `scheme_Name` TEXT, `ben_Id` TEXT, `lgd_GpCode` TEXT, `lgd_Villagecode` TEXT, `mobile_no` TEXT, `ben_availability` TEXT, `any_Familyinshg` TEXT, `willing_joinshg` TEXT, `reason` TEXT, `shg_Code` TEXT, `member_Code` TEXT, `village_Code` TEXT, `created_on` TEXT, `appVersion` TEXT, `syncFlag` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `NrlmBenefeciaryMobileEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `gp_code` TEXT, `village_code` TEXT, `shg_code` TEXT, `member_code` TEXT, `mobile_number` TEXT, `mobile_belongs_to` TEXT, `whether_part_of_shg` TEXT, `reason_of_discontinue` TEXT, `bank_code` TEXT, `branch_code` TEXT, `ifsc_code` TEXT, `account_number` TEXT, `entered_by` TEXT, `entered_date` TEXT, `syc_flag` TEXT, `updated_date` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `BankMasterEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `bank_code` TEXT, `bank_name` TEXT, `bank_branch_code` TEXT, `bank_branch_name` TEXT, `ifsc_code` TEXT, `alength` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MobileNoBelongsToEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type_id` TEXT, `type_name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MemberReasonEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `reason_id` INTEGER NOT NULL, `reason_name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '02544c3e43deac1714cd183374bcc858')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `LoginInfoEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `CheckAndDeleteEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `NrlmInfoEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `PmaygInfoEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `ReasonInfoEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `MemberEntryInfoEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `NrlmBenefeciaryMobileEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `BankMasterEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `MobileNoBelongsToEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `MemberReasonEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsLoginInfoEntity = new HashMap<String, TableInfo.Column>(11);
        _columnsLoginInfoEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("login_id", new TableInfo.Column("login_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("mobile_number", new TableInfo.Column("mobile_number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("state_code", new TableInfo.Column("state_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("state_short_name", new TableInfo.Column("state_short_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("server_date_time", new TableInfo.Column("server_date_time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("language_id", new TableInfo.Column("language_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("login_attempt", new TableInfo.Column("login_attempt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("logout_days", new TableInfo.Column("logout_days", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoginInfoEntity.put("user_name", new TableInfo.Column("user_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoginInfoEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLoginInfoEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLoginInfoEntity = new TableInfo("LoginInfoEntity", _columnsLoginInfoEntity, _foreignKeysLoginInfoEntity, _indicesLoginInfoEntity);
        final TableInfo _existingLoginInfoEntity = TableInfo.read(_db, "LoginInfoEntity");
        if (! _infoLoginInfoEntity.equals(_existingLoginInfoEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "LoginInfoEntity(com.example.convergenceapp.database.entity.LoginInfoEntity).\n"
                  + " Expected:\n" + _infoLoginInfoEntity + "\n"
                  + " Found:\n" + _existingLoginInfoEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsCheckAndDeleteEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsCheckAndDeleteEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckAndDeleteEntity.put("village_code", new TableInfo.Column("village_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCheckAndDeleteEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCheckAndDeleteEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCheckAndDeleteEntity = new TableInfo("CheckAndDeleteEntity", _columnsCheckAndDeleteEntity, _foreignKeysCheckAndDeleteEntity, _indicesCheckAndDeleteEntity);
        final TableInfo _existingCheckAndDeleteEntity = TableInfo.read(_db, "CheckAndDeleteEntity");
        if (! _infoCheckAndDeleteEntity.equals(_existingCheckAndDeleteEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "CheckAndDeleteEntity(com.example.convergenceapp.database.entity.CheckAndDeleteEntity).\n"
                  + " Expected:\n" + _infoCheckAndDeleteEntity + "\n"
                  + " Found:\n" + _existingCheckAndDeleteEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsNrlmInfoEntity = new HashMap<String, TableInfo.Column>(24);
        _columnsNrlmInfoEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("gp_code", new TableInfo.Column("gp_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("mem_branch_code", new TableInfo.Column("mem_branch_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("mem_bank_code", new TableInfo.Column("mem_bank_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("lgd_gp_code", new TableInfo.Column("lgd_gp_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("gp_name", new TableInfo.Column("gp_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("village_code", new TableInfo.Column("village_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("village_name", new TableInfo.Column("village_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("shg_name", new TableInfo.Column("shg_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("shg_code", new TableInfo.Column("shg_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("member_name", new TableInfo.Column("member_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("member_code", new TableInfo.Column("member_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("block_name", new TableInfo.Column("block_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("lgd_state_code", new TableInfo.Column("lgd_state_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("state_name", new TableInfo.Column("state_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("state_code", new TableInfo.Column("state_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("block_code", new TableInfo.Column("block_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("district_name", new TableInfo.Column("district_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("lgd_district_code", new TableInfo.Column("lgd_district_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("lgd_block_code", new TableInfo.Column("lgd_block_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("mobile_number", new TableInfo.Column("mobile_number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("belonging_name", new TableInfo.Column("belonging_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmInfoEntity.put("act_num", new TableInfo.Column("act_num", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNrlmInfoEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNrlmInfoEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNrlmInfoEntity = new TableInfo("NrlmInfoEntity", _columnsNrlmInfoEntity, _foreignKeysNrlmInfoEntity, _indicesNrlmInfoEntity);
        final TableInfo _existingNrlmInfoEntity = TableInfo.read(_db, "NrlmInfoEntity");
        if (! _infoNrlmInfoEntity.equals(_existingNrlmInfoEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "NrlmInfoEntity(com.example.convergenceapp.database.entity.NrlmInfoEntity).\n"
                  + " Expected:\n" + _infoNrlmInfoEntity + "\n"
                  + " Found:\n" + _existingNrlmInfoEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsPmaygInfoEntity = new HashMap<String, TableInfo.Column>(24);
        _columnsPmaygInfoEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("gp_code", new TableInfo.Column("gp_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("flag", new TableInfo.Column("flag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("gp_name", new TableInfo.Column("gp_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("village_code", new TableInfo.Column("village_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("village_name", new TableInfo.Column("village_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("scheme", new TableInfo.Column("scheme", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("beneficiary_holder_name", new TableInfo.Column("beneficiary_holder_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("beneficiary_id", new TableInfo.Column("beneficiary_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("ifsc_code", new TableInfo.Column("ifsc_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("beneficiary_acc_no", new TableInfo.Column("beneficiary_acc_no", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("beneficiary_bank_name", new TableInfo.Column("beneficiary_bank_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("beneficiary_branch_name", new TableInfo.Column("beneficiary_branch_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("mobile_no", new TableInfo.Column("mobile_no", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("member_name", new TableInfo.Column("member_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("holder_sync_flag", new TableInfo.Column("holder_sync_flag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("mothername", new TableInfo.Column("mothername", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("districtname", new TableInfo.Column("districtname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("blockcode", new TableInfo.Column("blockcode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("districtcode", new TableInfo.Column("districtcode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("statecode", new TableInfo.Column("statecode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("fathername", new TableInfo.Column("fathername", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("blockname", new TableInfo.Column("blockname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPmaygInfoEntity.put("sl_no_member", new TableInfo.Column("sl_no_member", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPmaygInfoEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPmaygInfoEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPmaygInfoEntity = new TableInfo("PmaygInfoEntity", _columnsPmaygInfoEntity, _foreignKeysPmaygInfoEntity, _indicesPmaygInfoEntity);
        final TableInfo _existingPmaygInfoEntity = TableInfo.read(_db, "PmaygInfoEntity");
        if (! _infoPmaygInfoEntity.equals(_existingPmaygInfoEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "PmaygInfoEntity(com.example.convergenceapp.database.entity.PmaygInfoEntity).\n"
                  + " Expected:\n" + _infoPmaygInfoEntity + "\n"
                  + " Found:\n" + _existingPmaygInfoEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsReasonInfoEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsReasonInfoEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReasonInfoEntity.put("reason", new TableInfo.Column("reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReasonInfoEntity.put("reasonCode", new TableInfo.Column("reasonCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReasonInfoEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReasonInfoEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReasonInfoEntity = new TableInfo("ReasonInfoEntity", _columnsReasonInfoEntity, _foreignKeysReasonInfoEntity, _indicesReasonInfoEntity);
        final TableInfo _existingReasonInfoEntity = TableInfo.read(_db, "ReasonInfoEntity");
        if (! _infoReasonInfoEntity.equals(_existingReasonInfoEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "ReasonInfoEntity(com.example.convergenceapp.database.entity.ReasonInfoEntity).\n"
                  + " Expected:\n" + _infoReasonInfoEntity + "\n"
                  + " Found:\n" + _existingReasonInfoEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsMemberEntryInfoEntity = new HashMap<String, TableInfo.Column>(17);
        _columnsMemberEntryInfoEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("gp_Name", new TableInfo.Column("gp_Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("scheme_Name", new TableInfo.Column("scheme_Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("ben_Id", new TableInfo.Column("ben_Id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("lgd_GpCode", new TableInfo.Column("lgd_GpCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("lgd_Villagecode", new TableInfo.Column("lgd_Villagecode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("mobile_no", new TableInfo.Column("mobile_no", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("ben_availability", new TableInfo.Column("ben_availability", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("any_Familyinshg", new TableInfo.Column("any_Familyinshg", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("willing_joinshg", new TableInfo.Column("willing_joinshg", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("reason", new TableInfo.Column("reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("shg_Code", new TableInfo.Column("shg_Code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("member_Code", new TableInfo.Column("member_Code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("village_Code", new TableInfo.Column("village_Code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("created_on", new TableInfo.Column("created_on", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("appVersion", new TableInfo.Column("appVersion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntryInfoEntity.put("syncFlag", new TableInfo.Column("syncFlag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMemberEntryInfoEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMemberEntryInfoEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMemberEntryInfoEntity = new TableInfo("MemberEntryInfoEntity", _columnsMemberEntryInfoEntity, _foreignKeysMemberEntryInfoEntity, _indicesMemberEntryInfoEntity);
        final TableInfo _existingMemberEntryInfoEntity = TableInfo.read(_db, "MemberEntryInfoEntity");
        if (! _infoMemberEntryInfoEntity.equals(_existingMemberEntryInfoEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "MemberEntryInfoEntity(com.example.convergenceapp.database.entity.MemberEntryInfoEntity).\n"
                  + " Expected:\n" + _infoMemberEntryInfoEntity + "\n"
                  + " Found:\n" + _existingMemberEntryInfoEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsNrlmBenefeciaryMobileEntity = new HashMap<String, TableInfo.Column>(17);
        _columnsNrlmBenefeciaryMobileEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("gp_code", new TableInfo.Column("gp_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("village_code", new TableInfo.Column("village_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("shg_code", new TableInfo.Column("shg_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("member_code", new TableInfo.Column("member_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("mobile_number", new TableInfo.Column("mobile_number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("mobile_belongs_to", new TableInfo.Column("mobile_belongs_to", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("whether_part_of_shg", new TableInfo.Column("whether_part_of_shg", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("reason_of_discontinue", new TableInfo.Column("reason_of_discontinue", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("bank_code", new TableInfo.Column("bank_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("branch_code", new TableInfo.Column("branch_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("ifsc_code", new TableInfo.Column("ifsc_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("account_number", new TableInfo.Column("account_number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("entered_by", new TableInfo.Column("entered_by", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("entered_date", new TableInfo.Column("entered_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("syc_flag", new TableInfo.Column("syc_flag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNrlmBenefeciaryMobileEntity.put("updated_date", new TableInfo.Column("updated_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNrlmBenefeciaryMobileEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNrlmBenefeciaryMobileEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNrlmBenefeciaryMobileEntity = new TableInfo("NrlmBenefeciaryMobileEntity", _columnsNrlmBenefeciaryMobileEntity, _foreignKeysNrlmBenefeciaryMobileEntity, _indicesNrlmBenefeciaryMobileEntity);
        final TableInfo _existingNrlmBenefeciaryMobileEntity = TableInfo.read(_db, "NrlmBenefeciaryMobileEntity");
        if (! _infoNrlmBenefeciaryMobileEntity.equals(_existingNrlmBenefeciaryMobileEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "NrlmBenefeciaryMobileEntity(com.example.convergenceapp.database.entity.NrlmBenefeciaryMobileEntity).\n"
                  + " Expected:\n" + _infoNrlmBenefeciaryMobileEntity + "\n"
                  + " Found:\n" + _existingNrlmBenefeciaryMobileEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsBankMasterEntity = new HashMap<String, TableInfo.Column>(7);
        _columnsBankMasterEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBankMasterEntity.put("bank_code", new TableInfo.Column("bank_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBankMasterEntity.put("bank_name", new TableInfo.Column("bank_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBankMasterEntity.put("bank_branch_code", new TableInfo.Column("bank_branch_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBankMasterEntity.put("bank_branch_name", new TableInfo.Column("bank_branch_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBankMasterEntity.put("ifsc_code", new TableInfo.Column("ifsc_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBankMasterEntity.put("alength", new TableInfo.Column("alength", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBankMasterEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBankMasterEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBankMasterEntity = new TableInfo("BankMasterEntity", _columnsBankMasterEntity, _foreignKeysBankMasterEntity, _indicesBankMasterEntity);
        final TableInfo _existingBankMasterEntity = TableInfo.read(_db, "BankMasterEntity");
        if (! _infoBankMasterEntity.equals(_existingBankMasterEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "BankMasterEntity(com.example.convergenceapp.database.entity.BankMasterEntity).\n"
                  + " Expected:\n" + _infoBankMasterEntity + "\n"
                  + " Found:\n" + _existingBankMasterEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsMobileNoBelongsToEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsMobileNoBelongsToEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMobileNoBelongsToEntity.put("type_id", new TableInfo.Column("type_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMobileNoBelongsToEntity.put("type_name", new TableInfo.Column("type_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMobileNoBelongsToEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMobileNoBelongsToEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMobileNoBelongsToEntity = new TableInfo("MobileNoBelongsToEntity", _columnsMobileNoBelongsToEntity, _foreignKeysMobileNoBelongsToEntity, _indicesMobileNoBelongsToEntity);
        final TableInfo _existingMobileNoBelongsToEntity = TableInfo.read(_db, "MobileNoBelongsToEntity");
        if (! _infoMobileNoBelongsToEntity.equals(_existingMobileNoBelongsToEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "MobileNoBelongsToEntity(com.example.convergenceapp.database.entity.MobileNoBelongsToEntity).\n"
                  + " Expected:\n" + _infoMobileNoBelongsToEntity + "\n"
                  + " Found:\n" + _existingMobileNoBelongsToEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsMemberReasonEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsMemberReasonEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberReasonEntity.put("reason_id", new TableInfo.Column("reason_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberReasonEntity.put("reason_name", new TableInfo.Column("reason_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMemberReasonEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMemberReasonEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMemberReasonEntity = new TableInfo("MemberReasonEntity", _columnsMemberReasonEntity, _foreignKeysMemberReasonEntity, _indicesMemberReasonEntity);
        final TableInfo _existingMemberReasonEntity = TableInfo.read(_db, "MemberReasonEntity");
        if (! _infoMemberReasonEntity.equals(_existingMemberReasonEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "MemberReasonEntity(com.example.convergenceapp.database.entity.MemberReasonEntity).\n"
                  + " Expected:\n" + _infoMemberReasonEntity + "\n"
                  + " Found:\n" + _existingMemberReasonEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "02544c3e43deac1714cd183374bcc858", "6895de55d3181f8795df6c8e08e6d849");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "LoginInfoEntity","CheckAndDeleteEntity","NrlmInfoEntity","PmaygInfoEntity","ReasonInfoEntity","MemberEntryInfoEntity","NrlmBenefeciaryMobileEntity","BankMasterEntity","MobileNoBelongsToEntity","MemberReasonEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `LoginInfoEntity`");
      _db.execSQL("DELETE FROM `CheckAndDeleteEntity`");
      _db.execSQL("DELETE FROM `NrlmInfoEntity`");
      _db.execSQL("DELETE FROM `PmaygInfoEntity`");
      _db.execSQL("DELETE FROM `ReasonInfoEntity`");
      _db.execSQL("DELETE FROM `MemberEntryInfoEntity`");
      _db.execSQL("DELETE FROM `NrlmBenefeciaryMobileEntity`");
      _db.execSQL("DELETE FROM `BankMasterEntity`");
      _db.execSQL("DELETE FROM `MobileNoBelongsToEntity`");
      _db.execSQL("DELETE FROM `MemberReasonEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LoginInfoDao.class, LoginInfoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NrlmInfoDao.class, NrlmInfoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PmaygInfoDao.class, PmaygInfoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReasonInfoDao.class, ReasonInfoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NrlmBenefeciaryMobileDao.class, NrlmBenefeciaryMobileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BankMasterDao.class, BankMasterDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MobileNoBelongsToDao.class, MobileNoBelongsToDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MemberReasonDao.class, MemberReasonDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CheckAndDeleteDao.class, CheckAndDeleteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MemberEntryInfoDao.class, MemberEntryInfoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public LoginInfoDao loginInfoDao() {
    if (_loginInfoDao != null) {
      return _loginInfoDao;
    } else {
      synchronized(this) {
        if(_loginInfoDao == null) {
          _loginInfoDao = new LoginInfoDao_Impl(this);
        }
        return _loginInfoDao;
      }
    }
  }

  @Override
  public NrlmInfoDao nrlmInfoDao() {
    if (_nrlmInfoDao != null) {
      return _nrlmInfoDao;
    } else {
      synchronized(this) {
        if(_nrlmInfoDao == null) {
          _nrlmInfoDao = new NrlmInfoDao_Impl(this);
        }
        return _nrlmInfoDao;
      }
    }
  }

  @Override
  public PmaygInfoDao pmaygInfoDao() {
    if (_pmaygInfoDao != null) {
      return _pmaygInfoDao;
    } else {
      synchronized(this) {
        if(_pmaygInfoDao == null) {
          _pmaygInfoDao = new PmaygInfoDao_Impl(this);
        }
        return _pmaygInfoDao;
      }
    }
  }

  @Override
  public ReasonInfoDao reasonInfoDao() {
    if (_reasonInfoDao != null) {
      return _reasonInfoDao;
    } else {
      synchronized(this) {
        if(_reasonInfoDao == null) {
          _reasonInfoDao = new ReasonInfoDao_Impl(this);
        }
        return _reasonInfoDao;
      }
    }
  }

  @Override
  public NrlmBenefeciaryMobileDao nrlmBenefeciaryMobileDao() {
    if (_nrlmBenefeciaryMobileDao != null) {
      return _nrlmBenefeciaryMobileDao;
    } else {
      synchronized(this) {
        if(_nrlmBenefeciaryMobileDao == null) {
          _nrlmBenefeciaryMobileDao = new NrlmBenefeciaryMobileDao_Impl(this);
        }
        return _nrlmBenefeciaryMobileDao;
      }
    }
  }

  @Override
  public BankMasterDao bankMasterDao() {
    if (_bankMasterDao != null) {
      return _bankMasterDao;
    } else {
      synchronized(this) {
        if(_bankMasterDao == null) {
          _bankMasterDao = new BankMasterDao_Impl(this);
        }
        return _bankMasterDao;
      }
    }
  }

  @Override
  public MobileNoBelongsToDao mobileNoBelongsToDao() {
    if (_mobileNoBelongsToDao != null) {
      return _mobileNoBelongsToDao;
    } else {
      synchronized(this) {
        if(_mobileNoBelongsToDao == null) {
          _mobileNoBelongsToDao = new MobileNoBelongsToDao_Impl(this);
        }
        return _mobileNoBelongsToDao;
      }
    }
  }

  @Override
  public MemberReasonDao memberReasonDao() {
    if (_memberReasonDao != null) {
      return _memberReasonDao;
    } else {
      synchronized(this) {
        if(_memberReasonDao == null) {
          _memberReasonDao = new MemberReasonDao_Impl(this);
        }
        return _memberReasonDao;
      }
    }
  }

  @Override
  public CheckAndDeleteDao checkAndDeleteDao() {
    if (_checkAndDeleteDao != null) {
      return _checkAndDeleteDao;
    } else {
      synchronized(this) {
        if(_checkAndDeleteDao == null) {
          _checkAndDeleteDao = new CheckAndDeleteDao_Impl(this);
        }
        return _checkAndDeleteDao;
      }
    }
  }

  @Override
  public MemberEntryInfoDao memberEntryInfoDao() {
    if (_memberEntryInfoDao != null) {
      return _memberEntryInfoDao;
    } else {
      synchronized(this) {
        if(_memberEntryInfoDao == null) {
          _memberEntryInfoDao = new MemberEntryInfoDao_Impl(this);
        }
        return _memberEntryInfoDao;
      }
    }
  }
}
