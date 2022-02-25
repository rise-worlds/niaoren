package com.p018b.p019a.p020a;

import java.util.concurrent.ThreadFactory;

/* compiled from: Util.java */
/* renamed from: com.b.a.a.d */
/* loaded from: classes.dex */
final class ThreadFactoryC0859d implements ThreadFactory {

    /* renamed from: a */
    final /* synthetic */ String f5816a;

    /* renamed from: b */
    final /* synthetic */ boolean f5817b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadFactoryC0859d(String str, boolean z) {
        this.f5816a = str;
        this.f5817b = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f5816a);
        thread.setDaemon(this.f5817b);
        return thread;
    }
}
