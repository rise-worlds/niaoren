package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bqp */
/* loaded from: classes3.dex */
public final class ExecutorScheduler extends Scheduler {

    /* renamed from: d */
    static final Scheduler f19881d = Schedulers.m9044e();

    /* renamed from: b */
    final boolean f19882b;
    @AbstractC3889atm

    /* renamed from: c */
    final Executor f19883c;

    public ExecutorScheduler(@AbstractC3889atm Executor executor, boolean z) {
        this.f19883c = executor;
        this.f19882b = z;
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new RunnableC4716c(this.f19883c, this.f19882b);
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9481a(@AbstractC3889atm Runnable runnable) {
        Runnable a = RxJavaPlugins.m9213a(runnable);
        try {
            if (this.f19883c instanceof ExecutorService) {
                ScheduledDirectTask bqyVar = new ScheduledDirectTask(a);
                bqyVar.setFuture(((ExecutorService) this.f19883c).submit(bqyVar));
                return bqyVar;
            } else if (this.f19882b) {
                RunnableC4716c.RunnableC4718b bVar = new RunnableC4716c.RunnableC4718b(a, null);
                this.f19883c.execute(bVar);
                return bVar;
            } else {
                RunnableC4716c.RunnableC4717a aVar = new RunnableC4716c.RunnableC4717a(a);
                this.f19883c.execute(aVar);
                return aVar;
            }
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.m9212a(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9480a(@AbstractC3889atm Runnable runnable, long j, TimeUnit timeUnit) {
        Runnable a = RxJavaPlugins.m9213a(runnable);
        if (this.f19883c instanceof ScheduledExecutorService) {
            try {
                ScheduledDirectTask bqyVar = new ScheduledDirectTask(a);
                bqyVar.setFuture(((ScheduledExecutorService) this.f19883c).schedule(bqyVar, j, timeUnit));
                return bqyVar;
            } catch (RejectedExecutionException e) {
                RxJavaPlugins.m9212a(e);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            RunnableC4715b bVar = new RunnableC4715b(a);
            bVar.timed.replace(f19881d.mo9480a(new RunnableC4714a(bVar), j, timeUnit));
            return bVar;
        }
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9485a(@AbstractC3889atm Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        if (!(this.f19883c instanceof ScheduledExecutorService)) {
            return super.mo9485a(runnable, j, j2, timeUnit);
        }
        try {
            ScheduledDirectPeriodicTask bqxVar = new ScheduledDirectPeriodicTask(RxJavaPlugins.m9213a(runnable));
            bqxVar.setFuture(((ScheduledExecutorService) this.f19883c).scheduleAtFixedRate(bqxVar, j, j2, timeUnit));
            return bqxVar;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.m9212a(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    /* compiled from: ExecutorScheduler.java */
    /* renamed from: z1.bqp$c */
    /* loaded from: classes3.dex */
    public static final class RunnableC4716c extends Scheduler.AbstractC3881c implements Runnable {

        /* renamed from: a */
        final boolean f19886a;

        /* renamed from: b */
        final Executor f19887b;

        /* renamed from: d */
        volatile boolean f19889d;

        /* renamed from: e */
        final AtomicInteger f19890e = new AtomicInteger();

        /* renamed from: f */
        final CompositeDisposable f19891f = new CompositeDisposable();

        /* renamed from: c */
        final MpscLinkedQueue<Runnable> f19888c = new MpscLinkedQueue<>();

        public RunnableC4716c(Executor executor, boolean z) {
            this.f19887b = executor;
            this.f19886a = z;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
            Disposable atrVar;
            if (this.f19889d) {
                return EmptyDisposable.INSTANCE;
            }
            Runnable a = RxJavaPlugins.m9213a(runnable);
            if (this.f19886a) {
                atrVar = new RunnableC4718b(a, this.f19891f);
                this.f19891f.mo9939a(atrVar);
            } else {
                atrVar = new RunnableC4717a(a);
            }
            this.f19888c.offer(atrVar);
            if (this.f19890e.getAndIncrement() == 0) {
                try {
                    this.f19887b.execute(this);
                } catch (RejectedExecutionException e) {
                    this.f19889d = true;
                    this.f19888c.clear();
                    RxJavaPlugins.m9212a(e);
                    return EmptyDisposable.INSTANCE;
                }
            }
            return atrVar;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            if (j <= 0) {
                return mo9031a(runnable);
            }
            if (this.f19889d) {
                return EmptyDisposable.INSTANCE;
            }
            SequentialDisposable avfVar = new SequentialDisposable();
            SequentialDisposable avfVar2 = new SequentialDisposable(avfVar);
            ScheduledRunnable bqzVar = new ScheduledRunnable(new RunnableC4719c(avfVar2, RxJavaPlugins.m9213a(runnable)), this.f19891f);
            this.f19891f.mo9939a(bqzVar);
            Executor executor = this.f19887b;
            if (executor instanceof ScheduledExecutorService) {
                try {
                    bqzVar.setFuture(((ScheduledExecutorService) executor).schedule((Callable) bqzVar, j, timeUnit));
                } catch (RejectedExecutionException e) {
                    this.f19889d = true;
                    RxJavaPlugins.m9212a(e);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                bqzVar.setFuture(new DisposeOnCancel(ExecutorScheduler.f19881d.mo9480a(bqzVar, j, timeUnit)));
            }
            avfVar.replace(bqzVar);
            return avfVar2;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.f19889d) {
                this.f19889d = true;
                this.f19891f.dispose();
                if (this.f19890e.getAndIncrement() == 0) {
                    this.f19888c.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19889d;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
            if (r3.f19889d == false) goto L_0x001b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0017, code lost:
            r0.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x001b, code lost:
            r1 = r3.f19890e.addAndGet(-r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0022, code lost:
            if (r1 != 0) goto L_0x0003;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0024, code lost:
            return;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r3 = this;
                z1.bqj<java.lang.Runnable> r0 = r3.f19888c
                r1 = 1
            L_0x0003:
                boolean r2 = r3.f19889d
                if (r2 == 0) goto L_0x000b
                r0.clear()
                return
            L_0x000b:
                java.lang.Object r2 = r0.poll()
                java.lang.Runnable r2 = (java.lang.Runnable) r2
                if (r2 != 0) goto L_0x0025
                boolean r2 = r3.f19889d
                if (r2 == 0) goto L_0x001b
                r0.clear()
                return
            L_0x001b:
                java.util.concurrent.atomic.AtomicInteger r2 = r3.f19890e
                int r1 = -r1
                int r1 = r2.addAndGet(r1)
                if (r1 != 0) goto L_0x0003
                return
            L_0x0025:
                r2.run()
                boolean r2 = r3.f19889d
                if (r2 == 0) goto L_0x000b
                r0.clear()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ExecutorScheduler.RunnableC4716c.run():void");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ExecutorScheduler.java */
        /* renamed from: z1.bqp$c$a */
        /* loaded from: classes3.dex */
        public static final class RunnableC4717a extends AtomicBoolean implements Runnable, Disposable {
            private static final long serialVersionUID = -2421395018820541164L;
            final Runnable actual;

            RunnableC4717a(Runnable runnable) {
                this.actual = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!get()) {
                    try {
                        this.actual.run();
                    } finally {
                        lazySet(true);
                    }
                }
            }

            @Override // p110z1.Disposable
            public void dispose() {
                lazySet(true);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return get();
            }
        }

        /* compiled from: ExecutorScheduler.java */
        /* renamed from: z1.bqp$c$c */
        /* loaded from: classes3.dex */
        final class RunnableC4719c implements Runnable {

            /* renamed from: b */
            private final SequentialDisposable f19893b;

            /* renamed from: c */
            private final Runnable f19894c;

            RunnableC4719c(SequentialDisposable avfVar, Runnable runnable) {
                this.f19893b = avfVar;
                this.f19894c = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f19893b.replace(RunnableC4716c.this.mo9031a(this.f19894c));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ExecutorScheduler.java */
        /* renamed from: z1.bqp$c$b */
        /* loaded from: classes3.dex */
        public static final class RunnableC4718b extends AtomicInteger implements Runnable, Disposable {
            static final int FINISHED = 2;
            static final int INTERRUPTED = 4;
            static final int INTERRUPTING = 3;
            static final int READY = 0;
            static final int RUNNING = 1;
            private static final long serialVersionUID = -3603436687413320876L;
            final Runnable run;
            final DisposableContainer tasks;
            volatile Thread thread;

            RunnableC4718b(Runnable runnable, DisposableContainer avaVar) {
                this.run = runnable;
                this.tasks = avaVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (get() == 0) {
                    this.thread = Thread.currentThread();
                    if (compareAndSet(0, 1)) {
                        try {
                            this.run.run();
                            this.thread = null;
                            if (compareAndSet(1, 2)) {
                                cleanup();
                                return;
                            }
                            while (get() == 3) {
                                Thread.yield();
                            }
                            Thread.interrupted();
                        } catch (Throwable th) {
                            this.thread = null;
                            if (!compareAndSet(1, 2)) {
                                while (get() == 3) {
                                    Thread.yield();
                                }
                                Thread.interrupted();
                            } else {
                                cleanup();
                            }
                            throw th;
                        }
                    } else {
                        this.thread = null;
                    }
                }
            }

            @Override // p110z1.Disposable
            public void dispose() {
                while (true) {
                    int i = get();
                    if (i < 2) {
                        if (i == 0) {
                            if (compareAndSet(0, 4)) {
                                cleanup();
                                return;
                            }
                        } else if (compareAndSet(1, 3)) {
                            Thread thread = this.thread;
                            if (thread != null) {
                                thread.interrupt();
                                this.thread = null;
                            }
                            set(4);
                            cleanup();
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }

            void cleanup() {
                DisposableContainer avaVar = this.tasks;
                if (avaVar != null) {
                    avaVar.mo9936c(this);
                }
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return get() >= 2;
            }
        }
    }

    /* compiled from: ExecutorScheduler.java */
    /* renamed from: z1.bqp$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4715b extends AtomicReference<Runnable> implements Runnable, Disposable, SchedulerRunnableIntrospection {
        private static final long serialVersionUID = -4101336210206799084L;
        final SequentialDisposable timed = new SequentialDisposable();
        final SequentialDisposable direct = new SequentialDisposable();

        RunnableC4715b(Runnable runnable) {
            super(runnable);
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = get();
            if (runnable != null) {
                try {
                    runnable.run();
                } finally {
                    lazySet(null);
                    this.timed.lazySet(DisposableHelper.DISPOSED);
                    this.direct.lazySet(DisposableHelper.DISPOSED);
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == null;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (getAndSet(null) != null) {
                this.timed.dispose();
                this.direct.dispose();
            }
        }

        @Override // p110z1.SchedulerRunnableIntrospection
        public Runnable getWrappedRunnable() {
            Runnable runnable = get();
            return runnable != null ? runnable : Functions.f17556b;
        }
    }

    /* compiled from: ExecutorScheduler.java */
    /* renamed from: z1.bqp$a */
    /* loaded from: classes3.dex */
    final class RunnableC4714a implements Runnable {

        /* renamed from: b */
        private final RunnableC4715b f19885b;

        RunnableC4714a(RunnableC4715b bVar) {
            this.f19885b = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f19885b.direct.replace(ExecutorScheduler.this.mo9481a(this.f19885b));
        }
    }
}
