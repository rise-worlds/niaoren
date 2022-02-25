package p110z1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import p110z1.StatementBuilder;

/* renamed from: z1.rj */
/* loaded from: classes3.dex */
public class AndroidCompiledStatement implements CompiledStatement {

    /* renamed from: a */
    private static C5570ui f23122a = LoggerFactory.m545a(AndroidCompiledStatement.class);

    /* renamed from: e */
    private static final String[] f23123e = new String[0];

    /* renamed from: b */
    private final String f23124b;

    /* renamed from: c */
    private final SQLiteDatabase f23125c;

    /* renamed from: d */
    private final StatementBuilder.EnumC5579b f23126d;

    /* renamed from: f */
    private Cursor f23127f;

    /* renamed from: g */
    private List<Object> f23128g;

    /* renamed from: h */
    private Integer f23129h;

    @Override // p110z1.CompiledStatement
    /* renamed from: a */
    public void mo263a(long j) {
    }

    public AndroidCompiledStatement(String str, SQLiteDatabase sQLiteDatabase, StatementBuilder.EnumC5579b bVar) {
        this.f23124b = str;
        this.f23125c = sQLiteDatabase;
        this.f23126d = bVar;
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: a */
    public int mo266a() throws SQLException {
        return m1152f().getColumnCount();
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: a */
    public String mo265a(int i) throws SQLException {
        return m1152f().getColumnName(i);
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: a */
    public DatabaseResults mo262a(ObjectCache scVar) throws SQLException {
        if (this.f23126d.isOkForQuery()) {
            return new AndroidDatabaseResults(m1152f(), scVar);
        }
        throw new IllegalArgumentException("Cannot call query on a " + this.f23126d + " statement");
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: b */
    public int mo261b() throws SQLException {
        String str;
        if (this.f23126d.isOkForUpdate()) {
            if (this.f23129h == null) {
                str = this.f23124b;
            } else {
                str = this.f23124b + ExpandableTextView.f6958c + this.f23129h;
            }
            return m1153a(this.f23125c, "runUpdate", str, m1150h());
        }
        throw new IllegalArgumentException("Cannot call update on a " + this.f23126d + " statement");
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: c */
    public int mo259c() throws SQLException {
        if (this.f23126d.isOkForExecute()) {
            return m1153a(this.f23125c, "runExecute", this.f23124b, m1150h());
        }
        throw new IllegalArgumentException("Cannot call execute on a " + this.f23126d + " statement");
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: d */
    public void mo258d() throws SQLException {
        Cursor cursor = this.f23127f;
        if (cursor != null) {
            try {
                cursor.close();
            } catch (android.database.SQLException e) {
                throw SqlExceptionUtil.m529a("Problems closing Android cursor", e);
            }
        }
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: e */
    public void mo257e() {
        try {
            mo258d();
        } catch (SQLException unused) {
        }
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: a */
    public void mo264a(int i, Object obj, SqlType suVar) throws SQLException {
        m1151g();
        if (this.f23128g == null) {
            this.f23128g = new ArrayList();
        }
        if (obj == null) {
            this.f23128g.add(i, null);
            return;
        }
        switch (suVar) {
            case STRING:
            case LONG_STRING:
            case DATE:
            case BOOLEAN:
            case CHAR:
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
                this.f23128g.add(i, obj.toString());
                return;
            case BYTE_ARRAY:
            case SERIALIZABLE:
                this.f23128g.add(i, obj);
                return;
            case BLOB:
            case BIG_DECIMAL:
                throw new SQLException("Invalid Android type: " + suVar);
            default:
                throw new SQLException("Unknown sql argument type: " + suVar);
        }
    }

    @Override // p110z1.CompiledStatement
    /* renamed from: b */
    public void mo260b(int i) throws SQLException {
        m1151g();
        this.f23129h = Integer.valueOf(i);
    }

    /* renamed from: f */
    public Cursor m1152f() throws SQLException {
        if (this.f23127f == null) {
            String str = null;
            try {
                if (this.f23129h == null) {
                    str = this.f23124b;
                } else {
                    str = this.f23124b + ExpandableTextView.f6958c + this.f23129h;
                }
                this.f23127f = this.f23125c.rawQuery(str, m1149i());
                this.f23127f.moveToFirst();
                f23122a.m618a("{}: started rawQuery cursor for: {}", this, str);
            } catch (android.database.SQLException e) {
                throw SqlExceptionUtil.m529a("Problems executing Android query: " + str, e);
            }
        }
        return this.f23127f;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (r5 == null) goto L_0x0021;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
        p110z1.AndroidCompiledStatement.f23122a.m617a("executing statement {} changed {} rows: {}", r3, java.lang.Integer.valueOf(r2), r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r5 != null) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0011, code lost:
        r5.close();
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m1153a(android.database.sqlite.SQLiteDatabase r2, java.lang.String r3, java.lang.String r4, java.lang.Object[] r5) throws java.sql.SQLException {
        /*
            r2.execSQL(r4, r5)     // Catch: SQLException -> 0x002d
            r5 = 0
            java.lang.String r0 = "SELECT CHANGES()"
            android.database.sqlite.SQLiteStatement r5 = r2.compileStatement(r0)     // Catch: all -> 0x0015, SQLException -> 0x001c
            long r0 = r5.simpleQueryForLong()     // Catch: all -> 0x0015, SQLException -> 0x001c
            int r2 = (int) r0
            if (r5 == 0) goto L_0x0021
        L_0x0011:
            r5.close()
            goto L_0x0021
        L_0x0015:
            r2 = move-exception
            if (r5 == 0) goto L_0x001b
            r5.close()
        L_0x001b:
            throw r2
        L_0x001c:
            r2 = 1
            if (r5 == 0) goto L_0x0021
            goto L_0x0011
        L_0x0021:
            z1.ui r5 = p110z1.AndroidCompiledStatement.f23122a
            java.lang.String r0 = "executing statement {} changed {} rows: {}"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r5.m617a(r0, r3, r1, r4)
            return r2
        L_0x002d:
            r2 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Problems executing "
            r5.append(r0)
            r5.append(r3)
            java.lang.String r3 = " Android statement: "
            r5.append(r3)
            r5.append(r4)
            java.lang.String r3 = r5.toString()
            java.sql.SQLException r2 = p110z1.SqlExceptionUtil.m529a(r3, r2)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AndroidCompiledStatement.m1153a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, java.lang.Object[]):int");
    }

    /* renamed from: g */
    private void m1151g() throws SQLException {
        if (this.f23127f != null) {
            throw new SQLException("Query already run. Cannot add argument values.");
        }
    }

    /* renamed from: h */
    private Object[] m1150h() {
        List<Object> list = this.f23128g;
        if (list == null) {
            return f23123e;
        }
        return list.toArray(new Object[list.size()]);
    }

    /* renamed from: i */
    private String[] m1149i() {
        List<Object> list = this.f23128g;
        if (list == null) {
            return f23123e;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}
