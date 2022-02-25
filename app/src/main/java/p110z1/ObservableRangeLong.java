package p110z1;

/* renamed from: z1.blo */
/* loaded from: classes3.dex */
public final class ObservableRangeLong extends Observable<Long> {

    /* renamed from: a */
    private final long f19270a;

    /* renamed from: b */
    private final long f19271b;

    public ObservableRangeLong(long j, long j2) {
        this.f19270a = j;
        this.f19271b = j2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Long> assVar) {
        long j = this.f19270a;
        C4509a aVar = new C4509a(assVar, j, j + this.f19271b);
        assVar.onSubscribe(aVar);
        aVar.run();
    }

    /* compiled from: ObservableRangeLong.java */
    /* renamed from: z1.blo$a */
    /* loaded from: classes3.dex */
    static final class C4509a extends BasicIntQueueDisposable<Long> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Long> downstream;
        final long end;
        boolean fused;
        long index;

        C4509a(Observer<? super Long> assVar, long j, long j2) {
            this.downstream = assVar;
            this.index = j;
            this.end = j2;
        }

        void run() {
            if (!this.fused) {
                Observer<? super Long> assVar = this.downstream;
                long j = this.end;
                for (long j2 = this.index; j2 != j && get() == 0; j2++) {
                    assVar.onNext(Long.valueOf(j2));
                }
                if (get() == 0) {
                    lazySet(1);
                    assVar.onComplete();
                }
            }
        }

        @Override // p110z1.SimpleQueue
        @atn
        public Long poll() throws Exception {
            long j = this.index;
            if (j != this.end) {
                this.index = 1 + j;
                return Long.valueOf(j);
            }
            lazySet(1);
            return null;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.index = this.end;
            lazySet(1);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            set(1);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() != 0;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.fused = true;
            return 1;
        }
    }
}
