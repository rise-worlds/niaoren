package com.p018b.p019a.p020a.p024d;

import com.p018b.p029b.Buffer;

/* compiled from: Http1Codec.java */
/* renamed from: com.b.a.a.d.g */
/* loaded from: classes.dex */
final class C0865g extends AbstractC0860b {

    /* renamed from: d */
    final /* synthetic */ Http1Codec f5839d;

    /* renamed from: e */
    private boolean f5840e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0865g(Http1Codec aVar) {
        super(aVar, (byte) 0);
        this.f5839d = aVar;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f5824b) {
            throw new IllegalStateException("closed");
        } else if (this.f5840e) {
            return -1L;
        } else {
            long a = this.f5839d.f5820c.mo24249a(fVar, j);
            if (a != -1) {
                return a;
            }
            this.f5840e = true;
            m24719a(true);
            return -1L;
        }
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f5824b) {
            if (!this.f5840e) {
                m24719a(false);
            }
            this.f5824b = true;
        }
    }
}
