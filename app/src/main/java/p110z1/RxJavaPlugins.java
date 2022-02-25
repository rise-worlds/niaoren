package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

/* renamed from: z1.bue */
/* loaded from: classes3.dex */
public final class RxJavaPlugins {
    @atn

    /* renamed from: a */
    static volatile Consumer<? super Throwable> f20145a;
    @atn

    /* renamed from: b */
    static volatile Function<? super Runnable, ? extends Runnable> f20146b;
    @atn

    /* renamed from: c */
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> f20147c;
    @atn

    /* renamed from: d */
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> f20148d;
    @atn

    /* renamed from: e */
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> f20149e;
    @atn

    /* renamed from: f */
    static volatile Function<? super Callable<Scheduler>, ? extends Scheduler> f20150f;
    @atn

    /* renamed from: g */
    static volatile Function<? super Scheduler, ? extends Scheduler> f20151g;
    @atn

    /* renamed from: h */
    static volatile Function<? super Scheduler, ? extends Scheduler> f20152h;
    @atn

    /* renamed from: i */
    static volatile Function<? super Scheduler, ? extends Scheduler> f20153i;
    @atn

    /* renamed from: j */
    static volatile Function<? super Scheduler, ? extends Scheduler> f20154j;
    @atn

    /* renamed from: k */
    static volatile Function<? super Flowable, ? extends Flowable> f20155k;
    @atn

    /* renamed from: l */
    static volatile Function<? super ConnectableFlowable, ? extends ConnectableFlowable> f20156l;
    @atn

    /* renamed from: m */
    static volatile Function<? super Observable, ? extends Observable> f20157m;
    @atn

    /* renamed from: n */
    static volatile Function<? super ConnectableObservable, ? extends ConnectableObservable> f20158n;
    @atn

    /* renamed from: o */
    static volatile Function<? super Maybe, ? extends Maybe> f20159o;
    @atn

    /* renamed from: p */
    static volatile Function<? super Single, ? extends Single> f20160p;
    @atn

    /* renamed from: q */
    static volatile Function<? super Completable, ? extends Completable> f20161q;
    @atn

    /* renamed from: r */
    static volatile Function<? super ParallelFlowable, ? extends ParallelFlowable> f20162r;
    @atn

    /* renamed from: s */
    static volatile BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> f20163s;
    @atn

    /* renamed from: t */
    static volatile BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> f20164t;
    @atn

    /* renamed from: u */
    static volatile BiFunction<? super Observable, ? super Observer, ? extends Observer> f20165u;
    @atn

    /* renamed from: v */
    static volatile BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> f20166v;
    @atn

    /* renamed from: w */
    static volatile BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> f20167w;
    @atn

    /* renamed from: x */
    static volatile BooleanSupplier f20168x;

    /* renamed from: y */
    static volatile boolean f20169y;

    /* renamed from: z */
    static volatile boolean f20170z;

    /* renamed from: a */
    public static void m9214a() {
        f20169y = true;
    }

    /* renamed from: b */
    public static boolean m9187b() {
        return f20169y;
    }

