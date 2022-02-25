package p110z1;

/* renamed from: z1.bpx */
/* loaded from: classes3.dex */
public final class SingleOnErrorReturn<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<? extends T> f19817a;

    /* renamed from: b */
    final Function<? super Throwable, ? extends T> f19818b;

    /* renamed from: c */
    final T f19819c;

    public SingleOnErrorReturn(SingleSource<? extends T> ataVar, Function<? super Throwable, ? extends T> aunVar, T t) {
        this.f19817a = ataVar;
        this.f19818b = aunVar;
        this.f19819c = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19817a.mo10018a(new C4693a(asxVar));
    }

    /* compiled from: SingleOnErrorReturn.java */
    /* renamed from: z1.bpx$a */
    /* loaded from: classes3.dex */
    final class C4693a implements SingleObserver<T> {

        /* renamed from: b */
        private final SingleObserver<? super T> f19821b;

        C4693a(SingleObserver<? super T> asxVar) {
            this.f19821b = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            T t;
            if (SingleOnErrorReturn.this.f19818b != null) {
                try {
                    t = (Object) SingleOnErrorReturn.this.f19818b.apply(th);
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.f19821b.onError(new CompositeException(th, th2));
                    return;
                }
            } else {
                t = SingleOnErrorReturn.this.f19819c;
            }
            if (t == null) {
                NullPointerException nullPointerException = new NullPointerException("Value supplied was null");
                nullPointerException.initCause(th);
                this.f19821b.onError(nullPointerException);
                return;
            }
            this.f19821b.onSuccess(t);
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19821b.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19821b.onSuccess(t);
        }
    }
}
