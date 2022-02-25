package com.p018b.p029b;

import java.io.Closeable;

/* renamed from: com.b.b.x */
/* loaded from: classes.dex */
public interface Source extends Closeable {
    /* renamed from: a */
    long mo24249a(Buffer fVar, long j);

    /* renamed from: a */
    Timeout mo24250a();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
