package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.e */
/* loaded from: classes.dex */
final class C0598e extends DefaultRedirectHandler {

    /* renamed from: a */
    int f147a;

    /* renamed from: b */
    final /* synthetic */ C0597d f148b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0598e(C0597d dVar) {
        this.f148b = dVar;
    }

    @Override // org.apache.http.impl.client.DefaultRedirectHandler, org.apache.http.client.RedirectHandler
    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        int statusCode;
        this.f147a++;
        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.f147a >= 5 || ((statusCode = httpResponse.getStatusLine().getStatusCode()) != 301 && statusCode != 302)) {
            return isRedirectRequested;
        }
        return true;
    }
}
