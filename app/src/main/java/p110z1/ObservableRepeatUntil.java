package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.blu */
/* loaded from: classes3.dex */
public final class ObservableRepeatUntil<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final BooleanSupplier f19297b;

    public ObservableRepeatUntil(Observable<T> aslVar, BooleanSupplier aukVar) {
        super(aslVar);
        this.f19297b = aukVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SequentialDisposable avfVar = new SequentialDisposable();
        assVar.onSubscribe(avfVar);
        new C4515a(assVar, this.f19297b, avfVar, this.f18807a).subscribeNext();
    }

    /* compiled from: ObservableRepeatUntil.java */
    /* renamed from: z1.blu$a */
    /* loaded from: classes3.dex */
    static final class C4515a<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> downstream;
        final ObservableSource<? extends T> source;
        final BooleanSupplier stop;
        final SequentialDisposable upstream;

        C4515a(Observer<? super T> assVar, BooleanSupplier aukVar, SequentialDisposable avfVar, ObservableSource<? extends T> asqVar) {
            this.downstream = assVar;
            this.upstream = avfVar;
            this.source = asqVar;
            this.stop = aukVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.upstream.replace(atrVar);
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
            try {
                if (this.stop.getAsBoolean()) {
                    this.downstream.onComplete();
                } else {
                    subscribeNext();
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }
}
