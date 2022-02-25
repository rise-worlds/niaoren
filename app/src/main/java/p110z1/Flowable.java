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
import p110z1.FlowableInternalHelper;

/* renamed from: z1.arv */
/* loaded from: classes3.dex */
public abstract class Flowable<T> implements Publisher<T> {

    /* renamed from: a */
    static final int f17448a = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    /* renamed from: d */
    protected abstract void mo9054d(Subscriber<? super T> dbxVar);

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11230a(Iterable<? extends Publisher<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9207a(new FlowableAmb(null, iterable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11097a(Publisher<? extends T>... dbwVarArr) {
        ObjectHelper.m9873a(dbwVarArr, "sources is null");
        int length = dbwVarArr.length;
        if (length == 0) {
            return m11094b();
        }
        if (length == 1) {
            return m10964d((Publisher) dbwVarArr[0]);
        }
        return RxJavaPlugins.m9207a(new FlowableAmb(dbwVarArr, null));
    }

    /* renamed from: a */
    public static int m11274a() {
        return f17448a;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T, R> Flowable<R> m11096a(Publisher<? extends T>[] dbwVarArr, Function<? super Object[], ? extends R> aunVar) {
        return m11095a(dbwVarArr, aunVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T, R> Flowable<R> m11137a(Function<? super Object[], ? extends R> aunVar, Publisher<? extends T>... dbwVarArr) {
        return m11095a(dbwVarArr, aunVar, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Flowable<R> m11095a(Publisher<? extends T>[] dbwVarArr, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9873a(dbwVarArr, "sources is null");
        if (dbwVarArr.length == 0) {
            return m11094b();
        }
        ObjectHelper.m9873a(aunVar, "combiner is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableCombineLatest((Publisher[]) dbwVarArr, (Function) aunVar, i, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T, R> Flowable<R> m11226a(Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        return m11225a(iterable, aunVar, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Flowable<R> m11225a(Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9873a(iterable, "sources is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableCombineLatest((Iterable) iterable, (Function) aunVar, i, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T, R> Flowable<R> m11024b(Publisher<? extends T>[] dbwVarArr, Function<? super Object[], ? extends R> aunVar) {
        return m11023b(dbwVarArr, aunVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T, R> Flowable<R> m11046b(Function<? super Object[], ? extends R> aunVar, Publisher<? extends T>... dbwVarArr) {
        return m11023b(dbwVarArr, aunVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T, R> Flowable<R> m11159a(Function<? super Object[], ? extends R> aunVar, int i, Publisher<? extends T>... dbwVarArr) {
        return m11023b(dbwVarArr, aunVar, i);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T, R> Flowable<R> m11023b(Publisher<? extends T>[] dbwVarArr, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9873a(dbwVarArr, "sources is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        ObjectHelper.m9878a(i, "bufferSize");
        if (dbwVarArr.length == 0) {
            return m11094b();
        }
        return RxJavaPlugins.m9207a(new FlowableCombineLatest((Publisher[]) dbwVarArr, (Function) aunVar, i, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T, R> Flowable<R> m11077b(Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        return m11076b(iterable, aunVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T, R> Flowable<R> m11076b(Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, int i) {
        ObjectHelper.m9873a(iterable, "sources is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableCombineLatest((Iterable) iterable, (Function) aunVar, i, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T1, T2, R> Flowable<R> m11118a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m11137a(Functions.m9927a((BiFunction) auiVar), dbwVar, dbwVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, R> Flowable<R> m11111a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Function3<? super T1, ? super T2, ? super T3, ? extends R> auoVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        return m11137a(Functions.m9921a((Function3) auoVar), dbwVar, dbwVar2, dbwVar3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, R> Flowable<R> m11108a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> aupVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        return m11137a(Functions.m9920a((Function4) aupVar), dbwVar, dbwVar2, dbwVar3, dbwVar4);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, R> Flowable<R> m11106a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> auqVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        return m11137a(Functions.m9919a((Function5) auqVar), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, R> Flowable<R> m11105a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> aurVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        return m11137a(Functions.m9918a((Function6) aurVar), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, R> Flowable<R> m11104a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Publisher<? extends T7> dbwVar7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> ausVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        ObjectHelper.m9873a(dbwVar7, "source7 is null");
        return m11137a(Functions.m9917a((Function7) ausVar), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6, dbwVar7);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Flowable<R> m11103a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Publisher<? extends T7> dbwVar7, Publisher<? extends T8> dbwVar8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> autVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        ObjectHelper.m9873a(dbwVar7, "source7 is null");
        ObjectHelper.m9873a(dbwVar8, "source8 is null");
        return m11137a(Functions.m9916a((Function8) autVar), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6, dbwVar7, dbwVar8);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Flowable<R> m11102a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Publisher<? extends T7> dbwVar7, Publisher<? extends T8> dbwVar8, Publisher<? extends T9> dbwVar9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> auuVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        ObjectHelper.m9873a(dbwVar7, "source7 is null");
        ObjectHelper.m9873a(dbwVar8, "source8 is null");
        ObjectHelper.m9873a(dbwVar9, "source9 is null");
        return m11137a(Functions.m9915a((Function9) auuVar), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6, dbwVar7, dbwVar8, dbwVar9);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m11080b(Iterable<? extends Publisher<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return m10952e((Iterable) iterable).m11160a(Functions.m9935a(), 2, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T> Flowable<T> m11132a(Publisher<? extends Publisher<? extends T>> dbwVar) {
        return m11131a(dbwVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T> Flowable<T> m11131a(Publisher<? extends Publisher<? extends T>> dbwVar, int i) {
        return m10964d((Publisher) dbwVar).m11166a(Functions.m9935a(), i);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11120a(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m11025b(dbwVar, dbwVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11112a(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, Publisher<? extends T> dbwVar3) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        return m11025b(dbwVar, dbwVar2, dbwVar3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11109a(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, Publisher<? extends T> dbwVar3, Publisher<? extends T> dbwVar4) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        return m11025b(dbwVar, dbwVar2, dbwVar3, dbwVar4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T> Flowable<T> m11025b(Publisher<? extends T>... dbwVarArr) {
        if (dbwVarArr.length == 0) {
            return m11094b();
        }
        if (dbwVarArr.length == 1) {
            return m10964d((Publisher) dbwVarArr[0]);
        }
        return RxJavaPlugins.m9207a(new FlowableConcatArray(dbwVarArr, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static <T> Flowable<T> m10988c(Publisher<? extends T>... dbwVarArr) {
        if (dbwVarArr.length == 0) {
            return m11094b();
        }
        if (dbwVarArr.length == 1) {
            return m10964d((Publisher) dbwVarArr[0]);
        }
        return RxJavaPlugins.m9207a(new FlowableConcatArray(dbwVarArr, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Flowable<T> m10960d(Publisher<? extends T>... dbwVarArr) {
        return m11270a(m11274a(), m11274a(), dbwVarArr);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11270a(int i, int i2, Publisher<? extends T>... dbwVarArr) {
        ObjectHelper.m9873a(dbwVarArr, "sources is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapEager(new FlowableFromArray(dbwVarArr), Functions.m9935a(), i, i2, ErrorMode.IMMEDIATE));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public static <T> Flowable<T> m10938e(Publisher<? extends T>... dbwVarArr) {
        return m11091b(m11274a(), m11274a(), dbwVarArr);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T> Flowable<T> m11091b(int i, int i2, Publisher<? extends T>... dbwVarArr) {
        return m11098a((Object[]) dbwVarArr).m11164a(Functions.m9935a(), i, i2, true);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m11011c(Iterable<? extends Publisher<? extends T>> iterable) {
        ObjectHelper.m9873a(iterable, "sources is null");
        return m10952e((Iterable) iterable).m10969d(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T> Flowable<T> m11044b(Publisher<? extends Publisher<? extends T>> dbwVar) {
        return m11129a((Publisher) dbwVar, m11274a(), true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T> Flowable<T> m11129a(Publisher<? extends Publisher<? extends T>> dbwVar, int i, boolean z) {
        return m10964d((Publisher) dbwVar).m11160a(Functions.m9935a(), i, z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static <T> Flowable<T> m10995c(Publisher<? extends Publisher<? extends T>> dbwVar) {
        return m11130a(dbwVar, m11274a(), m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11130a(Publisher<? extends Publisher<? extends T>> dbwVar, int i, int i2) {
        ObjectHelper.m9873a(dbwVar, "sources is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapEagerPublisher(dbwVar, Functions.m9935a(), i, i2, ErrorMode.IMMEDIATE));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Flowable<T> m10976d(Iterable<? extends Publisher<? extends T>> iterable) {
        return m11228a(iterable, m11274a(), m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11228a(Iterable<? extends Publisher<? extends T>> iterable, int i, int i2) {
        ObjectHelper.m9873a(iterable, "sources is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapEager(new FlowableFromIterable(iterable), Functions.m9935a(), i, i2, ErrorMode.IMMEDIATE));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11189a(FlowableOnSubscribe<T> aryVar, BackpressureStrategy arlVar) {
        ObjectHelper.m9873a(aryVar, "source is null");
        ObjectHelper.m9873a(arlVar, "mode is null");
        return RxJavaPlugins.m9207a(new FlowableCreate(aryVar, arlVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11208a(Callable<? extends Publisher<? extends T>> callable) {
        ObjectHelper.m9873a(callable, "supplier is null");
        return RxJavaPlugins.m9207a(new FlowableDefer(callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: b */
    public static <T> Flowable<T> m11094b() {
        return RxJavaPlugins.m9207a(FlowableEmpty.f18076b);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m11072b(Callable<? extends Throwable> callable) {
        ObjectHelper.m9873a(callable, "supplier is null");
        return RxJavaPlugins.m9207a(new FlowableError(callable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11211a(Throwable th) {
        ObjectHelper.m9873a(th, "throwable is null");
        return m11072b((Callable<? extends Throwable>) Functions.m9932a(th));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11098a(T... tArr) {
        ObjectHelper.m9873a(tArr, "items is null");
        if (tArr.length == 0) {
            return m11094b();
        }
        if (tArr.length == 1) {
            return m11223a(tArr[0]);
        }
        return RxJavaPlugins.m9207a(new FlowableFromArray(tArr));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m11007c(Callable<? extends T> callable) {
        ObjectHelper.m9873a(callable, "supplier is null");
        return RxJavaPlugins.m9207a((Flowable) new FlowableFromCallable(callable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11199a(Future<? extends T> future) {
        ObjectHelper.m9873a(future, "future is null");
        return RxJavaPlugins.m9207a(new FlowableFromFuture(future, 0L, null));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11198a(Future<? extends T> future, long j, TimeUnit timeUnit) {
        ObjectHelper.m9873a(future, "future is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        return RxJavaPlugins.m9207a(new FlowableFromFuture(future, j, timeUnit));
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11197a(Future<? extends T> future, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return m11198a(future, j, timeUnit).m11005c(astVar);
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11196a(Future<? extends T> future, Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return m11199a((Future) future).m11005c(astVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public static <T> Flowable<T> m10952e(Iterable<? extends T> iterable) {
        ObjectHelper.m9873a(iterable, "source is null");
        return RxJavaPlugins.m9207a(new FlowableFromIterable(iterable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public static <T> Flowable<T> m10964d(Publisher<? extends T> dbwVar) {
        if (dbwVar instanceof Flowable) {
            return RxJavaPlugins.m9207a((Flowable) dbwVar);
        }
        ObjectHelper.m9873a(dbwVar, "source is null");
        return RxJavaPlugins.m9207a(new FlowableFromPublisher(dbwVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11176a(Consumer<Emitter<T>> aumVar) {
        ObjectHelper.m9873a(aumVar, "generator is null");
        return m11202a(Functions.m9905e(), FlowableInternalHelper.m9780a(aumVar), Functions.m9914b());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, S> Flowable<T> m11205a(Callable<S> callable, BiConsumer<S, Emitter<T>> auhVar) {
        ObjectHelper.m9873a(auhVar, "generator is null");
        return m11202a((Callable) callable, FlowableInternalHelper.m9781a(auhVar), Functions.m9914b());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, S> Flowable<T> m11204a(Callable<S> callable, BiConsumer<S, Emitter<T>> auhVar, Consumer<? super S> aumVar) {
        ObjectHelper.m9873a(auhVar, "generator is null");
        return m11202a((Callable) callable, FlowableInternalHelper.m9781a(auhVar), (Consumer) aumVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T, S> Flowable<T> m11203a(Callable<S> callable, BiFunction<S, Emitter<T>, S> auiVar) {
        return m11202a((Callable) callable, (BiFunction) auiVar, Functions.m9914b());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, S> Flowable<T> m11202a(Callable<S> callable, BiFunction<S, Emitter<T>, S> auiVar, Consumer<? super S> aumVar) {
        ObjectHelper.m9873a(callable, "initialState is null");
        ObjectHelper.m9873a(auiVar, "generator is null");
        ObjectHelper.m9873a(aumVar, "disposeState is null");
        return RxJavaPlugins.m9207a(new FlowableGenerate(callable, auiVar, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public static Flowable<Long> m11256a(long j, long j2, TimeUnit timeUnit) {
        return m11255a(j, j2, timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static Flowable<Long> m11255a(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableInterval(Math.max(0L, j), Math.max(0L, j2), timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public static Flowable<Long> m11250a(long j, TimeUnit timeUnit) {
        return m11255a(j, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public static Flowable<Long> m11246a(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11255a(j, j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public static Flowable<Long> m11258a(long j, long j2, long j3, long j4, TimeUnit timeUnit) {
        return m11257a(j, j2, j3, j4, timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static Flowable<Long> m11257a(long j, long j2, long j3, long j4, TimeUnit timeUnit, Scheduler astVar) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + j2);
        } else if (i == 0) {
            return m11094b().m10955e(j3, timeUnit, astVar);
        } else {
            long j5 = j + (j2 - 1);
            if (j <= 0 || j5 >= 0) {
                ObjectHelper.m9873a(timeUnit, "unit is null");
                ObjectHelper.m9873a(astVar, "scheduler is null");
                return RxJavaPlugins.m9207a(new FlowableIntervalRange(j, j5, Math.max(0L, j3), Math.max(0L, j4), timeUnit, astVar));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11223a(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return RxJavaPlugins.m9207a((Flowable) new FlowableJust(t));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11222a(T t, T t2) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        return m11098a(t, t2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11221a(T t, T t2, T t3) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        return m11098a(t, t2, t3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11220a(T t, T t2, T t3, T t4) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        return m11098a(t, t2, t3, t4);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11219a(T t, T t2, T t3, T t4, T t5) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        return m11098a(t, t2, t3, t4, t5);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11218a(T t, T t2, T t3, T t4, T t5, T t6) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        return m11098a(t, t2, t3, t4, t5, t6);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11217a(T t, T t2, T t3, T t4, T t5, T t6, T t7) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        ObjectHelper.m9873a((Object) t7, "item7 is null");
        return m11098a(t, t2, t3, t4, t5, t6, t7);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11216a(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        ObjectHelper.m9873a((Object) t7, "item7 is null");
        ObjectHelper.m9873a((Object) t8, "item8 is null");
        return m11098a(t, t2, t3, t4, t5, t6, t7, t8);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11215a(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9) {
        ObjectHelper.m9873a((Object) t, "item1 is null");
        ObjectHelper.m9873a((Object) t2, "item2 is null");
        ObjectHelper.m9873a((Object) t3, "item3 is null");
        ObjectHelper.m9873a((Object) t4, "item4 is null");
        ObjectHelper.m9873a((Object) t5, "item5 is null");
        ObjectHelper.m9873a((Object) t6, "item6 is null");
        ObjectHelper.m9873a((Object) t7, "item7 is null");
        ObjectHelper.m9873a((Object) t8, "item8 is null");
        ObjectHelper.m9873a((Object) t9, "item9 is null");
        return m11098a(t, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Flowable<T> m11214a(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9, T t10) {
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
        return m11098a(t, t2, t3, t4, t5, t6, t7, t8, t9, t10);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T> Flowable<T> m11078b(Iterable<? extends Publisher<? extends T>> iterable, int i, int i2) {
        return m10952e((Iterable) iterable).m11139a(Functions.m9935a(), false, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static <T> Flowable<T> m11019c(int i, int i2, Publisher<? extends T>... dbwVarArr) {
        return m11098a((Object[]) dbwVarArr).m11139a(Functions.m9935a(), false, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public static <T> Flowable<T> m10932f(Iterable<? extends Publisher<? extends T>> iterable) {
        return m10952e((Iterable) iterable).m10839p(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T> Flowable<T> m11229a(Iterable<? extends Publisher<? extends T>> iterable, int i) {
        return m10952e((Iterable) iterable).m10926f(Functions.m9935a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public static <T> Flowable<T> m10941e(Publisher<? extends Publisher<? extends T>> dbwVar) {
        return m11043b(dbwVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T> Flowable<T> m11043b(Publisher<? extends Publisher<? extends T>> dbwVar, int i) {
        return m10964d((Publisher) dbwVar).m10926f(Functions.m9935a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public static <T> Flowable<T> m10920f(Publisher<? extends T>... dbwVarArr) {
        return m11098a((Object[]) dbwVarArr).m10926f(Functions.m9935a(), dbwVarArr.length);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m11038b(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m11098a((Object[]) new Publisher[]{dbwVar, dbwVar2}).m10966d(Functions.m9935a(), false, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m11036b(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, Publisher<? extends T> dbwVar3) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        return m11098a((Object[]) new Publisher[]{dbwVar, dbwVar2, dbwVar3}).m10966d(Functions.m9935a(), false, 3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T> Flowable<T> m11034b(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, Publisher<? extends T> dbwVar3, Publisher<? extends T> dbwVar4) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        return m11098a((Object[]) new Publisher[]{dbwVar, dbwVar2, dbwVar3, dbwVar4}).m10966d(Functions.m9935a(), false, 4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public static <T> Flowable<T> m10914g(Iterable<? extends Publisher<? extends T>> iterable) {
        return m10952e((Iterable) iterable).m10944e(Functions.m9935a(), true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static <T> Flowable<T> m11010c(Iterable<? extends Publisher<? extends T>> iterable, int i, int i2) {
        return m10952e((Iterable) iterable).m11139a(Functions.m9935a(), true, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Flowable<T> m10984d(int i, int i2, Publisher<? extends T>... dbwVarArr) {
        return m11098a((Object[]) dbwVarArr).m11139a(Functions.m9935a(), true, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public static <T> Flowable<T> m11079b(Iterable<? extends Publisher<? extends T>> iterable, int i) {
        return m10952e((Iterable) iterable).m10966d(Functions.m9935a(), true, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public static <T> Flowable<T> m10922f(Publisher<? extends Publisher<? extends T>> dbwVar) {
        return m10994c(dbwVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public static <T> Flowable<T> m10994c(Publisher<? extends Publisher<? extends T>> dbwVar, int i) {
        return m10964d((Publisher) dbwVar).m10966d(Functions.m9935a(), true, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public static <T> Flowable<T> m10905g(Publisher<? extends T>... dbwVarArr) {
        return m11098a((Object[]) dbwVarArr).m10966d(Functions.m9935a(), true, dbwVarArr.length);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10992c(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m11098a((Object[]) new Publisher[]{dbwVar, dbwVar2}).m10966d(Functions.m9935a(), true, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10991c(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, Publisher<? extends T> dbwVar3) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        return m11098a((Object[]) new Publisher[]{dbwVar, dbwVar2, dbwVar3}).m10966d(Functions.m9935a(), true, 3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T> Flowable<T> m10990c(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, Publisher<? extends T> dbwVar3, Publisher<? extends T> dbwVar4) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        return m11098a((Object[]) new Publisher[]{dbwVar, dbwVar2, dbwVar3, dbwVar4}).m10966d(Functions.m9935a(), true, 4);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: c */
    public static <T> Flowable<T> m11022c() {
        return RxJavaPlugins.m9207a(FlowableNever.f18202b);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static Flowable<Integer> m11272a(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + i2);
        } else if (i2 == 0) {
            return m11094b();
        } else {
            if (i2 == 1) {
                return m11223a(Integer.valueOf(i));
            }
            if (i + (i2 - 1) <= 2147483647L) {
                return RxJavaPlugins.m9207a(new FlowableRange(i, i2));
            }
            throw new IllegalArgumentException("Integer overflow");
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static Flowable<Long> m11260a(long j, long j2) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + j2);
        } else if (i == 0) {
            return m11094b();
        } else {
            if (j2 == 1) {
                return m11223a(Long.valueOf(j));
            }
            long j3 = (j2 - 1) + j;
            if (j <= 0 || j3 >= 0) {
                return RxJavaPlugins.m9207a(new FlowableRangeLong(j, j2));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Single<Boolean> m10961d(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2) {
        return m11114a(dbwVar, dbwVar2, ObjectHelper.m9880a(), m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T> Single<Boolean> m11115a(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, BiPredicate<? super T, ? super T> aujVar) {
        return m11114a(dbwVar, dbwVar2, aujVar, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Single<Boolean> m11114a(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, BiPredicate<? super T, ? super T> aujVar, int i) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(aujVar, "isEqual is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9200a(new FlowableSequenceEqualSingle(dbwVar, dbwVar2, aujVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public static <T> Single<Boolean> m11119a(Publisher<? extends T> dbwVar, Publisher<? extends T> dbwVar2, int i) {
        return m11114a(dbwVar, dbwVar2, ObjectHelper.m9880a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public static <T> Flowable<T> m10963d(Publisher<? extends Publisher<? extends T>> dbwVar, int i) {
        return m10964d((Publisher) dbwVar).m10875j(Functions.m9935a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public static <T> Flowable<T> m10907g(Publisher<? extends Publisher<? extends T>> dbwVar) {
        return m10964d((Publisher) dbwVar).m11302C(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: h */
    public static <T> Flowable<T> m10893h(Publisher<? extends Publisher<? extends T>> dbwVar) {
        return m10940e(dbwVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public static <T> Flowable<T> m10940e(Publisher<? extends Publisher<? extends T>> dbwVar, int i) {
        return m10964d((Publisher) dbwVar).m10866k(Functions.m9935a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: b */
    public static Flowable<Long> m11086b(long j, TimeUnit timeUnit) {
        return m11085b(j, timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static Flowable<Long> m11085b(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableTimer(Math.max(0L, j), timeUnit, astVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.NONE)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: i */
    public static <T> Flowable<T> m10883i(Publisher<T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "onSubscribe is null");
        if (!(dbwVar instanceof Flowable)) {
            return RxJavaPlugins.m9207a(new FlowableFromPublisher(dbwVar));
        }
        throw new IllegalArgumentException("unsafeCreate(Flowable) should be upgraded");
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: a */
    public static <T, D> Flowable<T> m11201a(Callable<? extends D> callable, Function<? super D, ? extends Publisher<? extends T>> aunVar, Consumer<? super D> aumVar) {
        return m11200a((Callable) callable, (Function) aunVar, (Consumer) aumVar, true);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, D> Flowable<T> m11200a(Callable<? extends D> callable, Function<? super D, ? extends Publisher<? extends T>> aunVar, Consumer<? super D> aumVar, boolean z) {
        ObjectHelper.m9873a(callable, "resourceSupplier is null");
        ObjectHelper.m9873a(aunVar, "sourceSupplier is null");
        ObjectHelper.m9873a(aumVar, "resourceDisposer is null");
        return RxJavaPlugins.m9207a(new FlowableUsing(callable, aunVar, aumVar, z));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public static <T, R> Flowable<R> m11009c(Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(iterable, "sources is null");
        return RxJavaPlugins.m9207a(new FlowableZip(null, iterable, aunVar, m11274a(), false));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Flowable<R> m11124a(Publisher<? extends Publisher<? extends T>> dbwVar, Function<? super Object[], ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        return m10964d((Publisher) dbwVar).m11278P().m10055d(FlowableInternalHelper.m9773c(aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, R> Flowable<R> m11037b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m11138a(Functions.m9927a((BiFunction) auiVar), false, m11274a(), dbwVar, dbwVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, R> Flowable<R> m11117a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar, boolean z) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m11138a(Functions.m9927a((BiFunction) auiVar), z, m11274a(), dbwVar, dbwVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T1, T2, R> Flowable<R> m11116a(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, BiFunction<? super T1, ? super T2, ? extends R> auiVar, boolean z, int i) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m11138a(Functions.m9927a((BiFunction) auiVar), z, i, dbwVar, dbwVar2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, T3, R> Flowable<R> m11035b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Function3<? super T1, ? super T2, ? super T3, ? extends R> auoVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        return m11138a(Functions.m9921a((Function3) auoVar), false, m11274a(), dbwVar, dbwVar2, dbwVar3);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, T3, T4, R> Flowable<R> m11033b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> aupVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        return m11138a(Functions.m9920a((Function4) aupVar), false, m11274a(), dbwVar, dbwVar2, dbwVar3, dbwVar4);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, R> Flowable<R> m11032b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> auqVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        return m11138a(Functions.m9919a((Function5) auqVar), false, m11274a(), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, R> Flowable<R> m11031b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> aurVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        return m11138a(Functions.m9918a((Function6) aurVar), false, m11274a(), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, T7, R> Flowable<R> m11030b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Publisher<? extends T7> dbwVar7, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> ausVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        ObjectHelper.m9873a(dbwVar7, "source7 is null");
        return m11138a(Functions.m9917a((Function7) ausVar), false, m11274a(), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6, dbwVar7);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Flowable<R> m11029b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Publisher<? extends T7> dbwVar7, Publisher<? extends T8> dbwVar8, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> autVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        ObjectHelper.m9873a(dbwVar7, "source7 is null");
        ObjectHelper.m9873a(dbwVar8, "source8 is null");
        return m11138a(Functions.m9916a((Function8) autVar), false, m11274a(), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6, dbwVar7, dbwVar8);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Flowable<R> m11028b(Publisher<? extends T1> dbwVar, Publisher<? extends T2> dbwVar2, Publisher<? extends T3> dbwVar3, Publisher<? extends T4> dbwVar4, Publisher<? extends T5> dbwVar5, Publisher<? extends T6> dbwVar6, Publisher<? extends T7> dbwVar7, Publisher<? extends T8> dbwVar8, Publisher<? extends T9> dbwVar9, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> auuVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        ObjectHelper.m9873a(dbwVar5, "source5 is null");
        ObjectHelper.m9873a(dbwVar6, "source6 is null");
        ObjectHelper.m9873a(dbwVar7, "source7 is null");
        ObjectHelper.m9873a(dbwVar8, "source8 is null");
        ObjectHelper.m9873a(dbwVar9, "source9 is null");
        return m11138a(Functions.m9915a((Function9) auuVar), false, m11274a(), dbwVar, dbwVar2, dbwVar3, dbwVar4, dbwVar5, dbwVar6, dbwVar7, dbwVar8, dbwVar9);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Flowable<R> m11138a(Function<? super Object[], ? extends R> aunVar, boolean z, int i, Publisher<? extends T>... dbwVarArr) {
        if (dbwVarArr.length == 0) {
            return m11094b();
        }
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableZip(dbwVarArr, null, aunVar, i, z));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public static <T, R> Flowable<R> m11224a(Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "zipper is null");
        ObjectHelper.m9873a(iterable, "sources is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableZip(null, iterable, aunVar, i, z));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<Boolean> m11135a(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9200a(new FlowableAllSingle(this, auxVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: j */
    public final Flowable<T> m10874j(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11097a(this, dbwVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Single<Boolean> m11045b(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9200a(new FlowableAnySingle(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: a */
    public final <R> R m11190a(@AbstractC3889atm FlowableConverter<T, ? extends R> arwVar) {
        return (R) ((FlowableConverter) ObjectHelper.m9873a(arwVar, "converter is null")).m10807a(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: d */
    public final T m10986d() {
        BlockingFirstSubscriber briVar = new BlockingFirstSubscriber();
        m11187a((FlowableSubscriber) briVar);
        T a = briVar.m9468a();
        if (a != null) {
            return a;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: b */
    public final T m11075b(T t) {
        BlockingFirstSubscriber briVar = new BlockingFirstSubscriber();
        m11187a((FlowableSubscriber) briVar);
        T a = briVar.m9468a();
        return a != null ? a : t;
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: b */
    public final void m11058b(Consumer<? super T> aumVar) {
        Iterator<T> it = m10959e().iterator();
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
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final Iterable<T> m10959e() {
        return m11273a(m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Iterable<T> m11273a(int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        return new BlockingFlowableIterable(this, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: f */
    public final T m10937f() {
        BlockingLastSubscriber brjVar = new BlockingLastSubscriber();
        m11187a((FlowableSubscriber) brjVar);
        T a = brjVar.m9468a();
        if (a != null) {
            return a;
        }
        throw new NoSuchElementException();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: c */
    public final T m11008c(T t) {
        BlockingLastSubscriber brjVar = new BlockingLastSubscriber();
        m11187a((FlowableSubscriber) brjVar);
        T a = brjVar.m9468a();
        return a != null ? a : t;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: g */
    public final Iterable<T> m10919g() {
        return new BlockingFlowableLatest(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: d */
    public final Iterable<T> m10974d(T t) {
        return new BlockingFlowableMostRecent(this, t);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: h */
    public final Iterable<T> m10904h() {
        return new BlockingFlowableNext(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: i */
    public final T m10892i() {
        return m11287K().m10063d();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: e */
    public final T m10951e(T t) {
        return m10869k((Flowable<T>) t).m10063d();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: j */
    public final Future<T> m10882j() {
        return (Future) m10939e((Flowable<T>) new FutureSubscriber());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: k */
    public final void m10873k() {
        FlowableBlockingSubscribe.m9817a(this);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: c */
    public final void m11003c(Consumer<? super T> aumVar) {
        FlowableBlockingSubscribe.m9816a(this, aumVar, Functions.f17560f, Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final void m11175a(Consumer<? super T> aumVar, int i) {
        FlowableBlockingSubscribe.m9815a(this, aumVar, Functions.f17560f, Functions.f17557c, i);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: a */
    public final void m11174a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2) {
        FlowableBlockingSubscribe.m9816a(this, aumVar, aumVar2, Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final void m11173a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, int i) {
        FlowableBlockingSubscribe.m9815a(this, aumVar, aumVar2, Functions.f17557c, i);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: a */
    public final void m11172a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar) {
        FlowableBlockingSubscribe.m9816a(this, aumVar, aumVar2, augVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final void m11171a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, int i) {
        FlowableBlockingSubscribe.m9815a(this, aumVar, aumVar2, augVar, i);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: a */
    public final void m11100a(Subscriber<? super T> dbxVar) {
        FlowableBlockingSubscribe.m9814a(this, dbxVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final Flowable<List<T>> m11093b(int i) {
        return m11092b(i, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final Flowable<List<T>> m11092b(int i, int i2) {
        return (Flowable<List<T>>) m11271a(i, i2, ArrayListSupplier.asCallable());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U extends Collection<? super T>> Flowable<U> m11271a(int i, int i2, Callable<U> callable) {
        ObjectHelper.m9878a(i, "count");
        ObjectHelper.m9878a(i2, MSVSSConstants.WRITABLE_SKIP);
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9207a(new FlowableBuffer(this, i, i2, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <U extends Collection<? super T>> Flowable<U> m11267a(int i, Callable<U> callable) {
        return m11271a(i, i, callable);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: b */
    public final Flowable<List<T>> m11088b(long j, long j2, TimeUnit timeUnit) {
        return (Flowable<List<T>>) m11253a(j, j2, timeUnit, Schedulers.m9050a(), ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: b */
    public final Flowable<List<T>> m11087b(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        return (Flowable<List<T>>) m11253a(j, j2, timeUnit, astVar, ArrayListSupplier.asCallable());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U extends Collection<? super T>> Flowable<U> m11253a(long j, long j2, TimeUnit timeUnit, Scheduler astVar, Callable<U> callable) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9207a(new FlowableBufferTimed(this, j, j2, timeUnit, astVar, callable, Integer.MAX_VALUE, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: c */
    public final Flowable<List<T>> m11015c(long j, TimeUnit timeUnit) {
        return m11245a(j, timeUnit, Schedulers.m9050a(), Integer.MAX_VALUE);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<List<T>> m11249a(long j, TimeUnit timeUnit, int i) {
        return m11245a(j, timeUnit, Schedulers.m9050a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<List<T>> m11245a(long j, TimeUnit timeUnit, Scheduler astVar, int i) {
        return (Flowable<List<T>>) m11244a(j, timeUnit, astVar, i, (Callable) ArrayListSupplier.asCallable(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final <U extends Collection<? super T>> Flowable<U> m11244a(long j, TimeUnit timeUnit, Scheduler astVar, int i, Callable<U> callable, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        ObjectHelper.m9878a(i, "count");
        return RxJavaPlugins.m9207a(new FlowableBufferTimed(this, j, j, timeUnit, astVar, callable, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: c */
    public final Flowable<List<T>> m11014c(long j, TimeUnit timeUnit, Scheduler astVar) {
        return (Flowable<List<T>>) m11244a(j, timeUnit, astVar, Integer.MAX_VALUE, (Callable) ArrayListSupplier.asCallable(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final <TOpening, TClosing> Flowable<List<T>> m11192a(Flowable<? extends TOpening> arvVar, Function<? super TOpening, ? extends Publisher<? extends TClosing>> aunVar) {
        return (Flowable<List<T>>) m11191a((Flowable) arvVar, (Function) aunVar, (Callable) ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final <TOpening, TClosing, U extends Collection<? super T>> Flowable<U> m11191a(Flowable<? extends TOpening> arvVar, Function<? super TOpening, ? extends Publisher<? extends TClosing>> aunVar, Callable<U> callable) {
        ObjectHelper.m9873a(arvVar, "openingIndicator is null");
        ObjectHelper.m9873a(aunVar, "closingIndicator is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9207a(new FlowableBufferBoundary(this, arvVar, aunVar, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: k */
    public final <B> Flowable<List<T>> m10865k(Publisher<B> dbwVar) {
        return (Flowable<List<T>>) m11128a((Publisher) dbwVar, (Callable) ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: f */
    public final <B> Flowable<List<T>> m10921f(Publisher<B> dbwVar, int i) {
        ObjectHelper.m9878a(i, "initialCapacity");
        return (Flowable<List<T>>) m11128a((Publisher) dbwVar, (Callable) Functions.m9934a(i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final <B, U extends Collection<? super T>> Flowable<U> m11128a(Publisher<B> dbwVar, Callable<U> callable) {
        ObjectHelper.m9873a(dbwVar, "boundaryIndicator is null");
        ObjectHelper.m9873a(callable, "bufferSupplier is null");
        return RxJavaPlugins.m9207a(new FlowableBufferExactBoundary(this, dbwVar, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: d */
    public final <B> Flowable<List<T>> m10973d(Callable<? extends Publisher<B>> callable) {
        return (Flowable<List<T>>) m11206a((Callable) callable, (Callable) ArrayListSupplier.asCallable());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final <B, U extends Collection<? super T>> Flowable<U> m11206a(Callable<? extends Publisher<B>> callable, Callable<U> callable2) {
        ObjectHelper.m9873a(callable, "boundaryIndicatorSupplier is null");
        ObjectHelper.m9873a(callable2, "bufferSupplier is null");
        return RxJavaPlugins.m9207a(new FlowableBufferBoundarySupplier(this, callable, callable2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: l */
    public final Flowable<T> m10864l() {
        return m11021c(16);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public final Flowable<T> m11021c(int i) {
        ObjectHelper.m9878a(i, "initialCapacity");
        return RxJavaPlugins.m9207a(new FlowableCache(this, i));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U> Flowable<U> m11231a(Class<U> cls) {
        ObjectHelper.m9873a(cls, "clazz is null");
        return (Flowable<U>) m10817v(Functions.m9933a((Class) cls));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <U> Single<U> m11071b(Callable<? extends U> callable, BiConsumer<? super U, ? super T> auhVar) {
        ObjectHelper.m9873a(callable, "initialItemSupplier is null");
        ObjectHelper.m9873a(auhVar, "collector is null");
        return RxJavaPlugins.m9200a(new FlowableCollectSingle(this, callable, auhVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U> Single<U> m11213a(U u, BiConsumer<? super U, ? super T> auhVar) {
        ObjectHelper.m9873a(u, "initialItem is null");
        return m11071b(Functions.m9932a(u), auhVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: a */
    public final <R> Flowable<R> m11186a(FlowableTransformer<? super T, ? extends R> asbVar) {
        return m10964d(((FlowableTransformer) ObjectHelper.m9873a(asbVar, "composer is null")).apply(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <R> Flowable<R> m11167a(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m11166a(aunVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11166a(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.m9207a(new FlowableConcatMap(this, aunVar, i, ErrorMode.IMMEDIATE));
        }
        Object call = ((ScalarCallable) this).call();
        if (call == null) {
            return m11094b();
        }
        return FlowableScalarXMap.m9736a(call, aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final Completable m11055b(Function<? super T, ? extends CompletableSource> aunVar) {
        return m11054b(aunVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Completable m11054b(Function<? super T, ? extends CompletableSource> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9209a(new FlowableConcatMapCompletable(this, aunVar, ErrorMode.IMMEDIATE, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public final Completable m11002c(Function<? super T, ? extends CompletableSource> aunVar) {
        return m11140a((Function) aunVar, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Completable m11141a(Function<? super T, ? extends CompletableSource> aunVar, boolean z) {
        return m11140a(aunVar, z, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Completable m11140a(Function<? super T, ? extends CompletableSource> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9209a(new FlowableConcatMapCompletable(this, aunVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public final <R> Flowable<R> m10969d(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m11160a((Function) aunVar, 2, true);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11160a(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        if (this instanceof ScalarCallable) {
            Object call = ((ScalarCallable) this).call();
            if (call == null) {
                return m11094b();
            }
            return FlowableScalarXMap.m9736a(call, aunVar);
        }
        return RxJavaPlugins.m9207a(new FlowableConcatMap(this, aunVar, i, z ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final <R> Flowable<R> m10946e(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m11165a(aunVar, m11274a(), m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11165a(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, int i2) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapEager(this, aunVar, i, i2, ErrorMode.IMMEDIATE));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final <R> Flowable<R> m11048b(Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z) {
        return m11164a(aunVar, m11274a(), m11274a(), z);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11164a(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, int i2, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapEager(this, aunVar, i, i2, z ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public final <U> Flowable<U> m10927f(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        return m11001c(aunVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final <U> Flowable<U> m11001c(Function<? super T, ? extends Iterable<? extends U>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableFlattenIterable(this, aunVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public final <R> Flowable<R> m10911g(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        return m10968d(aunVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public final <R> Flowable<R> m10968d(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapMaybe(this, aunVar, ErrorMode.IMMEDIATE, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: h */
    public final <R> Flowable<R> m10896h(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        return m11047b((Function) aunVar, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public final <R> Flowable<R> m10998c(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z) {
        return m11047b(aunVar, z, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <R> Flowable<R> m11047b(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapMaybe(this, aunVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: i */
    public final <R> Flowable<R> m10885i(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        return m10945e(aunVar, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public final <R> Flowable<R> m10945e(Function<? super T, ? extends SingleSource<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapSingle(this, aunVar, ErrorMode.IMMEDIATE, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: j */
    public final <R> Flowable<R> m10876j(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        return m10997c((Function) aunVar, true, 2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public final <R> Flowable<R> m10967d(Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
        return m10997c(aunVar, z, 2);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final <R> Flowable<R> m10997c(Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowableConcatMapSingle(this, aunVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: l */
    public final Flowable<T> m10858l(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11120a((Publisher) this, (Publisher) dbwVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m11181a(@AbstractC3889atm SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableConcatWithSingle(this, ataVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m11185a(@AbstractC3889atm MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableConcatWithMaybe(this, asiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: a */
    public final Flowable<T> m11193a(@AbstractC3889atm CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableConcatWithCompletable(this, arsVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final Single<Boolean> m10931f(Object obj) {
        ObjectHelper.m9873a(obj, "item is null");
        return m11045b((Predicate) Functions.m9908c(obj));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: m */
    public final Single<Long> m10857m() {
        return RxJavaPlugins.m9200a(new FlowableCountSingle(this));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: k */
    public final <U> Flowable<T> m10867k(Function<? super T, ? extends Publisher<U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "debounceIndicator is null");
        return RxJavaPlugins.m9207a(new FlowableDebounce(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: d */
    public final Flowable<T> m10980d(long j, TimeUnit timeUnit) {
        return m10979d(j, timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public final Flowable<T> m10979d(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableDebounceTimed(this, j, timeUnit, astVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: g */
    public final Flowable<T> m10913g(T t) {
        ObjectHelper.m9873a((Object) t, "defaultItem is null");
        return m10822t(m11223a(t));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: l */
    public final <U> Flowable<T> m10859l(Function<? super T, ? extends Publisher<U>> aunVar) {
        ObjectHelper.m9873a(aunVar, "itemDelayIndicator is null");
        return (Flowable<T>) m10839p(FlowableInternalHelper.m9779a(aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final Flowable<T> m10956e(long j, TimeUnit timeUnit) {
        return m11239a(j, timeUnit, Schedulers.m9050a(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m11235a(long j, TimeUnit timeUnit, boolean z) {
        return m11239a(j, timeUnit, Schedulers.m9050a(), z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final Flowable<T> m10955e(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11239a(j, timeUnit, astVar, false);
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11239a(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableDelay(this, Math.max(0L, j), timeUnit, astVar, z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final <U, V> Flowable<T> m11041b(Publisher<U> dbwVar, Function<? super T, ? extends Publisher<V>> aunVar) {
        return m10853m(dbwVar).m10859l((Function) aunVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: m */
    public final <U> Flowable<T> m10853m(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "subscriptionIndicator is null");
        return RxJavaPlugins.m9207a(new FlowableDelaySubscriptionOther(this, dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public final Flowable<T> m10934f(long j, TimeUnit timeUnit) {
        return m10933f(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public final Flowable<T> m10933f(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10853m(m11085b(j, timeUnit, astVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @Deprecated
    @CheckReturnValue
    /* renamed from: n */
    public final <T2> Flowable<T2> m10852n() {
        return RxJavaPlugins.m9207a(new FlowableDematerialize(this, Functions.m9935a()));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @Experimental
    @AbstractC3889atm
    /* renamed from: m */
    public final <R> Flowable<R> m10854m(Function<? super T, Notification<R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        return RxJavaPlugins.m9207a(new FlowableDematerialize(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: o */
    public final Flowable<T> m10847o() {
        return m11156a((Function) Functions.m9935a(), (Callable) Functions.m9903g());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: n */
    public final <K> Flowable<T> m10849n(Function<? super T, K> aunVar) {
        return m11156a((Function) aunVar, (Callable) Functions.m9903g());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <K> Flowable<T> m11156a(Function<? super T, K> aunVar, Callable<? extends Collection<? super K>> callable) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(callable, "collectionSupplier is null");
        return RxJavaPlugins.m9207a(new FlowableDistinct(this, aunVar, callable));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: p */
    public final Flowable<T> m10842p() {
        return m10844o(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: o */
    public final <K> Flowable<T> m10844o(Function<? super T, K> aunVar) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        return RxJavaPlugins.m9207a(new FlowableDistinctUntilChanged(this, aunVar, ObjectHelper.m9880a()));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m11178a(BiPredicate<? super T, ? super T> aujVar) {
        ObjectHelper.m9873a(aujVar, "comparer is null");
        return RxJavaPlugins.m9207a(new FlowableDistinctUntilChanged(this, Functions.m9935a(), aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: a */
    public final Flowable<T> m11180a(Action augVar) {
        ObjectHelper.m9873a(augVar, "onFinally is null");
        return RxJavaPlugins.m9207a(new FlowableDoFinally(this, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: d */
    public final Flowable<T> m10970d(Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onAfterNext is null");
        return RxJavaPlugins.m9207a(new FlowableDoAfterNext(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: b */
    public final Flowable<T> m11062b(Action augVar) {
        return m11170a((Consumer) Functions.m9914b(), Functions.m9914b(), Functions.f17557c, augVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: c */
    public final Flowable<T> m11004c(Action augVar) {
        return m11168a(Functions.m9914b(), Functions.f17561g, augVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: d */
    public final Flowable<T> m10971d(Action augVar) {
        return m11170a((Consumer) Functions.m9914b(), Functions.m9914b(), augVar, Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    private Flowable<T> m11170a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ObjectHelper.m9873a(augVar2, "onAfterTerminate is null");
        return RxJavaPlugins.m9207a(new FlowableDoOnEach(this, aumVar, aumVar2, augVar, augVar2));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public final Flowable<T> m10947e(Consumer<? super Notification<T>> aumVar) {
        ObjectHelper.m9873a(aumVar, "onNotification is null");
        return m11170a((Consumer) Functions.m9925a((Consumer) aumVar), (Consumer<? super Throwable>) Functions.m9910b((Consumer) aumVar), Functions.m9907c((Consumer) aumVar), Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11027b(Subscriber<? super T> dbxVar) {
        ObjectHelper.m9873a(dbxVar, "subscriber is null");
        return m11170a((Consumer) FlowableInternalHelper.m9776a(dbxVar), (Consumer<? super Throwable>) FlowableInternalHelper.m9774b(dbxVar), FlowableInternalHelper.m9772c(dbxVar), Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: f */
    public final Flowable<T> m10928f(Consumer<? super Throwable> aumVar) {
        return m11170a((Consumer) Functions.m9914b(), aumVar, Functions.f17557c, Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11168a(Consumer<? super dby> aumVar, LongConsumer auwVar, Action augVar) {
        ObjectHelper.m9873a(aumVar, "onSubscribe is null");
        ObjectHelper.m9873a(auwVar, "onRequest is null");
        ObjectHelper.m9873a(augVar, "onCancel is null");
        return RxJavaPlugins.m9207a(new FlowableDoOnLifecycle(this, aumVar, auwVar, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: g */
    public final Flowable<T> m10912g(Consumer<? super T> aumVar) {
        return m11170a((Consumer) aumVar, Functions.m9914b(), Functions.f17557c, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: a */
    public final Flowable<T> m11136a(LongConsumer auwVar) {
        return m11168a(Functions.m9914b(), auwVar, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: h */
    public final Flowable<T> m10897h(Consumer<? super dby> aumVar) {
        return m11168a(aumVar, Functions.f17561g, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: e */
    public final Flowable<T> m10948e(Action augVar) {
        return m11170a((Consumer) Functions.m9914b(), Functions.m9928a(augVar), augVar, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: a */
    public final Maybe<T> m11261a(long j) {
        if (j >= 0) {
            return RxJavaPlugins.m9205a(new FlowableElementAtMaybe(this, j));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + j);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<T> m11251a(long j, T t) {
        if (j >= 0) {
            ObjectHelper.m9873a((Object) t, "defaultItem is null");
            return RxJavaPlugins.m9200a(new FlowableElementAtSingle(this, j, t));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: b */
    public final Single<T> m11090b(long j) {
        if (j >= 0) {
            return RxJavaPlugins.m9200a(new FlowableElementAtSingle(this, j, null));
        }
        throw new IndexOutOfBoundsException("index >= 0 required but it was " + j);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final Flowable<T> m10996c(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9207a(new FlowableFilter(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: q */
    public final Maybe<T> m10837q() {
        return m11261a(0L);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: h */
    public final Single<T> m10898h(T t) {
        return m11251a(0L, (long) t);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: r */
    public final Single<T> m10832r() {
        return m11090b(0L);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: p */
    public final <R> Flowable<R> m10839p(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m11139a((Function) aunVar, false, m11274a(), m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final <R> Flowable<R> m10944e(Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z) {
        return m11139a(aunVar, z, m11274a(), m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public final <R> Flowable<R> m10926f(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i) {
        return m11139a((Function) aunVar, false, i, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public final <R> Flowable<R> m10966d(Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z, int i) {
        return m11139a(aunVar, z, i, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11139a(Function<? super T, ? extends Publisher<? extends R>> aunVar, boolean z, int i, int i2) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.m9207a(new FlowableFlatMap(this, aunVar, z, i, i2));
        }
        Object call = ((ScalarCallable) this).call();
        if (call == null) {
            return m11094b();
        }
        return FlowableScalarXMap.m9736a(call, aunVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11147a(Function<? super T, ? extends Publisher<? extends R>> aunVar, Function<? super Throwable, ? extends Publisher<? extends R>> aunVar2, Callable<? extends Publisher<? extends R>> callable) {
        ObjectHelper.m9873a(aunVar, "onNextMapper is null");
        ObjectHelper.m9873a(aunVar2, "onErrorMapper is null");
        ObjectHelper.m9873a(callable, "onCompleteSupplier is null");
        return m10941e((Publisher) new FlowableMapNotification(this, aunVar, aunVar2, callable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11146a(Function<? super T, ? extends Publisher<? extends R>> aunVar, Function<Throwable, ? extends Publisher<? extends R>> aunVar2, Callable<? extends Publisher<? extends R>> callable, int i) {
        ObjectHelper.m9873a(aunVar, "onNextMapper is null");
        ObjectHelper.m9873a(aunVar2, "onErrorMapper is null");
        ObjectHelper.m9873a(callable, "onCompleteSupplier is null");
        return m11043b(new FlowableMapNotification(this, aunVar, aunVar2, callable), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <U, R> Flowable<R> m11153a(Function<? super T, ? extends Publisher<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        return m11149a((Function) aunVar, (BiFunction) auiVar, false, m11274a(), m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <U, R> Flowable<R> m11151a(Function<? super T, ? extends Publisher<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z) {
        return m11149a(aunVar, auiVar, z, m11274a(), m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <U, R> Flowable<R> m11150a(Function<? super T, ? extends Publisher<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z, int i) {
        return m11149a(aunVar, auiVar, z, i, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U, R> Flowable<R> m11149a(Function<? super T, ? extends Publisher<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z, int i, int i2) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9873a(auiVar, "combiner is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        ObjectHelper.m9878a(i2, "bufferSize");
        return m11139a(FlowableInternalHelper.m9777a(aunVar, auiVar), z, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <U, R> Flowable<R> m11152a(Function<? super T, ? extends Publisher<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar, int i) {
        return m11149a((Function) aunVar, (BiFunction) auiVar, false, i, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: q */
    public final Completable m10834q(Function<? super T, ? extends CompletableSource> aunVar) {
        return m10943e((Function) aunVar, false, Integer.MAX_VALUE);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public final Completable m10943e(Function<? super T, ? extends CompletableSource> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9209a(new FlowableFlatMapCompletableCompletable(this, aunVar, z, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: r */
    public final <U> Flowable<U> m10829r(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        return m10910g(aunVar, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: g */
    public final <U> Flowable<U> m10910g(Function<? super T, ? extends Iterable<? extends U>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableFlattenIterable(this, aunVar, i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <U, V> Flowable<V> m11052b(Function<? super T, ? extends Iterable<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends V> auiVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return (Flowable<V>) m11149a((Function) FlowableInternalHelper.m9775b(aunVar), (BiFunction) auiVar, false, m11274a(), m11274a());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <U, V> Flowable<V> m11051b(Function<? super T, ? extends Iterable<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends V> auiVar, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return (Flowable<V>) m11149a((Function) FlowableInternalHelper.m9775b(aunVar), (BiFunction) auiVar, false, m11274a(), i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: s */
    public final <R> Flowable<R> m10826s(Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        return m10924f((Function) aunVar, false, Integer.MAX_VALUE);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final <R> Flowable<R> m10924f(Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9207a(new FlowableFlatMapMaybe(this, aunVar, z, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: t */
    public final <R> Flowable<R> m10823t(Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        return m10909g((Function) aunVar, false, Integer.MAX_VALUE);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: g */
    public final <R> Flowable<R> m10909g(Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "maxConcurrency");
        return RxJavaPlugins.m9207a(new FlowableFlatMapSingle(this, aunVar, z, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.NONE)
    /* renamed from: i */
    public final Disposable m10886i(Consumer<? super T> aumVar) {
        return m10868k((Consumer) aumVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.NONE)
    /* renamed from: d */
    public final Disposable m10965d(Predicate<? super T> auxVar) {
        return m11133a((Predicate) auxVar, (Consumer<? super Throwable>) Functions.f17560f, Functions.f17557c);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.NONE)
    /* renamed from: a */
    public final Disposable m11134a(Predicate<? super T> auxVar, Consumer<? super Throwable> aumVar) {
        return m11133a((Predicate) auxVar, aumVar, Functions.f17557c);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.NONE)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Disposable m11133a(Predicate<? super T> auxVar, Consumer<? super Throwable> aumVar, Action augVar) {
        ObjectHelper.m9873a(auxVar, "onNext is null");
        ObjectHelper.m9873a(aumVar, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ForEachWhileSubscriber brnVar = new ForEachWhileSubscriber(auxVar, aumVar, augVar);
        m11187a((FlowableSubscriber) brnVar);
        return brnVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: u */
    public final <K> Flowable<GroupedFlowable<K, T>> m10820u(Function<? super T, ? extends K> aunVar) {
        return (Flowable<GroupedFlowable<K, T>>) m11143a((Function) aunVar, (Function) Functions.m9935a(), false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public final <K> Flowable<GroupedFlowable<K, T>> m10925f(Function<? super T, ? extends K> aunVar, boolean z) {
        return (Flowable<GroupedFlowable<K, T>>) m11143a(aunVar, Functions.m9935a(), z, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <K, V> Flowable<GroupedFlowable<K, V>> m11148a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2) {
        return m11143a((Function) aunVar, (Function) aunVar2, false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <K, V> Flowable<GroupedFlowable<K, V>> m11144a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, boolean z) {
        return m11143a(aunVar, aunVar2, z, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <K, V> Flowable<GroupedFlowable<K, V>> m11143a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, boolean z, int i) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableGroupBy(this, aunVar, aunVar2, i, z, null));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <K, V> Flowable<GroupedFlowable<K, V>> m11142a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, boolean z, int i, Function<? super Consumer<Object>, ? extends Map<K, Object>> aunVar3) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(aunVar3, "evictingMapFactory is null");
        return RxJavaPlugins.m9207a(new FlowableGroupBy(this, aunVar, aunVar2, i, z, aunVar3));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <TRight, TLeftEnd, TRightEnd, R> Flowable<R> m11122a(Publisher<? extends TRight> dbwVar, Function<? super T, ? extends Publisher<TLeftEnd>> aunVar, Function<? super TRight, ? extends Publisher<TRightEnd>> aunVar2, BiFunction<? super T, ? super Flowable<TRight>, ? extends R> auiVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        ObjectHelper.m9873a(aunVar, "leftEnd is null");
        ObjectHelper.m9873a(aunVar2, "rightEnd is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return RxJavaPlugins.m9207a(new FlowableGroupJoin(this, dbwVar, aunVar, aunVar2, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: s */
    public final Flowable<T> m10827s() {
        return RxJavaPlugins.m9207a(new FlowableHide(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: t */
    public final Completable m10824t() {
        return RxJavaPlugins.m9209a(new FlowableIgnoreElementsCompletable(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: u */
    public final Single<Boolean> m10821u() {
        return m11135a((Predicate) Functions.m9906d());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <TRight, TLeftEnd, TRightEnd, R> Flowable<R> m11040b(Publisher<? extends TRight> dbwVar, Function<? super T, ? extends Publisher<TLeftEnd>> aunVar, Function<? super TRight, ? extends Publisher<TRightEnd>> aunVar2, BiFunction<? super T, ? super TRight, ? extends R> auiVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        ObjectHelper.m9873a(aunVar, "leftEnd is null");
        ObjectHelper.m9873a(aunVar2, "rightEnd is null");
        ObjectHelper.m9873a(auiVar, "resultSelector is null");
        return RxJavaPlugins.m9207a(new FlowableJoin(this, dbwVar, aunVar, aunVar2, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: v */
    public final Maybe<T> m10818v() {
        return RxJavaPlugins.m9205a(new FlowableLastMaybe(this));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: i */
    public final Single<T> m10887i(T t) {
        ObjectHelper.m9873a((Object) t, "defaultItem");
        return RxJavaPlugins.m9200a(new FlowableLastSingle(this, t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: w */
    public final Single<T> m10815w() {
        return RxJavaPlugins.m9200a(new FlowableLastSingle(this, null));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11188a(FlowableOperator<? extends R, ? super T> arzVar) {
        ObjectHelper.m9873a(arzVar, "lifter is null");
        return RxJavaPlugins.m9207a(new FlowableLift(this, arzVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: c */
    public final Flowable<T> m11018c(long j) {
        if (j >= 0) {
            return RxJavaPlugins.m9207a(new FlowableLimit(this, j));
        }
        throw new IllegalArgumentException("count >= 0 required but it was " + j);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: v */
    public final <R> Flowable<R> m10817v(Function<? super T, ? extends R> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new FlowableMap(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: x */
    public final Flowable<Notification<T>> m10813x() {
        return RxJavaPlugins.m9207a(new FlowableMaterialize(this));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: n */
    public final Flowable<T> m10848n(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11038b(this, dbwVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11063b(@AbstractC3889atm SingleSource<? extends T> ataVar) {
        ObjectHelper.m9873a(ataVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableMergeWithSingle(this, ataVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11066b(@AbstractC3889atm MaybeSource<? extends T> asiVar) {
        ObjectHelper.m9873a(asiVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableMergeWithMaybe(this, asiVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11067b(@AbstractC3889atm CompletableSource arsVar) {
        ObjectHelper.m9873a(arsVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableMergeWithCompletable(this, arsVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m11184a(Scheduler astVar) {
        return m11182a(astVar, false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<T> m11183a(Scheduler astVar, boolean z) {
        return m11182a(astVar, z, m11274a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11182a(Scheduler astVar, boolean z, int i) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableObserveOn(this, astVar, z, i));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <U> Flowable<U> m11081b(Class<U> cls) {
        ObjectHelper.m9873a(cls, "clazz is null");
        return m10996c((Predicate) Functions.m9912b((Class) cls)).m11231a((Class) cls);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: y */
    public final Flowable<T> m10811y() {
        return m11263a(m11274a(), false, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: a */
    public final Flowable<T> m11099a(boolean z) {
        return m11263a(m11274a(), z, true);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: d */
    public final Flowable<T> m10985d(int i) {
        return m11263a(i, false, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<T> m11264a(int i, boolean z) {
        return m11263a(i, z, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: a */
    public final Flowable<T> m11263a(int i, boolean z, boolean z2) {
        ObjectHelper.m9878a(i, "capacity");
        return RxJavaPlugins.m9207a(new FlowableOnBackpressureBuffer(this, i, z2, z, Functions.f17557c));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11262a(int i, boolean z, boolean z2, Action augVar) {
        ObjectHelper.m9873a(augVar, "onOverflow is null");
        ObjectHelper.m9878a(i, "capacity");
        return RxJavaPlugins.m9207a(new FlowableOnBackpressureBuffer(this, i, z2, z, augVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<T> m11265a(int i, Action augVar) {
        return m11262a(i, false, false, augVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11234a(long j, Action augVar, BackpressureOverflowStrategy arkVar) {
        ObjectHelper.m9873a(arkVar, "overflowStrategy is null");
        ObjectHelper.m9876a(j, "capacity");
        return RxJavaPlugins.m9207a(new FlowableOnBackpressureBufferStrategy(this, j, augVar, arkVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: z */
    public final Flowable<T> m10809z() {
        return RxJavaPlugins.m9207a((Flowable) new FlowableOnBackpressureDrop(this));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: j */
    public final Flowable<T> m10877j(Consumer<? super T> aumVar) {
        ObjectHelper.m9873a(aumVar, "onDrop is null");
        return RxJavaPlugins.m9207a((Flowable) new FlowableOnBackpressureDrop(this, aumVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: A */
    public final Flowable<T> m11307A() {
        return RxJavaPlugins.m9207a(new FlowableOnBackpressureLatest(this));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: w */
    public final Flowable<T> m10814w(Function<? super Throwable, ? extends Publisher<? extends T>> aunVar) {
        ObjectHelper.m9873a(aunVar, "resumeFunction is null");
        return RxJavaPlugins.m9207a(new FlowableOnErrorNext(this, aunVar, false));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: o */
    public final Flowable<T> m10843o(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "next is null");
        return m10814w(Functions.m9911b(dbwVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: x */
    public final Flowable<T> m10812x(Function<? super Throwable, ? extends T> aunVar) {
        ObjectHelper.m9873a(aunVar, "valueSupplier is null");
        return RxJavaPlugins.m9207a(new FlowableOnErrorReturn(this, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: j */
    public final Flowable<T> m10878j(T t) {
        ObjectHelper.m9873a((Object) t, "item is null");
        return m10812x(Functions.m9911b(t));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: p */
    public final Flowable<T> m10838p(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "next is null");
        return RxJavaPlugins.m9207a(new FlowableOnErrorNext(this, Functions.m9911b(dbwVar), true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: B */
    public final Flowable<T> m11305B() {
        return RxJavaPlugins.m9207a(new FlowableDetach(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: C */
    public final ParallelFlowable<T> m11303C() {
        return ParallelFlowable.m9240a(this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final ParallelFlowable<T> m10958e(int i) {
        ObjectHelper.m9878a(i, "parallelism");
        return ParallelFlowable.m9239a(this, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public final ParallelFlowable<T> m11020c(int i, int i2) {
        ObjectHelper.m9878a(i, "parallelism");
        ObjectHelper.m9878a(i2, "prefetch");
        return ParallelFlowable.m9238a(this, i, i2);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: D */
    public final ConnectableFlowable<T> m11301D() {
        return m10936f(m11274a());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: y */
    public final <R> Flowable<R> m10810y(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar) {
        return m10895h(aunVar, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: h */
    public final <R> Flowable<R> m10895h(Function<? super Flowable<T>, ? extends Publisher<? extends R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9878a(i, "prefetch");
        return RxJavaPlugins.m9207a(new FlowablePublishMulticast(this, aunVar, i, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public final ConnectableFlowable<T> m10936f(int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        return FlowablePublish.m9757a((Flowable) this, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public final Flowable<T> m10918g(int i) {
        return m11182a(ImmediateThinScheduler.f19895b, true, i);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Maybe<T> m11179a(BiFunction<T, T, T> auiVar) {
        ObjectHelper.m9873a(auiVar, "reducer is null");
        return RxJavaPlugins.m9205a(new FlowableReduceMaybe(this, auiVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Single<R> m11212a(R r, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(r, "seed is null");
        ObjectHelper.m9873a(auiVar, "reducer is null");
        return RxJavaPlugins.m9200a(new FlowableReduceSeedSingle(this, r, auiVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <R> Single<R> m11070b(Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(callable, "seedSupplier is null");
        ObjectHelper.m9873a(auiVar, "reducer is null");
        return RxJavaPlugins.m9200a(new FlowableReduceWithSingle(this, callable, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: E */
    public final Flowable<T> m11299E() {
        return m10983d((long) cjm.f20759b);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public final Flowable<T> m10983d(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("times >= 0 required but it was " + j);
        } else if (i == 0) {
            return m11094b();
        } else {
            return RxJavaPlugins.m9207a(new FlowableRepeat(this, j));
        }
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11177a(BooleanSupplier aukVar) {
        ObjectHelper.m9873a(aukVar, "stop is null");
        return RxJavaPlugins.m9207a(new FlowableRepeatUntil(this, aukVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: z */
    public final Flowable<T> m10808z(Function<? super Flowable<Object>, ? extends Publisher<?>> aunVar) {
        ObjectHelper.m9873a(aunVar, "handler is null");
        return RxJavaPlugins.m9207a(new FlowableRepeatWhen(this, aunVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: F */
    public final ConnectableFlowable<T> m11297F() {
        return FlowableReplay.m9747a((Flowable) this);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: A */
    public final <R> Flowable<R> m11306A(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        return FlowableReplay.m9748a(FlowableInternalHelper.m9785a(this), (Function) aunVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: i */
    public final <R> Flowable<R> m10884i(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, int i) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return FlowableReplay.m9748a(FlowableInternalHelper.m9784a(this, i), (Function) aunVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <R> Flowable<R> m11163a(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, int i, long j, TimeUnit timeUnit) {
        return m11162a(aunVar, i, j, timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11162a(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return FlowableReplay.m9748a(FlowableInternalHelper.m9783a(this, i, j, timeUnit, astVar), (Function) aunVar);
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11161a(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, int i, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return FlowableReplay.m9748a(FlowableInternalHelper.m9784a(this, i), FlowableInternalHelper.m9778a(aunVar, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <R> Flowable<R> m11158a(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, long j, TimeUnit timeUnit) {
        return m11157a(aunVar, j, timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11157a(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return FlowableReplay.m9748a(FlowableInternalHelper.m9782a(this, j, timeUnit, astVar), (Function) aunVar);
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <R> Flowable<R> m11154a(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, Scheduler astVar) {
        ObjectHelper.m9873a(aunVar, "selector is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return FlowableReplay.m9748a(FlowableInternalHelper.m9785a(this), FlowableInternalHelper.m9778a(aunVar, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: h */
    public final ConnectableFlowable<T> m10903h(int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        return FlowableReplay.m9746a((Flowable) this, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final ConnectableFlowable<T> m11269a(int i, long j, TimeUnit timeUnit) {
        return m11268a(i, j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final ConnectableFlowable<T> m11268a(int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return FlowableReplay.m9744a(this, j, timeUnit, astVar, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final ConnectableFlowable<T> m11266a(int i, Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return FlowableReplay.m9742a((ConnectableFlowable) m10903h(i), astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public final ConnectableFlowable<T> m10916g(long j, TimeUnit timeUnit) {
        return m10915g(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: g */
    public final ConnectableFlowable<T> m10915g(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return FlowableReplay.m9745a(this, j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final ConnectableFlowable<T> m11065b(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return FlowableReplay.m9742a((ConnectableFlowable) m11297F(), astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: G */
    public final Flowable<T> m11295G() {
        return m11233a((long) cjm.f20759b, Functions.m9909c());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11060b(BiPredicate<? super Integer, ? super Throwable> aujVar) {
        ObjectHelper.m9873a(aujVar, "predicate is null");
        return RxJavaPlugins.m9207a(new FlowableRetryBiPredicate(this, aujVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final Flowable<T> m10957e(long j) {
        return m11233a(j, Functions.m9909c());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11233a(long j, Predicate<? super Throwable> auxVar) {
        if (j >= 0) {
            ObjectHelper.m9873a(auxVar, "predicate is null");
            return RxJavaPlugins.m9207a(new FlowableRetryPredicate(this, j, auxVar));
        }
        throw new IllegalArgumentException("times >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: e */
    public final Flowable<T> m10942e(Predicate<? super Throwable> auxVar) {
        return m11233a((long) cjm.f20759b, auxVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11059b(BooleanSupplier aukVar) {
        ObjectHelper.m9873a(aukVar, "stop is null");
        return m11233a((long) cjm.f20759b, Functions.m9926a(aukVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: B */
    public final Flowable<T> m11304B(Function<? super Flowable<Throwable>, ? extends Publisher<?>> aunVar) {
        ObjectHelper.m9873a(aunVar, "handler is null");
        return RxJavaPlugins.m9207a(new FlowableRetryWhen(this, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: c */
    public final void m10989c(Subscriber<? super T> dbxVar) {
        ObjectHelper.m9873a(dbxVar, "s is null");
        if (dbxVar instanceof SafeSubscriber) {
            m11187a((FlowableSubscriber) ((SafeSubscriber) dbxVar));
        } else {
            m11187a((FlowableSubscriber) new SafeSubscriber(dbxVar));
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: h */
    public final Flowable<T> m10901h(long j, TimeUnit timeUnit) {
        return m10900h(j, timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: b */
    public final Flowable<T> m11082b(long j, TimeUnit timeUnit, boolean z) {
        return m11084b(j, timeUnit, Schedulers.m9050a(), z);
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: h */
    public final Flowable<T> m10900h(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableSampleTimed(this, j, timeUnit, astVar, false));
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11084b(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableSampleTimed(this, j, timeUnit, astVar, z));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: q */
    public final <U> Flowable<T> m10833q(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "sampler is null");
        return RxJavaPlugins.m9207a(new FlowableSamplePublisher(this, dbwVar, false));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U> Flowable<T> m11101a(Publisher<U> dbwVar, boolean z) {
        ObjectHelper.m9873a(dbwVar, "sampler is null");
        return RxJavaPlugins.m9207a(new FlowableSamplePublisher(this, dbwVar, z));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11061b(BiFunction<T, T, T> auiVar) {
        ObjectHelper.m9873a(auiVar, "accumulator is null");
        return RxJavaPlugins.m9207a(new FlowableScan(this, auiVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <R> Flowable<R> m11074b(R r, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(r, "initialValue is null");
        return m11006c(Functions.m9932a(r), auiVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final <R> Flowable<R> m11006c(Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        ObjectHelper.m9873a(callable, "seedSupplier is null");
        ObjectHelper.m9873a(auiVar, "accumulator is null");
        return RxJavaPlugins.m9207a(new FlowableScanSeed(this, callable, auiVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: H */
    public final Flowable<T> m11293H() {
        return RxJavaPlugins.m9207a(new FlowableSerialized(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: I */
    public final Flowable<T> m11291I() {
        return m11301D().m9960U();
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: J */
    public final Maybe<T> m11289J() {
        return RxJavaPlugins.m9205a(new FlowableSingleMaybe(this));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: k */
    public final Single<T> m10869k(T t) {
        ObjectHelper.m9873a((Object) t, "defaultItem is null");
        return RxJavaPlugins.m9200a(new FlowableSingleSingle(this, t));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: K */
    public final Single<T> m11287K() {
        return RxJavaPlugins.m9200a(new FlowableSingleSingle(this, null));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: f */
    public final Flowable<T> m10935f(long j) {
        if (j <= 0) {
            return RxJavaPlugins.m9207a(this);
        }
        return RxJavaPlugins.m9207a(new FlowableSkip(this, j));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: i */
    public final Flowable<T> m10889i(long j, TimeUnit timeUnit) {
        return m10828r(m11086b(j, timeUnit));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: i */
    public final Flowable<T> m10888i(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10828r(m11085b(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: i */
    public final Flowable<T> m10891i(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count >= 0 required but it was " + i);
        } else if (i == 0) {
            return RxJavaPlugins.m9207a(this);
        } else {
            return RxJavaPlugins.m9207a(new FlowableSkipLast(this, i));
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: j */
    public final Flowable<T> m10880j(long j, TimeUnit timeUnit) {
        return m11238a(j, timeUnit, Schedulers.m9050a(), false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: c */
    public final Flowable<T> m11012c(long j, TimeUnit timeUnit, boolean z) {
        return m11238a(j, timeUnit, Schedulers.m9050a(), z, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: j */
    public final Flowable<T> m10879j(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11238a(j, timeUnit, astVar, false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: c */
    public final Flowable<T> m11013c(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        return m11238a(j, timeUnit, astVar, z, m11274a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11238a(long j, TimeUnit timeUnit, Scheduler astVar, boolean z, int i) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableSkipLastTimed(this, j, timeUnit, astVar, i << 1, z));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: r */
    public final <U> Flowable<T> m10828r(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableSkipUntil(this, dbwVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final Flowable<T> m10923f(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9207a(new FlowableSkipWhile(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: L */
    public final Flowable<T> m11285L() {
        return m11278P().m10030l().m10817v(Functions.m9931a(Functions.m9902h())).m10829r(Functions.m9935a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11210a(Comparator<? super T> comparator) {
        ObjectHelper.m9873a(comparator, "sortFunction");
        return m11278P().m10030l().m10817v(Functions.m9931a((Comparator) comparator)).m10829r(Functions.m9935a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: h */
    public final Flowable<T> m10899h(Iterable<? extends T> iterable) {
        return m11025b(m10952e((Iterable) iterable), this);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: s */
    public final Flowable<T> m10825s(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11025b(dbwVar, this);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: l */
    public final Flowable<T> m10860l(T t) {
        ObjectHelper.m9873a((Object) t, "value is null");
        return m11025b(m11223a(t), this);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final Flowable<T> m11026b(T... tArr) {
        Flowable a = m11098a((Object[]) tArr);
        return a == m11094b() ? RxJavaPlugins.m9207a(this) : m11025b(a, this);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: M */
    public final Disposable m11283M() {
        return m11169a((Consumer) Functions.m9914b(), (Consumer<? super Throwable>) Functions.f17560f, Functions.f17557c, (Consumer<? super dby>) FlowableInternalHelper.EnumC4103i.INSTANCE);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: k */
    public final Disposable m10868k(Consumer<? super T> aumVar) {
        return m11169a((Consumer) aumVar, (Consumer<? super Throwable>) Functions.f17560f, Functions.f17557c, (Consumer<? super dby>) FlowableInternalHelper.EnumC4103i.INSTANCE);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: b */
    public final Disposable m11057b(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2) {
        return m11169a((Consumer) aumVar, aumVar2, Functions.f17557c, (Consumer<? super dby>) FlowableInternalHelper.EnumC4103i.INSTANCE);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: b */
    public final Disposable m11056b(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar) {
        return m11169a((Consumer) aumVar, aumVar2, augVar, (Consumer<? super dby>) FlowableInternalHelper.EnumC4103i.INSTANCE);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Disposable m11169a(Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Consumer<? super dby> aumVar3) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ObjectHelper.m9873a(aumVar3, "onSubscribe is null");
        LambdaSubscriber brrVar = new LambdaSubscriber(aumVar, aumVar2, augVar, aumVar3);
        m11187a((FlowableSubscriber) brrVar);
        return brrVar;
    }

    @Override // p110z1.Publisher
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    public final void subscribe(Subscriber<? super T> dbxVar) {
        if (dbxVar instanceof FlowableSubscriber) {
            m11187a((FlowableSubscriber) ((FlowableSubscriber) dbxVar));
            return;
        }
        ObjectHelper.m9873a(dbxVar, "s is null");
        m11187a((FlowableSubscriber) new StrictSubscriber(dbxVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: a */
    public final void m11187a(FlowableSubscriber<? super T> asaVar) {
        ObjectHelper.m9873a(asaVar, "s is null");
        try {
            Subscriber<? super T> a = RxJavaPlugins.m9206a(this, asaVar);
            ObjectHelper.m9873a(a, "The RxJavaPlugins.onSubscribe hook returned a null FlowableSubscriber. Please check the handler provided to RxJavaPlugins.setOnFlowableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            mo9054d((Subscriber) a);
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
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: e */
    public final <E extends Subscriber<? super T>> E m10939e(E e) {
        subscribe(e);
        return e;
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final Flowable<T> m11005c(@AbstractC3889atm Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return m11064b(astVar, !(this instanceof FlowableCreate));
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<T> m11064b(@AbstractC3889atm Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableSubscribeOn(this, astVar, z));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: t */
    public final Flowable<T> m10822t(Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableSwitchIfEmpty(this, dbwVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: C */
    public final <R> Flowable<R> m11302C(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m10875j(aunVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: j */
    public final <R> Flowable<R> m10875j(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i) {
        return m11053b((Function) aunVar, i, false);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: D */
    public final Completable m11300D(@AbstractC3889atm Function<? super T, ? extends CompletableSource> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9209a(new FlowableSwitchMapCompletable(this, aunVar, false));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: E */
    public final Completable m11298E(@AbstractC3889atm Function<? super T, ? extends CompletableSource> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9209a(new FlowableSwitchMapCompletable(this, aunVar, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: F */
    public final <R> Flowable<R> m11296F(Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        return m10866k(aunVar, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: k */
    public final <R> Flowable<R> m10866k(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i) {
        return m11053b((Function) aunVar, i, true);
    }

    /* renamed from: b */
    <R> Flowable<R> m11053b(Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, boolean z) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        ObjectHelper.m9878a(i, "bufferSize");
        if (!(this instanceof ScalarCallable)) {
            return RxJavaPlugins.m9207a(new FlowableSwitchMap(this, aunVar, i, z));
        }
        Object call = ((ScalarCallable) this).call();
        if (call == null) {
            return m11094b();
        }
        return FlowableScalarXMap.m9736a(call, aunVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: G */
    public final <R> Flowable<R> m11294G(@AbstractC3889atm Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new FlowableSwitchMapMaybe(this, aunVar, false));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: H */
    public final <R> Flowable<R> m11292H(@AbstractC3889atm Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new FlowableSwitchMapMaybe(this, aunVar, true));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: I */
    public final <R> Flowable<R> m11290I(@AbstractC3889atm Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new FlowableSwitchMapSingle(this, aunVar, false));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: J */
    public final <R> Flowable<R> m11288J(@AbstractC3889atm Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        ObjectHelper.m9873a(aunVar, "mapper is null");
        return RxJavaPlugins.m9207a(new FlowableSwitchMapSingle(this, aunVar, true));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: g */
    public final Flowable<T> m10917g(long j) {
        if (j >= 0) {
            return RxJavaPlugins.m9207a(new FlowableTake(this, j));
        }
        throw new IllegalArgumentException("count >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: k */
    public final Flowable<T> m10871k(long j, TimeUnit timeUnit) {
        return m10819u(m11086b(j, timeUnit));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: k */
    public final Flowable<T> m10870k(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10819u(m11085b(j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: j */
    public final Flowable<T> m10881j(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count >= 0 required but it was " + i);
        } else if (i == 0) {
            return RxJavaPlugins.m9207a(new FlowableIgnoreElements(this));
        } else {
            if (i == 1) {
                return RxJavaPlugins.m9207a(new FlowableTakeLastOne(this));
            }
            return RxJavaPlugins.m9207a(new FlowableTakeLast(this, i));
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public final Flowable<T> m11017c(long j, long j2, TimeUnit timeUnit) {
        return m11252a(j, j2, timeUnit, Schedulers.m9050a(), false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: c */
    public final Flowable<T> m11016c(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        return m11252a(j, j2, timeUnit, astVar, false, m11274a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11252a(long j, long j2, TimeUnit timeUnit, Scheduler astVar, boolean z, int i) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9878a(i, "bufferSize");
        if (j >= 0) {
            return RxJavaPlugins.m9207a(new FlowableTakeLastTimed(this, j, j2, timeUnit, astVar, i, z));
        }
        throw new IndexOutOfBoundsException("count >= 0 required but it was " + j);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: l */
    public final Flowable<T> m10862l(long j, TimeUnit timeUnit) {
        return m11083b(j, timeUnit, Schedulers.m9050a(), false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public final Flowable<T> m10977d(long j, TimeUnit timeUnit, boolean z) {
        return m11083b(j, timeUnit, Schedulers.m9050a(), z, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: l */
    public final Flowable<T> m10861l(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11083b(j, timeUnit, astVar, false, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: d */
    public final Flowable<T> m10978d(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        return m11083b(j, timeUnit, astVar, z, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final Flowable<T> m11083b(long j, TimeUnit timeUnit, Scheduler astVar, boolean z, int i) {
        return m11252a((long) cjm.f20759b, j, timeUnit, astVar, z, i);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: g */
    public final Flowable<T> m10908g(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "stopPredicate is null");
        return RxJavaPlugins.m9207a(new FlowableTakeUntilPredicate(this, auxVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: u */
    public final <U> Flowable<T> m10819u(Publisher<U> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return RxJavaPlugins.m9207a(new FlowableTakeUntil(this, dbwVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: h */
    public final Flowable<T> m10894h(Predicate<? super T> auxVar) {
        ObjectHelper.m9873a(auxVar, "predicate is null");
        return RxJavaPlugins.m9207a(new FlowableTakeWhile(this, auxVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: m */
    public final Flowable<T> m10856m(long j, TimeUnit timeUnit) {
        return m10855m(j, timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: m */
    public final Flowable<T> m10855m(long j, TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableThrottleFirstTimed(this, j, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: n */
    public final Flowable<T> m10851n(long j, TimeUnit timeUnit) {
        return m10901h(j, timeUnit);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: n */
    public final Flowable<T> m10850n(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10900h(j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: o */
    public final Flowable<T> m10846o(long j, TimeUnit timeUnit) {
        return m10954e(j, timeUnit, Schedulers.m9050a(), false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: e */
    public final Flowable<T> m10953e(long j, TimeUnit timeUnit, boolean z) {
        return m10954e(j, timeUnit, Schedulers.m9050a(), z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: o */
    public final Flowable<T> m10845o(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10954e(j, timeUnit, astVar, false);
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: e */
    public final Flowable<T> m10954e(long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableThrottleLatest(this, j, timeUnit, astVar, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: p */
    public final Flowable<T> m10841p(long j, TimeUnit timeUnit) {
        return m10980d(j, timeUnit);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: p */
    public final Flowable<T> m10840p(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m10979d(j, timeUnit, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: N */
    public final Flowable<Timed<T>> m11281N() {
        return m11194a(TimeUnit.MILLISECONDS, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: d */
    public final Flowable<Timed<T>> m10972d(Scheduler astVar) {
        return m11194a(TimeUnit.MILLISECONDS, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: a */
    public final Flowable<Timed<T>> m11195a(TimeUnit timeUnit) {
        return m11194a(timeUnit, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: a */
    public final Flowable<Timed<T>> m11194a(TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableTimeInterval(this, timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: K */
    public final <V> Flowable<T> m11286K(Function<? super T, ? extends Publisher<V>> aunVar) {
        return m11039b((Publisher) null, aunVar, (Publisher) null);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <V> Flowable<T> m11155a(Function<? super T, ? extends Publisher<V>> aunVar, Flowable<? extends T> arvVar) {
        ObjectHelper.m9873a(arvVar, "other is null");
        return m11039b((Publisher) null, aunVar, arvVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: q */
    public final Flowable<T> m10836q(long j, TimeUnit timeUnit) {
        return m11236a(j, timeUnit, (Publisher) null, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11237a(long j, TimeUnit timeUnit, Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11236a(j, timeUnit, dbwVar, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<T> m11240a(long j, TimeUnit timeUnit, Scheduler astVar, Publisher<? extends T> dbwVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11236a(j, timeUnit, dbwVar, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: q */
    public final Flowable<T> m10835q(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11236a(j, timeUnit, (Publisher) null, astVar);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final <U, V> Flowable<T> m10993c(Publisher<U> dbwVar, Function<? super T, ? extends Publisher<V>> aunVar) {
        ObjectHelper.m9873a(dbwVar, "firstTimeoutIndicator is null");
        return m11039b(dbwVar, aunVar, (Publisher) null);
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U, V> Flowable<T> m11121a(Publisher<U> dbwVar, Function<? super T, ? extends Publisher<V>> aunVar, Publisher<? extends T> dbwVar2) {
        ObjectHelper.m9873a(dbwVar, "firstTimeoutSelector is null");
        ObjectHelper.m9873a(dbwVar2, "other is null");
        return m11039b(dbwVar, aunVar, dbwVar2);
    }

    /* renamed from: a */
    private Flowable<T> m11236a(long j, TimeUnit timeUnit, Publisher<? extends T> dbwVar, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "timeUnit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableTimeoutTimed(this, j, timeUnit, astVar, dbwVar));
    }

    /* renamed from: b */
    private <U, V> Flowable<T> m11039b(Publisher<U> dbwVar, Function<? super T, ? extends Publisher<V>> aunVar, Publisher<? extends T> dbwVar2) {
        ObjectHelper.m9873a(aunVar, "itemTimeoutIndicator is null");
        return RxJavaPlugins.m9207a(new FlowableTimeout(this, dbwVar, aunVar, dbwVar2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: O */
    public final Flowable<Timed<T>> m11279O() {
        return m11068b(TimeUnit.MILLISECONDS, Schedulers.m9050a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: e */
    public final Flowable<Timed<T>> m10949e(Scheduler astVar) {
        return m11068b(TimeUnit.MILLISECONDS, astVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    /* renamed from: b */
    public final Flowable<Timed<T>> m11069b(TimeUnit timeUnit) {
        return m11068b(timeUnit, Schedulers.m9050a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Flowable<Timed<T>> m11068b(TimeUnit timeUnit, Scheduler astVar) {
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return (Flowable<Timed<T>>) m10817v(Functions.m9929a(timeUnit, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.SPECIAL)
    /* renamed from: L */
    public final <R> R m11284L(Function<? super Flowable<T>, R> aunVar) {
        try {
            return (R) ((Function) ObjectHelper.m9873a(aunVar, "converter is null")).apply(this);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            throw ExceptionHelper.m9432a(th);
        }
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: P */
    public final Single<List<T>> m11278P() {
        return RxJavaPlugins.m9200a(new FlowableToListSingle(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: k */
    public final Single<List<T>> m10872k(int i) {
        ObjectHelper.m9878a(i, "capacityHint");
        return RxJavaPlugins.m9200a(new FlowableToListSingle(this, Functions.m9934a(i)));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: e */
    public final <U extends Collection<? super T>> Single<U> m10950e(Callable<U> callable) {
        ObjectHelper.m9873a(callable, "collectionSupplier is null");
        return RxJavaPlugins.m9200a(new FlowableToListSingle(this, callable));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: M */
    public final <K> Single<Map<K, T>> m11282M(Function<? super T, ? extends K> aunVar) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        return (Single<Map<K, T>>) m11071b(HashMapSupplier.asCallable(), Functions.m9924a((Function) aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <K, V> Single<Map<K, V>> m11050b(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        return (Single<Map<K, V>>) m11071b(HashMapSupplier.asCallable(), Functions.m9923a(aunVar, aunVar2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <K, V> Single<Map<K, V>> m11049b(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, Callable<? extends Map<K, V>> callable) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        return (Single<Map<K, V>>) m11071b(callable, Functions.m9923a(aunVar, aunVar2));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: N */
    public final <K> Single<Map<K, Collection<T>>> m11280N(Function<? super T, ? extends K> aunVar) {
        return (Single<Map<K, Collection<T>>>) m11145a((Function) aunVar, (Function) Functions.m9935a(), (Callable) HashMapSupplier.asCallable(), (Function) ArrayListSupplier.asFunction());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: c */
    public final <K, V> Single<Map<K, Collection<V>>> m11000c(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2) {
        return m11145a((Function) aunVar, (Function) aunVar2, (Callable) HashMapSupplier.asCallable(), (Function) ArrayListSupplier.asFunction());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <K, V> Single<Map<K, Collection<V>>> m11145a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, Callable<? extends Map<K, Collection<V>>> callable, Function<? super K, ? extends Collection<? super V>> aunVar3) {
        ObjectHelper.m9873a(aunVar, "keySelector is null");
        ObjectHelper.m9873a(aunVar2, "valueSelector is null");
        ObjectHelper.m9873a(callable, "mapSupplier is null");
        ObjectHelper.m9873a(aunVar3, "collectionFactory is null");
        return (Single<Map<K, Collection<V>>>) m11071b(callable, Functions.m9922a(aunVar, aunVar2, aunVar3));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: c */
    public final <K, V> Single<Map<K, Collection<V>>> m10999c(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, Callable<Map<K, Collection<V>>> callable) {
        return m11145a((Function) aunVar, (Function) aunVar2, (Callable) callable, (Function) ArrayListSupplier.asFunction());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: Q */
    public final Observable<T> m11277Q() {
        return RxJavaPlugins.m9203a(new ObservableFromPublisher(this));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: R */
    public final Single<List<T>> m11276R() {
        return m11073b((Comparator) Functions.m9902h());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final Single<List<T>> m11073b(Comparator<? super T> comparator) {
        ObjectHelper.m9873a(comparator, "comparator is null");
        return (Single<List<T>>) m11278P().m10035i(Functions.m9931a((Comparator) comparator));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Single<List<T>> m11209a(Comparator<? super T> comparator, int i) {
        ObjectHelper.m9873a(comparator, "comparator is null");
        return (Single<List<T>>) m10872k(i).m10035i(Functions.m9931a((Comparator) comparator));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: l */
    public final Single<List<T>> m10863l(int i) {
        return m11209a(Functions.m9902h(), i);
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: f */
    public final Flowable<T> m10929f(Scheduler astVar) {
        ObjectHelper.m9873a(astVar, "scheduler is null");
        return RxJavaPlugins.m9207a(new FlowableUnsubscribeOn(this, astVar));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: h */
    public final Flowable<Flowable<T>> m10902h(long j) {
        return m11259a(j, j, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: b */
    public final Flowable<Flowable<T>> m11089b(long j, long j2) {
        return m11259a(j, j2, m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final Flowable<Flowable<T>> m11259a(long j, long j2, int i) {
        ObjectHelper.m9876a(j2, MSVSSConstants.WRITABLE_SKIP);
        ObjectHelper.m9876a(j, "count");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableWindow(this, j, j2, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: d */
    public final Flowable<Flowable<T>> m10982d(long j, long j2, TimeUnit timeUnit) {
        return m11254a(j, j2, timeUnit, Schedulers.m9050a(), m11274a());
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: d */
    public final Flowable<Flowable<T>> m10981d(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        return m11254a(j, j2, timeUnit, astVar, m11274a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<Flowable<T>> m11254a(long j, long j2, TimeUnit timeUnit, Scheduler astVar, int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9876a(j, "timespan");
        ObjectHelper.m9876a(j2, "timeskip");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        return RxJavaPlugins.m9207a(new FlowableWindowTimed(this, j, j2, timeUnit, astVar, cjm.f20759b, i, false));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: r */
    public final Flowable<Flowable<T>> m10831r(long j, TimeUnit timeUnit) {
        return m11242a(j, timeUnit, Schedulers.m9050a(), (long) cjm.f20759b, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<Flowable<T>> m11248a(long j, TimeUnit timeUnit, long j2) {
        return m11242a(j, timeUnit, Schedulers.m9050a(), j2, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17507c)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<Flowable<T>> m11247a(long j, TimeUnit timeUnit, long j2, boolean z) {
        return m11242a(j, timeUnit, Schedulers.m9050a(), j2, z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: r */
    public final Flowable<Flowable<T>> m10830r(long j, TimeUnit timeUnit, Scheduler astVar) {
        return m11242a(j, timeUnit, astVar, (long) cjm.f20759b, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<Flowable<T>> m11243a(long j, TimeUnit timeUnit, Scheduler astVar, long j2) {
        return m11242a(j, timeUnit, astVar, j2, false);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: a */
    public final Flowable<Flowable<T>> m11242a(long j, TimeUnit timeUnit, Scheduler astVar, long j2, boolean z) {
        return m11241a(j, timeUnit, astVar, j2, z, m11274a());
    }

    @SchedulerSupport(m10000a = SchedulerSupport.f17506b)
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final Flowable<Flowable<T>> m11241a(long j, TimeUnit timeUnit, Scheduler astVar, long j2, boolean z, int i) {
        ObjectHelper.m9878a(i, "bufferSize");
        ObjectHelper.m9873a(astVar, "scheduler is null");
        ObjectHelper.m9873a(timeUnit, "unit is null");
        ObjectHelper.m9876a(j2, "count");
        return RxJavaPlugins.m9207a(new FlowableWindowTimed(this, j, j, timeUnit, astVar, j2, i, z));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: v */
    public final <B> Flowable<Flowable<T>> m10816v(Publisher<B> dbwVar) {
        return m10906g(dbwVar, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: g */
    public final <B> Flowable<Flowable<T>> m10906g(Publisher<B> dbwVar, int i) {
        ObjectHelper.m9873a(dbwVar, "boundaryIndicator is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableWindowBoundary(this, dbwVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: d */
    public final <U, V> Flowable<Flowable<T>> m10962d(Publisher<U> dbwVar, Function<? super U, ? extends Publisher<V>> aunVar) {
        return m11123a(dbwVar, aunVar, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U, V> Flowable<Flowable<T>> m11123a(Publisher<U> dbwVar, Function<? super U, ? extends Publisher<V>> aunVar, int i) {
        ObjectHelper.m9873a(dbwVar, "openingIndicator is null");
        ObjectHelper.m9873a(aunVar, "closingIndicator is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableWindowBoundarySelector(this, dbwVar, aunVar, i));
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    /* renamed from: f */
    public final <B> Flowable<Flowable<T>> m10930f(Callable<? extends Publisher<B>> callable) {
        return m11207a(callable, m11274a());
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.ERROR)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <B> Flowable<Flowable<T>> m11207a(Callable<? extends Publisher<B>> callable, int i) {
        ObjectHelper.m9873a(callable, "boundaryIndicatorSupplier is null");
        ObjectHelper.m9878a(i, "bufferSize");
        return RxJavaPlugins.m9207a(new FlowableWindowBoundarySupplier(this, callable, i));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U, R> Flowable<R> m11127a(Publisher<? extends U> dbwVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        ObjectHelper.m9873a(auiVar, "combiner is null");
        return RxJavaPlugins.m9207a(new FlowableWithLatestFrom(this, auiVar, dbwVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <T1, T2, R> Flowable<R> m11113a(Publisher<T1> dbwVar, Publisher<T2> dbwVar2, Function3<? super T, ? super T1, ? super T2, R> auoVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        return m10987c(new Publisher[]{dbwVar, dbwVar2}, Functions.m9921a((Function3) auoVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <T1, T2, T3, R> Flowable<R> m11110a(Publisher<T1> dbwVar, Publisher<T2> dbwVar2, Publisher<T3> dbwVar3, Function4<? super T, ? super T1, ? super T2, ? super T3, R> aupVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        return m10987c(new Publisher[]{dbwVar, dbwVar2, dbwVar3}, Functions.m9920a((Function4) aupVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <T1, T2, T3, T4, R> Flowable<R> m11107a(Publisher<T1> dbwVar, Publisher<T2> dbwVar2, Publisher<T3> dbwVar3, Publisher<T4> dbwVar4, Function5<? super T, ? super T1, ? super T2, ? super T3, ? super T4, R> auqVar) {
        ObjectHelper.m9873a(dbwVar, "source1 is null");
        ObjectHelper.m9873a(dbwVar2, "source2 is null");
        ObjectHelper.m9873a(dbwVar3, "source3 is null");
        ObjectHelper.m9873a(dbwVar4, "source4 is null");
        return m10987c(new Publisher[]{dbwVar, dbwVar2, dbwVar3, dbwVar4}, Functions.m9919a((Function5) auqVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: c */
    public final <R> Flowable<R> m10987c(Publisher<?>[] dbwVarArr, Function<? super Object[], R> aunVar) {
        ObjectHelper.m9873a(dbwVarArr, "others is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        return RxJavaPlugins.m9207a(new FlowableWithLatestFromMany(this, dbwVarArr, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: d */
    public final <R> Flowable<R> m10975d(Iterable<? extends Publisher<?>> iterable, Function<? super Object[], R> aunVar) {
        ObjectHelper.m9873a(iterable, "others is null");
        ObjectHelper.m9873a(aunVar, "combiner is null");
        return RxJavaPlugins.m9207a(new FlowableWithLatestFromMany(this, iterable, aunVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: a */
    public final <U, R> Flowable<R> m11227a(Iterable<U> iterable, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(iterable, "other is null");
        ObjectHelper.m9873a(auiVar, "zipper is null");
        return RxJavaPlugins.m9207a(new FlowableZipIterable(this, iterable, auiVar));
    }

    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: b */
    public final <U, R> Flowable<R> m11042b(Publisher<? extends U> dbwVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        ObjectHelper.m9873a(dbwVar, "other is null");
        return m11037b(this, dbwVar, auiVar);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <U, R> Flowable<R> m11126a(Publisher<? extends U> dbwVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z) {
        return m11117a(this, dbwVar, auiVar, z);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final <U, R> Flowable<R> m11125a(Publisher<? extends U> dbwVar, BiFunction<? super T, ? super U, ? extends R> auiVar, boolean z, int i) {
        return m11116a(this, dbwVar, auiVar, z, i);
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.UNBOUNDED_IN)
    /* renamed from: S */
    public final TestSubscriber<T> m11275S() {
        TestSubscriber<T> bvgVar = new TestSubscriber<>();
        m11187a((FlowableSubscriber) bvgVar);
        return bvgVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: i */
    public final TestSubscriber<T> m10890i(long j) {
        TestSubscriber<T> bvgVar = new TestSubscriber<>(j);
        m11187a((FlowableSubscriber) bvgVar);
        return bvgVar;
    }

    @CheckReturnValue
    @SchedulerSupport(m10000a = "none")
    @BackpressureSupport(m10001a = BackpressureKind.FULL)
    /* renamed from: a */
    public final TestSubscriber<T> m11232a(long j, boolean z) {
        TestSubscriber<T> bvgVar = new TestSubscriber<>(j);
        if (z) {
            bvgVar.cancel();
        }
        m11187a((FlowableSubscriber) bvgVar);
        return bvgVar;
    }
}
