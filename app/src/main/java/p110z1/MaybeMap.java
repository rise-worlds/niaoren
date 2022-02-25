package p110z1;

/* renamed from: z1.bgm */
/* loaded from: classes3.dex */
public final class MaybeMap<T, R> extends AbstractMaybeWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends R> f18666b;

    public MaybeMap(MaybeSource<T> asiVar, Function<? super T, ? extends R> aunVar) {
        super(asiVar);
        this.f18666b = aunVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        this.f18529a.mo10646a(new C4309a(asfVar, this.f18666b));
    }

    /* compiled from: MaybeMap.java */
    /* renamed from: z1.bgm$a */
    /* loaded from: classes3.dex */
    static final class C4309a<T, R> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super R> f18667a;

        /* renamed from: b */
        final Function<? super T, ? extends R> f18668b;

        /* renamed from: c */
        Disposable f18669c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4309a(MaybeObserver<? super R> asfVar, Function<? super T, ? extends R> aunVar) {
            this.f18667a = asfVar;
            this.f18668b = aunVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Disposable atrVar = this.f18669c;
            this.f18669c = DisposableHelper.DISPOSED;
            atrVar.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18669c.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18669c, atrVar)) {
                this.f18669c = atrVar;
                this.f18667a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                this.f18667a.onSuccess(ObjectHelper.m9873a(this.f18668b.apply(t), "The mapper returned a null item"));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18667a.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18667a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18667a.onComplete();
        }
    }
}
