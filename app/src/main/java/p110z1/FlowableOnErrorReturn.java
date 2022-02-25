package p110z1;

/* renamed from: z1.bcg */
/* loaded from: classes3.dex */
public final class FlowableOnErrorReturn<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super Throwable, ? extends T> f18218c;

    public FlowableOnErrorReturn(Flowable<T> arvVar, Function<? super Throwable, ? extends T> aunVar) {
        super(arvVar);
        this.f18218c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4138a(dbxVar, this.f18218c));
    }

    /* compiled from: FlowableOnErrorReturn.java */
    /* renamed from: z1.bcg$a */
    /* loaded from: classes3.dex */
    static final class C4138a<T> extends SinglePostCompleteSubscriber<T, T> {
        private static final long serialVersionUID = -3740826063558713822L;
        final Function<? super Throwable, ? extends T> valueSupplier;

        C4138a(Subscriber<? super T> dbxVar, Function<? super Throwable, ? extends T> aunVar) {
            super(dbxVar);
            this.valueSupplier = aunVar;
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            try {
                complete(ObjectHelper.m9873a(this.valueSupplier.apply(th), "The valueSupplier returned a null value"));
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
