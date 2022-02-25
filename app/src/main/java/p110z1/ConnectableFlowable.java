package p110z1;

import java.util.concurrent.TimeUnit;

/* renamed from: z1.aue */
/* loaded from: classes3.dex */
public abstract class ConnectableFlowable<T> extends Flowable<T> {
    /* renamed from: l */
    public abstract void mo9740l(@AbstractC3889atm Consumer<? super Disposable> aumVar);

    /* renamed from: T */
    public final Disposable m9961T() {
        ConnectConsumer bsrVar = new ConnectConsumer();
        mo9740l((Consumer<? super Disposable>) bsrVar);
        return bsrVar.f20063a;
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: U */
    public Flowable<T> m9960U() {
        return RxJavaPlugins.m9207a(new FlowableRefCount(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: m */
    public final Flowable<T> m9955m(int i) {
        return m9956b(i, 0L, TimeUnit.NANOSECONDS, Schedulers.m9046c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: s */
    public final Flowable<T> m9953s(long j, TimeUnit timeUnit) {
        return m9956b(1, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: s */
    public final Flowable<T> m9952s(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m9956b(1, j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: b */
    public final Flowable<T> m9957b(int i, long j, TimeUnit timeUnit) {
        return m9956b(i, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: b */
    public final Flowable<T> m9956b(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9878a(i, "subscriberCount");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableRefCount(this, i, j, timeUnit, astVar));
    }

    @AbstractC3889atm
    /* renamed from: V */
    public Flowable<T> m9959V() {
        return m9954n(1);
    }

    @AbstractC3889atm
    /* renamed from: n */
    public Flowable<T> m9954n(int i) {
        return m9958a(i, Functions.m9914b());
    }

    @AbstractC3889atm
    /* renamed from: a */
    public Flowable<T> m9958a(int i, @AbstractC3889atm Consumer<? super Disposable> aumVar) {
        if (i > 0) {
            return RxJavaPlugins.m9207a(new FlowableAutoConnect(this, i, aumVar));
        }
        mo9740l(aumVar);
        return RxJavaPlugins.m9198a((ConnectableFlowable) this);
    }
}
