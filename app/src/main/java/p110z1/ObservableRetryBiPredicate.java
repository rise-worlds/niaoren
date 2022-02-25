package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.blx */
/* loaded from: classes3.dex */
public final class ObservableRetryBiPredicate<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final BiPredicate<? super Integer, ? super Throwable> f19316b;

    public ObservableRetryBiPredicate(Observable<T> aslVar, BiPredicate<? super Integer, ? super Throwable> aujVar) {
        super(aslVar);
        this.f19316b = aujVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SequentialDisposable avfVar = new SequentialDisposable();
        assVar.onSubscribe(avfVar);
        new C4534a(assVar, this.f19316b, avfVar, this.f18807a).subscribeNext();
    }

    /* compiled from: ObservableRetryBiPredicate.java */
    /* renamed from: z1.blx$a */
    /* loaded from: classes3.dex */
    static final class C4534a<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> downstream;
        final BiPredicate<? super Integer, ? super Throwable> predicate;
        int retries;
        final ObservableSource<? extends T> source;
        final SequentialDisposable upstream;

        C4534a(Observer<? super T> assVar, BiPredicate<? super Integer, ? super Throwable> aujVar, SequentialDisposable avfVar, ObservableSource<? extends T> asqVar) {
            this.downstream = assVar;
            this.upstream = avfVar;
            this.source = asqVar;
            this.predicate = aujVar;
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
            try {
                BiPredicate<? super Integer, ? super Throwable> aujVar = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (!aujVar.mo9871a(Integer.valueOf(i), th)) {
                    this.downstream.onError(th);
                } else {
                    subscribeNext();
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.upstream.isDisposed()) {
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
