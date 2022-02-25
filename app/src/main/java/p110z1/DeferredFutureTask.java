package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import p110z1.DeferredManager;

/* renamed from: z1.dan */
/* loaded from: classes3.dex */
public class DeferredFutureTask<D, P> extends FutureTask<D> {

    /* renamed from: a */
    protected final Deferred<D, Throwable, P> f21226a;

    /* renamed from: b */
    protected final DeferredManager.EnumC5238a f21227b;

    public DeferredFutureTask(Callable<D> callable) {
        super(callable);
        this.f21226a = new DeferredObject();
        this.f21227b = DeferredManager.EnumC5238a.DEFAULT;
    }

    public DeferredFutureTask(Runnable runnable) {
        super(runnable, null);
        this.f21226a = new DeferredObject();
        this.f21227b = DeferredManager.EnumC5238a.DEFAULT;
    }

    public DeferredFutureTask(DeferredCallable<D, P> damVar) {
        super(damVar);
        this.f21226a = damVar.m3346a();
        this.f21227b = damVar.m3344b();
    }

    public DeferredFutureTask(DeferredRunnable<P> dapVar) {
        super(dapVar, null);
        this.f21226a = (Deferred<Void, Throwable, P>) dapVar.m3341a();
        this.f21227b = dapVar.m3339b();
    }

    /* renamed from: a */
    public Promise<D, Throwable, P> m3343a() {
        return this.f21226a.mo3300a();
    }

    @Override // java.util.concurrent.FutureTask
    protected void done() {
        try {
            if (isCancelled()) {
                this.f21226a.mo3298b((Deferred<D, Throwable, P>) new CancellationException());
            }
            this.f21226a.mo3299a((Deferred<D, Throwable, P>) get());
        } catch (InterruptedException unused) {
        } catch (ExecutionException e) {
            this.f21226a.mo3298b((Deferred<D, Throwable, P>) e.getCause());
        }
    }

    /* renamed from: b */
    public DeferredManager.EnumC5238a m3342b() {
        return this.f21227b;
    }
}
