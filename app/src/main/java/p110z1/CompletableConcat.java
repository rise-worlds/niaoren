package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.axb */
/* loaded from: classes3.dex */
public final class CompletableConcat extends Completable {

    /* renamed from: a */
    final Publisher<? extends CompletableSource> f17691a;

    /* renamed from: b */
    final int f17692b;

    public CompletableConcat(Publisher<? extends CompletableSource> dbwVar, int i) {
        this.f17691a = dbwVar;
        this.f17692b = i;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        this.f17691a.subscribe(new C3944a(arpVar, this.f17692b));
    }

    /* compiled from: CompletableConcat.java */
    /* renamed from: z1.axb$a */
    /* loaded from: classes3.dex */
    static final class C3944a extends AtomicInteger implements FlowableSubscriber<CompletableSource>, Disposable {
        private static final long serialVersionUID = 9032184911934499404L;
        volatile boolean active;
        int consumed;
        volatile boolean done;
        final CompletableObserver downstream;
        final int limit;
        final int prefetch;
        SimpleQueue<CompletableSource> queue;
        int sourceFused;
        dby upstream;
        final C3945a inner = new C3945a(this);
        final AtomicBoolean once = new AtomicBoolean();

        C3944a(CompletableObserver arpVar, int i) {
            this.downstream = arpVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                int i = this.prefetch;
                long j = i == Integer.MAX_VALUE ? cjm.f20759b : i;
                if (dbyVar instanceof QueueSubscription) {
                    QueueSubscription avtVar = (QueueSubscription) dbyVar;
                    int requestFusion = avtVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceFused = requestFusion;
                        this.queue = avtVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceFused = requestFusion;
                        this.queue = avtVar;
                        this.downstream.onSubscribe(this);
                        dbyVar.request(j);
                        return;
                    }
                }
                int i2 = this.prefetch;
                if (i2 == Integer.MAX_VALUE) {
                    this.queue = new SpscLinkedArrayQueue(Flowable.m11274a());
                } else {
                    this.queue = new SpscArrayQueue(i2);
                }
                this.downstream.onSubscribe(this);
                dbyVar.request(j);
            }
        }

        public void onNext(CompletableSource arsVar) {
            if (this.sourceFused != 0 || this.queue.offer(arsVar)) {
                drain();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.inner);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.cancel();
            DisposableHelper.dispose(this.inner);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.inner.get());
        }

        void drain() {
            if (getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            CompletableSource poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (!z || !z2) {
                                if (!z2) {
                                    this.active = true;
                                    poll.mo11309a(this.inner);
                                    request();
                                }
                            } else if (this.once.compareAndSet(false, true)) {
                                this.downstream.onComplete();
                                return;
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            innerError(th);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        void request() {
            if (this.sourceFused != 1) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.upstream.request(i);
                    return;
                }
                this.consumed = i;
            }
        }

        void innerError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.upstream.cancel();
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CompletableConcat.java */
        /* renamed from: z1.axb$a$a */
        /* loaded from: classes3.dex */
        public static final class C3945a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -5454794857847146511L;
            final C3944a parent;

            C3945a(C3944a aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.replace(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                this.parent.innerComplete();
            }
        }
    }
}
