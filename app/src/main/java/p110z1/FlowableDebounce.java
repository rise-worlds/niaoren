package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.azw */
/* loaded from: classes3.dex */
public final class FlowableDebounce<T, U> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<U>> f17983c;

    public FlowableDebounce(Flowable<T> arvVar, Function<? super T, ? extends Publisher<U>> aunVar) {
        super(arvVar);
        this.f17983c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4035a(new SerializedSubscriber(dbxVar), this.f17983c));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableDebounce.java */
    /* renamed from: z1.azw$a */
    /* loaded from: classes3.dex */
    public static final class C4035a<T, U> extends AtomicLong implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 6725975399620862591L;
        final Function<? super T, ? extends Publisher<U>> debounceSelector;
        final AtomicReference<Disposable> debouncer = new AtomicReference<>();
        boolean done;
        final Subscriber<? super T> downstream;
        volatile long index;
        dby upstream;

        C4035a(Subscriber<? super T> dbxVar, Function<? super T, ? extends Publisher<U>> aunVar) {
            this.downstream = dbxVar;
            this.debounceSelector = aunVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                long j = this.index + 1;
                this.index = j;
                Disposable atrVar = this.debouncer.get();
                if (atrVar != null) {
                    atrVar.dispose();
                }
                try {
                    Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.debounceSelector.apply(t), "The publisher supplied is null");
                    C4036a aVar = new C4036a(this, j, t);
                    if (this.debouncer.compareAndSet(atrVar, aVar)) {
                        dbwVar.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    this.downstream.onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.debouncer);
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                Disposable atrVar = this.debouncer.get();
                if (!DisposableHelper.isDisposed(atrVar)) {
                    ((C4036a) atrVar).m9796a();
                    DisposableHelper.dispose(this.debouncer);
                    this.downstream.onComplete();
                }
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
            DisposableHelper.dispose(this.debouncer);
        }

        void emit(long j, T t) {
            if (j != this.index) {
                return;
            }
            if (get() != 0) {
                this.downstream.onNext(t);
                BackpressureHelper.m9446c(this, 1L);
                return;
            }
            cancel();
            this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
        }

        /* compiled from: FlowableDebounce.java */
        /* renamed from: z1.azw$a$a */
        /* loaded from: classes3.dex */
        static final class C4036a<T, U> extends DisposableSubscriber<U> {

            /* renamed from: a */
            final C4035a<T, U> f17984a;

            /* renamed from: b */
            final long f17985b;

            /* renamed from: c */
            final T f17986c;

            /* renamed from: d */
            boolean f17987d;

            /* renamed from: e */
            final AtomicBoolean f17988e = new AtomicBoolean();

            C4036a(C4035a<T, U> aVar, long j, T t) {
                this.f17984a = aVar;
                this.f17985b = j;
                this.f17986c = t;
            }

            @Override // p110z1.Subscriber
            public void onNext(U u) {
                if (!this.f17987d) {
                    this.f17987d = true;
                    m8927d();
                    m9796a();
                }
            }

            /* renamed from: a */
            void m9796a() {
                if (this.f17988e.compareAndSet(false, true)) {
                    this.f17984a.emit(this.f17985b, this.f17986c);
                }
            }

            @Override // p110z1.Subscriber
            public void onError(Throwable th) {
                if (this.f17987d) {
                    RxJavaPlugins.m9212a(th);
                    return;
                }
                this.f17987d = true;
                this.f17984a.onError(th);
            }

            @Override // p110z1.Subscriber
            public void onComplete() {
                if (!this.f17987d) {
                    this.f17987d = true;
                    m9796a();
                }
            }
        }
    }
}
