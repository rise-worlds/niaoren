package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bjx */
/* loaded from: classes3.dex */
public final class ObservableElementAtSingle<T> extends Single<T> implements FuseToObservable<T> {

    /* renamed from: a */
    final ObservableSource<T> f19075a;

    /* renamed from: b */
    final long f19076b;

    /* renamed from: c */
    final T f19077c;

    public ObservableElementAtSingle(ObservableSource<T> asqVar, long j, T t) {
        this.f19075a = asqVar;
        this.f19076b = j;
        this.f19077c = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    public void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19075a.subscribe(new C4441a(asxVar, this.f19076b, this.f19077c));
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<T> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableElementAt(this.f19075a, this.f19076b, this.f19077c, true));
    }

    /* compiled from: ObservableElementAtSingle.java */
    /* renamed from: z1.bjx$a */
    /* loaded from: classes3.dex */
    static final class C4441a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f19078a;

        /* renamed from: b */
        final long f19079b;

        /* renamed from: c */
        final T f19080c;

        /* renamed from: d */
        Disposable f19081d;

        /* renamed from: e */
        long f19082e;

        /* renamed from: f */
        boolean f19083f;

        C4441a(SingleObserver<? super T> asxVar, long j, T t) {
            this.f19078a = asxVar;
            this.f19079b = j;
            this.f19080c = t;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19081d, atrVar)) {
                this.f19081d = atrVar;
                this.f19078a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19081d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19081d.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19083f) {
                long j = this.f19082e;
                if (j == this.f19079b) {
                    this.f19083f = true;
                    this.f19081d.dispose();
                    this.f19078a.onSuccess(t);
                    return;
                }
                this.f19082e = j + 1;
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19083f) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19083f = true;
            this.f19078a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19083f) {
                this.f19083f = true;
                T t = this.f19080c;
                if (t != null) {
                    this.f19078a.onSuccess(t);
                } else {
                    this.f19078a.onError(new NoSuchElementException());
                }
            }
        }
    }
}
