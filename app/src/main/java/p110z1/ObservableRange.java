package p110z1;

/* renamed from: z1.bln */
/* loaded from: classes3.dex */
public final class ObservableRange extends Observable<Integer> {

    /* renamed from: a */
    private final int f19268a;

    /* renamed from: b */
    private final long f19269b;

    public ObservableRange(int i, int i2) {
        this.f19268a = i;
        this.f19269b = i + i2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Integer> assVar) {
        C4508a aVar = new C4508a(assVar, this.f19268a, this.f19269b);
        assVar.onSubscribe(aVar);
        aVar.run();
    }

    /* compiled from: ObservableRange.java */
    /* renamed from: z1.bln$a */
    /* loaded from: classes3.dex */
    static final class C4508a extends BasicIntQueueDisposable<Integer> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Integer> downstream;
        final long end;
        boolean fused;
        long index;

        C4508a(Observer<? super Integer> assVar, long j, long j2) {
            this.downstream = assVar;
            this.index = j;
            this.end = j2;
        }

        void run() {
            if (!this.fused) {
                Observer<? super Integer> assVar = this.downstream;
                long j = this.end;
                for (long j2 = this.index; j2 != j && get() == 0; j2++) {
                    assVar.onNext(Integer.valueOf((int) j2));
                }
                if (get() == 0) {
                    lazySet(1);
                    assVar.onComplete();
                }
            }
        }

        @Override // p110z1.SimpleQueue
        @atn
        public Integer poll() throws Exception {
            long j = this.index;
            if (j != this.end) {
                this.index = 1 + j;
                return Integer.valueOf((int) j);
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
