package p110z1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.bkt */
/* loaded from: classes3.dex */
public final class ObservableInternalHelper {
    private ObservableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$n */
    /* loaded from: classes3.dex */
    public static final class C4482n<T, S> implements BiFunction<S, Emitter<T>, S> {

        /* renamed from: a */
        final Consumer<Emitter<T>> f19192a;

        C4482n(Consumer<Emitter<T>> aumVar) {
            this.f19192a = aumVar;
        }

        /* renamed from: a */
        public S apply(S s, Emitter<T> aruVar) throws Exception {
            this.f19192a.accept(aruVar);
            return s;
        }
    }

    /* renamed from: a */
    public static <T, S> BiFunction<S, Emitter<T>, S> m9613a(Consumer<Emitter<T>> aumVar) {
        return new C4482n(aumVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$m */
    /* loaded from: classes3.dex */
    public static final class C4481m<T, S> implements BiFunction<S, Emitter<T>, S> {

        /* renamed from: a */
        final BiConsumer<S, Emitter<T>> f19191a;

        C4481m(BiConsumer<S, Emitter<T>> auhVar) {
            this.f19191a = auhVar;
        }

        /* renamed from: a */
        public S apply(S s, Emitter<T> aruVar) throws Exception {
            this.f19191a.mo9895a(s, aruVar);
            return s;
        }
    }

    /* renamed from: a */
    public static <T, S> BiFunction<S, Emitter<T>, S> m9614a(BiConsumer<S, Emitter<T>> auhVar) {
        return new C4481m(auhVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$f */
    /* loaded from: classes3.dex */
    public static final class C4474f<T, U> implements Function<T, ObservableSource<T>> {

        /* renamed from: a */
        final Function<? super T, ? extends ObservableSource<U>> f19184a;

        C4474f(Function<? super T, ? extends ObservableSource<U>> aunVar) {
            this.f19184a = aunVar;
        }

        /* renamed from: a */
        public ObservableSource<T> apply(T t) throws Exception {
            return new ObservableTake((ObservableSource) ObjectHelper.m9873a(this.f19184a.apply(t), "The itemDelay returned a null ObservableSource"), 1L).m10174v(Functions.m9911b(t)).m10264g((Observable<R>) t);
        }
    }

    /* renamed from: a */
    public static <T, U> Function<T, ObservableSource<T>> m9612a(Function<? super T, ? extends ObservableSource<U>> aunVar) {
        return new C4474f(aunVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$j */
    /* loaded from: classes3.dex */
    public static final class C4478j<T> implements Consumer<T> {

        /* renamed from: a */
        final Observer<T> f19187a;

        C4478j(Observer<T> assVar) {
            this.f19187a = assVar;
        }

        @Override // p110z1.Consumer
        public void accept(T t) throws Exception {
            this.f19187a.onNext(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$i */
    /* loaded from: classes3.dex */
    public static final class C4477i<T> implements Consumer<Throwable> {

        /* renamed from: a */
        final Observer<T> f19186a;

        C4477i(Observer<T> assVar) {
            this.f19186a = assVar;
        }

        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.f19186a.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$h */
    /* loaded from: classes3.dex */
    public static final class C4476h<T> implements Action {

        /* renamed from: a */
        final Observer<T> f19185a;

        C4476h(Observer<T> assVar) {
            this.f19185a = assVar;
        }

        @Override // p110z1.Action
        /* renamed from: a */
        public void mo9442a() throws Exception {
            this.f19185a.onComplete();
        }
    }

    /* renamed from: a */
    public static <T> Consumer<T> m9615a(Observer<T> assVar) {
        return new C4478j(assVar);
    }

    /* renamed from: b */
    public static <T> Consumer<Throwable> m9609b(Observer<T> assVar) {
        return new C4477i(assVar);
    }

    /* renamed from: c */
    public static <T> Action m9607c(Observer<T> assVar) {
        return new C4476h(assVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$d */
    /* loaded from: classes3.dex */
    public static final class C4472d<U, R, T> implements Function<U, R> {

        /* renamed from: a */
        private final BiFunction<? super T, ? super U, ? extends R> f19180a;

        /* renamed from: b */
        private final T f19181b;

        C4472d(BiFunction<? super T, ? super U, ? extends R> auiVar, T t) {
            this.f19180a = auiVar;
            this.f19181b = t;
        }

        @Override // p110z1.Function
        public R apply(U u) throws Exception {
            return (R) this.f19180a.apply((T) this.f19181b, u);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$e */
    /* loaded from: classes3.dex */
    public static final class C4473e<T, R, U> implements Function<T, ObservableSource<R>> {

        /* renamed from: a */
        private final BiFunction<? super T, ? super U, ? extends R> f19182a;

        /* renamed from: b */
        private final Function<? super T, ? extends ObservableSource<? extends U>> f19183b;

        C4473e(BiFunction<? super T, ? super U, ? extends R> auiVar, Function<? super T, ? extends ObservableSource<? extends U>> aunVar) {
            this.f19182a = auiVar;
            this.f19183b = aunVar;
        }

        /* renamed from: a */
        public ObservableSource<R> apply(T t) throws Exception {
            return new ObservableMap((ObservableSource) ObjectHelper.m9873a(this.f19183b.apply(t), "The mapper returned a null ObservableSource"), new C4472d(this.f19182a, t));
        }
    }

    /* renamed from: a */
    public static <T, U, R> Function<T, ObservableSource<R>> m9610a(Function<? super T, ? extends ObservableSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        return new C4473e(auiVar, aunVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$c */
    /* loaded from: classes3.dex */
    public static final class C4471c<T, U> implements Function<T, ObservableSource<U>> {

        /* renamed from: a */
        private final Function<? super T, ? extends Iterable<? extends U>> f19179a;

        C4471c(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
            this.f19179a = aunVar;
        }

        /* renamed from: a */
        public ObservableSource<U> apply(T t) throws Exception {
            return new ObservableFromIterable((Iterable) ObjectHelper.m9873a(this.f19179a.apply(t), "The mapper returned a null Iterable"));
        }
    }

    /* renamed from: b */
    public static <T, U> Function<T, ObservableSource<U>> m9608b(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        return new C4471c(aunVar);
    }

    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$g */
    /* loaded from: classes3.dex */
    enum EnumC4475g implements Function<Object, Object> {
        INSTANCE;

        @Override // p110z1.Function
        public Object apply(Object obj) throws Exception {
            return 0;
        }
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableObservable<T>> m9619a(Observable<T> aslVar) {
        return new CallableC4479k(aslVar);
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableObservable<T>> m9618a(Observable<T> aslVar, int i) {
        return new CallableC4469a(aslVar, i);
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableObservable<T>> m9617a(Observable<T> aslVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        return new CallableC4470b(aslVar, i, j, timeUnit, astVar);
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableObservable<T>> m9616a(Observable<T> aslVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        return new CallableC4483o(aslVar, j, timeUnit, astVar);
    }

    /* renamed from: a */
    public static <T, R> Function<Observable<T>, ObservableSource<R>> m9611a(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, Scheduler astVar) {
        return new C4480l(aunVar, astVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$p */
    /* loaded from: classes3.dex */
    public static final class C4484p<T, R> implements Function<List<ObservableSource<? extends T>>, ObservableSource<? extends R>> {

        /* renamed from: a */
        private final Function<? super Object[], ? extends R> f19197a;

        C4484p(Function<? super Object[], ? extends R> aunVar) {
            this.f19197a = aunVar;
        }

        /* renamed from: a */
        public ObservableSource<? extends R> apply(List<ObservableSource<? extends T>> list) {
            return Observable.m10562a((Iterable) list, (Function) this.f19197a, false, Observable.m10338d());
        }
    }

    /* renamed from: c */
    public static <T, R> Function<List<ObservableSource<? extends T>>, ObservableSource<? extends R>> m9606c(Function<? super Object[], ? extends R> aunVar) {
        return new C4484p(aunVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$k */
    /* loaded from: classes3.dex */
    public static final class CallableC4479k<T> implements Callable<ConnectableObservable<T>> {

        /* renamed from: a */
        private final Observable<T> f19188a;

        CallableC4479k(Observable<T> aslVar) {
            this.f19188a = aslVar;
        }

        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.f19188a.m10628E();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$a */
    /* loaded from: classes3.dex */
    public static final class CallableC4469a<T> implements Callable<ConnectableObservable<T>> {

        /* renamed from: a */
        private final Observable<T> f19172a;

        /* renamed from: b */
        private final int f19173b;

        CallableC4469a(Observable<T> aslVar, int i) {
            this.f19172a = aslVar;
            this.f19173b = i;
        }

        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.f19172a.m10337d(this.f19173b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$b */
    /* loaded from: classes3.dex */
    public static final class CallableC4470b<T> implements Callable<ConnectableObservable<T>> {

        /* renamed from: a */
        private final Observable<T> f19174a;

        /* renamed from: b */
        private final int f19175b;

        /* renamed from: c */
        private final long f19176c;

        /* renamed from: d */
        private final TimeUnit f19177d;

        /* renamed from: e */
        private final Scheduler f19178e;

        CallableC4470b(Observable<T> aslVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.f19174a = aslVar;
            this.f19175b = i;
            this.f19176c = j;
            this.f19177d = timeUnit;
            this.f19178e = astVar;
        }

        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.f19174a.m10600a(this.f19175b, this.f19176c, this.f19177d, this.f19178e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$o */
    /* loaded from: classes3.dex */
    public static final class CallableC4483o<T> implements Callable<ConnectableObservable<T>> {

        /* renamed from: a */
        private final Observable<T> f19193a;

        /* renamed from: b */
        private final long f19194b;

        /* renamed from: c */
        private final TimeUnit f19195c;

        /* renamed from: d */
        private final Scheduler f19196d;

        CallableC4483o(Observable<T> aslVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.f19193a = aslVar;
            this.f19194b = j;
            this.f19195c = timeUnit;
            this.f19196d = astVar;
        }

        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.f19193a.m10266g(this.f19194b, this.f19195c, this.f19196d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableInternalHelper.java */
    /* renamed from: z1.bkt$l */
    /* loaded from: classes3.dex */
    public static final class C4480l<T, R> implements Function<Observable<T>, ObservableSource<R>> {

        /* renamed from: a */
        private final Function<? super Observable<T>, ? extends ObservableSource<R>> f19189a;

        /* renamed from: b */
        private final Scheduler f19190b;

        C4480l(Function<? super Observable<T>, ? extends ObservableSource<R>> aunVar, Scheduler astVar) {
            this.f19189a = aunVar;
            this.f19190b = astVar;
        }

        /* renamed from: a */
        public ObservableSource<R> apply(Observable<T> aslVar) throws Exception {
            return Observable.m10239i((ObservableSource) ObjectHelper.m9873a(this.f19189a.apply(aslVar), "The selector returned a null ObservableSource")).m10491a(this.f19190b);
        }
    }
}
