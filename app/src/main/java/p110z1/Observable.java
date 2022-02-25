package p110z1;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;

/* renamed from: z1.asl */
/* loaded from: classes3.dex */
public abstract class Observable<T> implements ObservableSource<T> {
    /* renamed from: a */
    protected abstract void mo34a(Observer<? super T> assVar);

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10568a(Iterable<? extends ObservableSource<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9203a(new ObservableAmb(null, iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10442a(ObservableSource<? extends T>... asqVarArr) {
        ObjectHelper.m9873a(asqVarArr, "sources is null");
        int length = asqVarArr.length;
        if (length == 0) {
            return m10310e();
        }
        if (length == 1) {
            return m10239i((ObservableSource) asqVarArr[0]);
        }
        return RxJavaPlugins.m9203a(new ObservableAmb(asqVarArr, null));
    }

    /* renamed from: d */
    public static int m10338d() {
        return Flowable.m11274a();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, R> Observable<R> m10469a(Function<? super Object[], ? extends R> aunVar, int i, ObservableSource<? extends T>... asqVarArr) {
        return m10440a(asqVarArr, aunVar, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, R> Observable<R> m10564a(Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        return m10563a(iterable, aunVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Observable<R> m10563a(Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9873a(iterable, "sources is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableCombineLatest(null, iterable, aunVar, i << 1, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, R> Observable<R> m10441a(ObservableSource<? extends T>[] asqVarArr, Function<? super Object[], ? extends R> aunVar) {
        return m10440a(asqVarArr, aunVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Observable<R> m10440a(ObservableSource<? extends T>[] asqVarArr, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9873a(asqVarArr, "sources is null");
        if (asqVarArr.length == 0) {
            return m10310e();
        }
        ObjectHelper.m9873a(aunVar, "combiner is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableCombineLatest(asqVarArr, null, aunVar, i << 1, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, R> Observable<R> m10507a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        return m10469a(Functions.m9927a((BiFunction) auiVar), m10338d(), asqVar, asqVar2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, R> Observable<R> m10509a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, Function3<? super T1, ? super T2, ? super T3, ? extends R> auoVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        return m10469a(Functions.m9921a((Function3) auoVar), m10338d(), asqVar, asqVar2, asqVar3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, R> Observable<R> m10511a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> aupVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        return m10469a(Functions.m9920a((Function4) aupVar), m10338d(), asqVar, asqVar2, asqVar3, asqVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, R> Observable<R> m10512a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> auqVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        return m10469a(Functions.m9919a((Function5) auqVar), m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> m10513a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> aurVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        return m10469a(Functions.m9918a((Function6) aurVar), m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> m10514a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, ObservableSource<? extends T7> asqVar7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> ausVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        ObjectHelper.m9873a(asqVar7, "source7 is null");
        return m10469a(Functions.m9917a((Function7) ausVar), m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6, asqVar7);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> m10515a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, ObservableSource<? extends T7> asqVar7, ObservableSource<? extends T8> asqVar8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> autVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        ObjectHelper.m9873a(asqVar7, "source7 is null");
        ObjectHelper.m9873a(asqVar8, "source8 is null");
        return m10469a(Functions.m9916a((Function8) autVar), m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6, asqVar7, asqVar8);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> m10516a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, ObservableSource<? extends T7> asqVar7, ObservableSource<? extends T8> asqVar8, ObservableSource<? extends T9> asqVar9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> auuVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        ObjectHelper.m9873a(asqVar7, "source7 is null");
        ObjectHelper.m9873a(asqVar8, "source8 is null");
        ObjectHelper.m9873a(asqVar9, "source9 is null");
        return m10469a(Functions.m9915a((Function9) auuVar), m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6, asqVar7, asqVar8, asqVar9);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T, R> Observable<R> m10374b(ObservableSource<? extends T>[] asqVarArr, Function<? super Object[], ? extends R> aunVar) {
        return m10373b(asqVarArr, aunVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T, R> Observable<R> m10383b(Function<? super Object[], ? extends R> aunVar, int i, ObservableSource<? extends T>... asqVarArr) {
        return m10373b(asqVarArr, aunVar, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T, R> Observable<R> m10373b(ObservableSource<? extends T>[] asqVarArr, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        if (asqVarArr.length == 0) {
            return m10310e();
        }
        return RxJavaPlugins.m9203a(new ObservableCombineLatest(asqVarArr, null, aunVar, i << 1, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T, R> Observable<R> m10423b(Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        return m10422b(iterable, aunVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T, R> Observable<R> m10422b(Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9873a(iterable, "sources is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableCombineLatest(null, iterable, aunVar, i << 1, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Observable<T> m10426b(Iterable<? extends ObservableSource<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return m10303e((Iterable) iterable).m10470a(Functions.m9935a(), m10338d(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Observable<T> m10525a(ObservableSource<? extends ObservableSource<? extends T>> asqVar) {
        return m10524a(asqVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10524a(ObservableSource<? extends ObservableSource<? extends T>> asqVar, int i) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableConcatMap(asqVar, Functions.m9935a(), i, ErrorMode.IMMEDIATE));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10520a(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        return m10375b(asqVar, asqVar2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10518a(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, ObservableSource<? extends T> asqVar3) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        return m10375b(asqVar, asqVar2, asqVar3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10517a(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, ObservableSource<? extends T> asqVar3, ObservableSource<? extends T> asqVar4) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        return m10375b(asqVar, asqVar2, asqVar3, asqVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10375b(ObservableSource<? extends T>... asqVarArr) {
        if (asqVarArr.length == 0) {
            return m10310e();
        }
        if (asqVarArr.length == 1) {
            return m10239i((ObservableSource) asqVarArr[0]);
        }
        return RxJavaPlugins.m9203a(new ObservableConcatMap(m10443a((Object[]) asqVarArr), Functions.m9935a(), m10338d(), ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10340c(ObservableSource<? extends T>... asqVarArr) {
        if (asqVarArr.length == 0) {
            return m10310e();
        }
        if (asqVarArr.length == 1) {
            return m10239i((ObservableSource) asqVarArr[0]);
        }
        return m10411b((ObservableSource) m10443a((Object[]) asqVarArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public static <T> Observable<T> m10311d(ObservableSource<? extends T>... asqVarArr) {
        return m10602a(m10338d(), m10338d(), asqVarArr);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Observable<T> m10602a(int i, int i2, ObservableSource<? extends T>... asqVarArr) {
        return m10443a((Object[]) asqVarArr).m10474a(Functions.m9935a(), i, i2, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public static <T> Observable<T> m10289e(ObservableSource<? extends T>... asqVarArr) {
        return m10437b(m10338d(), m10338d(), asqVarArr);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10437b(int i, int i2, ObservableSource<? extends T>... asqVarArr) {
        return m10443a((Object[]) asqVarArr).m10474a(Functions.m9935a(), i, i2, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Observable<T> m10363c(Iterable<? extends ObservableSource<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return m10411b((ObservableSource) m10303e((Iterable) iterable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10411b(ObservableSource<? extends ObservableSource<? extends T>> asqVar) {
        return m10522a((ObservableSource) asqVar, m10338d(), true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10522a(ObservableSource<? extends ObservableSource<? extends T>> asqVar, int i, boolean z) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        ObjectHelper.m9878a(i, "prefetch is null");
        return RxJavaPlugins.m9203a(new ObservableConcatMap(asqVar, Functions.m9935a(), i, z ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10357c(ObservableSource<? extends ObservableSource<? extends T>> asqVar) {
        return m10523a(asqVar, m10338d(), m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Observable<T> m10523a(ObservableSource<? extends ObservableSource<? extends T>> asqVar, int i, int i2) {
        return m10239i((ObservableSource) asqVar).m10475a(Functions.m9935a(), i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public static <T> Observable<T> m10328d(Iterable<? extends ObservableSource<? extends T>> iterable) {
        return m10566a(iterable, m10338d(), m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Observable<T> m10566a(Iterable<? extends ObservableSource<? extends T>> iterable, int i, int i2) {
        return m10303e((Iterable) iterable).m10474a(Functions.m9935a(), i, i2, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10527a(ObservableOnSubscribe<T> asoVar) {
        ObjectHelper.m9873a(asoVar, "source is null");
        return RxJavaPlugins.m9203a(new ObservableCreate(asoVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10546a(Callable<? extends ObservableSource<? extends T>> callable) {
        ObjectHelper.m9873a(callable, "supplier is null");
        return RxJavaPlugins.m9203a(new ObservableDefer(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public static <T> Observable<T> m10310e() {
        return RxJavaPlugins.m9203a(ObservableEmpty.f19084a);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Observable<T> m10418b(Callable<? extends Throwable> callable) {
        ObjectHelper.m9873a(callable, "errorSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableError(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10549a(Throwable th) {
        ObjectHelper.m9873a(th, "exception is null");
        return m10418b((Callable<? extends Throwable>) Functions.m9932a(th));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10443a(T... tArr) {
        ObjectHelper.m9873a(tArr, "items is null");
        if (tArr.length == 0) {
            return m10310e();
        }
        if (tArr.length == 1) {
            return m10561a(tArr[0]);
        }
        return RxJavaPlugins.m9203a(new ObservableFromArray(tArr));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Observable<T> m10359c(Callable<? extends T> callable) {
        ObjectHelper.m9873a(callable, "supplier is null");
        return RxJavaPlugins.m9203a((Observable) new ObservableFromCallable(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10537a(Future<? extends T> future) {
        ObjectHelper.m9873a(future, "future is null");
        return RxJavaPlugins.m9203a(new ObservableFromFuture(future, 0L, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10536a(Future<? extends T> future, long j, TimeUnit timeUnit) {
        ObjectHelper.m9873a(future, "future is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        return RxJavaPlugins.m9203a(new ObservableFromFuture(future, j, timeUnit));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10535a(Future<? extends T> future, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return m10536a(future, j, timeUnit).m10350c(astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10534a(Future<? extends T> future, Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return m10537a((Future) future).m10350c(astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: e */
    public static <T> Observable<T> m10303e(Iterable<? extends T> iterable) {
        ObjectHelper.m9873a(iterable, "source is null");
        return RxJavaPlugins.m9203a(new ObservableFromIterable(iterable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10445a(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "publisher is null");
        return RxJavaPlugins.m9203a(new ObservableFromPublisher(dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10483a(Consumer<Emitter<T>> aumVar) {
        ObjectHelper.m9873a(aumVar, "generator is null");
        return m10540a(Functions.m9905e(), ObservableInternalHelper.m9613a(aumVar), Functions.m9914b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, S> Observable<T> m10543a(Callable<S> callable, BiConsumer<S, Emitter<T>> auhVar) {
        ObjectHelper.m9873a(auhVar, "generator is null");
        return m10540a((Callable) callable, ObservableInternalHelper.m9614a(auhVar), Functions.m9914b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, S> Observable<T> m10542a(Callable<S> callable, BiConsumer<S, Emitter<T>> auhVar, Consumer<? super S> aumVar) {
        ObjectHelper.m9873a(auhVar, "generator is null");
        return m10540a((Callable) callable, ObservableInternalHelper.m9614a(auhVar), (Consumer) aumVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, S> Observable<T> m10541a(Callable<S> callable, BiFunction<S, Emitter<T>, S> auiVar) {
        return m10540a((Callable) callable, (BiFunction) auiVar, Functions.m9914b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, S> Observable<T> m10540a(Callable<S> callable, BiFunction<S, Emitter<T>, S> auiVar, Consumer<? super S> aumVar) {
        ObjectHelper.m9873a(callable, "initialState is null");
        ObjectHelper.m9873a(auiVar, "generator is null");
        ObjectHelper.m9873a(aumVar, "disposeState is null");
        return RxJavaPlugins.m9203a(new ObservableGenerate(callable, auiVar, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public static Observable<Long> m10592a(long j, long j2, TimeUnit timeUnit) {
        return m10591a(j, j2, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public static Observable<Long> m10591a(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableInterval(Math.max(0L, j), Math.max(0L, j2), timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public static Observable<Long> m10586a(long j, TimeUnit timeUnit) {
        return m10591a(j, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public static Observable<Long> m10580a(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10591a(j, j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public static Observable<Long> m10594a(long j, long j2, long j3, long j4, TimeUnit timeUnit) {
        return m10593a(j, j2, j3, j4, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @AbstractC3889atm
    /* renamed from: a */
    public static Observable<Long> m10593a(long j, long j2, long j3, long j4, TimeUnit timeUnit, Scheduler astVar) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + j2);
        } else if (i == 0) {
            return m10310e().m10306e(j3, timeUnit, astVar);
        } else {
            long j5 = j + (j2 - 1);
            if (j <= 0 || j5 >= 0) {
                ObjectHelper.m9873a(timeUnit, "unit is null");
                ObjectHelper.m9873a(astVar, "scheduler is null");
                return RxJavaPlugins.m9203a(new ObservableIntervalRange(j, j5, Math.max(0L, j3), Math.max(0L, j4), timeUnit, astVar));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10561a(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return RxJavaPlugins.m9203a((Observable) new ObservableJust(t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10560a(T t, T t2) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        return m10443a(t, t2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10559a(T t, T t2, T t3) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        return m10443a(t, t2, t3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10558a(T t, T t2, T t3, T t4) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        return m10443a(t, t2, t3, t4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10557a(T t, T t2, T t3, T t4, T t5) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        return m10443a(t, t2, t3, t4, t5);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10556a(T t, T t2, T t3, T t4, T t5, T t6) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        return m10443a(t, t2, t3, t4, t5, t6);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10555a(T t, T t2, T t3, T t4, T t5, T t6, T t7) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        ObjectHelper.m9873a((Object) t7, "item7 is null");
        return m10443a(t, t2, t3, t4, t5, t6, t7);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10554a(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        ObjectHelper.m9873a((Object) t7, "item7 is null");
        ObjectHelper.m9873a((Object) t8, "item8 is null");
        return m10443a(t, t2, t3, t4, t5, t6, t7, t8);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10553a(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        ObjectHelper.m9873a((Object) t7, "item7 is null");
        ObjectHelper.m9873a((Object) t8, "item8 is null");
        ObjectHelper.m9873a((Object) t9, "item9 is null");
        return m10443a(t, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Observable<T> m10552a(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9, T t10) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        ObjectHelper.m9873a((Object) t7, "item7 is null");
        ObjectHelper.m9873a((Object) t8, "item8 is null");
        ObjectHelper.m9873a((Object) t9, "item9 is null");
        ObjectHelper.m9873a((Object) t10, "item10 is null");
        return m10443a(t, t2, t3, t4, t5, t6, t7, t8, t9, t10);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10424b(Iterable<? extends ObservableSource<? extends T>> iterable, int i, int i2) {
        return m10303e((Iterable) iterable).m10450a(Functions.m9935a(), false, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10371c(int i, int i2, ObservableSource<? extends T>... asqVarArr) {
        return m10443a((Object[]) asqVarArr).m10450a(Functions.m9935a(), false, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public static <T> Observable<T> m10283f(Iterable<? extends ObservableSource<? extends T>> iterable) {
        return m10303e((Iterable) iterable).m10196p(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Observable<T> m10567a(Iterable<? extends ObservableSource<? extends T>> iterable, int i) {
        return m10303e((Iterable) iterable).m10274f(Functions.m9935a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public static <T> Observable<T> m10324d(ObservableSource<? extends ObservableSource<? extends T>> asqVar) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        return RxJavaPlugins.m9203a(new ObservableFlatMap(asqVar, Functions.m9935a(), false, Integer.MAX_VALUE, m10338d()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10410b(ObservableSource<? extends ObservableSource<? extends T>> asqVar, int i) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9203a(new ObservableFlatMap(asqVar, Functions.m9935a(), false, i, m10338d()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10409b(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        return m10443a((Object[]) new ObservableSource[]{asqVar, asqVar2}).m10313d(Functions.m9935a(), false, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10408b(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, ObservableSource<? extends T> asqVar3) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        return m10443a((Object[]) new ObservableSource[]{asqVar, asqVar2, asqVar3}).m10313d(Functions.m9935a(), false, 3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10407b(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, ObservableSource<? extends T> asqVar3, ObservableSource<? extends T> asqVar4) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        return m10443a((Object[]) new ObservableSource[]{asqVar, asqVar2, asqVar3, asqVar4}).m10313d(Functions.m9935a(), false, 4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public static <T> Observable<T> m10271f(ObservableSource<? extends T>... asqVarArr) {
        return m10443a((Object[]) asqVarArr).m10274f(Functions.m9935a(), asqVarArr.length);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public static <T> Observable<T> m10265g(Iterable<? extends ObservableSource<? extends T>> iterable) {
        return m10303e((Iterable) iterable).m10291e(Functions.m9935a(), true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10362c(Iterable<? extends ObservableSource<? extends T>> iterable, int i, int i2) {
        return m10303e((Iterable) iterable).m10450a(Functions.m9935a(), true, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public static <T> Observable<T> m10336d(int i, int i2, ObservableSource<? extends T>... asqVarArr) {
        return m10443a((Object[]) asqVarArr).m10450a(Functions.m9935a(), true, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T> Observable<T> m10425b(Iterable<? extends ObservableSource<? extends T>> iterable, int i) {
        return m10303e((Iterable) iterable).m10313d(Functions.m9935a(), true, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public static <T> Observable<T> m10300e(ObservableSource<? extends ObservableSource<? extends T>> asqVar) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        return RxJavaPlugins.m9203a(new ObservableFlatMap(asqVar, Functions.m9935a(), true, Integer.MAX_VALUE, m10338d()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10356c(ObservableSource<? extends ObservableSource<? extends T>> asqVar, int i) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9203a(new ObservableFlatMap(asqVar, Functions.m9935a(), true, i, m10338d()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10355c(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        return m10443a((Object[]) new ObservableSource[]{asqVar, asqVar2}).m10313d(Functions.m9935a(), true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10354c(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, ObservableSource<? extends T> asqVar3) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        return m10443a((Object[]) new ObservableSource[]{asqVar, asqVar2, asqVar3}).m10313d(Functions.m9935a(), true, 3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T> Observable<T> m10353c(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, ObservableSource<? extends T> asqVar3, ObservableSource<? extends T> asqVar4) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        return m10443a((Object[]) new ObservableSource[]{asqVar, asqVar2, asqVar3, asqVar4}).m10313d(Functions.m9935a(), true, 4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public static <T> Observable<T> m10256g(ObservableSource<? extends T>... asqVarArr) {
        return m10443a((Object[]) asqVarArr).m10313d(Functions.m9935a(), true, asqVarArr.length);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public static <T> Observable<T> m10288f() {
        return RxJavaPlugins.m9203a(ObservableNever.f19239a);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static Observable<Integer> m10604a(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + i2);
        } else if (i2 == 0) {
            return m10310e();
        } else {
            if (i2 == 1) {
                return m10561a(Integer.valueOf(i));
            }
            if (i + (i2 - 1) <= 2147483647L) {
                return RxJavaPlugins.m9203a(new ObservableRange(i, i2));
            }
            throw new IllegalArgumentException("Integer overflow");
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static Observable<Long> m10596a(long j, long j2) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + j2);
        } else if (i == 0) {
            return m10310e();
        } else {
            if (j2 == 1) {
                return m10561a(Long.valueOf(j));
            }
            long j3 = (j2 - 1) + j;
            if (j <= 0 || j3 >= 0) {
                return RxJavaPlugins.m9203a(new ObservableRangeLong(j, j2));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public static <T> Single<Boolean> m10322d(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2) {
        return m10503a(asqVar, asqVar2, ObjectHelper.m9880a(), m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Single<Boolean> m10504a(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, BiPredicate<? super T, ? super T> aujVar) {
        return m10503a(asqVar, asqVar2, aujVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Single<Boolean> m10503a(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, BiPredicate<? super T, ? super T> aujVar, int i) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(aujVar, "isEqual is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9200a(new ObservableSequenceEqualSingle(asqVar, asqVar2, aujVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T> Single<Boolean> m10519a(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, int i) {
        return m10503a(asqVar, asqVar2, ObjectHelper.m9880a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public static <T> Observable<T> m10323d(ObservableSource<? extends ObservableSource<? extends T>> asqVar, int i) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableSwitchMap(asqVar, Functions.m9935a(), i, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public static <T> Observable<T> m10280f(ObservableSource<? extends ObservableSource<? extends T>> asqVar) {
        return m10323d(asqVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public static <T> Observable<T> m10263g(ObservableSource<? extends ObservableSource<? extends T>> asqVar) {
        return m10299e(asqVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public static <T> Observable<T> m10299e(ObservableSource<? extends ObservableSource<? extends T>> asqVar, int i) {
        ObjectHelper.m9873a(asqVar, "sources is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableSwitchMap(asqVar, Functions.m9935a(), i, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: b */
    public static Observable<Long> m10432b(long j, TimeUnit timeUnit) {
        return m10431b(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public static Observable<Long> m10431b(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableTimer(Math.max(j, 0L), timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public static <T> Observable<T> m10249h(ObservableSource<T> asqVar) {
        ObjectHelper.m9873a(asqVar, "onSubscribe is null");
        if (!(asqVar instanceof Observable)) {
            return RxJavaPlugins.m9203a(new ObservableFromUnsafeSource(asqVar));
        }
        throw new IllegalArgumentException("unsafeCreate(Observable) should be upgraded");
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, D> Observable<T> m10539a(Callable<? extends D> callable, Function<? super D, ? extends ObservableSource<? extends T>> aunVar, Consumer<? super D> aumVar) {
        return m10538a((Callable) callable, (Function) aunVar, (Consumer) aumVar, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, D> Observable<T> m10538a(Callable<? extends D> callable, Function<? super D, ? extends ObservableSource<? extends T>> aunVar, Consumer<? super D> aumVar, boolean z) {
        ObjectHelper.m9873a(callable, "resourceSupplier is null");
        ObjectHelper.m9873a(aunVar, "sourceSupplier is null");
        ObjectHelper.m9873a(aumVar, "disposer is null");
        return RxJavaPlugins.m9203a(new ObservableUsing(callable, aunVar, aumVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public static <T> Observable<T> m10239i(ObservableSource<T> asqVar) {
        ObjectHelper.m9873a(asqVar, "source is null");
        if (asqVar instanceof Observable) {
            return RxJavaPlugins.m9203a((Observable) asqVar);
        }
        return RxJavaPlugins.m9203a(new ObservableFromUnsafeSource(asqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public static <T, R> Observable<R> m10361c(Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9203a(new ObservableZip(null, iterable, aunVar, m10338d(), false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, R> Observable<R> m10498a(ObservableSource<? extends ObservableSource<? extends T>> asqVar, Function<? super Object[], ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(asqVar, "sources is null");
        return RxJavaPlugins.m9203a(new ObservableToList(asqVar, 16).m10196p(ObservableInternalHelper.m9606c(aunVar)));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, R> Observable<R> m10399b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        return m10449a(Functions.m9927a((BiFunction) auiVar), false, m10338d(), asqVar, asqVar2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T1, T2, R> Observable<R> m10506a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar, boolean z) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        return m10449a(Functions.m9927a((BiFunction) auiVar), z, m10338d(), asqVar, asqVar2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T1, T2, R> Observable<R> m10505a(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar, boolean z, int i) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        return m10449a(Functions.m9927a((BiFunction) auiVar), z, i, asqVar, asqVar2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, T3, R> Observable<R> m10400b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, Function3<? super T1, ? super T2, ? super T3, ? extends R> auoVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        return m10449a(Functions.m9921a((Function3) auoVar), false, m10338d(), asqVar, asqVar2, asqVar3);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, T3, T4, R> Observable<R> m10401b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> aupVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        return m10449a(Functions.m9920a((Function4) aupVar), false, m10338d(), asqVar, asqVar2, asqVar3, asqVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, R> Observable<R> m10402b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> auqVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        return m10449a(Functions.m9919a((Function5) auqVar), false, m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> m10403b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> aurVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        return m10449a(Functions.m9918a((Function6) aurVar), false, m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> m10404b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, ObservableSource<? extends T7> asqVar7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> ausVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        ObjectHelper.m9873a(asqVar7, "source7 is null");
        return m10449a(Functions.m9917a((Function7) ausVar), false, m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6, asqVar7);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> m10405b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, ObservableSource<? extends T7> asqVar7, ObservableSource<? extends T8> asqVar8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> autVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        ObjectHelper.m9873a(asqVar7, "source7 is null");
        ObjectHelper.m9873a(asqVar8, "source8 is null");
        return m10449a(Functions.m9916a((Function8) autVar), false, m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6, asqVar7, asqVar8);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> m10406b(ObservableSource<? extends T1> asqVar, ObservableSource<? extends T2> asqVar2, ObservableSource<? extends T3> asqVar3, ObservableSource<? extends T4> asqVar4, ObservableSource<? extends T5> asqVar5, ObservableSource<? extends T6> asqVar6, ObservableSource<? extends T7> asqVar7, ObservableSource<? extends T8> asqVar8, ObservableSource<? extends T9> asqVar9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> auuVar) {
        ObjectHelper.m9873a(asqVar, "source1 is null");
        ObjectHelper.m9873a(asqVar2, "source2 is null");
        ObjectHelper.m9873a(asqVar3, "source3 is null");
        ObjectHelper.m9873a(asqVar4, "source4 is null");
        ObjectHelper.m9873a(asqVar5, "source5 is null");
        ObjectHelper.m9873a(asqVar6, "source6 is null");
        ObjectHelper.m9873a(asqVar7, "source7 is null");
        ObjectHelper.m9873a(asqVar8, "source8 is null");
        ObjectHelper.m9873a(asqVar9, "source9 is null");
        return m10449a(Functions.m9915a((Function9) auuVar), false, m10338d(), asqVar, asqVar2, asqVar3, asqVar4, asqVar5, asqVar6, asqVar7, asqVar8, asqVar9);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, R> Observable<R> m10449a(Function<? super Object[], ? extends R> aunVar, boolean z, int i, ObservableSource<? extends T>... asqVarArr) {
        if (asqVarArr.length == 0) {
            return m10310e();
        }
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableZip(asqVarArr, null, aunVar, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public static <T, R> Observable<R> m10562a(Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(iterable, "sources is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableZip(null, iterable, aunVar, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Single<Boolean> m10448a(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9200a(new ObservableAllSingle(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: j */
    public final Observable<T> m10230j(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10442a(this, asqVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Single<Boolean> m10377b(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9200a(new ObservableAnySingle(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> R m10528a(@AbstractC3889atm ObservableConverter<T, ? extends R> asmVar) {
        return (R) ((ObservableConverter) ObjectHelper.m9873a(asmVar, "converter is null")).m10165a(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final T m10270g() {
        BlockingFirstObserver awcVar = new BlockingFirstObserver();
        subscribe(awcVar);
        T a = awcVar.m9850a();
        if (a != null) {
            return a;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final T m10421b(T t) {
        BlockingFirstObserver awcVar = new BlockingFirstObserver();
        subscribe(awcVar);
        T a = awcVar.m9850a();
        return a != null ? a : t;
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final void m10388b(Consumer<? super T> aumVar) {
        Iterator<T> it = m10255h().iterator();
        while (it.hasNext()) {
            try {
                aumVar.accept(it.next());
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                ((Disposable) it).dispose();
                throw ExceptionHelper.m9432a(th);
            }
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Iterable<T> m10255h() {
        return m10605a(m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Iterable<T> m10605a(int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        return new BlockingObservableIterable(this, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final T m10243i() {
        BlockingLastObserver awdVar = new BlockingLastObserver();
        subscribe(awdVar);
        T a = awdVar.m9850a();
        if (a != null) {
            return a;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final T m10360c(T t) {
        BlockingLastObserver awdVar = new BlockingLastObserver();
        subscribe(awdVar);
        T a = awdVar.m9850a();
        return a != null ? a : t;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: j */
    public final Iterable<T> m10234j() {
        return new BlockingObservableLatest(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Iterable<T> m10326d(T t) {
        return new BlockingObservableMostRecent(this, t);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final Iterable<T> m10227k() {
        return new BlockingObservableNext(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: l */
    public final T m10221l() {
        T c = m10620I().m10727c();
        if (c != null) {
            return c;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final T m10302e(T t) {
        return m10224k((Observable<T>) t).m10063d();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: m */
    public final Future<T> m10215m() {
        return (Future) m10278f((Observable<T>) new FutureObserver());
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: n */
    public final void m10210n() {
        ObservableBlockingSubscribe.m9661a(this);
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final void m10348c(Consumer<? super T> aumVar) {
        ObservableBlockingSubscribe.m9659a(this, aumVar, Functions.f17560f, Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final void m10481a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2) {
        ObservableBlockingSubscribe.m9659a(this, aumVar, aumVar2, Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final void m10480a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar) {
        ObservableBlockingSubscribe.m9659a(this, aumVar, aumVar2, augVar);
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final void m10351c(Observer<? super T> assVar) {
        ObservableBlockingSubscribe.m9660a(this, assVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<List<T>> m10439b(int i) {
        return m10438b(i, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<List<T>> m10438b(int i, int i2) {
        return (Observable<List<T>>) m10603a(i, i2, ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U extends Collection<? super T>> Observable<U> m10603a(int i, int i2, Callable<U> callable) {
        ObjectHelper.m9878a(i, "count");
        ObjectHelper.m9878a(i2, MSVSSConstants.WRITABLE_SKIP);
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableBuffer(this, i, i2, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U extends Collection<? super T>> Observable<U> m10599a(int i, Callable<U> callable) {
        return m10603a(i, i, callable);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: b */
    public final Observable<List<T>> m10434b(long j, long j2, TimeUnit timeUnit) {
        return (Observable<List<T>>) m10589a(j, j2, timeUnit, Schedulers.m9050a(), ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public final Observable<List<T>> m10433b(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        return (Observable<List<T>>) m10589a(j, j2, timeUnit, astVar, ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final <U extends Collection<? super T>> Observable<U> m10589a(long j, long j2, TimeUnit timeUnit, Scheduler astVar, Callable<U> callable) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableBufferTimed(this, j, j2, timeUnit, astVar, callable, Integer.MAX_VALUE, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: c */
    public final Observable<List<T>> m10367c(long j, TimeUnit timeUnit) {
        return m10579a(j, timeUnit, Schedulers.m9050a(), Integer.MAX_VALUE);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final Observable<List<T>> m10585a(long j, TimeUnit timeUnit, int i) {
        return m10579a(j, timeUnit, Schedulers.m9050a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<List<T>> m10579a(long j, TimeUnit timeUnit, Scheduler astVar, int i) {
        return (Observable<List<T>>) m10578a(j, timeUnit, astVar, i, (Callable) ArrayListSupplier.asCallable(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final <U extends Collection<? super T>> Observable<U> m10578a(long j, TimeUnit timeUnit, Scheduler astVar, int i, Callable<U> callable, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        ObjectHelper.m9878a(i, "count");
        return RxJavaPlugins.m9203a(new ObservableBufferTimed(this, j, j, timeUnit, astVar, callable, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: c */
    public final Observable<List<T>> m10366c(long j, TimeUnit timeUnit, Scheduler astVar) {
        return (Observable<List<T>>) m10578a(j, timeUnit, astVar, Integer.MAX_VALUE, (Callable) ArrayListSupplier.asCallable(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <TOpening, TClosing> Observable<List<T>> m10397b(ObservableSource<? extends TOpening> asqVar, Function<? super TOpening, ? extends ObservableSource<? extends TClosing>> aunVar) {
        return (Observable<List<T>>) m10496a((ObservableSource) asqVar, (Function) aunVar, (Callable) ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <TOpening, TClosing, U extends Collection<? super T>> Observable<U> m10496a(ObservableSource<? extends TOpening> asqVar, Function<? super TOpening, ? extends ObservableSource<? extends TClosing>> aunVar, Callable<U> callable) {
        ObjectHelper.m9873a(asqVar, "openingIndicator is null");
        ObjectHelper.m9873a(aunVar, "closingIndicator is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableBufferBoundary(this, asqVar, aunVar, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final <B> Observable<List<T>> m10223k(ObservableSource<B> asqVar) {
        return (Observable<List<T>>) m10521a((ObservableSource) asqVar, (Callable) ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final <B> Observable<List<T>> m10279f(ObservableSource<B> asqVar, int i) {
        ObjectHelper.m9878a(i, "initialCapacity");
        return (Observable<List<T>>) m10521a((ObservableSource) asqVar, (Callable) Functions.m9934a(i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <B, U extends Collection<? super T>> Observable<U> m10521a(ObservableSource<B> asqVar, Callable<U> callable) {
        ObjectHelper.m9873a(asqVar, "boundary is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableBufferExactBoundary(this, asqVar, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final <B> Observable<List<T>> m10325d(Callable<? extends ObservableSource<B>> callable) {
        return (Observable<List<T>>) m10544a((Callable) callable, (Callable) ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <B, U extends Collection<? super T>> Observable<U> m10544a(Callable<? extends ObservableSource<B>> callable, Callable<U> callable2) {
        ObjectHelper.m9873a(callable, "boundarySupplier is null");
        ObjectHelper.m9873a(callable2, "bufferSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableBufferBoundarySupplier(this, callable, callable2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: o */
    public final Observable<T> m10205o() {
        return m10372c(16);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Observable<T> m10372c(int i) {
        ObjectHelper.m9878a(i, "initialCapacity");
        return RxJavaPlugins.m9203a(new ObservableCache(this, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U> Observable<U> m10569a(Class<U> cls) {
        ObjectHelper.m9873a(cls, "clazz is null");
        return (Observable<U>) m10174v(Functions.m9933a((Class) cls));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <U> Single<U> m10417b(Callable<? extends U> callable, BiConsumer<? super U, ? super T> auhVar) {
        ObjectHelper.m9873a(callable, "initialValueSupplier is null");
        ObjectHelper.m9873a(auhVar, "collector is null");
        return RxJavaPlugins.m9200a(new ObservableCollectSingle(this, callable, auhVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U> Single<U> m10551a(U u, BiConsumer<? super U, ? super T> auhVar) {
        ObjectHelper.m9873a(u, "initialValue is null");
        return m10417b(Functions.m9932a(u), auhVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10492a(ObservableTransformer<? super T, ? extends R> asrVar) {
        return m10239i(((ObservableTransformer) ObjectHelper.m9873a(asrVar, "composer is null")).m10162a(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10477a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        return m10476a(aunVar, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10476a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.m9203a(new ObservableConcatMap(this, aunVar, i, ErrorMode.IMMEDIATE));
        }
        Object call = ((ScalarCallable) this).call();
        if (call == null) {
            return m10310e();
        }
        return ObservableScalarXMap.m9576a(call, aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <R> Observable<R> m10385b(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        return m10470a((Function) aunVar, m10338d(), true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10470a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        if (this instanceof ScalarCallable) {
            Object call = ((ScalarCallable) this).call();
            if (call == null) {
                return m10310e();
            }
            return ObservableScalarXMap.m9576a(call, aunVar);
        }
        return RxJavaPlugins.m9203a(new ObservableConcatMap(this, aunVar, i, z ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <R> Observable<R> m10347c(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        return m10475a(aunVar, Integer.MAX_VALUE, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10475a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i, int i2) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableConcatMapEager(this, aunVar, ErrorMode.IMMEDIATE, i, i2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10452a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, boolean z) {
        return m10474a(aunVar, Integer.MAX_VALUE, m10338d(), z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10474a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i, int i2, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableConcatMapEager(this, aunVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i, i2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Completable m10316d(Function<? super T, ? extends CompletableSource> aunVar) {
        return m10384b(aunVar, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Completable m10384b(Function<? super T, ? extends CompletableSource> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "capacityHint");
        return RxJavaPlugins.m9209a(new ObservableConcatMapCompletable(this, aunVar, ErrorMode.IMMEDIATE, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Completable m10293e(Function<? super T, ? extends CompletableSource> aunVar) {
        return m10451a((Function) aunVar, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Completable m10379b(Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
        return m10451a(aunVar, z, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Completable m10451a(Function<? super T, ? extends CompletableSource> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9209a(new ObservableConcatMapCompletable(this, aunVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final <U> Observable<U> m10275f(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableFlattenIterable(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <U> Observable<U> m10346c(Function<? super T, ? extends Iterable<? extends U>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return (Observable<U>) m10476a(ObservableInternalHelper.m9608b(aunVar), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final <R> Observable<R> m10260g(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        return m10315d(aunVar, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final <R> Observable<R> m10315d(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableConcatMapMaybe(this, aunVar, ErrorMode.IMMEDIATE, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final <R> Observable<R> m10247h(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        return m10378b((Function) aunVar, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <R> Observable<R> m10343c(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z) {
        return m10378b(aunVar, z, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <R> Observable<R> m10378b(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableConcatMapMaybe(this, aunVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final <R> Observable<R> m10237i(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        return m10292e(aunVar, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final <R> Observable<R> m10292e(Function<? super T, ? extends SingleSource<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableConcatMapSingle(this, aunVar, ErrorMode.IMMEDIATE, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: j */
    public final <R> Observable<R> m10228j(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        return m10342c((Function) aunVar, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final <R> Observable<R> m10314d(Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
        return m10342c(aunVar, z, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <R> Observable<R> m10342c(Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9203a(new ObservableConcatMapSingle(this, aunVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: l */
    public final Observable<T> m10217l(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10520a((ObservableSource) this, (ObservableSource) asqVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10488a(@AbstractC3889atm SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableConcatWithSingle(this, ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10529a(@AbstractC3889atm MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableConcatWithMaybe(this, asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10530a(@AbstractC3889atm CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableConcatWithCompletable(this, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Single<Boolean> m10282f(Object obj) {
        ObjectHelper.m9873a(obj, "element is null");
        return m10377b((Predicate) Functions.m9908c(obj));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: p */
    public final Single<Long> m10200p() {
        return RxJavaPlugins.m9200a(new ObservableCountSingle(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final <U> Observable<T> m10222k(Function<? super T, ? extends ObservableSource<U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "debounceSelector is null");
        return RxJavaPlugins.m9203a(new ObservableDebounce(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: d */
    public final Observable<T> m10332d(long j, TimeUnit timeUnit) {
        return m10331d(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: d */
    public final Observable<T> m10331d(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableDebounceTimed(this, j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final Observable<T> m10264g(T t) {
        ObjectHelper.m9873a((Object) t, "defaultItem is null");
        return m10181t(m10561a(t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: l */
    public final <U> Observable<T> m10216l(Function<? super T, ? extends ObservableSource<U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "itemDelay is null");
        return (Observable<T>) m10196p(ObservableInternalHelper.m9612a(aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: e */
    public final Observable<T> m10307e(long j, TimeUnit timeUnit) {
        return m10573a(j, timeUnit, Schedulers.m9050a(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final Observable<T> m10571a(long j, TimeUnit timeUnit, boolean z) {
        return m10573a(j, timeUnit, Schedulers.m9050a(), z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: e */
    public final Observable<T> m10306e(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10573a(j, timeUnit, astVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<T> m10573a(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableDelay(this, j, timeUnit, astVar, z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <U, V> Observable<T> m10352c(ObservableSource<U> asqVar, Function<? super T, ? extends ObservableSource<V>> aunVar) {
        return m10212m(asqVar).m10216l((Function) aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: m */
    public final <U> Observable<T> m10212m(ObservableSource<U> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableDelaySubscriptionOther(this, asqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: f */
    public final Observable<T> m10285f(long j, TimeUnit timeUnit) {
        return m10284f(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: f */
    public final Observable<T> m10284f(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10212m(m10431b(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @Deprecated
    /* renamed from: q */
    public final <T2> Observable<T2> m10195q() {
        return RxJavaPlugins.m9203a(new ObservableDematerialize(this, Functions.m9935a()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @Experimental
    /* renamed from: m */
    public final <R> Observable<R> m10211m(Function<? super T, Notification<R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        return RxJavaPlugins.m9203a(new ObservableDematerialize(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: r */
    public final Observable<T> m10190r() {
        return m10466a((Function) Functions.m9935a(), (Callable) Functions.m9903g());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: n */
    public final <K> Observable<T> m10206n(Function<? super T, K> aunVar) {
        return m10466a((Function) aunVar, (Callable) Functions.m9903g());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <K> Observable<T> m10466a(Function<? super T, K> aunVar, Callable<? extends Collection<? super K>> callable) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(callable, "collectionSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableDistinct(this, aunVar, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: s */
    public final Observable<T> m10185s() {
        return m10201o(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: o */
    public final <K> Observable<T> m10201o(Function<? super T, K> aunVar) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        return RxJavaPlugins.m9203a(new ObservableDistinctUntilChanged(this, aunVar, ObjectHelper.m9880a()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10485a(BiPredicate<? super T, ? super T> aujVar) {
        ObjectHelper.m9873a(aujVar, "comparer is null");
        return RxJavaPlugins.m9203a(new ObservableDistinctUntilChanged(this, Functions.m9935a(), aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Observable<T> m10317d(Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onAfterNext is null");
        return RxJavaPlugins.m9203a(new ObservableDoAfterNext(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10487a(Action augVar) {
        ObjectHelper.m9873a(augVar, "onFinally is null");
        return m10479a((Consumer) Functions.m9914b(), Functions.m9914b(), Functions.f17557c, augVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10392b(Action augVar) {
        ObjectHelper.m9873a(augVar, "onFinally is null");
        return RxJavaPlugins.m9203a(new ObservableDoFinally(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Observable<T> m10349c(Action augVar) {
        return m10482a(Functions.m9914b(), augVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Observable<T> m10318d(Action augVar) {
        return m10479a((Consumer) Functions.m9914b(), Functions.m9914b(), augVar, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    private Observable<T> m10479a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ObjectHelper.m9873a(augVar2, "onAfterTerminate is null");
        return RxJavaPlugins.m9203a(new ObservableDoOnEach(this, aumVar, aumVar2, augVar, augVar2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Observable<T> m10294e(Consumer<? super Notification<T>> aumVar) {
        ObjectHelper.m9873a(aumVar, "onNotification is null");
        return m10479a((Consumer) Functions.m9925a((Consumer) aumVar), (Consumer<? super Throwable>) Functions.m9910b((Consumer) aumVar), Functions.m9907c((Consumer) aumVar), Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Observable<T> m10320d(Observer<? super T> assVar) {
        ObjectHelper.m9873a(assVar, "observer is null");
        return m10479a((Consumer) ObservableInternalHelper.m9615a(assVar), (Consumer<? super Throwable>) ObservableInternalHelper.m9609b(assVar), ObservableInternalHelper.m9607c(assVar), Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Observable<T> m10276f(Consumer<? super Throwable> aumVar) {
        return m10479a((Consumer) Functions.m9914b(), aumVar, Functions.f17557c, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10482a(Consumer<? super Disposable> aumVar, Action augVar) {
        ObjectHelper.m9873a(aumVar, "onSubscribe is null");
        ObjectHelper.m9873a(augVar, "onDispose is null");
        return RxJavaPlugins.m9203a(new ObservableDoOnLifecycle(this, aumVar, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final Observable<T> m10261g(Consumer<? super T> aumVar) {
        return m10479a((Consumer) aumVar, Functions.m9914b(), Functions.f17557c, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Observable<T> m10248h(Consumer<? super Disposable> aumVar) {
        return m10482a(aumVar, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Observable<T> m10295e(Action augVar) {
        ObjectHelper.m9873a(augVar, "onTerminate is null");
        return m10479a((Consumer) Functions.m9914b(), Functions.m9928a(augVar), augVar, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Maybe<T> m10597a(long j) {
        if (j >= 0) {
            return RxJavaPlugins.m9205a(new ObservableElementAtMaybe(this, j));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Single<T> m10587a(long j, T t) {
        if (j >= 0) {
            ObjectHelper.m9873a((Object) t, "defaultItem is null");
            return RxJavaPlugins.m9200a(new ObservableElementAtSingle(this, j, t));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Single<T> m10436b(long j) {
        if (j >= 0) {
            return RxJavaPlugins.m9200a(new ObservableElementAtSingle(this, j, null));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Observable<T> m10341c(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9203a(new ObservableFilter(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: t */
    public final Maybe<T> m10182t() {
        return m10597a(0L);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Single<T> m10250h(T t) {
        return m10587a(0L, (long) t);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: u */
    public final Single<T> m10179u() {
        return m10436b(0L);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: p */
    public final <R> Observable<R> m10196p(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        return m10291e((Function) aunVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final <R> Observable<R> m10291e(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, boolean z) {
        return m10313d(aunVar, z, Integer.MAX_VALUE);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final <R> Observable<R> m10313d(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, boolean z, int i) {
        return m10450a(aunVar, z, i, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10450a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, boolean z, int i, int i2) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.m9203a(new ObservableFlatMap(this, aunVar, z, i, i2));
        }
        Object call = ((ScalarCallable) this).call();
        if (call == null) {
            return m10310e();
        }
        return ObservableScalarXMap.m9576a(call, aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10457a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, Function<? super Throwable, ? extends ObservableSource<? extends R>> aunVar2, Callable<? extends ObservableSource<? extends R>> callable) {
        ObjectHelper.m9873a(aunVar, "onNextMapper is null");
        ObjectHelper.m9873a(aunVar2, "onErrorMapper is null");
        ObjectHelper.m9873a(callable, "onCompleteSupplier is null");
        return m10324d((ObservableSource) new ObservableMapNotification(this, aunVar, aunVar2, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10456a(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, Function<Throwable, ? extends ObservableSource<? extends R>> aunVar2, Callable<? extends ObservableSource<? extends R>> callable, int i) {
        ObjectHelper.m9873a(aunVar, "onNextMapper is null");
        ObjectHelper.m9873a(aunVar2, "onErrorMapper is null");
        ObjectHelper.m9873a(callable, "onCompleteSupplier is null");
        return m10410b(new ObservableMapNotification(this, aunVar, aunVar2, callable), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final <R> Observable<R> m10274f(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i) {
        return m10450a((Function) aunVar, false, i, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10463a(Function<? super T, ? extends ObservableSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        return m10459a((Function) aunVar, (BiFunction) auiVar, false, m10338d(), m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10461a(Function<? super T, ? extends ObservableSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z) {
        return m10459a(aunVar, auiVar, z, m10338d(), m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10460a(Function<? super T, ? extends ObservableSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z, int i) {
        return m10459a(aunVar, auiVar, z, i, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10459a(Function<? super T, ? extends ObservableSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z, int i, int i2) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9873a(auiVar, "combiner is null");
        return m10450a(ObservableInternalHelper.m9610a(aunVar, auiVar), z, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10462a(Function<? super T, ? extends ObservableSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, int i) {
        return m10459a((Function) aunVar, (BiFunction) auiVar, false, i, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: q */
    public final Completable m10191q(Function<? super T, ? extends CompletableSource> aunVar) {
        return m10273f((Function) aunVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Completable m10273f(Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9209a(new ObservableFlatMapCompletableCompletable(this, aunVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: r */
    public final <U> Observable<U> m10186r(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableFlattenIterable(this, aunVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <U, V> Observable<V> m10382b(Function<? super T, ? extends Iterable<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends V> auiVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return (Observable<V>) m10459a((Function) ObservableInternalHelper.m9608b(aunVar), (BiFunction) auiVar, false, m10338d(), m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: s */
    public final <R> Observable<R> m10183s(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        return m10258g((Function) aunVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final <R> Observable<R> m10258g(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableFlatMapMaybe(this, aunVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: t */
    public final <R> Observable<R> m10180t(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        return m10245h((Function) aunVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final <R> Observable<R> m10245h(Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableFlatMapSingle(this, aunVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final Disposable m10238i(Consumer<? super T> aumVar) {
        return m10229j((Consumer) aumVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Disposable m10312d(Predicate<? super T> auxVar) {
        return m10446a((Predicate) auxVar, (Consumer<? super Throwable>) Functions.f17560f, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Disposable m10447a(Predicate<? super T> auxVar, Consumer<? super Throwable> aumVar) {
        return m10446a((Predicate) auxVar, aumVar, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Disposable m10446a(Predicate<? super T> auxVar, Consumer<? super Throwable> aumVar, Action augVar) {
        ObjectHelper.m9873a(auxVar, "onNext is null");
        ObjectHelper.m9873a(aumVar, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ForEachWhileObserver awmVar = new ForEachWhileObserver(auxVar, aumVar, augVar);
        subscribe(awmVar);
        return awmVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: u */
    public final <K> Observable<GroupedObservable<K, T>> m10177u(Function<? super T, ? extends K> aunVar) {
        return (Observable<GroupedObservable<K, T>>) m10453a((Function) aunVar, (Function) Functions.m9935a(), false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final <K> Observable<GroupedObservable<K, T>> m10235i(Function<? super T, ? extends K> aunVar, boolean z) {
        return (Observable<GroupedObservable<K, T>>) m10453a(aunVar, Functions.m9935a(), z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <K, V> Observable<GroupedObservable<K, V>> m10458a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2) {
        return m10453a((Function) aunVar, (Function) aunVar2, false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <K, V> Observable<GroupedObservable<K, V>> m10454a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, boolean z) {
        return m10453a(aunVar, aunVar2, z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <K, V> Observable<GroupedObservable<K, V>> m10453a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableGroupBy(this, aunVar, aunVar2, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <TRight, TLeftEnd, TRightEnd, R> Observable<R> m10494a(ObservableSource<? extends TRight> asqVar, Function<? super T, ? extends ObservableSource<TLeftEnd>> aunVar, Function<? super TRight, ? extends ObservableSource<TRightEnd>> aunVar2, BiFunction<? super T, ? super Observable<TRight>, ? extends R> auiVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        ObjectHelper.m9873a(aunVar, "leftEnd is null");
        ObjectHelper.m9873a(aunVar2, "rightEnd is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return RxJavaPlugins.m9203a(new ObservableGroupJoin(this, asqVar, aunVar, aunVar2, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: v */
    public final Observable<T> m10176v() {
        return RxJavaPlugins.m9203a(new ObservableHide(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: w */
    public final Completable m10173w() {
        return RxJavaPlugins.m9209a(new ObservableIgnoreElementsCompletable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: x */
    public final Single<Boolean> m10171x() {
        return m10448a((Predicate) Functions.m9906d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <TRight, TLeftEnd, TRightEnd, R> Observable<R> m10395b(ObservableSource<? extends TRight> asqVar, Function<? super T, ? extends ObservableSource<TLeftEnd>> aunVar, Function<? super TRight, ? extends ObservableSource<TRightEnd>> aunVar2, BiFunction<? super T, ? super TRight, ? extends R> auiVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        ObjectHelper.m9873a(aunVar, "leftEnd is null");
        ObjectHelper.m9873a(aunVar2, "rightEnd is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return RxJavaPlugins.m9203a(new ObservableJoin(this, asqVar, aunVar, aunVar2, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: y */
    public final Maybe<T> m10169y() {
        return RxJavaPlugins.m9205a(new ObservableLastMaybe(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final Single<T> m10240i(T t) {
        ObjectHelper.m9873a((Object) t, "defaultItem is null");
        return RxJavaPlugins.m9200a(new ObservableLastSingle(this, t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: z */
    public final Single<T> m10167z() {
        return RxJavaPlugins.m9200a(new ObservableLastSingle(this, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Observable<R> m10526a(ObservableOperator<? extends R, ? super T> aspVar) {
        ObjectHelper.m9873a(aspVar, "lifter is null");
        return RxJavaPlugins.m9203a(new ObservableLift(this, aspVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: v */
    public final <R> Observable<R> m10174v(Function<? super T, ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableMap(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: A */
    public final Observable<Notification<T>> m10636A() {
        return RxJavaPlugins.m9203a(new ObservableMaterialize(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: n */
    public final Observable<T> m10207n(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10409b(this, asqVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10393b(@AbstractC3889atm SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableMergeWithSingle(this, ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10412b(@AbstractC3889atm MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableMergeWithMaybe(this, asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10413b(@AbstractC3889atm CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableMergeWithCompletable(this, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<T> m10491a(Scheduler astVar) {
        return m10489a(astVar, false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<T> m10490a(Scheduler astVar, boolean z) {
        return m10489a(astVar, z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<T> m10489a(Scheduler astVar, boolean z, int i) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableObserveOn(this, astVar, z, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <U> Observable<U> m10427b(Class<U> cls) {
        ObjectHelper.m9873a(cls, "clazz is null");
        return m10341c((Predicate) Functions.m9912b((Class) cls)).m10569a((Class) cls);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: w */
    public final Observable<T> m10172w(Function<? super Throwable, ? extends ObservableSource<? extends T>> aunVar) {
        ObjectHelper.m9873a(aunVar, "resumeFunction is null");
        return RxJavaPlugins.m9203a(new ObservableOnErrorNext(this, aunVar, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: o */
    public final Observable<T> m10202o(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "next is null");
        return m10172w(Functions.m9911b(asqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: x */
    public final Observable<T> m10170x(Function<? super Throwable, ? extends T> aunVar) {
        ObjectHelper.m9873a(aunVar, "valueSupplier is null");
        return RxJavaPlugins.m9203a(new ObservableOnErrorReturn(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: j */
    public final Observable<T> m10231j(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return m10170x(Functions.m9911b(t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: p */
    public final Observable<T> m10197p(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "next is null");
        return RxJavaPlugins.m9203a(new ObservableOnErrorNext(this, Functions.m9911b(asqVar), true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: B */
    public final Observable<T> m10634B() {
        return RxJavaPlugins.m9203a(new ObservableDetach(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: C */
    public final ConnectableObservable<T> m10632C() {
        return ObservablePublish.m9593w(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: y */
    public final <R> Observable<R> m10168y(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        return RxJavaPlugins.m9203a(new ObservablePublishSelector(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Maybe<T> m10486a(BiFunction<T, T, T> auiVar) {
        ObjectHelper.m9873a(auiVar, "reducer is null");
        return RxJavaPlugins.m9205a(new ObservableReduceMaybe(this, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <R> Single<R> m10550a(R r, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(r, "seed is null");
        ObjectHelper.m9873a(auiVar, "reducer is null");
        return RxJavaPlugins.m9200a(new ObservableReduceSeedSingle(this, r, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <R> Single<R> m10416b(Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(callable, "seedSupplier is null");
        ObjectHelper.m9873a(auiVar, "reducer is null");
        return RxJavaPlugins.m9200a(new ObservableReduceWithSingle(this, callable, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: D */
    public final Observable<T> m10630D() {
        return m10370c((long) cjm.f20759b);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final Observable<T> m10370c(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("times >= 0 required but it was " + j);
        } else if (i == 0) {
            return m10310e();
        } else {
            return RxJavaPlugins.m9203a(new ObservableRepeat(this, j));
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10484a(BooleanSupplier aukVar) {
        ObjectHelper.m9873a(aukVar, "stop is null");
        return RxJavaPlugins.m9203a(new ObservableRepeatUntil(this, aukVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: z */
    public final Observable<T> m10166z(Function<? super Observable<Object>, ? extends ObservableSource<?>> aunVar) {
        ObjectHelper.m9873a(aunVar, "handler is null");
        return RxJavaPlugins.m9203a(new ObservableRepeatWhen(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: E */
    public final ConnectableObservable<T> m10628E() {
        return ObservableReplay.m9579w(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: A */
    public final <R> Observable<R> m10635A(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        return ObservableReplay.m9586a(ObservableInternalHelper.m9619a(this), (Function) aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final <R> Observable<R> m10259g(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return ObservableReplay.m9586a(ObservableInternalHelper.m9618a(this, i), (Function) aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final <R> Observable<R> m10473a(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, int i, long j, TimeUnit timeUnit) {
        return m10472a(aunVar, i, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final <R> Observable<R> m10472a(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return ObservableReplay.m9586a(ObservableInternalHelper.m9617a(this, i, j, timeUnit, astVar), (Function) aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final <R> Observable<R> m10471a(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, int i, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return ObservableReplay.m9586a(ObservableInternalHelper.m9618a(this, i), ObservableInternalHelper.m9611a(aunVar, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final <R> Observable<R> m10468a(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, long j, TimeUnit timeUnit) {
        return m10467a(aunVar, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final <R> Observable<R> m10467a(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return ObservableReplay.m9586a(ObservableInternalHelper.m9616a(this, j, timeUnit, astVar), (Function) aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final <R> Observable<R> m10464a(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return ObservableReplay.m9586a(ObservableInternalHelper.m9619a(this), ObservableInternalHelper.m9611a(aunVar, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final ConnectableObservable<T> m10337d(int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        return ObservableReplay.m9580h(this, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final ConnectableObservable<T> m10601a(int i, long j, TimeUnit timeUnit) {
        return m10600a(i, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final ConnectableObservable<T> m10600a(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return ObservableReplay.m9584a(this, j, timeUnit, astVar, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final ConnectableObservable<T> m10598a(int i, Scheduler astVar) {
        ObjectHelper.m9878a(i, "bufferSize");
        return ObservableReplay.m9581a((ConnectableObservable) m10337d(i), astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: g */
    public final ConnectableObservable<T> m10267g(long j, TimeUnit timeUnit) {
        return m10266g(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: g */
    public final ConnectableObservable<T> m10266g(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return ObservableReplay.m9585a(this, j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public final ConnectableObservable<T> m10394b(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return ObservableReplay.m9581a((ConnectableObservable) m10628E(), astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: F */
    public final Observable<T> m10626F() {
        return m10570a((long) cjm.f20759b, Functions.m9909c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10390b(BiPredicate<? super Integer, ? super Throwable> aujVar) {
        ObjectHelper.m9873a(aujVar, "predicate is null");
        return RxJavaPlugins.m9203a(new ObservableRetryBiPredicate(this, aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Observable<T> m10335d(long j) {
        return m10570a(j, Functions.m9909c());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10570a(long j, Predicate<? super Throwable> auxVar) {
        if (j >= 0) {
            ObjectHelper.m9873a(auxVar, "predicate is null");
            return RxJavaPlugins.m9203a(new ObservableRetryPredicate(this, j, auxVar));
        }
        throw new IllegalArgumentException("times >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Observable<T> m10290e(Predicate<? super Throwable> auxVar) {
        return m10570a((long) cjm.f20759b, auxVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10389b(BooleanSupplier aukVar) {
        ObjectHelper.m9873a(aukVar, "stop is null");
        return m10570a((long) cjm.f20759b, Functions.m9926a(aukVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: B */
    public final Observable<T> m10633B(Function<? super Observable<Throwable>, ? extends ObservableSource<?>> aunVar) {
        ObjectHelper.m9873a(aunVar, "handler is null");
        return RxJavaPlugins.m9203a(new ObservableRetryWhen(this, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final void m10297e(Observer<? super T> assVar) {
        ObjectHelper.m9873a(assVar, "observer is null");
        if (assVar instanceof SafeObserver) {
            subscribe(assVar);
        } else {
            subscribe(new SafeObserver(assVar));
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: h */
    public final Observable<T> m10253h(long j, TimeUnit timeUnit) {
        return m10252h(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: b */
    public final Observable<T> m10428b(long j, TimeUnit timeUnit, boolean z) {
        return m10430b(j, timeUnit, Schedulers.m9050a(), z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: h */
    public final Observable<T> m10252h(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableSampleTimed(this, j, timeUnit, astVar, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public final Observable<T> m10430b(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableSampleTimed(this, j, timeUnit, astVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: q */
    public final <U> Observable<T> m10192q(ObservableSource<U> asqVar) {
        ObjectHelper.m9873a(asqVar, "sampler is null");
        return RxJavaPlugins.m9203a(new ObservableSampleWithObservable(this, asqVar, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U> Observable<T> m10493a(ObservableSource<U> asqVar, boolean z) {
        ObjectHelper.m9873a(asqVar, "sampler is null");
        return RxJavaPlugins.m9203a(new ObservableSampleWithObservable(this, asqVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10391b(BiFunction<T, T, T> auiVar) {
        ObjectHelper.m9873a(auiVar, "accumulator is null");
        return RxJavaPlugins.m9203a(new ObservableScan(this, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <R> Observable<R> m10420b(R r, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(r, "initialValue is null");
        return m10358c(Functions.m9932a(r), auiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <R> Observable<R> m10358c(Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(callable, "seedSupplier is null");
        ObjectHelper.m9873a(auiVar, "accumulator is null");
        return RxJavaPlugins.m9203a(new ObservableScanSeed(this, callable, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: G */
    public final Observable<T> m10624G() {
        return RxJavaPlugins.m9203a(new ObservableSerialized(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: H */
    public final Observable<T> m10622H() {
        return m10632C().m9361c();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: I */
    public final Maybe<T> m10620I() {
        return RxJavaPlugins.m9205a(new ObservableSingleMaybe(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final Single<T> m10224k(T t) {
        ObjectHelper.m9873a((Object) t, "defaultItem is null");
        return RxJavaPlugins.m9200a(new ObservableSingleSingle(this, t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: J */
    public final Single<T> m10618J() {
        return RxJavaPlugins.m9200a(new ObservableSingleSingle(this, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Observable<T> m10308e(long j) {
        if (j <= 0) {
            return RxJavaPlugins.m9203a(this);
        }
        return RxJavaPlugins.m9203a(new ObservableSkip(this, j));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: i */
    public final Observable<T> m10242i(long j, TimeUnit timeUnit) {
        return m10187r(m10432b(j, timeUnit));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: i */
    public final Observable<T> m10241i(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10187r(m10431b(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Observable<T> m10309e(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count >= 0 required but it was " + i);
        } else if (i == 0) {
            return RxJavaPlugins.m9203a(this);
        } else {
            return RxJavaPlugins.m9203a(new ObservableSkipLast(this, i));
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17510f)
    /* renamed from: j */
    public final Observable<T> m10233j(long j, TimeUnit timeUnit) {
        return m10572a(j, timeUnit, Schedulers.m9046c(), false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17510f)
    /* renamed from: c */
    public final Observable<T> m10364c(long j, TimeUnit timeUnit, boolean z) {
        return m10572a(j, timeUnit, Schedulers.m9046c(), z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: j */
    public final Observable<T> m10232j(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10572a(j, timeUnit, astVar, false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: c */
    public final Observable<T> m10365c(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        return m10572a(j, timeUnit, astVar, z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<T> m10572a(long j, TimeUnit timeUnit, Scheduler astVar, boolean z, int i) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableSkipLastTimed(this, j, timeUnit, astVar, i << 1, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: r */
    public final <U> Observable<T> m10187r(ObservableSource<U> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableSkipUntil(this, asqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Observable<T> m10272f(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9203a(new ObservableSkipWhile(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: K */
    public final Observable<T> m10616K() {
        return m10608O().m10024o().m10174v(Functions.m9931a(Functions.m9902h())).m10186r(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<T> m10548a(Comparator<? super T> comparator) {
        ObjectHelper.m9873a(comparator, "sortFunction is null");
        return m10608O().m10024o().m10174v(Functions.m9931a((Comparator) comparator)).m10186r(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Observable<T> m10251h(Iterable<? extends T> iterable) {
        return m10375b(m10303e((Iterable) iterable), this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: s */
    public final Observable<T> m10184s(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10375b(asqVar, this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: l */
    public final Observable<T> m10218l(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return m10375b(m10561a(t), this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<T> m10376b(T... tArr) {
        Observable a = m10443a((Object[]) tArr);
        return a == m10310e() ? RxJavaPlugins.m9203a(this) : m10375b(a, this);
    }

    @SchedulerSupport(m10000a = "none")
    /* renamed from: L */
    public final Disposable m10614L() {
        return m10478a((Consumer) Functions.m9914b(), (Consumer<? super Throwable>) Functions.f17560f, Functions.f17557c, Functions.m9914b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: j */
    public final Disposable m10229j(Consumer<? super T> aumVar) {
        return m10478a((Consumer) aumVar, (Consumer<? super Throwable>) Functions.f17560f, Functions.f17557c, Functions.m9914b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Disposable m10387b(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2) {
        return m10478a((Consumer) aumVar, aumVar2, Functions.f17557c, Functions.m9914b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Disposable m10386b(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar) {
        return m10478a((Consumer) aumVar, aumVar2, augVar, Functions.m9914b());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Disposable m10478a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Consumer<? super Disposable> aumVar3) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ObjectHelper.m9873a(aumVar3, "onSubscribe is null");
        LambdaObserver awrVar = new LambdaObserver(aumVar, aumVar2, augVar, aumVar3);
        subscribe(awrVar);
        return awrVar;
    }

    @Override // p110z1.ObservableSource
    @SchedulerSupport(m10000a = "none")
    public final void subscribe(Observer<? super T> assVar) {
        ObjectHelper.m9873a(assVar, "observer is null");
        try {
            Observer<? super T> a = RxJavaPlugins.m9202a(this, assVar);
            ObjectHelper.m9873a(a, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            mo34a((Observer) a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            RxJavaPlugins.m9212a(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final <E extends Observer<? super T>> E m10278f(E e) {
        subscribe(e);
        return e;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: c */
    public final Observable<T> m10350c(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableSubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: t */
    public final Observable<T> m10181t(ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableSwitchIfEmpty(this, asqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: C */
    public final <R> Observable<R> m10631C(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        return m10246h(aunVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final <R> Observable<R> m10246h(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.m9203a(new ObservableSwitchMap(this, aunVar, i, false));
        }
        Object call = ((ScalarCallable) this).call();
        if (call == null) {
            return m10310e();
        }
        return ObservableScalarXMap.m9576a(call, aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: D */
    public final Completable m10629D(@AbstractC3889atm Function<? super T, ? extends CompletableSource> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9209a(new ObservableSwitchMapCompletable(this, aunVar, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: E */
    public final Completable m10627E(@AbstractC3889atm Function<? super T, ? extends CompletableSource> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9209a(new ObservableSwitchMapCompletable(this, aunVar, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: F */
    public final <R> Observable<R> m10625F(@AbstractC3889atm Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableSwitchMapMaybe(this, aunVar, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: G */
    public final <R> Observable<R> m10623G(@AbstractC3889atm Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableSwitchMapMaybe(this, aunVar, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: H */
    public final <R> Observable<R> m10621H(@AbstractC3889atm Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableSwitchMapSingle(this, aunVar, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @AbstractC3889atm
    /* renamed from: I */
    public final <R> Observable<R> m10619I(@AbstractC3889atm Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9203a(new ObservableSwitchMapSingle(this, aunVar, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: J */
    public final <R> Observable<R> m10617J(Function<? super T, ? extends ObservableSource<? extends R>> aunVar) {
        return m10236i(aunVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: i */
    public final <R> Observable<R> m10236i(Function<? super T, ? extends ObservableSource<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.m9203a(new ObservableSwitchMap(this, aunVar, i, true));
        }
        Object call = ((ScalarCallable) this).call();
        if (call == null) {
            return m10310e();
        }
        return ObservableScalarXMap.m9576a(call, aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Observable<T> m10286f(long j) {
        if (j >= 0) {
            return RxJavaPlugins.m9203a(new ObservableTake(this, j));
        }
        throw new IllegalArgumentException("count >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: k */
    public final Observable<T> m10226k(long j, TimeUnit timeUnit) {
        return m10178u(m10432b(j, timeUnit));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: k */
    public final Observable<T> m10225k(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10178u(m10431b(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final Observable<T> m10287f(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count >= 0 required but it was " + i);
        } else if (i == 0) {
            return RxJavaPlugins.m9203a(new ObservableIgnoreElements(this));
        } else {
            if (i == 1) {
                return RxJavaPlugins.m9203a(new ObservableTakeLastOne(this));
            }
            return RxJavaPlugins.m9203a(new ObservableTakeLast(this, i));
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17510f)
    /* renamed from: c */
    public final Observable<T> m10369c(long j, long j2, TimeUnit timeUnit) {
        return m10588a(j, j2, timeUnit, Schedulers.m9046c(), false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: c */
    public final Observable<T> m10368c(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        return m10588a(j, j2, timeUnit, astVar, false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<T> m10588a(long j, long j2, TimeUnit timeUnit, Scheduler astVar, boolean z, int i) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        if (j >= 0) {
            return RxJavaPlugins.m9203a(new ObservableTakeLastTimed(this, j, j2, timeUnit, astVar, i, z));
        }
        throw new IndexOutOfBoundsException("count >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17510f)
    /* renamed from: l */
    public final Observable<T> m10220l(long j, TimeUnit timeUnit) {
        return m10429b(j, timeUnit, Schedulers.m9046c(), false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17510f)
    /* renamed from: d */
    public final Observable<T> m10329d(long j, TimeUnit timeUnit, boolean z) {
        return m10429b(j, timeUnit, Schedulers.m9046c(), z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: l */
    public final Observable<T> m10219l(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10429b(j, timeUnit, astVar, false, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: d */
    public final Observable<T> m10330d(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        return m10429b(j, timeUnit, astVar, z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: b */
    public final Observable<T> m10429b(long j, TimeUnit timeUnit, Scheduler astVar, boolean z, int i) {
        return m10588a((long) cjm.f20759b, j, timeUnit, astVar, z, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: u */
    public final <U> Observable<T> m10178u(ObservableSource<U> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return RxJavaPlugins.m9203a(new ObservableTakeUntil(this, asqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final Observable<T> m10257g(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "stopPredicate is null");
        return RxJavaPlugins.m9203a(new ObservableTakeUntilPredicate(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Observable<T> m10244h(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9203a(new ObservableTakeWhile(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: m */
    public final Observable<T> m10214m(long j, TimeUnit timeUnit) {
        return m10213m(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: m */
    public final Observable<T> m10213m(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableThrottleFirstTimed(this, j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: n */
    public final Observable<T> m10209n(long j, TimeUnit timeUnit) {
        return m10253h(j, timeUnit);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: n */
    public final Observable<T> m10208n(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10252h(j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: o */
    public final Observable<T> m10204o(long j, TimeUnit timeUnit) {
        return m10305e(j, timeUnit, Schedulers.m9050a(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: e */
    public final Observable<T> m10304e(long j, TimeUnit timeUnit, boolean z) {
        return m10305e(j, timeUnit, Schedulers.m9050a(), z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: o */
    public final Observable<T> m10203o(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10305e(j, timeUnit, astVar, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: e */
    public final Observable<T> m10305e(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableThrottleLatest(this, j, timeUnit, astVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: p */
    public final Observable<T> m10199p(long j, TimeUnit timeUnit) {
        return m10332d(j, timeUnit);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: p */
    public final Observable<T> m10198p(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10331d(j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: M */
    public final Observable<Timed<T>> m10612M() {
        return m10532a(TimeUnit.MILLISECONDS, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final Observable<Timed<T>> m10319d(Scheduler astVar) {
        return m10532a(TimeUnit.MILLISECONDS, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<Timed<T>> m10533a(TimeUnit timeUnit) {
        return m10532a(timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<Timed<T>> m10532a(TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableTimeInterval(this, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: K */
    public final <V> Observable<T> m10615K(Function<? super T, ? extends ObservableSource<V>> aunVar) {
        return m10396b((ObservableSource) null, aunVar, (ObservableSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <V> Observable<T> m10465a(Function<? super T, ? extends ObservableSource<V>> aunVar, ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10396b((ObservableSource) null, aunVar, asqVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: q */
    public final Observable<T> m10194q(long j, TimeUnit timeUnit) {
        return m10581a(j, timeUnit, (ObservableSource) null, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final Observable<T> m10582a(long j, TimeUnit timeUnit, ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10581a(j, timeUnit, asqVar, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<T> m10574a(long j, TimeUnit timeUnit, Scheduler astVar, ObservableSource<? extends T> asqVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10581a(j, timeUnit, asqVar, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: q */
    public final Observable<T> m10193q(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10581a(j, timeUnit, (ObservableSource) null, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final <U, V> Observable<T> m10321d(ObservableSource<U> asqVar, Function<? super T, ? extends ObservableSource<V>> aunVar) {
        ObjectHelper.m9873a(asqVar, "firstTimeoutIndicator is null");
        return m10396b(asqVar, aunVar, (ObservableSource) null);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, V> Observable<T> m10495a(ObservableSource<U> asqVar, Function<? super T, ? extends ObservableSource<V>> aunVar, ObservableSource<? extends T> asqVar2) {
        ObjectHelper.m9873a(asqVar, "firstTimeoutIndicator is null");
        ObjectHelper.m9873a(asqVar2, "other is null");
        return m10396b(asqVar, aunVar, asqVar2);
    }

    /* renamed from: a */
    private Observable<T> m10581a(long j, TimeUnit timeUnit, ObservableSource<? extends T> asqVar, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "timeUnit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableTimeoutTimed(this, j, timeUnit, astVar, asqVar));
    }

    /* renamed from: b */
    private <U, V> Observable<T> m10396b(ObservableSource<U> asqVar, Function<? super T, ? extends ObservableSource<V>> aunVar, ObservableSource<? extends T> asqVar2) {
        ObjectHelper.m9873a(aunVar, "itemTimeoutIndicator is null");
        return RxJavaPlugins.m9203a(new ObservableTimeout(this, asqVar, aunVar, asqVar2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: N */
    public final Observable<Timed<T>> m10610N() {
        return m10414b(TimeUnit.MILLISECONDS, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final Observable<Timed<T>> m10296e(Scheduler astVar) {
        return m10414b(TimeUnit.MILLISECONDS, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<Timed<T>> m10415b(TimeUnit timeUnit) {
        return m10414b(timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<Timed<T>> m10414b(TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return (Observable<Timed<T>>) m10174v(Functions.m9929a(timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: L */
    public final <R> R m10613L(Function<? super Observable<T>, R> aunVar) {
        try {
            return (R) ((Function) ObjectHelper.m9873a(aunVar, "converter is null")).apply(this);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: O */
    public final Single<List<T>> m10608O() {
        return m10269g(16);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final Single<List<T>> m10269g(int i) {
        ObjectHelper.m9878a(i, "capacityHint");
        return RxJavaPlugins.m9200a(new ObservableToListSingle(this, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final <U extends Collection<? super T>> Single<U> m10301e(Callable<U> callable) {
        ObjectHelper.m9873a(callable, "collectionSupplier is null");
        return RxJavaPlugins.m9200a(new ObservableToListSingle(this, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: M */
    public final <K> Single<Map<K, T>> m10611M(Function<? super T, ? extends K> aunVar) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        return (Single<Map<K, T>>) m10417b(HashMapSupplier.asCallable(), Functions.m9924a((Function) aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <K, V> Single<Map<K, V>> m10381b(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        return (Single<Map<K, V>>) m10417b(HashMapSupplier.asCallable(), Functions.m9923a(aunVar, aunVar2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <K, V> Single<Map<K, V>> m10380b(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, Callable<? extends Map<K, V>> callable) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        ObjectHelper.m9873a(callable, "mapSupplier is null");
        return (Single<Map<K, V>>) m10417b(callable, Functions.m9923a(aunVar, aunVar2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: N */
    public final <K> Single<Map<K, Collection<T>>> m10609N(Function<? super T, ? extends K> aunVar) {
        return (Single<Map<K, Collection<T>>>) m10455a((Function) aunVar, (Function) Functions.m9935a(), (Callable) HashMapSupplier.asCallable(), (Function) ArrayListSupplier.asFunction());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <K, V> Single<Map<K, Collection<V>>> m10345c(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2) {
        return m10455a((Function) aunVar, (Function) aunVar2, (Callable) HashMapSupplier.asCallable(), (Function) ArrayListSupplier.asFunction());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <K, V> Single<Map<K, Collection<V>>> m10455a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, Callable<? extends Map<K, Collection<V>>> callable, Function<? super K, ? extends Collection<? super V>> aunVar3) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        ObjectHelper.m9873a(callable, "mapSupplier is null");
        ObjectHelper.m9873a(aunVar3, "collectionFactory is null");
        return (Single<Map<K, Collection<V>>>) m10417b(callable, Functions.m9922a(aunVar, aunVar2, aunVar3));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <K, V> Single<Map<K, Collection<V>>> m10344c(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, Callable<Map<K, Collection<V>>> callable) {
        return m10455a((Function) aunVar, (Function) aunVar2, (Callable) callable, (Function) ArrayListSupplier.asFunction());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: a */
    public final Flowable<T> m10531a(BackpressureStrategy arlVar) {
        FlowableFromObservable bbaVar = new FlowableFromObservable(this);
        switch (arlVar) {
            case DROP:
                return bbaVar.m10809z();
            case LATEST:
                return bbaVar.m11307A();
            case MISSING:
                return bbaVar;
            case ERROR:
                return RxJavaPlugins.m9207a(new FlowableOnBackpressureError(bbaVar));
            default:
                return bbaVar.m10811y();
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: P */
    public final Single<List<T>> m10607P() {
        return m10419b((Comparator) Functions.m9904f());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Single<List<T>> m10419b(Comparator<? super T> comparator) {
        ObjectHelper.m9873a(comparator, "comparator is null");
        return (Single<List<T>>) m10608O().m10035i(Functions.m9931a((Comparator) comparator));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Single<List<T>> m10547a(Comparator<? super T> comparator, int i) {
        ObjectHelper.m9873a(comparator, "comparator is null");
        return (Single<List<T>>) m10269g(i).m10035i(Functions.m9931a((Comparator) comparator));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: h */
    public final Single<List<T>> m10254h(int i) {
        return m10547a(Functions.m9904f(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: f */
    public final Observable<T> m10277f(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9203a(new ObservableUnsubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final Observable<Observable<T>> m10268g(long j) {
        return m10595a(j, j, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final Observable<Observable<T>> m10435b(long j, long j2) {
        return m10595a(j, j2, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final Observable<Observable<T>> m10595a(long j, long j2, int i) {
        ObjectHelper.m9876a(j, "count");
        ObjectHelper.m9876a(j2, MSVSSConstants.WRITABLE_SKIP);
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableWindow(this, j, j2, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: d */
    public final Observable<Observable<T>> m10334d(long j, long j2, TimeUnit timeUnit) {
        return m10590a(j, j2, timeUnit, Schedulers.m9050a(), m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: d */
    public final Observable<Observable<T>> m10333d(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        return m10590a(j, j2, timeUnit, astVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<Observable<T>> m10590a(long j, long j2, TimeUnit timeUnit, Scheduler astVar, int i) {
        ObjectHelper.m9876a(j, "timespan");
        ObjectHelper.m9876a(j2, "timeskip");
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        return RxJavaPlugins.m9203a(new ObservableWindowTimed(this, j, j2, timeUnit, astVar, cjm.f20759b, i, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: r */
    public final Observable<Observable<T>> m10189r(long j, TimeUnit timeUnit) {
        return m10576a(j, timeUnit, Schedulers.m9050a(), (long) cjm.f20759b, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final Observable<Observable<T>> m10584a(long j, TimeUnit timeUnit, long j2) {
        return m10576a(j, timeUnit, Schedulers.m9050a(), j2, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    /* renamed from: a */
    public final Observable<Observable<T>> m10583a(long j, TimeUnit timeUnit, long j2, boolean z) {
        return m10576a(j, timeUnit, Schedulers.m9050a(), j2, z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: r */
    public final Observable<Observable<T>> m10188r(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10576a(j, timeUnit, astVar, (long) cjm.f20759b, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<Observable<T>> m10577a(long j, TimeUnit timeUnit, Scheduler astVar, long j2) {
        return m10576a(j, timeUnit, astVar, j2, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<Observable<T>> m10576a(long j, TimeUnit timeUnit, Scheduler astVar, long j2, boolean z) {
        return m10575a(j, timeUnit, astVar, j2, z, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    /* renamed from: a */
    public final Observable<Observable<T>> m10575a(long j, TimeUnit timeUnit, Scheduler astVar, long j2, boolean z, int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9876a(j2, "count");
        return RxJavaPlugins.m9203a(new ObservableWindowTimed(this, j, j, timeUnit, astVar, j2, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: v */
    public final <B> Observable<Observable<T>> m10175v(ObservableSource<B> asqVar) {
        return m10262g(asqVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: g */
    public final <B> Observable<Observable<T>> m10262g(ObservableSource<B> asqVar, int i) {
        ObjectHelper.m9873a(asqVar, "boundary is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableWindowBoundary(this, asqVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: e */
    public final <U, V> Observable<Observable<T>> m10298e(ObservableSource<U> asqVar, Function<? super U, ? extends ObservableSource<V>> aunVar) {
        return m10497a(asqVar, aunVar, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, V> Observable<Observable<T>> m10497a(ObservableSource<U> asqVar, Function<? super U, ? extends ObservableSource<V>> aunVar, int i) {
        ObjectHelper.m9873a(asqVar, "openingIndicator is null");
        ObjectHelper.m9873a(aunVar, "closingIndicator is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableWindowBoundarySelector(this, asqVar, aunVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: f */
    public final <B> Observable<Observable<T>> m10281f(Callable<? extends ObservableSource<B>> callable) {
        return m10545a(callable, m10338d());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <B> Observable<Observable<T>> m10545a(Callable<? extends ObservableSource<B>> callable, int i) {
        ObjectHelper.m9873a(callable, "boundary is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9203a(new ObservableWindowBoundarySupplier(this, callable, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10501a(ObservableSource<? extends U> asqVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        ObjectHelper.m9873a(auiVar, "combiner is null");
        return RxJavaPlugins.m9203a(new ObservableWithLatestFrom(this, auiVar, asqVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <T1, T2, R> Observable<R> m10502a(ObservableSource<T1> asqVar, ObservableSource<T2> asqVar2, Function3<? super T, ? super T1, ? super T2, R> auoVar) {
        ObjectHelper.m9873a(asqVar, "o1 is null");
        ObjectHelper.m9873a(asqVar2, "o2 is null");
        ObjectHelper.m9873a(auoVar, "combiner is null");
        return m10339c(new ObservableSource[]{asqVar, asqVar2}, Functions.m9921a((Function3) auoVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <T1, T2, T3, R> Observable<R> m10508a(ObservableSource<T1> asqVar, ObservableSource<T2> asqVar2, ObservableSource<T3> asqVar3, Function4<? super T, ? super T1, ? super T2, ? super T3, R> aupVar) {
        ObjectHelper.m9873a(asqVar, "o1 is null");
        ObjectHelper.m9873a(asqVar2, "o2 is null");
        ObjectHelper.m9873a(asqVar3, "o3 is null");
        ObjectHelper.m9873a(aupVar, "combiner is null");
        return m10339c(new ObservableSource[]{asqVar, asqVar2, asqVar3}, Functions.m9920a((Function4) aupVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <T1, T2, T3, T4, R> Observable<R> m10510a(ObservableSource<T1> asqVar, ObservableSource<T2> asqVar2, ObservableSource<T3> asqVar3, ObservableSource<T4> asqVar4, Function5<? super T, ? super T1, ? super T2, ? super T3, ? super T4, R> auqVar) {
        ObjectHelper.m9873a(asqVar, "o1 is null");
        ObjectHelper.m9873a(asqVar2, "o2 is null");
        ObjectHelper.m9873a(asqVar3, "o3 is null");
        ObjectHelper.m9873a(asqVar4, "o4 is null");
        ObjectHelper.m9873a(auqVar, "combiner is null");
        return m10339c(new ObservableSource[]{asqVar, asqVar2, asqVar3, asqVar4}, Functions.m9919a((Function5) auqVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: c */
    public final <R> Observable<R> m10339c(ObservableSource<?>[] asqVarArr, Function<? super Object[], R> aunVar) {
        ObjectHelper.m9873a(asqVarArr, "others is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        return RxJavaPlugins.m9203a(new ObservableWithLatestFromMany(this, asqVarArr, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: d */
    public final <R> Observable<R> m10327d(Iterable<? extends ObservableSource<?>> iterable, Function<? super Object[], R> aunVar) {
        ObjectHelper.m9873a(iterable, "others is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        return RxJavaPlugins.m9203a(new ObservableWithLatestFromMany(this, iterable, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10565a(Iterable<U> iterable, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(iterable, "other is null");
        ObjectHelper.m9873a(auiVar, "zipper is null");
        return RxJavaPlugins.m9203a(new ObservableZipIterable(this, iterable, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: b */
    public final <U, R> Observable<R> m10398b(ObservableSource<? extends U> asqVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(asqVar, "other is null");
        return m10399b(this, asqVar, auiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10500a(ObservableSource<? extends U> asqVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z) {
        return m10506a(this, asqVar, auiVar, z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final <U, R> Observable<R> m10499a(ObservableSource<? extends U> asqVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z, int i) {
        return m10505a(this, asqVar, auiVar, z, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: Q */
    public final TestObserver<T> m10606Q() {
        TestObserver<T> btzVar = new TestObserver<>();
        subscribe(btzVar);
        return btzVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    /* renamed from: a */
    public final TestObserver<T> m10444a(boolean z) {
        TestObserver<T> btzVar = new TestObserver<>();
        if (z) {
            btzVar.dispose();
        }
        subscribe(btzVar);
        return btzVar;
    }
}
