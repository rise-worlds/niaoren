package p110z1;

import java.util.concurrent.TimeUnit;

/* renamed from: z1.btk */
/* loaded from: classes3.dex */
public abstract class ConnectableObservable<T> extends Observable<T> {
    /* renamed from: k */
    public abstract void mo9358k(@AbstractC3889atm Consumer<? super Disposable> aumVar);

    /* renamed from: b */
    public final Disposable m9364b() {
        ConnectConsumer bsrVar = new ConnectConsumer();
        mo9358k((Consumer<? super Disposable>) bsrVar);
        return bsrVar.f20063a;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public Observable<T> m9361c() {
        return RxJavaPlugins.m9203a(new ObservableRefCount(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final Observable<T> m9360i(int i) {
        return m9362b(i, 0L, TimeUnit.NANOSECONDS, Schedulers.m9046c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: s */
    public final Observable<T> m9357s(long j, TimeUnit timeUnit) {
        return m9362b(1, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: s */
    public final Observable<T> m9356s(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m9362b(1, j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: b */
    public final Observable<T> m9363b(int i, long j, TimeUnit timeUnit) {
        return m9362b(i, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public final Observable<T> m9362b(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9878a(i, "subscriberCount");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableRefCount(this, i, j, timeUnit, astVar));
    }

    @AbstractC3889atm
    /* renamed from: R */
    public Observable<T> m9366R() {
        return m9359j(1);
    }

    @AbstractC3889atm
    /* renamed from: j */
    public Observable<T> m9359j(int i) {
        return m9365a(i, Functions.m9914b());
    }

    @AbstractC3889atm
    /* renamed from: a */
    public Observable<T> m9365a(int i, @AbstractC3889atm Consumer<? super Disposable> aumVar) {
        if (i > 0) {
            return RxJavaPlugins.m9203a(new ObservableAutoConnect(this, i, aumVar));
        }
        mo9358k(aumVar);
        return RxJavaPlugins.m9190a((ConnectableObservable) this);
    }
}
