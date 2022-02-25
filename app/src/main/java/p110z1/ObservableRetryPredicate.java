package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bly */
/* loaded from: classes3.dex */
public final class ObservableRetryPredicate<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Predicate<? super Throwable> f19317b;

    /* renamed from: c */
    final long f19318c;

    public ObservableRetryPredicate(Observable<T> aslVar, long j, Predicate<? super Throwable> auxVar) {
        super(aslVar);
        this.f19317b = auxVar;
        this.f19318c = j;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SequentialDisposable avfVar = new SequentialDisposable();
        assVar.onSubscribe(avfVar);
        new C4535a(assVar, this.f19318c, this.f19317b, avfVar, this.f18807a).subscribeNext();
    }

    /* compiled from: ObservableRetryPredicate.java */
    /* renamed from: z1.bly$a */
    /* loaded from: classes3.dex */
    static final class C4535a<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> downstream;
        final Predicate<? super Throwable> predicate;
        long remaining;
        final ObservableSource<? extends T> source;
        final SequentialDisposable upstream;

        C4535a(Observer<? super T> assVar, long j, Predicate<? super Throwable> auxVar, SequentialDisposable avfVar, ObservableSource<? extends T> asqVar) {
            this.downstream = assVar;
            this.upstream = avfVar;
            this.source = asqVar;
            this.predicate = auxVar;
            this.remaining = j;
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
            long j = this.remaining;
            if (j != cjm.f20759b) {
                this.remaining = j - 1;
            }
            if (j == 0) {
                this.downstream.onError(th);
                return;
            }
            try {
                if (!this.predicate.test(th)) {
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
