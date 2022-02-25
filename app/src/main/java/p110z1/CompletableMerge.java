package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.axy */
/* loaded from: classes3.dex */
public final class CompletableMerge extends Completable {

    /* renamed from: a */
    final Publisher<? extends CompletableSource> f17739a;

    /* renamed from: b */
    final int f17740b;

    /* renamed from: c */
    final boolean f17741c;

    public CompletableMerge(Publisher<? extends CompletableSource> dbwVar, int i, boolean z) {
        this.f17739a = dbwVar;
        this.f17740b = i;
        this.f17741c = z;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        this.f17739a.subscribe(new C3958a(arpVar, this.f17740b, this.f17741c));
    }

    /* compiled from: CompletableMerge.java */
    /* renamed from: z1.axy$a */
    /* loaded from: classes3.dex */
    static final class C3958a extends AtomicInteger implements FlowableSubscriber<CompletableSource>, Disposable {
        private static final long serialVersionUID = -2108443387387077490L;
        final boolean delayErrors;
        final CompletableObserver downstream;
        final int maxConcurrency;
        dby upstream;
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicThrowable error = new AtomicThrowable();

        C3958a(CompletableObserver arpVar, int i, boolean z) {
            this.downstream = arpVar;
            this.maxConcurrency = i;
            this.delayErrors = z;
            lazySet(1);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.cancel();
            this.set.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.set.isDisposed();
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

        public void onNext(CompletableSource arsVar) {
            getAndIncrement();
            C3959a aVar = new C3959a();
            this.set.mo9939a(aVar);
            arsVar.mo11309a(aVar);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.delayErrors) {
                this.set.dispose();
                if (!this.error.addThrowable(th)) {
                    RxJavaPlugins.m9212a(th);
                } else if (getAndSet(0) > 0) {
                    this.downstream.onError(this.error.terminate());
                }
            } else if (!this.error.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (decrementAndGet() == 0) {
                this.downstream.onError(this.error.terminate());
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (decrementAndGet() != 0) {
                return;
            }
            if (this.error.get() != null) {
                this.downstream.onError(this.error.terminate());
            } else {
                this.downstream.onComplete();
            }
        }

        void innerError(C3959a aVar, Throwable th) {
            this.set.mo9936c(aVar);
            if (!this.delayErrors) {
                this.upstream.cancel();
                this.set.dispose();
                if (!this.error.addThrowable(th)) {
                    RxJavaPlugins.m9212a(th);
                } else if (getAndSet(0) > 0) {
                    this.downstream.onError(this.error.terminate());
                }
            } else if (!this.error.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (decrementAndGet() == 0) {
                this.downstream.onError(this.error.terminate());
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1L);
            }
        }

        void innerComplete(C3959a aVar) {
            this.set.mo9936c(aVar);
            if (decrementAndGet() == 0) {
                Throwable th = this.error.get();
                if (th != null) {
                    this.downstream.onError(th);
                } else {
                    this.downstream.onComplete();
                }
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1L);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CompletableMerge.java */
        /* renamed from: z1.axy$a$a */
        /* loaded from: classes3.dex */
        public final class C3959a extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
            private static final long serialVersionUID = 251330541679988317L;

            C3959a() {
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                C3958a.this.innerError(this, th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                C3958a.this.innerComplete(this);
            }

            @Override // p110z1.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // p110z1.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
