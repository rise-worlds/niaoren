package com.p018b.p029b;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import p110z1.C3894au;

/* compiled from: Okio.java */
/* renamed from: com.b.b.q */
/* loaded from: classes.dex */
final class C0922q extends AsyncTimeout {

    /* renamed from: a */
    final /* synthetic */ Socket f6444a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0922q(Socket socket) {
        this.f6444a = socket;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p018b.p029b.AsyncTimeout
    /* renamed from: a */
    public final void mo24300a() {
        try {
            this.f6444a.close();
        } catch (AssertionError e) {
            if (Okio.m24304a(e)) {
                Logger logger = Okio.f6439a;
                Level level = Level.WARNING;
                logger.log(level, "Failed to close timed out socket " + this.f6444a, (Throwable) e);
                return;
            }
            throw e;
        } catch (Exception e2) {
            Logger logger2 = Okio.f6439a;
            Level level2 = Level.WARNING;
            logger2.log(level2, "Failed to close timed out socket " + this.f6444a, (Throwable) e2);
        }
    }
}
