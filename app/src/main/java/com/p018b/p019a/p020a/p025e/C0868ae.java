package com.p018b.p019a.p020a.p025e;

import com.p018b.p029b.AsyncTimeout;
import java.io.IOException;
import java.net.SocketTimeoutException;
import p110z1.C3894au;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Stream.java */
/* renamed from: com.b.a.a.e.ae */
/* loaded from: classes.dex */
public final class C0868ae extends AsyncTimeout {

    /* renamed from: a */
    final /* synthetic */ Http2Stream f5866a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0868ae(Http2Stream abVar) {
        this.f5866a = abVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p018b.p029b.AsyncTimeout
    /* renamed from: a */
    public final void mo24300a() {
        this.f5866a.m24712b(ErrorCode.CANCEL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p018b.p029b.AsyncTimeout
    /* renamed from: a */
    public final IOException mo24299a(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException(C3894au.f17527j);
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    /* renamed from: b */
    public final void m24699b() {
        if (m24354a_()) {
            throw mo24299a((IOException) null);
        }
    }
}
