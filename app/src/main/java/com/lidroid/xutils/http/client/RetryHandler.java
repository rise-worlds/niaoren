package com.lidroid.xutils.http.client;

import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;

/* loaded from: classes.dex */
public class RetryHandler implements HttpRequestRetryHandler {
    private static final int RETRY_SLEEP_INTERVAL = 500;
    private final int maxRetries;
    private static HashSet<Class<?>> exceptionWhiteList = new HashSet<>();
    private static HashSet<Class<?>> exceptionBlackList = new HashSet<>();

    static {
        exceptionWhiteList.add(NoHttpResponseException.class);
        exceptionWhiteList.add(UnknownHostException.class);
        exceptionWhiteList.add(SocketException.class);
        exceptionBlackList.add(InterruptedIOException.class);
        exceptionBlackList.add(SSLHandshakeException.class);
    }

    public RetryHandler(int i) {
        this.maxRetries = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
    @Override // org.apache.http.client.HttpRequestRetryHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean retryRequest(java.io.IOException r5, int r6, org.apache.http.protocol.HttpContext r7) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L_0x0078
            if (r7 != 0) goto L_0x0007
            goto L_0x0078
        L_0x0007:
            java.lang.String r1 = "http.request_sent"
            java.lang.Object r1 = r7.getAttribute(r1)
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0017
        L_0x0011:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
        L_0x0017:
            int r2 = r4.maxRetries
            r3 = 1
            if (r6 <= r2) goto L_0x001e
            r3 = 0
            goto L_0x0036
        L_0x001e:
            java.util.HashSet<java.lang.Class<?>> r6 = com.lidroid.xutils.http.client.RetryHandler.exceptionBlackList
            java.lang.Class r2 = r5.getClass()
            boolean r6 = r6.contains(r2)
            if (r6 == 0) goto L_0x002c
            r3 = 0
            goto L_0x0036
        L_0x002c:
            java.util.HashSet<java.lang.Class<?>> r6 = com.lidroid.xutils.http.client.RetryHandler.exceptionWhiteList
            java.lang.Class r5 = r5.getClass()
            boolean r5 = r6.contains(r5)
        L_0x0036:
            if (r3 == 0) goto L_0x006f
            java.lang.String r5 = "http.request"
            java.lang.Object r5 = r7.getAttribute(r5)     // Catch: Throwable -> 0x0068
            if (r5 == 0) goto L_0x0062
            boolean r6 = r5 instanceof org.apache.http.client.methods.HttpRequestBase     // Catch: Throwable -> 0x0068
            if (r6 == 0) goto L_0x0051
            org.apache.http.client.methods.HttpRequestBase r5 = (org.apache.http.client.methods.HttpRequestBase) r5     // Catch: Throwable -> 0x0068
            java.lang.String r6 = "GET"
            java.lang.String r5 = r5.getMethod()     // Catch: Throwable -> 0x0068
            boolean r0 = r6.equals(r5)     // Catch: Throwable -> 0x0068
            goto L_0x0070
        L_0x0051:
            boolean r6 = r5 instanceof org.apache.http.impl.client.RequestWrapper     // Catch: Throwable -> 0x0068
            if (r6 == 0) goto L_0x006f
            org.apache.http.impl.client.RequestWrapper r5 = (org.apache.http.impl.client.RequestWrapper) r5     // Catch: Throwable -> 0x0068
            java.lang.String r6 = "GET"
            java.lang.String r5 = r5.getMethod()     // Catch: Throwable -> 0x0068
            boolean r0 = r6.equals(r5)     // Catch: Throwable -> 0x0068
            goto L_0x0070
        L_0x0062:
            java.lang.String r5 = "retry error, curr request is null"
            com.lidroid.xutils.util.LogUtils.m19220e(r5)     // Catch: Throwable -> 0x0068
            goto L_0x0070
        L_0x0068:
            r5 = move-exception
            java.lang.String r6 = "retry error"
            com.lidroid.xutils.util.LogUtils.m19219e(r6, r5)
            goto L_0x0070
        L_0x006f:
            r0 = r3
        L_0x0070:
            if (r0 == 0) goto L_0x0077
            r5 = 500(0x1f4, double:2.47E-321)
            android.os.SystemClock.sleep(r5)
        L_0x0077:
            return r0
        L_0x0078:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.http.client.RetryHandler.retryRequest(java.io.IOException, int, org.apache.http.protocol.HttpContext):boolean");
    }
}
