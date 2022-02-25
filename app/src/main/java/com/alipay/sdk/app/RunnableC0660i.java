package com.alipay.sdk.app;

import android.app.Activity;
import p110z1.C3876ar;
import p110z1.C4745bt;
import p110z1.C4828bz;
import p110z1.C4921cd;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.alipay.sdk.app.i */
/* loaded from: classes.dex */
public class RunnableC0660i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f323a;

    /* renamed from: b */
    final /* synthetic */ boolean f324b;

    /* renamed from: c */
    final /* synthetic */ H5PayCallback f325c;

    /* renamed from: d */
    final /* synthetic */ PayTask f326d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0660i(PayTask payTask, String str, boolean z, H5PayCallback h5PayCallback) {
        this.f326d = payTask;
        this.f323a = str;
        this.f324b = z;
        this.f325c = h5PayCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        Activity activity;
        activity = this.f326d.f286b;
        C4828bz h5Pay = this.f326d.h5Pay(new C4745bt(activity, this.f323a, "payInterceptorWithUrl"), this.f323a, this.f324b);
        C4921cd.m5616b(C3876ar.f17447x, "inc finished: " + h5Pay.m8292b());
        this.f325c.onPayResult(h5Pay);
    }
}
