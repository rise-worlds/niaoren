package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bar */
/* loaded from: classes3.dex */
public final class FlowableFlatMapCompletableCompletable<T> extends Completable implements FuseToFlowable<T> {

    /* renamed from: a */
    final Flowable<T> f18089a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f18090b;

    /* renamed from: c */
    final int f18091c;

    /* renamed from: d */
    final boolean f18092d;

    public FlowableFlatMapCompletableCompletable(Flowable<T> arvVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z, int i) {
        this.f18089a = arvVar;
        this.f18090b = aunVar;
        this.f18092d = z;
        this.f18091c = i;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f18089a.m11187a((FlowableSubscriber) new C4068a(arpVar, this.f18090b, this.f18092d, this.f18091c));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<T> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableFlatMapCompletable(this.f18089a, this.f18090b, this.f18092d, this.f18091c));
    }

    /* compiled from: FlowableFlatMapCompletableCompletable.java */
    /* renamed from: z1.bar$a */
    /* loaded from: classes3.dex */
    static final class C4068a<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        private static final long serialVersionUID = 8443155186132538303L;
        final boolean delayErrors;
        volatile boolean disposed;
        final CompletableObserver downstream;
        final Function<? super T, ? extends CompletableSource> mapper;
        final int maxConcurrency;
        dby upstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final CompositeDisposable set = new CompositeDisposable();

        C4068a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar, boolean z, int i) {
            this.downstream = arpVar;
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
                C4069a aVar = new C4069a();
                if (!this.disposed && this.set.mo9939a(aVar)) {
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
                dispose();
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

        @Override // p110z1.Disposable
        public void dispose() {
            this.disposed = true;
            this.upstream.cancel();
            this.set.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.set.isDisposed();
        }

        void innerComplete(C4068a<T>.C4069a aVar) {
            this.set.mo9936c(aVar);
            onComplete();
        }

        void innerError(C4068a<T>.C4069a aVar, Throwable th) {
            this.set.mo9936c(aVar);
            onError(th);
        }

        /* compiled from: FlowableFlatMapCompletableCompletable.java */
        /* renamed from: z1.bar$a$a */
        /* loaded from: classes3.dex */
        final class C4069a extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            C4069a() {
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                C4068a.this.innerComplete(this);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                C4068a.this.innerError(this, th);
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
