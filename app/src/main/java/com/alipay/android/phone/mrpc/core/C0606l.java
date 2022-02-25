package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.alipay.android.phone.mrpc.core.l */
/* loaded from: classes.dex */
public final class C0606l implements AbstractC0590ab {

    /* renamed from: b */
    private static C0606l f159b;

    /* renamed from: i */
    private static final ThreadFactory f160i = new ThreadFactoryC0608n();

    /* renamed from: a */
    Context f161a;

    /* renamed from: e */
    private long f164e;

    /* renamed from: f */
    private long f165f;

    /* renamed from: g */
    private long f166g;

    /* renamed from: h */
    private int f167h;

    /* renamed from: d */
    private C0593b f163d = C0593b.m25511a("android");

    /* renamed from: c */
    private ThreadPoolExecutor f162c = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), f160i, new ThreadPoolExecutor.CallerRunsPolicy());

    private C0606l(Context context) {
        this.f161a = context;
        try {
            this.f162c.allowCoreThreadTimeOut(true);
        } catch (Exception unused) {
        }
        CookieSyncManager.createInstance(this.f161a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    /* renamed from: a */
    public static final C0606l m25488a(Context context) {
        C0606l lVar = f159b;
        return lVar != null ? lVar : m25485b(context);
    }

    /* renamed from: b */
    private static final synchronized C0606l m25485b(Context context) {
        synchronized (C0606l.class) {
            if (f159b != null) {
                return f159b;
            }
            C0606l lVar = new C0606l(context);
            f159b = lVar;
            return lVar;
        }
    }

    /* renamed from: a */
    public final C0593b m25490a() {
        return this.f163d;
    }

    @Override // com.alipay.android.phone.mrpc.core.AbstractC0590ab
    /* renamed from: a */
    public final Future<C0615u> mo25487a(AbstractC0614t tVar) {
        if (C0613s.m25452a(this.f161a)) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.f162c.getActiveCount());
            objArr[1] = Long.valueOf(this.f162c.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.f162c.getTaskCount());
            long j = this.f166g;
            long j2 = 0;
            objArr[3] = Long.valueOf(j == 0 ? 0L : ((this.f164e * 1000) / j) >> 10);
            int i = this.f167h;
            if (i != 0) {
                j2 = this.f165f / i;
            }
            objArr[4] = Long.valueOf(j2);
            objArr[5] = Long.valueOf(this.f164e);
            objArr[6] = Long.valueOf(this.f165f);
            objArr[7] = Long.valueOf(this.f166g);
            objArr[8] = Integer.valueOf(this.f167h);
            String.format(str, objArr);
        }
        CallableC0611q qVar = new CallableC0611q(this, (C0609o) tVar);
        C0607m mVar = new C0607m(this, qVar, qVar);
        this.f162c.execute(mVar);
        return mVar;
    }

    /* renamed from: a */
    public final void m25489a(long j) {
        this.f164e += j;
    }

    /* renamed from: b */
    public final void m25486b(long j) {
        this.f165f += j;
        this.f167h++;
    }

    /* renamed from: c */
    public final void m25484c(long j) {
        this.f166g += j;
    }
}
