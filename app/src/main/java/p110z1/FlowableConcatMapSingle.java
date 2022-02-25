package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhq */
/* loaded from: classes3.dex */
public final class FlowableConcatMapSingle<T, R> extends Flowable<R> {

    /* renamed from: b */
    final Flowable<T> f18747b;

    /* renamed from: c */
    final Function<? super T, ? extends SingleSource<? extends R>> f18748c;

    /* renamed from: d */
    final ErrorMode f18749d;

    /* renamed from: e */
    final int f18750e;

    public FlowableConcatMapSingle(Flowable<T> arvVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, ErrorMode bsuVar, int i) {
        this.f18747b = arvVar;
        this.f18748c = aunVar;
        this.f18749d = bsuVar;
        this.f18750e = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f18747b.m11187a((FlowableSubscriber) new C4353a(dbxVar, this.f18748c, this.f18750e, this.f18749d));
    }

    /* compiled from: FlowableConcatMapSingle.java */
    /* renamed from: z1.bhq$a */
    /* loaded from: classes3.dex */
    static final class C4353a<T, R> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        long emitted;
        final ErrorMode errorMode;
        R item;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        final int prefetch;
        final SimplePlainQueue<T> queue;
        volatile int state;
        dby upstream;
        final AtomicLong requested = new AtomicLong();
        final AtomicThrowable errors = new AtomicThrowable();
        final C4354a<R> inner = new C4354a<>(this);

        C4353a(Subscriber<? super R> dbxVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, int i, ErrorMode bsuVar) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.prefetch = i;
            this.errorMode = bsuVar;
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
            if (!this.queue.offer(t)) {
                this.upstream.cancel();
                onError(new MissingBackpressureException("queue full?!"));
                return;
            }
            drain();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.inner.dispose();
                }
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.dby
        public void request(long j) {
            BackpressureHelper.m9449a(this.requested, j);
            drain();
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
                this.item = null;
            }
        }

        void innerSuccess(R r) {
            this.item = r;
            this.state = 2;
            drain();
        }

        void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                this.state = 0;
                drain();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> dbxVar = this.downstream;
                ErrorMode bsuVar = this.errorMode;
                SimplePlainQueue<T> avvVar = this.queue;
                AtomicThrowable bsnVar = this.errors;
                AtomicLong atomicLong = this.requested;
                int i = this.prefetch;
                int i2 = i - (i >> 1);
                int i3 = 1;
                while (true) {
                    if (this.cancelled) {
                        avvVar.clear();
                        this.item = null;
                    } else {
                        int i4 = this.state;
                        if (bsnVar.get() == null || !(bsuVar == ErrorMode.IMMEDIATE || (bsuVar == ErrorMode.BOUNDARY && i4 == 0))) {
                            if (i4 == 0) {
                                boolean z = this.done;
                                T poll = avvVar.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    Throwable terminate = bsnVar.terminate();
                                    if (terminate == null) {
                                        dbxVar.onComplete();
                                        return;
                                    } else {
                                        dbxVar.onError(terminate);
                                        return;
                                    }
                                } else if (!z2) {
                                    int i5 = this.consumed + 1;
                                    if (i5 == i2) {
                                        this.consumed = 0;
                                        this.upstream.request(i2);
                                    } else {
                                        this.consumed = i5;
                                    }
                                    try {
                                        SingleSource ataVar = (SingleSource) ObjectHelper.m9873a(this.mapper.apply(poll), "The mapper returned a null SingleSource");
                                        this.state = 1;
                                        ataVar.mo10018a(this.inner);
                                    } catch (Throwable th) {
                                        Exceptions.m9983b(th);
                                        this.upstream.cancel();
                                        avvVar.clear();
                                        bsnVar.addThrowable(th);
                                        dbxVar.onError(bsnVar.terminate());
                                        return;
                                    }
                                }
                            } else if (i4 == 2) {
                                long j = this.emitted;
                                if (j != atomicLong.get()) {
                                    this.item = null;
                                    dbxVar.onNext((R) this.item);
                                    this.emitted = j + 1;
                                    this.state = 0;
                                }
                            }
                        }
                    }
                    i3 = addAndGet(-i3);
                    if (i3 == 0) {
                        return;
                    }
                }
                avvVar.clear();
                this.item = null;
                dbxVar.onError(bsnVar.terminate());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableConcatMapSingle.java */
        /* renamed from: z1.bhq$a$a */
        /* loaded from: classes3.dex */
        public static final class C4354a<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final C4353a<?, R> parent;

            C4354a(C4353a<?, R> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.replace(this, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(R r) {
                this.parent.innerSuccess(r);
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
