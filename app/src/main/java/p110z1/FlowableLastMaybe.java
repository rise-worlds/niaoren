package p110z1;

/* renamed from: z1.bbn */
/* loaded from: classes3.dex */
public final class FlowableLastMaybe<T> extends Maybe<T> {

    /* renamed from: a */
    final Publisher<T> f18179a;

    public FlowableLastMaybe(Publisher<T> dbwVar) {
        this.f18179a = dbwVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18179a.subscribe(new C4114a(asfVar));
    }

    /* compiled from: FlowableLastMaybe.java */
    /* renamed from: z1.bbn$a */
    /* loaded from: classes3.dex */
    static final class C4114a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18180a;

        /* renamed from: b */
        dby f18181b;

        /* renamed from: c */
        T f18182c;

        C4114a(MaybeObserver<? super T> asfVar) {
            this.f18180a = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18181b.cancel();
            this.f18181b = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18181b == SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18181b, dbyVar)) {
                this.f18181b = dbyVar;
                this.f18180a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18182c = t;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18181b = SubscriptionHelper.CANCELLED;
            this.f18182c = null;
            this.f18180a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18181b = SubscriptionHelper.CANCELLED;
            T t = this.f18182c;
            if (t != null) {
                this.f18182c = null;
                this.f18180a.onSuccess(t);
                return;
            }
            this.f18180a.onComplete();
        }
    }
}
