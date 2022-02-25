package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bhw */
/* loaded from: classes3.dex */
public final class MaybeFlatMapPublisher<T, R> extends Flowable<R> {

    /* renamed from: b */
    final MaybeSource<T> f18772b;

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends R>> f18773c;

    public MaybeFlatMapPublisher(MaybeSource<T> asiVar, Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        this.f18772b = asiVar;
        this.f18773c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f18772b.mo10646a(new C4362a(dbxVar, this.f18773c));
    }

    /* compiled from: MaybeFlatMapPublisher.java */
    /* renamed from: z1.bhw$a */
    /* loaded from: classes3.dex */
    static final class C4362a<T, R> extends AtomicReference<dby> implements FlowableSubscriber<R>, MaybeObserver<T>, dby {
        private static final long serialVersionUID = -8948264376121066672L;
        final Subscriber<? super R> downstream;
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final AtomicLong requested = new AtomicLong();
        Disposable upstream;

        C4362a(Subscriber<? super R> dbxVar, Function<? super T, ? extends Publisher<? extends R>> aunVar) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
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

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                ((Publisher) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null Publisher")).subscribe(this);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this, this.requested, dbyVar);
        }
    }
}
