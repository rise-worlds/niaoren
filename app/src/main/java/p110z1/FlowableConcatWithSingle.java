package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.azs */
/* loaded from: classes3.dex */
public final class FlowableConcatWithSingle<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final SingleSource<? extends T> f17975c;

    public FlowableConcatWithSingle(Flowable<T> arvVar, SingleSource<? extends T> ataVar) {
        super(arvVar);
        this.f17975c = ataVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4023a(dbxVar, this.f17975c));
    }

    /* compiled from: FlowableConcatWithSingle.java */
    /* renamed from: z1.azs$a */
    /* loaded from: classes3.dex */
    static final class C4023a<T> extends SinglePostCompleteSubscriber<T, T> implements SingleObserver<T> {
        private static final long serialVersionUID = -7346385463600070225L;
        SingleSource<? extends T> other;
        final AtomicReference<Disposable> otherDisposable = new AtomicReference<>();

        C4023a(Subscriber<? super T> dbxVar, SingleSource<? extends T> ataVar) {
            super(dbxVar);
            this.other = ataVar;
        }

        @Override // p110z1.SingleObserver
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

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.upstream = SubscriptionHelper.CANCELLED;
            SingleSource<? extends T> ataVar = this.other;
            this.other = null;
            ataVar.mo10018a(this);
        }

        @Override // p110z1.SinglePostCompleteSubscriber, p110z1.dby
        public void cancel() {
            super.cancel();
            DisposableHelper.dispose(this.otherDisposable);
        }
    }
}
