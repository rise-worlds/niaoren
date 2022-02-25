package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.baq */
/* loaded from: classes3.dex */
public final class FlowableFlatMapCompletable<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super T, ? extends CompletableSource> f18086c;

    /* renamed from: d */
    final int f18087d;

    /* renamed from: e */
    final boolean f18088e;

    public FlowableFlatMapCompletable(Flowable<T> arvVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z, int i) {
        super(arvVar);
        this.f18086c = aunVar;
        this.f18088e = z;
        this.f18087d = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4066a(dbxVar, this.f18086c, this.f18088e, this.f18087d));
    }

    /* compiled from: FlowableFlatMapCompletable.java */
    /* renamed from: z1.baq$a */
    /* loaded from: classes3.dex */
    static final class C4066a<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 8443155186132538303L;
        volatile boolean cancelled;
        final boolean delayErrors;
        final Subscriber<? super T> downstream;
        final Function<? super T, ? extends CompletableSource> mapper;
        final int maxConcurrency;
        dby upstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final CompositeDisposable set = new CompositeDisposable();

        @Override // p110z1.SimpleQueue
        public void clear() {
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return true;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            return null;
        }

        @Override // p110z1.dby
        public void request(long j) {
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return i & 2;
        }

        C4066a(Subscriber<? super T> dbxVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z, int i) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            lazySet(1);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    dbyVar.request(cjm.f20759b);
                } else {
                    dbyVar.request(i);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            try {
                CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                getAndIncrement();
                C4067a aVar = new C4067a();
                if (!this.cancelled && this.set.mo9939a(aVar)) {
                    arsVar.mo11309a(aVar);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (!this.delayErrors) {
                cancel();
                if (getAndSet(0) > 0) {
                    this.downstream.onError(this.errors.terminate());
                }
            } else if (decrementAndGet() == 0) {
                this.downstream.onError(this.errors.terminate());
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1L);
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable terminate = this.errors.terminate();
                if (terminate != null) {
                    this.downstream.onError(terminate);
                } else {
                    this.downstream.onComplete();
                }
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1L);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.set.dispose();
        }

        void innerComplete(C4066a<T>.C4067a aVar) {
            this.set.mo9936c(aVar);
            onComplete();
        }

        void innerError(C4066a<T>.C4067a aVar, Throwable th) {
            this.set.mo9936c(aVar);
            onError(th);
        }

        /* compiled from: FlowableFlatMapCompletable.java */
        /* renamed from: z1.baq$a$a */
        /* loaded from: classes3.dex */
        final class C4067a extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            C4067a() {
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                C4066a.this.innerComplete(this);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                C4066a.this.innerError(this, th);
            }

            @Override // p110z1.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }
        }
    }
}
