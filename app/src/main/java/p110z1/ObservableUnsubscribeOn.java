package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.bnh */
/* loaded from: classes3.dex */
public final class ObservableUnsubscribeOn<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Scheduler f19476b;

    public ObservableUnsubscribeOn(ObservableSource<T> asqVar, Scheduler astVar) {
        super(asqVar);
        this.f19476b = astVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4591a(assVar, this.f19476b));
    }

    /* compiled from: ObservableUnsubscribeOn.java */
    /* renamed from: z1.bnh$a */
    /* loaded from: classes3.dex */
    static final class C4591a<T> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = 1015244841293359600L;
        final Observer<? super T> downstream;
        final Scheduler scheduler;
        Disposable upstream;

        C4591a(Observer<? super T> assVar, Scheduler astVar) {
            this.downstream = assVar;
            this.scheduler = astVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (get()) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.scheduler.mo9481a(new RunnableC4592a());
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get();
        }

        /* compiled from: ObservableUnsubscribeOn.java */
        /* renamed from: z1.bnh$a$a */
        /* loaded from: classes3.dex */
        final class RunnableC4592a implements Runnable {
            RunnableC4592a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C4591a.this.upstream.dispose();
            }
        }
    }
}
