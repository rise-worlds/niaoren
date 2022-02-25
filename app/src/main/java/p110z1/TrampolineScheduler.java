package p110z1;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p110z1.Scheduler;

/* renamed from: z1.bre */
/* loaded from: classes3.dex */
public final class TrampolineScheduler extends Scheduler {

    /* renamed from: b */
    private static final TrampolineScheduler f19972b = new TrampolineScheduler();

    /* renamed from: e */
    public static TrampolineScheduler m9479e() {
        return f19972b;
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new C4740c();
    }

    TrampolineScheduler() {
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9481a(@AbstractC3889atm Runnable runnable) {
        RxJavaPlugins.m9213a(runnable).run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9480a(@AbstractC3889atm Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            RxJavaPlugins.m9213a(runnable).run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            RxJavaPlugins.m9212a(e);
        }
        return EmptyDisposable.INSTANCE;
    }

    /* compiled from: TrampolineScheduler.java */
    /* renamed from: z1.bre$c */
    /* loaded from: classes3.dex */
    static final class C4740c extends Scheduler.AbstractC3881c implements Disposable {

        /* renamed from: c */
        volatile boolean f19982c;

        /* renamed from: a */
        final PriorityBlockingQueue<C4739b> f19980a = new PriorityBlockingQueue<>();

        /* renamed from: d */
        private final AtomicInteger f19983d = new AtomicInteger();

        /* renamed from: b */
        final AtomicInteger f19981b = new AtomicInteger();

        C4740c() {
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
            return m9477a(runnable, mo9029a(TimeUnit.MILLISECONDS));
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            long a = mo9029a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return m9477a(new RunnableC4738a(runnable, this, a), a);
        }

        /* renamed from: a */
        Disposable m9477a(Runnable runnable, long j) {
            if (this.f19982c) {
                return EmptyDisposable.INSTANCE;
            }
            C4739b bVar = new C4739b(runnable, Long.valueOf(j), this.f19981b.incrementAndGet());
            this.f19980a.add(bVar);
            if (this.f19983d.getAndIncrement() != 0) {
                return Disposables.m9994a(new RunnableC4741a(bVar));
            }
            int i = 1;
            while (!this.f19982c) {
                C4739b poll = this.f19980a.poll();
                if (poll == null) {
                    i = this.f19983d.addAndGet(-i);
                    if (i == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!poll.f19979d) {
                    poll.f19976a.run();
                }
            }
            this.f19980a.clear();
            return EmptyDisposable.INSTANCE;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19982c = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19982c;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: TrampolineScheduler.java */
        /* renamed from: z1.bre$c$a */
        /* loaded from: classes3.dex */
        public final class RunnableC4741a implements Runnable {

            /* renamed from: a */
            final C4739b f19984a;

            RunnableC4741a(C4739b bVar) {
                this.f19984a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f19984a.f19979d = true;
                C4740c.this.f19980a.remove(this.f19984a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TrampolineScheduler.java */
    /* renamed from: z1.bre$b */
    /* loaded from: classes3.dex */
    public static final class C4739b implements Comparable<C4739b> {

        /* renamed from: a */
        final Runnable f19976a;

        /* renamed from: b */
        final long f19977b;

        /* renamed from: c */
        final int f19978c;

        /* renamed from: d */
        volatile boolean f19979d;

        C4739b(Runnable runnable, Long l, int i) {
            this.f19976a = runnable;
            this.f19977b = l.longValue();
            this.f19978c = i;
        }

        /* renamed from: a */
        public int compareTo(C4739b bVar) {
            int a = ObjectHelper.m9877a(this.f19977b, bVar.f19977b);
            return a == 0 ? ObjectHelper.m9879a(this.f19978c, bVar.f19978c) : a;
        }
    }

    /* compiled from: TrampolineScheduler.java */
    /* renamed from: z1.bre$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4738a implements Runnable {

        /* renamed from: a */
        private final Runnable f19973a;

        /* renamed from: b */
        private final C4740c f19974b;

        /* renamed from: c */
        private final long f19975c;

        RunnableC4738a(Runnable runnable, C4740c cVar, long j) {
            this.f19973a = runnable;
            this.f19974b = cVar;
            this.f19975c = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f19974b.f19982c) {
                long a = this.f19974b.mo9029a(TimeUnit.MILLISECONDS);
                long j = this.f19975c;
                if (j > a) {
                    try {
                        Thread.sleep(j - a);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        RxJavaPlugins.m9212a(e);
                        return;
                    }
                }
                if (!this.f19974b.f19982c) {
                    this.f19973a.run();
                }
            }
        }
    }
}
