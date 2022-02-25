package com.p018b.p019a;

import com.p018b.p019a.p020a.p021a.DiskLruCache;
import com.p018b.p019a.p020a.p021a.InternalCache;
import java.io.Closeable;
import java.io.Flushable;

/* renamed from: com.b.a.d */
/* loaded from: classes.dex */
public final class Cache implements Closeable, Flushable {

    /* renamed from: a */
    final InternalCache f6210a;

    /* renamed from: b */
    final DiskLruCache f6211b;

    @Override // java.io.Flushable
    public final void flush() {
        this.f6211b.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6211b.close();
    }
}
