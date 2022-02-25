package p110z1;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import p110z1.Scheduler;

/* renamed from: z1.bup */
/* loaded from: classes3.dex */
public final class TestScheduler extends Scheduler {

    /* renamed from: b */
    final Queue<C4782b> f20253b = new PriorityBlockingQueue(11);

    /* renamed from: c */
    long f20254c;

    /* renamed from: d */
    volatile long f20255d;

    public TestScheduler() {
    }

    public TestScheduler(long j, TimeUnit timeUnit) {
        this.f20255d = timeUnit.toNanos(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TestScheduler.java */
    /* renamed from: z1.bup$b */
    /* loaded from: classes3.dex */
    public static final class C4782b implements Comparable<C4782b> {

        /* renamed from: a */
        final long f20260a;

        /* renamed from: b */
        final Runnable f20261b;

        /* renamed from: c */
        final C4780a f20262c;

        /* renamed from: d */
        final long f20263d;

        C4782b(C4780a aVar, long j, Runnable runnable, long j2) {
            this.f20260a = j;
            this.f20261b = runnable;
            this.f20262c = aVar;
            this.f20263d = j2;
        }

        public String toString() {
            return String.format("TimedRunnable(time = %d, run = %s)", Long.valueOf(this.f20260a), this.f20261b.toString());
        }

        /* renamed from: a */
        public int compareTo(C4782b bVar) {
            long j = this.f20260a;
            long j2 = bVar.f20260a;
            if (j == j2) {
                return ObjectHelper.m9877a(this.f20263d, bVar.f20263d);
            }
            return ObjectHelper.m9877a(j, j2);
        }
    }

    @Override // p110z1.Scheduler
    /* renamed from: a */
    public long mo9035a(@AbstractC3889atm TimeUnit timeUnit) {
        return timeUnit.convert(this.f20255d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: a */
    public void m9036a(long j, TimeUnit timeUnit) {
        m9033b(this.f20255d + timeUnit.toNanos(j), TimeUnit.NANOSECONDS);
    }

    /* renamed from: b */
    public void m9033b(long j, TimeUnit timeUnit) {
        m9037a(timeUnit.toNanos(j));
    }

    /* renamed from: e */
    public void m9032e() {
        m9037a(this.f20255d);
    }

    /* renamed from: a */
    private void m9037a(long j) {
        while (true) {
            C4782b peek = this.f20253b.peek();
            if (peek == null || peek.f20260a > j) {
                break;
            }
            this.f20255d = peek.f20260a == 0 ? this.f20255d : peek.f20260a;
            this.f20253b.remove(peek);
            if (!peek.f20262c.f20256a) {
                peek.f20261b.run();
            }
        }
        this.f20255d = j;
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new C4780a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TestScheduler.java */
    /* renamed from: z1.bup$a */
    /* loaded from: classes3.dex */
    public final class C4780a extends Scheduler.AbstractC3881c {

        /* renamed from: a */
        volatile boolean f20256a;

        C4780a() {
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f20256a = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f20256a;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            if (this.f20256a) {
                return EmptyDisposable.INSTANCE;
            }
            long nanos = TestScheduler.this.f20255d + timeUnit.toNanos(j);
            TestScheduler bupVar = TestScheduler.this;
            long j2 = bupVar.f20254c;
            bupVar.f20254c = 1 + j2;
            C4782b bVar = new C4782b(this, nanos, runnable, j2);
            TestScheduler.this.f20253b.add(bVar);
            return Disposables.m9994a(new RunnableC4781a(bVar));
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
            if (this.f20256a) {
                return EmptyDisposable.INSTANCE;
            }
            TestScheduler bupVar = TestScheduler.this;
            long j = bupVar.f20254c;
            bupVar.f20254c = 1 + j;
            C4782b bVar = new C4782b(this, 0L, runnable, j);
            TestScheduler.this.f20253b.add(bVar);
            return Disposables.m9994a(new RunnableC4781a(bVar));
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        /* renamed from: a */
        public long mo9029a(@AbstractC3889atm TimeUnit timeUnit) {
            return TestScheduler.this.mo9035a(timeUnit);
        }

        /* compiled from: TestScheduler.java */
        /* renamed from: z1.bup$a$a */
        /* loaded from: classes3.dex */
        final class RunnableC4781a implements Runnable {

            /* renamed from: a */
            final C4782b f20258a;

            RunnableC4781a(C4782b bVar) {
                this.f20258a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                TestScheduler.this.f20253b.remove(this.f20258a);
            }
        }
    }
}