    /* renamed from: a */
    public static void m9188a(boolean z) {
        if (!f20169y) {
            f20170z = z;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: c */
    public static boolean m9180c() {
        return f20170z;
    }

    @atn
    /* renamed from: d */
    public static Function<? super Scheduler, ? extends Scheduler> m9173d() {
        return f20151g;
    }

    @atn
    /* renamed from: e */
    public static Consumer<? super Throwable> m9167e() {
        return f20145a;
    }

    @atn
    /* renamed from: f */
    public static Function<? super Callable<Scheduler>, ? extends Scheduler> m9163f() {
        return f20147c;
    }

    @atn
    /* renamed from: g */
    public static Function<? super Callable<Scheduler>, ? extends Scheduler> m9161g() {
        return f20149e;
    }

    @atn
    /* renamed from: h */
    public static Function<? super Callable<Scheduler>, ? extends Scheduler> m9159h() {
        return f20150f;
    }

    @atn
    /* renamed from: i */
    public static Function<? super Callable<Scheduler>, ? extends Scheduler> m9157i() {
        return f20148d;
    }

    @atn
    /* renamed from: j */
    public static Function<? super Scheduler, ? extends Scheduler> m9155j() {
        return f20153i;
    }

    @atn
    /* renamed from: k */
    public static Function<? super Scheduler, ? extends Scheduler> m9153k() {
        return f20154j;
    }

    @atn
    /* renamed from: l */
    public static Function<? super Runnable, ? extends Runnable> m9151l() {
        return f20146b;
    }

    @atn
    /* renamed from: m */
    public static Function<? super Scheduler, ? extends Scheduler> m9149m() {
        return f20152h;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Scheduler m9211a(@AbstractC3889atm Callable<Scheduler> callable) {
        ObjectHelper.m9873a(callable, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar = f20147c;
        if (aunVar == null) {
            return m9166e(callable);
        }
        return m9191a(aunVar, callable);
    }

    @AbstractC3889atm
    /* renamed from: b */
    public static Scheduler m9185b(@AbstractC3889atm Callable<Scheduler> callable) {
        ObjectHelper.m9873a(callable, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar = f20149e;
        if (aunVar == null) {
            return m9166e(callable);
        }
        return m9191a(aunVar, callable);
    }

    @AbstractC3889atm
    /* renamed from: c */
    public static Scheduler m9178c(@AbstractC3889atm Callable<Scheduler> callable) {
        ObjectHelper.m9873a(callable, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar = f20150f;
        if (aunVar == null) {
            return m9166e(callable);
        }
        return m9191a(aunVar, callable);
    }

    @AbstractC3889atm
    /* renamed from: d */
    public static Scheduler m9172d(@AbstractC3889atm Callable<Scheduler> callable) {
        ObjectHelper.m9873a(callable, "Scheduler Callable can't be null");
        Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar = f20148d;
        if (aunVar == null) {
            return m9166e(callable);
        }
        return m9191a(aunVar, callable);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Scheduler m9201a(@AbstractC3889atm Scheduler astVar) {
        Function<? super Scheduler, ? extends Scheduler> aunVar = f20151g;
        return aunVar == null ? astVar : (Scheduler) m9192a((Function<Scheduler, Object>) aunVar, astVar);
    }

    /* renamed from: a */
    public static void m9212a(@AbstractC3889atm Throwable th) {
        Consumer<? super Throwable> aumVar = f20145a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!m9186b(th)) {
            th = new UndeliverableException(th);
        }
        if (aumVar != null) {
            try {
                aumVar.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                m9179c(th2);
            }
        }
        th.printStackTrace();
        m9179c(th);
    }

    /* renamed from: b */
    static boolean m9186b(Throwable th) {
        return (th instanceof OnErrorNotImplementedException) || (th instanceof MissingBackpressureException) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof CompositeException);
    }

    /* renamed from: c */
    static void m9179c(@AbstractC3889atm Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    @AbstractC3889atm
    /* renamed from: b */
    public static Scheduler m9183b(@AbstractC3889atm Scheduler astVar) {
        Function<? super Scheduler, ? extends Scheduler> aunVar = f20153i;
        return aunVar == null ? astVar : (Scheduler) m9192a((Function<Scheduler, Object>) aunVar, astVar);
    }

    @AbstractC3889atm
    /* renamed from: c */
    public static Scheduler m9176c(@AbstractC3889atm Scheduler astVar) {
        Function<? super Scheduler, ? extends Scheduler> aunVar = f20154j;
        return aunVar == null ? astVar : (Scheduler) m9192a((Function<Scheduler, Object>) aunVar, astVar);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Runnable m9213a(@AbstractC3889atm Runnable runnable) {
        ObjectHelper.m9873a(runnable, "run is null");
        Function<? super Runnable, ? extends Runnable> aunVar = f20146b;
        return aunVar == null ? runnable : (Runnable) m9192a((Function<Runnable, Object>) aunVar, runnable);
    }

    @AbstractC3889atm
    /* renamed from: d */
    public static Scheduler m9170d(@AbstractC3889atm Scheduler astVar) {
        Function<? super Scheduler, ? extends Scheduler> aunVar = f20152h;
        return aunVar == null ? astVar : (Scheduler) m9192a((Function<Scheduler, Object>) aunVar, astVar);
    }

    /* renamed from: n */
    public static void m9147n() {
        m9194a((Consumer<? super Throwable>) null);
        m9158h(null);
        m9193a((Function<? super Scheduler, ? extends Scheduler>) null);
        m9181b((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        m9162f(null);
        m9174c((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        m9156i(null);
        m9164e((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        m9160g(null);
        m9168d((Function<? super Callable<Scheduler>, ? extends Scheduler>) null);
        m9152k(null);
        m9182b((BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber>) null);
        m9146n(null);
        m9169d((BiFunction<? super Observable, ? super Observer, ? extends Observer>) null);
        m9142p(null);
        m9165e((BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver>) null);
        m9154j(null);
        m9197a((BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver>) null);
        m9148m(null);
        m9144o(null);
        m9150l(null);
        m9175c((BiFunction<? super Maybe, MaybeObserver, ? extends MaybeObserver>) null);
        m9140q(null);
        m9188a(false);
        m9195a((BooleanSupplier) null);
    }

    /* renamed from: a */
    public static void m9193a(@atn Function<? super Scheduler, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20151g = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: a */
    public static void m9194a(@atn Consumer<? super Throwable> aumVar) {
        if (!f20169y) {
            f20145a = aumVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: b */
    public static void m9181b(@atn Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20147c = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: c */
    public static void m9174c(@atn Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20149e = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: d */
    public static void m9168d(@atn Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20150f = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: e */
    public static void m9164e(@atn Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20148d = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: f */
    public static void m9162f(@atn Function<? super Scheduler, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20153i = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: g */
    public static void m9160g(@atn Function<? super Scheduler, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20154j = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: h */
    public static void m9158h(@atn Function<? super Runnable, ? extends Runnable> aunVar) {
        if (!f20169y) {
            f20146b = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: i */
    public static void m9156i(@atn Function<? super Scheduler, ? extends Scheduler> aunVar) {
        if (!f20169y) {
            f20152h = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: o */
    static void m9145o() {
        f20169y = false;
    }

    @atn
    /* renamed from: p */
    public static Function<? super Completable, ? extends Completable> m9143p() {
        return f20161q;
    }

    @atn
    /* renamed from: q */
    public static BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> m9141q() {
        return f20167w;
    }

    @atn
    /* renamed from: r */
    public static Function<? super Flowable, ? extends Flowable> m9139r() {
        return f20155k;
    }

    @atn
    /* renamed from: s */
    public static Function<? super ConnectableFlowable, ? extends ConnectableFlowable> m9138s() {
        return f20156l;
    }

    @atn
    /* renamed from: t */
    public static BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> m9137t() {
        return f20163s;
    }

    @atn
    /* renamed from: u */
    public static BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> m9136u() {
        return f20164t;
    }

    @atn
    /* renamed from: v */
    public static Function<? super Maybe, ? extends Maybe> m9135v() {
        return f20159o;
    }

    @atn
    /* renamed from: w */
    public static Function<? super Single, ? extends Single> m9134w() {
        return f20160p;
    }

    @atn
    /* renamed from: x */
    public static BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> m9133x() {
        return f20166v;
    }

    @atn
    /* renamed from: y */
    public static Function<? super Observable, ? extends Observable> m9132y() {
        return f20157m;
    }

    @atn
    /* renamed from: z */
    public static Function<? super ConnectableObservable, ? extends ConnectableObservable> m9131z() {
        return f20158n;
    }

    @atn
    /* renamed from: A */
    public static BiFunction<? super Observable, ? super Observer, ? extends Observer> m9218A() {
        return f20165u;
    }

    /* renamed from: j */
    public static void m9154j(@atn Function<? super Completable, ? extends Completable> aunVar) {
        if (!f20169y) {
            f20161q = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: a */
    public static void m9197a(@atn BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> auiVar) {
        if (!f20169y) {
            f20167w = auiVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: k */
    public static void m9152k(@atn Function<? super Flowable, ? extends Flowable> aunVar) {
        if (!f20169y) {
            f20155k = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: l */
    public static void m9150l(@atn Function<? super Maybe, ? extends Maybe> aunVar) {
        if (!f20169y) {
            f20159o = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: m */
    public static void m9148m(@atn Function<? super ConnectableFlowable, ? extends ConnectableFlowable> aunVar) {
        if (!f20169y) {
            f20156l = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: b */
    public static void m9182b(@atn BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> auiVar) {
        if (!f20169y) {
            f20163s = auiVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: c */
    public static void m9175c(@atn BiFunction<? super Maybe, MaybeObserver, ? extends MaybeObserver> auiVar) {
        if (!f20169y) {
            f20164t = auiVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: n */
    public static void m9146n(@atn Function<? super Observable, ? extends Observable> aunVar) {
        if (!f20169y) {
            f20157m = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: o */
    public static void m9144o(@atn Function<? super ConnectableObservable, ? extends ConnectableObservable> aunVar) {
        if (!f20169y) {
            f20158n = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: d */
    public static void m9169d(@atn BiFunction<? super Observable, ? super Observer, ? extends Observer> auiVar) {
        if (!f20169y) {
            f20165u = auiVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: p */
    public static void m9142p(@atn Function<? super Single, ? extends Single> aunVar) {
        if (!f20169y) {
            f20160p = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    /* renamed from: e */
    public static void m9165e(@atn BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> auiVar) {
        if (!f20169y) {
            f20166v = auiVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Subscriber<? super T> m9206a(@AbstractC3889atm Flowable<T> arvVar, @AbstractC3889atm Subscriber<? super T> dbxVar) {
        BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> auiVar = f20163s;
        return auiVar != null ? (Subscriber) m9196a(auiVar, arvVar, dbxVar) : dbxVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observer<? super T> m9202a(@AbstractC3889atm Observable<T> aslVar, @AbstractC3889atm Observer<? super T> assVar) {
        BiFunction<? super Observable, ? super Observer, ? extends Observer> auiVar = f20165u;
        return auiVar != null ? (Observer) m9196a(auiVar, aslVar, assVar) : assVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> SingleObserver<? super T> m9199a(@AbstractC3889atm Single<T> asuVar, @AbstractC3889atm SingleObserver<? super T> asxVar) {
        BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> auiVar = f20166v;
        return auiVar != null ? (SingleObserver) m9196a(auiVar, asuVar, asxVar) : asxVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static CompletableObserver m9208a(@AbstractC3889atm Completable armVar, @AbstractC3889atm CompletableObserver arpVar) {
        BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> auiVar = f20167w;
        return auiVar != null ? (CompletableObserver) m9196a(auiVar, armVar, arpVar) : arpVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> MaybeObserver<? super T> m9204a(@AbstractC3889atm Maybe<T> ascVar, @AbstractC3889atm MaybeObserver<? super T> asfVar) {
        BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> auiVar = f20164t;
        return auiVar != null ? (MaybeObserver) m9196a(auiVar, ascVar, asfVar) : asfVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m9205a(@AbstractC3889atm Maybe<T> ascVar) {
        Function<? super Maybe, ? extends Maybe> aunVar = f20159o;
        return aunVar != null ? (Maybe) m9192a((Function<Maybe<T>, Object>) aunVar, ascVar) : ascVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m9207a(@AbstractC3889atm Flowable<T> arvVar) {
        Function<? super Flowable, ? extends Flowable> aunVar = f20155k;
        return aunVar != null ? (Flowable) m9192a((Function<Flowable<T>, Object>) aunVar, arvVar) : arvVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> ConnectableFlowable<T> m9198a(@AbstractC3889atm ConnectableFlowable<T> aueVar) {
        Function<? super ConnectableFlowable, ? extends ConnectableFlowable> aunVar = f20156l;
        return aunVar != null ? (ConnectableFlowable) m9192a((Function<ConnectableFlowable<T>, Object>) aunVar, aueVar) : aueVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m9203a(@AbstractC3889atm Observable<T> aslVar) {
        Function<? super Observable, ? extends Observable> aunVar = f20157m;
        return aunVar != null ? (Observable) m9192a((Function<Observable<T>, Object>) aunVar, aslVar) : aslVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> ConnectableObservable<T> m9190a(@AbstractC3889atm ConnectableObservable<T> btkVar) {
        Function<? super ConnectableObservable, ? extends ConnectableObservable> aunVar = f20158n;
        return aunVar != null ? (ConnectableObservable) m9192a((Function<ConnectableObservable<T>, Object>) aunVar, btkVar) : btkVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<T> m9200a(@AbstractC3889atm Single<T> asuVar) {
        Function<? super Single, ? extends Single> aunVar = f20160p;
        return aunVar != null ? (Single) m9192a((Function<Single<T>, Object>) aunVar, asuVar) : asuVar;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m9209a(@AbstractC3889atm Completable armVar) {
        Function<? super Completable, ? extends Completable> aunVar = f20161q;
        return aunVar != null ? (Completable) m9192a((Function<Completable, Object>) aunVar, armVar) : armVar;
    }

    /* renamed from: q */
    public static void m9140q(@atn Function<? super ParallelFlowable, ? extends ParallelFlowable> aunVar) {
        if (!f20169y) {
            f20162r = aunVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    @atn
    /* renamed from: B */
    public static Function<? super ParallelFlowable, ? extends ParallelFlowable> m9217B() {
        return f20162r;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> ParallelFlowable<T> m9189a(@AbstractC3889atm ParallelFlowable<T> bubVar) {
        Function<? super ParallelFlowable, ? extends ParallelFlowable> aunVar = f20162r;
        return aunVar != null ? (ParallelFlowable) m9192a((Function<ParallelFlowable<T>, Object>) aunVar, bubVar) : bubVar;
    }

    /* renamed from: C */
    public static boolean m9216C() {
        BooleanSupplier aukVar = f20168x;
        if (aukVar == null) {
            return false;
        }
        try {
            return aukVar.getAsBoolean();
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* renamed from: a */
    public static void m9195a(@atn BooleanSupplier aukVar) {
        if (!f20169y) {
            f20168x = aukVar;
            return;
        }
        throw new IllegalStateException("Plugins can't be changed anymore");
    }

    @atn
    /* renamed from: D */
    public static BooleanSupplier m9215D() {
        return f20168x;
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Scheduler m9210a(@AbstractC3889atm ThreadFactory threadFactory) {
        return new ComputationScheduler((ThreadFactory) ObjectHelper.m9873a(threadFactory, "threadFactory is null"));
    }

    @AbstractC3889atm
    /* renamed from: b */
    public static Scheduler m9184b(@AbstractC3889atm ThreadFactory threadFactory) {
        return new IoScheduler((ThreadFactory) ObjectHelper.m9873a(threadFactory, "threadFactory is null"));
    }

    @AbstractC3889atm
    /* renamed from: c */
    public static Scheduler m9177c(@AbstractC3889atm ThreadFactory threadFactory) {
        return new NewThreadScheduler((ThreadFactory) ObjectHelper.m9873a(threadFactory, "threadFactory is null"));
    }

    @AbstractC3889atm
    /* renamed from: d */
    public static Scheduler m9171d(@AbstractC3889atm ThreadFactory threadFactory) {
        return new SingleScheduler((ThreadFactory) ObjectHelper.m9873a(threadFactory, "threadFactory is null"));
    }

    @AbstractC3889atm
    /* renamed from: a */
    static <T, R> R m9192a(@AbstractC3889atm Function<T, R> aunVar, @AbstractC3889atm T t) {
        try {
            return aunVar.apply(t);
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    @AbstractC3889atm
    /* renamed from: a */
    static <T, U, R> R m9196a(@AbstractC3889atm BiFunction<T, U, R> auiVar, @AbstractC3889atm T t, @AbstractC3889atm U u) {
        try {
            return auiVar.apply(t, u);
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    @AbstractC3889atm
    /* renamed from: e */
    static Scheduler m9166e(@AbstractC3889atm Callable<Scheduler> callable) {
        try {
            return (Scheduler) ObjectHelper.m9873a(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    @AbstractC3889atm
    /* renamed from: a */
    static Scheduler m9191a(@AbstractC3889atm Function<? super Callable<Scheduler>, ? extends Scheduler> aunVar, Callable<Scheduler> callable) {
        return (Scheduler) ObjectHelper.m9873a(m9192a((Function<Callable<Scheduler>, Object>) aunVar, callable), "Scheduler Callable result can't be null");
    }

    private RxJavaPlugins() {
        throw new IllegalStateException("No instances!");
    }
}
