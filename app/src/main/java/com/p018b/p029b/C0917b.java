package com.p018b.p029b;

import android.support.p003v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AsyncTimeout.java */
/* renamed from: com.b.b.b */
/* loaded from: classes.dex */
public final class C0917b implements Sink {

    /* renamed from: a */
    final /* synthetic */ Sink f6414a;

    /* renamed from: b */
    final /* synthetic */ AsyncTimeout f6415b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0917b(AsyncTimeout aVar, Sink wVar) {
        this.f6415b = aVar;
        this.f6414a = wVar;
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a_ */
    public final void mo24251a_(Buffer fVar, long j) {
        C0916aa.m24347a(fVar.f6422b, 0L, j);
        while (true) {
            long j2 = 0;
            if (j > 0) {
                Segment tVar = fVar.f6421a;
                while (true) {
                    if (j2 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        break;
                    }
                    j2 += fVar.f6421a.f6453c - fVar.f6421a.f6452b;
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                }
                this.f6415b.m24352c();
                try {
                    try {
                        this.f6414a.mo24251a_(fVar, j2);
                        j -= j2;
                        this.f6415b.m24355a(true);
                    } catch (IOException e) {
                        throw this.f6415b.m24353b(e);
                    }
                } catch (Throwable th) {
                    this.f6415b.m24355a(false);
                    throw th;
                }
            } else {
                return;
            }
        }
    }

    @Override // com.p018b.p029b.Sink, java.io.Flushable
    public final void flush() {
        this.f6415b.m24352c();
        try {
            try {
                this.f6414a.flush();
                this.f6415b.m24355a(true);
            } catch (IOException e) {
                throw this.f6415b.m24353b(e);
            }
        } catch (Throwable th) {
            this.f6415b.m24355a(false);
            throw th;
        }
    }

    @Override // com.p018b.p029b.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6415b.m24352c();
        try {
            try {
                this.f6414a.close();
                this.f6415b.m24355a(true);
            } catch (IOException e) {
                throw this.f6415b.m24353b(e);
            }
        } catch (Throwable th) {
            this.f6415b.m24355a(false);
            throw th;
        }
    }

    @Override // com.p018b.p029b.Sink
    /* renamed from: a */
    public final Timeout mo24252a() {
        return this.f6415b;
    }

    public final String toString() {
        return "AsyncTimeout.sink(" + this.f6414a + ")";
    }
}
