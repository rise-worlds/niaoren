package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bkz */
/* loaded from: classes3.dex */
public final class ObservableLastSingle<T> extends Single<T> {

    /* renamed from: a */
    final ObservableSource<T> f19217a;

    /* renamed from: b */
    final T f19218b;

    public ObservableLastSingle(ObservableSource<T> asqVar, T t) {
        this.f19217a = asqVar;
        this.f19218b = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19217a.subscribe(new C4489a(asxVar, this.f19218b));
    }

    /* compiled from: ObservableLastSingle.java */
    /* renamed from: z1.bkz$a */
    /* loaded from: classes3.dex */
    static final class C4489a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f19219a;

        /* renamed from: b */
        final T f19220b;

        /* renamed from: c */
        Disposable f19221c;

        /* renamed from: d */
        T f19222d;

        C4489a(SingleObserver<? super T> asxVar, T t) {
            this.f19219a = asxVar;
            this.f19220b = t;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19221c.dispose();
            this.f19221c = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19221c == DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19221c, atrVar)) {
                this.f19221c = atrVar;
                this.f19219a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19222d = t;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19221c = DisposableHelper.DISPOSED;
            this.f19222d = null;
            this.f19219a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19221c = DisposableHelper.DISPOSED;
            T t = this.f19222d;
            if (t != null) {
                this.f19222d = null;
                this.f19219a.onSuccess(t);
                return;
            }
            T t2 = this.f19220b;
            if (t2 != null) {
                this.f19219a.onSuccess(t2);
            } else {
                this.f19219a.onError(new NoSuchElementException());
            }
        }
    }
}
