package p110z1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import p110z1.StatementBuilder;

/* renamed from: z1.wv */
/* loaded from: classes3.dex */
public class TableUtils {

    /* renamed from: a */
    private static C5570ui f23643a = LoggerFactory.m545a(TableUtils.class);

    /* renamed from: b */
    private static final FieldType[] f23644b = new FieldType[0];

    private TableUtils() {
    }

    /* renamed from: a */
    public static <T> int m158a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        return m148b(wmVar, (Class) cls, false);
    }

    /* renamed from: b */
    public static <T> int m149b(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        return m148b(wmVar, (Class) cls, true);
    }

    /* renamed from: a */
    public static <T> int m155a(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        return m146b(wmVar, (DatabaseTableConfig) wrVar, false);
    }

    /* renamed from: b */
    public static <T> int m147b(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        return m146b(wmVar, (DatabaseTableConfig) wrVar, true);
    }

    /* renamed from: c */
    public static <T, ID> List<String> m144c(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        Dao a = DaoManager.m1027a(wmVar, cls);
        if (a instanceof BaseDaoImpl) {
            return m145b(wmVar, (TableInfo) ((BaseDaoImpl) a).m1114v(), false);
        }
        return m145b(wmVar, new TableInfo(wmVar, (BaseDaoImpl) null, cls), false);
    }

    /* renamed from: c */
    public static <T, ID> List<String> m143c(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        Dao a = DaoManager.m1025a(wmVar, wrVar);
        if (a instanceof BaseDaoImpl) {
            return m145b(wmVar, (TableInfo) ((BaseDaoImpl) a).m1114v(), false);
        }
        wrVar.m192a(wmVar);
        return m145b(wmVar, new TableInfo(wmVar.mo249e(), (BaseDaoImpl) null, wrVar), false);
    }

    /* renamed from: a */
    public static <T, ID> int m157a(ConnectionSource wmVar, Class<T> cls, boolean z) throws SQLException {
        DatabaseType e = wmVar.mo249e();
        Dao a = DaoManager.m1027a(wmVar, cls);
        if (a instanceof BaseDaoImpl) {
            return m162a(e, wmVar, ((BaseDaoImpl) a).m1114v(), z);
        }
        return m162a(e, wmVar, new TableInfo(wmVar, (BaseDaoImpl) null, cls), z);
    }

    /* renamed from: a */
    public static <T, ID> int m154a(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar, boolean z) throws SQLException {
        DatabaseType e = wmVar.mo249e();
        Dao a = DaoManager.m1025a(wmVar, wrVar);
        if (a instanceof BaseDaoImpl) {
            return m162a(e, wmVar, ((BaseDaoImpl) a).m1114v(), z);
        }
        wrVar.m192a(wmVar);
        return m162a(e, wmVar, new TableInfo(e, (BaseDaoImpl) null, wrVar), z);
    }

    /* renamed from: d */
    public static <T> int m142d(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        String b = DatabaseTableConfig.m187b(cls);
        if (wmVar.mo249e().mo887m()) {
            b = b.toUpperCase();
        }
        return m156a(wmVar, b);
    }

    /* renamed from: d */
    public static <T> int m141d(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        return m156a(wmVar, wrVar.m186c());
    }

    /* renamed from: b */
    private static <T, ID> int m148b(ConnectionSource wmVar, Class<T> cls, boolean z) throws SQLException {
        Dao a = DaoManager.m1027a(wmVar, cls);
        if (a instanceof BaseDaoImpl) {
            return m153a(wmVar, ((BaseDaoImpl) a).m1114v(), z);
        }
        return m153a(wmVar, new TableInfo(wmVar, (BaseDaoImpl) null, cls), z);
    }

    /* renamed from: b */
    private static <T, ID> int m146b(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar, boolean z) throws SQLException {
        Dao a = DaoManager.m1025a(wmVar, wrVar);
        if (a instanceof BaseDaoImpl) {
            return m153a(wmVar, ((BaseDaoImpl) a).m1114v(), z);
        }
        wrVar.m192a(wmVar);
        return m153a(wmVar, new TableInfo(wmVar.mo249e(), (BaseDaoImpl) null, wrVar), z);
    }

    /* renamed from: a */
    private static <T> int m156a(ConnectionSource wmVar, String str) throws SQLException {
        DatabaseType e = wmVar.mo249e();
        StringBuilder sb = new StringBuilder(48);
        if (e.mo885q()) {
            sb.append("TRUNCATE TABLE ");
        } else {
            sb.append("DELETE FROM ");
        }
        e.mo899b(sb, str);
        String sb2 = sb.toString();
        f23643a.m584c("clearing table '{}' with '{}", str, sb2);
        CompiledStatement wlVar = null;
        DatabaseConnection b = wmVar.mo254b();
        try {
            wlVar = b.mo243a(sb2, StatementBuilder.EnumC5579b.EXECUTE, f23644b);
            return wlVar.mo259c();
        } finally {
            if (wlVar != null) {
                wlVar.mo258d();
            }
            wmVar.mo255a(b);
        }
    }

    /* renamed from: a */
    private static <T, ID> int m162a(DatabaseType siVar, ConnectionSource wmVar, TableInfo<T, ID> wuVar, boolean z) throws SQLException {
        f23643a.m585c("dropping table '{}'", wuVar.m171b());
        ArrayList arrayList = new ArrayList();
        m161a(siVar, wuVar, arrayList);
        m150b(siVar, wuVar, arrayList);
        DatabaseConnection b = wmVar.mo254b();
        try {
            return m152a(b, "drop", arrayList, z, siVar.mo888l(), false);
        } finally {
            wmVar.mo255a(b);
        }
    }

    /* renamed from: a */
    private static <T, ID> void m161a(DatabaseType siVar, TableInfo<T, ID> wuVar, List<String> list) {
        FieldType[] c;
        HashSet<String> hashSet = new HashSet();
        for (FieldType ssVar : wuVar.m169c()) {
            String w = ssVar.m692w();
            if (w != null) {
                hashSet.add(w);
            }
            String x = ssVar.m691x();
            if (x != null) {
                hashSet.add(x);
            }
        }
        StringBuilder sb = new StringBuilder(48);
        for (String str : hashSet) {
            f23643a.m584c("dropping index '{}' for table '{}", str, wuVar.m171b());
            sb.append("DROP INDEX ");
            siVar.mo899b(sb, str);
            list.add(sb.toString());
            sb.setLength(0);
        }
    }

    /* renamed from: a */
    private static <T, ID> void m160a(DatabaseType siVar, TableInfo<T, ID> wuVar, List<String> list, List<String> list2, boolean z) throws SQLException {
        int i;
        StringBuilder sb = new StringBuilder(256);
        sb.append("CREATE TABLE ");
        if (z && siVar.mo884r()) {
            sb.append("IF NOT EXISTS ");
        }
        siVar.mo899b(sb, wuVar.m171b());
        sb.append(" (");
        ArrayList<String> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        FieldType[] c = wuVar.m169c();
        int length = c.length;
        boolean z2 = true;
        for (int i2 = 0; i2 < length; i2 = i + 1) {
            FieldType ssVar = c[i2];
            if (ssVar.m741B()) {
                i = i2;
                length = length;
                c = c;
            } else {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(", ");
                    z2 = z2;
                }
                String E = ssVar.m738E();
                if (E == null) {
                    i = i2;
                    length = length;
                    c = c;
                    siVar.mo908a(wuVar.m171b(), sb, ssVar, arrayList, arrayList2, arrayList3, list2);
                } else {
                    i = i2;
                    length = length;
                    c = c;
                    siVar.mo899b(sb, ssVar.m715e());
                    sb.append(' ');
                    sb.append(E);
                    sb.append(' ');
                }
            }
        }
        siVar.mo900a(wuVar.m169c(), arrayList, arrayList2, arrayList3, list2);
        siVar.mo898b(wuVar.m169c(), arrayList, arrayList2, arrayList3, list2);
        for (String str : arrayList) {
            sb.append(", ");
            sb.append(str);
        }
        sb.append(") ");
        siVar.mo906a(sb);
        list.addAll(arrayList2);
        list.add(sb.toString());
        list.addAll(arrayList3);
        m159a(siVar, (TableInfo) wuVar, list, z, false);
        m159a(siVar, (TableInfo) wuVar, list, z, true);
    }

    /* renamed from: a */
    private static <T, ID> void m159a(DatabaseType siVar, TableInfo<T, ID> wuVar, List<String> list, boolean z, boolean z2) {
        FieldType[] c;
        String str;
        HashMap hashMap = new HashMap();
        for (FieldType ssVar : wuVar.m169c()) {
            if (z2) {
                str = ssVar.m691x();
            } else {
                str = ssVar.m692w();
            }
            if (str != null) {
                List list2 = (List) hashMap.get(str);
                if (list2 == null) {
                    list2 = new ArrayList();
                    hashMap.put(str, list2);
                }
                list2.add(ssVar.m715e());
            }
        }
        StringBuilder sb = new StringBuilder(128);
        for (Map.Entry entry : hashMap.entrySet()) {
            f23643a.m584c("creating index '{}' for table '{}", entry.getKey(), wuVar.m171b());
            sb.append("CREATE ");
            if (z2) {
                sb.append("UNIQUE ");
            }
            sb.append("INDEX ");
            if (z && siVar.mo883s()) {
                sb.append("IF NOT EXISTS ");
            }
            siVar.mo899b(sb, (String) entry.getKey());
            sb.append(" ON ");
            siVar.mo899b(sb, wuVar.m171b());
            sb.append(" ( ");
            boolean z3 = true;
            for (String str2 : (List) entry.getValue()) {
                if (z3) {
                    z3 = false;
                } else {
                    sb.append(", ");
                }
                siVar.mo899b(sb, str2);
            }
            sb.append(" )");
            list.add(sb.toString());
            sb.setLength(0);
        }
    }

    /* renamed from: b */
    private static <T, ID> void m150b(DatabaseType siVar, TableInfo<T, ID> wuVar, List<String> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (FieldType ssVar : wuVar.m169c()) {
            siVar.mo901a(ssVar, arrayList, arrayList2);
        }
        StringBuilder sb = new StringBuilder(64);
        sb.append("DROP TABLE ");
        siVar.mo899b(sb, wuVar.m171b());
        sb.append(' ');
        list.addAll(arrayList);
        list.add(sb.toString());
        list.addAll(arrayList2);
    }

    /* renamed from: a */
    private static <T, ID> int m153a(ConnectionSource wmVar, TableInfo<T, ID> wuVar, boolean z) throws SQLException {
        DatabaseType e = wmVar.mo249e();
        f23643a.m585c("creating table '{}'", wuVar.m171b());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        m160a(e, wuVar, arrayList, arrayList2, z);
        DatabaseConnection b = wmVar.mo254b();
        try {
            return m152a(b, "create", arrayList, false, e.mo888l(), e.mo889k()) + m151a(b, e, arrayList2);
        } finally {
            wmVar.mo255a(b);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r3 != null) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        if (r4 >= 0) goto L_0x006c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        if (r12 == false) goto L_0x0048;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
        throw new java.sql.SQLException("SQL statement " + r2 + " updated " + r4 + " rows, we were expecting >= 0");
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:
        if (r4 <= 0) goto L_0x0090;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        if (r13 != false) goto L_0x0071;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008f, code lost:
        throw new java.sql.SQLException("SQL statement updated " + r4 + " rows, we were expecting == 0: " + r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0090, code lost:
        continue;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int m152a(p110z1.DatabaseConnection r8, java.lang.String r9, java.util.Collection<java.lang.String> r10, boolean r11, boolean r12, boolean r13) throws java.sql.SQLException {
        /*
            java.util.Iterator r10 = r10.iterator()
            r0 = 0
            r1 = 0
        L_0x0006:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x00b0
            java.lang.Object r2 = r10.next()
            java.lang.String r2 = (java.lang.String) r2
            r3 = 0
            z1.ve$b r4 = p110z1.StatementBuilder.EnumC5579b.EXECUTE     // Catch: all -> 0x0032, SQLException -> 0x0035
            z1.ss[] r5 = p110z1.TableUtils.f23644b     // Catch: all -> 0x0032, SQLException -> 0x0035
            z1.wl r3 = r8.mo243a(r2, r4, r5)     // Catch: all -> 0x0032, SQLException -> 0x0035
            int r4 = r3.mo259c()     // Catch: all -> 0x0032, SQLException -> 0x0035
            z1.ui r5 = p110z1.TableUtils.f23643a     // Catch: SQLException -> 0x0030, all -> 0x0032
            java.lang.String r6 = "executed {} table statement changed {} rows: {}"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch: SQLException -> 0x0030, all -> 0x0032
            r5.m583c(r6, r9, r7, r2)     // Catch: SQLException -> 0x0030, all -> 0x0032
            if (r3 == 0) goto L_0x0043
        L_0x002c:
            r3.mo258d()
            goto L_0x0043
        L_0x0030:
            r5 = move-exception
            goto L_0x0037
        L_0x0032:
            r8 = move-exception
            goto L_0x00aa
        L_0x0035:
            r5 = move-exception
            r4 = 0
        L_0x0037:
            if (r11 == 0) goto L_0x0094
            z1.ui r6 = p110z1.TableUtils.f23643a     // Catch: all -> 0x0032
            java.lang.String r7 = "ignoring {} error '{}' for statement: {}"
            r6.m583c(r7, r9, r5, r2)     // Catch: all -> 0x0032
            if (r3 == 0) goto L_0x0043
            goto L_0x002c
        L_0x0043:
            if (r4 >= 0) goto L_0x006c
            if (r12 == 0) goto L_0x0048
            goto L_0x0090
        L_0x0048:
            java.sql.SQLException r8 = new java.sql.SQLException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "SQL statement "
            r9.append(r10)
            r9.append(r2)
            java.lang.String r10 = " updated "
            r9.append(r10)
            r9.append(r4)
            java.lang.String r10 = " rows, we were expecting >= 0"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x006c:
            if (r4 <= 0) goto L_0x0090
            if (r13 != 0) goto L_0x0071
            goto L_0x0090
        L_0x0071:
            java.sql.SQLException r8 = new java.sql.SQLException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "SQL statement updated "
            r9.append(r10)
            r9.append(r4)
            java.lang.String r10 = " rows, we were expecting == 0: "
            r9.append(r10)
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0090:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x0094:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: all -> 0x0032
            r8.<init>()     // Catch: all -> 0x0032
            java.lang.String r9 = "SQL statement failed: "
            r8.append(r9)     // Catch: all -> 0x0032
            r8.append(r2)     // Catch: all -> 0x0032
            java.lang.String r8 = r8.toString()     // Catch: all -> 0x0032
            java.sql.SQLException r8 = p110z1.SqlExceptionUtil.m529a(r8, r5)     // Catch: all -> 0x0032
            throw r8     // Catch: all -> 0x0032
        L_0x00aa:
            if (r3 == 0) goto L_0x00af
            r3.mo258d()
        L_0x00af:
            throw r8
        L_0x00b0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.TableUtils.m152a(z1.wn, java.lang.String, java.util.Collection, boolean, boolean, boolean):int");
    }

    /* renamed from: a */
    private static int m151a(DatabaseConnection wnVar, DatabaseType siVar, List<String> list) throws SQLException {
        Throwable th;
        SQLException e;
        CompiledStatement a;
        int i = 0;
        for (String str : list) {
            CompiledStatement wlVar = null;
            try {
                try {
                    a = wnVar.mo243a(str, StatementBuilder.EnumC5579b.SELECT, f23644b);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (SQLException e2) {
                e = e2;
            }
            try {
                DatabaseResults a2 = a.mo262a((ObjectCache) null);
                int i2 = 0;
                for (boolean c = a2.mo222c(); c; c = a2.mo220d()) {
                    i2++;
                }
                f23643a.m584c("executing create table after-query got {} results: {}", Integer.valueOf(i2), str);
                if (a != null) {
                    a.mo258d();
                }
                i++;
            } catch (SQLException e3) {
                e = e3;
                wlVar = a;
                throw SqlExceptionUtil.m529a("executing create table after-query failed: " + str, e);
            } catch (Throwable th3) {
                th = th3;
                wlVar = a;
                if (wlVar != null) {
                    wlVar.mo258d();
                }
                throw th;
            }
        }
        return i;
    }

    /* renamed from: b */
    private static <T, ID> List<String> m145b(ConnectionSource wmVar, TableInfo<T, ID> wuVar, boolean z) throws SQLException {
        ArrayList arrayList = new ArrayList();
        m160a(wmVar.mo249e(), wuVar, arrayList, new ArrayList(), z);
        return arrayList;
    }
}
