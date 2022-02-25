package com.alipay.sdk.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: com.alipay.sdk.widget.d */
/* loaded from: classes.dex */
class HandlerC0669d extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0665a f359a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0669d(C0665a aVar, Looper looper) {
        super(looper);
        this.f359a = aVar;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        this.f359a.m25267c();
    }
}
