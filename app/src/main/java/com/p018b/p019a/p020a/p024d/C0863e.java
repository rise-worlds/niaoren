package com.p018b.p019a.p020a.p024d;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.ForwardingTimeout;
import com.p018b.p029b.Sink;
import com.p018b.p029b.Timeout;
import java.net.ProtocolException;

/* compiled from: Http1Codec.java */
/* renamed from: com.b.a.a.d.e */
/* loaded from: classes.dex */
final class C0863e implements Sink {

    /* renamed from: a */
    final /* synthetic */ Http1Codec f5833a;

    /* renamed from: b */
    private final ForwardingTimeout f5834b;

    /* renamed from: c */
    private boolean f5835c;

    /* renamed from: d */
    private long f5836d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0863e(Http1Codec aVar, long j) {
        this.f5833a = aVar;
        this.f5834b = new ForwardingTimeout(this.f5833a.f5821d.mo24252a());
        this.f5836d = j;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a */
    public final Timeout mo24252a() {
        return this.f5834b;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a_ */
    public final void mo24251a_(Buffer fVar, long j) {
        if (!this.f5835c) {
            Util.m24769a(fVar.m24331b(), j);
            if (j <= this.f5836d) {
                this.f5833a.f5821d.mo24251a_(fVar, j);
                this.f5836d -= j;
                return;
            }
            throw new ProtocolException("expected " + this.f5836d + " bytes but received " + j);
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.Sink, java.io.Flushable
    public final void flush() {
        if (!this.f5835c) {
            this.f5833a.f5821d.flush();
        }
    }

    @Override // com.p018b.p029b.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f5835c) {
            this.f5835c = true;
            if (this.f5836d <= 0) {
                Http1Codec.m24721a(this.f5834b);
                this.f5833a.f5822e = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }
    }
}
