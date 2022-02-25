package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import p110z1.Scheduler;

/* renamed from: z1.bqu */
/* loaded from: classes3.dex */
public class NewThreadWorker extends Scheduler.AbstractC3881c implements Disposable {

    /* renamed from: a */
    volatile boolean f19932a;

    /* renamed from: b */
    private final ScheduledExecutorService f19933b;

    public NewThreadWorker(ThreadFactory threadFactory) {
        this.f19933b = SchedulerPoolFactory.m9491a(threadFactory);
    }

    @Override // p110z1.Scheduler.AbstractC3881c
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
        return mo9030a(runnable, 0L, null);
    }

    @Override // p110z1.Scheduler.AbstractC3881c
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
        if (this.f19932a) {
            return EmptyDisposable.INSTANCE;
        }
        return m9499a(runnable, j, timeUnit, (DisposableContainer) null);
    }

    /* renamed from: b */
    public Disposable m9496b(Runnable runnable, long j, TimeUnit timeUnit) {
        Future<?> future;
        ScheduledDirectTask bqyVar = new ScheduledDirectTask(RxJavaPlugins.m9213a(runnable));
        try {
            if (j <= 0) {
                future = this.f19933b.submit(bqyVar);
            } else {
                future = this.f19933b.schedule(bqyVar, j, timeUnit);
            }
            bqyVar.setFuture(future);
            return bqyVar;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.m9212a(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    /* renamed from: b */
    public Disposable m9497b(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future<?> future;
        Runnable a = RxJavaPlugins.m9213a(runnable);
        if (j2 <= 0) {
            InstantPeriodicTask bqrVar = new InstantPeriodicTask(a, this.f19933b);
            try {
                if (j <= 0) {
                    future = this.f19933b.submit(bqrVar);
                } else {
                    future = this.f19933b.schedule(bqrVar, j, timeUnit);
                }
                bqrVar.m9509a(future);
                return bqrVar;
            } catch (RejectedExecutionException e) {
                RxJavaPlugins.m9212a(e);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            ScheduledDirectPeriodicTask bqxVar = new ScheduledDirectPeriodicTask(a);
            try {
                bqxVar.setFuture(this.f19933b.scheduleAtFixedRate(bqxVar, j, j2, timeUnit));
                return bqxVar;
            } catch (RejectedExecutionException e2) {
                RxJavaPlugins.m9212a(e2);
                return EmptyDisposable.INSTANCE;
            }
        }
    }

    @AbstractC3889atm
    /* renamed from: a */
    public ScheduledRunnable m9499a(Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit, @atn DisposableContainer avaVar) {
        Future<?> future;
        ScheduledRunnable bqzVar = new ScheduledRunnable(RxJavaPlugins.m9213a(runnable), avaVar);
        if (avaVar != null && !avaVar.mo9939a(bqzVar)) {
            return bqzVar;
        }
        try {
            if (j <= 0) {
                future = this.f19933b.submit((Callable) bqzVar);
            } else {
                future = this.f19933b.schedule((Callable) bqzVar, j, timeUnit);
            }
            bqzVar.setFuture(future);
        } catch (RejectedExecutionException e) {
            if (avaVar != null) {
                avaVar.mo9937b(bqzVar);
            }
            RxJavaPlugins.m9212a(e);
        }
        return bqzVar;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        if (!this.f19932a) {
            this.f19932a = true;
            this.f19933b.shutdownNow();
        }
    }

    /* renamed from: b */
    public void m9498b() {
        if (!this.f19932a) {
            this.f19932a = true;
            this.f19933b.shutdown();
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f19932a;
    }
}
