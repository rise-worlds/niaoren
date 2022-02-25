package p110z1;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bqa */
/* loaded from: classes3.dex */
public final class SingleTakeUntil<T, U> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19826a;

    /* renamed from: b */
    final Publisher<U> f19827b;

    public SingleTakeUntil(SingleSource<T> ataVar, Publisher<U> dbwVar) {
        this.f19826a = ataVar;
        this.f19827b = dbwVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        C4697a aVar = new C4697a(asxVar);
        asxVar.onSubscribe(aVar);
        this.f19827b.subscribe(aVar.other);
        this.f19826a.mo10018a(aVar);
    }

    /* compiled from: SingleTakeUntil.java */
    /* renamed from: z1.bqa$a */
    /* loaded from: classes3.dex */
    static final class C4697a<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -622603812305745221L;
        final SingleObserver<? super T> downstream;
        final C4698b other = new C4698b(this);

        C4697a(SingleObserver<? super T> asxVar) {
            this.downstream = asxVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            this.other.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.other.dispose();
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.other.dispose();
            if (get() == DisposableHelper.DISPOSED || getAndSet(DisposableHelper.DISPOSED) == DisposableHelper.DISPOSED) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        void otherError(Throwable th) {
            Disposable andSet;
            if (get() == DisposableHelper.DISPOSED || (andSet = getAndSet(DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            if (andSet != null) {
                andSet.dispose();
            }
            this.downstream.onError(th);
        }
    }

    /* compiled from: SingleTakeUntil.java */
    /* renamed from: z1.bqa$b */
    /* loaded from: classes3.dex */
    static final class C4698b extends AtomicReference<dby> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = 5170026210238877381L;
        final C4697a<?> parent;

        C4698b(C4697a<?> aVar) {
            this.parent = aVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.otherError(new CancellationException());
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.otherError(new CancellationException());
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }
    }
}
