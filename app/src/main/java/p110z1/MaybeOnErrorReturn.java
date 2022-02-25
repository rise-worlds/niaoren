package p110z1;

/* renamed from: z1.bgt */
/* loaded from: classes3.dex */
public final class MaybeOnErrorReturn<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super Throwable, ? extends T> f18682b;

    public MaybeOnErrorReturn(MaybeSource<T> asiVar, Function<? super Throwable, ? extends T> aunVar) {
        super(asiVar);
        this.f18682b = aunVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4318a(asfVar, this.f18682b));
    }

    /* compiled from: MaybeOnErrorReturn.java */
    /* renamed from: z1.bgt$a */
    /* loaded from: classes3.dex */
    static final class C4318a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18683a;

        /* renamed from: b */
        final Function<? super Throwable, ? extends T> f18684b;

        /* renamed from: c */
        Disposable f18685c;

        C4318a(MaybeObserver<? super T> asfVar, Function<? super Throwable, ? extends T> aunVar) {
            this.f18683a = asfVar;
            this.f18684b = aunVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18685c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18685c.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18685c, atrVar)) {
                this.f18685c = atrVar;
                this.f18683a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18683a.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                this.f18683a.onSuccess(ObjectHelper.m9873a(this.f18684b.apply(th), "The valueSupplier returned a null value"));
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.f18683a.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18683a.onComplete();
        }
    }
}
