package com.alipay.sdk.app;

import android.app.Activity;

/* renamed from: com.alipay.sdk.app.h */
/* loaded from: classes.dex */
final class RunnableC0659h implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Activity f322a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0659h(Activity activity) {
        this.f322a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f322a.finish();
    }
}
