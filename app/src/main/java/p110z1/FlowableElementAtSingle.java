package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bal */
/* loaded from: classes3.dex */
public final class FlowableElementAtSingle<T> extends Single<T> implements FuseToFlowable<T> {

    /* renamed from: a */
    final Flowable<T> f18067a;

    /* renamed from: b */
    final long f18068b;

    /* renamed from: c */
    final T f18069c;

    public FlowableElementAtSingle(Flowable<T> arvVar, long j, T t) {
        this.f18067a = arvVar;
        this.f18068b = j;
        this.f18069c = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f18067a.m11187a((FlowableSubscriber) new C4061a(asxVar, this.f18068b, this.f18069c));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<T> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableElementAt(this.f18067a, this.f18068b, this.f18069c, true));
    }

    /* compiled from: FlowableElementAtSingle.java */
    /* renamed from: z1.bal$a */
    /* loaded from: classes3.dex */
    static final class C4061a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f18070a;

        /* renamed from: b */
        final long f18071b;

        /* renamed from: c */
        final T f18072c;

        /* renamed from: d */
        dby f18073d;

        /* renamed from: e */
        long f18074e;

        /* renamed from: f */
        boolean f18075f;

        C4061a(SingleObserver<? super T> asxVar, long j, T t) {
            this.f18070a = asxVar;
            this.f18071b = j;
            this.f18072c = t;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18073d, dbyVar)) {
                this.f18073d = dbyVar;
                this.f18070a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18075f) {
                long j = this.f18074e;
                if (j == this.f18071b) {
                    this.f18075f = true;
                    this.f18073d.cancel();
                    this.f18073d = SubscriptionHelper.CANCELLED;
                    this.f18070a.onSuccess(t);
                    return;
                }
                this.f18074e = j + 1;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18075f) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18075f = true;
            this.f18073d = SubscriptionHelper.CANCELLED;
            this.f18070a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18073d = SubscriptionHelper.CANCELLED;
            if (!this.f18075f) {
                this.f18075f = true;
                T t = this.f18072c;
                if (t != null) {
                    this.f18070a.onSuccess(t);
                } else {
                    this.f18070a.onError(new NoSuchElementException());
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18073d.cancel();
            this.f18073d = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18073d == SubscriptionHelper.CANCELLED;
        }
    }
}
