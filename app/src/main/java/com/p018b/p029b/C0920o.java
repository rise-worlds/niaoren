package com.p018b.p029b;

import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Okio.java */
/* renamed from: com.b.b.o */
/* loaded from: classes.dex */
public final class C0920o implements Sink {

    /* renamed from: a */
    final /* synthetic */ Timeout f6440a;

    /* renamed from: b */
    final /* synthetic */ OutputStream f6441b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0920o(Timeout yVar, OutputStream outputStream) {
        this.f6440a = yVar;
        this.f6441b = outputStream;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a_ */
    public final void mo24251a_(Buffer fVar, long j) {
        C0916aa.m24347a(fVar.f6422b, 0L, j);
        while (j > 0) {
            this.f6440a.mo24241f_();
            Segment tVar = fVar.f6421a;
            int min = (int) Math.min(j, tVar.f6453c - tVar.f6452b);
            this.f6441b.write(tVar.f6451a, tVar.f6452b, min);
            tVar.f6452b += min;
            long j2 = min;
            j -= j2;
            fVar.f6422b -= j2;
            if (tVar.f6452b == tVar.f6453c) {
                fVar.f6421a = tVar.m24272a();
                SegmentPool.m24268a(tVar);
            }
        }
    }

    @Override // com.p018b.p029b.Sink, java.io.Flushable
    public final void flush() {
        this.f6441b.flush();
    }

    @Override // com.p018b.p029b.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6441b.close();
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a */
    public final Timeout mo24252a() {
        return this.f6440a;
    }

    public final String toString() {
        return "sink(" + this.f6441b + ")";
    }
}
