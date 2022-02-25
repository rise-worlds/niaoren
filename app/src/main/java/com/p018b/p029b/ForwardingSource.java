package com.p018b.p029b;

/* renamed from: com.b.b.j */
/* loaded from: classes.dex */
public abstract class ForwardingSource implements Source {

    /* renamed from: a */
    private final Source f6428a;

    public ForwardingSource(Source xVar) {
        if (xVar != null) {
            this.f6428a = xVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        return this.f6428a.mo24249a(fVar, j);
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f6428a.mo24250a();
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f6428a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f6428a.toString() + ")";
    }
}
