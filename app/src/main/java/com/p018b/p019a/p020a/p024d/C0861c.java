package com.p018b.p019a.p020a.p024d;

import com.p018b.p029b.Buffer;
import com.p018b.p029b.ForwardingTimeout;
import com.p018b.p029b.Sink;
import com.p018b.p029b.Timeout;

/* compiled from: Http1Codec.java */
/* renamed from: com.b.a.a.d.c */
/* loaded from: classes.dex */
final class C0861c implements Sink {

    /* renamed from: a */
    final /* synthetic */ Http1Codec f5826a;

    /* renamed from: b */
    private final ForwardingTimeout f5827b;

    /* renamed from: c */
    private boolean f5828c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0861c(Http1Codec aVar) {
        this.f5826a = aVar;
        this.f5827b = new ForwardingTimeout(this.f5826a.f5821d.mo24252a());
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a */
    public final Timeout mo24252a() {
        return this.f5827b;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a_ */
    public final void mo24251a_(Buffer fVar, long j) {
        if (this.f5828c) {
            throw new IllegalStateException("closed");
        } else if (j != 0) {
            this.f5826a.f5821d.mo24292i(j);
            this.f5826a.f5821d.mo24298b("\r\n");
            this.f5826a.f5821d.mo24251a_(fVar, j);
            this.f5826a.f5821d.mo24298b("\r\n");
        }
    }

    @Override // com.p018b.p029b.Sink, java.io.Flushable
    public final synchronized void flush() {
        if (!this.f5828c) {
            this.f5826a.f5821d.flush();
        }
    }

    @Override // com.p018b.p029b.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (!this.f5828c) {
            this.f5828c = true;
            this.f5826a.f5821d.mo24298b("0\r\n\r\n");
            Http1Codec.m24721a(this.f5827b);
            this.f5826a.f5822e = 3;
        }
    }
}
