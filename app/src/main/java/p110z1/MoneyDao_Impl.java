package p110z1;

import android.arch.persistence.p000db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.nrzs.data.redbag.bean.MoneyInfo;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.taskdefs.WaitFor;

/* renamed from: z1.alo */
/* loaded from: classes3.dex */
public class MoneyDao_Impl implements MoneyDao {

    /* renamed from: a */
    private final RoomDatabase f16364a;

    /* renamed from: b */
    private final EntityInsertionAdapter f16365b;

    /* renamed from: c */
    private final EntityDeletionOrUpdateAdapter f16366c;

    /* renamed from: d */
    private final EntityDeletionOrUpdateAdapter f16367d;

    public MoneyDao_Impl(RoomDatabase roomDatabase) {
        this.f16364a = roomDatabase;
        this.f16365b = new EntityInsertionAdapter<MoneyInfo>(roomDatabase) { // from class: z1.alo.1
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `MoneyInfo`(`tid`,`day`,`time`,`username`,`money`,`countMoeny`,`zje`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MoneyInfo moneyInfo) {
                supportSQLiteStatement.bindLong(1, moneyInfo.f10651b);
                if (moneyInfo.f10652c == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, moneyInfo.f10652c);
                }
                if (moneyInfo.f10653d == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, moneyInfo.f10653d);
                }
                if (moneyInfo.f10654e == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, moneyInfo.f10654e);
                }
                if (moneyInfo.f10655f == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, moneyInfo.f10655f);
                }
                if (moneyInfo.f10656g == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, moneyInfo.f10656g);
                }
                if (moneyInfo.f10657h == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, moneyInfo.f10657h);
                }
            }
        };
        this.f16366c = new EntityDeletionOrUpdateAdapter<MoneyInfo>(roomDatabase) { // from class: z1.alo.2
            @Override // android.arch.persistence.room.EntityDeletionOrUpdateAdapter, android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `MoneyInfo` WHERE `tid` = ?";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MoneyInfo moneyInfo) {
                supportSQLiteStatement.bindLong(1, moneyInfo.f10651b);
            }
        };
        this.f16367d = new EntityDeletionOrUpdateAdapter<MoneyInfo>(roomDatabase) { // from class: z1.alo.3
            @Override // android.arch.persistence.room.EntityDeletionOrUpdateAdapter, android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `MoneyInfo` SET `tid` = ?,`day` = ?,`time` = ?,`username` = ?,`money` = ?,`countMoeny` = ?,`zje` = ? WHERE `tid` = ?";
            }

            /* renamed from: a */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MoneyInfo moneyInfo) {
                supportSQLiteStatement.bindLong(1, moneyInfo.f10651b);
                if (moneyInfo.f10652c == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, moneyInfo.f10652c);
                }
                if (moneyInfo.f10653d == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, moneyInfo.f10653d);
                }
                if (moneyInfo.f10654e == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, moneyInfo.f10654e);
                }
                if (moneyInfo.f10655f == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, moneyInfo.f10655f);
                }
                if (moneyInfo.f10656g == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, moneyInfo.f10656g);
                }
                if (moneyInfo.f10657h == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, moneyInfo.f10657h);
                }
                supportSQLiteStatement.bindLong(8, moneyInfo.f10651b);
            }
        };
    }

    /* renamed from: c  reason: avoid collision after fix types in other method */
    public void mo12572a(MoneyInfo moneyInfo) {
        this.f16364a.beginTransaction();
        try {
            this.f16365b.insert((EntityInsertionAdapter) moneyInfo);
            this.f16364a.setTransactionSuccessful();
        } finally {
            this.f16364a.endTransaction();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // p110z1.MoneyDao
    /* renamed from: b */
    public void mo12571b(MoneyInfo moneyInfo) {
        this.f16364a.beginTransaction();
        try {
            this.f16365b.insert((EntityInsertionAdapter) moneyInfo);
            this.f16364a.setTransactionSuccessful();
        } finally {
            this.f16364a.endTransaction();
        }
    }

    /* renamed from: d */
    public void mo12570b(MoneyInfo moneyInfo) {
        this.f16364a.beginTransaction();
        try {
            this.f16366c.handle(moneyInfo);
            this.f16364a.setTransactionSuccessful();
        } finally {
            this.f16364a.endTransaction();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // p110z1.MoneyDao
    /* renamed from: a */
    public int mo12573a(MoneyInfo moneyInfo) {
        this.f16364a.beginTransaction();
        try {
            int handle = this.f16366c.handle(moneyInfo) + 0;
            this.f16364a.setTransactionSuccessful();
            return handle;
        } finally {
            this.f16364a.endTransaction();
        }
    }

    /* renamed from: e */
    public void mo12568c(MoneyInfo moneyInfo) {
        this.f16364a.beginTransaction();
        try {
            this.f16367d.handle(moneyInfo);
            this.f16364a.setTransactionSuccessful();
        } finally {
            this.f16364a.endTransaction();
        }
    }

    @Override // p110z1.MoneyDao
    /* renamed from: a */
    public List<MoneyInfo> mo12574a() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from MoneyInfo", 0);
        Cursor query = this.f16364a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("tid");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow(WaitFor.Unit.DAY);
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("username");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("money");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("countMoeny");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("zje");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                MoneyInfo moneyInfo = new MoneyInfo();
                moneyInfo.f10651b = query.getLong(columnIndexOrThrow);
                moneyInfo.f10652c = query.getString(columnIndexOrThrow2);
                moneyInfo.f10653d = query.getString(columnIndexOrThrow3);
                moneyInfo.f10654e = query.getString(columnIndexOrThrow4);
                moneyInfo.f10655f = query.getString(columnIndexOrThrow5);
                moneyInfo.f10656g = query.getString(columnIndexOrThrow6);
                moneyInfo.f10657h = query.getString(columnIndexOrThrow7);
                arrayList.add(moneyInfo);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
