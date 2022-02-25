package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bor */
/* loaded from: classes3.dex */
public final class SingleDelayWithPublisher<T, U> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19720a;

    /* renamed from: b */
    final Publisher<U> f19721b;

    public SingleDelayWithPublisher(SingleSource<T> ataVar, Publisher<U> dbwVar) {
        this.f19720a = ataVar;
        this.f19721b = dbwVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19721b.subscribe(new C4661a(asxVar, this.f19720a));
    }

    /* compiled from: SingleDelayWithPublisher.java */
    /* renamed from: z1.bor$a */
    /* loaded from: classes3.dex */
    static final class C4661a<T, U> extends AtomicReference<Disposable> implements FlowableSubscriber<U>, Disposable {
        private static final long serialVersionUID = -8565274649390031272L;
        boolean done;
        final SingleObserver<? super T> downstream;
        final SingleSource<T> source;
        dby upstream;

        C4661a(SingleObserver<? super T> asxVar, SingleSource<T> ataVar) {
            this.downstream = asxVar;
            this.source = ataVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(U u) {
            this.upstream.cancel();
            onComplete();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.source.mo10018a(new ResumeSingleObserver(this, this.downstream));
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.cancel();
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
