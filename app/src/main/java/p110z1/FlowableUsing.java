package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.beh */
/* loaded from: classes3.dex */
public final class FlowableUsing<T, D> extends Flowable<T> {

    /* renamed from: b */
    final Callable<? extends D> f18433b;

    /* renamed from: c */
    final Function<? super D, ? extends Publisher<? extends T>> f18434c;

    /* renamed from: d */
    final Consumer<? super D> f18435d;

    /* renamed from: e */
    final boolean f18436e;

    public FlowableUsing(Callable<? extends D> callable, Function<? super D, ? extends Publisher<? extends T>> aunVar, Consumer<? super D> aumVar, boolean z) {
        this.f18433b = callable;
        this.f18434c = aunVar;
        this.f18435d = aumVar;
        this.f18436e = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        try {
            Object call = this.f18433b.call();
            try {
                ((Publisher) ObjectHelper.m9873a(this.f18434c.apply(call), "The sourceSupplier returned a null Publisher")).subscribe(new C4235a(dbxVar, call, this.f18435d, this.f18436e));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                try {
                    this.f18435d.accept(call);
                    EmptySubscription.error(th, dbxVar);
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    EmptySubscription.error(new CompositeException(th, th2), dbxVar);
                }
            }
        } catch (Throwable th3) {
            Exceptions.m9983b(th3);
            EmptySubscription.error(th3, dbxVar);
        }
    }

    /* compiled from: FlowableUsing.java */
    /* renamed from: z1.beh$a */
    /* loaded from: classes3.dex */
    static final class C4235a<T, D> extends AtomicBoolean implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 5904473792286235046L;
        final Consumer<? super D> disposer;
        final Subscriber<? super T> downstream;
        final boolean eager;
        final D resource;
        dby upstream;

        C4235a(Subscriber<? super T> dbxVar, D d, Consumer<? super D> aumVar, boolean z) {
            this.downstream = dbxVar;
            this.resource = d;
            this.disposer = aumVar;
            this.eager = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.eager) {
                Throwable th2 = null;
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept((D) this.resource);
                    } catch (Throwable th3) {
                        th2 = th3;
                        Exceptions.m9983b(th2);
                    }
                }
                this.upstream.cancel();
                if (th2 != null) {
                    this.downstream.onError(new CompositeException(th, th2));
                } else {
                    this.downstream.onError(th);
                }
            } else {
                this.downstream.onError(th);
                this.upstream.cancel();
                disposeAfter();
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept((D) this.resource);
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        this.downstream.onError(th);
                        return;
                    }
                }
                this.upstream.cancel();
                this.downstream.onComplete();
                return;
            }
            this.downstream.onComplete();
            this.upstream.cancel();
            disposeAfter();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            disposeAfter();
            this.upstream.cancel();
        }

        void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept((D) this.resource);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
            }
        }
    }
}
