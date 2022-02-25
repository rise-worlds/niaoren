package com.p018b.p019a.p020a.p025e;

import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.Source;
import com.p018b.p029b.Timeout;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Reader.java */
/* renamed from: com.b.a.a.e.z */
/* loaded from: classes.dex */
public final class C0889z implements Source {

    /* renamed from: a */
    int f6012a;

    /* renamed from: b */
    byte f6013b;

    /* renamed from: c */
    int f6014c;

    /* renamed from: d */
    int f6015d;

    /* renamed from: e */
    short f6016e;

    /* renamed from: f */
    private final BufferedSource f6017f;

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    public C0889z(BufferedSource hVar) {
        this.f6017f = hVar;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        int i;
        do {
            int i2 = this.f6015d;
            if (i2 == 0) {
                this.f6017f.mo24278f(this.f6016e);
                this.f6016e = (short) 0;
                if ((this.f6013b & 4) != 0) {
                    return -1L;
                }
                i = this.f6014c;
                int a = C0888y.m24600a(this.f6017f);
                this.f6015d = a;
                this.f6012a = a;
                byte e = (byte) (this.f6017f.mo24281e() & 255);
                this.f6013b = (byte) (this.f6017f.mo24281e() & 255);
                if (C0888y.f6007a.isLoggable(Level.FINE)) {
                    C0888y.f6007a.fine(Http2.m24643a(true, this.f6014c, this.f6012a, e, this.f6013b));
                }
                this.f6014c = this.f6017f.mo24277g() & Integer.MAX_VALUE;
                if (e != 9) {
                    throw Http2.m24642b("%s != TYPE_CONTINUATION", Byte.valueOf(e));
                }
            } else {
                long a2 = this.f6017f.mo24249a(fVar, Math.min(j, i2));
                if (a2 == -1) {
                    return -1L;
                }
                this.f6015d = (int) (this.f6015d - a2);
                return a2;
            }
        } while (this.f6014c == i);
        throw Http2.m24642b("TYPE_CONTINUATION streamId changed", new Object[0]);
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f6017f.mo24250a();
    }
}
