package p110z1;

import java.sql.SQLException;

/* renamed from: z1.um */
/* loaded from: classes3.dex */
public class SqlExceptionUtil {
    private SqlExceptionUtil() {
    }

    /* renamed from: a */
    public static SQLException m529a(String str, Throwable th) {
        SQLException sQLException = new SQLException(str);
        sQLException.initCause(th);
        return sQLException;
    }
}
