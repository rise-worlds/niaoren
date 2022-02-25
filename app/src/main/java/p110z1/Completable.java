package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.arm */
/* loaded from: classes3.dex */
public abstract class Completable implements CompletableSource {
    /* renamed from: b */
    protected abstract void mo9001b(CompletableObserver arpVar);

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11380a(CompletableSource... arsVarArr) {
        ObjectHelper.m9873a(arsVarArr, "sources is null");
        if (arsVarArr.length == 0) {
            return m11418a();
        }
        if (arsVarArr.length == 1) {
            return m11371b(arsVarArr[0]);
        }
        return RxJavaPlugins.m9209a(new CompletableAmb(arsVarArr, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11410a(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9209a(new CompletableAmb(null, iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11418a() {
        return RxJavaPlugins.m9209a(CompletableEmpty.f17719a);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static Completable m11360b(CompletableSource... arsVarArr) {
        ObjectHelper.m9873a(arsVarArr, "sources is null");
        if (arsVarArr.length == 0) {
            return m11418a();
        }
        if (arsVarArr.length == 1) {
            return m11371b(arsVarArr[0]);
        }
        return RxJavaPlugins.m9209a(new CompletableConcatArray(arsVarArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static Completable m11374b(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9209a(new CompletableConcatIterable(iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static Completable m11384a(Publisher<? extends CompletableSource> dbwVar) {
        return m11383a(dbwVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11383a(Publisher<? extends CompletableSource> dbwVar, int i) {
        ObjectHelper.m9873a(dbwVar, "sources is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9209a(new CompletableConcat(dbwVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11401a(CompletableOnSubscribe arqVar) {
        ObjectHelper.m9873a(arqVar, "source is null");
        return RxJavaPlugins.m9209a(new CompletableCreate(arqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11399a(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "source is null");
        if (!(arsVar instanceof Completable)) {
            return RxJavaPlugins.m9209a(new CompletableFromUnsafeSource(arsVar));
        }
        throw new IllegalArgumentException("Use of unsafeCreate(Completable)!");
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11406a(Callable<? extends CompletableSource> callable) {
        ObjectHelper.m9873a(callable, "completableSupplier");
        return RxJavaPlugins.m9209a(new CompletableDefer(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static Completable m11372b(Callable<? extends Throwable> callable) {
        ObjectHelper.m9873a(callable, "errorSupplier is null");
        return RxJavaPlugins.m9209a(new CompletableErrorSupplier(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11407a(Throwable th) {
        ObjectHelper.m9873a(th, "error is null");
        return RxJavaPlugins.m9209a(new CompletableError(th));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11392a(Action augVar) {
        ObjectHelper.m9873a(augVar, "run is null");
        return RxJavaPlugins.m9209a(new CompletableFromAction(augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static Completable m11355c(Callable<?> callable) {
        ObjectHelper.m9873a(callable, "callable is null");
        return RxJavaPlugins.m9209a(new CompletableFromCallable(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11403a(Future<?> future) {
        ObjectHelper.m9873a(future, "future is null");
        return m11392a(Functions.m9930a(future));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Completable m11397a(MaybeSource<T> asiVar) {
        ObjectHelper.m9873a(asiVar, "maybe is null");
        return RxJavaPlugins.m9209a(new MaybeIgnoreElementCompletable(asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11408a(Runnable runnable) {
        ObjectHelper.m9873a(runnable, "run is null");
        return RxJavaPlugins.m9209a(new CompletableFromRunnable(runnable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Completable m11395a(ObservableSource<T> asqVar) {
        ObjectHelper.m9873a(asqVar, "observable is null");
        return RxJavaPlugins.m9209a(new CompletableFromObservable(asqVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Completable m11362b(Publisher<T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "publisher is null");
        return RxJavaPlugins.m9209a(new CompletableFromPublisher(dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Completable m11393a(SingleSource<T> ataVar) {
        ObjectHelper.m9873a(ataVar, "single is null");
        return RxJavaPlugins.m9209a(new CompletableFromSingle(ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static Completable m11346c(CompletableSource... arsVarArr) {
        ObjectHelper.m9873a(arsVarArr, "sources is null");
        if (arsVarArr.length == 0) {
            return m11418a();
        }
        if (arsVarArr.length == 1) {
            return m11371b(arsVarArr[0]);
        }
        return RxJavaPlugins.m9209a(new CompletableMergeArray(arsVarArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static Completable m11356c(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9209a(new CompletableMergeIterable(iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: c */
    public static Completable m11348c(Publisher<? extends CompletableSource> dbwVar) {
        return m11382a(dbwVar, Integer.MAX_VALUE, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static Completable m11361b(Publisher<? extends CompletableSource> dbwVar, int i) {
        return m11382a(dbwVar, i, false);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    private static Completable m11382a(Publisher<? extends CompletableSource> dbwVar, int i, boolean z) {
        ObjectHelper.m9873a(dbwVar, "sources is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9209a(new CompletableMerge(dbwVar, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public static Completable m11336d(CompletableSource... arsVarArr) {
        ObjectHelper.m9873a(arsVarArr, "sources is null");
        return RxJavaPlugins.m9209a(new CompletableMergeDelayErrorArray(arsVarArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public static Completable m11342d(Iterable<? extends CompletableSource> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9209a(new CompletableMergeDelayErrorIterable(iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: d */
    public static Completable m11337d(Publisher<? extends CompletableSource> dbwVar) {
        return m11382a(dbwVar, Integer.MAX_VALUE, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static Completable m11347c(Publisher<? extends CompletableSource> dbwVar, int i) {
        return m11382a(dbwVar, i, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static Completable m11379b() {
        return RxJavaPlugins.m9209a(CompletableNever.f17760a);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public static Completable m11416a(long j, TimeUnit timeUnit) {
        return m11414a(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public static Completable m11414a(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9209a(new CompletableTimer(j, timeUnit, astVar));
    }

    /* renamed from: b */
    private static NullPointerException m11373b(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <R> Completable m11405a(Callable<R> callable, Function<? super R, ? extends CompletableSource> aunVar, Consumer<? super R> aumVar) {
        return m11404a((Callable) callable, (Function) aunVar, (Consumer) aumVar, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <R> Completable m11404a(Callable<R> callable, Function<? super R, ? extends CompletableSource> aunVar, Consumer<? super R> aumVar, boolean z) {
        ObjectHelper.m9873a(callable, "resourceSupplier is null");
        ObjectHelper.m9873a(aunVar, "completableFunction is null");
        ObjectHelper.m9873a(aumVar, "disposer is null");
        return RxJavaPlugins.m9209a(new CompletableUsing(callable, aunVar, aumVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static Completable m11371b(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "source is null");
        if (arsVar instanceof Completable) {
            return RxJavaPlugins.m9209a((Completable) arsVar);
        }
        return RxJavaPlugins.m9209a(new CompletableFromUnsafeSource(arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public final Completable m11353c(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return m11380a(this, arsVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final <T> Observable<T> m11369b(ObservableSource<T> asqVar) {
        ObjectHelper.m9873a(asqVar, "next is null");
        return RxJavaPlugins.m9203a(new CompletableAndThenObservable(this, asqVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public final <T> Flowable<T> m11331e(Publisher<T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "next is null");
        return RxJavaPlugins.m9207a(new CompletableAndThenPublisher(this, dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final <T> Single<T> m11367b(SingleSource<T> ataVar) {
        ObjectHelper.m9873a(ataVar, "next is null");
        return RxJavaPlugins.m9200a(new SingleDelayWithCompletable(ataVar, this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final <T> Maybe<T> m11370b(MaybeSource<T> asiVar) {
        ObjectHelper.m9873a(asiVar, "next is null");
        return RxJavaPlugins.m9205a(new MaybeDelayWithCompletable(asiVar, this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Completable m11340d(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "next is null");
        return RxJavaPlugins.m9209a(new CompletableAndThenCompletable(this, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> R m11402a(@AbstractC3889atm CompletableConverter<? extends R> arnVar) {
        return (R) ((CompletableConverter) ObjectHelper.m9873a(arnVar, "converter is null")).m11312a(this);
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final void m11359c() {
        BlockingMultiObserver aweVar = new BlockingMultiObserver();
        mo11309a((CompletableObserver) aweVar);
        aweVar.m9846b();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final boolean m11377b(long j, TimeUnit timeUnit) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        BlockingMultiObserver aweVar = new BlockingMultiObserver();
        mo11309a((CompletableObserver) aweVar);
        return aweVar.m9845b(j, timeUnit);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @atn
    /* renamed from: d */
    public final Throwable m11345d() {
        BlockingMultiObserver aweVar = new BlockingMultiObserver();
        mo11309a((CompletableObserver) aweVar);
        return aweVar.m9844c();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @atn
    /* renamed from: c */
    public final Throwable m11358c(long j, TimeUnit timeUnit) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        BlockingMultiObserver aweVar = new BlockingMultiObserver();
        mo11309a((CompletableObserver) aweVar);
        return aweVar.m9848a(j, timeUnit);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Completable m11335e() {
        return RxJavaPlugins.m9209a(new CompletableCache(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Completable m11398a(CompletableTransformer artVar) {
        return m11371b(((CompletableTransformer) ObjectHelper.m9873a(artVar, "transformer is null")).m11308a(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: e */
    public final Completable m11333e(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return RxJavaPlugins.m9209a(new CompletableAndThenCompletable(this, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: d */
    public final Completable m11344d(long j, TimeUnit timeUnit) {
        return m11412a(j, timeUnit, Schedulers.m9050a(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public final Completable m11376b(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11412a(j, timeUnit, astVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11412a(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9209a(new CompletableDelay(this, j, timeUnit, astVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @Experimental
    /* renamed from: e */
    public final Completable m11334e(long j, TimeUnit timeUnit) {
        return m11357c(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @Experimental
    /* renamed from: c */
    public final Completable m11357c(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11414a(j, timeUnit, astVar).m11340d(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Completable m11366b(Action augVar) {
        return m11387a(Functions.m9914b(), Functions.m9914b(), augVar, Functions.f17557c, Functions.f17557c, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Completable m11351c(Action augVar) {
        return m11387a(Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.f17557c, augVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Completable m11388a(Consumer<? super Throwable> aumVar) {
        return m11387a(Functions.m9914b(), aumVar, Functions.f17557c, Functions.f17557c, Functions.f17557c, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public final Completable m11365b(Consumer<? super Throwable> aumVar) {
        ObjectHelper.m9873a(aumVar, "onEvent is null");
        return RxJavaPlugins.m9209a(new CompletableDoOnEvent(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    private Completable m11387a(Consumer<? super Disposable> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2, Action augVar3, Action augVar4) {
        ObjectHelper.m9873a(aumVar, "onSubscribe is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ObjectHelper.m9873a(augVar2, "onTerminate is null");
        ObjectHelper.m9873a(augVar3, "onAfterTerminate is null");
        ObjectHelper.m9873a(augVar4, "onDispose is null");
        return RxJavaPlugins.m9209a(new CompletablePeek(this, aumVar, aumVar2, augVar, augVar2, augVar3, augVar4));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Completable m11350c(Consumer<? super Disposable> aumVar) {
        return m11387a(aumVar, Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.f17557c, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Completable m11339d(Action augVar) {
        return m11387a(Functions.m9914b(), Functions.m9914b(), Functions.f17557c, augVar, Functions.f17557c, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Completable m11332e(Action augVar) {
        return m11387a(Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, augVar, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: f */
    public final Completable m11327f(Action augVar) {
        ObjectHelper.m9873a(augVar, "onFinally is null");
        return RxJavaPlugins.m9209a(new CompletableDoFinally(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11400a(CompletableOperator arrVar) {
        ObjectHelper.m9873a(arrVar, "onLift is null");
        return RxJavaPlugins.m9209a(new CompletableLift(this, arrVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @Experimental
    /* renamed from: f */
    public final <T> Single<Notification<T>> m11330f() {
        return RxJavaPlugins.m9200a(new CompletableMaterialize(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: f */
    public final Completable m11328f(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return m11346c(this, arsVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11394a(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9209a(new CompletableObserveOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final Completable m11325g() {
        return m11385a(Functions.m9909c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11385a(Predicate<? super Throwable> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9209a(new CompletableOnErrorComplete(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11386a(Function<? super Throwable, ? extends CompletableSource> aunVar) {
        ObjectHelper.m9873a(aunVar, "errorMapper is null");
        return RxJavaPlugins.m9209a(new CompletableResumeNext(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Completable m11322h() {
        return RxJavaPlugins.m9209a(new CompletableDetach(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final Completable m11320i() {
        return m11362b(m11316m().m11299E());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Completable m11417a(long j) {
        return m11362b(m11316m().m10983d(j));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Completable m11389a(BooleanSupplier aukVar) {
        return m11362b(m11316m().m11177a(aukVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Completable m11364b(Function<? super Flowable<Object>, ? extends Publisher<?>> aunVar) {
        return m11362b(m11316m().m10808z(aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: j */
    public final Completable m11319j() {
        return m11362b(m11316m().m11295G());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Completable m11390a(BiPredicate<? super Integer, ? super Throwable> aujVar) {
        return m11362b(m11316m().m11060b(aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Completable m11378b(long j) {
        return m11362b(m11316m().m10957e(j));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Completable m11411a(long j, Predicate<? super Throwable> auxVar) {
        return m11362b(m11316m().m11233a(j, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Completable m11363b(Predicate<? super Throwable> auxVar) {
        return m11362b(m11316m().m10942e(auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Completable m11349c(Function<? super Flowable<Throwable>, ? extends Publisher<?>> aunVar) {
        return m11362b(m11316m().m11304B(aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: g */
    public final Completable m11324g(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return m11360b(arsVar, this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <T> Observable<T> m11396a(Observable<T> aslVar) {
        ObjectHelper.m9873a(aslVar, "other is null");
        return aslVar.m10217l((ObservableSource) m11314o());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final <T> Flowable<T> m11326f(Publisher<T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11316m().m10825s(dbwVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final Completable m11318k() {
        return RxJavaPlugins.m9209a(new CompletableHide(this));
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: l */
    public final Disposable m11317l() {
        EmptyCompletableObserver awlVar = new EmptyCompletableObserver();
        mo11309a((CompletableObserver) awlVar);
        return awlVar;
    }

    @Override // p110z1.CompletableSource
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final void mo11309a(CompletableObserver arpVar) {
        ObjectHelper.m9873a(arpVar, "observer is null");
        try {
            CompletableObserver a = RxJavaPlugins.m9208a(this, arpVar);
            ObjectHelper.m9873a(a, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            mo9001b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            RxJavaPlugins.m9212a(th);
            throw m11373b(th);
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <E extends CompletableObserver> E m11354c(E e) {
        mo11309a((CompletableObserver) e);
        return e;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final Disposable m11391a(Action augVar, Consumer<? super Throwable> aumVar) {
        ObjectHelper.m9873a(aumVar, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        CallbackCompletableObserver awgVar = new CallbackCompletableObserver(aumVar, augVar);
        mo11309a((CompletableObserver) awgVar);
        return awgVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: g */
    public final Disposable m11323g(Action augVar) {
        ObjectHelper.m9873a(augVar, "onComplete is null");
        CallbackCompletableObserver awgVar = new CallbackCompletableObserver(augVar);
        mo11309a((CompletableObserver) awgVar);
        return awgVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: b */
    public final Completable m11368b(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9209a(new CompletableSubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: h */
    public final Completable m11321h(CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return RxJavaPlugins.m9209a(new CompletableTakeUntilCompletable(this, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: f */
    public final Completable m11329f(long j, TimeUnit timeUnit) {
        return m11375b(j, timeUnit, Schedulers.m9050a(), null);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11415a(long j, TimeUnit timeUnit, CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return m11375b(j, timeUnit, Schedulers.m9050a(), arsVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: d */
    public final Completable m11343d(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11375b(j, timeUnit, astVar, null);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11413a(long j, TimeUnit timeUnit, Scheduler astVar, CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return m11375b(j, timeUnit, astVar, arsVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: b */
    private Completable m11375b(long j, TimeUnit timeUnit, Scheduler astVar, CompletableSource arsVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9209a(new CompletableTimeout(this, j, timeUnit, astVar, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final <U> U m11338d(Function<? super Completable, U> aunVar) {
        try {
            return (U) ((Function) ObjectHelper.m9873a(aunVar, "converter is null")).apply(this);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: m */
    public final <T> Flowable<T> m11316m() {
        if (this instanceof FuseToFlowable) {
            return ((FuseToFlowable) this).mo9727r_();
        }
        return RxJavaPlugins.m9207a(new CompletableToFlowable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: n */
    public final <T> Maybe<T> m11315n() {
        if (this instanceof FuseToMaybe) {
            return ((FuseToMaybe) this).mo9694v_();
        }
        return RxJavaPlugins.m9205a(new MaybeFromCompletable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: o */
    public final <T> Observable<T> m11314o() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).mo9572w_();
        }
        return RxJavaPlugins.m9203a(new CompletableToObservable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: d */
    public final <T> Single<T> m11341d(Callable<? extends T> callable) {
        ObjectHelper.m9873a(callable, "completionValueSupplier is null");
        return RxJavaPlugins.m9200a(new CompletableToSingle(this, callable, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public final <T> Single<T> m11409a(T t) {
        ObjectHelper.m9873a((Object) t, "completionValue is null");
        return RxJavaPlugins.m9200a(new CompletableToSingle(this, null, t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: c */
    public final Completable m11352c(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9209a(new CompletableDisposeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: p */
    public final TestObserver<Void> m11313p() {
        TestObserver<Void> btzVar = new TestObserver<>();
        mo11309a((CompletableObserver) btzVar);
        return btzVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final TestObserver<Void> m11381a(boolean z) {
        TestObserver<Void> btzVar = new TestObserver<>();
        if (z) {
            btzVar.m9274z();
        }
        mo11309a((CompletableObserver) btzVar);
        return btzVar;
    }
}
