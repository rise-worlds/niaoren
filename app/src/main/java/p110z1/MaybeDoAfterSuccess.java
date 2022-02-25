package p110z1;

/* renamed from: z1.bfh */
/* loaded from: classes3.dex */
public final class MaybeDoAfterSuccess<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Consumer<? super T> f18581b;

    public MaybeDoAfterSuccess(MaybeSource<T> asiVar, Consumer<? super T> aumVar) {
        super(asiVar);
        this.f18581b = aumVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4280a(asfVar, this.f18581b));
    }

    /* compiled from: MaybeDoAfterSuccess.java */
    /* renamed from: z1.bfh$a */
    /* loaded from: classes3.dex */
    static final class C4280a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18582a;

        /* renamed from: b */
        final Consumer<? super T> f18583b;

        /* renamed from: c */
        Disposable f18584c;

        C4280a(MaybeObserver<? super T> asfVar, Consumer<? super T> aumVar) {
            this.f18582a = asfVar;
            this.f18583b = aumVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18584c, atrVar)) {
                this.f18584c = atrVar;
                this.f18582a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18582a.onSuccess(t);
            try {
                this.f18583b.accept(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18582a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18582a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18584c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18584c.isDisposed();
        }
    }
}
