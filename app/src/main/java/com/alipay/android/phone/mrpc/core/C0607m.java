package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* renamed from: com.alipay.android.phone.mrpc.core.m */
/* loaded from: classes.dex */
final class C0607m extends FutureTask<C0615u> {

    /* renamed from: a */
    final /* synthetic */ CallableC0611q f168a;

    /* renamed from: b */
    final /* synthetic */ C0606l f169b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0607m(C0606l lVar, Callable callable, CallableC0611q qVar) {
        super(callable);
        this.f169b = lVar;
        this.f168a = qVar;
    }

    @Override // java.util.concurrent.FutureTask
    protected final void done() {
        C0609o a = this.f168a.m25468a();
        if (a.m25451f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a.m25449h()) {
                a.m25450g();
                if (!isCancelled() || !isDone()) {
                    cancel(false);
                }
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (CancellationException unused) {
            a.m25450g();
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (Throwable th) {
            throw new RuntimeException("An error occured while executing http request", th);
        }
    }
}
