package p110z1;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.un */
/* loaded from: classes3.dex */
public class TransactionManager {

    /* renamed from: b */
    private static final String f23464b = "ORMLITE";

    /* renamed from: c */
    private ConnectionSource f23466c;

    /* renamed from: a */
    private static final C5570ui f23463a = LoggerFactory.m545a(TransactionManager.class);

    /* renamed from: d */
    private static AtomicInteger f23465d = new AtomicInteger();

    public TransactionManager() {
    }

    public TransactionManager(ConnectionSource wmVar) {
        this.f23466c = wmVar;
        m528a();
    }

    /* renamed from: a */
    public void m528a() {
        if (this.f23466c == null) {
            throw new IllegalStateException("dataSource was not set on " + getClass().getSimpleName());
        }
    }

    /* renamed from: a */
    public <T> T m527a(Callable<T> callable) throws SQLException {
        return (T) m525a(this.f23466c, callable);
    }

    /* renamed from: a */
    public static <T> T m525a(ConnectionSource wmVar, Callable<T> callable) throws SQLException {
        DatabaseConnection b = wmVar.mo254b();
        try {
            return (T) m522a(b, wmVar.mo253b(b), wmVar.mo249e(), callable);
        } finally {
            wmVar.mo251c(b);
            wmVar.mo255a(b);
        }
    }

