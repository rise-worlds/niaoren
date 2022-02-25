package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.avg */
/* loaded from: classes3.dex */
public final class Functions {

    /* renamed from: a */
    static final Function<Object, Object> f17555a = new C3933x();

    /* renamed from: b */
    public static final Runnable f17556b = new RunnableC3927r();

    /* renamed from: c */
    public static final Action f17557c = new C3924o();

    /* renamed from: d */
    static final Consumer<Object> f17558d = new C3925p();

    /* renamed from: e */
    public static final Consumer<Throwable> f17559e = new C3929t();

    /* renamed from: f */
    public static final Consumer<Throwable> f17560f = new C3905ah();

    /* renamed from: g */
    public static final LongConsumer f17561g = new C3926q();

    /* renamed from: h */
    static final Predicate<Object> f17562h = new C3910am();

    /* renamed from: i */
    static final Predicate<Object> f17563i = new C3930u();

    /* renamed from: j */
    static final Callable<Object> f17564j = new CallableC3904ag();

    /* renamed from: k */
    static final Comparator<Object> f17565k = new C3900ac();

    /* renamed from: l */
    public static final Consumer<dby> f17566l = new C3898aa();

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static <T1, T2, R> Function<Object[], R> m9927a(BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
        ObjectHelper.m9873a(auiVar, "f is null");
        return new C3911b(auiVar);
    }

    /* renamed from: a */
    public static <T1, T2, T3, R> Function<Object[], R> m9921a(Function3<T1, T2, T3, R> auoVar) {
        ObjectHelper.m9873a(auoVar, "f is null");
        return new C3912c(auoVar);
    }

    /* renamed from: a */
    public static <T1, T2, T3, T4, R> Function<Object[], R> m9920a(Function4<T1, T2, T3, T4, R> aupVar) {
        ObjectHelper.m9873a(aupVar, "f is null");
        return new C3913d(aupVar);
    }

    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, R> Function<Object[], R> m9919a(Function5<T1, T2, T3, T4, T5, R> auqVar) {
        ObjectHelper.m9873a(auqVar, "f is null");
        return new C3914e(auqVar);
    }

    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, R> Function<Object[], R> m9918a(Function6<T1, T2, T3, T4, T5, T6, R> aurVar) {
        ObjectHelper.m9873a(aurVar, "f is null");
        return new C3915f(aurVar);
    }

    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, R> Function<Object[], R> m9917a(Function7<T1, T2, T3, T4, T5, T6, T7, R> ausVar) {
        ObjectHelper.m9873a(ausVar, "f is null");
        return new C3916g(ausVar);
    }

    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Function<Object[], R> m9916a(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> autVar) {
        ObjectHelper.m9873a(autVar, "f is null");
        return new C3917h(autVar);
    }

    /* renamed from: a */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function<Object[], R> m9915a(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> auuVar) {
        ObjectHelper.m9873a(auuVar, "f is null");
        return new C3918i(auuVar);
    }

    /* renamed from: a */
    public static <T> Function<T, T> m9935a() {
        return (Function<T, T>) f17555a;
    }

    /* renamed from: b */
    public static <T> Consumer<T> m9914b() {
        return (Consumer<T>) f17558d;
    }

    /* renamed from: c */
    public static <T> Predicate<T> m9909c() {
        return (Predicate<T>) f17562h;
    }

    /* renamed from: d */
    public static <T> Predicate<T> m9906d() {
        return (Predicate<T>) f17563i;
    }

    /* renamed from: e */
    public static <T> Callable<T> m9905e() {
        return (Callable<T>) f17564j;
    }

