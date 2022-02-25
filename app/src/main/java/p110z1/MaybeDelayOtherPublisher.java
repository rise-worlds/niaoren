package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfd */
/* loaded from: classes3.dex */
public final class MaybeDelayOtherPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Publisher<U> f18567b;

    public MaybeDelayOtherPublisher(MaybeSource<T> asiVar, Publisher<U> dbwVar) {
        super(asiVar);
        this.f18567b = dbwVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4273a(asfVar, this.f18567b));
    }

    /* compiled from: MaybeDelayOtherPublisher.java */
    /* renamed from: z1.bfd$a */
    /* loaded from: classes3.dex */
    static final class C4273a<T, U> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final C4274b<T> f18568a;

        /* renamed from: b */
        final Publisher<U> f18569b;

        /* renamed from: c */
        Disposable f18570c;

        C4273a(MaybeObserver<? super T> asfVar, Publisher<U> dbwVar) {
            this.f18568a = new C4274b<>(asfVar);
            this.f18569b = dbwVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18570c.dispose();
            this.f18570c = DisposableHelper.DISPOSED;
            SubscriptionHelper.cancel(this.f18568a);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18568a.get() == SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18570c, atrVar)) {
                this.f18570c = atrVar;
                this.f18568a.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18570c = DisposableHelper.DISPOSED;
            this.f18568a.value = t;
            m9704a();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18570c = DisposableHelper.DISPOSED;
            this.f18568a.error = th;
            m9704a();
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18570c = DisposableHelper.DISPOSED;
            m9704a();
        }

        /* renamed from: a */
        void m9704a() {
            this.f18569b.subscribe(this.f18568a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeDelayOtherPublisher.java */
    /* renamed from: z1.bfd$b */
    /* loaded from: classes3.dex */
    public static final class C4274b<T> extends AtomicReference<dby> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = -1215060610805418006L;
        final MaybeObserver<? super T> downstream;
        Throwable error;
        T value;

        C4274b(MaybeObserver<? super T> asfVar) {
            this.downstream = asfVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, cjm.f20759b);
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            dby dbyVar = get();
            if (dbyVar != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                dbyVar.cancel();
                onComplete();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == null) {
                this.downstream.onError(th);
            } else {
                this.downstream.onError(new CompositeException(th2, th));
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.downstream.onSuccess(t);
            } else {
                this.downstream.onComplete();
            }
        }
    }
}
