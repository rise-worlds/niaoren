package com.p018b.p029b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.b.b.r */
/* loaded from: classes.dex */
public final class RealBufferedSink implements BufferedSink {

    /* renamed from: a */
    public final Buffer f6445a = new Buffer();

    /* renamed from: b */
    public final Sink f6446b;

    /* renamed from: c */
    boolean f6447c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RealBufferedSink(Sink wVar) {
        if (wVar != null) {
            this.f6446b = wVar;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // com.p018b.p029b.BufferedSink, com.p018b.p029b.BufferedSource
    /* renamed from: c */
    public final Buffer mo24284c() {
        return this.f6445a;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a_ */
    public final void mo24251a_(Buffer fVar, long j) {
        if (!this.f6447c) {
            this.f6445a.mo24251a_(fVar, j);
            mo24290p();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: b */
    public final BufferedSink mo24298b(String str) {
        if (!this.f6447c) {
            this.f6445a.mo24298b(str);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: b */
    public final BufferedSink mo24297b(byte[] bArr) {
        if (!this.f6447c) {
            this.f6445a.mo24297b(bArr);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: b */
    public final BufferedSink mo24296b(byte[] bArr, int i, int i2) {
        if (!this.f6447c) {
            this.f6445a.mo24296b(bArr, i, i2);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: h */
    public final BufferedSink mo24293h(int i) {
        if (!this.f6447c) {
            this.f6445a.mo24293h(i);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: g */
    public final BufferedSink mo24294g(int i) {
        if (!this.f6447c) {
            this.f6445a.mo24294g(i);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: f */
    public final BufferedSink mo24295f(int i) {
        if (!this.f6447c) {
            this.f6445a.mo24295f(i);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: j */
    public final BufferedSink mo24291j(long j) {
        if (!this.f6447c) {
            this.f6445a.mo24291j(j);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: i */
    public final BufferedSink mo24292i(long j) {
        if (!this.f6447c) {
            this.f6445a.mo24292i(j);
            return mo24290p();
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink
    /* renamed from: p */
    public final BufferedSink mo24290p() {
        if (!this.f6447c) {
            Buffer fVar = this.f6445a;
            long j = fVar.f6422b;
            if (j == 0) {
                j = 0;
            } else {
                Segment tVar = fVar.f6421a.f6457g;
                if (tVar.f6453c < 8192 && tVar.f6455e) {
                    j -= tVar.f6453c - tVar.f6452b;
                }
            }
            if (j > 0) {
                this.f6446b.mo24251a_(this.f6445a, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.BufferedSink, com.p018b.p029b.Sink, java.io.Flushable
    public final void flush() {
        if (!this.f6447c) {
            if (this.f6445a.f6422b > 0) {
                Sink wVar = this.f6446b;
                Buffer fVar = this.f6445a;
                wVar.mo24251a_(fVar, fVar.f6422b);
            }
            this.f6446b.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.p018b.p029b.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f6447c) {
            Throwable th = null;
            try {
                if (this.f6445a.f6422b > 0) {
                    this.f6446b.mo24251a_(this.f6445a, this.f6445a.f6422b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.f6446b.close();
            } catch (Throwable th3) {
                th = th3;
                if (th == null) {
                }
            }
            this.f6447c = true;
            if (th != null) {
                C0916aa.m24346a(th);
            }
        }
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a */
    public final Timeout mo24252a() {
        return this.f6446b.mo24252a();
    }

    public final String toString() {
        return "buffer(" + this.f6446b + ")";
    }
}
