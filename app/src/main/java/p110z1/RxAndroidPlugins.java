package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.ate */
/* loaded from: classes3.dex */
public final class RxAndroidPlugins {

    /* renamed from: a */
    private static volatile Function<Callable<Scheduler>, Scheduler> f17493a;

    /* renamed from: b */
    private static volatile Function<Scheduler, Scheduler> f17494b;

    /* renamed from: a */
    public static void m10012a(Function<Callable<Scheduler>, Scheduler> aunVar) {
        f17493a = aunVar;
    }

    /* renamed from: a */
    public static Scheduler m10014a(Callable<Scheduler> callable) {
        if (callable != null) {
            Function<Callable<Scheduler>, Scheduler> aunVar = f17493a;
            if (aunVar == null) {
                return m10008b(callable);
            }
            return m10010a(aunVar, callable);
        }
        throw new NullPointerException("scheduler == null");
    }

    /* renamed from: b */
    public static void m10007b(Function<Scheduler, Scheduler> aunVar) {
        f17494b = aunVar;
    }

    /* renamed from: a */
    public static Scheduler m10013a(Scheduler astVar) {
        if (astVar != null) {
            Function<Scheduler, Scheduler> aunVar = f17494b;
            return aunVar == null ? astVar : (Scheduler) m10011a(aunVar, astVar);
        }
        throw new NullPointerException("scheduler == null");
    }

    /* renamed from: a */
    public static Function<Callable<Scheduler>, Scheduler> m10015a() {
        return f17493a;
    }

    /* renamed from: b */
    public static Function<Scheduler, Scheduler> m10009b() {
        return f17494b;
    }

    /* renamed from: c */
    public static void m10006c() {
        m10012a((Function<Callable<Scheduler>, Scheduler>) null);
        m10007b((Function<Scheduler, Scheduler>) null);
    }

    /* renamed from: b */
    static Scheduler m10008b(Callable<Scheduler> callable) {
        try {
            Scheduler call = callable.call();
            if (call != null) {
                return call;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw Exceptions.m9984a(th);
        }
    }

    /* renamed from: a */
    static Scheduler m10010a(Function<Callable<Scheduler>, Scheduler> aunVar, Callable<Scheduler> callable) {
        Scheduler astVar = (Scheduler) m10011a((Function<Callable<Scheduler>, Object>) aunVar, callable);
        if (astVar != null) {
            return astVar;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }

    /* renamed from: a */
    static <T, R> R m10011a(Function<T, R> aunVar, T t) {
        try {
            return aunVar.apply(t);
        } catch (Throwable th) {
            throw Exceptions.m9984a(th);
        }
    }

    private RxAndroidPlugins() {
        throw new AssertionError("No instances.");
    }
}
