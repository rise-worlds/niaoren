package p110z1;

import android.arch.persistence.p000db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.nrzs.data.game.bean.TopicInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.akq */
/* loaded from: classes3.dex */
public class TopicInfoDao_Impl implements TopicInfoDao {

    /* renamed from: a */
    private final RoomDatabase f16215a;

    /* renamed from: b */
    private final EntityInsertionAdapter f16216b;

    /* renamed from: c */
    private final EntityDeletionOrUpdateAdapter f16217c;

    /* renamed from: d */
    private final EntityDeletionOrUpdateAdapter f16218d;

    /* renamed from: e */
    private final SharedSQLiteStatement f16219e;

    public TopicInfoDao_Impl(RoomDatabase roomDatabase) {
        this.f16215a = roomDatabase;
        this.f16216b = new EntityInsertionAdapter<TopicInfo>(roomDatabase) { // from class: z1.akq.1
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `TopicInfo`(`tid`,`TopicID`,`Package`,`PackageNames`,`IfRecommend`,`TopicName`,`ImgPath`,`ScriptCount`,`FnTags`,`isVip`,`SportBackGround`,`isVaVip`,`isShield`,`VaScriptCount`,`sTags`,`IsShowServerIcon`,`MatchType`,`OrderNum`,`MatchPackage`,`ShowOrder`,`LastBulletinID`,`ShowType`,`sportModel`,`isTop`,`localAppPackage`,`localAppName`,`sortLetters`,`localVersionName`,`time`,`BitSportCfg`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TopicInfo topicInfo) {
                supportSQLiteStatement.bindLong(1, topicInfo.tid);
                supportSQLiteStatement.bindLong(2, topicInfo.TopicID);
                if (topicInfo.Package == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, topicInfo.Package);
                }
                if (topicInfo.PackageNames == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, topicInfo.PackageNames);
                }
                supportSQLiteStatement.bindLong(5, topicInfo.IfRecommend);
                if (topicInfo.TopicName == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, topicInfo.TopicName);
                }
                if (topicInfo.ImgPath == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, topicInfo.ImgPath);
                }
                supportSQLiteStatement.bindLong(8, topicInfo.ScriptCount);
                if (topicInfo.FnTags == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, topicInfo.FnTags);
                }
                supportSQLiteStatement.bindLong(10, topicInfo.isVip);
                supportSQLiteStatement.bindLong(11, topicInfo.SportBackGround);
                supportSQLiteStatement.bindLong(12, topicInfo.isVaVip);
                supportSQLiteStatement.bindLong(13, topicInfo.isShield);
                supportSQLiteStatement.bindLong(14, topicInfo.VaScriptCount);
                if (topicInfo.sTags == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, topicInfo.sTags);
                }
                supportSQLiteStatement.bindLong(16, topicInfo.IsShowServerIcon);
                supportSQLiteStatement.bindLong(17, topicInfo.MatchType);
                supportSQLiteStatement.bindLong(18, topicInfo.OrderNum);
                if (topicInfo.MatchPackage == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, topicInfo.MatchPackage);
                }
                supportSQLiteStatement.bindLong(20, topicInfo.ShowOrder);
                supportSQLiteStatement.bindLong(21, topicInfo.LastBulletinID);
                supportSQLiteStatement.bindLong(22, topicInfo.ShowType);
                supportSQLiteStatement.bindLong(23, topicInfo.sportModel);
                supportSQLiteStatement.bindLong(24, topicInfo.isTop);
                if (topicInfo.localAppPackage == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, topicInfo.localAppPackage);
                }
                if (topicInfo.localAppName == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, topicInfo.localAppName);
                }
                if (topicInfo.sortLetters == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, topicInfo.sortLetters);
                }
                if (topicInfo.localVersionName == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, topicInfo.localVersionName);
                }
                supportSQLiteStatement.bindLong(29, topicInfo.time);
                supportSQLiteStatement.bindLong(30, topicInfo.BitSportCfg);
            }
        };
        this.f16217c = new EntityDeletionOrUpdateAdapter<TopicInfo>(roomDatabase) { // from class: z1.akq.2
            @Override // android.arch.persistence.room.EntityDeletionOrUpdateAdapter, android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `TopicInfo` WHERE `tid` = ?";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TopicInfo topicInfo) {
                supportSQLiteStatement.bindLong(1, topicInfo.tid);
            }
        };
        this.f16218d = new EntityDeletionOrUpdateAdapter<TopicInfo>(roomDatabase) { // from class: z1.akq.3
            @Override // android.arch.persistence.room.EntityDeletionOrUpdateAdapter, android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `TopicInfo` SET `tid` = ?,`TopicID` = ?,`Package` = ?,`PackageNames` = ?,`IfRecommend` = ?,`TopicName` = ?,`ImgPath` = ?,`ScriptCount` = ?,`FnTags` = ?,`isVip` = ?,`SportBackGround` = ?,`isVaVip` = ?,`isShield` = ?,`VaScriptCount` = ?,`sTags` = ?,`IsShowServerIcon` = ?,`MatchType` = ?,`OrderNum` = ?,`MatchPackage` = ?,`ShowOrder` = ?,`LastBulletinID` = ?,`ShowType` = ?,`sportModel` = ?,`isTop` = ?,`localAppPackage` = ?,`localAppName` = ?,`sortLetters` = ?,`localVersionName` = ?,`time` = ?,`BitSportCfg` = ? WHERE `tid` = ?";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TopicInfo topicInfo) {
                supportSQLiteStatement.bindLong(1, topicInfo.tid);
                supportSQLiteStatement.bindLong(2, topicInfo.TopicID);
                if (topicInfo.Package == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, topicInfo.Package);
                }
                if (topicInfo.PackageNames == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, topicInfo.PackageNames);
                }
                supportSQLiteStatement.bindLong(5, topicInfo.IfRecommend);
                if (topicInfo.TopicName == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, topicInfo.TopicName);
                }
                if (topicInfo.ImgPath == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, topicInfo.ImgPath);
                }
                supportSQLiteStatement.bindLong(8, topicInfo.ScriptCount);
                if (topicInfo.FnTags == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, topicInfo.FnTags);
                }
                supportSQLiteStatement.bindLong(10, topicInfo.isVip);
                supportSQLiteStatement.bindLong(11, topicInfo.SportBackGround);
                supportSQLiteStatement.bindLong(12, topicInfo.isVaVip);
                supportSQLiteStatement.bindLong(13, topicInfo.isShield);
                supportSQLiteStatement.bindLong(14, topicInfo.VaScriptCount);
                if (topicInfo.sTags == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, topicInfo.sTags);
                }
                supportSQLiteStatement.bindLong(16, topicInfo.IsShowServerIcon);
                supportSQLiteStatement.bindLong(17, topicInfo.MatchType);
                supportSQLiteStatement.bindLong(18, topicInfo.OrderNum);
                if (topicInfo.MatchPackage == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, topicInfo.MatchPackage);
                }
                supportSQLiteStatement.bindLong(20, topicInfo.ShowOrder);
                supportSQLiteStatement.bindLong(21, topicInfo.LastBulletinID);
                supportSQLiteStatement.bindLong(22, topicInfo.ShowType);
                supportSQLiteStatement.bindLong(23, topicInfo.sportModel);
                supportSQLiteStatement.bindLong(24, topicInfo.isTop);
                if (topicInfo.localAppPackage == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, topicInfo.localAppPackage);
                }
                if (topicInfo.localAppName == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, topicInfo.localAppName);
                }
                if (topicInfo.sortLetters == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, topicInfo.sortLetters);
                }
                if (topicInfo.localVersionName == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, topicInfo.localVersionName);
                }
                supportSQLiteStatement.bindLong(29, topicInfo.time);
                supportSQLiteStatement.bindLong(30, topicInfo.BitSportCfg);
                supportSQLiteStatement.bindLong(31, topicInfo.tid);
            }
        };
        this.f16219e = new SharedSQLiteStatement(roomDatabase) { // from class: z1.akq.4
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM TopicInfo where TopicID == ?";
            }
        };
    }

    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void mo12572a(TopicInfo topicInfo) {
        this.f16215a.beginTransaction();
        try {
            this.f16216b.insert((EntityInsertionAdapter) topicInfo);
            this.f16215a.setTransactionSuccessful();
        } finally {
            this.f16215a.endTransaction();
        }
    }

    @Override // p110z1.TopicInfoDao
    /* renamed from: a */
    public void mo12749a(List<TopicInfo> list) {
        this.f16215a.beginTransaction();
        try {
            this.f16216b.insert((Iterable) list);
            this.f16215a.setTransactionSuccessful();
        } finally {
            this.f16215a.endTransaction();
        }
    }

    /* renamed from: c  reason: avoid collision after fix types in other method */
    public void mo12570b(TopicInfo topicInfo) {
        this.f16215a.beginTransaction();
        try {
            this.f16217c.handle(topicInfo);
            this.f16215a.setTransactionSuccessful();
        } finally {
            this.f16215a.endTransaction();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // p110z1.TopicInfoDao
    /* renamed from: a */
    public int mo12750a(TopicInfo topicInfo) {
        this.f16215a.beginTransaction();
        try {
            int handle = this.f16217c.handle(topicInfo) + 0;
            this.f16215a.setTransactionSuccessful();
            return handle;
        } finally {
            this.f16215a.endTransaction();
        }
    }

    /* renamed from: d */
    public void mo12568c(TopicInfo topicInfo) {
        this.f16215a.beginTransaction();
        try {
            this.f16218d.handle(topicInfo);
            this.f16215a.setTransactionSuccessful();
        } finally {
            this.f16215a.endTransaction();
        }
    }

    @Override // p110z1.TopicInfoDao
    /* renamed from: b */
    public void mo12745b(List<TopicInfo> list) {
        this.f16215a.beginTransaction();
        try {
            this.f16218d.handleMultiple(list);
            this.f16215a.setTransactionSuccessful();
        } finally {
            this.f16215a.endTransaction();
        }
    }

    @Override // p110z1.TopicInfoDao
    /* renamed from: c */
    public void mo12744c(long j) {
        SupportSQLiteStatement acquire = this.f16219e.acquire();
        this.f16215a.beginTransaction();
        try {
            acquire.bindLong(1, j);
            acquire.executeUpdateDelete();
            this.f16215a.setTransactionSuccessful();
        } finally {
            this.f16215a.endTransaction();
            this.f16219e.release(acquire);
        }
    }

    @Override // p110z1.TopicInfoDao
    /* renamed from: a */
    public List<TopicInfo> mo12752a() {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from TopicInfo", 0);
        Cursor query = this.f16215a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow("tid");
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("TopicID");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("Package");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("PackageNames");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("IfRecommend");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("TopicName");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("ImgPath");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("ScriptCount");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("FnTags");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("isVip");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("SportBackGround");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("isVaVip");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("isShield");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("VaScriptCount");
            roomSQLiteQuery = acquire;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("sTags");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("IsShowServerIcon");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("MatchType");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("OrderNum");
            int columnIndexOrThrow19 = query.getColumnIndexOrThrow("MatchPackage");
            int columnIndexOrThrow20 = query.getColumnIndexOrThrow("ShowOrder");
            int columnIndexOrThrow21 = query.getColumnIndexOrThrow("LastBulletinID");
            int columnIndexOrThrow22 = query.getColumnIndexOrThrow("ShowType");
            int columnIndexOrThrow23 = query.getColumnIndexOrThrow("sportModel");
            int columnIndexOrThrow24 = query.getColumnIndexOrThrow("isTop");
            int columnIndexOrThrow25 = query.getColumnIndexOrThrow("localAppPackage");
            int columnIndexOrThrow26 = query.getColumnIndexOrThrow("localAppName");
            int columnIndexOrThrow27 = query.getColumnIndexOrThrow("sortLetters");
            int columnIndexOrThrow28 = query.getColumnIndexOrThrow("localVersionName");
            int columnIndexOrThrow29 = query.getColumnIndexOrThrow("time");
            int columnIndexOrThrow30 = query.getColumnIndexOrThrow("BitSportCfg");
            int i = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TopicInfo topicInfo = new TopicInfo();
                topicInfo.tid = query.getLong(columnIndexOrThrow);
                topicInfo.TopicID = query.getLong(columnIndexOrThrow2);
                topicInfo.Package = query.getString(columnIndexOrThrow3);
                topicInfo.PackageNames = query.getString(columnIndexOrThrow4);
                topicInfo.IfRecommend = query.getInt(columnIndexOrThrow5);
                topicInfo.TopicName = query.getString(columnIndexOrThrow6);
                topicInfo.ImgPath = query.getString(columnIndexOrThrow7);
                topicInfo.ScriptCount = query.getInt(columnIndexOrThrow8);
                topicInfo.FnTags = query.getString(columnIndexOrThrow9);
                topicInfo.isVip = query.getInt(columnIndexOrThrow10);
                topicInfo.SportBackGround = query.getInt(columnIndexOrThrow11);
                topicInfo.isVaVip = query.getInt(columnIndexOrThrow12);
                topicInfo.isShield = query.getInt(columnIndexOrThrow13);
                topicInfo.VaScriptCount = query.getInt(i);
                topicInfo.sTags = query.getString(columnIndexOrThrow15);
                topicInfo.IsShowServerIcon = query.getInt(columnIndexOrThrow16);
                topicInfo.MatchType = query.getInt(columnIndexOrThrow17);
                topicInfo.OrderNum = query.getInt(columnIndexOrThrow18);
                topicInfo.MatchPackage = query.getString(columnIndexOrThrow19);
                topicInfo.ShowOrder = query.getInt(columnIndexOrThrow20);
                topicInfo.LastBulletinID = query.getInt(columnIndexOrThrow21);
                topicInfo.ShowType = query.getInt(columnIndexOrThrow22);
                topicInfo.sportModel = query.getInt(columnIndexOrThrow23);
                topicInfo.isTop = query.getLong(columnIndexOrThrow24);
                topicInfo.localAppPackage = query.getString(columnIndexOrThrow25);
                topicInfo.localAppName = query.getString(columnIndexOrThrow26);
                topicInfo.sortLetters = query.getString(columnIndexOrThrow27);
                columnIndexOrThrow27 = columnIndexOrThrow27;
                topicInfo.localVersionName = query.getString(columnIndexOrThrow28);
                topicInfo.time = query.getLong(columnIndexOrThrow29);
                topicInfo.BitSportCfg = query.getInt(columnIndexOrThrow30);
                arrayList.add(topicInfo);
                columnIndexOrThrow30 = columnIndexOrThrow30;
                columnIndexOrThrow3 = columnIndexOrThrow3;
                columnIndexOrThrow24 = columnIndexOrThrow24;
                columnIndexOrThrow25 = columnIndexOrThrow25;
                columnIndexOrThrow26 = columnIndexOrThrow26;
                columnIndexOrThrow28 = columnIndexOrThrow28;
                columnIndexOrThrow2 = columnIndexOrThrow2;
                columnIndexOrThrow29 = columnIndexOrThrow29;
                i = i;
                columnIndexOrThrow15 = columnIndexOrThrow15;
                columnIndexOrThrow16 = columnIndexOrThrow16;
                columnIndexOrThrow17 = columnIndexOrThrow17;
                columnIndexOrThrow18 = columnIndexOrThrow18;
                columnIndexOrThrow19 = columnIndexOrThrow19;
                columnIndexOrThrow20 = columnIndexOrThrow20;
                columnIndexOrThrow21 = columnIndexOrThrow21;
                columnIndexOrThrow22 = columnIndexOrThrow22;
                columnIndexOrThrow = columnIndexOrThrow;
                columnIndexOrThrow23 = columnIndexOrThrow23;
            }
            query.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // p110z1.TopicInfoDao
    /* renamed from: b */
    public long mo12748b() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select Max(TopicID) from TopicInfo", 0);
        Cursor query = this.f16215a.query(acquire);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0L;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // p110z1.TopicInfoDao
    /* renamed from: a */
    public List<TopicInfo> mo12751a(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from TopicInfo where TopicID == ?", 1);
        acquire.bindLong(1, j);
        Cursor query = this.f16215a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("tid");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("TopicID");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("Package");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("PackageNames");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("IfRecommend");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("TopicName");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("ImgPath");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("ScriptCount");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("FnTags");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isVip");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("SportBackGround");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("isVaVip");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("isShield");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("VaScriptCount");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("sTags");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("IsShowServerIcon");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("MatchType");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("OrderNum");
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("MatchPackage");
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("ShowOrder");
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("LastBulletinID");
                int columnIndexOrThrow22 = query.getColumnIndexOrThrow("ShowType");
                int columnIndexOrThrow23 = query.getColumnIndexOrThrow("sportModel");
                int columnIndexOrThrow24 = query.getColumnIndexOrThrow("isTop");
                int columnIndexOrThrow25 = query.getColumnIndexOrThrow("localAppPackage");
                int columnIndexOrThrow26 = query.getColumnIndexOrThrow("localAppName");
                int columnIndexOrThrow27 = query.getColumnIndexOrThrow("sortLetters");
                int columnIndexOrThrow28 = query.getColumnIndexOrThrow("localVersionName");
                int columnIndexOrThrow29 = query.getColumnIndexOrThrow("time");
                int columnIndexOrThrow30 = query.getColumnIndexOrThrow("BitSportCfg");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    TopicInfo topicInfo = new TopicInfo();
                    topicInfo.tid = query.getLong(columnIndexOrThrow);
                    topicInfo.TopicID = query.getLong(columnIndexOrThrow2);
                    topicInfo.Package = query.getString(columnIndexOrThrow3);
                    topicInfo.PackageNames = query.getString(columnIndexOrThrow4);
                    topicInfo.IfRecommend = query.getInt(columnIndexOrThrow5);
                    topicInfo.TopicName = query.getString(columnIndexOrThrow6);
                    topicInfo.ImgPath = query.getString(columnIndexOrThrow7);
                    topicInfo.ScriptCount = query.getInt(columnIndexOrThrow8);
                    topicInfo.FnTags = query.getString(columnIndexOrThrow9);
                    topicInfo.isVip = query.getInt(columnIndexOrThrow10);
                    topicInfo.SportBackGround = query.getInt(columnIndexOrThrow11);
                    topicInfo.isVaVip = query.getInt(columnIndexOrThrow12);
                    topicInfo.isShield = query.getInt(columnIndexOrThrow13);
                    topicInfo.VaScriptCount = query.getInt(i);
                    topicInfo.sTags = query.getString(columnIndexOrThrow15);
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    topicInfo.IsShowServerIcon = query.getInt(columnIndexOrThrow16);
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    topicInfo.MatchType = query.getInt(columnIndexOrThrow17);
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    topicInfo.OrderNum = query.getInt(columnIndexOrThrow18);
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    topicInfo.MatchPackage = query.getString(columnIndexOrThrow19);
                    columnIndexOrThrow19 = columnIndexOrThrow19;
                    topicInfo.ShowOrder = query.getInt(columnIndexOrThrow20);
                    columnIndexOrThrow20 = columnIndexOrThrow20;
                    topicInfo.LastBulletinID = query.getInt(columnIndexOrThrow21);
                    columnIndexOrThrow21 = columnIndexOrThrow21;
                    topicInfo.ShowType = query.getInt(columnIndexOrThrow22);
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    topicInfo.sportModel = query.getInt(columnIndexOrThrow23);
                    topicInfo.isTop = query.getLong(columnIndexOrThrow24);
                    topicInfo.localAppPackage = query.getString(columnIndexOrThrow25);
                    topicInfo.localAppName = query.getString(columnIndexOrThrow26);
                    topicInfo.sortLetters = query.getString(columnIndexOrThrow27);
                    columnIndexOrThrow27 = columnIndexOrThrow27;
                    topicInfo.localVersionName = query.getString(columnIndexOrThrow28);
                    topicInfo.time = query.getLong(columnIndexOrThrow29);
                    topicInfo.BitSportCfg = query.getInt(columnIndexOrThrow30);
                    arrayList.add(topicInfo);
                    columnIndexOrThrow30 = columnIndexOrThrow30;
                    columnIndexOrThrow3 = columnIndexOrThrow3;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    columnIndexOrThrow24 = columnIndexOrThrow24;
                    columnIndexOrThrow25 = columnIndexOrThrow25;
                    columnIndexOrThrow26 = columnIndexOrThrow26;
                    columnIndexOrThrow28 = columnIndexOrThrow28;
                    columnIndexOrThrow2 = columnIndexOrThrow2;
                    i = i;
                    columnIndexOrThrow29 = columnIndexOrThrow29;
                    columnIndexOrThrow = columnIndexOrThrow;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // p110z1.TopicInfoDao
    /* renamed from: b */
    public TopicInfo mo12747b(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        TopicInfo topicInfo;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from TopicInfo where tid == ?", 1);
        acquire.bindLong(1, j);
        Cursor query = this.f16215a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("tid");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("TopicID");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("Package");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("PackageNames");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("IfRecommend");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("TopicName");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("ImgPath");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("ScriptCount");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("FnTags");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isVip");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("SportBackGround");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("isVaVip");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("isShield");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("VaScriptCount");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("sTags");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("IsShowServerIcon");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("MatchType");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("OrderNum");
                int columnIndexOrThrow19 = query.getColumnIndexOrThrow("MatchPackage");
                int columnIndexOrThrow20 = query.getColumnIndexOrThrow("ShowOrder");
                int columnIndexOrThrow21 = query.getColumnIndexOrThrow("LastBulletinID");
                int columnIndexOrThrow22 = query.getColumnIndexOrThrow("ShowType");
                int columnIndexOrThrow23 = query.getColumnIndexOrThrow("sportModel");
                int columnIndexOrThrow24 = query.getColumnIndexOrThrow("isTop");
                int columnIndexOrThrow25 = query.getColumnIndexOrThrow("localAppPackage");
                int columnIndexOrThrow26 = query.getColumnIndexOrThrow("localAppName");
                int columnIndexOrThrow27 = query.getColumnIndexOrThrow("sortLetters");
                int columnIndexOrThrow28 = query.getColumnIndexOrThrow("localVersionName");
                int columnIndexOrThrow29 = query.getColumnIndexOrThrow("time");
                int columnIndexOrThrow30 = query.getColumnIndexOrThrow("BitSportCfg");
                if (query.moveToFirst()) {
                    topicInfo = new TopicInfo();
                    topicInfo.tid = query.getLong(columnIndexOrThrow);
                    topicInfo.TopicID = query.getLong(columnIndexOrThrow2);
                    topicInfo.Package = query.getString(columnIndexOrThrow3);
                    topicInfo.PackageNames = query.getString(columnIndexOrThrow4);
                    topicInfo.IfRecommend = query.getInt(columnIndexOrThrow5);
                    topicInfo.TopicName = query.getString(columnIndexOrThrow6);
                    topicInfo.ImgPath = query.getString(columnIndexOrThrow7);
                    topicInfo.ScriptCount = query.getInt(columnIndexOrThrow8);
                    topicInfo.FnTags = query.getString(columnIndexOrThrow9);
                    topicInfo.isVip = query.getInt(columnIndexOrThrow10);
                    topicInfo.SportBackGround = query.getInt(columnIndexOrThrow11);
                    topicInfo.isVaVip = query.getInt(columnIndexOrThrow12);
                    topicInfo.isShield = query.getInt(columnIndexOrThrow13);
                    topicInfo.VaScriptCount = query.getInt(columnIndexOrThrow14);
                    topicInfo.sTags = query.getString(columnIndexOrThrow15);
                    topicInfo.IsShowServerIcon = query.getInt(columnIndexOrThrow16);
                    topicInfo.MatchType = query.getInt(columnIndexOrThrow17);
                    topicInfo.OrderNum = query.getInt(columnIndexOrThrow18);
                    topicInfo.MatchPackage = query.getString(columnIndexOrThrow19);
                    topicInfo.ShowOrder = query.getInt(columnIndexOrThrow20);
                    topicInfo.LastBulletinID = query.getInt(columnIndexOrThrow21);
                    topicInfo.ShowType = query.getInt(columnIndexOrThrow22);
                    topicInfo.sportModel = query.getInt(columnIndexOrThrow23);
                    topicInfo.isTop = query.getLong(columnIndexOrThrow24);
                    topicInfo.localAppPackage = query.getString(columnIndexOrThrow25);
                    topicInfo.localAppName = query.getString(columnIndexOrThrow26);
                    topicInfo.sortLetters = query.getString(columnIndexOrThrow27);
                    topicInfo.localVersionName = query.getString(columnIndexOrThrow28);
                    topicInfo.time = query.getLong(columnIndexOrThrow29);
                    topicInfo.BitSportCfg = query.getInt(columnIndexOrThrow30);
                } else {
                    topicInfo = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return topicInfo;
            } catch (Throwable th2) {
                th = th2;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            roomSQLiteQuery = acquire;
        }
    }
}
