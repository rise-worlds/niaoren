package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bht */
/* loaded from: classes3.dex */
public final class FlowableSwitchMapSingle<T, R> extends Flowable<R> {

    /* renamed from: b */
    final Flowable<T> f18765b;

    /* renamed from: c */
    final Function<? super T, ? extends SingleSource<? extends R>> f18766c;

    /* renamed from: d */
    final boolean f18767d;

    public FlowableSwitchMapSingle(Flowable<T> arvVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
        this.f18765b = arvVar;
        this.f18766c = aunVar;
        this.f18767d = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f18765b.m11187a((FlowableSubscriber) new C4359a(dbxVar, this.f18766c, this.f18767d));
    }

    /* compiled from: FlowableSwitchMapSingle.java */
    /* renamed from: z1.bht$a */
    /* loaded from: classes3.dex */
    static final class C4359a<T, R> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        static final C4360a<Object> INNER_DISPOSED = new C4360a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        long emitted;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        dby upstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<C4360a<R>> inner = new AtomicReference<>();

        C4359a(Subscriber<? super R> dbxVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar, boolean z) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
            this.delayErrors = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Subscriber
        public void onNext(T t) {
            C4360a<R> aVar;
            C4360a<R> aVar2 = this.inner.get();
            if (aVar2 != null) {
                aVar2.dispose();
            }
            try {
                SingleSource ataVar = (SingleSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                C4360a<R> aVar3 = new C4360a<>(this);
                do {
                    aVar = this.inner.get();
                    if (aVar == INNER_DISPOSED) {
                        return;
                    }
                } while (!this.inner.compareAndSet(aVar, aVar3));
                ataVar.mo10018a(aVar3);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.upstream.cancel();
                this.inner.getAndSet(INNER_DISPOSED);
                onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    disposeInner();
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

        /* JADX WARN: Multi-variable type inference failed */
        void disposeInner() {
            C4360a<Object> aVar = (C4360a) this.inner.getAndSet(INNER_DISPOSED);
            if (aVar != null && aVar != INNER_DISPOSED) {
                aVar.dispose();
            }
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
            disposeInner();
        }

        void innerError(C4360a<R> aVar, Throwable th) {
            if (!this.inner.compareAndSet(aVar, null) || !this.errors.addThrowable(th)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (!this.delayErrors) {
                this.upstream.cancel();
                disposeInner();
            }
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> dbxVar = this.downstream;
                AtomicThrowable bsnVar = this.errors;
                AtomicReference<C4360a<R>> atomicReference = this.inner;
                AtomicLong atomicLong = this.requested;
                long j = this.emitted;
                int i = 1;
                while (!this.cancelled) {
                    if (bsnVar.get() == null || this.delayErrors) {
                        boolean z = this.done;
                        C4360a<R> aVar = atomicReference.get();
                        boolean z2 = aVar == null;
                        if (z && z2) {
                            Throwable terminate = bsnVar.terminate();
                            if (terminate != null) {
                                dbxVar.onError(terminate);
                                return;
                            } else {
                                dbxVar.onComplete();
                                return;
                            }
                        } else if (z2 || aVar.item == null || j == atomicLong.get()) {
                            this.emitted = j;
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            atomicReference.compareAndSet(aVar, null);
                            dbxVar.onNext((R) aVar.item);
                            j++;
                        }
                    } else {
                        dbxVar.onError(bsnVar.terminate());
                        return;
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FlowableSwitchMapSingle.java */
        /* renamed from: z1.bht$a$a */
        /* loaded from: classes3.dex */
        public static final class C4360a<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            volatile R item;
            final C4359a<?, R> parent;

            C4360a(C4359a<?, R> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(R r) {
                this.item = r;
                this.parent.drain();
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
