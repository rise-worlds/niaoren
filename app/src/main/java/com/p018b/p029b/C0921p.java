package com.p018b.p029b;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Okio.java */
/* renamed from: com.b.b.p */
/* loaded from: classes.dex */
public final class C0921p implements Source {

    /* renamed from: a */
    final /* synthetic */ Timeout f6442a;

    /* renamed from: b */
    final /* synthetic */ InputStream f6443b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0921p(Timeout yVar, InputStream inputStream) {
        this.f6442a = yVar;
        this.f6443b = inputStream;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (i == 0) {
            return 0L;
        } else {
            try {
                this.f6442a.mo24241f_();
                Segment e = fVar.m24324e(1);
                int read = this.f6443b.read(e.f6451a, e.f6453c, (int) Math.min(j, 8192 - e.f6453c));
                if (read == -1) {
                    return -1L;
                }
                e.f6453c += read;
                long j2 = read;
                fVar.f6422b += j2;
                return j2;
            } catch (AssertionError e2) {
                if (Okio.m24304a(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6443b.close();
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f6442a;
    }

    public final String toString() {
        return "source(" + this.f6443b + ")";
    }
}
