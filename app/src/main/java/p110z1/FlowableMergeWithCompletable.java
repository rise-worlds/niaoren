package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bbv */
/* loaded from: classes3.dex */
public final class FlowableMergeWithCompletable<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final CompletableSource f18199c;

    public FlowableMergeWithCompletable(Flowable<T> arvVar, CompletableSource arsVar) {
        super(arvVar);
        this.f18199c = arsVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4121a aVar = new C4121a(dbxVar);
        dbxVar.onSubscribe(aVar);
        this.f17812b.m11187a((FlowableSubscriber) aVar);
        this.f18199c.mo11309a(aVar.otherObserver);
    }

    /* compiled from: FlowableMergeWithCompletable.java */
    /* renamed from: z1.bbv$a */
    /* loaded from: classes3.dex */
    static final class C4121a<T> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -4592979584110982903L;
        final Subscriber<? super T> downstream;
        volatile boolean mainDone;
        volatile boolean otherDone;
        final AtomicReference<dby> mainSubscription = new AtomicReference<>();
        final C4122a otherObserver = new C4122a(this);
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();

        C4121a(Subscriber<? super T> dbxVar) {
            this.downstream = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.mainSubscription, this.requested, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            HalfSerializer.m9424a(this.downstream, t, this, this.error);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.mainSubscription);
            HalfSerializer.m9423a((Subscriber<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.mainDone = true;
            if (this.otherDone) {
                HalfSerializer.m9422a(this.downstream, this, this.error);
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.mainSubscription, this.requested, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            SubscriptionHelper.cancel(this.mainSubscription);
            DisposableHelper.dispose(this.otherObserver);
        }

        void otherError(Throwable th) {
            SubscriptionHelper.cancel(this.mainSubscription);
            HalfSerializer.m9423a((Subscriber<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        void otherComplete() {
            this.otherDone = true;
            if (this.mainDone) {
                HalfSerializer.m9422a(this.downstream, this, this.error);
            }
        }

        /* compiled from: FlowableMergeWithCompletable.java */
        /* renamed from: z1.bbv$a$a */
        /* loaded from: classes3.dex */
        static final class C4122a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -2935427570954647017L;
            final C4121a<?> parent;

            C4122a(C4121a<?> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
