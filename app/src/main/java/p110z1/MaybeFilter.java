package p110z1;

/* renamed from: z1.bfp */
/* loaded from: classes3.dex */
public final class MaybeFilter<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Predicate<? super T> f18600b;

    public MaybeFilter(MaybeSource<T> asiVar, Predicate<? super T> auxVar) {
        super(asiVar);
        this.f18600b = auxVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4286a(asfVar, this.f18600b));
    }

    /* compiled from: MaybeFilter.java */
    /* renamed from: z1.bfp$a */
    /* loaded from: classes3.dex */
    static final class C4286a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18601a;

        /* renamed from: b */
        final Predicate<? super T> f18602b;

        /* renamed from: c */
        Disposable f18603c;

        C4286a(MaybeObserver<? super T> asfVar, Predicate<? super T> auxVar) {
            this.f18601a = asfVar;
            this.f18602b = auxVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Disposable atrVar = this.f18603c;
            this.f18603c = DisposableHelper.DISPOSED;
            atrVar.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18603c.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18603c, atrVar)) {
                this.f18603c = atrVar;
                this.f18601a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                if (this.f18602b.test(t)) {
                    this.f18601a.onSuccess(t);
                } else {
                    this.f18601a.onComplete();
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18601a.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18601a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18601a.onComplete();
        }
    }
}
