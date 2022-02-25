package com.nrzs.data.database;

import android.arch.persistence.p000db.SupportSQLiteDatabase;
import android.arch.persistence.p000db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomMasterTable;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.util.TableInfo;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.BannerInfoDao;
import p110z1.BannerInfoDao_Impl;
import p110z1.MoneyDao;
import p110z1.MoneyDao_Impl;
import p110z1.TopicInfoDao;
import p110z1.TopicInfoDao_Impl;

/* loaded from: classes2.dex */
public class AppDatabase_Impl extends AppDatabase {

    /* renamed from: a */
    private volatile TopicInfoDao f10625a;

    /* renamed from: b */
    private volatile BannerInfoDao f10626b;

    /* renamed from: c */
    private volatile MoneyDao f10627c;

    @Override // android.arch.persistence.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(7) { // from class: com.nrzs.data.database.AppDatabase_Impl.1
            @Override // android.arch.persistence.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `TopicInfo` (`tid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `TopicID` INTEGER NOT NULL, `Package` TEXT, `PackageNames` TEXT, `IfRecommend` INTEGER NOT NULL, `TopicName` TEXT, `ImgPath` TEXT, `ScriptCount` INTEGER NOT NULL, `FnTags` TEXT, `isVip` INTEGER NOT NULL, `SportBackGround` INTEGER NOT NULL, `isVaVip` INTEGER NOT NULL, `isShield` INTEGER NOT NULL, `VaScriptCount` INTEGER NOT NULL, `sTags` TEXT, `IsShowServerIcon` INTEGER NOT NULL, `MatchType` INTEGER NOT NULL, `OrderNum` INTEGER NOT NULL, `MatchPackage` TEXT, `ShowOrder` INTEGER NOT NULL, `LastBulletinID` INTEGER NOT NULL, `ShowType` INTEGER NOT NULL, `sportModel` INTEGER NOT NULL, `isTop` INTEGER NOT NULL, `localAppPackage` TEXT, `localAppName` TEXT, `sortLetters` TEXT, `localVersionName` TEXT, `time` INTEGER NOT NULL, `BitSportCfg` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `AdResultInfoItem` (`tid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Id` INTEGER NOT NULL, `Appid` TEXT, `AdName` TEXT, `ImgUrl` TEXT, `Title` TEXT, `SubTitle` TEXT, `ExecCommand` TEXT, `ExecArgs` TEXT, `EffectiveTime` TEXT, `FailureTime` TEXT, `CreateTime` TEXT, `UserType` INTEGER NOT NULL, `BigImg` TEXT, `AdPosition` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `MoneyInfo` (`tid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `day` TEXT, `time` TEXT, `username` TEXT, `money` TEXT, `countMoeny` TEXT, `zje` TEXT)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b10bd4f280ef5a1b61ff497c3552e873\")");
            }

            @Override // android.arch.persistence.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `TopicInfo`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `AdResultInfoItem`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `MoneyInfo`");
            }

            @Override // android.arch.persistence.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // android.arch.persistence.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                AppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                AppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // android.arch.persistence.room.RoomOpenHelper.Delegate
            protected void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(30);
                hashMap.put("tid", new TableInfo.Column("tid", "INTEGER", true, 1));
                hashMap.put("TopicID", new TableInfo.Column("TopicID", "INTEGER", true, 0));
                hashMap.put("Package", new TableInfo.Column("Package", "TEXT", false, 0));
                hashMap.put("PackageNames", new TableInfo.Column("PackageNames", "TEXT", false, 0));
                hashMap.put("IfRecommend", new TableInfo.Column("IfRecommend", "INTEGER", true, 0));
                hashMap.put("TopicName", new TableInfo.Column("TopicName", "TEXT", false, 0));
                hashMap.put("ImgPath", new TableInfo.Column("ImgPath", "TEXT", false, 0));
                hashMap.put("ScriptCount", new TableInfo.Column("ScriptCount", "INTEGER", true, 0));
                hashMap.put("FnTags", new TableInfo.Column("FnTags", "TEXT", false, 0));
                hashMap.put("isVip", new TableInfo.Column("isVip", "INTEGER", true, 0));
                hashMap.put("SportBackGround", new TableInfo.Column("SportBackGround", "INTEGER", true, 0));
                hashMap.put("isVaVip", new TableInfo.Column("isVaVip", "INTEGER", true, 0));
                hashMap.put("isShield", new TableInfo.Column("isShield", "INTEGER", true, 0));
                hashMap.put("VaScriptCount", new TableInfo.Column("VaScriptCount", "INTEGER", true, 0));
                hashMap.put("sTags", new TableInfo.Column("sTags", "TEXT", false, 0));
                hashMap.put("IsShowServerIcon", new TableInfo.Column("IsShowServerIcon", "INTEGER", true, 0));
                hashMap.put("MatchType", new TableInfo.Column("MatchType", "INTEGER", true, 0));
                hashMap.put("OrderNum", new TableInfo.Column("OrderNum", "INTEGER", true, 0));
                hashMap.put("MatchPackage", new TableInfo.Column("MatchPackage", "TEXT", false, 0));
                hashMap.put("ShowOrder", new TableInfo.Column("ShowOrder", "INTEGER", true, 0));
                hashMap.put("LastBulletinID", new TableInfo.Column("LastBulletinID", "INTEGER", true, 0));
                hashMap.put("ShowType", new TableInfo.Column("ShowType", "INTEGER", true, 0));
                hashMap.put("sportModel", new TableInfo.Column("sportModel", "INTEGER", true, 0));
                hashMap.put("isTop", new TableInfo.Column("isTop", "INTEGER", true, 0));
                hashMap.put("localAppPackage", new TableInfo.Column("localAppPackage", "TEXT", false, 0));
                hashMap.put("localAppName", new TableInfo.Column("localAppName", "TEXT", false, 0));
                hashMap.put("sortLetters", new TableInfo.Column("sortLetters", "TEXT", false, 0));
                hashMap.put("localVersionName", new TableInfo.Column("localVersionName", "TEXT", false, 0));
                hashMap.put("time", new TableInfo.Column("time", "INTEGER", true, 0));
                hashMap.put("BitSportCfg", new TableInfo.Column("BitSportCfg", "INTEGER", true, 0));
                TableInfo tableInfo = new TableInfo("TopicInfo", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "TopicInfo");
                if (tableInfo.equals(read)) {
                    HashMap hashMap2 = new HashMap(15);
                    hashMap2.put("tid", new TableInfo.Column("tid", "INTEGER", true, 1));
                    hashMap2.put("Id", new TableInfo.Column("Id", "INTEGER", true, 0));
                    hashMap2.put("Appid", new TableInfo.Column("Appid", "TEXT", false, 0));
                    hashMap2.put("AdName", new TableInfo.Column("AdName", "TEXT", false, 0));
                    hashMap2.put("ImgUrl", new TableInfo.Column("ImgUrl", "TEXT", false, 0));
                    hashMap2.put("Title", new TableInfo.Column("Title", "TEXT", false, 0));
                    hashMap2.put("SubTitle", new TableInfo.Column("SubTitle", "TEXT", false, 0));
                    hashMap2.put("ExecCommand", new TableInfo.Column("ExecCommand", "TEXT", false, 0));
                    hashMap2.put("ExecArgs", new TableInfo.Column("ExecArgs", "TEXT", false, 0));
                    hashMap2.put("EffectiveTime", new TableInfo.Column("EffectiveTime", "TEXT", false, 0));
                    hashMap2.put("FailureTime", new TableInfo.Column("FailureTime", "TEXT", false, 0));
                    hashMap2.put("CreateTime", new TableInfo.Column("CreateTime", "TEXT", false, 0));
                    hashMap2.put("UserType", new TableInfo.Column("UserType", "INTEGER", true, 0));
                    hashMap2.put("BigImg", new TableInfo.Column("BigImg", "TEXT", false, 0));
                    hashMap2.put("AdPosition", new TableInfo.Column("AdPosition", "INTEGER", true, 0));
                    TableInfo tableInfo2 = new TableInfo("AdResultInfoItem", hashMap2, new HashSet(0), new HashSet(0));
                    TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "AdResultInfoItem");
                    if (tableInfo2.equals(read2)) {
                        HashMap hashMap3 = new HashMap(7);
                        hashMap3.put("tid", new TableInfo.Column("tid", "INTEGER", true, 1));
                        hashMap3.put(WaitFor.Unit.DAY, new TableInfo.Column(WaitFor.Unit.DAY, "TEXT", false, 0));
                        hashMap3.put("time", new TableInfo.Column("time", "TEXT", false, 0));
                        hashMap3.put("username", new TableInfo.Column("username", "TEXT", false, 0));
                        hashMap3.put("money", new TableInfo.Column("money", "TEXT", false, 0));
                        hashMap3.put("countMoeny", new TableInfo.Column("countMoeny", "TEXT", false, 0));
                        hashMap3.put("zje", new TableInfo.Column("zje", "TEXT", false, 0));
                        TableInfo tableInfo3 = new TableInfo("MoneyInfo", hashMap3, new HashSet(0), new HashSet(0));
                        TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "MoneyInfo");
                        if (!tableInfo3.equals(read3)) {
                            throw new IllegalStateException("Migration didn't properly handle MoneyInfo(com.nrzs.data.redbag.bean.MoneyInfo).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                        }
                        return;
                    }
                    throw new IllegalStateException("Migration didn't properly handle AdResultInfoItem(com.nrzs.data.other.bean.AdResultInfoItem).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                throw new IllegalStateException("Migration didn't properly handle TopicInfo(com.nrzs.data.game.bean.TopicInfo).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
        }, "b10bd4f280ef5a1b61ff497c3552e873", "38457734337597e3425d0f1e4094c6f5")).build());
    }

    @Override // android.arch.persistence.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "TopicInfo", "AdResultInfoItem", "MoneyInfo");
    }

    @Override // android.arch.persistence.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `TopicInfo`");
            writableDatabase.execSQL("DELETE FROM `AdResultInfoItem`");
            writableDatabase.execSQL("DELETE FROM `MoneyInfo`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.nrzs.data.database.AppDatabase
    /* renamed from: a */
    public TopicInfoDao mo18932a() {
        TopicInfoDao akpVar;
        if (this.f10625a != null) {
            return this.f10625a;
        }
        synchronized (this) {
            if (this.f10625a == null) {
                this.f10625a = new TopicInfoDao_Impl(this);
            }
            akpVar = this.f10625a;
        }
        return akpVar;
    }

    @Override // com.nrzs.data.database.AppDatabase
    /* renamed from: b */
    public BannerInfoDao mo18929b() {
        BannerInfoDao aknVar;
        if (this.f10626b != null) {
            return this.f10626b;
        }
        synchronized (this) {
            if (this.f10626b == null) {
                this.f10626b = new BannerInfoDao_Impl(this);
            }
            aknVar = this.f10626b;
        }
        return aknVar;
    }

    @Override // com.nrzs.data.database.AppDatabase
    /* renamed from: c */
    public MoneyDao mo18926c() {
        MoneyDao alnVar;
        if (this.f10627c != null) {
            return this.f10627c;
        }
        synchronized (this) {
            if (this.f10627c == null) {
                this.f10627c = new MoneyDao_Impl(this);
            }
            alnVar = this.f10627c;
        }
        return alnVar;
    }
}
