package com.p018b.p019a.p020a.p024d;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.Source;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http1Codec.java */
/* renamed from: com.b.a.a.d.f */
/* loaded from: classes.dex */
public final class C0864f extends AbstractC0860b {

    /* renamed from: d */
    final /* synthetic */ Http1Codec f5837d;

    /* renamed from: e */
    private long f5838e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0864f(Http1Codec aVar, long j) {
        super(aVar, (byte) 0);
        this.f5837d = aVar;
        this.f5838e = j;
        if (this.f5838e == 0) {
            m24719a(true);
        }
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f5824b) {
            throw new IllegalStateException("closed");
        } else if (this.f5838e == 0) {
            return -1L;
        } else {
            long a = this.f5837d.f5820c.mo24249a(fVar, Math.min(this.f5838e, j));
            if (a != -1) {
                this.f5838e -= a;
                if (this.f5838e == 0) {
                    m24719a(true);
                }
                return a;
            }
            m24719a(false);
            throw new ProtocolException("unexpected end of stream");
        }
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f5824b) {
            if (this.f5838e != 0 && !Util.m24765a((Source) this, TimeUnit.MILLISECONDS)) {
                m24719a(false);
            }
            this.f5824b = true;
        }
    }
}
