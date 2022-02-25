package p110z1;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhb */
/* loaded from: classes3.dex */
public final class MaybeTimeoutPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Publisher<U> f18711b;

    /* renamed from: c */
    final MaybeSource<? extends T> f18712c;

    public MaybeTimeoutPublisher(MaybeSource<T> asiVar, Publisher<U> dbwVar, MaybeSource<? extends T> asiVar2) {
        super(asiVar);
        this.f18711b = dbwVar;
        this.f18712c = asiVar2;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4335b bVar = new C4335b(asfVar, this.f18712c);
        asfVar.onSubscribe(bVar);
        this.f18711b.subscribe(bVar.other);
        this.f18529a.mo10646a(bVar);
    }

    /* compiled from: MaybeTimeoutPublisher.java */
    /* renamed from: z1.bhb$b */
    /* loaded from: classes3.dex */
    static final class C4335b<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -5955289211445418871L;
        final MaybeObserver<? super T> downstream;
        final MaybeSource<? extends T> fallback;
        final C4336c<T, U> other = new C4336c<>(this);
        final C4334a<T> otherObserver;

        C4335b(MaybeObserver<? super T> asfVar, MaybeSource<? extends T> asiVar) {
            this.downstream = asfVar;
            this.fallback = asiVar;
            this.otherObserver = asiVar != null ? new C4334a<>(asfVar) : null;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            SubscriptionHelper.cancel(this.other);
            C4334a<T> aVar = this.otherObserver;
            if (aVar != null) {
                DisposableHelper.dispose(aVar);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onComplete();
            }
        }

        public void otherError(Throwable th) {
            if (DisposableHelper.dispose(this)) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        public void otherComplete() {
            if (DisposableHelper.dispose(this)) {
                MaybeSource<? extends T> asiVar = this.fallback;
                if (asiVar == null) {
                    this.downstream.onError(new TimeoutException());
                } else {
                    asiVar.mo10646a(this.otherObserver);
                }
            }
        }
    }

    /* compiled from: MaybeTimeoutPublisher.java */
    /* renamed from: z1.bhb$c */
    /* loaded from: classes3.dex */
    static final class C4336c<T, U> extends AtomicReference<dby> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = 8663801314800248617L;
        final C4335b<T, U> parent;

        C4336c(C4335b<T, U> bVar) {
            this.parent = bVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            get().cancel();
            this.parent.otherComplete();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.parent.otherComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeTimeoutPublisher.java */
    /* renamed from: z1.bhb$a */
    /* loaded from: classes3.dex */
    public static final class C4334a<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = 8663801314800248617L;
        final MaybeObserver<? super T> downstream;

        C4334a(MaybeObserver<? super T> asfVar) {
            this.downstream = asfVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
