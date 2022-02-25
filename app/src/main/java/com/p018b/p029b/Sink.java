package com.p018b.p029b;

import java.io.Closeable;
import java.io.Flushable;

/* renamed from: com.b.b.w */
/* loaded from: classes.dex */
public interface Sink extends Closeable, Flushable {
    /* renamed from: a */
    Timeout mo24252a();

    /* renamed from: a_ */
    void mo24251a_(Buffer fVar, long j);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();
}
