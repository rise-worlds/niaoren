package p110z1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.va */
/* loaded from: classes3.dex */
public class RawResultsImpl<T> implements GenericRawResults<T> {

    /* renamed from: a */
    private SelectIterator<T, Void> f23503a;

    /* renamed from: b */
    private final String[] f23504b;

    public RawResultsImpl(ConnectionSource wmVar, DatabaseConnection wnVar, String str, Class<?> cls, CompiledStatement wlVar, GenericRowMapper<T> utVar, ObjectCache scVar) throws SQLException {
        this.f23503a = new SelectIterator<>(cls, null, utVar, wmVar, wnVar, wlVar, str, scVar);
        this.f23504b = this.f23503a.mo444c().mo224b();
    }

    @Override // p110z1.GenericRawResults
    /* renamed from: a */
    public int mo453a() {
        return this.f23504b.length;
    }

    @Override // p110z1.GenericRawResults
    /* renamed from: b */
    public String[] mo452b() {
        return this.f23504b;
    }

    @Override // p110z1.GenericRawResults
    /* renamed from: c */
    public List<T> mo451c() throws SQLException {
        ArrayList arrayList = new ArrayList();
        while (this.f23503a.hasNext()) {
            try {
                arrayList.add(this.f23503a.next());
            } finally {
                this.f23503a.mo447a();
            }
        }
        return arrayList;
    }

    @Override // p110z1.GenericRawResults
    /* renamed from: d */
    public T mo450d() throws SQLException {
        try {
            if (this.f23503a.m438i()) {
                return this.f23503a.mo439h();
            }
            return null;
        } finally {
            close();
        }
    }

    /* renamed from: e */
    public CloseableIterator<T> iterator() {
        return this.f23503a;
    }

    @Override // p110z1.CloseableIterable
    public CloseableIterator<T> closeableIterator() {
        return this.f23503a;
    }

    @Override // p110z1.GenericRawResults, p110z1.CloseableWrappedIterable
    public void close() throws SQLException {
        SelectIterator<T, Void> vdVar = this.f23503a;
        if (vdVar != null) {
            vdVar.mo447a();
            this.f23503a = null;
        }
    }
}
