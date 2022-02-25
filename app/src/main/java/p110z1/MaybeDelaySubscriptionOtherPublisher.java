package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfe */
/* loaded from: classes3.dex */
public final class MaybeDelaySubscriptionOtherPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Publisher<U> f18571b;

    public MaybeDelaySubscriptionOtherPublisher(MaybeSource<T> asiVar, Publisher<U> dbwVar) {
        super(asiVar);
        this.f18571b = dbwVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18571b.subscribe(new C4276b(asfVar, this.f18529a));
    }

    /* compiled from: MaybeDelaySubscriptionOtherPublisher.java */
    /* renamed from: z1.bfe$b */
    /* loaded from: classes3.dex */
    static final class C4276b<T> implements FlowableSubscriber<Object>, Disposable {

        /* renamed from: a */
        final C4275a<T> f18572a;

        /* renamed from: b */
        MaybeSource<T> f18573b;

        /* renamed from: c */
        dby f18574c;

        C4276b(MaybeObserver<? super T> asfVar, MaybeSource<T> asiVar) {
            this.f18572a = new C4275a<>(asfVar);
            this.f18573b = asiVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18574c, dbyVar)) {
                this.f18574c = dbyVar;
                this.f18572a.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            if (this.f18574c != SubscriptionHelper.CANCELLED) {
                this.f18574c.cancel();
                this.f18574c = SubscriptionHelper.CANCELLED;
                m9703a();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18574c != SubscriptionHelper.CANCELLED) {
                this.f18574c = SubscriptionHelper.CANCELLED;
                this.f18572a.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.f18574c != SubscriptionHelper.CANCELLED) {
                this.f18574c = SubscriptionHelper.CANCELLED;
                m9703a();
            }
        }

        /* renamed from: a */
        void m9703a() {
            MaybeSource<T> asiVar = this.f18573b;
            this.f18573b = null;
            asiVar.mo10646a(this.f18572a);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.f18572a.get());
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18574c.cancel();
            this.f18574c = SubscriptionHelper.CANCELLED;
            DisposableHelper.dispose(this.f18572a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeDelaySubscriptionOtherPublisher.java */
    /* renamed from: z1.bfe$a */
    /* loaded from: classes3.dex */
    public static final class C4275a<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = 706635022205076709L;
        final MaybeObserver<? super T> downstream;

        C4275a(MaybeObserver<? super T> asfVar) {
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
