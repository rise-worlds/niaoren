package p110z1;

/* renamed from: z1.bgu */
/* loaded from: classes3.dex */
public final class MaybePeek<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Consumer<? super Disposable> f18686b;

    /* renamed from: c */
    final Consumer<? super T> f18687c;

    /* renamed from: d */
    final Consumer<? super Throwable> f18688d;

    /* renamed from: e */
    final Action f18689e;

    /* renamed from: f */
    final Action f18690f;

    /* renamed from: g */
    final Action f18691g;

    public MaybePeek(MaybeSource<T> asiVar, Consumer<? super Disposable> aumVar, Consumer<? super T> aumVar2, Consumer<? super Throwable> aumVar3, Action augVar, Action augVar2, Action augVar3) {
        super(asiVar);
        this.f18686b = aumVar;
        this.f18687c = aumVar2;
        this.f18688d = aumVar3;
        this.f18689e = augVar;
        this.f18690f = augVar2;
        this.f18691g = augVar3;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4319a(asfVar, this));
    }

    /* compiled from: MaybePeek.java */
    /* renamed from: z1.bgu$a */
    /* loaded from: classes3.dex */
    static final class C4319a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18692a;

        /* renamed from: b */
        final MaybePeek<T> f18693b;

        /* renamed from: c */
        Disposable f18694c;

        C4319a(MaybeObserver<? super T> asfVar, MaybePeek<T> bguVar) {
            this.f18692a = asfVar;
            this.f18693b = bguVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            try {
                this.f18693b.f18691g.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
            this.f18694c.dispose();
            this.f18694c = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18694c.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18694c, atrVar)) {
                try {
                    this.f18693b.f18686b.accept(atrVar);
                    this.f18694c = atrVar;
                    this.f18692a.onSubscribe(this);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    atrVar.dispose();
                    this.f18694c = DisposableHelper.DISPOSED;
                    EmptyDisposable.error(th, this.f18692a);
                }
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            if (this.f18694c != DisposableHelper.DISPOSED) {
                try {
                    this.f18693b.f18687c.accept(t);
                    this.f18694c = DisposableHelper.DISPOSED;
                    this.f18692a.onSuccess(t);
                    m9693a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    m9692a(th);
                }
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f18694c == DisposableHelper.DISPOSED) {
                RxJavaPlugins.m9212a(th);
            } else {
                m9692a(th);
            }
        }

        /* renamed from: a */
        void m9692a(Throwable th) {
            try {
                this.f18693b.f18688d.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f18694c = DisposableHelper.DISPOSED;
            this.f18692a.onError(th);
            m9693a();
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            if (this.f18694c != DisposableHelper.DISPOSED) {
                try {
                    this.f18693b.f18689e.mo9442a();
                    this.f18694c = DisposableHelper.DISPOSED;
                    this.f18692a.onComplete();
                    m9693a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    m9692a(th);
                }
            }
        }

        /* renamed from: a */
        void m9693a() {
            try {
                this.f18693b.f18690f.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }
    }
}
