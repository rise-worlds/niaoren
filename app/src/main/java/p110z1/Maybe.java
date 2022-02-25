package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.asc */
/* loaded from: classes3.dex */
public abstract class Maybe<T> implements MaybeSource<T> {
    /* renamed from: b */
    protected abstract void mo8992b(MaybeObserver<? super T> asfVar);

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10797a(Iterable<? extends MaybeSource<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9205a(new MaybeAmb(null, iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Maybe<T> m10750a(MaybeSource<? extends T>... asiVarArr) {
        if (asiVarArr.length == 0) {
            return m10805a();
        }
        if (asiVarArr.length == 1) {
            return m10720c((MaybeSource) asiVarArr[0]);
        }
        return RxJavaPlugins.m9205a(new MaybeAmb(asiVarArr, null));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10744b(Iterable<? extends MaybeSource<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9207a(new MaybeConcatIterable(iterable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10782a(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        return m10728b(asiVar, asiVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10781a(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, MaybeSource<? extends T> asiVar3) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        return m10728b(asiVar, asiVar2, asiVar3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10780a(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, MaybeSource<? extends T> asiVar3, MaybeSource<? extends T> asiVar4) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        return m10728b(asiVar, asiVar2, asiVar3, asiVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T> Flowable<T> m10754a(Publisher<? extends MaybeSource<? extends T>> dbwVar) {
        return m10753a(dbwVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10753a(Publisher<? extends MaybeSource<? extends T>> dbwVar, int i) {
        ObjectHelper.m9873a(dbwVar, "sources is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapPublisher(dbwVar, MaybeToPublisher.instance(), i, ErrorMode.IMMEDIATE));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10728b(MaybeSource<? extends T>... asiVarArr) {
        ObjectHelper.m9873a(asiVarArr, "sources is null");
        if (asiVarArr.length == 0) {
            return Flowable.m11094b();
        }
        if (asiVarArr.length == 1) {
            return RxJavaPlugins.m9207a(new MaybeToFlowable(asiVarArr[0]));
        }
        return RxJavaPlugins.m9207a(new MaybeConcatArray(asiVarArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static <T> Flowable<T> m10709c(MaybeSource<? extends T>... asiVarArr) {
        if (asiVarArr.length == 0) {
            return Flowable.m11094b();
        }
        if (asiVarArr.length == 1) {
            return RxJavaPlugins.m9207a(new MaybeToFlowable(asiVarArr[0]));
        }
        return RxJavaPlugins.m9207a(new MaybeConcatArrayDelayError(asiVarArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Flowable<T> m10697d(MaybeSource<? extends T>... asiVarArr) {
        return Flowable.m11098a((Object[]) asiVarArr).m10946e(MaybeToPublisher.instance());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10724c(Iterable<? extends MaybeSource<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return Flowable.m10952e((Iterable) iterable).m10969d(MaybeToPublisher.instance());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T> Flowable<T> m10730b(Publisher<? extends MaybeSource<? extends T>> dbwVar) {
        return Flowable.m10964d((Publisher) dbwVar).m10969d(MaybeToPublisher.instance());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Flowable<T> m10705d(Iterable<? extends MaybeSource<? extends T>> iterable) {
        return Flowable.m10952e((Iterable) iterable).m10946e(MaybeToPublisher.instance());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static <T> Flowable<T> m10711c(Publisher<? extends MaybeSource<? extends T>> dbwVar) {
        return Flowable.m10964d((Publisher) dbwVar).m10946e(MaybeToPublisher.instance());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10785a(MaybeOnSubscribe<T> asgVar) {
        ObjectHelper.m9873a(asgVar, "onSubscribe is null");
        return RxJavaPlugins.m9205a(new MaybeCreate(asgVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10792a(Callable<? extends MaybeSource<? extends T>> callable) {
        ObjectHelper.m9873a(callable, "maybeSupplier is null");
        return RxJavaPlugins.m9205a(new MaybeDefer(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Maybe<T> m10805a() {
        return RxJavaPlugins.m9205a((Maybe) MaybeEmpty.f18594a);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10793a(Throwable th) {
        ObjectHelper.m9873a(th, "exception is null");
        return RxJavaPlugins.m9205a(new MaybeError(th));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Maybe<T> m10742b(Callable<? extends Throwable> callable) {
        ObjectHelper.m9873a(callable, "errorSupplier is null");
        return RxJavaPlugins.m9205a(new MaybeErrorCallable(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10766a(Action augVar) {
        ObjectHelper.m9873a(augVar, "run is null");
        return RxJavaPlugins.m9205a((Maybe) new MaybeFromAction(augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10787a(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "completableSource is null");
        return RxJavaPlugins.m9205a(new MaybeFromCompletable(arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10767a(SingleSource<T> ataVar) {
        ObjectHelper.m9873a(ataVar, "singleSource is null");
        return RxJavaPlugins.m9205a(new MaybeFromSingle(ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Maybe<T> m10722c(@AbstractC3889atm Callable<? extends T> callable) {
        ObjectHelper.m9873a(callable, "callable is null");
        return RxJavaPlugins.m9205a((Maybe) new MaybeFromCallable(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10789a(Future<? extends T> future) {
        ObjectHelper.m9873a(future, "future is null");
        return RxJavaPlugins.m9205a(new MaybeFromFuture(future, 0L, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10788a(Future<? extends T> future, long j, TimeUnit timeUnit) {
        ObjectHelper.m9873a(future, "future is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        return RxJavaPlugins.m9205a(new MaybeFromFuture(future, j, timeUnit));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10794a(Runnable runnable) {
        ObjectHelper.m9873a(runnable, "run is null");
        return RxJavaPlugins.m9205a((Maybe) new MaybeFromRunnable(runnable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10795a(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return RxJavaPlugins.m9205a((Maybe) new MaybeJust(t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public static <T> Flowable<T> m10695e(Iterable<? extends MaybeSource<? extends T>> iterable) {
        return m10698d((Publisher) Flowable.m10952e((Iterable) iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Flowable<T> m10698d(Publisher<? extends MaybeSource<? extends T>> dbwVar) {
        return m10729b(dbwVar, Integer.MAX_VALUE);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10729b(Publisher<? extends MaybeSource<? extends T>> dbwVar, int i) {
        ObjectHelper.m9873a(dbwVar, "source is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9207a(new FlowableFlatMapPublisher(dbwVar, MaybeToPublisher.instance(), false, i, 1));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Maybe<T> m10783a(MaybeSource<? extends MaybeSource<? extends T>> asiVar) {
        ObjectHelper.m9873a(asiVar, "source is null");
        return RxJavaPlugins.m9205a(new MaybeFlatten(asiVar, Functions.m9935a()));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10740b(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        return m10687e(asiVar, asiVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10739b(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, MaybeSource<? extends T> asiVar3) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        return m10687e(asiVar, asiVar2, asiVar3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10738b(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, MaybeSource<? extends T> asiVar3, MaybeSource<? extends T> asiVar4) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        return m10687e(asiVar, asiVar2, asiVar3, asiVar4);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public static <T> Flowable<T> m10687e(MaybeSource<? extends T>... asiVarArr) {
        ObjectHelper.m9873a(asiVarArr, "sources is null");
        if (asiVarArr.length == 0) {
            return Flowable.m11094b();
        }
        if (asiVarArr.length == 1) {
            return RxJavaPlugins.m9207a(new MaybeToFlowable(asiVarArr[0]));
        }
        return RxJavaPlugins.m9207a(new MaybeMergeArray(asiVarArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public static <T> Flowable<T> m10679f(MaybeSource<? extends T>... asiVarArr) {
        if (asiVarArr.length == 0) {
            return Flowable.m11094b();
        }
        return Flowable.m11098a((Object[]) asiVarArr).m10966d(MaybeToPublisher.instance(), true, asiVarArr.length);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public static <T> Flowable<T> m10685f(Iterable<? extends MaybeSource<? extends T>> iterable) {
        return Flowable.m10952e((Iterable) iterable).m10944e(MaybeToPublisher.instance(), true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public static <T> Flowable<T> m10688e(Publisher<? extends MaybeSource<? extends T>> dbwVar) {
        return m10710c(dbwVar, Integer.MAX_VALUE);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10710c(Publisher<? extends MaybeSource<? extends T>> dbwVar, int i) {
        ObjectHelper.m9873a(dbwVar, "source is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9207a(new FlowableFlatMapPublisher(dbwVar, MaybeToPublisher.instance(), true, i, 1));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10719c(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        return m10679f(asiVar, asiVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10718c(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, MaybeSource<? extends T> asiVar3) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        return m10679f(asiVar, asiVar2, asiVar3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10717c(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, MaybeSource<? extends T> asiVar3, MaybeSource<? extends T> asiVar4) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        return m10679f(asiVar, asiVar2, asiVar3, asiVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Maybe<T> m10749b() {
        return RxJavaPlugins.m9205a(MaybeNever.f18672a);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public static <T> Single<Boolean> m10702d(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2) {
        return m10771a(asiVar, asiVar2, ObjectHelper.m9880a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<Boolean> m10771a(MaybeSource<? extends T> asiVar, MaybeSource<? extends T> asiVar2, BiPredicate<? super T, ? super T> aujVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(aujVar, "isEqual is null");
        return RxJavaPlugins.m9200a(new MaybeEqualSingle(asiVar, asiVar2, aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public static Maybe<Long> m10803a(long j, TimeUnit timeUnit) {
        return m10801a(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public static Maybe<Long> m10801a(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9205a(new MaybeTimer(Math.max(0L, j), timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Maybe<T> m10741b(MaybeSource<T> asiVar) {
        if (!(asiVar instanceof Maybe)) {
            ObjectHelper.m9873a(asiVar, "onSubscribe is null");
            return RxJavaPlugins.m9205a(new MaybeUnsafeCreate(asiVar));
        }
        throw new IllegalArgumentException("unsafeCreate(Maybe) should be upgraded");
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, D> Maybe<T> m10791a(Callable<? extends D> callable, Function<? super D, ? extends MaybeSource<? extends T>> aunVar, Consumer<? super D> aumVar) {
        return m10790a((Callable) callable, (Function) aunVar, (Consumer) aumVar, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, D> Maybe<T> m10790a(Callable<? extends D> callable, Function<? super D, ? extends MaybeSource<? extends T>> aunVar, Consumer<? super D> aumVar, boolean z) {
        ObjectHelper.m9873a(callable, "resourceSupplier is null");
        ObjectHelper.m9873a(aunVar, "sourceSupplier is null");
        ObjectHelper.m9873a(aumVar, "disposer is null");
        return RxJavaPlugins.m9205a(new MaybeUsing(callable, aunVar, aumVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Maybe<T> m10720c(MaybeSource<T> asiVar) {
        if (asiVar instanceof Maybe) {
            return RxJavaPlugins.m9205a((Maybe) asiVar);
        }
        ObjectHelper.m9873a(asiVar, "onSubscribe is null");
        return RxJavaPlugins.m9205a(new MaybeUnsafeCreate(asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Maybe<R> m10796a(Iterable<? extends MaybeSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9205a(new MaybeZipIterable(iterable, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, R> Maybe<R> m10772a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        return m10756a(Functions.m9927a((BiFunction) auiVar), asiVar, asiVar2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, R> Maybe<R> m10773a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, MaybeSource<? extends T3> asiVar3, Function3<? super T1, ? super T2, ? super T3, ? extends R> auoVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        return m10756a(Functions.m9921a((Function3) auoVar), asiVar, asiVar2, asiVar3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, R> Maybe<R> m10774a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, MaybeSource<? extends T3> asiVar3, MaybeSource<? extends T4> asiVar4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> aupVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        return m10756a(Functions.m9920a((Function4) aupVar), asiVar, asiVar2, asiVar3, asiVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, R> Maybe<R> m10775a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, MaybeSource<? extends T3> asiVar3, MaybeSource<? extends T4> asiVar4, MaybeSource<? extends T5> asiVar5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> auqVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        ObjectHelper.m9873a(asiVar5, "source5 is null");
        return m10756a(Functions.m9919a((Function5) auqVar), asiVar, asiVar2, asiVar3, asiVar4, asiVar5);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, R> Maybe<R> m10776a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, MaybeSource<? extends T3> asiVar3, MaybeSource<? extends T4> asiVar4, MaybeSource<? extends T5> asiVar5, MaybeSource<? extends T6> asiVar6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> aurVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        ObjectHelper.m9873a(asiVar5, "source5 is null");
        ObjectHelper.m9873a(asiVar6, "source6 is null");
        return m10756a(Functions.m9918a((Function6) aurVar), asiVar, asiVar2, asiVar3, asiVar4, asiVar5, asiVar6);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, R> Maybe<R> m10777a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, MaybeSource<? extends T3> asiVar3, MaybeSource<? extends T4> asiVar4, MaybeSource<? extends T5> asiVar5, MaybeSource<? extends T6> asiVar6, MaybeSource<? extends T7> asiVar7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> ausVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        ObjectHelper.m9873a(asiVar5, "source5 is null");
        ObjectHelper.m9873a(asiVar6, "source6 is null");
        ObjectHelper.m9873a(asiVar7, "source7 is null");
        return m10756a(Functions.m9917a((Function7) ausVar), asiVar, asiVar2, asiVar3, asiVar4, asiVar5, asiVar6, asiVar7);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Maybe<R> m10778a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, MaybeSource<? extends T3> asiVar3, MaybeSource<? extends T4> asiVar4, MaybeSource<? extends T5> asiVar5, MaybeSource<? extends T6> asiVar6, MaybeSource<? extends T7> asiVar7, MaybeSource<? extends T8> asiVar8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> autVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        ObjectHelper.m9873a(asiVar5, "source5 is null");
        ObjectHelper.m9873a(asiVar6, "source6 is null");
        ObjectHelper.m9873a(asiVar7, "source7 is null");
        ObjectHelper.m9873a(asiVar8, "source8 is null");
        return m10756a(Functions.m9916a((Function8) autVar), asiVar, asiVar2, asiVar3, asiVar4, asiVar5, asiVar6, asiVar7, asiVar8);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Maybe<R> m10779a(MaybeSource<? extends T1> asiVar, MaybeSource<? extends T2> asiVar2, MaybeSource<? extends T3> asiVar3, MaybeSource<? extends T4> asiVar4, MaybeSource<? extends T5> asiVar5, MaybeSource<? extends T6> asiVar6, MaybeSource<? extends T7> asiVar7, MaybeSource<? extends T8> asiVar8, MaybeSource<? extends T9> asiVar9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> auuVar) {
        ObjectHelper.m9873a(asiVar, "source1 is null");
        ObjectHelper.m9873a(asiVar2, "source2 is null");
        ObjectHelper.m9873a(asiVar3, "source3 is null");
        ObjectHelper.m9873a(asiVar4, "source4 is null");
        ObjectHelper.m9873a(asiVar5, "source5 is null");
        ObjectHelper.m9873a(asiVar6, "source6 is null");
        ObjectHelper.m9873a(asiVar7, "source7 is null");
        ObjectHelper.m9873a(asiVar8, "source8 is null");
        ObjectHelper.m9873a(asiVar9, "source9 is null");
        return m10756a(Functions.m9915a((Function9) auuVar), asiVar, asiVar2, asiVar3, asiVar4, asiVar5, asiVar6, asiVar7, asiVar8, asiVar9);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Maybe<R> m10756a(Function<? super Object[], ? extends R> aunVar, MaybeSource<? extends T>... asiVarArr) {
        ObjectHelper.m9873a(asiVarArr, "sources is null");
        if (asiVarArr.length == 0) {
            return m10805a();
        }
        ObjectHelper.m9873a(aunVar, "zipper is null");
        return RxJavaPlugins.m9205a(new MaybeZipArray(asiVarArr, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final Maybe<T> m10703d(MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return m10750a(this, asiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> R m10786a(@AbstractC3889atm MaybeConverter<T, ? extends R> asdVar) {
        return (R) ((MaybeConverter) ObjectHelper.m9873a(asdVar, "converter is null")).m10649a(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final T m10727c() {
        BlockingMultiObserver aweVar = new BlockingMultiObserver();
        mo10646a((MaybeObserver) aweVar);
        return (T) aweVar.m9846b();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final T m10743b(T t) {
        ObjectHelper.m9873a((Object) t, "defaultValue is null");
        BlockingMultiObserver aweVar = new BlockingMultiObserver();
        mo10646a((MaybeObserver) aweVar);
        return (T) aweVar.m9847a(t);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Maybe<T> m10708d() {
        return RxJavaPlugins.m9205a(new MaybeCache(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <U> Maybe<U> m10798a(Class<? extends U> cls) {
        ObjectHelper.m9873a(cls, "clazz is null");
        return (Maybe<U>) m10664j(Functions.m9933a((Class) cls));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Maybe<R> m10769a(MaybeTransformer<? super T, ? extends R> asjVar) {
        return m10720c(((MaybeTransformer) ObjectHelper.m9873a(asjVar, "transformer is null")).m10645a(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Maybe<R> m10759a(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9205a(new MaybeFlatten(this, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public final Flowable<T> m10693e(MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return m10782a(this, asiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final Single<Boolean> m10723c(Object obj) {
        ObjectHelper.m9873a(obj, "item is null");
        return RxJavaPlugins.m9200a(new MaybeContains(this, obj));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Single<Long> m10696e() {
        return RxJavaPlugins.m9200a(new MaybeCount(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final Maybe<T> m10704d(T t) {
        ObjectHelper.m9873a((Object) t, "defaultItem is null");
        return m10669i(m10795a(t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: b */
    public final Maybe<T> m10747b(long j, TimeUnit timeUnit) {
        return m10746b(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: b */
    public final Maybe<T> m10746b(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9205a(new MaybeDelay(this, Math.max(0L, j), timeUnit, astVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final <U, V> Maybe<T> m10680f(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "delayIndicator is null");
        return RxJavaPlugins.m9205a(new MaybeDelayOtherPublisher(this, dbwVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: g */
    public final <U> Maybe<T> m10675g(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "subscriptionIndicator is null");
        return RxJavaPlugins.m9205a(new MaybeDelaySubscriptionOtherPublisher(this, dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: c */
    public final Maybe<T> m10726c(long j, TimeUnit timeUnit) {
        return m10725c(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: c */
    public final Maybe<T> m10725c(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10675g(Flowable.m11085b(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Maybe<T> m10762a(Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onAfterSuccess is null");
        return RxJavaPlugins.m9205a(new MaybeDoAfterSuccess(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Maybe<T> m10735b(Action augVar) {
        return RxJavaPlugins.m9205a(new MaybePeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), Functions.f17557c, (Action) ObjectHelper.m9873a(augVar, "onAfterTerminate is null"), Functions.f17557c));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final Maybe<T> m10715c(Action augVar) {
        ObjectHelper.m9873a(augVar, "onFinally is null");
        return RxJavaPlugins.m9205a(new MaybeDoFinally(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final Maybe<T> m10701d(Action augVar) {
        return RxJavaPlugins.m9205a(new MaybePeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, (Action) ObjectHelper.m9873a(augVar, "onDispose is null")));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: e */
    public final Maybe<T> m10691e(Action augVar) {
        return RxJavaPlugins.m9205a(new MaybePeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), (Action) ObjectHelper.m9873a(augVar, "onComplete is null"), Functions.f17557c, Functions.f17557c));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Maybe<T> m10733b(Consumer<? super Throwable> aumVar) {
        return RxJavaPlugins.m9205a(new MaybePeek(this, Functions.m9914b(), Functions.m9914b(), (Consumer) ObjectHelper.m9873a(aumVar, "onError is null"), Functions.f17557c, Functions.f17557c, Functions.f17557c));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Maybe<T> m10765a(BiConsumer<? super T, ? super Throwable> auhVar) {
        ObjectHelper.m9873a(auhVar, "onEvent is null");
        return RxJavaPlugins.m9205a(new MaybeDoOnEvent(this, auhVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final Maybe<T> m10714c(Consumer<? super Disposable> aumVar) {
        return RxJavaPlugins.m9205a(new MaybePeek(this, (Consumer) ObjectHelper.m9873a(aumVar, "onSubscribe is null"), Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.f17557c));
    }

    @SchedulerSupport(m10000a = "none")
    @CheckReturnValue
    @Experimental
    @AbstractC3889atm
    /* renamed from: f */
    public final Maybe<T> m10682f(Action augVar) {
        ObjectHelper.m9873a(augVar, "onTerminate is null");
        return RxJavaPlugins.m9205a(new MaybeDoOnTerminate(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final Maybe<T> m10700d(Consumer<? super T> aumVar) {
        return RxJavaPlugins.m9205a(new MaybePeek(this, Functions.m9914b(), (Consumer) ObjectHelper.m9873a(aumVar, "onSuccess is null"), Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.f17557c));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Maybe<T> m10755a(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9205a(new MaybeFilter(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final <R> Maybe<R> m10732b(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9205a(new MaybeFlatten(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Maybe<R> m10757a(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, Function<? super Throwable, ? extends MaybeSource<? extends R>> aunVar2, Callable<? extends MaybeSource<? extends R>> callable) {
        ObjectHelper.m9873a(aunVar, "onSuccessMapper is null");
        ObjectHelper.m9873a(aunVar2, "onErrorMapper is null");
        ObjectHelper.m9873a(callable, "onCompleteSupplier is null");
        return RxJavaPlugins.m9205a(new MaybeFlatMapNotification(this, aunVar, aunVar2, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <U, R> Maybe<R> m10758a(Function<? super T, ? extends MaybeSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return RxJavaPlugins.m9205a(new MaybeFlatMapBiSelector(this, aunVar, auiVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final <U> Flowable<U> m10713c(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new MaybeFlatMapIterableFlowable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final <U> Observable<U> m10699d(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new MaybeFlatMapIterableObservable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: e */
    public final <R> Observable<R> m10689e(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new MaybeFlatMapObservable(this, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final <R> Flowable<R> m10681f(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new MaybeFlatMapPublisher(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: g */
    public final <R> Single<R> m10676g(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9200a(new MaybeFlatMapSingle(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: h */
    public final <R> Maybe<R> m10672h(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9205a(new MaybeFlatMapSingleElement(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: i */
    public final Completable m10668i(Function<? super T, ? extends CompletableSource> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9209a(new MaybeFlatMapCompletable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Maybe<T> m10686f() {
        return RxJavaPlugins.m9205a(new MaybeHide(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final Completable m10678g() {
        return RxJavaPlugins.m9209a(new MaybeIgnoreElementCompletable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Single<Boolean> m10674h() {
        return RxJavaPlugins.m9200a(new MaybeIsEmptySingle(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Maybe<R> m10784a(MaybeOperator<? extends R, ? super T> ashVar) {
        ObjectHelper.m9873a(ashVar, "lift is null");
        return RxJavaPlugins.m9205a(new MaybeLift(this, ashVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: j */
    public final <R> Maybe<R> m10664j(Function<? super T, ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9205a(new MaybeMap(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @Experimental
    /* renamed from: i */
    public final Single<Notification<T>> m10670i() {
        return RxJavaPlugins.m9200a(new MaybeMaterialize(this));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final Flowable<T> m10683f(MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return m10740b(this, asiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Maybe<T> m10768a(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9205a(new MaybeObserveOn(this, astVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final <U> Maybe<U> m10745b(Class<U> cls) {
        ObjectHelper.m9873a(cls, "clazz is null");
        return m10755a((Predicate) Functions.m9912b((Class) cls)).m10798a((Class) cls);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: k */
    public final <R> R m10661k(Function<? super Maybe<T>, R> aunVar) {
        try {
            return (R) ((Function) ObjectHelper.m9873a(aunVar, "convert is null")).apply(this);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: j */
    public final Flowable<T> m10666j() {
        if (this instanceof FuseToFlowable) {
            return ((FuseToFlowable) this).mo9727r_();
        }
        return RxJavaPlugins.m9207a(new MaybeToFlowable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final Observable<T> m10663k() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).mo9572w_();
        }
        return RxJavaPlugins.m9203a(new MaybeToObservable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: e */
    public final Single<T> m10694e(T t) {
        ObjectHelper.m9873a((Object) t, "defaultValue is null");
        return RxJavaPlugins.m9200a(new MaybeToSingle(this, t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: l */
    public final Single<T> m10660l() {
        return RxJavaPlugins.m9200a(new MaybeToSingle(this, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: m */
    public final Maybe<T> m10658m() {
        return m10731b(Functions.m9909c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Maybe<T> m10731b(Predicate<? super Throwable> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9205a(new MaybeOnErrorComplete(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: g */
    public final Maybe<T> m10677g(MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "next is null");
        return m10659l(Functions.m9911b(asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: l */
    public final Maybe<T> m10659l(Function<? super Throwable, ? extends MaybeSource<? extends T>> aunVar) {
        ObjectHelper.m9873a(aunVar, "resumeFunction is null");
        return RxJavaPlugins.m9205a(new MaybeOnErrorNext(this, aunVar, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: m */
    public final Maybe<T> m10657m(Function<? super Throwable, ? extends T> aunVar) {
        ObjectHelper.m9873a(aunVar, "valueSupplier is null");
        return RxJavaPlugins.m9205a(new MaybeOnErrorReturn(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: f */
    public final Maybe<T> m10684f(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return m10657m(Functions.m9911b(t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: h */
    public final Maybe<T> m10673h(MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "next is null");
        return RxJavaPlugins.m9205a(new MaybeOnErrorNext(this, Functions.m9911b(asiVar), false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: n */
    public final Maybe<T> m10656n() {
        return RxJavaPlugins.m9205a(new MaybeDetach(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: o */
    public final Flowable<T> m10654o() {
        return m10804a((long) cjm.f20759b);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m10804a(long j) {
        return m10666j().m10983d(j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m10763a(BooleanSupplier aukVar) {
        return m10666j().m11177a(aukVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: n */
    public final Flowable<T> m10655n(Function<? super Flowable<Object>, ? extends Publisher<?>> aunVar) {
        return m10666j().m10808z(aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: p */
    public final Maybe<T> m10652p() {
        return m10799a((long) cjm.f20759b, Functions.m9909c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Maybe<T> m10764a(BiPredicate<? super Integer, ? super Throwable> aujVar) {
        return m10666j().m11060b(aujVar).m11289J();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Maybe<T> m10748b(long j) {
        return m10799a(j, Functions.m9909c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Maybe<T> m10799a(long j, Predicate<? super Throwable> auxVar) {
        return m10666j().m11233a(j, auxVar).m11289J();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Maybe<T> m10712c(Predicate<? super Throwable> auxVar) {
        return m10799a((long) cjm.f20759b, auxVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Maybe<T> m10734b(BooleanSupplier aukVar) {
        ObjectHelper.m9873a(aukVar, "stop is null");
        return m10799a((long) cjm.f20759b, Functions.m9926a(aukVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: o */
    public final Maybe<T> m10653o(Function<? super Flowable<Throwable>, ? extends Publisher<?>> aunVar) {
        return m10666j().m11304B(aunVar).m11289J();
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: q */
    public final Disposable m10651q() {
        return m10760a(Functions.m9914b(), Functions.f17560f, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Disposable m10690e(Consumer<? super T> aumVar) {
        return m10760a(aumVar, Functions.f17560f, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Disposable m10761a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2) {
        return m10760a(aumVar, aumVar2, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Disposable m10760a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar) {
        ObjectHelper.m9873a(aumVar, "onSuccess is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        return (Disposable) m10721c((Maybe<T>) new MaybeCallbackObserver(aumVar, aumVar2, augVar));
    }

    @Override // p110z1.MaybeSource
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final void mo10646a(MaybeObserver<? super T> asfVar) {
        ObjectHelper.m9873a(asfVar, "observer is null");
        MaybeObserver<? super T> a = RxJavaPlugins.m9204a(this, asfVar);
        ObjectHelper.m9873a(a, "The RxJavaPlugins.onSubscribe hook returned a null MaybeObserver. Please check the handler provided to RxJavaPlugins.setOnMaybeSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            mo8992b((MaybeObserver) a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: b */
    public final Maybe<T> m10737b(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9205a(new MaybeSubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <E extends MaybeObserver<? super T>> E m10721c(E e) {
        mo10646a((MaybeObserver) e);
        return e;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: i */
    public final Maybe<T> m10669i(MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return RxJavaPlugins.m9205a(new MaybeSwitchIfEmpty(this, asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Single<T> m10736b(SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return RxJavaPlugins.m9200a(new MaybeSwitchIfEmptySingle(this, ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: j */
    public final <U> Maybe<T> m10665j(MaybeSource<U> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return RxJavaPlugins.m9205a(new MaybeTakeUntilMaybe(this, asiVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: h */
    public final <U> Maybe<T> m10671h(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return RxJavaPlugins.m9205a(new MaybeTakeUntilPublisher(this, dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: d */
    public final Maybe<T> m10707d(long j, TimeUnit timeUnit) {
        return m10706d(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @AbstractC3889atm
    /* renamed from: a */
    public final Maybe<T> m10802a(long j, TimeUnit timeUnit, MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "fallback is null");
        return m10800a(j, timeUnit, Schedulers.m9050a(), asiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Maybe<T> m10800a(long j, TimeUnit timeUnit, Scheduler astVar, MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "fallback is null");
        return m10692e(m10801a(j, timeUnit, astVar), asiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: d */
    public final Maybe<T> m10706d(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10662k(m10801a(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: k */
    public final <U> Maybe<T> m10662k(MaybeSource<U> asiVar) {
        ObjectHelper.m9873a(asiVar, "timeoutIndicator is null");
        return RxJavaPlugins.m9205a(new MaybeTimeoutMaybe(this, asiVar, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: e */
    public final <U> Maybe<T> m10692e(MaybeSource<U> asiVar, MaybeSource<? extends T> asiVar2) {
        ObjectHelper.m9873a(asiVar, "timeoutIndicator is null");
        ObjectHelper.m9873a(asiVar2, "fallback is null");
        return RxJavaPlugins.m9205a(new MaybeTimeoutMaybe(this, asiVar, asiVar2));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: i */
    public final <U> Maybe<T> m10667i(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "timeoutIndicator is null");
        return RxJavaPlugins.m9205a(new MaybeTimeoutPublisher(this, dbwVar, null));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U> Maybe<T> m10752a(Publisher<U> dbwVar, MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(dbwVar, "timeoutIndicator is null");
        ObjectHelper.m9873a(asiVar, "fallback is null");
        return RxJavaPlugins.m9205a(new MaybeTimeoutPublisher(this, dbwVar, asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: c */
    public final Maybe<T> m10716c(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9205a(new MaybeUnsubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <U, R> Maybe<R> m10770a(MaybeSource<? extends U> asiVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return m10772a(this, asiVar, auiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: r */
    public final TestObserver<T> m10650r() {
        TestObserver<T> btzVar = new TestObserver<>();
        mo10646a((MaybeObserver) btzVar);
        return btzVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final TestObserver<T> m10751a(boolean z) {
        TestObserver<T> btzVar = new TestObserver<>();
        if (z) {
            btzVar.m9274z();
        }
        mo10646a((MaybeObserver) btzVar);
        return btzVar;
    }
}
