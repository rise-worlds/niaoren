package p110z1;

/* renamed from: z1.bcn */
/* loaded from: classes3.dex */
public final class FlowableReduceSeedSingle<T, R> extends Single<R> {

    /* renamed from: a */
    final Publisher<T> f18257a;

    /* renamed from: b */
    final R f18258b;

    /* renamed from: c */
    final BiFunction<R, ? super T, R> f18259c;

    public FlowableReduceSeedSingle(Publisher<T> dbwVar, R r, BiFunction<R, ? super T, R> auiVar) {
        this.f18257a = dbwVar;
        this.f18258b = r;
        this.f18259c = auiVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        this.f18257a.subscribe(new C4153a(asxVar, this.f18259c, this.f18258b));
    }

    /* compiled from: FlowableReduceSeedSingle.java */
    /* renamed from: z1.bcn$a */
    /* loaded from: classes3.dex */
    static final class C4153a<T, R> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super R> f18260a;

        /* renamed from: b */
        final BiFunction<R, ? super T, R> f18261b;

        /* renamed from: c */
        R f18262c;

        /* renamed from: d */
        dby f18263d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4153a(SingleObserver<? super R> asxVar, BiFunction<R, ? super T, R> auiVar, R r) {
            this.f18260a = asxVar;
            this.f18262c = r;
            this.f18261b = auiVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18263d, dbyVar)) {
                this.f18263d = dbyVar;
                this.f18260a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            R r = this.f18262c;
            if (r != null) {
                try {
                    this.f18262c = (R) ObjectHelper.m9873a(this.f18261b.apply(r, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18263d.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18262c != null) {
                this.f18262c = null;
                this.f18263d = SubscriptionHelper.CANCELLED;
                this.f18260a.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            R r = this.f18262c;
            if (r != null) {
                this.f18262c = null;
                this.f18263d = SubscriptionHelper.CANCELLED;
                this.f18260a.onSuccess(r);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18263d.cancel();
            this.f18263d = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18263d == SubscriptionHelper.CANCELLED;
        }
    }
}
