package com.tendcloud.tenddata;

import com.tendcloud.tenddata.AbstractRunnableC2798dh;
import java.lang.Thread;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.di */
/* loaded from: classes2.dex */
class C2801di implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ AbstractRunnableC2798dh.C2800b this$1;
    final /* synthetic */ AbstractRunnableC2798dh val$this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2801di(AbstractRunnableC2798dh.C2800b bVar, AbstractRunnableC2798dh dhVar) {
        this.this$1 = bVar;
        this.val$this$0 = dhVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, th);
    }
}
