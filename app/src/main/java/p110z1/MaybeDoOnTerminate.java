package p110z1;

/* renamed from: z1.bfk */
/* loaded from: classes3.dex */
public final class MaybeDoOnTerminate<T> extends Maybe<T> {

    /* renamed from: a */
    final MaybeSource<T> f18590a;

    /* renamed from: b */
    final Action f18591b;

    public MaybeDoOnTerminate(MaybeSource<T> asiVar, Action augVar) {
        this.f18590a = asiVar;
        this.f18591b = augVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18590a.mo10646a(new C4283a(asfVar));
    }

    /* compiled from: MaybeDoOnTerminate.java */
    /* renamed from: z1.bfk$a */
    /* loaded from: classes3.dex */
    final class C4283a implements MaybeObserver<T> {

        /* renamed from: a */
        final MaybeObserver<? super T> f18592a;

        C4283a(MaybeObserver<? super T> asfVar) {
            this.f18592a = asfVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f18592a.onSubscribe(atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                MaybeDoOnTerminate.this.f18591b.mo9442a();
                this.f18592a.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18592a.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                MaybeDoOnTerminate.this.f18591b.mo9442a();
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f18592a.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            try {
                MaybeDoOnTerminate.this.f18591b.mo9442a();
                this.f18592a.onComplete();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18592a.onError(th);
            }
        }
    }
}
