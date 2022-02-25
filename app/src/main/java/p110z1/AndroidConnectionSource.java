package p110z1;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;

/* renamed from: z1.rk */
/* loaded from: classes3.dex */
public class AndroidConnectionSource extends BaseConnectionSource implements ConnectionSource {

    /* renamed from: a */
    private static final C5570ui f23131a = LoggerFactory.m545a(AndroidConnectionSource.class);

    /* renamed from: b */
    private final SQLiteOpenHelper f23132b;

    /* renamed from: c */
    private final SQLiteDatabase f23133c;

    /* renamed from: d */
    private AndroidDatabaseConnection f23134d;

    /* renamed from: e */
    private volatile boolean f23135e;

    /* renamed from: f */
    private final DatabaseType f23136f;

    @Override // p110z1.ConnectionSource
    /* renamed from: a */
    public void mo255a(DatabaseConnection wnVar) {
    }

    public AndroidConnectionSource(SQLiteOpenHelper sQLiteOpenHelper) {
        this.f23134d = null;
        this.f23135e = true;
        this.f23136f = new SqliteAndroidDatabaseType();
        this.f23132b = sQLiteOpenHelper;
        this.f23133c = null;
    }

    public AndroidConnectionSource(SQLiteDatabase sQLiteDatabase) {
        this.f23134d = null;
        this.f23135e = true;
        this.f23136f = new SqliteAndroidDatabaseType();
        this.f23132b = null;
        this.f23133c = sQLiteDatabase;
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: a */
    public DatabaseConnection mo256a() throws SQLException {
        return mo254b();
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: b */
    public DatabaseConnection mo254b() throws SQLException {
        DatabaseConnection h = m269h();
        if (h != null) {
            return h;
        }
        AndroidDatabaseConnection rlVar = this.f23134d;
        if (rlVar == null) {
            SQLiteDatabase sQLiteDatabase = this.f23133c;
            if (sQLiteDatabase == null) {
                try {
                    sQLiteDatabase = this.f23132b.getWritableDatabase();
                } catch (android.database.SQLException e) {
                    throw SqlExceptionUtil.m529a("Getting a writable database from helper " + this.f23132b + " failed", e);
                }
            }
            this.f23134d = new AndroidDatabaseConnection(sQLiteDatabase, true);
            f23131a.m617a("created connection {} for db {}, helper {}", this.f23134d, sQLiteDatabase, this.f23132b);
        } else {
            f23131a.m617a("{}: returning read-write connection {}, helper {}", this, rlVar, this.f23132b);
        }
        return this.f23134d;
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: b */
    public boolean mo253b(DatabaseConnection wnVar) throws SQLException {
        return m270e(wnVar);
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: c */
    public void mo251c(DatabaseConnection wnVar) {
        m272a(wnVar, f23131a);
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: c */
    public void mo252c() {
        this.f23135e = false;
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: d */
    public void mo250d() {
        mo252c();
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: e */
    public DatabaseType mo249e() {
        return this.f23136f;
    }

    @Override // p110z1.ConnectionSource
    /* renamed from: f */
    public boolean mo248f() {
        return this.f23135e;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }
}
