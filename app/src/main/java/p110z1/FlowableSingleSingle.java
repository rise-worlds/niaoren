package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bdh */
/* loaded from: classes3.dex */
public final class FlowableSingleSingle<T> extends Single<T> implements FuseToFlowable<T> {

    /* renamed from: a */
    final Flowable<T> f18340a;

    /* renamed from: b */
    final T f18341b;

    public FlowableSingleSingle(Flowable<T> arvVar, T t) {
        this.f18340a = arvVar;
        this.f18341b = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f18340a.m11187a((FlowableSubscriber) new C4196a(asxVar, this.f18341b));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<T> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableSingle(this.f18340a, this.f18341b, true));
    }

    /* compiled from: FlowableSingleSingle.java */
    /* renamed from: z1.bdh$a */
    /* loaded from: classes3.dex */
    static final class C4196a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f18342a;

        /* renamed from: b */
        final T f18343b;

        /* renamed from: c */
        dby f18344c;

        /* renamed from: d */
        boolean f18345d;

        /* renamed from: e */
        T f18346e;

        C4196a(SingleObserver<? super T> asxVar, T t) {
            this.f18342a = asxVar;
            this.f18343b = t;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18344c, dbyVar)) {
                this.f18344c = dbyVar;
                this.f18342a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18345d) {
                if (this.f18346e != null) {
                    this.f18345d = true;
                    this.f18344c.cancel();
                    this.f18344c = SubscriptionHelper.CANCELLED;
                    this.f18342a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.f18346e = t;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18345d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18345d = true;
            this.f18344c = SubscriptionHelper.CANCELLED;
            this.f18342a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18345d) {
                this.f18345d = true;
                this.f18344c = SubscriptionHelper.CANCELLED;
                T t = this.f18346e;
                this.f18346e = null;
                if (t == null) {
                    t = this.f18343b;
                }
                if (t != null) {
                    this.f18342a.onSuccess(t);
                } else {
                    this.f18342a.onError(new NoSuchElementException());
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18344c.cancel();
            this.f18344c = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18344c == SubscriptionHelper.CANCELLED;
        }
    }
}
