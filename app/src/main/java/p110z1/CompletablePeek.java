package p110z1;

/* renamed from: z1.ayg */
/* loaded from: classes3.dex */
public final class CompletablePeek extends Completable {

    /* renamed from: a */
    final CompletableSource f17767a;

    /* renamed from: b */
    final Consumer<? super Disposable> f17768b;

    /* renamed from: c */
    final Consumer<? super Throwable> f17769c;

    /* renamed from: d */
    final Action f17770d;

    /* renamed from: e */
    final Action f17771e;

    /* renamed from: f */
    final Action f17772f;

    /* renamed from: g */
    final Action f17773g;

    public CompletablePeek(CompletableSource arsVar, Consumer<? super Disposable> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, Action augVar2, Action augVar3, Action augVar4) {
        this.f17767a = arsVar;
        this.f17768b = aumVar;
        this.f17769c = aumVar2;
        this.f17770d = augVar;
        this.f17771e = augVar2;
        this.f17772f = augVar3;
        this.f17773g = augVar4;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17767a.mo11309a(new C3966a(arpVar));
    }

    /* compiled from: CompletablePeek.java */
    /* renamed from: z1.ayg$a */
    /* loaded from: classes3.dex */
    final class C3966a implements CompletableObserver, Disposable {

        /* renamed from: a */
        final CompletableObserver f17774a;

        /* renamed from: b */
        Disposable f17775b;

        C3966a(CompletableObserver arpVar) {
            this.f17774a = arpVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            try {
                CompletablePeek.this.f17768b.accept(atrVar);
                if (DisposableHelper.validate(this.f17775b, atrVar)) {
                    this.f17775b = atrVar;
                    this.f17774a.onSubscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                atrVar.dispose();
                this.f17775b = DisposableHelper.DISPOSED;
                EmptyDisposable.error(th, this.f17774a);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f17775b == DisposableHelper.DISPOSED) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            try {
                CompletablePeek.this.f17769c.accept(th);
                CompletablePeek.this.f17771e.mo9442a();
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f17774a.onError(th);
            m9830a();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (this.f17775b != DisposableHelper.DISPOSED) {
                try {
                    CompletablePeek.this.f17770d.mo9442a();
                    CompletablePeek.this.f17771e.mo9442a();
                    this.f17774a.onComplete();
                    m9830a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17774a.onError(th);
                }
            }
        }

        /* renamed from: a */
        void m9830a() {
            try {
                CompletablePeek.this.f17772f.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            try {
                CompletablePeek.this.f17773g.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
            this.f17775b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17775b.isDisposed();
        }
    }
}
