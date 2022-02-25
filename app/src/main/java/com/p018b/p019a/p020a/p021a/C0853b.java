package com.p018b.p019a.p020a.p021a;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSink;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.Source;
import com.p018b.p029b.Timeout;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* compiled from: CacheInterceptor.java */
/* renamed from: com.b.a.a.a.b */
/* loaded from: classes.dex */
final class C0853b implements Source {

    /* renamed from: a */
    boolean f5691a;

    /* renamed from: b */
    final /* synthetic */ BufferedSource f5692b;

    /* renamed from: c */
    final /* synthetic */ CacheRequest f5693c;

    /* renamed from: d */
    final /* synthetic */ BufferedSink f5694d;

    /* renamed from: e */
    final /* synthetic */ CacheInterceptor f5695e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0853b(CacheInterceptor aVar, BufferedSource hVar, CacheRequest cVar, BufferedSink gVar) {
        this.f5695e = aVar;
        this.f5692b = hVar;
        this.f5693c = cVar;
        this.f5694d = gVar;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        try {
            long a = this.f5692b.mo24249a(fVar, j);
            if (a == -1) {
                if (!this.f5691a) {
                    this.f5691a = true;
                    this.f5694d.close();
                }
                return -1L;
            }
            fVar.m24338a(this.f5694d.mo24284c(), fVar.m24331b() - a, a);
            this.f5694d.mo24290p();
            return a;
        } catch (IOException e) {
            if (!this.f5691a) {
                this.f5691a = true;
            }
            throw e;
        }
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f5692b.mo24250a();
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f5691a && !Util.m24765a((Source) this, TimeUnit.MILLISECONDS)) {
            this.f5691a = true;
        }
        this.f5692b.close();
    }
}
