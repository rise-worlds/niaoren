package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bho */
/* loaded from: classes3.dex */
public final class FlowableConcatMapCompletable<T> extends Completable {

    /* renamed from: a */
    final Flowable<T> f18739a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f18740b;

    /* renamed from: c */
    final ErrorMode f18741c;

    /* renamed from: d */
    final int f18742d;

    public FlowableConcatMapCompletable(Flowable<T> arvVar, Function<? super T, ? extends CompletableSource> aunVar, ErrorMode bsuVar, int i) {
        this.f18739a = arvVar;
        this.f18740b = aunVar;
        this.f18741c = bsuVar;
        this.f18742d = i;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f18739a.m11187a((FlowableSubscriber) new C4349a(arpVar, this.f18740b, this.f18741c, this.f18742d));
    }

    /* compiled from: FlowableConcatMapCompletable.java */
    /* renamed from: z1.bho$a */
    /* loaded from: classes3.dex */
    static final class C4349a<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        private static final long serialVersionUID = 3610901111000061034L;
        volatile boolean active;
        int consumed;
        volatile boolean disposed;
        volatile boolean done;
        final CompletableObserver downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final C4350a inner = new C4350a(this);
        final Function<? super T, ? extends CompletableSource> mapper;
        final int prefetch;
        final SimplePlainQueue<T> queue;
        dby upstream;

        C4349a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar, ErrorMode bsuVar, int i) {
            this.downstream = arpVar;
            this.mapper = aunVar;
            this.errorMode = bsuVar;
            this.prefetch = i;
            this.queue = new SpscArrayQueue(i);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(this.prefetch);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (this.queue.offer(t)) {
                drain();
                return;
            }
            this.upstream.cancel();
            onError(new MissingBackpressureException("Queue full?!"));
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (this.errorMode == ErrorMode.IMMEDIATE) {
                this.inner.dispose();
                Throwable terminate = this.errors.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.downstream.onError(terminate);
                }
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            } else {
                this.done = true;
                drain();
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.disposed = true;
            this.upstream.cancel();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        void innerError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
            } else if (this.errorMode == ErrorMode.IMMEDIATE) {
                this.upstream.cancel();
                Throwable terminate = this.errors.terminate();
                if (terminate != ExceptionHelper.f20064a) {
                    this.downstream.onError(terminate);
                }
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            } else {
                this.active = false;
                drain();
            }
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                while (!this.disposed) {
                    if (!this.active) {
                        if (this.errorMode != ErrorMode.BOUNDARY || this.errors.get() == null) {
                            boolean z = this.done;
                            T poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                Throwable terminate = this.errors.terminate();
                                if (terminate != null) {
                                    this.downstream.onError(terminate);
                                    return;
                                } else {
                                    this.downstream.onComplete();
                                    return;
                                }
                            } else if (!z2) {
                                int i = this.prefetch;
                                int i2 = i - (i >> 1);
                                int i3 = this.consumed + 1;
                                if (i3 == i2) {
                                    this.consumed = 0;
                                    this.upstream.request(i2);
                                } else {
                                    this.consumed = i3;
                                }
                                try {
                                    CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null CompletableSource");
                                    this.active = true;
                                    arsVar.mo11309a(this.inner);
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    this.queue.clear();
                                    this.upstream.cancel();
                                    this.errors.addThrowable(th);
                                    this.downstream.onError(this.errors.terminate());
                                    return;
                                }
                            }
                        } else {
                            this.queue.clear();
                            this.downstream.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableConcatMapCompletable.java */
        /* renamed from: z1.bho$a$a */
        /* loaded from: classes3.dex */
        public static final class C4350a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = 5638352172918776687L;
            final C4349a<?> parent;

            C4350a(C4349a<?> aVar) {
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

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
