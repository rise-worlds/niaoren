package p110z1;

/* renamed from: z1.bkh */
/* loaded from: classes3.dex */
public final class ObservableFromArray<T> extends Observable<T> {

    /* renamed from: a */
    final T[] f19125a;

    public ObservableFromArray(T[] tArr) {
        this.f19125a = tArr;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        C4455a aVar = new C4455a(assVar, this.f19125a);
        assVar.onSubscribe(aVar);
        if (!aVar.f19129i) {
            aVar.m9627a();
        }
    }

    /* compiled from: ObservableFromArray.java */
    /* renamed from: z1.bkh$a */
    /* loaded from: classes3.dex */
    static final class C4455a<T> extends BasicQueueDisposable<T> {

        /* renamed from: f */
        final Observer<? super T> f19126f;

        /* renamed from: g */
        final T[] f19127g;

        /* renamed from: h */
        int f19128h;

        /* renamed from: i */
        boolean f19129i;

        /* renamed from: j */
        volatile boolean f19130j;

        C4455a(Observer<? super T> assVar, T[] tArr) {
            this.f19126f = assVar;
            this.f19127g = tArr;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.f19129i = true;
            return 1;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public T poll() {
            int i = this.f19128h;
            T[] tArr = this.f19127g;
            if (i == tArr.length) {
                return null;
            }
            this.f19128h = i + 1;
            return (T) ObjectHelper.m9873a((Object) tArr[i], "The array element is null");
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f19128h == this.f19127g.length;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.f19128h = this.f19127g.length;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19130j = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19130j;
        }

        /* renamed from: a */
        void m9627a() {
            T[] tArr = this.f19127g;
            int length = tArr.length;
            for (int i = 0; i < length && !isDisposed(); i++) {
                T t = tArr[i];
                if (t == null) {
                    Observer<? super T> assVar = this.f19126f;
                    assVar.onError(new NullPointerException("The element at index " + i + " is null"));
                    return;
                }
                this.f19126f.onNext(t);
            }
            if (!isDisposed()) {
                this.f19126f.onComplete();
            }
        }
    }
}
