package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhs */
/* loaded from: classes3.dex */
public final class FlowableSwitchMapMaybe<T, R> extends Flowable<R> {

    /* renamed from: b */
    final Flowable<T> f18762b;

    /* renamed from: c */
    final Function<? super T, ? extends MaybeSource<? extends R>> f18763c;

    /* renamed from: d */
    final boolean f18764d;

    public FlowableSwitchMapMaybe(Flowable<T> arvVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z) {
        this.f18762b = arvVar;
        this.f18763c = aunVar;
        this.f18764d = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f18762b.m11187a((FlowableSubscriber) new C4357a(dbxVar, this.f18763c, this.f18764d));
    }

    /* compiled from: FlowableSwitchMapMaybe.java */
    /* renamed from: z1.bhs$a */
    /* loaded from: classes3.dex */
    static final class C4357a<T, R> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        static final C4358a<Object> INNER_DISPOSED = new C4358a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        long emitted;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        dby upstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<C4358a<R>> inner = new AtomicReference<>();

        C4357a(Subscriber<? super R> dbxVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, boolean z) {
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
            C4358a<R> aVar;
            C4358a<R> aVar2 = this.inner.get();
            if (aVar2 != null) {
                aVar2.dispose();
            }
            try {
                MaybeSource asiVar = (MaybeSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                C4358a<R> aVar3 = new C4358a<>(this);
                do {
                    aVar = this.inner.get();
                    if (aVar == INNER_DISPOSED) {
                        return;
                    }
                } while (!this.inner.compareAndSet(aVar, aVar3));
                asiVar.mo10646a(aVar3);
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
            C4358a<Object> aVar = (C4358a) this.inner.getAndSet(INNER_DISPOSED);
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

        void innerError(C4358a<R> aVar, Throwable th) {
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

        void innerComplete(C4358a<R> aVar) {
            if (this.inner.compareAndSet(aVar, null)) {
                drain();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> dbxVar = this.downstream;
                AtomicThrowable bsnVar = this.errors;
                AtomicReference<C4358a<R>> atomicReference = this.inner;
                AtomicLong atomicLong = this.requested;
                long j = this.emitted;
                int i = 1;
                while (!this.cancelled) {
                    if (bsnVar.get() == null || this.delayErrors) {
                        boolean z = this.done;
                        C4358a<R> aVar = atomicReference.get();
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
        /* compiled from: FlowableSwitchMapMaybe.java */
        /* renamed from: z1.bhs$a$a */
        /* loaded from: classes3.dex */
        public static final class C4358a<R> extends AtomicReference<Disposable> implements MaybeObserver<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            volatile R item;
            final C4357a<?, R> parent;

            C4358a(C4357a<?, R> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(R r) {
                this.item = r;
                this.parent.drain();
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.parent.innerComplete(this);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
