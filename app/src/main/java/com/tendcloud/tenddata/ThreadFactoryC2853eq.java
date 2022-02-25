package com.tendcloud.tenddata;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.eq */
/* loaded from: classes2.dex */
final class ThreadFactoryC2853eq implements ThreadFactory {
    private final AtomicInteger mCount = new AtomicInteger(1);

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "ThreadPoolUtils #" + this.mCount.getAndIncrement());
    }
}
