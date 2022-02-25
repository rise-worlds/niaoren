package p110z1;

/* renamed from: z1.bfj */
/* loaded from: classes3.dex */
public final class MaybeDoOnEvent<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final BiConsumer<? super T, ? super Throwable> f18586b;

    public MaybeDoOnEvent(MaybeSource<T> asiVar, BiConsumer<? super T, ? super Throwable> auhVar) {
        super(asiVar);
        this.f18586b = auhVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4282a(asfVar, this.f18586b));
    }

    /* compiled from: MaybeDoOnEvent.java */
    /* renamed from: z1.bfj$a */
    /* loaded from: classes3.dex */
    static final class C4282a<T> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18587a;

        /* renamed from: b */
        final BiConsumer<? super T, ? super Throwable> f18588b;

        /* renamed from: c */
        Disposable f18589c;

        C4282a(MaybeObserver<? super T> asfVar, BiConsumer<? super T, ? super Throwable> auhVar) {
            this.f18587a = asfVar;
            this.f18588b = auhVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18589c.dispose();
            this.f18589c = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18589c.isDisposed();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18589c, atrVar)) {
                this.f18589c = atrVar;
                this.f18587a.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f18589c = DisposableHelper.DISPOSED;
            try {
                this.f18588b.mo9895a(t, null);
                this.f18587a.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18587a.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18589c = DisposableHelper.DISPOSED;
            try {
                this.f18588b.mo9895a(null, th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f18587a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18589c = DisposableHelper.DISPOSED;
            try {
                this.f18588b.mo9895a(null, null);
                this.f18587a.onComplete();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18587a.onError(th);
            }
        }
    }
}
