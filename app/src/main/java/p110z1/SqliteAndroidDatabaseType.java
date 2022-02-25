package p110z1;

import java.sql.SQLException;

/* renamed from: z1.sj */
/* loaded from: classes3.dex */
public class SqliteAndroidDatabaseType extends BaseSqliteDatabaseType implements DatabaseType {
    @Override // p110z1.BaseDatabaseType
    /* renamed from: a */
    protected String mo880a() {
        return null;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public boolean mo879a(String str, String str2) {
        return true;
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: b */
    public void mo876b() {
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: n */
    public boolean mo873n() {
        return false;
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: p */
    public boolean mo872p() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: v */
    public String mo871v() {
        return "Android SQLite";
    }

    @Override // p110z1.BaseDatabaseType
    /* renamed from: c */
    protected void mo875c(StringBuilder sb, FieldType ssVar, int i) {
        m926a(sb, ssVar, i);
    }

    @Override // p110z1.BaseDatabaseType
    /* renamed from: d */
    protected void mo874d(StringBuilder sb, FieldType ssVar, int i) {
        m918g(sb, ssVar, i);
    }

    /* compiled from: SqliteAndroidDatabaseType.java */
    /* renamed from: z1.sj$1 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C55611 {

        /* renamed from: a */
        static final /* synthetic */ int[] f23244a = new int[SqlType.values().length];

        static {
            try {
                f23244a[SqlType.DATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // p110z1.BaseSqliteDatabaseType, p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: a */
    public FieldConverter mo878a(DataPersister slVar) {
        if (C55611.f23244a[slVar.getSqlType().ordinal()] != 1) {
            return super.mo878a(slVar);
        }
        return DateStringType.m656a();
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: a */
    public <T> DatabaseTableConfig<T> mo877a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        return DatabaseTableConfigUtil.m1132a(wmVar, cls);
    }
}
