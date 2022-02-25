package p110z1;

/* renamed from: z1.bjm */
/* loaded from: classes3.dex */
public final class ObservableDelaySubscriptionOther<T, U> extends Observable<T> {

    /* renamed from: a */
    final ObservableSource<? extends T> f19017a;

    /* renamed from: b */
    final ObservableSource<U> f19018b;

    public ObservableDelaySubscriptionOther(ObservableSource<? extends T> asqVar, ObservableSource<U> asqVar2) {
        this.f19017a = asqVar;
        this.f19018b = asqVar2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SequentialDisposable avfVar = new SequentialDisposable();
        assVar.onSubscribe(avfVar);
        this.f19018b.subscribe(new C4430a(avfVar, assVar));
    }

    /* compiled from: ObservableDelaySubscriptionOther.java */
    /* renamed from: z1.bjm$a */
    /* loaded from: classes3.dex */
    final class C4430a implements Observer<U> {

        /* renamed from: a */
        final SequentialDisposable f19019a;

        /* renamed from: b */
        final Observer<? super T> f19020b;

        /* renamed from: c */
        boolean f19021c;

        C4430a(SequentialDisposable avfVar, Observer<? super T> assVar) {
            this.f19019a = avfVar;
            this.f19020b = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19019a.update(atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(U u) {
            onComplete();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19021c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19021c = true;
            this.f19020b.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19021c) {
                this.f19021c = true;
                ObservableDelaySubscriptionOther.this.f19017a.subscribe(new C4431a());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: ObservableDelaySubscriptionOther.java */
        /* renamed from: z1.bjm$a$a */
        /* loaded from: classes3.dex */
        public final class C4431a implements Observer<T> {
            C4431a() {
            }

            @Override // p110z1.Observer
            public void onSubscribe(Disposable atrVar) {
                C4430a.this.f19019a.update(atrVar);
            }

            @Override // p110z1.Observer
            public void onNext(T t) {
                C4430a.this.f19020b.onNext(t);
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                C4430a.this.f19020b.onError(th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                C4430a.this.f19020b.onComplete();
            }
        }
    }
}
