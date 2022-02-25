package p110z1;

import java.sql.SQLException;

/* renamed from: z1.ru */
/* loaded from: classes3.dex */
public class CloseableWrappedIterableImpl<T> implements CloseableWrappedIterable<T> {

    /* renamed from: a */
    private final CloseableIterable<T> f23211a;

    /* renamed from: b */
    private CloseableIterator<T> f23212b;

    public CloseableWrappedIterableImpl(CloseableIterable<T> rrVar) {
        this.f23211a = rrVar;
    }

    /* renamed from: a */
    public CloseableIterator<T> iterator() {
        return closeableIterator();
    }

    @Override // p110z1.CloseableIterable
    public CloseableIterator<T> closeableIterator() {
        try {
            close();
        } catch (SQLException unused) {
        }
        this.f23212b = this.f23211a.closeableIterator();
        return this.f23212b;
    }

    @Override // p110z1.CloseableWrappedIterable
    public void close() throws SQLException {
        CloseableIterator<T> rsVar = this.f23212b;
        if (rsVar != null) {
            rsVar.mo447a();
            this.f23212b = null;
        }
    }
}
