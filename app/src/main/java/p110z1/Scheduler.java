package p110z1;

import java.util.concurrent.TimeUnit;

/* renamed from: z1.ast */
/* loaded from: classes3.dex */
public abstract class Scheduler {

    /* renamed from: a */
    static final long f17463a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    @AbstractC3889atm
    /* renamed from: b */
    public abstract AbstractC3881c mo9034b();

    /* renamed from: c */
    public void mo9483c() {
    }

    /* renamed from: d */
    public void mo9482d() {
    }

    /* renamed from: a */
    public static long m10161a() {
        return f17463a;
    }

    /* renamed from: a */
    public long mo9035a(@AbstractC3889atm TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9481a(@AbstractC3889atm Runnable runnable) {
        return mo9480a(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9480a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
        AbstractC3881c b = mo9034b();
        RunnableC3879a aVar = new RunnableC3879a(RxJavaPlugins.m9213a(runnable), b);
        b.mo9030a(aVar, j, timeUnit);
        return aVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9485a(@AbstractC3889atm Runnable runnable, long j, long j2, @AbstractC3889atm TimeUnit timeUnit) {
        AbstractC3881c b = mo9034b();
        RunnableC3880b bVar = new RunnableC3880b(RxJavaPlugins.m9213a(runnable), b);
        Disposable a = b.mo9511a(bVar, j, j2, timeUnit);
        return a == EmptyDisposable.INSTANCE ? a : bVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public <S extends Scheduler & Disposable> S m10160a(@AbstractC3889atm Function<Flowable<Flowable<Completable>>, Completable> aunVar) {
        return new SchedulerWhen(aunVar, this);
    }

    /* compiled from: Scheduler.java */
    /* renamed from: z1.ast$c */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC3881c implements Disposable {
        @AbstractC3889atm
        /* renamed from: a */
        public abstract Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit);

        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
            return mo9030a(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9511a(@AbstractC3889atm Runnable runnable, long j, long j2, @AbstractC3889atm TimeUnit timeUnit) {
            SequentialDisposable avfVar = new SequentialDisposable();
            SequentialDisposable avfVar2 = new SequentialDisposable(avfVar);
            Runnable a = RxJavaPlugins.m9213a(runnable);
            long nanos = timeUnit.toNanos(j2);
            long a2 = mo9029a(TimeUnit.NANOSECONDS);
            Disposable a3 = mo9030a(new RunnableC3882a(a2 + timeUnit.toNanos(j), a, a2, avfVar2, nanos), j, timeUnit);
            if (a3 == EmptyDisposable.INSTANCE) {
                return a3;
            }
            avfVar.replace(a3);
            return avfVar2;
        }

        /* renamed from: a */
        public long mo9029a(@AbstractC3889atm TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: Scheduler.java */
        /* renamed from: z1.ast$c$a */
        /* loaded from: classes3.dex */
        public final class RunnableC3882a implements Runnable, SchedulerRunnableIntrospection {
            @AbstractC3889atm

            /* renamed from: a */
            final Runnable f17470a;
            @AbstractC3889atm

            /* renamed from: b */
            final SequentialDisposable f17471b;

            /* renamed from: c */
            final long f17472c;

            /* renamed from: d */
            long f17473d;

            /* renamed from: e */
            long f17474e;

            /* renamed from: f */
            long f17475f;

            RunnableC3882a(long j, Runnable runnable, @AbstractC3889atm long j2, SequentialDisposable avfVar, @AbstractC3889atm long j3) {
                this.f17470a = runnable;
                this.f17471b = avfVar;
                this.f17472c = j3;
                this.f17474e = j2;
                this.f17475f = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j;
                this.f17470a.run();
                if (!this.f17471b.isDisposed()) {
                    long a = AbstractC3881c.this.mo9029a(TimeUnit.NANOSECONDS);
                    long j2 = this.f17474e;
                    if (Scheduler.f17463a + a < j2 || a >= j2 + this.f17472c + Scheduler.f17463a) {
                        long j3 = this.f17472c;
                        long j4 = a + j3;
                        long j5 = this.f17473d + 1;
                        this.f17473d = j5;
                        this.f17475f = j4 - (j3 * j5);
                        j = j4;
                    } else {
                        long j6 = this.f17475f;
                        long j7 = this.f17473d + 1;
                        this.f17473d = j7;
                        j = j6 + (j7 * this.f17472c);
                    }
                    this.f17474e = a;
                    this.f17471b.replace(AbstractC3881c.this.mo9030a(this, j - a, TimeUnit.NANOSECONDS));
                }
            }

            @Override // p110z1.SchedulerRunnableIntrospection
            public Runnable getWrappedRunnable() {
                return this.f17470a;
            }
        }
    }

    /* compiled from: Scheduler.java */
    /* renamed from: z1.ast$b */
    /* loaded from: classes3.dex */
    static final class RunnableC3880b implements Runnable, Disposable, SchedulerRunnableIntrospection {
        @AbstractC3889atm

        /* renamed from: a */
        final Runnable f17467a;
        @AbstractC3889atm

        /* renamed from: b */
        final AbstractC3881c f17468b;

        /* renamed from: c */
        volatile boolean f17469c;

        RunnableC3880b(@AbstractC3889atm Runnable runnable, @AbstractC3889atm AbstractC3881c cVar) {
            this.f17467a = runnable;
            this.f17468b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f17469c) {
                try {
                    this.f17467a.run();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17468b.dispose();
                    throw ExceptionHelper.m9432a(th);
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17469c = true;
            this.f17468b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17469c;
        }

        @Override // p110z1.SchedulerRunnableIntrospection
        public Runnable getWrappedRunnable() {
            return this.f17467a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Scheduler.java */
    /* renamed from: z1.ast$a */
    /* loaded from: classes3.dex */
    public static final class RunnableC3879a implements Runnable, Disposable, SchedulerRunnableIntrospection {
        @AbstractC3889atm

        /* renamed from: a */
        final Runnable f17464a;
        @AbstractC3889atm

        /* renamed from: b */
        final AbstractC3881c f17465b;
        @atn

        /* renamed from: c */
        Thread f17466c;

        RunnableC3879a(@AbstractC3889atm Runnable runnable, @AbstractC3889atm AbstractC3881c cVar) {
            this.f17464a = runnable;
            this.f17465b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f17466c = Thread.currentThread();
            try {
                this.f17464a.run();
            } finally {
                dispose();
                this.f17466c = null;
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.f17466c == Thread.currentThread()) {
                AbstractC3881c cVar = this.f17465b;
                if (cVar instanceof NewThreadWorker) {
                    ((NewThreadWorker) cVar).m9498b();
                    return;
                }
            }
            this.f17465b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17465b.isDisposed();
        }

        @Override // p110z1.SchedulerRunnableIntrospection
        public Runnable getWrappedRunnable() {
            return this.f17464a;
        }
    }
}
