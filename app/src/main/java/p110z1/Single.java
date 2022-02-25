package p110z1;

import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.asu */
/* loaded from: classes3.dex */
public abstract class Single<T> implements SingleSource<T> {
    /* renamed from: b */
    protected abstract void mo8961b(@AbstractC3889atm SingleObserver<? super T> asxVar);

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<T> m10150a(Iterable<? extends SingleSource<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9200a(new SingleAmb(null, iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Single<T> m10104a(SingleSource<? extends T>... ataVarArr) {
        if (ataVarArr.length == 0) {
            return m10096b((Callable<? extends Throwable>) SingleInternalHelper.m9545a());
        }
        if (ataVarArr.length == 1) {
            return m10072c((SingleSource) ataVarArr[0]);
        }
        return RxJavaPlugins.m9200a(new SingleAmb(ataVarArr, null));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10098b(Iterable<? extends SingleSource<? extends T>> iterable) {
        return m10107a((Publisher) Flowable.m10952e((Iterable) iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10136a(ObservableSource<? extends SingleSource<? extends T>> asqVar) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        return RxJavaPlugins.m9203a(new ObservableConcatMap(asqVar, SingleInternalHelper.m9542c(), 2, ErrorMode.IMMEDIATE));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10107a(Publisher<? extends SingleSource<? extends T>> dbwVar) {
        return m10106a(dbwVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10106a(Publisher<? extends SingleSource<? extends T>> dbwVar, int i) {
        ObjectHelper.m9873a(dbwVar, "sources is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapPublisher(dbwVar, SingleInternalHelper.m9543b(), i, ErrorMode.IMMEDIATE));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10129a(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        return m10107a((Publisher) Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10128a(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2, SingleSource<? extends T> ataVar3) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        return m10107a((Publisher) Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2, ataVar3}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m10127a(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2, SingleSource<? extends T> ataVar3, SingleSource<? extends T> ataVar4) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        return m10107a((Publisher) Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2, ataVar3, ataVar4}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10082b(SingleSource<? extends T>... ataVarArr) {
        return RxJavaPlugins.m9207a(new FlowableConcatMap(Flowable.m11098a((Object[]) ataVarArr), SingleInternalHelper.m9543b(), 2, ErrorMode.BOUNDARY));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10064c(SingleSource<? extends T>... ataVarArr) {
        return Flowable.m11098a((Object[]) ataVarArr).m10946e(SingleInternalHelper.m9543b());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10083b(Publisher<? extends SingleSource<? extends T>> dbwVar) {
        return Flowable.m10964d((Publisher) dbwVar).m10946e(SingleInternalHelper.m9543b());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10078c(Iterable<? extends SingleSource<? extends T>> iterable) {
        return Flowable.m10952e((Iterable) iterable).m10946e(SingleInternalHelper.m9543b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<T> m10132a(SingleOnSubscribe<T> asyVar) {
        ObjectHelper.m9873a(asyVar, "source is null");
        return RxJavaPlugins.m9200a(new SingleCreate(asyVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<T> m10145a(Callable<? extends SingleSource<? extends T>> callable) {
        ObjectHelper.m9873a(callable, "singleSupplier is null");
        return RxJavaPlugins.m9200a(new SingleDefer(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Single<T> m10096b(Callable<? extends Throwable> callable) {
        ObjectHelper.m9873a(callable, "errorSupplier is null");
        return RxJavaPlugins.m9200a(new SingleError(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<T> m10146a(Throwable th) {
        ObjectHelper.m9873a(th, "exception is null");
        return m10096b((Callable<? extends Throwable>) Functions.m9932a(th));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Single<T> m10076c(Callable<? extends T> callable) {
        ObjectHelper.m9873a(callable, "callable is null");
        return RxJavaPlugins.m9200a(new SingleFromCallable(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Single<T> m10142a(Future<? extends T> future) {
        return m10137a(Flowable.m11199a((Future) future));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Single<T> m10141a(Future<? extends T> future, long j, TimeUnit timeUnit) {
        return m10137a(Flowable.m11198a(future, j, timeUnit));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public static <T> Single<T> m10140a(Future<? extends T> future, long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10137a(Flowable.m11197a(future, j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public static <T> Single<T> m10139a(Future<? extends T> future, Scheduler astVar) {
        return m10137a(Flowable.m11196a((Future) future, astVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Single<T> m10065c(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "publisher is null");
        return RxJavaPlugins.m9200a(new SingleFromPublisher(dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Single<T> m10094b(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "observableSource is null");
        return RxJavaPlugins.m9200a(new ObservableSingleSingle(asqVar, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<T> m10148a(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return RxJavaPlugins.m9200a(new SingleJust(t));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public static <T> Flowable<T> m10060d(Iterable<? extends SingleSource<? extends T>> iterable) {
        return m10054d(Flowable.m10952e((Iterable) iterable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public static <T> Flowable<T> m10054d(Publisher<? extends SingleSource<? extends T>> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "sources is null");
        return RxJavaPlugins.m9207a(new FlowableFlatMapPublisher(dbwVar, SingleInternalHelper.m9543b(), false, Integer.MAX_VALUE, Flowable.m11274a()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<T> m10130a(SingleSource<? extends SingleSource<? extends T>> ataVar) {
        ObjectHelper.m9873a(ataVar, "source is null");
        return RxJavaPlugins.m9200a(new SingleFlatMap(ataVar, Functions.m9935a()));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10091b(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        return m10054d(Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10090b(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2, SingleSource<? extends T> ataVar3) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        return m10054d(Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2, ataVar3}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m10089b(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2, SingleSource<? extends T> ataVar3, SingleSource<? extends T> ataVar4) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        return m10054d(Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2, ataVar3, ataVar4}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public static <T> Flowable<T> m10052e(Iterable<? extends SingleSource<? extends T>> iterable) {
        return m10048e(Flowable.m10952e((Iterable) iterable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public static <T> Flowable<T> m10048e(Publisher<? extends SingleSource<? extends T>> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "sources is null");
        return RxJavaPlugins.m9207a(new FlowableFlatMapPublisher(dbwVar, SingleInternalHelper.m9543b(), true, Integer.MAX_VALUE, Flowable.m11274a()));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10071c(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        return m10048e(Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10070c(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2, SingleSource<? extends T> ataVar3) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        return m10048e(Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2, ataVar3}));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10069c(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2, SingleSource<? extends T> ataVar3, SingleSource<? extends T> ataVar4) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        return m10048e(Flowable.m11098a((Object[]) new SingleSource[]{ataVar, ataVar2, ataVar3, ataVar4}));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: q_ */
    public static <T> Single<T> m10022q_() {
        return RxJavaPlugins.m9200a(SingleNever.f19814a);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public static Single<Long> m10158a(long j, TimeUnit timeUnit) {
        return m10157a(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public static Single<Long> m10157a(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9200a(new SingleTimer(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public static <T> Single<Boolean> m10058d(SingleSource<? extends T> ataVar, SingleSource<? extends T> ataVar2) {
        ObjectHelper.m9873a(ataVar, "first is null");
        ObjectHelper.m9873a(ataVar2, "second is null");
        return RxJavaPlugins.m9200a(new SingleEquals(ataVar, ataVar2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Single<T> m10092b(SingleSource<T> ataVar) {
        ObjectHelper.m9873a(ataVar, "onSubscribe is null");
        if (!(ataVar instanceof Single)) {
            return RxJavaPlugins.m9200a(new SingleFromUnsafeSource(ataVar));
        }
        throw new IllegalArgumentException("unsafeCreate(Single) should be upgraded");
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, U> Single<T> m10144a(Callable<U> callable, Function<? super U, ? extends SingleSource<? extends T>> aunVar, Consumer<? super U> aumVar) {
        return m10143a((Callable) callable, (Function) aunVar, (Consumer) aumVar, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, U> Single<T> m10143a(Callable<U> callable, Function<? super U, ? extends SingleSource<? extends T>> aunVar, Consumer<? super U> aumVar, boolean z) {
        ObjectHelper.m9873a(callable, "resourceSupplier is null");
        ObjectHelper.m9873a(aunVar, "singleFunction is null");
        ObjectHelper.m9873a(aumVar, "disposer is null");
        return RxJavaPlugins.m9200a(new SingleUsing(callable, aunVar, aumVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Single<T> m10072c(SingleSource<T> ataVar) {
        ObjectHelper.m9873a(ataVar, "source is null");
        if (ataVar instanceof Single) {
            return RxJavaPlugins.m9200a((Single) ataVar);
        }
        return RxJavaPlugins.m9200a(new SingleFromUnsafeSource(ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Single<R> m10149a(Iterable<? extends SingleSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9200a(new SingleZipIterable(iterable, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, R> Single<R> m10119a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        return m10109a(Functions.m9927a((BiFunction) auiVar), ataVar, ataVar2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, R> Single<R> m10120a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, SingleSource<? extends T3> ataVar3, Function3<? super T1, ? super T2, ? super T3, ? extends R> auoVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        return m10109a(Functions.m9921a((Function3) auoVar), ataVar, ataVar2, ataVar3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, R> Single<R> m10121a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, SingleSource<? extends T3> ataVar3, SingleSource<? extends T4> ataVar4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> aupVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        return m10109a(Functions.m9920a((Function4) aupVar), ataVar, ataVar2, ataVar3, ataVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, R> Single<R> m10122a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, SingleSource<? extends T3> ataVar3, SingleSource<? extends T4> ataVar4, SingleSource<? extends T5> ataVar5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> auqVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        ObjectHelper.m9873a(ataVar5, "source5 is null");
        return m10109a(Functions.m9919a((Function5) auqVar), ataVar, ataVar2, ataVar3, ataVar4, ataVar5);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, R> Single<R> m10123a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, SingleSource<? extends T3> ataVar3, SingleSource<? extends T4> ataVar4, SingleSource<? extends T5> ataVar5, SingleSource<? extends T6> ataVar6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> aurVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        ObjectHelper.m9873a(ataVar5, "source5 is null");
        ObjectHelper.m9873a(ataVar6, "source6 is null");
        return m10109a(Functions.m9918a((Function6) aurVar), ataVar, ataVar2, ataVar3, ataVar4, ataVar5, ataVar6);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> m10124a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, SingleSource<? extends T3> ataVar3, SingleSource<? extends T4> ataVar4, SingleSource<? extends T5> ataVar5, SingleSource<? extends T6> ataVar6, SingleSource<? extends T7> ataVar7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> ausVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        ObjectHelper.m9873a(ataVar5, "source5 is null");
        ObjectHelper.m9873a(ataVar6, "source6 is null");
        ObjectHelper.m9873a(ataVar7, "source7 is null");
        return m10109a(Functions.m9917a((Function7) ausVar), ataVar, ataVar2, ataVar3, ataVar4, ataVar5, ataVar6, ataVar7);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> m10125a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, SingleSource<? extends T3> ataVar3, SingleSource<? extends T4> ataVar4, SingleSource<? extends T5> ataVar5, SingleSource<? extends T6> ataVar6, SingleSource<? extends T7> ataVar7, SingleSource<? extends T8> ataVar8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> autVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        ObjectHelper.m9873a(ataVar5, "source5 is null");
        ObjectHelper.m9873a(ataVar6, "source6 is null");
        ObjectHelper.m9873a(ataVar7, "source7 is null");
        ObjectHelper.m9873a(ataVar8, "source8 is null");
        return m10109a(Functions.m9916a((Function8) autVar), ataVar, ataVar2, ataVar3, ataVar4, ataVar5, ataVar6, ataVar7, ataVar8);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> m10126a(SingleSource<? extends T1> ataVar, SingleSource<? extends T2> ataVar2, SingleSource<? extends T3> ataVar3, SingleSource<? extends T4> ataVar4, SingleSource<? extends T5> ataVar5, SingleSource<? extends T6> ataVar6, SingleSource<? extends T7> ataVar7, SingleSource<? extends T8> ataVar8, SingleSource<? extends T9> ataVar9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> auuVar) {
        ObjectHelper.m9873a(ataVar, "source1 is null");
        ObjectHelper.m9873a(ataVar2, "source2 is null");
        ObjectHelper.m9873a(ataVar3, "source3 is null");
        ObjectHelper.m9873a(ataVar4, "source4 is null");
        ObjectHelper.m9873a(ataVar5, "source5 is null");
        ObjectHelper.m9873a(ataVar6, "source6 is null");
        ObjectHelper.m9873a(ataVar7, "source7 is null");
        ObjectHelper.m9873a(ataVar8, "source8 is null");
        ObjectHelper.m9873a(ataVar9, "source9 is null");
        return m10109a(Functions.m9915a((Function9) auuVar), ataVar, ataVar2, ataVar3, ataVar4, ataVar5, ataVar6, ataVar7, ataVar8, ataVar9);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Single<R> m10109a(Function<? super Object[], ? extends R> aunVar, SingleSource<? extends T>... ataVarArr) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(ataVarArr, "sources is null");
        if (ataVarArr.length == 0) {
            return m10146a((Throwable) new NoSuchElementException());
        }
        return RxJavaPlugins.m9200a(new SingleZipArray(ataVarArr, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final Single<T> m10059d(SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return m10104a(this, ataVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> R m10133a(@AbstractC3889atm SingleConverter<T, ? extends R> asvVar) {
        return (R) ((SingleConverter) ObjectHelper.m9873a(asvVar, "converter is null")).m10021a(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Single<T> m10103b() {
        return RxJavaPlugins.m9200a(new SingleHide(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Single<R> m10117a(SingleTransformer<? super T, ? extends R> atbVar) {
        return m10072c(((SingleTransformer) ObjectHelper.m9873a(atbVar, "transformer is null")).m10017a(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Single<T> m10081c() {
        return RxJavaPlugins.m9200a(new SingleCache(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <U> Single<U> m10151a(Class<? extends U> cls) {
        ObjectHelper.m9873a(cls, "clazz is null");
        return (Single<U>) m10035i(Functions.m9933a((Class) cls));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final Flowable<T> m10051e(SingleSource<? extends T> ataVar) {
        return m10129a(this, ataVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: b */
    public final Single<T> m10101b(long j, TimeUnit timeUnit) {
        return m10155a(j, timeUnit, Schedulers.m9050a(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final Single<T> m10153a(long j, TimeUnit timeUnit, boolean z) {
        return m10155a(j, timeUnit, Schedulers.m9050a(), z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public final Single<T> m10100b(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10155a(j, timeUnit, astVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10155a(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9200a(new SingleDelay(this, j, timeUnit, astVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10138a(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return RxJavaPlugins.m9200a(new SingleDelayWithCompletable(this, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: f */
    public final <U> Single<T> m10046f(SingleSource<U> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return RxJavaPlugins.m9200a(new SingleDelayWithSingle(this, ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final <U> Single<T> m10075c(ObservableSource<U> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return RxJavaPlugins.m9200a(new SingleDelayWithObservable(this, asqVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final <U> Single<T> m10044f(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return RxJavaPlugins.m9200a(new SingleDelayWithPublisher(this, dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: c */
    public final Single<T> m10080c(long j, TimeUnit timeUnit) {
        return m10079c(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: c */
    public final Single<T> m10079c(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10075c((ObservableSource) Observable.m10431b(j, timeUnit, astVar));
    }

    @SchedulerSupport(m10000a = "none")
    @CheckReturnValue
    @Experimental
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Maybe<R> m10110a(Function<? super T, Notification<R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        return RxJavaPlugins.m9205a(new SingleDematerialize(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10112a(Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onAfterSuccess is null");
        return RxJavaPlugins.m9200a(new SingleDoAfterSuccess(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10116a(Action augVar) {
        ObjectHelper.m9873a(augVar, "onAfterTerminate is null");
        return RxJavaPlugins.m9200a(new SingleDoAfterTerminate(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Single<T> m10088b(Action augVar) {
        ObjectHelper.m9873a(augVar, "onFinally is null");
        return RxJavaPlugins.m9200a(new SingleDoFinally(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Single<T> m10086b(Consumer<? super Disposable> aumVar) {
        ObjectHelper.m9873a(aumVar, "onSubscribe is null");
        return RxJavaPlugins.m9200a(new SingleDoOnSubscribe(this, aumVar));
    }

    @SchedulerSupport(m10000a = "none")
    @CheckReturnValue
    @Experimental
    @AbstractC3889atm
    /* renamed from: c */
    public final Single<T> m10068c(Action augVar) {
        ObjectHelper.m9873a(augVar, "onTerminate is null");
        return RxJavaPlugins.m9200a(new SingleDoOnTerminate(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final Single<T> m10067c(Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onSuccess is null");
        return RxJavaPlugins.m9200a(new SingleDoOnSuccess(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10115a(BiConsumer<? super T, ? super Throwable> auhVar) {
        ObjectHelper.m9873a(auhVar, "onEvent is null");
        return RxJavaPlugins.m9200a(new SingleDoOnEvent(this, auhVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final Single<T> m10056d(Consumer<? super Throwable> aumVar) {
        ObjectHelper.m9873a(aumVar, "onError is null");
        return RxJavaPlugins.m9200a(new SingleDoOnError(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final Single<T> m10057d(Action augVar) {
        ObjectHelper.m9873a(augVar, "onDispose is null");
        return RxJavaPlugins.m9200a(new SingleDoOnDispose(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Maybe<T> m10108a(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9205a(new MaybeFilterSingle(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final <R> Single<R> m10085b(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9200a(new SingleFlatMap(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final <R> Maybe<R> m10066c(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9205a(new SingleFlatMapMaybe(this, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public final <R> Flowable<R> m10055d(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new SingleFlatMapPublisher(this, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public final <U> Flowable<U> m10049e(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new SingleFlatMapIterableFlowable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: f */
    public final <U> Observable<U> m10045f(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new SingleFlatMapIterableObservable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: g */
    public final <R> Observable<R> m10041g(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new SingleFlatMapObservable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: h */
    public final Completable m10037h(Function<? super T, ? extends CompletableSource> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9209a(new SingleFlatMapCompletable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final T m10063d() {
        BlockingMultiObserver aweVar = new BlockingMultiObserver();
        mo10018a((SingleObserver) aweVar);
        return (T) aweVar.m9846b();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Single<R> m10131a(SingleOperator<? extends R, ? super T> aszVar) {
        ObjectHelper.m9873a(aszVar, "lift is null");
        return RxJavaPlugins.m9200a(new SingleLift(this, aszVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: i */
    public final <R> Single<R> m10035i(Function<? super T, ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9200a(new SingleMap(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @Experimental
    /* renamed from: e */
    public final Single<Notification<T>> m10053e() {
        return RxJavaPlugins.m9200a(new SingleMaterialize(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Single<Boolean> m10097b(Object obj) {
        return m10147a(obj, ObjectHelper.m9880a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<Boolean> m10147a(Object obj, BiPredicate<Object, Object> aujVar) {
        ObjectHelper.m9873a(obj, "value is null");
        ObjectHelper.m9873a(aujVar, "comparer is null");
        return RxJavaPlugins.m9200a(new SingleContains(this, obj, aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public final Flowable<T> m10042g(SingleSource<? extends T> ataVar) {
        return m10091b(this, ataVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10135a(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9200a(new SingleObserveOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: j */
    public final Single<T> m10033j(Function<Throwable, ? extends T> aunVar) {
        ObjectHelper.m9873a(aunVar, "resumeFunction is null");
        return RxJavaPlugins.m9200a(new SingleOnErrorReturn(this, aunVar, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final Single<T> m10077c(T t) {
        ObjectHelper.m9873a((Object) t, "value is null");
        return RxJavaPlugins.m9200a(new SingleOnErrorReturn(this, null, t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10134a(Single<? extends T> asuVar) {
        ObjectHelper.m9873a(asuVar, "resumeSingleInCaseOfError is null");
        return m10031k(Functions.m9911b(asuVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: k */
    public final Single<T> m10031k(Function<? super Throwable, ? extends SingleSource<? extends T>> aunVar) {
        ObjectHelper.m9873a(aunVar, "resumeFunctionInCaseOfError is null");
        return RxJavaPlugins.m9200a(new SingleResumeNext(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Single<T> m10047f() {
        return RxJavaPlugins.m9200a(new SingleDetach(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public final Flowable<T> m10043g() {
        return m10030l().m11299E();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m10159a(long j) {
        return m10030l().m10983d(j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: l */
    public final Flowable<T> m10029l(Function<? super Flowable<Object>, ? extends Publisher<?>> aunVar) {
        return m10030l().m10808z(aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m10113a(BooleanSupplier aukVar) {
        return m10030l().m11177a(aukVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Single<T> m10039h() {
        return m10137a((Flowable) m10030l().m11295G());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Single<T> m10102b(long j) {
        return m10137a((Flowable) m10030l().m10957e(j));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Single<T> m10114a(BiPredicate<? super Integer, ? super Throwable> aujVar) {
        return m10137a((Flowable) m10030l().m11060b(aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Single<T> m10152a(long j, Predicate<? super Throwable> auxVar) {
        return m10137a((Flowable) m10030l().m11233a(j, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Single<T> m10084b(Predicate<? super Throwable> auxVar) {
        return m10137a((Flowable) m10030l().m10942e(auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: m */
    public final Single<T> m10027m(Function<? super Flowable<Throwable>, ? extends Publisher<?>> aunVar) {
        return m10137a((Flowable) m10030l().m11304B(aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final Disposable m10036i() {
        return m10111a(Functions.m9914b(), Functions.f17560f);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Disposable m10087b(BiConsumer<? super T, ? super Throwable> auhVar) {
        ObjectHelper.m9873a(auhVar, "onCallback is null");
        BiConsumerSingleObserver awaVar = new BiConsumerSingleObserver(auhVar);
        mo10018a((SingleObserver) awaVar);
        return awaVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Disposable m10050e(Consumer<? super T> aumVar) {
        return m10111a(aumVar, Functions.f17560f);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Disposable m10111a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2) {
        ObjectHelper.m9873a(aumVar, "onSuccess is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ConsumerSingleObserver awhVar = new ConsumerSingleObserver(aumVar, aumVar2);
        mo10018a((SingleObserver) awhVar);
        return awhVar;
    }

    @Override // p110z1.SingleSource
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final void mo10018a(SingleObserver<? super T> asxVar) {
        ObjectHelper.m9873a(asxVar, "observer is null");
        SingleObserver<? super T> a = RxJavaPlugins.m9199a(this, asxVar);
        ObjectHelper.m9873a(a, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            mo8961b((SingleObserver) a);
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
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <E extends SingleObserver<? super T>> E m10073c(E e) {
        mo10018a((SingleObserver) e);
        return e;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: b */
    public final Single<T> m10093b(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9200a(new SingleSubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Single<T> m10095b(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return m10040g(new CompletableToFlowable(arsVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: g */
    public final <E> Single<T> m10040g(Publisher<E> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return RxJavaPlugins.m9200a(new SingleTakeUntil(this, dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: h */
    public final <E> Single<T> m10038h(SingleSource<? extends E> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return m10040g(new SingleToFlowable(ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: d */
    public final Single<T> m10062d(long j, TimeUnit timeUnit) {
        return m10099b(j, timeUnit, Schedulers.m9050a(), (SingleSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: d */
    public final Single<T> m10061d(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10099b(j, timeUnit, astVar, (SingleSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10156a(long j, TimeUnit timeUnit, Scheduler astVar, SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return m10099b(j, timeUnit, astVar, ataVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m10154a(long j, TimeUnit timeUnit, SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return m10099b(j, timeUnit, Schedulers.m9050a(), ataVar);
    }

    /* renamed from: b */
    private Single<T> m10099b(long j, TimeUnit timeUnit, Scheduler astVar, SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9200a(new SingleTimeout(this, j, timeUnit, astVar, ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: n */
    public final <R> R m10025n(Function<? super Single<T>, R> aunVar) {
        try {
            return (R) ((Function) ObjectHelper.m9873a(aunVar, "convert is null")).apply(this);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @Deprecated
    /* renamed from: j */
    public final Completable m10034j() {
        return RxJavaPlugins.m9209a(new CompletableFromSingle(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final Completable m10032k() {
        return RxJavaPlugins.m9209a(new CompletableFromSingle(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: l */
    public final Flowable<T> m10030l() {
        if (this instanceof FuseToFlowable) {
            return ((FuseToFlowable) this).mo9727r_();
        }
        return RxJavaPlugins.m9207a(new SingleToFlowable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: m */
    public final Future<T> m10028m() {
        return (Future) m10073c((Single<T>) new FutureSingleObserver());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: n */
    public final Maybe<T> m10026n() {
        if (this instanceof FuseToMaybe) {
            return ((FuseToMaybe) this).mo9694v_();
        }
        return RxJavaPlugins.m9205a(new MaybeFromSingle(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: o */
    public final Observable<T> m10024o() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).mo9572w_();
        }
        return RxJavaPlugins.m9203a(new SingleToObservable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: c */
    public final Single<T> m10074c(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9200a(new SingleUnsubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Single<R> m10118a(SingleSource<U> ataVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        return m10119a(this, ataVar, auiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: p */
    public final TestObserver<T> m10023p() {
        TestObserver<T> btzVar = new TestObserver<>();
        mo10018a((SingleObserver) btzVar);
        return btzVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final TestObserver<T> m10105a(boolean z) {
        TestObserver<T> btzVar = new TestObserver<>();
        if (z) {
            btzVar.m9274z();
        }
        mo10018a((SingleObserver) btzVar);
        return btzVar;
    }

    /* renamed from: a */
    private static <T> Single<T> m10137a(Flowable<T> arvVar) {
        return RxJavaPlugins.m9200a(new FlowableSingleSingle(arvVar, null));
    }
}
