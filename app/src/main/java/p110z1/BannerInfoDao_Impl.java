package p110z1;

import android.arch.persistence.p000db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.nrzs.data.other.bean.AdResultInfoItem;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.ako */
/* loaded from: classes3.dex */
public class BannerInfoDao_Impl implements BannerInfoDao {

    /* renamed from: a */
    private final RoomDatabase f16204a;

    /* renamed from: b */
    private final EntityInsertionAdapter f16205b;

    /* renamed from: c */
    private final EntityDeletionOrUpdateAdapter f16206c;

    /* renamed from: d */
    private final EntityDeletionOrUpdateAdapter f16207d;

    /* renamed from: e */
    private final SharedSQLiteStatement f16208e;

    /* renamed from: f */
    private final SharedSQLiteStatement f16209f;

    public BannerInfoDao_Impl(RoomDatabase roomDatabase) {
        this.f16204a = roomDatabase;
        this.f16205b = new EntityInsertionAdapter<AdResultInfoItem>(roomDatabase) { // from class: z1.ako.1
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `AdResultInfoItem`(`tid`,`Id`,`Appid`,`AdName`,`ImgUrl`,`Title`,`SubTitle`,`ExecCommand`,`ExecArgs`,`EffectiveTime`,`FailureTime`,`CreateTime`,`UserType`,`BigImg`,`AdPosition`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AdResultInfoItem adResultInfoItem) {
                supportSQLiteStatement.bindLong(1, adResultInfoItem.tid);
                supportSQLiteStatement.bindLong(2, adResultInfoItem.f10647Id);
                if (adResultInfoItem.Appid == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, adResultInfoItem.Appid);
                }
                if (adResultInfoItem.AdName == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, adResultInfoItem.AdName);
                }
                if (adResultInfoItem.ImgUrl == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, adResultInfoItem.ImgUrl);
                }
                if (adResultInfoItem.Title == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, adResultInfoItem.Title);
                }
                if (adResultInfoItem.SubTitle == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, adResultInfoItem.SubTitle);
                }
                if (adResultInfoItem.ExecCommand == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, adResultInfoItem.ExecCommand);
                }
                if (adResultInfoItem.ExecArgs == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, adResultInfoItem.ExecArgs);
                }
                if (adResultInfoItem.EffectiveTime == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, adResultInfoItem.EffectiveTime);
                }
                if (adResultInfoItem.FailureTime == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, adResultInfoItem.FailureTime);
                }
                if (adResultInfoItem.CreateTime == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, adResultInfoItem.CreateTime);
                }
                supportSQLiteStatement.bindLong(13, adResultInfoItem.UserType);
                if (adResultInfoItem.BigImg == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, adResultInfoItem.BigImg);
                }
                supportSQLiteStatement.bindLong(15, adResultInfoItem.AdPosition);
            }
        };
        this.f16206c = new EntityDeletionOrUpdateAdapter<AdResultInfoItem>(roomDatabase) { // from class: z1.ako.2
            @Override // android.arch.persistence.room.EntityDeletionOrUpdateAdapter, android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `AdResultInfoItem` WHERE `tid` = ?";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AdResultInfoItem adResultInfoItem) {
                supportSQLiteStatement.bindLong(1, adResultInfoItem.tid);
            }
        };
        this.f16207d = new EntityDeletionOrUpdateAdapter<AdResultInfoItem>(roomDatabase) { // from class: z1.ako.3
            @Override // android.arch.persistence.room.EntityDeletionOrUpdateAdapter, android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `AdResultInfoItem` SET `tid` = ?,`Id` = ?,`Appid` = ?,`AdName` = ?,`ImgUrl` = ?,`Title` = ?,`SubTitle` = ?,`ExecCommand` = ?,`ExecArgs` = ?,`EffectiveTime` = ?,`FailureTime` = ?,`CreateTime` = ?,`UserType` = ?,`BigImg` = ?,`AdPosition` = ? WHERE `tid` = ?";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AdResultInfoItem adResultInfoItem) {
                supportSQLiteStatement.bindLong(1, adResultInfoItem.tid);
                supportSQLiteStatement.bindLong(2, adResultInfoItem.f10647Id);
                if (adResultInfoItem.Appid == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, adResultInfoItem.Appid);
                }
                if (adResultInfoItem.AdName == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, adResultInfoItem.AdName);
                }
                if (adResultInfoItem.ImgUrl == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, adResultInfoItem.ImgUrl);
                }
                if (adResultInfoItem.Title == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, adResultInfoItem.Title);
                }
                if (adResultInfoItem.SubTitle == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, adResultInfoItem.SubTitle);
                }
                if (adResultInfoItem.ExecCommand == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, adResultInfoItem.ExecCommand);
                }
                if (adResultInfoItem.ExecArgs == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, adResultInfoItem.ExecArgs);
                }
                if (adResultInfoItem.EffectiveTime == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, adResultInfoItem.EffectiveTime);
                }
                if (adResultInfoItem.FailureTime == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, adResultInfoItem.FailureTime);
                }
                if (adResultInfoItem.CreateTime == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, adResultInfoItem.CreateTime);
                }
                supportSQLiteStatement.bindLong(13, adResultInfoItem.UserType);
                if (adResultInfoItem.BigImg == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, adResultInfoItem.BigImg);
                }
                supportSQLiteStatement.bindLong(15, adResultInfoItem.AdPosition);
                supportSQLiteStatement.bindLong(16, adResultInfoItem.tid);
            }
        };
        this.f16208e = new SharedSQLiteStatement(roomDatabase) { // from class: z1.ako.4
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM AdResultInfoItem";
            }
        };
        this.f16209f = new SharedSQLiteStatement(roomDatabase) { // from class: z1.ako.5
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM AdResultInfoItem where AdPosition == ?";
            }
        };
    }

    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void mo12572a(AdResultInfoItem adResultInfoItem) {
        this.f16204a.beginTransaction();
        try {
            this.f16205b.insert((EntityInsertionAdapter) adResultInfoItem);
            this.f16204a.setTransactionSuccessful();
        } finally {
            this.f16204a.endTransaction();
        }
    }

    @Override // p110z1.BannerInfoDao
    /* renamed from: a */
    public void mo12761a(List<AdResultInfoItem> list) {
        this.f16204a.beginTransaction();
        try {
            this.f16205b.insert((Iterable) list);
            this.f16204a.setTransactionSuccessful();
        } finally {
            this.f16204a.endTransaction();
        }
    }

    /* renamed from: c  reason: avoid collision after fix types in other method */
    public void mo12570b(AdResultInfoItem adResultInfoItem) {
        this.f16204a.beginTransaction();
        try {
            this.f16206c.handle(adResultInfoItem);
            this.f16204a.setTransactionSuccessful();
        } finally {
            this.f16204a.endTransaction();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // p110z1.BannerInfoDao
    /* renamed from: a */
    public int mo12762a(AdResultInfoItem adResultInfoItem) {
        this.f16204a.beginTransaction();
        try {
            int handle = this.f16206c.handle(adResultInfoItem) + 0;
            this.f16204a.setTransactionSuccessful();
            return handle;
        } finally {
            this.f16204a.endTransaction();
        }
    }

    /* renamed from: d */
    public void mo12568c(AdResultInfoItem adResultInfoItem) {
        this.f16204a.beginTransaction();
        try {
            this.f16207d.handle(adResultInfoItem);
            this.f16204a.setTransactionSuccessful();
        } finally {
            this.f16204a.endTransaction();
        }
    }

    @Override // p110z1.BannerInfoDao
    /* renamed from: b */
    public void mo12760b() {
        SupportSQLiteStatement acquire = this.f16208e.acquire();
        this.f16204a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f16204a.setTransactionSuccessful();
        } finally {
            this.f16204a.endTransaction();
            this.f16208e.release(acquire);
        }
    }

    @Override // p110z1.BannerInfoDao
    /* renamed from: b */
    public void mo12759b(int i) {
        SupportSQLiteStatement acquire = this.f16209f.acquire();
        this.f16204a.beginTransaction();
        try {
            acquire.bindLong(1, i);
            acquire.executeUpdateDelete();
            this.f16204a.setTransactionSuccessful();
        } finally {
            this.f16204a.endTransaction();
            this.f16209f.release(acquire);
        }
    }

    @Override // p110z1.BannerInfoDao
    /* renamed from: a */
    public List<AdResultInfoItem> mo12765a() {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from AdResultInfoItem", 0);
        Cursor query = this.f16204a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("tid");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("Id");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("Appid");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("AdName");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("ImgUrl");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("Title");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("SubTitle");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("ExecCommand");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("ExecArgs");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("EffectiveTime");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("FailureTime");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("CreateTime");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("UserType");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("BigImg");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("AdPosition");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                    adResultInfoItem.tid = query.getLong(columnIndexOrThrow);
                    adResultInfoItem.f10647Id = query.getInt(columnIndexOrThrow2);
                    adResultInfoItem.Appid = query.getString(columnIndexOrThrow3);
                    adResultInfoItem.AdName = query.getString(columnIndexOrThrow4);
                    adResultInfoItem.ImgUrl = query.getString(columnIndexOrThrow5);
                    adResultInfoItem.Title = query.getString(columnIndexOrThrow6);
                    adResultInfoItem.SubTitle = query.getString(columnIndexOrThrow7);
                    adResultInfoItem.ExecCommand = query.getString(columnIndexOrThrow8);
                    adResultInfoItem.ExecArgs = query.getString(columnIndexOrThrow9);
                    adResultInfoItem.EffectiveTime = query.getString(columnIndexOrThrow10);
                    adResultInfoItem.FailureTime = query.getString(columnIndexOrThrow11);
                    adResultInfoItem.CreateTime = query.getString(columnIndexOrThrow12);
                    adResultInfoItem.UserType = query.getInt(columnIndexOrThrow13);
                    adResultInfoItem.BigImg = query.getString(i);
                    adResultInfoItem.AdPosition = query.getInt(columnIndexOrThrow15);
                    arrayList.add(adResultInfoItem);
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    columnIndexOrThrow2 = columnIndexOrThrow2;
                    i = i;
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

    @Override // p110z1.BannerInfoDao
    /* renamed from: a */
    public List<AdResultInfoItem> mo12764a(int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from AdResultInfoItem where AdPosition == ?", 1);
        acquire.bindLong(1, i);
        Cursor query = this.f16204a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("tid");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("Id");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("Appid");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("AdName");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("ImgUrl");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("Title");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("SubTitle");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("ExecCommand");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("ExecArgs");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("EffectiveTime");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("FailureTime");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("CreateTime");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("UserType");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("BigImg");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("AdPosition");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                    adResultInfoItem.tid = query.getLong(columnIndexOrThrow);
                    adResultInfoItem.f10647Id = query.getInt(columnIndexOrThrow2);
                    adResultInfoItem.Appid = query.getString(columnIndexOrThrow3);
                    adResultInfoItem.AdName = query.getString(columnIndexOrThrow4);
                    adResultInfoItem.ImgUrl = query.getString(columnIndexOrThrow5);
                    adResultInfoItem.Title = query.getString(columnIndexOrThrow6);
                    adResultInfoItem.SubTitle = query.getString(columnIndexOrThrow7);
                    adResultInfoItem.ExecCommand = query.getString(columnIndexOrThrow8);
                    adResultInfoItem.ExecArgs = query.getString(columnIndexOrThrow9);
                    adResultInfoItem.EffectiveTime = query.getString(columnIndexOrThrow10);
                    adResultInfoItem.FailureTime = query.getString(columnIndexOrThrow11);
                    adResultInfoItem.CreateTime = query.getString(columnIndexOrThrow12);
                    adResultInfoItem.UserType = query.getInt(columnIndexOrThrow13);
                    adResultInfoItem.BigImg = query.getString(i2);
                    adResultInfoItem.AdPosition = query.getInt(columnIndexOrThrow15);
                    arrayList.add(adResultInfoItem);
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    columnIndexOrThrow2 = columnIndexOrThrow2;
                    columnIndexOrThrow = columnIndexOrThrow;
                    i2 = i2;
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

    @Override // p110z1.BannerInfoDao
    /* renamed from: a */
    public List<AdResultInfoItem> mo12763a(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        Throwable th;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from AdResultInfoItem where id == ?", 1);
        acquire.bindLong(1, j);
        Cursor query = this.f16204a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("tid");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("Id");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("Appid");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("AdName");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("ImgUrl");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("Title");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("SubTitle");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("ExecCommand");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("ExecArgs");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("EffectiveTime");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("FailureTime");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("CreateTime");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("UserType");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("BigImg");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("AdPosition");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                    adResultInfoItem.tid = query.getLong(columnIndexOrThrow);
                    adResultInfoItem.f10647Id = query.getInt(columnIndexOrThrow2);
                    adResultInfoItem.Appid = query.getString(columnIndexOrThrow3);
                    adResultInfoItem.AdName = query.getString(columnIndexOrThrow4);
                    adResultInfoItem.ImgUrl = query.getString(columnIndexOrThrow5);
                    adResultInfoItem.Title = query.getString(columnIndexOrThrow6);
                    adResultInfoItem.SubTitle = query.getString(columnIndexOrThrow7);
                    adResultInfoItem.ExecCommand = query.getString(columnIndexOrThrow8);
                    adResultInfoItem.ExecArgs = query.getString(columnIndexOrThrow9);
                    adResultInfoItem.EffectiveTime = query.getString(columnIndexOrThrow10);
                    adResultInfoItem.FailureTime = query.getString(columnIndexOrThrow11);
                    adResultInfoItem.CreateTime = query.getString(columnIndexOrThrow12);
                    adResultInfoItem.UserType = query.getInt(columnIndexOrThrow13);
                    adResultInfoItem.BigImg = query.getString(i);
                    adResultInfoItem.AdPosition = query.getInt(columnIndexOrThrow15);
                    arrayList.add(adResultInfoItem);
                    columnIndexOrThrow15 = columnIndexOrThrow15;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow2 = columnIndexOrThrow2;
                    i = i;
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
}
