package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhn */
/* loaded from: classes3.dex */
public final class CompletableAndThenPublisher<R> extends Flowable<R> {

    /* renamed from: b */
    final CompletableSource f18737b;

    /* renamed from: c */
    final Publisher<? extends R> f18738c;

    public CompletableAndThenPublisher(CompletableSource arsVar, Publisher<? extends R> dbwVar) {
        this.f18737b = arsVar;
        this.f18738c = dbwVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f18737b.mo11309a(new C4348a(dbxVar, this.f18738c));
    }

    /* compiled from: CompletableAndThenPublisher.java */
    /* renamed from: z1.bhn$a */
    /* loaded from: classes3.dex */
    static final class C4348a<R> extends AtomicReference<dby> implements CompletableObserver, FlowableSubscriber<R>, dby {
        private static final long serialVersionUID = -8948264376121066672L;
        final Subscriber<? super R> downstream;
        Publisher<? extends R> other;
        final AtomicLong requested = new AtomicLong();
        Disposable upstream;

        C4348a(Subscriber<? super R> dbxVar, Publisher<? extends R> dbwVar) {
            this.downstream = dbxVar;
            this.other = dbwVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            Publisher<? extends R> dbwVar = this.other;
            if (dbwVar == null) {
                this.downstream.onComplete();
                return;
            }
            this.other = null;
            dbwVar.subscribe(this);
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this, this.requested, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.dispose();
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this, this.requested, dbyVar);
        }
    }
}
