package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.ayo */
/* loaded from: classes3.dex */
public final class CompletableToSingle<T> extends Single<T> {

    /* renamed from: a */
    final CompletableSource f17803a;

    /* renamed from: b */
    final Callable<? extends T> f17804b;

    /* renamed from: c */
    final T f17805c;

    public CompletableToSingle(CompletableSource arsVar, Callable<? extends T> callable, T t) {
        this.f17803a = arsVar;
        this.f17805c = t;
        this.f17804b = callable;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f17803a.mo11309a(new C3976a(asxVar));
    }

    /* compiled from: CompletableToSingle.java */
    /* renamed from: z1.ayo$a */
    /* loaded from: classes3.dex */
    final class C3976a implements CompletableObserver {

        /* renamed from: b */
        private final SingleObserver<? super T> f17807b;

        C3976a(SingleObserver<? super T> asxVar) {
            this.f17807b = asxVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            T t;
            if (CompletableToSingle.this.f17804b != null) {
                try {
                    t = (Object) CompletableToSingle.this.f17804b.call();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17807b.onError(th);
                    return;
                }
            } else {
                t = CompletableToSingle.this.f17805c;
            }
            if (t == null) {
                this.f17807b.onError(new NullPointerException("The value supplied is null"));
            } else {
                this.f17807b.onSuccess(t);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f17807b.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17807b.onSubscribe(atrVar);
        }
    }
}
