package p110z1;

/* renamed from: z1.btx */
/* loaded from: classes3.dex */
public final class SafeObserver<T> implements Observer<T>, Disposable {

    /* renamed from: a */
    final Observer<? super T> f20130a;

    /* renamed from: b */
    Disposable f20131b;

    /* renamed from: c */
    boolean f20132c;

    public SafeObserver(@AbstractC3889atm Observer<? super T> assVar) {
        this.f20130a = assVar;
    }

    @Override // p110z1.Observer
    public void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (DisposableHelper.validate(this.f20131b, atrVar)) {
            this.f20131b = atrVar;
            try {
                this.f20130a.onSubscribe(this);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f20132c = true;
                try {
                    atrVar.dispose();
                    RxJavaPlugins.m9212a(th);
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    RxJavaPlugins.m9212a(new CompositeException(th, th2));
                }
            }
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        this.f20131b.dispose();
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f20131b.isDisposed();
    }

    @Override // p110z1.Observer
    public void onNext(@AbstractC3889atm T t) {
        if (!this.f20132c) {
            if (this.f20131b == null) {
                m9289a();
            } else if (t == null) {
                NullPointerException nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                try {
                    this.f20131b.dispose();
                    onError(nullPointerException);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    onError(new CompositeException(nullPointerException, th));
                }
            } else {
                try {
                    this.f20130a.onNext(t);
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    try {
                        this.f20131b.dispose();
                        onError(th2);
                    } catch (Throwable th3) {
                        Exceptions.m9983b(th3);
                        onError(new CompositeException(th2, th3));
                    }
                }
            }
        }
    }

    /* renamed from: a */
    void m9289a() {
        this.f20132c = true;
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f20130a.onSubscribe(EmptyDisposable.INSTANCE);
            try {
                this.f20130a.onError(nullPointerException);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th));
            }
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th2));
        }
    }

    @Override // p110z1.Observer
    public void onError(@AbstractC3889atm Throwable th) {
        if (this.f20132c) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20132c = true;
        if (this.f20131b == null) {
            NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
            try {
                this.f20130a.onSubscribe(EmptyDisposable.INSTANCE);
                try {
                    this.f20130a.onError(new CompositeException(th, nullPointerException));
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    RxJavaPlugins.m9212a(new CompositeException(th, nullPointerException, th2));
                }
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                RxJavaPlugins.m9212a(new CompositeException(th, nullPointerException, th3));
            }
        } else {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            try {
                this.f20130a.onError(th);
            } catch (Throwable th4) {
                Exceptions.m9983b(th4);
                RxJavaPlugins.m9212a(new CompositeException(th, th4));
            }
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!this.f20132c) {
            this.f20132c = true;
            if (this.f20131b == null) {
                m9288b();
                return;
            }
            try {
                this.f20130a.onComplete();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }
    }

    /* renamed from: b */
    void m9288b() {
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f20130a.onSubscribe(EmptyDisposable.INSTANCE);
            try {
                this.f20130a.onError(nullPointerException);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th));
            }
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th2));
        }
    }
}
