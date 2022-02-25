package p110z1;

/* renamed from: z1.bgr */
/* loaded from: classes3.dex */
public final class MaybeOnErrorComplete<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Predicate<? super Throwable> f18674b;

    public MaybeOnErrorComplete(MaybeSource<T> asiVar, Predicate<? super Throwable> auxVar) {
        super(asiVar);
        this.f18674b = auxVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4315a(asfVar, this.f18674b));
    }

    /* compiled from: MaybeOnErrorComplete.java */
    /* renamed from: z1.bgr$a */
    /* loaded from: classes3.dex */
    static final class C4315a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18675a;

        /* renamed from: b */
        final Predicate<? super Throwable> f18676b;

        /* renamed from: c */
        Disposable f18677c;

        C4315a(MaybeObserver<? super T> asfVar, Predicate<? super Throwable> auxVar) {
            this.f18675a = asfVar;
            this.f18676b = auxVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18677c, atrVar)) {
                this.f18677c = atrVar;
                this.f18675a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18675a.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                if (this.f18676b.test(th)) {
                    this.f18675a.onComplete();
                } else {
                    this.f18675a.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.f18675a.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18675a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18677c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18677c.isDisposed();
        }
    }
}
