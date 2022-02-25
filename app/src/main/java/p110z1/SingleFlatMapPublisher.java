package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bpl */
/* loaded from: classes3.dex */
public final class SingleFlatMapPublisher<T, R> extends Flowable<R> {

    /* renamed from: b */
    final SingleSource<T> f19791b;

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends R>> f19792c;

    public SingleFlatMapPublisher(SingleSource<T> ataVar, Function<? super T, ? extends Publisher<? extends R>> aunVar) {
        this.f19791b = ataVar;
        this.f19792c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f19791b.mo10018a(new C4683a(dbxVar, this.f19792c));
    }

    /* compiled from: SingleFlatMapPublisher.java */
    /* renamed from: z1.bpl$a */
    /* loaded from: classes3.dex */
    static final class C4683a<S, T> extends AtomicLong implements FlowableSubscriber<T>, SingleObserver<S>, dby {
        private static final long serialVersionUID = 7759721921468635667L;
        Disposable disposable;
        final Subscriber<? super T> downstream;
        final Function<? super S, ? extends Publisher<? extends T>> mapper;
        final AtomicReference<dby> parent = new AtomicReference<>();

        C4683a(Subscriber<? super T> dbxVar, Function<? super S, ? extends Publisher<? extends T>> aunVar) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.disposable = atrVar;
            this.downstream.onSubscribe(this);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(S s) {
            try {
                ((Publisher) ObjectHelper.m9873a(this.mapper.apply(s), "the mapper returned a null Publisher")).subscribe(this);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.deferredSetOnce(this.parent, this, dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.dby
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.parent, this, j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.disposable.dispose();
            SubscriptionHelper.cancel(this.parent);
        }
    }
}