    /* renamed from: f */
    public static <T> Comparator<T> m9904f() {
        return (Comparator<T>) f17565k;
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$v */
    /* loaded from: classes3.dex */
    static final class C3931v implements Action {

        /* renamed from: a */
        final Future<?> f17593a;

        C3931v(Future<?> future) {
            this.f17593a = future;
        }

        @Override // p110z1.Action
        /* renamed from: a */
        public void mo9442a() throws Exception {
            this.f17593a.get();
        }
    }

    /* renamed from: a */
    public static Action m9930a(Future<?> future) {
        return new C3931v(future);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$y */
    /* loaded from: classes3.dex */
    static final class CallableC3934y<T, U> implements Callable<U>, Function<T, U> {

        /* renamed from: a */
        final U f17594a;

        CallableC3934y(U u) {
            this.f17594a = u;
        }

        @Override // java.util.concurrent.Callable
        public U call() throws Exception {
            return this.f17594a;
        }

        @Override // p110z1.Function
        public U apply(T t) throws Exception {
            return this.f17594a;
        }
    }

    /* renamed from: a */
    public static <T> Callable<T> m9932a(T t) {
        return new CallableC3934y(t);
    }

    /* renamed from: b */
    public static <T, U> Function<T, U> m9911b(U u) {
        return new CallableC3934y(u);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$m */
    /* loaded from: classes3.dex */
    static final class C3922m<T, U> implements Function<T, U> {

        /* renamed from: a */
        final Class<U> f17590a;

        C3922m(Class<U> cls) {
            this.f17590a = cls;
        }

        @Override // p110z1.Function
        public U apply(T t) throws Exception {
            return this.f17590a.cast(t);
        }
    }

    /* renamed from: a */
    public static <T, U> Function<T, U> m9933a(Class<U> cls) {
        return new C3922m(cls);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$j */
    /* loaded from: classes3.dex */
    static final class CallableC3919j<T> implements Callable<List<T>> {

        /* renamed from: a */
        final int f17587a;

        CallableC3919j(int i) {
            this.f17587a = i;
        }

        /* renamed from: a */
        public List<T> call() throws Exception {
            return new ArrayList(this.f17587a);
        }
    }

    /* renamed from: a */
    public static <T> Callable<List<T>> m9934a(int i) {
        return new CallableC3919j(i);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$s */
    /* loaded from: classes3.dex */
    static final class C3928s<T> implements Predicate<T> {

        /* renamed from: a */
        final T f17592a;

        C3928s(T t) {
            this.f17592a = t;
        }

        @Override // p110z1.Predicate
        public boolean test(T t) throws Exception {
            return ObjectHelper.m9874a(t, this.f17592a);
        }
    }

    /* renamed from: c */
    public static <T> Predicate<T> m9908c(T t) {
        return new C3928s(t);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$w */
    /* loaded from: classes3.dex */
    enum EnumC3932w implements Callable<Set<Object>> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    /* renamed from: g */
    public static <T> Callable<Set<T>> m9903g() {
        return EnumC3932w.INSTANCE;
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$af */
    /* loaded from: classes3.dex */
    static final class C3903af<T> implements Consumer<T> {

        /* renamed from: a */
        final Consumer<? super Notification<T>> f17570a;

        C3903af(Consumer<? super Notification<T>> aumVar) {
            this.f17570a = aumVar;
        }

        @Override // p110z1.Consumer
        public void accept(T t) throws Exception {
            this.f17570a.accept(Notification.m10643a(t));
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ae */
    /* loaded from: classes3.dex */
    static final class C3902ae<T> implements Consumer<Throwable> {

        /* renamed from: a */
        final Consumer<? super Notification<T>> f17569a;

        C3902ae(Consumer<? super Notification<T>> aumVar) {
            this.f17569a = aumVar;
        }

        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.f17569a.accept(Notification.m10642a(th));
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ad */
    /* loaded from: classes3.dex */
    static final class C3901ad<T> implements Action {

        /* renamed from: a */
        final Consumer<? super Notification<T>> f17568a;

        C3901ad(Consumer<? super Notification<T>> aumVar) {
            this.f17568a = aumVar;
        }

        @Override // p110z1.Action
        /* renamed from: a */
        public void mo9442a() throws Exception {
            this.f17568a.accept(Notification.m10637f());
        }
    }

    /* renamed from: a */
    public static <T> Consumer<T> m9925a(Consumer<? super Notification<T>> aumVar) {
        return new C3903af(aumVar);
    }

    /* renamed from: b */
    public static <T> Consumer<Throwable> m9910b(Consumer<? super Notification<T>> aumVar) {
        return new C3902ae(aumVar);
    }

    /* renamed from: c */
    public static <T> Action m9907c(Consumer<? super Notification<T>> aumVar) {
        return new C3901ad(aumVar);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$a */
    /* loaded from: classes3.dex */
    static final class C3897a<T> implements Consumer<T> {

        /* renamed from: a */
        final Action f17567a;

        C3897a(Action augVar) {
            this.f17567a = augVar;
        }

        @Override // p110z1.Consumer
        public void accept(T t) throws Exception {
            this.f17567a.mo9442a();
        }
    }

    /* renamed from: a */
    public static <T> Consumer<T> m9928a(Action augVar) {
        return new C3897a(augVar);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$n */
    /* loaded from: classes3.dex */
    static final class C3923n<T, U> implements Predicate<T> {

        /* renamed from: a */
        final Class<U> f17591a;

        C3923n(Class<U> cls) {
            this.f17591a = cls;
        }

        @Override // p110z1.Predicate
        public boolean test(T t) throws Exception {
            return this.f17591a.isInstance(t);
        }
    }

    /* renamed from: b */
    public static <T, U> Predicate<T> m9912b(Class<U> cls) {
        return new C3923n(cls);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$k */
    /* loaded from: classes3.dex */
    static final class C3920k<T> implements Predicate<T> {

        /* renamed from: a */
        final BooleanSupplier f17588a;

        C3920k(BooleanSupplier aukVar) {
            this.f17588a = aukVar;
        }

        @Override // p110z1.Predicate
        public boolean test(T t) throws Exception {
            return !this.f17588a.getAsBoolean();
        }
    }

    /* renamed from: a */
    public static <T> Predicate<T> m9926a(BooleanSupplier aukVar) {
        return new C3920k(aukVar);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ai */
    /* loaded from: classes3.dex */
    static final class C3906ai<T> implements Function<T, Timed<T>> {

        /* renamed from: a */
        final TimeUnit f17571a;

        /* renamed from: b */
        final Scheduler f17572b;

        C3906ai(TimeUnit timeUnit, Scheduler astVar) {
            this.f17571a = timeUnit;
            this.f17572b = astVar;
        }

        /* renamed from: a */
        public Timed<T> apply(T t) throws Exception {
            return new Timed<>(t, this.f17572b.mo9035a(this.f17571a), this.f17571a);
        }
    }

    /* renamed from: a */
    public static <T> Function<T, Timed<T>> m9929a(TimeUnit timeUnit, Scheduler astVar) {
        return new C3906ai(timeUnit, astVar);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$aj */
    /* loaded from: classes3.dex */
    static final class C3907aj<K, T> implements BiConsumer<Map<K, T>, T> {

        /* renamed from: a */
        private final Function<? super T, ? extends K> f17573a;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.BiConsumer
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9895a(Object obj, Object obj2) throws Exception {
            m9897a((Map<K, Map<K, T>>) obj, (Map<K, T>) obj2);
        }

        C3907aj(Function<? super T, ? extends K> aunVar) {
            this.f17573a = aunVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public void m9897a(Map<K, T> map, T t) throws Exception {
            map.put(this.f17573a.apply(t), t);
        }
    }

    /* renamed from: a */
    public static <T, K> BiConsumer<Map<K, T>, T> m9924a(Function<? super T, ? extends K> aunVar) {
        return new C3907aj(aunVar);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ak */
    /* loaded from: classes3.dex */
    static final class C3908ak<K, V, T> implements BiConsumer<Map<K, V>, T> {

        /* renamed from: a */
        private final Function<? super T, ? extends V> f17574a;

        /* renamed from: b */
        private final Function<? super T, ? extends K> f17575b;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.BiConsumer
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9895a(Object obj, Object obj2) throws Exception {
            m9896a((Map) ((Map) obj), (Map<K, V>) obj2);
        }

        C3908ak(Function<? super T, ? extends V> aunVar, Function<? super T, ? extends K> aunVar2) {
            this.f17574a = aunVar;
            this.f17575b = aunVar2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public void m9896a(Map<K, V> map, T t) throws Exception {
            map.put(this.f17575b.apply(t), this.f17574a.apply(t));
        }
    }

    /* renamed from: a */
    public static <T, K, V> BiConsumer<Map<K, V>, T> m9923a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2) {
        return new C3908ak(aunVar2, aunVar);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$al */
    /* loaded from: classes3.dex */
    static final class C3909al<K, V, T> implements BiConsumer<Map<K, Collection<V>>, T> {

        /* renamed from: a */
        private final Function<? super K, ? extends Collection<? super V>> f17576a;

        /* renamed from: b */
        private final Function<? super T, ? extends V> f17577b;

        /* renamed from: c */
        private final Function<? super T, ? extends K> f17578c;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.BiConsumer
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo9895a(Object obj, Object obj2) throws Exception {
            m9894a((Map) ((Map) obj), (Map<K, Collection<V>>) obj2);
        }

        C3909al(Function<? super K, ? extends Collection<? super V>> aunVar, Function<? super T, ? extends V> aunVar2, Function<? super T, ? extends K> aunVar3) {
            this.f17576a = aunVar;
            this.f17577b = aunVar2;
            this.f17578c = aunVar3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public void m9894a(Map<K, Collection<V>> map, T t) throws Exception {
            Object apply = this.f17578c.apply(t);
            Collection collection = (Collection) map.get(apply);
            if (collection == null) {
                collection = (Collection) this.f17576a.apply(apply);
                map.put(apply, collection);
            }
            collection.add(this.f17577b.apply(t));
        }
    }

    /* renamed from: a */
    public static <T, K, V> BiConsumer<Map<K, Collection<V>>, T> m9922a(Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, Function<? super K, ? extends Collection<? super V>> aunVar3) {
        return new C3909al(aunVar3, aunVar2, aunVar);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ab */
    /* loaded from: classes3.dex */
    enum EnumC3899ab implements Comparator<Object> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* renamed from: h */
    public static <T> Comparator<T> m9902h() {
        return EnumC3899ab.INSTANCE;
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$z */
    /* loaded from: classes3.dex */
    static final class C3935z<T> implements Function<List<T>, List<T>> {

        /* renamed from: a */
        final Comparator<? super T> f17595a;

        C3935z(Comparator<? super T> comparator) {
            this.f17595a = comparator;
        }

        /* renamed from: a */
        public List<T> apply(List<T> list) {
            Collections.sort(list, this.f17595a);
            return list;
        }
    }

    /* renamed from: a */
    public static <T> Function<List<T>, List<T>> m9931a(Comparator<? super T> comparator) {
        return new C3935z(comparator);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$b */
    /* loaded from: classes3.dex */
    static final class C3911b<T1, T2, R> implements Function<Object[], R> {

        /* renamed from: a */
        final BiFunction<? super T1, ? super T2, ? extends R> f17579a;

        C3911b(BiFunction<? super T1, ? super T2, ? extends R> auiVar) {
            this.f17579a = auiVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 2) {
                return (R) this.f17579a.apply(objArr[0], objArr[1]);
            }
            throw new IllegalArgumentException("Array of size 2 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$c */
    /* loaded from: classes3.dex */
    static final class C3912c<T1, T2, T3, R> implements Function<Object[], R> {

        /* renamed from: a */
        final Function3<T1, T2, T3, R> f17580a;

        C3912c(Function3<T1, T2, T3, R> auoVar) {
            this.f17580a = auoVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 3) {
                return (R) this.f17580a.m9949a(objArr[0], objArr[1], objArr[2]);
            }
            throw new IllegalArgumentException("Array of size 3 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$d */
    /* loaded from: classes3.dex */
    static final class C3913d<T1, T2, T3, T4, R> implements Function<Object[], R> {

        /* renamed from: a */
        final Function4<T1, T2, T3, T4, R> f17581a;

        C3913d(Function4<T1, T2, T3, T4, R> aupVar) {
            this.f17581a = aupVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 4) {
                return (R) this.f17581a.m9948a(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
            throw new IllegalArgumentException("Array of size 4 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$e */
    /* loaded from: classes3.dex */
    static final class C3914e<T1, T2, T3, T4, T5, R> implements Function<Object[], R> {

        /* renamed from: a */
        private final Function5<T1, T2, T3, T4, T5, R> f17582a;

        C3914e(Function5<T1, T2, T3, T4, T5, R> auqVar) {
            this.f17582a = auqVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 5) {
                return (R) this.f17582a.m9947a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
            throw new IllegalArgumentException("Array of size 5 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$f */
    /* loaded from: classes3.dex */
    static final class C3915f<T1, T2, T3, T4, T5, T6, R> implements Function<Object[], R> {

        /* renamed from: a */
        final Function6<T1, T2, T3, T4, T5, T6, R> f17583a;

        C3915f(Function6<T1, T2, T3, T4, T5, T6, R> aurVar) {
            this.f17583a = aurVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 6) {
                return (R) this.f17583a.m9946a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
            throw new IllegalArgumentException("Array of size 6 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$g */
    /* loaded from: classes3.dex */
    static final class C3916g<T1, T2, T3, T4, T5, T6, T7, R> implements Function<Object[], R> {

        /* renamed from: a */
        final Function7<T1, T2, T3, T4, T5, T6, T7, R> f17584a;

        C3916g(Function7<T1, T2, T3, T4, T5, T6, T7, R> ausVar) {
            this.f17584a = ausVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 7) {
                return (R) this.f17584a.m9945a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
            throw new IllegalArgumentException("Array of size 7 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$h */
    /* loaded from: classes3.dex */
    static final class C3917h<T1, T2, T3, T4, T5, T6, T7, T8, R> implements Function<Object[], R> {

        /* renamed from: a */
        final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> f17585a;

        C3917h(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> autVar) {
            this.f17585a = autVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 8) {
                return (R) this.f17585a.m9944a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
            throw new IllegalArgumentException("Array of size 8 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$i */
    /* loaded from: classes3.dex */
    static final class C3918i<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements Function<Object[], R> {

        /* renamed from: a */
        final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> f17586a;

        C3918i(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> auuVar) {
            this.f17586a = auuVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 9) {
                return (R) this.f17586a.m9943a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
            throw new IllegalArgumentException("Array of size 9 expected but got " + objArr.length);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$x */
    /* loaded from: classes3.dex */
    static final class C3933x implements Function<Object, Object> {
        @Override // p110z1.Function
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }

        C3933x() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$r */
    /* loaded from: classes3.dex */
    static final class RunnableC3927r implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }

        RunnableC3927r() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$o */
    /* loaded from: classes3.dex */
    static final class C3924o implements Action {
        @Override // p110z1.Action
        /* renamed from: a */
        public void mo9442a() {
        }

        public String toString() {
            return "EmptyAction";
        }

        C3924o() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$p */
    /* loaded from: classes3.dex */
    static final class C3925p implements Consumer<Object> {
        @Override // p110z1.Consumer
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }

        C3925p() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$t */
    /* loaded from: classes3.dex */
    static final class C3929t implements Consumer<Throwable> {
        C3929t() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            RxJavaPlugins.m9212a(th);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ah */
    /* loaded from: classes3.dex */
    static final class C3905ah implements Consumer<Throwable> {
        C3905ah() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            RxJavaPlugins.m9212a(new OnErrorNotImplementedException(th));
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$q */
    /* loaded from: classes3.dex */
    static final class C3926q implements LongConsumer {
        @Override // p110z1.LongConsumer
        /* renamed from: a */
        public void mo9883a(long j) {
        }

        C3926q() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$am */
    /* loaded from: classes3.dex */
    static final class C3910am implements Predicate<Object> {
        @Override // p110z1.Predicate
        public boolean test(Object obj) {
            return true;
        }

        C3910am() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$u */
    /* loaded from: classes3.dex */
    static final class C3930u implements Predicate<Object> {
        @Override // p110z1.Predicate
        public boolean test(Object obj) {
            return false;
        }

        C3930u() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ag */
    /* loaded from: classes3.dex */
    static final class CallableC3904ag implements Callable<Object> {
        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }

        CallableC3904ag() {
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$ac */
    /* loaded from: classes3.dex */
    static final class C3900ac implements Comparator<Object> {
        C3900ac() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$aa */
    /* loaded from: classes3.dex */
    static final class C3898aa implements Consumer<dby> {
        C3898aa() {
        }

        /* renamed from: a */
        public void accept(dby dbyVar) throws Exception {
            dbyVar.request(cjm.f20759b);
        }
    }

    /* renamed from: b */
    public static <T> Consumer<T> m9913b(int i) {
        return new C3921l(i);
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.avg$l */
    /* loaded from: classes3.dex */
    public static class C3921l implements Consumer<dby> {

        /* renamed from: a */
        final int f17589a;

        C3921l(int i) {
            this.f17589a = i;
        }

        /* renamed from: a */
        public void accept(dby dbyVar) throws Exception {
            dbyVar.request(this.f17589a);
        }
    }
}
