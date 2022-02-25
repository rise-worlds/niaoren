package p110z1;

import java.sql.SQLException;

/* renamed from: z1.rt */
/* loaded from: classes3.dex */
public interface CloseableWrappedIterable<T> extends CloseableIterable<T> {
    void close() throws SQLException;
}
