package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bmp */
/* loaded from: classes3.dex */
public final class ObservableSubscribeOn<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Scheduler f19402b;

    public ObservableSubscribeOn(ObservableSource<T> asqVar, Scheduler astVar) {
        super(asqVar);
        this.f19402b = astVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        C4562a aVar = new C4562a(assVar);
        assVar.onSubscribe(aVar);
        aVar.setDisposable(this.f19402b.mo9481a(new RunnableC4563b(aVar)));
    }

    /* compiled from: ObservableSubscribeOn.java */
    /* renamed from: z1.bmp$a */
    /* loaded from: classes3.dex */
    static final class C4562a<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8094547886072529208L;
        final Observer<? super T> downstream;
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        C4562a(Observer<? super T> assVar) {
            this.downstream = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
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
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        void setDisposable(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }
    }

    /* compiled from: ObservableSubscribeOn.java */
    /* renamed from: z1.bmp$b */
    /* loaded from: classes3.dex */
    final class RunnableC4563b implements Runnable {

        /* renamed from: b */
        private final C4562a<T> f19404b;

        RunnableC4563b(C4562a<T> aVar) {
            this.f19404b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            ObservableSubscribeOn.this.f18807a.subscribe(this.f19404b);
        }
    }
}
