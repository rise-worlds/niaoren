package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.azq */
/* loaded from: classes3.dex */
public final class FlowableConcatWithCompletable<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final CompletableSource f17973c;

    public FlowableConcatWithCompletable(Flowable<T> arvVar, CompletableSource arsVar) {
        super(arvVar);
        this.f17973c = arsVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4021a(dbxVar, this.f17973c));
    }

    /* compiled from: FlowableConcatWithCompletable.java */
    /* renamed from: z1.azq$a */
    /* loaded from: classes3.dex */
    static final class C4021a<T> extends AtomicReference<Disposable> implements CompletableObserver, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -7346385463600070225L;
        final Subscriber<? super T> downstream;
        boolean inCompletable;
        CompletableSource other;
        dby upstream;

        C4021a(Subscriber<? super T> dbxVar, CompletableSource arsVar) {
            this.downstream = dbxVar;
            this.other = arsVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (this.inCompletable) {
                this.downstream.onComplete();
                return;
            }
            this.inCompletable = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            CompletableSource arsVar = this.other;
            this.other = null;
            arsVar.mo11309a(this);
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
            DisposableHelper.dispose(this);
        }
    }
}
