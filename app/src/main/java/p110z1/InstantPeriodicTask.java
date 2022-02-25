package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.bqr */
/* loaded from: classes3.dex */
public final class InstantPeriodicTask implements Callable<Void>, Disposable {

    /* renamed from: f */
    static final FutureTask<Void> f19898f = new FutureTask<>(Functions.f17556b, null);

    /* renamed from: a */
    final Runnable f19899a;

    /* renamed from: d */
    final ExecutorService f19902d;

    /* renamed from: e */
    Thread f19903e;

    /* renamed from: c */
    final AtomicReference<Future<?>> f19901c = new AtomicReference<>();

    /* renamed from: b */
    final AtomicReference<Future<?>> f19900b = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public InstantPeriodicTask(Runnable runnable, ExecutorService executorService) {
        this.f19899a = runnable;
        this.f19902d = executorService;
    }

    /* renamed from: a */
    public Void call() throws Exception {
        this.f19903e = Thread.currentThread();
        try {
            this.f19899a.run();
            m9508b(this.f19902d.submit(this));
            this.f19903e = null;
        } catch (Throwable th) {
            this.f19903e = null;
            RxJavaPlugins.m9212a(th);
        }
        return null;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        Future<?> andSet = this.f19901c.getAndSet(f19898f);
        boolean z = true;
        if (!(andSet == null || andSet == f19898f)) {
            andSet.cancel(this.f19903e != Thread.currentThread());
        }
        Future<?> andSet2 = this.f19900b.getAndSet(f19898f);
        if (andSet2 != null && andSet2 != f19898f) {
            if (this.f19903e == Thread.currentThread()) {
                z = false;
            }
            andSet2.cancel(z);
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f19901c.get() == f19898f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m9509a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.f19901c.get();
            if (future2 == f19898f) {
                future.cancel(this.f19903e != Thread.currentThread());
                return;
            }
        } while (!this.f19901c.compareAndSet(future2, future));
    }

    /* renamed from: b */
    void m9508b(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.f19900b.get();
            if (future2 == f19898f) {
                future.cancel(this.f19903e != Thread.currentThread());
                return;
            }
        } while (!this.f19900b.compareAndSet(future2, future));
    }
}
