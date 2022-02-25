package p110z1;

/* renamed from: z1.bjq */
/* loaded from: classes3.dex */
public final class ObservableDistinctUntilChanged<T, K> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super T, K> f19035b;

    /* renamed from: c */
    final BiPredicate<? super K, ? super K> f19036c;

    public ObservableDistinctUntilChanged(ObservableSource<T> asqVar, Function<? super T, K> aunVar, BiPredicate<? super K, ? super K> aujVar) {
        super(asqVar);
        this.f19035b = aunVar;
        this.f19036c = aujVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4435a(assVar, this.f19035b, this.f19036c));
    }

    /* compiled from: ObservableDistinctUntilChanged.java */
    /* renamed from: z1.bjq$a */
    /* loaded from: classes3.dex */
    static final class C4435a<T, K> extends BasicFuseableObserver<T, T> {

        /* renamed from: k */
        final Function<? super T, K> f19037k;

        /* renamed from: l */
        final BiPredicate<? super K, ? super K> f19038l;

        /* renamed from: m */
        K f19039m;

        /* renamed from: n */
        boolean f19040n;

        C4435a(Observer<? super T> assVar, Function<? super T, K> aunVar, BiPredicate<? super K, ? super K> aujVar) {
            super(assVar);
            this.f19037k = aunVar;
            this.f19038l = aujVar;
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f17605i) {
                if (this.f17606j != 0) {
                    this.f17602f.onNext(t);
                    return;
                }
                try {
                    K apply = this.f19037k.apply(t);
                    if (this.f19040n) {
                        boolean a = this.f19038l.mo9871a((K) this.f19039m, apply);
                        this.f19039m = apply;
                        if (a) {
                            return;
                        }
                    } else {
                        this.f19040n = true;
                        this.f19039m = apply;
                    }
                    this.f17602f.onNext(t);
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
        public T poll() throws Exception {
            while (true) {
                T poll = this.f17604h.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.f19037k.apply(poll);
                if (!this.f19040n) {
                    this.f19040n = true;
                    this.f19039m = apply;
                    return poll;
                } else if (!this.f19038l.mo9871a((K) this.f19039m, apply)) {
                    this.f19039m = apply;
                    return poll;
                } else {
                    this.f19039m = apply;
                }
            }
        }
    }
}
