package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.bef */
/* loaded from: classes3.dex */
public final class FlowableToListSingle<T, U extends Collection<? super T>> extends Single<U> implements FuseToFlowable<U> {

    /* renamed from: a */
    final Flowable<T> f18426a;

    /* renamed from: b */
    final Callable<U> f18427b;

    public FlowableToListSingle(Flowable<T> arvVar) {
        this(arvVar, ArrayListSupplier.asCallable());
    }

    public FlowableToListSingle(Flowable<T> arvVar, Callable<U> callable) {
        this.f18426a = arvVar;
        this.f18427b = callable;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super U> asxVar) {
        try {
            this.f18426a.m11187a((FlowableSubscriber) new C4232a(asxVar, (Collection) ObjectHelper.m9873a(this.f18427b.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asxVar);
        }
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<U> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableToList(this.f18426a, this.f18427b));
    }

    /* compiled from: FlowableToListSingle.java */
    /* renamed from: z1.bef$a */
    /* loaded from: classes3.dex */
    static final class C4232a<T, U extends Collection<? super T>> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super U> f18428a;

        /* renamed from: b */
        dby f18429b;

        /* renamed from: c */
        U f18430c;

        C4232a(SingleObserver<? super U> asxVar, U u) {
            this.f18428a = asxVar;
            this.f18430c = u;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18429b, dbyVar)) {
                this.f18429b = dbyVar;
                this.f18428a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18430c.add(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18430c = null;
            this.f18429b = SubscriptionHelper.CANCELLED;
            this.f18428a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18429b = SubscriptionHelper.CANCELLED;
            this.f18428a.onSuccess(this.f18430c);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18429b.cancel();
            this.f18429b = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18429b == SubscriptionHelper.CANCELLED;
        }
    }
}
