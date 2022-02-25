package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.rz */
/* loaded from: classes3.dex */
public interface GenericRawResults<T> extends CloseableWrappedIterable<T> {
    /* renamed from: a */
    int mo453a();

    /* renamed from: b */
    String[] mo452b();

    /* renamed from: c */
    List<T> mo451c() throws SQLException;

    @Override // p110z1.CloseableWrappedIterable
    void close() throws SQLException;

    /* renamed from: d */
    T mo450d() throws SQLException;
}
