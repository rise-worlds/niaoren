package p110z1;

import com.stripe.android.RequestOptions;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: z1.bub */
/* loaded from: classes3.dex */
public abstract class ParallelFlowable<T> {
    /* renamed from: a */
    public abstract int mo9267a();

    /* renamed from: a */
    public abstract void mo9236a(@AbstractC3889atm Subscriber<? super T>[] dbxVarArr);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean m9227b(@AbstractC3889atm Subscriber<?>[] dbxVarArr) {
        int a = mo9267a();
        if (dbxVarArr.length == a) {
            return true;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("parallelism = " + a + ", subscribers = " + dbxVarArr.length);
        for (Subscriber<?> dbxVar : dbxVarArr) {
            EmptySubscription.error(illegalArgumentException, dbxVar);
        }
        return false;
    }

    @CheckReturnValue
    /* renamed from: a */
    public static <T> ParallelFlowable<T> m9240a(@AbstractC3889atm Publisher<? extends T> dbwVar) {
        return m9238a(dbwVar, Runtime.getRuntime().availableProcessors(), Flowable.m11274a());
    }

    @CheckReturnValue
    /* renamed from: a */
    public static <T> ParallelFlowable<T> m9239a(@AbstractC3889atm Publisher<? extends T> dbwVar, int i) {
        return m9238a(dbwVar, i, Flowable.m11274a());
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> ParallelFlowable<T> m9238a(@AbstractC3889atm Publisher<? extends T> dbwVar, int i, int i2) {
        ObjectHelper.m9873a(dbwVar, RequestOptions.f12301a);
        ObjectHelper.m9878a(i, "parallelism");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9189a(new ParallelFromPublisher(dbwVar, i, i2));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> R m9242a(@AbstractC3889atm ParallelFlowableConverter<T, R> bucVar) {
        return (R) ((ParallelFlowableConverter) ObjectHelper.m9873a(bucVar, "converter is null")).m9220a(this);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9254a(@AbstractC3889atm Function<? super T, ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper");
        return RxJavaPlugins.m9189a(new ParallelMap(this, aunVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9250a(@AbstractC3889atm Function<? super T, ? extends R> aunVar, @AbstractC3889atm ParallelFailureHandling buaVar) {
        ObjectHelper.m9873a(aunVar, "mapper");
        ObjectHelper.m9873a(buaVar, "errorHandler is null");
        return RxJavaPlugins.m9189a(new ParallelMapTry(this, aunVar, buaVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9251a(@AbstractC3889atm Function<? super T, ? extends R> aunVar, @AbstractC3889atm BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
        ObjectHelper.m9873a(aunVar, "mapper");
        ObjectHelper.m9873a(auiVar, "errorHandler is null");
        return RxJavaPlugins.m9189a(new ParallelMapTry(this, aunVar, auiVar));
    }

    @CheckReturnValue
    /* renamed from: a */
    public final ParallelFlowable<T> m9245a(@AbstractC3889atm Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate");
        return RxJavaPlugins.m9189a(new ParallelFilter(this, auxVar));
    }

    @CheckReturnValue
    /* renamed from: a */
    public final ParallelFlowable<T> m9243a(@AbstractC3889atm Predicate<? super T> auxVar, @AbstractC3889atm ParallelFailureHandling buaVar) {
        ObjectHelper.m9873a(auxVar, "predicate");
        ObjectHelper.m9873a(buaVar, "errorHandler is null");
        return RxJavaPlugins.m9189a(new ParallelFilterTry(this, auxVar, buaVar));
    }

    @CheckReturnValue
    /* renamed from: a */
    public final ParallelFlowable<T> m9244a(@AbstractC3889atm Predicate<? super T> auxVar, @AbstractC3889atm BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
        ObjectHelper.m9873a(auxVar, "predicate");
        ObjectHelper.m9873a(auiVar, "errorHandler is null");
        return RxJavaPlugins.m9189a(new ParallelFilterTry(this, auxVar, auiVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final ParallelFlowable<T> m9261a(@AbstractC3889atm Scheduler astVar) {
        return m9260a(astVar, Flowable.m11274a());
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final ParallelFlowable<T> m9260a(@AbstractC3889atm Scheduler astVar, int i) {
        ObjectHelper.m9873a(astVar, "scheduler");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9189a(new ParallelRunOn(this, astVar, i));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m9258a(@AbstractC3889atm BiFunction<T, T, T> auiVar) {
        ObjectHelper.m9873a(auiVar, "reducer");
        return RxJavaPlugins.m9207a(new ParallelReduceFull(this, auiVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9262a(@AbstractC3889atm Callable<R> callable, @AbstractC3889atm BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(callable, "initialSupplier");
        ObjectHelper.m9873a(auiVar, "reducer");
        return RxJavaPlugins.m9189a(new ParallelReduce(this, callable, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final Flowable<T> m9235b() {
        return m9266a(Flowable.m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m9266a(int i) {
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new ParallelJoin(this, i, false));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final Flowable<T> m9226c() {
        return m9234b(Flowable.m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m9234b(int i) {
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new ParallelJoin(this, i, true));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m9265a(@AbstractC3889atm Comparator<? super T> comparator) {
        return m9264a(comparator, 16);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m9264a(@AbstractC3889atm Comparator<? super T> comparator, int i) {
        ObjectHelper.m9873a(comparator, "comparator is null");
        ObjectHelper.m9878a(i, "capacityHint");
        return RxJavaPlugins.m9207a(new ParallelSortedJoin(m9262a(Functions.m9934a((i / mo9267a()) + 1), ListAddBiConsumer.instance()).m9254a(new SorterFunction(comparator)), comparator));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<List<T>> m9233b(@AbstractC3889atm Comparator<? super T> comparator) {
        return m9232b(comparator, 16);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<List<T>> m9232b(@AbstractC3889atm Comparator<? super T> comparator, int i) {
        ObjectHelper.m9873a(comparator, "comparator is null");
        ObjectHelper.m9878a(i, "capacityHint");
        return RxJavaPlugins.m9207a(m9262a(Functions.m9934a((i / mo9267a()) + 1), ListAddBiConsumer.instance()).m9254a(new SorterFunction(comparator)).m9258a(new MergerBiFunction(comparator)));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final ParallelFlowable<T> m9257a(@AbstractC3889atm Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, aumVar, Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.m9914b(), Functions.f17561g, Functions.f17557c));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final ParallelFlowable<T> m9255a(@AbstractC3889atm Consumer<? super T> aumVar, @AbstractC3889atm ParallelFailureHandling buaVar) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(buaVar, "errorHandler is null");
        return RxJavaPlugins.m9189a(new ParallelDoOnNextTry(this, aumVar, buaVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final ParallelFlowable<T> m9256a(@AbstractC3889atm Consumer<? super T> aumVar, @AbstractC3889atm BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(auiVar, "errorHandler is null");
        return RxJavaPlugins.m9189a(new ParallelDoOnNextTry(this, aumVar, auiVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final ParallelFlowable<T> m9230b(@AbstractC3889atm Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onAfterNext is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, Functions.m9914b(), aumVar, Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.m9914b(), Functions.f17561g, Functions.f17557c));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final ParallelFlowable<T> m9224c(@AbstractC3889atm Consumer<Throwable> aumVar) {
        ObjectHelper.m9873a(aumVar, "onError is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, Functions.m9914b(), Functions.m9914b(), aumVar, Functions.f17557c, Functions.f17557c, Functions.m9914b(), Functions.f17561g, Functions.f17557c));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final ParallelFlowable<T> m9259a(@AbstractC3889atm Action augVar) {
        ObjectHelper.m9873a(augVar, "onComplete is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), augVar, Functions.f17557c, Functions.m9914b(), Functions.f17561g, Functions.f17557c));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final ParallelFlowable<T> m9231b(@AbstractC3889atm Action augVar) {
        ObjectHelper.m9873a(augVar, "onAfterTerminate is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), Functions.f17557c, augVar, Functions.m9914b(), Functions.f17561g, Functions.f17557c));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public final ParallelFlowable<T> m9222d(@AbstractC3889atm Consumer<? super dby> aumVar) {
        ObjectHelper.m9873a(aumVar, "onSubscribe is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, aumVar, Functions.f17561g, Functions.f17557c));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final ParallelFlowable<T> m9246a(@AbstractC3889atm LongConsumer auwVar) {
        ObjectHelper.m9873a(auwVar, "onRequest is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.m9914b(), auwVar, Functions.f17557c));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final ParallelFlowable<T> m9225c(@AbstractC3889atm Action augVar) {
        ObjectHelper.m9873a(augVar, "onCancel is null");
        return RxJavaPlugins.m9189a(new ParallelPeek(this, Functions.m9914b(), Functions.m9914b(), Functions.m9914b(), Functions.f17557c, Functions.f17557c, Functions.m9914b(), Functions.f17561g, augVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <C> ParallelFlowable<C> m9263a(@AbstractC3889atm Callable<? extends C> callable, @AbstractC3889atm BiConsumer<? super C, ? super T> auhVar) {
        ObjectHelper.m9873a(callable, "collectionSupplier is null");
        ObjectHelper.m9873a(auhVar, "collector is null");
        return RxJavaPlugins.m9189a(new ParallelCollect(this, callable, auhVar));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> ParallelFlowable<T> m9237a(@AbstractC3889atm Publisher<T>... dbwVarArr) {
        if (dbwVarArr.length != 0) {
            return RxJavaPlugins.m9189a(new ParallelFromArray(dbwVarArr));
        }
        throw new IllegalArgumentException("Zero publishers not supported");
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <U> U m9229b(@AbstractC3889atm Function<? super ParallelFlowable<T>, U> aunVar) {
        try {
            return (U) ((Function) ObjectHelper.m9873a(aunVar, "converter is null")).apply(this);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U> ParallelFlowable<U> m9241a(@AbstractC3889atm ParallelTransformer<T, U> budVar) {
        return RxJavaPlugins.m9189a(((ParallelTransformer) ObjectHelper.m9873a(budVar, "composer is null")).m9219a(this));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final <R> ParallelFlowable<R> m9223c(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m9247a(aunVar, false, Integer.MAX_VALUE, Flowable.m11274a());
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9249a(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z) {
        return m9247a(aunVar, z, Integer.MAX_VALUE, Flowable.m11274a());
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9248a(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z, int i) {
        return m9247a(aunVar, z, i, Flowable.m11274a());
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9247a(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z, int i, int i2) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9189a(new ParallelFlatMap(this, aunVar, z, i, i2));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public final <R> ParallelFlowable<R> m9221d(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m9253a(aunVar, 2);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9253a(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9189a(new ParallelConcatMap(this, aunVar, i, ErrorMode.IMMEDIATE));
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <R> ParallelFlowable<R> m9228b(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z) {
        return m9252a(aunVar, 2, z);
    }

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> ParallelFlowable<R> m9252a(@AbstractC3889atm Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9189a(new ParallelConcatMap(this, aunVar, i, z ? ErrorMode.END : ErrorMode.BOUNDARY));
    }
}
