package p110z1;

/* renamed from: z1.blb */
/* loaded from: classes3.dex */
public final class ObservableMap<T, U> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final Function<? super T, ? extends U> f19224b;

    public ObservableMap(ObservableSource<T> asqVar, Function<? super T, ? extends U> aunVar) {
        super(asqVar);
        this.f19224b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super U> assVar) {
        this.f18807a.subscribe(new C4491a(assVar, this.f19224b));
    }

    /* compiled from: ObservableMap.java */
    /* renamed from: z1.blb$a */
    /* loaded from: classes3.dex */
    static final class C4491a<T, U> extends BasicFuseableObserver<T, U> {

        /* renamed from: k */
        final Function<? super T, ? extends U> f19225k;

        C4491a(Observer<? super U> assVar, Function<? super T, ? extends U> aunVar) {
            super(assVar);
            this.f19225k = aunVar;
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f17605i) {
                if (this.f17606j != 0) {
                    this.f17602f.onNext(null);
                    return;
                }
                try {
                    this.f17602f.onNext(ObjectHelper.m9873a(this.f19225k.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    m9868a(th);
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9869a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public U poll() throws Exception {
            T poll = this.f17604h.poll();
            if (poll != null) {
                return (U) ObjectHelper.m9873a(this.f19225k.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }
}
