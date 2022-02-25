package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bbo */
/* loaded from: classes3.dex */
public final class FlowableLastSingle<T> extends Single<T> {

    /* renamed from: a */
    final Publisher<T> f18183a;

    /* renamed from: b */
    final T f18184b;

    public FlowableLastSingle(Publisher<T> dbwVar, T t) {
        this.f18183a = dbwVar;
        this.f18184b = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f18183a.subscribe(new C4115a(asxVar, this.f18184b));
    }

    /* compiled from: FlowableLastSingle.java */
    /* renamed from: z1.bbo$a */
    /* loaded from: classes3.dex */
    static final class C4115a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f18185a;

        /* renamed from: b */
        final T f18186b;

        /* renamed from: c */
        dby f18187c;

        /* renamed from: d */
        T f18188d;

        C4115a(SingleObserver<? super T> asxVar, T t) {
            this.f18185a = asxVar;
            this.f18186b = t;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18187c.cancel();
            this.f18187c = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18187c == SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18187c, dbyVar)) {
                this.f18187c = dbyVar;
                this.f18185a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18188d = t;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18187c = SubscriptionHelper.CANCELLED;
            this.f18188d = null;
            this.f18185a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18187c = SubscriptionHelper.CANCELLED;
            T t = this.f18188d;
            if (t != null) {
                this.f18188d = null;
                this.f18185a.onSuccess(t);
                return;
            }
            T t2 = this.f18186b;
            if (t2 != null) {
                this.f18185a.onSuccess(t2);
            } else {
                this.f18185a.onError(new NoSuchElementException());
            }
        }
    }
}
