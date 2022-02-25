package p110z1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.bbi */
/* loaded from: classes3.dex */
public final class FlowableInternalHelper {
    private FlowableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$k */
    /* loaded from: classes3.dex */
    public static final class C4105k<T, S> implements BiFunction<S, Emitter<T>, S> {

        /* renamed from: a */
        final Consumer<Emitter<T>> f18155a;

        C4105k(Consumer<Emitter<T>> aumVar) {
            this.f18155a = aumVar;
        }

        /* renamed from: a */
        public S apply(S s, Emitter<T> aruVar) throws Exception {
            this.f18155a.accept(aruVar);
            return s;
        }
    }

    /* renamed from: a */
    public static <T, S> BiFunction<S, Emitter<T>, S> m9780a(Consumer<Emitter<T>> aumVar) {
        return new C4105k(aumVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$j */
    /* loaded from: classes3.dex */
    public static final class C4104j<T, S> implements BiFunction<S, Emitter<T>, S> {

        /* renamed from: a */
        final BiConsumer<S, Emitter<T>> f18154a;

        C4104j(BiConsumer<S, Emitter<T>> auhVar) {
            this.f18154a = auhVar;
        }

        /* renamed from: a */
        public S apply(S s, Emitter<T> aruVar) throws Exception {
            this.f18154a.mo9895a(s, aruVar);
            return s;
        }
    }

    /* renamed from: a */
    public static <T, S> BiFunction<S, Emitter<T>, S> m9781a(BiConsumer<S, Emitter<T>> auhVar) {
        return new C4104j(auhVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$f */
    /* loaded from: classes3.dex */
    public static final class C4100f<T, U> implements Function<T, Publisher<T>> {

        /* renamed from: a */
        final Function<? super T, ? extends Publisher<U>> f18150a;

        C4100f(Function<? super T, ? extends Publisher<U>> aunVar) {
            this.f18150a = aunVar;
        }

        /* renamed from: a */
        public Publisher<T> apply(T t) throws Exception {
            return new FlowableTakePublisher((Publisher) ObjectHelper.m9873a(this.f18150a.apply(t), "The itemDelay returned a null Publisher"), 1L).m10817v(Functions.m9911b(t)).m10913g((Flowable<R>) t);
        }
    }

    /* renamed from: a */
    public static <T, U> Function<T, Publisher<T>> m9779a(Function<? super T, ? extends Publisher<U>> aunVar) {
        return new C4100f(aunVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$n */
    /* loaded from: classes3.dex */
    public static final class C4108n<T> implements Consumer<T> {

        /* renamed from: a */
        final Subscriber<T> f18158a;

        C4108n(Subscriber<T> dbxVar) {
            this.f18158a = dbxVar;
        }

        @Override // p110z1.Consumer
        public void accept(T t) throws Exception {
            this.f18158a.onNext(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$m */
    /* loaded from: classes3.dex */
    public static final class C4107m<T> implements Consumer<Throwable> {

        /* renamed from: a */
        final Subscriber<T> f18157a;

        C4107m(Subscriber<T> dbxVar) {
            this.f18157a = dbxVar;
        }

        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.f18157a.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$l */
    /* loaded from: classes3.dex */
    public static final class C4106l<T> implements Action {

        /* renamed from: a */
        final Subscriber<T> f18156a;

        C4106l(Subscriber<T> dbxVar) {
            this.f18156a = dbxVar;
        }

        @Override // p110z1.Action
        /* renamed from: a */
        public void mo9442a() throws Exception {
            this.f18156a.onComplete();
        }
    }

    /* renamed from: a */
    public static <T> Consumer<T> m9776a(Subscriber<T> dbxVar) {
        return new C4108n(dbxVar);
    }

    /* renamed from: b */
    public static <T> Consumer<Throwable> m9774b(Subscriber<T> dbxVar) {
        return new C4107m(dbxVar);
    }

    /* renamed from: c */
    public static <T> Action m9772c(Subscriber<T> dbxVar) {
        return new C4106l(dbxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$d */
    /* loaded from: classes3.dex */
    public static final class C4098d<U, R, T> implements Function<U, R> {

        /* renamed from: a */
        private final BiFunction<? super T, ? super U, ? extends R> f18146a;

        /* renamed from: b */
        private final T f18147b;

        C4098d(BiFunction<? super T, ? super U, ? extends R> auiVar, T t) {
            this.f18146a = auiVar;
            this.f18147b = t;
        }

        @Override // p110z1.Function
        public R apply(U u) throws Exception {
            return (R) this.f18146a.apply((T) this.f18147b, u);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$e */
    /* loaded from: classes3.dex */
    public static final class C4099e<T, R, U> implements Function<T, Publisher<R>> {

        /* renamed from: a */
        private final BiFunction<? super T, ? super U, ? extends R> f18148a;

        /* renamed from: b */
        private final Function<? super T, ? extends Publisher<? extends U>> f18149b;

        C4099e(BiFunction<? super T, ? super U, ? extends R> auiVar, Function<? super T, ? extends Publisher<? extends U>> aunVar) {
            this.f18148a = auiVar;
            this.f18149b = aunVar;
        }

        /* renamed from: a */
        public Publisher<R> apply(T t) throws Exception {
            return new FlowableMapPublisher((Publisher) ObjectHelper.m9873a(this.f18149b.apply(t), "The mapper returned a null Publisher"), new C4098d(this.f18148a, t));
        }
    }

    /* renamed from: a */
    public static <T, U, R> Function<T, Publisher<R>> m9777a(Function<? super T, ? extends Publisher<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        return new C4099e(auiVar, aunVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$c */
    /* loaded from: classes3.dex */
    public static final class C4097c<T, U> implements Function<T, Publisher<U>> {

        /* renamed from: a */
        private final Function<? super T, ? extends Iterable<? extends U>> f18145a;

        C4097c(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
            this.f18145a = aunVar;
        }

        /* renamed from: a */
        public Publisher<U> apply(T t) throws Exception {
            return new FlowableFromIterable((Iterable) ObjectHelper.m9873a(this.f18145a.apply(t), "The mapper returned a null Iterable"));
        }
    }

    /* renamed from: b */
    public static <T, U> Function<T, Publisher<U>> m9775b(Function<? super T, ? extends Iterable<? extends U>> aunVar) {
        return new C4097c(aunVar);
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableFlowable<T>> m9785a(Flowable<T> arvVar) {
        return new CallableC4101g(arvVar);
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableFlowable<T>> m9784a(Flowable<T> arvVar, int i) {
        return new CallableC4095a(arvVar, i);
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableFlowable<T>> m9783a(Flowable<T> arvVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
        return new CallableC4096b(arvVar, i, j, timeUnit, astVar);
    }

    /* renamed from: a */
    public static <T> Callable<ConnectableFlowable<T>> m9782a(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        return new CallableC4109o(arvVar, j, timeUnit, astVar);
    }

    /* renamed from: a */
    public static <T, R> Function<Flowable<T>, Publisher<R>> m9778a(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, Scheduler astVar) {
        return new C4102h(aunVar, astVar);
    }

    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$i */
    /* loaded from: classes3.dex */
    public enum EnumC4103i implements Consumer<dby> {
        INSTANCE;

        public void accept(dby dbyVar) throws Exception {
            dbyVar.request(cjm.f20759b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$p */
    /* loaded from: classes3.dex */
    public static final class C4110p<T, R> implements Function<List<Publisher<? extends T>>, Publisher<? extends R>> {

        /* renamed from: a */
        private final Function<? super Object[], ? extends R> f18163a;

        C4110p(Function<? super Object[], ? extends R> aunVar) {
            this.f18163a = aunVar;
        }

        /* renamed from: a */
        public Publisher<? extends R> apply(List<Publisher<? extends T>> list) {
            return Flowable.m11224a((Iterable) list, (Function) this.f18163a, false, Flowable.m11274a());
        }
    }

    /* renamed from: c */
    public static <T, R> Function<List<Publisher<? extends T>>, Publisher<? extends R>> m9773c(Function<? super Object[], ? extends R> aunVar) {
        return new C4110p(aunVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$g */
    /* loaded from: classes3.dex */
    public static final class CallableC4101g<T> implements Callable<ConnectableFlowable<T>> {

        /* renamed from: a */
        private final Flowable<T> f18151a;

        CallableC4101g(Flowable<T> arvVar) {
            this.f18151a = arvVar;
        }

        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.f18151a.m11297F();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$a */
    /* loaded from: classes3.dex */
    public static final class CallableC4095a<T> implements Callable<ConnectableFlowable<T>> {

        /* renamed from: a */
        private final Flowable<T> f18138a;

        /* renamed from: b */
        private final int f18139b;

        CallableC4095a(Flowable<T> arvVar, int i) {
            this.f18138a = arvVar;
            this.f18139b = i;
        }

        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.f18138a.m10903h(this.f18139b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$b */
    /* loaded from: classes3.dex */
    public static final class CallableC4096b<T> implements Callable<ConnectableFlowable<T>> {

        /* renamed from: a */
        private final Flowable<T> f18140a;

        /* renamed from: b */
        private final int f18141b;

        /* renamed from: c */
        private final long f18142c;

        /* renamed from: d */
        private final TimeUnit f18143d;

        /* renamed from: e */
        private final Scheduler f18144e;

        CallableC4096b(Flowable<T> arvVar, int i, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.f18140a = arvVar;
            this.f18141b = i;
            this.f18142c = j;
            this.f18143d = timeUnit;
            this.f18144e = astVar;
        }

        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.f18140a.m11268a(this.f18141b, this.f18142c, this.f18143d, this.f18144e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$o */
    /* loaded from: classes3.dex */
    public static final class CallableC4109o<T> implements Callable<ConnectableFlowable<T>> {

        /* renamed from: a */
        private final Flowable<T> f18159a;

        /* renamed from: b */
        private final long f18160b;

        /* renamed from: c */
        private final TimeUnit f18161c;

        /* renamed from: d */
        private final Scheduler f18162d;

        CallableC4109o(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.f18159a = arvVar;
            this.f18160b = j;
            this.f18161c = timeUnit;
            this.f18162d = astVar;
        }

        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.f18159a.m10915g(this.f18160b, this.f18161c, this.f18162d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableInternalHelper.java */
    /* renamed from: z1.bbi$h */
    /* loaded from: classes3.dex */
    public static final class C4102h<T, R> implements Function<Flowable<T>, Publisher<R>> {

        /* renamed from: a */
        private final Function<? super Flowable<T>, ? extends Publisher<R>> f18152a;

        /* renamed from: b */
        private final Scheduler f18153b;

        C4102h(Function<? super Flowable<T>, ? extends Publisher<R>> aunVar, Scheduler astVar) {
            this.f18152a = aunVar;
            this.f18153b = astVar;
        }

        /* renamed from: a */
        public Publisher<R> apply(Flowable<T> arvVar) throws Exception {
            return Flowable.m10964d((Publisher) ObjectHelper.m9873a(this.f18152a.apply(arvVar), "The selector returned a null Publisher")).m11184a(this.f18153b);
        }
    }
}
