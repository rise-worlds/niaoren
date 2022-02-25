package p110z1;

/* renamed from: z1.bka */
/* loaded from: classes3.dex */
public final class ObservableFilter<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Predicate<? super T> f19105b;

    public ObservableFilter(ObservableSource<T> asqVar, Predicate<? super T> auxVar) {
        super(asqVar);
        this.f19105b = auxVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4443a(assVar, this.f19105b));
    }

    /* compiled from: ObservableFilter.java */
    /* renamed from: z1.bka$a */
    /* loaded from: classes3.dex */
    static final class C4443a<T> extends BasicFuseableObserver<T, T> {

        /* renamed from: k */
        final Predicate<? super T> f19106k;

        C4443a(Observer<? super T> assVar, Predicate<? super T> auxVar) {
            super(assVar);
            this.f19106k = auxVar;
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.f17606j == 0) {
                try {
                    if (this.f19106k.test(t)) {
                        this.f17602f.onNext(t);
                    }
                } catch (Throwable th) {
                    m9868a(th);
                }
            } else {
                this.f17602f.onNext(null);
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            return m9869a(i);
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() throws Exception {
            T poll;
            do {
                poll = this.f17604h.poll();
                if (poll == null) {
                    break;
                }
            } while (!this.f19106k.test(poll));
            return poll;
        }
    }
}
