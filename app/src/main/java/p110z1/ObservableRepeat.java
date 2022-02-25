package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.blt */
/* loaded from: classes3.dex */
public final class ObservableRepeat<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19295b;

    public ObservableRepeat(Observable<T> aslVar, long j) {
        super(aslVar);
        this.f19295b = j;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SequentialDisposable avfVar = new SequentialDisposable();
        assVar.onSubscribe(avfVar);
        long j = this.f19295b;
        long j2 = cjm.f20759b;
        if (j != cjm.f20759b) {
            j2 = j - 1;
        }
        new C4514a(assVar, j2, avfVar, this.f18807a).subscribeNext();
    }

    /* compiled from: ObservableRepeat.java */
    /* renamed from: z1.blt$a */
    /* loaded from: classes3.dex */
    static final class C4514a<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> downstream;
        long remaining;

        /* renamed from: sd */
        final SequentialDisposable f19296sd;
        final ObservableSource<? extends T> source;

        C4514a(Observer<? super T> assVar, long j, SequentialDisposable avfVar, ObservableSource<? extends T> asqVar) {
            this.downstream = assVar;
            this.f19296sd = avfVar;
            this.source = asqVar;
            this.remaining = j;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19296sd.replace(atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            long j = this.remaining;
            if (j != cjm.f20759b) {
                this.remaining = j - 1;
            }
            if (j != 0) {
                subscribeNext();
            } else {
                this.downstream.onComplete();
            }
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.f19296sd.isDisposed()) {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }
}
