package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;
import org.apache.tools.ant.taskdefs.WaitFor;

/* renamed from: com.alipay.android.phone.mrpc.core.f */
/* loaded from: classes.dex */
final class C0599f implements ConnectionKeepAliveStrategy {

    /* renamed from: a */
    final /* synthetic */ C0597d f149a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0599f(C0597d dVar) {
        this.f149a = dVar;
    }

    @Override // org.apache.http.conn.ConnectionKeepAliveStrategy
    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return WaitFor.DEFAULT_MAX_WAIT_MILLIS;
    }
}
