package p110z1;

import java.util.concurrent.Future;

/* renamed from: z1.ats */
/* loaded from: classes3.dex */
public final class Disposables {
    private Disposables() {
        throw new IllegalStateException("No instances!");
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Disposable m9994a(@AbstractC3889atm Runnable runnable) {
        ObjectHelper.m9873a(runnable, "run is null");
        return new RunnableDisposable(runnable);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Disposable m9991a(@AbstractC3889atm Action augVar) {
        ObjectHelper.m9873a(augVar, "run is null");
        return new ActionDisposable(augVar);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Disposable m9993a(@AbstractC3889atm Future<?> future) {
        ObjectHelper.m9873a(future, "future is null");
        return m9992a(future, true);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Disposable m9992a(@AbstractC3889atm Future<?> future, boolean z) {
        ObjectHelper.m9873a(future, "future is null");
        return new FutureDisposable(future, z);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Disposable m9990a(@AbstractC3889atm dby dbyVar) {
        ObjectHelper.m9873a(dbyVar, "subscription is null");
        return new SubscriptionDisposable(dbyVar);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static Disposable m9995a() {
        return m9994a(Functions.f17556b);
    }

    @AbstractC3889atm
    /* renamed from: b */
    public static Disposable m9989b() {
        return EmptyDisposable.INSTANCE;
    }
}
