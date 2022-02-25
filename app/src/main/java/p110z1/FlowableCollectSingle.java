package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.azj */
/* loaded from: classes3.dex */
public final class FlowableCollectSingle<T, U> extends Single<U> implements FuseToFlowable<U> {

    /* renamed from: a */
    final Flowable<T> f17937a;

    /* renamed from: b */
    final Callable<? extends U> f17938b;

    /* renamed from: c */
    final BiConsumer<? super U, ? super T> f17939c;

    public FlowableCollectSingle(Flowable<T> arvVar, Callable<? extends U> callable, BiConsumer<? super U, ? super T> auhVar) {
        this.f17937a = arvVar;
        this.f17938b = callable;
        this.f17939c = auhVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super U> asxVar) {
        try {
            this.f17937a.m11187a((FlowableSubscriber) new C4008a(asxVar, ObjectHelper.m9873a(this.f17938b.call(), "The initialSupplier returned a null value"), this.f17939c));
        } catch (Throwable th) {
            EmptyDisposable.error(th, asxVar);
        }
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<U> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableCollect(this.f17937a, this.f17938b, this.f17939c));
    }

    /* compiled from: FlowableCollectSingle.java */
    /* renamed from: z1.azj$a */
    /* loaded from: classes3.dex */
    static final class C4008a<T, U> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super U> f17940a;

        /* renamed from: b */
        final BiConsumer<? super U, ? super T> f17941b;

        /* renamed from: c */
        final U f17942c;

        /* renamed from: d */
        dby f17943d;

        /* renamed from: e */
        boolean f17944e;

        C4008a(SingleObserver<? super U> asxVar, U u, BiConsumer<? super U, ? super T> auhVar) {
            this.f17940a = asxVar;
            this.f17941b = auhVar;
            this.f17942c = u;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17943d, dbyVar)) {
                this.f17943d = dbyVar;
                this.f17940a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f17944e) {
                try {
                    this.f17941b.mo9895a((U) this.f17942c, t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17943d.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f17944e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17944e = true;
            this.f17943d = SubscriptionHelper.CANCELLED;
            this.f17940a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f17944e) {
                this.f17944e = true;
                this.f17943d = SubscriptionHelper.CANCELLED;
                this.f17940a.onSuccess((U) this.f17942c);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17943d.cancel();
            this.f17943d = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17943d == SubscriptionHelper.CANCELLED;
        }
    }
}
