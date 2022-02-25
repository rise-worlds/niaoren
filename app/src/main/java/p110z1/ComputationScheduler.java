package p110z1;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;
import p110z1.SchedulerMultiWorkerSupport;

/* renamed from: z1.bqn */
/* loaded from: classes3.dex */
public final class ComputationScheduler extends Scheduler implements SchedulerMultiWorkerSupport {

    /* renamed from: g */
    final ThreadFactory f19870g;

    /* renamed from: h */
    final AtomicReference<C4712b> f19871h;

    /* renamed from: d */
    static final String f19865d = "rx2.computation-threads";

    /* renamed from: e */
    static final int f19866e = m9514a(Runtime.getRuntime().availableProcessors(), Integer.getInteger(f19865d, 0).intValue());

    /* renamed from: f */
    static final C4713c f19867f = new C4713c(new RxThreadFactory("RxComputationShutdown"));

    /* renamed from: i */
    private static final String f19868i = "RxComputationThreadPool";

    /* renamed from: j */
    private static final String f19869j = "rx2.computation-priority";

    /* renamed from: c */
    static final RxThreadFactory f19864c = new RxThreadFactory(f19868i, Math.max(1, Math.min(10, Integer.getInteger(f19869j, 5).intValue())), true);

    /* renamed from: b */
    static final C4712b f19863b = new C4712b(0, f19864c);

    /* renamed from: a */
    static int m9514a(int i, int i2) {
        return (i2 <= 0 || i2 > i) ? i : i2;
    }

    static {
        f19867f.dispose();
        f19863b.m9512b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ComputationScheduler.java */
    /* renamed from: z1.bqn$b */
    /* loaded from: classes3.dex */
    public static final class C4712b implements SchedulerMultiWorkerSupport {

        /* renamed from: a */
        final int f19877a;

        /* renamed from: b */
        final C4713c[] f19878b;

        /* renamed from: c */
        long f19879c;

        C4712b(int i, ThreadFactory threadFactory) {
            this.f19877a = i;
            this.f19878b = new C4713c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.f19878b[i2] = new C4713c(threadFactory);
            }
        }

        /* renamed from: a */
        public C4713c m9513a() {
            int i = this.f19877a;
            if (i == 0) {
                return ComputationScheduler.f19867f;
            }
            C4713c[] cVarArr = this.f19878b;
            long j = this.f19879c;
            this.f19879c = 1 + j;
            return cVarArr[(int) (j % i)];
        }

        /* renamed from: b */
        public void m9512b() {
            for (C4713c cVar : this.f19878b) {
                cVar.dispose();
            }
        }

        @Override // p110z1.SchedulerMultiWorkerSupport
        /* renamed from: a */
        public void mo9494a(int i, SchedulerMultiWorkerSupport.AbstractC4726a aVar) {
            int i2 = this.f19877a;
            if (i2 == 0) {
                for (int i3 = 0; i3 < i; i3++) {
                    aVar.mo9493a(i3, ComputationScheduler.f19867f);
                }
                return;
            }
            int i4 = ((int) this.f19879c) % i2;
            for (int i5 = 0; i5 < i; i5++) {
                aVar.mo9493a(i5, new C4711a(this.f19878b[i4]));
                i4++;
                if (i4 == i2) {
                    i4 = 0;
                }
            }
            this.f19879c = i4;
        }
    }

    public ComputationScheduler() {
        this(f19864c);
    }

    public ComputationScheduler(ThreadFactory threadFactory) {
        this.f19870g = threadFactory;
        this.f19871h = new AtomicReference<>(f19863b);
        mo9483c();
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new C4711a(this.f19871h.get().m9513a());
    }

    @Override // p110z1.SchedulerMultiWorkerSupport
    /* renamed from: a */
    public void mo9494a(int i, SchedulerMultiWorkerSupport.AbstractC4726a aVar) {
        ObjectHelper.m9878a(i, "number > 0 required");
        this.f19871h.get().mo9494a(i, aVar);
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9480a(@AbstractC3889atm Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f19871h.get().m9513a().m9496b(runnable, j, timeUnit);
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9485a(@AbstractC3889atm Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.f19871h.get().m9513a().m9497b(runnable, j, j2, timeUnit);
    }

    @Override // p110z1.Scheduler
    /* renamed from: c */
    public void mo9483c() {
        C4712b bVar = new C4712b(f19866e, this.f19870g);
        if (!this.f19871h.compareAndSet(f19863b, bVar)) {
            bVar.m9512b();
        }
    }

    @Override // p110z1.Scheduler
    /* renamed from: d */
    public void mo9482d() {
        C4712b bVar;
        C4712b bVar2;
        do {
            bVar = this.f19871h.get();
            bVar2 = f19863b;
            if (bVar == bVar2) {
                return;
            }
        } while (!this.f19871h.compareAndSet(bVar, bVar2));
        bVar.m9512b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ComputationScheduler.java */
    /* renamed from: z1.bqn$a */
    /* loaded from: classes3.dex */
    public static final class C4711a extends Scheduler.AbstractC3881c {

        /* renamed from: a */
        volatile boolean f19872a;

        /* renamed from: b */
        private final ListCompositeDisposable f19873b = new ListCompositeDisposable();

        /* renamed from: c */
        private final CompositeDisposable f19874c = new CompositeDisposable();

        /* renamed from: d */
        private final ListCompositeDisposable f19875d = new ListCompositeDisposable();

        /* renamed from: e */
        private final C4713c f19876e;

        C4711a(C4713c cVar) {
            this.f19876e = cVar;
            this.f19875d.mo9939a(this.f19873b);
            this.f19875d.mo9939a(this.f19874c);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.f19872a) {
                this.f19872a = true;
                this.f19875d.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19872a;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
            if (this.f19872a) {
                return EmptyDisposable.INSTANCE;
            }
            return this.f19876e.m9499a(runnable, 0L, TimeUnit.MILLISECONDS, this.f19873b);
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            if (this.f19872a) {
                return EmptyDisposable.INSTANCE;
            }
            return this.f19876e.m9499a(runnable, j, timeUnit, this.f19874c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ComputationScheduler.java */
    /* renamed from: z1.bqn$c */
    /* loaded from: classes3.dex */
    public static final class C4713c extends NewThreadWorker {
        C4713c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }
}