    /* renamed from: a */
    public static <T> T m523a(DatabaseConnection wnVar, DatabaseType siVar, Callable<T> callable) throws SQLException {
        return (T) m522a(wnVar, false, siVar, callable);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005d A[Catch: all -> 0x006d, Exception -> 0x0071, SQLException -> 0x0086, TRY_LEAVE, TryCatch #6 {SQLException -> 0x0086, Exception -> 0x0071, blocks: (B:19:0x0057, B:21:0x005d), top: B:48:0x0057, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0098  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> T m522a(p110z1.DatabaseConnection r4, boolean r5, p110z1.DatabaseType r6, java.util.concurrent.Callable<T> r7) throws java.sql.SQLException {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            if (r5 != 0) goto L_0x000e
            boolean r5 = r6.mo873n()     // Catch: all -> 0x0095
            if (r5 == 0) goto L_0x000c
            goto L_0x000e
        L_0x000c:
            r5 = 0
            goto L_0x0057
        L_0x000e:
            boolean r5 = r4.mo246a()     // Catch: all -> 0x0095
            if (r5 == 0) goto L_0x0025
            boolean r5 = r4.mo236b()     // Catch: all -> 0x0095
            if (r5 == 0) goto L_0x0024
            r4.mo237a(r2)     // Catch: all -> 0x006d
            z1.ui r6 = p110z1.TransactionManager.f23463a     // Catch: all -> 0x006d
            java.lang.String r0 = "had to set auto-commit to false"
            r6.m596b(r0)     // Catch: all -> 0x006d
        L_0x0024:
            r2 = r5
        L_0x0025:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0095
            r5.<init>()     // Catch: all -> 0x0095
            java.lang.String r6 = "ORMLITE"
            r5.append(r6)     // Catch: all -> 0x0095
            java.util.concurrent.atomic.AtomicInteger r6 = p110z1.TransactionManager.f23465d     // Catch: all -> 0x0095
            int r6 = r6.incrementAndGet()     // Catch: all -> 0x0095
            r5.append(r6)     // Catch: all -> 0x0095
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x0095
            java.sql.Savepoint r0 = r4.mo245a(r5)     // Catch: all -> 0x0095
            if (r0 != 0) goto L_0x004a
            z1.ui r5 = p110z1.TransactionManager.f23463a     // Catch: all -> 0x0095
            java.lang.String r6 = "started savePoint transaction"
            r5.m596b(r6)     // Catch: all -> 0x0095
            goto L_0x0055
        L_0x004a:
            z1.ui r5 = p110z1.TransactionManager.f23463a     // Catch: all -> 0x0095
            java.lang.String r6 = "started savePoint transaction {}"
            java.lang.String r3 = r0.getSavepointName()     // Catch: all -> 0x0095
            r5.m595b(r6, r3)     // Catch: all -> 0x0095
        L_0x0055:
            r5 = r2
            r2 = 1
        L_0x0057:
            java.lang.Object r6 = r7.call()     // Catch: all -> 0x006d, Exception -> 0x0071, SQLException -> 0x0086
            if (r2 == 0) goto L_0x0060
            m524a(r4, r0)     // Catch: all -> 0x006d, Exception -> 0x0071, SQLException -> 0x0086
        L_0x0060:
            if (r5 == 0) goto L_0x006c
            r4.mo237a(r1)
            z1.ui r4 = p110z1.TransactionManager.f23463a
            java.lang.String r5 = "restored auto-commit to true"
            r4.m596b(r5)
        L_0x006c:
            return r6
        L_0x006d:
            r6 = move-exception
            r2 = r5
            r5 = r6
            goto L_0x0096
        L_0x0071:
            r6 = move-exception
            if (r2 == 0) goto L_0x007f
            m521b(r4, r0)     // Catch: all -> 0x006d, SQLException -> 0x0078
            goto L_0x007f
        L_0x0078:
            z1.ui r7 = p110z1.TransactionManager.f23463a     // Catch: all -> 0x006d
            java.lang.String r0 = "after commit exception, rolling back to save-point also threw exception"
            r7.m561e(r6, r0)     // Catch: all -> 0x006d
        L_0x007f:
            java.lang.String r7 = "Transaction callable threw non-SQL exception"
            java.sql.SQLException r6 = p110z1.SqlExceptionUtil.m529a(r7, r6)     // Catch: all -> 0x006d
            throw r6     // Catch: all -> 0x006d
        L_0x0086:
            r6 = move-exception
            if (r2 == 0) goto L_0x0094
            m521b(r4, r0)     // Catch: all -> 0x006d, SQLException -> 0x008d
            goto L_0x0094
        L_0x008d:
            z1.ui r7 = p110z1.TransactionManager.f23463a     // Catch: all -> 0x006d
            java.lang.String r0 = "after commit exception, rolling back to save-point also threw exception"
            r7.m561e(r6, r0)     // Catch: all -> 0x006d
        L_0x0094:
            throw r6     // Catch: all -> 0x006d
        L_0x0095:
            r5 = move-exception
        L_0x0096:
            if (r2 == 0) goto L_0x00a2
            r4.mo237a(r1)
            z1.ui r4 = p110z1.TransactionManager.f23463a
            java.lang.String r6 = "restored auto-commit to true"
            r4.m596b(r6)
        L_0x00a2:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.TransactionManager.m522a(z1.wn, boolean, z1.si, java.util.concurrent.Callable):java.lang.Object");
    }

    /* renamed from: a */
    public void m526a(ConnectionSource wmVar) {
        this.f23466c = wmVar;
    }

    /* renamed from: a */
    private static void m524a(DatabaseConnection wnVar, Savepoint savepoint) throws SQLException {
        String savepointName = savepoint == null ? null : savepoint.getSavepointName();
        wnVar.mo238a(savepoint);
        if (savepointName == null) {
            f23463a.m596b("committed savePoint transaction");
        } else {
            f23463a.m595b("committed savePoint transaction {}", savepointName);
        }
    }

    /* renamed from: b */
    private static void m521b(DatabaseConnection wnVar, Savepoint savepoint) throws SQLException {
        String savepointName = savepoint == null ? null : savepoint.getSavepointName();
        wnVar.mo233b(savepoint);
        if (savepointName == null) {
            f23463a.m596b("rolled back savePoint transaction");
        } else {
            f23463a.m595b("rolled back savePoint transaction {}", savepointName);
        }
    }
}
