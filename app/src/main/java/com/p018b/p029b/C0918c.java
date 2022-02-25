package com.p018b.p029b;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AsyncTimeout.java */
/* renamed from: com.b.b.c */
/* loaded from: classes.dex */
public final class C0918c implements Source {

    /* renamed from: a */
    final /* synthetic */ Source f6416a;

    /* renamed from: b */
    final /* synthetic */ AsyncTimeout f6417b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0918c(AsyncTimeout aVar, Source xVar) {
        this.f6417b = aVar;
        this.f6416a = xVar;
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final long mo24249a(Buffer fVar, long j) {
        this.f6417b.m24352c();
        try {
            try {
                long a = this.f6416a.mo24249a(fVar, j);
                this.f6417b.m24355a(true);
                return a;
            } catch (IOException e) {
                throw this.f6417b.m24353b(e);
            }
        } catch (Throwable th) {
            this.f6417b.m24355a(false);
            throw th;
        }
    }

    @Override // com.p018b.p029b.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            try {
                this.f6416a.close();
                this.f6417b.m24355a(true);
            } catch (IOException e) {
                throw this.f6417b.m24353b(e);
            }
        } catch (Throwable th) {
            this.f6417b.m24355a(false);
            throw th;
        }
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f6417b;
    }

    public final String toString() {
        return "AsyncTimeout.source(" + this.f6416a + ")";
    }
}
