package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.brd */
/* loaded from: classes3.dex */
public final class SingleScheduler extends Scheduler {

    /* renamed from: b */
    final ThreadFactory f19967b;

    /* renamed from: c */
    final AtomicReference<ScheduledExecutorService> f19968c;

    /* renamed from: e */
    static final ScheduledExecutorService f19964e = Executors.newScheduledThreadPool(0);

    /* renamed from: g */
    private static final String f19966g = "RxSingleScheduler";

    /* renamed from: f */
    private static final String f19965f = "rx2.single-priority";

    /* renamed from: d */
    static final RxThreadFactory f19963d = new RxThreadFactory(f19966g, Math.max(1, Math.min(10, Integer.getInteger(f19965f, 5).intValue())), true);

    static {
        f19964e.shutdown();
    }

    public SingleScheduler() {
        this(f19963d);
    }

    public SingleScheduler(ThreadFactory threadFactory) {
        this.f19968c = new AtomicReference<>();
        this.f19967b = threadFactory;
        this.f19968c.lazySet(m9484a(threadFactory));
    }

    /* renamed from: a */
    static ScheduledExecutorService m9484a(ThreadFactory threadFactory) {
        return SchedulerPoolFactory.m9491a(threadFactory);
    }

    @Override // p110z1.Scheduler
    /* renamed from: c */
    public void mo9483c() {
        ScheduledExecutorService scheduledExecutorService;
        ScheduledExecutorService scheduledExecutorService2 = null;
        do {
            scheduledExecutorService = this.f19968c.get();
            if (scheduledExecutorService != f19964e) {
                if (scheduledExecutorService2 != null) {
                    scheduledExecutorService2.shutdown();
                    return;
                }
                return;
            } else if (scheduledExecutorService2 == null) {
                scheduledExecutorService2 = m9484a(this.f19967b);
            }
        } while (!this.f19968c.compareAndSet(scheduledExecutorService, scheduledExecutorService2));
    }

    @Override // p110z1.Scheduler
    /* renamed from: d */
    public void mo9482d() {
        ScheduledExecutorService andSet;
        ScheduledExecutorService scheduledExecutorService = this.f19968c.get();
        ScheduledExecutorService scheduledExecutorService2 = f19964e;
        if (scheduledExecutorService != scheduledExecutorService2 && (andSet = this.f19968c.getAndSet(scheduledExecutorService2)) != f19964e) {
            andSet.shutdownNow();
        }
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new C4737a(this.f19968c.get());
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9480a(@AbstractC3889atm Runnable runnable, long j, TimeUnit timeUnit) {
        Future<?> future;
        ScheduledDirectTask bqyVar = new ScheduledDirectTask(RxJavaPlugins.m9213a(runnable));
        try {
            if (j <= 0) {
                future = this.f19968c.get().submit(bqyVar);
            } else {
                future = this.f19968c.get().schedule(bqyVar, j, timeUnit);
            }
            bqyVar.setFuture(future);
            return bqyVar;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.m9212a(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9485a(@AbstractC3889atm Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future<?> future;
        Runnable a = RxJavaPlugins.m9213a(runnable);
        if (j2 <= 0) {
            ScheduledExecutorService scheduledExecutorService = this.f19968c.get();
            InstantPeriodicTask bqrVar = new InstantPeriodicTask(a, scheduledExecutorService);
            try {
                if (j <= 0) {
                    future = scheduledExecutorService.submit(bqrVar);
                } else {
                    future = scheduledExecutorService.schedule(bqrVar, j, timeUnit);
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
                bqxVar.setFuture(this.f19968c.get().scheduleAtFixedRate(bqxVar, j, j2, timeUnit));
                return bqxVar;
            } catch (RejectedExecutionException e2) {
                RxJavaPlugins.m9212a(e2);
                return EmptyDisposable.INSTANCE;
            }
        }
    }

    /* compiled from: SingleScheduler.java */
    /* renamed from: z1.brd$a */
    /* loaded from: classes3.dex */
    static final class C4737a extends Scheduler.AbstractC3881c {

        /* renamed from: a */
        final ScheduledExecutorService f19969a;

        /* renamed from: b */
        final CompositeDisposable f19970b = new CompositeDisposable();

        /* renamed from: c */
        volatile boolean f19971c;

        C4737a(ScheduledExecutorService scheduledExecutorService) {
            this.f19969a = scheduledExecutorService;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            Future<?> future;
            if (this.f19971c) {
                return EmptyDisposable.INSTANCE;
            }
            ScheduledRunnable bqzVar = new ScheduledRunnable(RxJavaPlugins.m9213a(runnable), this.f19970b);
            this.f19970b.mo9939a(bqzVar);
            try {
                if (j <= 0) {
                    future = this.f19969a.submit((Callable) bqzVar);
                } else {
                    future = this.f19969a.schedule((Callable) bqzVar, j, timeUnit);
                }
                bqzVar.setFuture(future);
                return bqzVar;
            } catch (RejectedExecutionException e) {
                dispose();
                RxJavaPlugins.m9212a(e);
                return EmptyDisposable.INSTANCE;
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.f19971c) {
                this.f19971c = true;
                this.f19970b.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19971c;
        }
    }
}
