package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ble */
/* loaded from: classes3.dex */
public final class ObservableMergeWithCompletable<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final CompletableSource f19236b;

    public ObservableMergeWithCompletable(Observable<T> aslVar, CompletableSource arsVar) {
        super(aslVar);
        this.f19236b = arsVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        C4494a aVar = new C4494a(assVar);
        assVar.onSubscribe(aVar);
        this.f18807a.subscribe(aVar);
        this.f19236b.mo11309a(aVar.otherObserver);
    }

    /* compiled from: ObservableMergeWithCompletable.java */
    /* renamed from: z1.ble$a */
    /* loaded from: classes3.dex */
    static final class C4494a<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -4592979584110982903L;
        final Observer<? super T> downstream;
        volatile boolean mainDone;
        volatile boolean otherDone;
        final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
        final C4495a otherObserver = new C4495a(this);
        final AtomicThrowable error = new AtomicThrowable();

        C4494a(Observer<? super T> assVar) {
            this.downstream = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.mainDisposable, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            HalfSerializer.m9427a(this.downstream, t, this, this.error);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.mainDisposable);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.mainDone = true;
            if (this.otherDone) {
                HalfSerializer.m9425a(this.downstream, this, this.error);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.mainDisposable.get());
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.mainDisposable);
            DisposableHelper.dispose(this.otherObserver);
        }

        void otherError(Throwable th) {
            DisposableHelper.dispose(this.mainDisposable);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        void otherComplete() {
            this.otherDone = true;
            if (this.mainDone) {
                HalfSerializer.m9425a(this.downstream, this, this.error);
            }
        }

        /* compiled from: ObservableMergeWithCompletable.java */
        /* renamed from: z1.ble$a$a */
        /* loaded from: classes3.dex */
        static final class C4495a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -2935427570954647017L;
            final C4494a<?> parent;

            C4495a(C4494a<?> aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
