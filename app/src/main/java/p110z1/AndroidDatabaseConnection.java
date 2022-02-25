package p110z1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import p110z1.StatementBuilder;

/* renamed from: z1.rl */
/* loaded from: classes3.dex */
public class AndroidDatabaseConnection implements DatabaseConnection {

    /* renamed from: c */
    private static final String f23137c = "VERSION__4.46__";

    /* renamed from: d */
    private static C5570ui f23138d = LoggerFactory.m545a(AndroidDatabaseConnection.class);

    /* renamed from: e */
    private static final String[] f23139e = new String[0];

    /* renamed from: f */
    private final SQLiteDatabase f23140f;

    /* renamed from: g */
    private final boolean f23141g;

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public boolean mo246a() {
        return true;
    }

    static {
        C5574uo.m514b(f23137c);
    }

    public AndroidDatabaseConnection(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.f23140f = sQLiteDatabase;
        this.f23141g = z;
        f23138d.m617a("{}: db {} opened, read-write = {}", this, sQLiteDatabase, Boolean.valueOf(z));
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: b */
    public boolean mo236b() throws SQLException {
        try {
            boolean inTransaction = this.f23140f.inTransaction();
            f23138d.m618a("{}: in transaction is {}", this, Boolean.valueOf(inTransaction));
            return !inTransaction;
        } catch (android.database.SQLException e) {
            throw SqlExceptionUtil.m529a("problems getting auto-commit from database", e);
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public void mo237a(boolean z) {
        if (z) {
            if (this.f23140f.inTransaction()) {
                this.f23140f.setTransactionSuccessful();
                this.f23140f.endTransaction();
            }
        } else if (!this.f23140f.inTransaction()) {
            this.f23140f.beginTransaction();
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public Savepoint mo245a(String str) throws SQLException {
        try {
            this.f23140f.beginTransaction();
            f23138d.m618a("{}: save-point set with name {}", this, str);
            return new C5542a(str);
        } catch (android.database.SQLException e) {
            throw SqlExceptionUtil.m529a("problems beginning transaction " + str, e);
        }
    }

    /* renamed from: c */
    public boolean m1145c() {
        return this.f23141g;
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public void mo238a(Savepoint savepoint) throws SQLException {
        try {
            this.f23140f.setTransactionSuccessful();
            this.f23140f.endTransaction();
            if (savepoint == null) {
                f23138d.m619a("{}: transaction is successfuly ended", this);
            } else {
                f23138d.m618a("{}: transaction {} is successfuly ended", this, savepoint.getSavepointName());
            }
        } catch (android.database.SQLException e) {
            throw SqlExceptionUtil.m529a("problems commiting transaction " + savepoint.getSavepointName(), e);
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: b */
    public void mo233b(Savepoint savepoint) throws SQLException {
        try {
            this.f23140f.endTransaction();
            if (savepoint == null) {
                f23138d.m619a("{}: transaction is ended, unsuccessfuly", this);
            } else {
                f23138d.m618a("{}: transaction {} is ended, unsuccessfuly", this, savepoint.getSavepointName());
            }
        } catch (android.database.SQLException e) {
            throw SqlExceptionUtil.m529a("problems rolling back transaction " + savepoint.getSavepointName(), e);
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public int mo244a(String str, int i) throws SQLException {
        return AndroidCompiledStatement.m1153a(this.f23140f, str, str, f23139e);
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public CompiledStatement mo243a(String str, StatementBuilder.EnumC5579b bVar, FieldType[] ssVarArr) {
        AndroidCompiledStatement rjVar = new AndroidCompiledStatement(str, this.f23140f, bVar);
        f23138d.m617a("{}: compiled statement got {}: {}", this, rjVar, str);
        return rjVar;
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public CompiledStatement mo242a(String str, StatementBuilder.EnumC5579b bVar, FieldType[] ssVarArr, int i) {
        return mo243a(str, bVar, ssVarArr);
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public int mo239a(String str, Object[] objArr, FieldType[] ssVarArr, GeneratedKeyHolder wpVar) throws SQLException {
        SQLiteStatement sQLiteStatement = null;
        try {
            try {
                sQLiteStatement = this.f23140f.compileStatement(str);
                m1148a(sQLiteStatement, objArr, ssVarArr);
                long executeInsert = sQLiteStatement.executeInsert();
                if (wpVar != null) {
                    wpVar.mo201a(Long.valueOf(executeInsert));
                }
                f23138d.m617a("{}: insert statement is compiled and executed, changed {}: {}", (Object) this, (Object) 1, (Object) str);
                return 1;
            } catch (android.database.SQLException e) {
                throw SqlExceptionUtil.m529a("inserting to database failed: " + str, e);
            }
        } finally {
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    public int mo241a(String str, Object[] objArr, FieldType[] ssVarArr) throws SQLException {
        return m1147a(str, objArr, ssVarArr, MSVSSConstants.TIME_UPDATED);
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: b */
    public int mo234b(String str, Object[] objArr, FieldType[] ssVarArr) throws SQLException {
        return m1147a(str, objArr, ssVarArr, "deleted");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005d  */
    @Override // p110z1.DatabaseConnection
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> java.lang.Object mo240a(java.lang.String r4, java.lang.Object[] r5, p110z1.FieldType[] r6, p110z1.GenericRowMapper<T> r7, p110z1.ObjectCache r8) throws java.sql.SQLException {
        /*
            r3 = this;
            r6 = 0
            android.database.sqlite.SQLiteDatabase r0 = r3.f23140f     // Catch: all -> 0x003d, SQLException -> 0x0040
            java.lang.String[] r5 = r3.m1146a(r5)     // Catch: all -> 0x003d, SQLException -> 0x0040
            android.database.Cursor r5 = r0.rawQuery(r4, r5)     // Catch: all -> 0x003d, SQLException -> 0x0040
            z1.rm r0 = new z1.rm     // Catch: SQLException -> 0x003b, all -> 0x005a
            r0.<init>(r5, r8)     // Catch: SQLException -> 0x003b, all -> 0x005a
            z1.ui r8 = p110z1.AndroidDatabaseConnection.f23138d     // Catch: SQLException -> 0x003b, all -> 0x005a
            java.lang.String r1 = "{}: queried for one result: {}"
            r8.m618a(r1, r3, r4)     // Catch: SQLException -> 0x003b, all -> 0x005a
            boolean r8 = r0.mo222c()     // Catch: SQLException -> 0x003b, all -> 0x005a
            if (r8 != 0) goto L_0x0023
            if (r5 == 0) goto L_0x0022
            r5.close()
        L_0x0022:
            return r6
        L_0x0023:
            java.lang.Object r6 = r7.mo322a(r0)     // Catch: SQLException -> 0x003b, all -> 0x005a
            boolean r7 = r0.mo220d()     // Catch: SQLException -> 0x003b, all -> 0x005a
            if (r7 == 0) goto L_0x0035
            java.lang.Object r4 = p110z1.AndroidDatabaseConnection.f23620a     // Catch: SQLException -> 0x003b, all -> 0x005a
            if (r5 == 0) goto L_0x0034
            r5.close()
        L_0x0034:
            return r4
        L_0x0035:
            if (r5 == 0) goto L_0x003a
            r5.close()
        L_0x003a:
            return r6
        L_0x003b:
            r6 = move-exception
            goto L_0x0044
        L_0x003d:
            r4 = move-exception
            r5 = r6
            goto L_0x005b
        L_0x0040:
            r5 = move-exception
            r2 = r6
            r6 = r5
            r5 = r2
        L_0x0044:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: all -> 0x005a
            r7.<init>()     // Catch: all -> 0x005a
            java.lang.String r8 = "queryForOne from database failed: "
            r7.append(r8)     // Catch: all -> 0x005a
            r7.append(r4)     // Catch: all -> 0x005a
            java.lang.String r4 = r7.toString()     // Catch: all -> 0x005a
            java.sql.SQLException r4 = p110z1.SqlExceptionUtil.m529a(r4, r6)     // Catch: all -> 0x005a
            throw r4     // Catch: all -> 0x005a
        L_0x005a:
            r4 = move-exception
        L_0x005b:
            if (r5 == 0) goto L_0x0060
            r5.close()
        L_0x0060:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AndroidDatabaseConnection.mo240a(java.lang.String, java.lang.Object[], z1.ss[], z1.ut, z1.sc):java.lang.Object");
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: b */
    public long mo235b(String str) throws SQLException {
        SQLiteStatement sQLiteStatement = null;
        try {
            try {
                sQLiteStatement = this.f23140f.compileStatement(str);
                long simpleQueryForLong = sQLiteStatement.simpleQueryForLong();
                f23138d.m617a("{}: query for long simple query returned {}: {}", this, Long.valueOf(simpleQueryForLong), str);
                return simpleQueryForLong;
            } catch (android.database.SQLException e) {
                throw SqlExceptionUtil.m529a("queryForLong from database failed: " + str, e);
            }
        } finally {
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    @Override // p110z1.DatabaseConnection
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long mo231c(java.lang.String r6, java.lang.Object[] r7, p110z1.FieldType[] r8) throws java.sql.SQLException {
        /*
            r5 = this;
            r8 = 0
            android.database.sqlite.SQLiteDatabase r0 = r5.f23140f     // Catch: all -> 0x0031, SQLException -> 0x0034
            java.lang.String[] r7 = r5.m1146a(r7)     // Catch: all -> 0x0031, SQLException -> 0x0034
            android.database.Cursor r7 = r0.rawQuery(r6, r7)     // Catch: all -> 0x0031, SQLException -> 0x0034
            z1.rm r0 = new z1.rm     // Catch: SQLException -> 0x002f, all -> 0x004e
            r0.<init>(r7, r8)     // Catch: SQLException -> 0x002f, all -> 0x004e
            boolean r8 = r0.mo222c()     // Catch: SQLException -> 0x002f, all -> 0x004e
            if (r8 == 0) goto L_0x001c
            r8 = 0
            long r0 = r0.mo209j(r8)     // Catch: SQLException -> 0x002f, all -> 0x004e
            goto L_0x001e
        L_0x001c:
            r0 = 0
        L_0x001e:
            z1.ui r8 = p110z1.AndroidDatabaseConnection.f23138d     // Catch: SQLException -> 0x002f, all -> 0x004e
            java.lang.String r2 = "{}: query for long raw query returned {}: {}"
            java.lang.Long r3 = java.lang.Long.valueOf(r0)     // Catch: SQLException -> 0x002f, all -> 0x004e
            r8.m617a(r2, r5, r3, r6)     // Catch: SQLException -> 0x002f, all -> 0x004e
            if (r7 == 0) goto L_0x002e
            r7.close()
        L_0x002e:
            return r0
        L_0x002f:
            r8 = move-exception
            goto L_0x0038
        L_0x0031:
            r6 = move-exception
            r7 = r8
            goto L_0x004f
        L_0x0034:
            r7 = move-exception
            r4 = r8
            r8 = r7
            r7 = r4
        L_0x0038:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: all -> 0x004e
            r0.<init>()     // Catch: all -> 0x004e
            java.lang.String r1 = "queryForLong from database failed: "
            r0.append(r1)     // Catch: all -> 0x004e
            r0.append(r6)     // Catch: all -> 0x004e
            java.lang.String r6 = r0.toString()     // Catch: all -> 0x004e
            java.sql.SQLException r6 = p110z1.SqlExceptionUtil.m529a(r6, r8)     // Catch: all -> 0x004e
            throw r6     // Catch: all -> 0x004e
        L_0x004e:
            r6 = move-exception
        L_0x004f:
            if (r7 == 0) goto L_0x0054
            r7.close()
        L_0x0054:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AndroidDatabaseConnection.mo231c(java.lang.String, java.lang.Object[], z1.ss[]):long");
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: d */
    public void mo230d() throws SQLException {
        try {
            this.f23140f.close();
            f23138d.m618a("{}: db {} closed", this, this.f23140f);
        } catch (android.database.SQLException e) {
            throw SqlExceptionUtil.m529a("problems closing the database connection", e);
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: e */
    public void mo229e() {
        try {
            mo230d();
        } catch (SQLException unused) {
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: f */
    public boolean mo228f() throws SQLException {
        try {
            boolean isOpen = this.f23140f.isOpen();
            f23138d.m617a("{}: db {} isOpen returned {}", this, this.f23140f, Boolean.valueOf(isOpen));
            return !isOpen;
        } catch (android.database.SQLException e) {
            throw SqlExceptionUtil.m529a("problems detecting if the database is closed", e);
        }
    }

    @Override // p110z1.DatabaseConnection
    /* renamed from: c */
    public boolean mo232c(String str) {
        boolean z;
        SQLiteDatabase sQLiteDatabase = this.f23140f;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" + str + "'", null);
        if (rawQuery != null) {
            try {
                if (rawQuery.getCount() > 0) {
                    z = true;
                    f23138d.m617a("{}: isTableExists '{}' returned {}", this, str, Boolean.valueOf(z));
                    rawQuery.close();
                    return z;
                }
            } catch (Throwable th) {
                rawQuery.close();
                throw th;
            }
        }
        z = false;
        f23138d.m617a("{}: isTableExists '{}' returned {}", this, str, Boolean.valueOf(z));
        rawQuery.close();
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
        if (r0 != null) goto L_0x0023;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
        if (r0 == null) goto L_0x0033;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0033, code lost:
        p110z1.AndroidDatabaseConnection.f23138d.m617a("{} statement is compiled and executed, changed {}: {}", r6, java.lang.Integer.valueOf(r4), r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003e, code lost:
        return r4;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int m1147a(java.lang.String r3, java.lang.Object[] r4, p110z1.FieldType[] r5, java.lang.String r6) throws java.sql.SQLException {
        /*
            r2 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r2.f23140f     // Catch: all -> 0x0045, SQLException -> 0x0047
            android.database.sqlite.SQLiteStatement r1 = r1.compileStatement(r3)     // Catch: all -> 0x0045, SQLException -> 0x0047
            r2.m1148a(r1, r4, r5)     // Catch: all -> 0x003f, SQLException -> 0x0042
            r1.execute()     // Catch: all -> 0x003f, SQLException -> 0x0042
            if (r1 == 0) goto L_0x0013
            r1.close()
            goto L_0x0014
        L_0x0013:
            r0 = r1
        L_0x0014:
            android.database.sqlite.SQLiteDatabase r4 = r2.f23140f     // Catch: all -> 0x0027, SQLException -> 0x002e
            java.lang.String r5 = "SELECT CHANGES()"
            android.database.sqlite.SQLiteStatement r0 = r4.compileStatement(r5)     // Catch: all -> 0x0027, SQLException -> 0x002e
            long r4 = r0.simpleQueryForLong()     // Catch: all -> 0x0027, SQLException -> 0x002e
            int r4 = (int) r4
            if (r0 == 0) goto L_0x0033
        L_0x0023:
            r0.close()
            goto L_0x0033
        L_0x0027:
            r3 = move-exception
            if (r0 == 0) goto L_0x002d
            r0.close()
        L_0x002d:
            throw r3
        L_0x002e:
            r4 = 1
            if (r0 == 0) goto L_0x0033
            goto L_0x0023
        L_0x0033:
            z1.ui r5 = p110z1.AndroidDatabaseConnection.f23138d
            java.lang.String r0 = "{} statement is compiled and executed, changed {}: {}"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            r5.m617a(r0, r6, r1, r3)
            return r4
        L_0x003f:
            r3 = move-exception
            r0 = r1
            goto L_0x005e
        L_0x0042:
            r4 = move-exception
            r0 = r1
            goto L_0x0048
        L_0x0045:
            r3 = move-exception
            goto L_0x005e
        L_0x0047:
            r4 = move-exception
        L_0x0048:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0045
            r5.<init>()     // Catch: all -> 0x0045
            java.lang.String r6 = "updating database failed: "
            r5.append(r6)     // Catch: all -> 0x0045
            r5.append(r3)     // Catch: all -> 0x0045
            java.lang.String r3 = r5.toString()     // Catch: all -> 0x0045
            java.sql.SQLException r3 = p110z1.SqlExceptionUtil.m529a(r3, r4)     // Catch: all -> 0x0045
            throw r3     // Catch: all -> 0x0045
        L_0x005e:
            if (r0 == 0) goto L_0x0063
            r0.close()
        L_0x0063:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.AndroidDatabaseConnection.m1147a(java.lang.String, java.lang.Object[], z1.ss[], java.lang.String):int");
    }

    /* renamed from: a */
    private void m1148a(SQLiteStatement sQLiteStatement, Object[] objArr, FieldType[] ssVarArr) throws SQLException {
        if (objArr != null) {
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                if (obj == null) {
                    sQLiteStatement.bindNull(i + 1);
                } else {
                    SqlType h = ssVarArr[i].m709h();
                    switch (h) {
                        case STRING:
                        case LONG_STRING:
                        case CHAR:
                            sQLiteStatement.bindString(i + 1, obj.toString());
                            continue;
                        case BOOLEAN:
                        case BYTE:
                        case SHORT:
                        case INTEGER:
                        case LONG:
                            sQLiteStatement.bindLong(i + 1, ((Number) obj).longValue());
                            continue;
                        case FLOAT:
                        case DOUBLE:
                            sQLiteStatement.bindDouble(i + 1, ((Number) obj).doubleValue());
                            continue;
                        case BYTE_ARRAY:
                        case SERIALIZABLE:
                            sQLiteStatement.bindBlob(i + 1, (byte[]) obj);
                            continue;
                        case DATE:
                        case BLOB:
                        case BIG_DECIMAL:
                            throw new SQLException("Invalid Android type: " + h);
                        default:
                            throw new SQLException("Unknown sql argument type: " + h);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private String[] m1146a(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        String[] strArr = new String[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null) {
                strArr[i] = null;
            } else {
                strArr[i] = obj.toString();
            }
        }
        return strArr;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }

    /* compiled from: AndroidDatabaseConnection.java */
    /* renamed from: z1.rl$a */
    /* loaded from: classes3.dex */
    private static class C5542a implements Savepoint {

        /* renamed from: a */
        private String f23143a;

        @Override // java.sql.Savepoint
        public int getSavepointId() {
            return 0;
        }

        public C5542a(String str) {
            this.f23143a = str;
        }

        @Override // java.sql.Savepoint
        public String getSavepointName() {
            return this.f23143a;
        }
    }
}
