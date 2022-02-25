package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: z1.buo */
/* loaded from: classes3.dex */
public final class Schedulers {
    @AbstractC3889atm

    /* renamed from: a */
    static final Scheduler f20244a = RxJavaPlugins.m9172d(new CallableC4779h());
    @AbstractC3889atm

    /* renamed from: b */
    static final Scheduler f20245b = RxJavaPlugins.m9211a(new CallableC4773b());
    @AbstractC3889atm

    /* renamed from: c */
    static final Scheduler f20246c = RxJavaPlugins.m9185b(new CallableC4774c());
    @AbstractC3889atm

    /* renamed from: d */
    static final Scheduler f20247d = TrampolineScheduler.m9479e();
    @AbstractC3889atm

    /* renamed from: e */
    static final Scheduler f20248e = RxJavaPlugins.m9178c(new CallableC4777f());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$g */
    /* loaded from: classes3.dex */
    public static final class C4778g {

        /* renamed from: a */
        static final Scheduler f20252a = new SingleScheduler();

        C4778g() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$a */
    /* loaded from: classes3.dex */
    public static final class C4772a {

        /* renamed from: a */
        static final Scheduler f20249a = new ComputationScheduler();

        C4772a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$d */
    /* loaded from: classes3.dex */
    public static final class C4775d {

        /* renamed from: a */
        static final Scheduler f20250a = new IoScheduler();

        C4775d() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$e */
    /* loaded from: classes3.dex */
    public static final class C4776e {

        /* renamed from: a */
        static final Scheduler f20251a = new NewThreadScheduler();

        C4776e() {
        }
    }

    private Schedulers() {
        throw new IllegalStateException("No instances!");
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Scheduler m9050a() {
        return RxJavaPlugins.m9201a(f20245b);
    }

    @AbstractC3889atm
    /* renamed from: b */
    public static Scheduler m9047b() {
        return RxJavaPlugins.m9183b(f20246c);
    }

    @AbstractC3889atm
    /* renamed from: c */
    public static Scheduler m9046c() {
        return f20247d;
    }

    @AbstractC3889atm
    /* renamed from: d */
    public static Scheduler m9045d() {
        return RxJavaPlugins.m9176c(f20248e);
    }

    @AbstractC3889atm
    /* renamed from: e */
    public static Scheduler m9044e() {
        return RxJavaPlugins.m9170d(f20244a);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Scheduler m9049a(@AbstractC3889atm Executor executor) {
        return new ExecutorScheduler(executor, false);
    }

    @Experimental
    @AbstractC3889atm
    /* renamed from: a */
    public static Scheduler m9048a(@AbstractC3889atm Executor executor, boolean z) {
        return new ExecutorScheduler(executor, z);
    }

    /* renamed from: f */
    public static void m9043f() {
        m9050a().mo9482d();
        m9047b().mo9482d();
        m9045d().mo9482d();
        m9044e().mo9482d();
        m9046c().mo9482d();
        SchedulerPoolFactory.m9488b();
    }

    /* renamed from: g */
    public static void m9042g() {
        m9050a().mo9483c();
        m9047b().mo9483c();
        m9045d().mo9483c();
        m9044e().mo9483c();
        m9046c().mo9483c();
        SchedulerPoolFactory.m9492a();
    }

    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$c */
    /* loaded from: classes3.dex */
    static final class CallableC4774c implements Callable<Scheduler> {
        CallableC4774c() {
        }

        /* renamed from: a */
        public Scheduler call() throws Exception {
            return C4775d.f20250a;
        }
    }

    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$f */
    /* loaded from: classes3.dex */
    static final class CallableC4777f implements Callable<Scheduler> {
        CallableC4777f() {
        }

        /* renamed from: a */
        public Scheduler call() throws Exception {
            return C4776e.f20251a;
        }
    }

    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$h */
    /* loaded from: classes3.dex */
    static final class CallableC4779h implements Callable<Scheduler> {
        CallableC4779h() {
        }

        /* renamed from: a */
        public Scheduler call() throws Exception {
            return C4778g.f20252a;
        }
    }

    /* compiled from: Schedulers.java */
    /* renamed from: z1.buo$b */
    /* loaded from: classes3.dex */
    static final class CallableC4773b implements Callable<Scheduler> {
        CallableC4773b() {
        }

        /* renamed from: a */
        public Scheduler call() throws Exception {
            return C4772a.f20249a;
        }
    }
}
