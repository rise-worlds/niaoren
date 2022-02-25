package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.azr */
/* loaded from: classes3.dex */
public final class FlowableConcatWithMaybe<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final MaybeSource<? extends T> f17974c;

    public FlowableConcatWithMaybe(Flowable<T> arvVar, MaybeSource<? extends T> asiVar) {
        super(arvVar);
        this.f17974c = asiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4022a(dbxVar, this.f17974c));
    }

    /* compiled from: FlowableConcatWithMaybe.java */
    /* renamed from: z1.azr$a */
    /* loaded from: classes3.dex */
    static final class C4022a<T> extends SinglePostCompleteSubscriber<T, T> implements MaybeObserver<T> {
        private static final long serialVersionUID = -7346385463600070225L;
        boolean inMaybe;
        MaybeSource<? extends T> other;
        final AtomicReference<Disposable> otherDisposable = new AtomicReference<>();

        C4022a(Subscriber<? super T> dbxVar, MaybeSource<? extends T> asiVar) {
            super(dbxVar);
            this.other = asiVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.otherDisposable, atrVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.inMaybe) {
                this.downstream.onComplete();
                return;
            }
            this.inMaybe = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            MaybeSource<? extends T> asiVar = this.other;
            this.other = null;
            asiVar.mo10646a(this);
        }

        @Override // p110z1.SinglePostCompleteSubscriber, p110z1.dby
        public void cancel() {
            super.cancel();
            DisposableHelper.dispose(this.otherDisposable);
        }
    }
}
