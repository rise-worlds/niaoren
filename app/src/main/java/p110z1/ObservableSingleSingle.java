package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bmj */
/* loaded from: classes3.dex */
public final class ObservableSingleSingle<T> extends Single<T> {

    /* renamed from: a */
    final ObservableSource<? extends T> f19369a;

    /* renamed from: b */
    final T f19370b;

    public ObservableSingleSingle(ObservableSource<? extends T> asqVar, T t) {
        this.f19369a = asqVar;
        this.f19370b = t;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    public void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19369a.subscribe(new C4555a(asxVar, this.f19370b));
    }

    /* compiled from: ObservableSingleSingle.java */
    /* renamed from: z1.bmj$a */
    /* loaded from: classes3.dex */
    static final class C4555a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super T> f19371a;

        /* renamed from: b */
        final T f19372b;

        /* renamed from: c */
        Disposable f19373c;

        /* renamed from: d */
        T f19374d;

        /* renamed from: e */
        boolean f19375e;

        C4555a(SingleObserver<? super T> asxVar, T t) {
            this.f19371a = asxVar;
            this.f19372b = t;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19373c, atrVar)) {
                this.f19373c = atrVar;
                this.f19371a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19373c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19373c.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19375e) {
                if (this.f19374d != null) {
                    this.f19375e = true;
                    this.f19373c.dispose();
                    this.f19371a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.f19374d = t;
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19375e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19375e = true;
            this.f19371a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19375e) {
                this.f19375e = true;
                T t = this.f19374d;
                this.f19374d = null;
                if (t == null) {
                    t = this.f19372b;
                }
                if (t != null) {
                    this.f19371a.onSuccess(t);
                } else {
                    this.f19371a.onError(new NoSuchElementException());
                }
            }
        }
    }
}
