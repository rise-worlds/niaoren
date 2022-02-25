package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bbs */
/* loaded from: classes3.dex */
public final class FlowableMapNotification<T, R> extends AbstractFlowableWithUpstream<T, R> {

    /* renamed from: c */
    final Function<? super T, ? extends R> f18194c;

    /* renamed from: d */
    final Function<? super Throwable, ? extends R> f18195d;

    /* renamed from: e */
    final Callable<? extends R> f18196e;

    public FlowableMapNotification(Flowable<T> arvVar, Function<? super T, ? extends R> aunVar, Function<? super Throwable, ? extends R> aunVar2, Callable<? extends R> callable) {
        super(arvVar);
        this.f18194c = aunVar;
        this.f18195d = aunVar2;
        this.f18196e = callable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4119a(dbxVar, this.f18194c, this.f18195d, this.f18196e));
    }

    /* compiled from: FlowableMapNotification.java */
    /* renamed from: z1.bbs$a */
    /* loaded from: classes3.dex */
    static final class C4119a<T, R> extends SinglePostCompleteSubscriber<T, R> {
        private static final long serialVersionUID = 2757120512858778108L;
        final Callable<? extends R> onCompleteSupplier;
        final Function<? super Throwable, ? extends R> onErrorMapper;
        final Function<? super T, ? extends R> onNextMapper;

        C4119a(Subscriber<? super R> dbxVar, Function<? super T, ? extends R> aunVar, Function<? super Throwable, ? extends R> aunVar2, Callable<? extends R> callable) {
            super(dbxVar);
            this.onNextMapper = aunVar;
            this.onErrorMapper = aunVar2;
            this.onCompleteSupplier = callable;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            try {
                Object a = ObjectHelper.m9873a(this.onNextMapper.apply(t), "The onNext publisher returned is null");
                this.produced++;
                this.downstream.onNext(a);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            try {
                complete(ObjectHelper.m9873a(this.onErrorMapper.apply(th), "The onError publisher returned is null"));
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Subscriber
        public void onComplete() {
            try {
                complete(ObjectHelper.m9873a(this.onCompleteSupplier.call(), "The onComplete publisher returned is null"));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }
    }
}
