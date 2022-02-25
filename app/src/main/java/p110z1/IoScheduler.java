package p110z1;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bqs */
/* loaded from: classes3.dex */
public final class IoScheduler extends Scheduler {

    /* renamed from: b */
    static final RxThreadFactory f19904b;

    /* renamed from: c */
    static final RxThreadFactory f19905c;

    /* renamed from: d */
    public static final long f19906d = 60;

    /* renamed from: h */
    static final RunnableC4721a f19908h;

    /* renamed from: i */
    private static final String f19909i = "RxCachedThreadScheduler";

    /* renamed from: j */
    private static final String f19910j = "RxCachedWorkerPoolEvictor";

    /* renamed from: n */
    private static final String f19914n = "rx2.io-priority";

    /* renamed from: f */
    final ThreadFactory f19915f;

    /* renamed from: g */
    final AtomicReference<RunnableC4721a> f19916g;

    /* renamed from: m */
    private static final TimeUnit f19913m = TimeUnit.SECONDS;

    /* renamed from: k */
    private static final String f19911k = "rx2.io-keep-alive-time";

    /* renamed from: l */
    private static final long f19912l = Long.getLong(f19911k, 60).longValue();

    /* renamed from: e */
    static final C4723c f19907e = new C4723c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));

    static {
        f19907e.dispose();
        int max = Math.max(1, Math.min(10, Integer.getInteger(f19914n, 5).intValue()));
        f19904b = new RxThreadFactory(f19909i, max);
        f19905c = new RxThreadFactory(f19910j, max);
        f19908h = new RunnableC4721a(0L, null, f19904b);
        f19908h.m9502d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: IoScheduler.java */
    /* renamed from: z1.bqs$a */
    /* loaded from: classes3.dex */
    public static final class RunnableC4721a implements Runnable {

        /* renamed from: a */
        final CompositeDisposable f19917a;

        /* renamed from: b */
        private final long f19918b;

        /* renamed from: c */
        private final ConcurrentLinkedQueue<C4723c> f19919c;

        /* renamed from: d */
        private final ScheduledExecutorService f19920d;

        /* renamed from: e */
        private final Future<?> f19921e;

        /* renamed from: f */
        private final ThreadFactory f19922f;

        RunnableC4721a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFuture;
            this.f19918b = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f19919c = new ConcurrentLinkedQueue<>();
            this.f19917a = new CompositeDisposable();
            this.f19922f = threadFactory;
            ScheduledExecutorService scheduledExecutorService = null;
            if (timeUnit != null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, IoScheduler.f19905c);
                long j2 = this.f19918b;
                scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(this, j2, j2, TimeUnit.NANOSECONDS);
            } else {
                scheduledFuture = null;
            }
            this.f19920d = scheduledExecutorService;
            this.f19921e = scheduledFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            m9504b();
        }

        /* renamed from: a */
        C4723c m9506a() {
            if (this.f19917a.isDisposed()) {
                return IoScheduler.f19907e;
            }
            while (!this.f19919c.isEmpty()) {
                C4723c poll = this.f19919c.poll();
                if (poll != null) {
                    return poll;
                }
            }
            C4723c cVar = new C4723c(this.f19922f);
            this.f19917a.mo9939a(cVar);
            return cVar;
        }

        /* renamed from: a */
        void m9505a(C4723c cVar) {
            cVar.m9500a(m9503c() + this.f19918b);
            this.f19919c.offer(cVar);
        }

        /* renamed from: b */
        void m9504b() {
            if (!this.f19919c.isEmpty()) {
                long c = m9503c();
                Iterator<C4723c> it = this.f19919c.iterator();
                while (it.hasNext()) {
                    C4723c next = it.next();
                    if (next.m9501a() > c) {
                        return;
                    }
                    if (this.f19919c.remove(next)) {
                        this.f19917a.mo9937b(next);
                    }
                }
            }
        }

        /* renamed from: c */
        long m9503c() {
            return System.nanoTime();
        }

        /* renamed from: d */
        void m9502d() {
            this.f19917a.dispose();
            Future<?> future = this.f19921e;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f19920d;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }
    }

    public IoScheduler() {
        this(f19904b);
    }

    public IoScheduler(ThreadFactory threadFactory) {
        this.f19915f = threadFactory;
        this.f19916g = new AtomicReference<>(f19908h);
        mo9483c();
    }

    @Override // p110z1.Scheduler
    /* renamed from: c */
    public void mo9483c() {
        RunnableC4721a aVar = new RunnableC4721a(f19912l, f19913m, this.f19915f);
        if (!this.f19916g.compareAndSet(f19908h, aVar)) {
            aVar.m9502d();
        }
    }

    @Override // p110z1.Scheduler
    /* renamed from: d */
    public void mo9482d() {
        RunnableC4721a aVar;
        RunnableC4721a aVar2;
        do {
            aVar = this.f19916g.get();
            aVar2 = f19908h;
            if (aVar == aVar2) {
                return;
            }
        } while (!this.f19916g.compareAndSet(aVar, aVar2));
        aVar.m9502d();
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new C4722b(this.f19916g.get());
    }

    /* renamed from: e */
    public int m9507e() {
        return this.f19916g.get().f19917a.m9996b();
    }

    /* compiled from: IoScheduler.java */
    /* renamed from: z1.bqs$b */
    /* loaded from: classes3.dex */
    static final class C4722b extends Scheduler.AbstractC3881c {

        /* renamed from: a */
        final AtomicBoolean f19923a = new AtomicBoolean();

        /* renamed from: b */
        private final CompositeDisposable f19924b = new CompositeDisposable();

        /* renamed from: c */
        private final RunnableC4721a f19925c;

        /* renamed from: d */
        private final C4723c f19926d;

        C4722b(RunnableC4721a aVar) {
            this.f19925c = aVar;
            this.f19926d = aVar.m9506a();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.f19923a.compareAndSet(false, true)) {
                this.f19924b.dispose();
                this.f19925c.m9505a(this.f19926d);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19923a.get();
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            if (this.f19924b.isDisposed()) {
                return EmptyDisposable.INSTANCE;
            }
            return this.f19926d.m9499a(runnable, j, timeUnit, this.f19924b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: IoScheduler.java */
    /* renamed from: z1.bqs$c */
    /* loaded from: classes3.dex */
    public static final class C4723c extends NewThreadWorker {

        /* renamed from: b */
        private long f19927b = 0;

        C4723c(ThreadFactory threadFactory) {
            super(threadFactory);
        }

        /* renamed from: a */
        public long m9501a() {
            return this.f19927b;
        }

        /* renamed from: a */
        public void m9500a(long j) {
            this.f19927b = j;
        }
    }
}
