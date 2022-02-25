package p110z1;

/* renamed from: z1.bjr */
/* loaded from: classes3.dex */
public final class ObservableDoAfterNext<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Consumer<? super T> f19041b;

    public ObservableDoAfterNext(ObservableSource<T> asqVar, Consumer<? super T> aumVar) {
        super(asqVar);
        this.f19041b = aumVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4436a(assVar, this.f19041b));
    }

    /* compiled from: ObservableDoAfterNext.java */
    /* renamed from: z1.bjr$a */
    /* loaded from: classes3.dex */
    static final class C4436a<T> extends BasicFuseableObserver<T, T> {

        /* renamed from: k */
        final Consumer<? super T> f19042k;

        C4436a(Observer<? super T> assVar, Consumer<? super T> aumVar) {
            super(assVar);
            this.f19042k = aumVar;
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f17602f.onNext(t);
            if (this.f17606j == 0) {
                try {
                    this.f19042k.accept(t);
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
            T poll = this.f17604h.poll();
            if (poll != null) {
                this.f19042k.accept(poll);
            }
            return poll;
        }
    }
}
