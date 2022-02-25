package com.p018b.p019a.p020a.p024d;

import com.p018b.p019a.HttpUrl;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p023c.HttpHeaders;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.Source;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import p110z1.C4963cj;

/* compiled from: Http1Codec.java */
/* renamed from: com.b.a.a.d.d */
/* loaded from: classes.dex */
final class C0862d extends AbstractC0860b {

    /* renamed from: d */
    final /* synthetic */ Http1Codec f5829d;

    /* renamed from: e */
    private final HttpUrl f5830e;

    /* renamed from: f */
    private long f5831f = -1;

    /* renamed from: g */
    private boolean f5832g = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0862d(Http1Codec aVar, HttpUrl acVar) {
        super(aVar, (byte) 0);
        this.f5829d = aVar;
        this.f5830e = acVar;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f5824b) {
            throw new IllegalStateException("closed");
        } else if (!this.f5832g) {
            return -1L;
        } else {
            long j2 = this.f5831f;
            if (j2 == 0 || j2 == -1) {
                if (this.f5831f != -1) {
                    this.f5829d.f5820c.mo24273m();
                }
                try {
                    this.f5831f = this.f5829d.f5820c.mo24274j();
                    String trim = this.f5829d.f5820c.mo24273m().trim();
                    if (this.f5831f < 0 || (!trim.isEmpty() && !trim.startsWith(C4963cj.f20745b))) {
                        throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f5831f + trim + "\"");
                    }
                    if (this.f5831f == 0) {
                        this.f5832g = false;
                        HttpHeaders.m24741a(this.f5829d.f5818a.m24501f(), this.f5830e, this.f5829d.m24720c());
                        m24719a(true);
                    }
                    if (!this.f5832g) {
                        return -1L;
                    }
                } catch (NumberFormatException e) {
                    throw new ProtocolException(e.getMessage());
                }
            }
            long a = this.f5829d.f5820c.mo24249a(fVar, Math.min(j, this.f5831f));
            if (a != -1) {
                this.f5831f -= a;
                return a;
            }
            m24719a(false);
            throw new ProtocolException("unexpected end of stream");
        }
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f5824b) {
            if (this.f5832g && !Util.m24765a((Source) this, TimeUnit.MILLISECONDS)) {
                m24719a(false);
            }
            this.f5824b = true;
        }
    }
}
